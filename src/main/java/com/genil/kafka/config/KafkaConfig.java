package com.genil.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
/**
 * Created on Sun, 3/7/21 at 1:09 PM by Genil.
 */
@Configuration
public class KafkaConfig {

    @Autowired
    KafkaClusterConfig clusterConfiguration;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, clusterConfiguration.getBootstrapServer());
        return new KafkaAdmin(configs);
    }
}
