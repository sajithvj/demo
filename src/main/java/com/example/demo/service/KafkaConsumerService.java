package com.example.demo.service;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaConsumerService {

//    final Properties props;
//    KafkaConsumer<String, String> consumer;
//    public KafkaConsumerService() throws IOException {
//        props = loadConfigConsumer("C:\\Users\\HP\\Downloads\\demo\\demo\\src\\main\\resources\\client.properties");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//    }
//    public static Properties loadConfigConsumer(final String configFile) throws IOException {
//        if (!Files.exists(Paths.get(configFile))) {
//            throw new IOException(configFile + " not found.");
//        }
//        final Properties cfg = new Properties();
//        try (InputStream inputStream = new FileInputStream(configFile)) {
//            cfg.load(inputStream);
//        }
//        return cfg;
//    }
//
//    public void consumeKafka(){
//        consumer = new KafkaConsumer<>(props);
//        consumer.subscribe(Arrays.asList("first_test_topic"));
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf("key = %s, value = %s%n", record.key(), record.value());
//            }
//        }
//    }
  @KafkaListener(topics = "first_test_topic",groupId = "kafka-java-getting-started")
  public void consume(String message) {System.out.println("Received Message: "+message);}
  @KafkaListener(topics = "topic_1",groupId = "kafka-group-1",clientIdPrefix = "string",containerFactory = "kafkaListenerContainerFactory")
  public void consumeTopic(ConsumerRecord<String,String> message, @Payload String payload) {
    System.out.println("Payload"+payload);
    System.out.println("Received String Message: " +message.partition() +"  "+message.key()+"  "+message.value());

  }

  @KafkaListener(topics = "topic_1",groupId = "kafka-group-1",clientIdPrefix = "object",containerFactory = "tripsKafkaListenerContainerFactory")
  public void consumeJsonTopic(ConsumerRecord<String,Object> message, @Payload String payload) {
    System.out.println("Payload"+payload);
    System.out.println("Received Json Message: " +message.key()+"  "+message.value());

  }

  @KafkaListener(topics = "topic_1",groupId = "kafka-group-1",clientIdPrefix = "bytearray",containerFactory = "byteKafkaListenerContainerFactory")
  public void consumeByteTopic(ConsumerRecord<String,byte[]> message, @Payload String payload) {
    System.out.println("Payload"+payload);
    System.out.println("Received Byte Message: " +message.key()+"  "+message.value());

  }

//  @KafkaListener(topics = "advice-topic", clientIdPrefix = "string",
//          containerFactory = "kafkaListenerStringContainerFactory")
//  public void listenasString(ConsumerRecord<String, String> cr,
//                             @Payload String payload) {
//    logger.info("Logger 2 [String] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
//            typeIdHeader(cr.headers()), payload, cr.toString());
//    latch.countDown();
//  }
//
//  @KafkaListener(topics = "advice-topic", clientIdPrefix = "bytearray",
//          containerFactory = "kafkaListenerByteArrayContainerFactory")
//  public void listenAsByteArray(ConsumerRecord<String, byte[]> cr,
//                                @Payload byte[] payload) {
//    logger.info("Logger 3 [ByteArray] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
//            typeIdHeader(cr.headers()), payload, cr.toString());
//    latch.countDown();
//  }
//
//  private static String typeIdHeader(Headers headers) {
//    return StreamSupport.stream(headers.spliterator(), false)
//            .filter(header -> header.key().equals("__TypeId__"))
//            .findFirst().map(header -> new String(header.value())).orElse("N/A");
//  }
}


