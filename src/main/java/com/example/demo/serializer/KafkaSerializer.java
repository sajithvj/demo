package com.example.demo.serializer;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.TripsDto;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;


import java.io.*;
import java.util.Map;

public class KafkaSerializer implements Closeable,AutoCloseable, Serializer<TripsDto>, Deserializer<TripsDto> {

    @Override
    public byte[] serialize(String topic, TripsDto data) {
        byte[] output = null;
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream ob = new ObjectOutputStream(b)) {
                ob.writeObject(data);
            }
            output = b.toByteArray();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return output;
    }


    @Override
    public byte[] serialize(String topic, Headers headers, TripsDto data) {
        return Serializer.super.serialize(topic, headers, data);
    }


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }



    @Override
    public TripsDto deserialize(String topic, byte[] data) {
        TripsDto output = null;
        try(ByteArrayInputStream b = new ByteArrayInputStream(data)){
            try(ObjectInputStream o= new ObjectInputStream(b)){
                output = (TripsDto) o.readObject();
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public TripsDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
        Serializer.super.close();
    }
}
