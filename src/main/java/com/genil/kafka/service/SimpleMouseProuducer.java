package com.genil.kafka.service;

import com.genil.kafka.config.KafkaClusterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created on Sun, 3/7/21 at 2:08 PM by Genil. Producer service class to send messages in string
 * format to local.genil.demo.mouseposition.0 topic.
 */
@Component
@Slf4j
public class SimpleMouseProuducer {
  @Autowired KafkaClusterConfig kafkaClusterConfig;

  @Autowired
  @Qualifier(value = "kafkaTemplateForString")
  private KafkaTemplate kafkaTemplate;

  /** @param mousePosition */
  public void postMessage(final String mousePosition) {
    // No transactions associated with simple sending of messages.
    ListenableFuture<SendResult<String, String>> listenableFuture =
        kafkaTemplate.send(kafkaClusterConfig.getSimpleTopic(), null, mousePosition);

    listenableFuture.addCallback(
        new ListenableFutureCallback<SendResult<String, String>>() {

          @Override
          public void onSuccess(SendResult<String, String> result) {
            log.info(
                "message sent, partition={}, offset={}",
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().offset());
          }

          @Override
          public void onFailure(Throwable throwable) {
            log.warn("Sorry. Unable to send mouse x, y positions ={}", mousePosition, throwable);
          }
        });
  }
}
