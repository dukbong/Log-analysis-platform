# Log-analysis-platform

<details>
<summary>docker-compose.yaml</summary>
  
```yaml
version: '3'
services:
  zookeeper:
    image: zookeeper:3.6.3
    ports:
      - "2181:2181"
    environment:
        ZOOKEEPER_CLIENT_PORT: 2181
  kafka:
    image: wurstmeister/kafka:2.13-2.7.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_HOST://kafka:9093
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:9093
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock

  mysql:
    image: mysql:latest
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: rootpwd
      MYSQL_DATABASE: maindata
      MYSQL_USER: user
      MYSQL_PASSWORD: pwd
    volumes:
      - mysql-data:/var/lib/mysql

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.0
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - cluster.name=docker-cluster
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ulimits:
      memlock:
        soft: -1
        hard: -1
    ports:
      - "9200:9200"
      - "9300:9300"
    volumes:
      - esdata:/usr/share/elasticsearch/data

volumes:
  mysql-data:
  esdata:
```
</details>

### 1. 로그 수집
- 사용 기술: `Spring for Apache Kafka`, `logback-kafka-appender`

- **Kafka 사용 이유**
  - 로그 데이터는 대량을 발생할 수 있으며, Kafka는 대규모 데이터를 안정적으로 처리할 수 있는 장점이 있습니다.

- **logback-kafka-appender 사용 이유**
  - logback의 강력한 로깅 기능을 그대로 활용하면서 Kafka로 로그 데이터를 효율적으로 전송할 수 있습니다.
  - 비동기적으로 로그 메시지를 Kafka로 전달할 수 있지만, 이번 프로젝트에서는 순서가 중요하여 사용하지 않았습니다.

<details>
<summary>logback.xml 설정 내용 펼치기/접기</summary>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="TERMINAL" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</Pattern>
        </layout>
    </appender>

    <appender name="kafkaAppender" class="com.github.danielwegener.logback.kafka.KafkaAppender">
        <encoder class="com.github.danielwegener.logback.kafka.encoding.LayoutKafkaMessageEncoder">
            <layout class="ch.qos.logback.classic.PatternLayout">
                <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %M - %msg%n</Pattern>
            </layout>
        </encoder>
        <topic>test-logs</topic>
        <producerConfig>bootstrap.servers=localhost:9092</producerConfig>
    </appender>

    <logger name="org.apache.kafka" level="ERROR"/>
    <logger name="com.example.kafkadashboard" level="DEBUG">
        <appender-ref ref="kafkaAppender"/>
    </logger>

    <root level="DEBUG">
        <appender-ref ref="TERMINAL"/>
    </root>

    <root level="INFO">
        <appender-ref ref="kafkaAppender" />
    </root>

</configuration>
```
</details>

### 2. 로그 저장
- 사용 기술 : `ElasticSearch`

- **ElasticSearch 사용 이유**
  - 실시간을 데이터를 색인하고 검색할 수 있는 검색 엔진으로 데이터를 빠르게 쿼리하고 분석해야 하기 때문에 사용합니다.
  - ElasticSearch에서 logs로 구분해서 데이터 읽기 : `curl -XGET 'http://localhost:9200/logs/_search?q=*'`
