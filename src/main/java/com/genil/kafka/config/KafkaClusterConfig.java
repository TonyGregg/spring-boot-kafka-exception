package com.genil.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created on Sun, 3/7/21 at 12:58 PM by Genil.
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.cluster")
@Data
public class KafkaClusterConfig {
  String bootstrapServer;
  String simpleTopic;
  String jsonTopic;
}
