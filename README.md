# Log-analysis-platform

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
