package com.example.demo;


import com.example.demo.dto.TripsDto;
import com.example.demo.serializer.KafkaSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
  @Value("${spring.kafka.properties.bootstrap.servers}")
  private String bootStrapServers;

//  @Value("${spring.kafka.properties.sasl.jaas.config}")
//  private String saslconfig;

//  @Value("${spring.kafka.properties.security.protocol}")
//  private String securityprotocol;

  @Value("${spring.kafka.properties.session.timeout.ms}")
  private String sessionTimeout;

//  @Value("${spring.kafka.properties.schema.registry.url}")
//  private String schemaRegistry;

//  @Value("${spring.kafka.properties.basic.auth.credentials.source}")
//  private String basicauthcredentialssource;

//  @Value("${spring.kafka.properties.basic.auth.user.info}")
//  private String basicauthuserinfo;

//  private String username="T4T2MGN5CQ6IC5YO";
//
//  private String password="cLg31yb411bPDPC0zelQ8q/l8yhjdd3QLP4kcdgQDgiSJCWWcaBEkW5vedH399i0";

  @Bean(name="consumerFactory")
  public ConsumerFactory<String,String> consumerFactory(){
    Map<String,Object> props = new HashMap<>();
   props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //props.put("sasl.jaas.config",String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';",username,password));
    props.put("sasl.mechanism","PLAIN");
    //props.put("client.dns.lookup","use_all_dns_ips");
    //props.put("security.protocol",securityprotocol);
    props.put("session.timeout.ms",sessionTimeout);
    //props.put("schema.registry.url",schemaRegistry);
    //props.put("basic.auth.credentials.source",basicauthcredentialssource);
    //props.put("basic.auth.user.info",basicauthuserinfo);
    return new DefaultKafkaConsumerFactory<>(props);

  }
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String>
  kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, TripsDto> tripsDtoConsumerFactory(){
    Map<String,Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
   //props.put("sasl.jaas.config",String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';",username,password));
    props.put("sasl.mechanism","PLAIN");
    //props.put("client.dns.lookup","use_all_dns_ips");
    //props.put("security.protocol",securityprotocol);
    props.put("session.timeout.ms",sessionTimeout);
    //props.put("schema.registry.url",schemaRegistry);
    //props.put("basic.auth.credentials.source",basicauthcredentialssource);
    //props.put("basic.auth.user.info",basicauthuserinfo);
    return new DefaultKafkaConsumerFactory<>(props);

  }
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, TripsDto>
  tripsKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, TripsDto> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(tripsDtoConsumerFactory());
    return factory;
  }
    @Bean
    public ConsumerFactory<String, TripsDto> byteContainerFactory(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaSerializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
//        props.put("sasl.jaas.config",String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';",username,password));
        props.put("sasl.mechanism","PLAIN");
//        props.put("client.dns.lookup","use_all_dns_ips");
//        props.put("security.protocol",securityprotocol);
        props.put("session.timeout.ms",sessionTimeout);
//        props.put("schema.registry.url",schemaRegistry);
//        props.put("basic.auth.credentials.source",basicauthcredentialssource);
//        props.put("basic.auth.user.info",basicauthuserinfo);
        return new DefaultKafkaConsumerFactory<>(props);

    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TripsDto>
    byteKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TripsDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(byteContainerFactory());
        return factory;
    }

}
