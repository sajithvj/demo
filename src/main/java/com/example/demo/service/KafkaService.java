package com.example.demo.service;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.TripsDto;
import com.example.demo.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class KafkaService {


    String kafkaTopic = "topic_1";
    int messagesPerRequest=1;
    private CountDownLatch latch;
    private final KafkaTemplate<String,Object> kafkaTemplate;


    @Autowired
    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    //KafkaProducer<String,TripsDto> producer;
    //final Properties props ;

//    public KafkaService() throws IOException {
//        props = loadConfig("C:\\Users\\HP\\Downloads\\demo\\demo\\src\\main\\resources\\client.properties");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerializer.class);
//
//    }

//    public static Properties loadConfig(final String configFile) throws IOException {
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
    public void publishKafka(TripsDto tripsDto)throws Exception{
        latch = new CountDownLatch(messagesPerRequest);
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> this.kafkaTemplate.send(kafkaTopic, String.valueOf(i),
                        new PracticalAdvice("Trips Details", tripsDto))
                );
        latch.await(60, TimeUnit.SECONDS);

        this.kafkaTemplate.send(kafkaTopic,1, String.valueOf(1),
                new StringPracticalAdvice("String Details","test string"));
        kafkaTemplate.flush();
        //kafkaTemplate.send(kafkaTopic,tripsDto);
    }

    public void publishBooks(BooksDto books)throws Exception{
        latch = new CountDownLatch(messagesPerRequest);
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> this.kafkaTemplate.send(kafkaTopic, String.valueOf(i),
                        new PracticalAdvice("Books Details", books))
                );
        latch.await(60, TimeUnit.SECONDS);

        this.kafkaTemplate.send(kafkaTopic,1, String.valueOf(1),
                new StringPracticalAdvice("String Details","test string"));
        kafkaTemplate.flush();
        //kafkaTemplate.send(kafkaTopic,tripsDto);
    }
}
