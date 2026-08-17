package com.example.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class ConnectBasicTest {
    public void connectBasic() {
        RedisURI uri = RedisURI.Builder
                .redis("microstylish-lime-stone-37711.db.redis.io", 16219)
                .withAuthentication("default", "F5qJ2hpY8ZScBhMY9AfXsVZBj78q76qj")
                .build();
        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();

        commands.set("foo", "bar");
        String result = commands.get("foo");
        System.out.println(result); // >>> bar

        connection.close();

        client.shutdown();
    }

    public static void main(String[] args){
        ConnectBasicTest basicTest = new ConnectBasicTest();
        basicTest.connectBasic();
    }
}
