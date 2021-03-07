package com.genil.kafka.controller;

import com.genil.kafka.service.SimpleMouseProuducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on Sun, 3/7/21 at 2:16 PM by Genil.
 */
@RestController
public class ProducerController {
  @Autowired
  private SimpleMouseProuducer simpleMouseProuducer;

  @PostMapping(value = "/mousePosition")
  public void postMousePosition(@RequestBody String mousePosition) {
    simpleMouseProuducer.postMessage(mousePosition);
  }

}
