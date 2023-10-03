package com.example.demo;

import com.example.demo.serializer.KafkaSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaProducerConfig
{

    @Value("${spring.kafka.properties.bootstrap.servers}")
    private String bootStrapServers;
//    @Value("${spring.kafka.properties.sasl.jaas.config}")
//    private String saslconfig;
//    @Value("${spring.kafka.properties.security.protocol}")
//    private String securityprotocol;

    @Value("${spring.kafka.properties.session.timeout.ms}")
    private String sessionTimeout;

    @Value("${spring.kafka.properties.acks}")
    private String acks;

//    @Value("${spring.kafka.properties.schema.registry.url}")
//    private String schemaRegistry;
//
//    @Value("${spring.kafka.properties.basic.auth.credentials.source}")
//    private String basicauthcredentialssource;
//
//    @Value("${spring.kafka.properties.basic.auth.user.info}")
//    private String basicauthuserinfo;
//
//    private String username="T4T2MGN5CQ6IC5YO";
//
//    private String password="cLg31yb411bPDPC0zelQ8q/l8yhjdd3QLP4kcdgQDgiSJCWWcaBEkW5vedH399i0";

    private String topicName="topic_1";


    public Map producerConfigs(){
        Map <String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //props.put("sasl.jaas.config",String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';",username,password));
        props.put("sasl.mechanism","PLAIN");
        //props.put("client.dns.lookup","use_all_dns_ips");
        //props.put("security.protocol",securityprotocol);
        //props.put("session.timeout.ms",sessionTimeout);
        //props.put("schema.registry.url",schemaRegistry);
        props.put("acks",acks);
        //props.put("basic.auth.credentials.source",basicauthcredentialssource);
        //props.put("basic.auth.user.info",basicauthuserinfo);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        return props;
    }
    @Bean(name = "producerFactory")
    public ProducerFactory<String, Object> producerFactory() { return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean(name = "kafkaTemplate")
    public KafkaTemplate<String, Object> kafkaTemplate() {return new KafkaTemplate<>(producerFactory());}

    @Bean(name="adviceTopic")
    public NewTopic adviceTopic() {
        return new NewTopic(topicName, 2, (short) 1);
    }
}
