package com.agisoft.cucumber.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisBackedCache {

    private final StatefulRedisConnection<String, String> connection;

    public RedisBackedCache(String hostname, Integer port) {
        final String connectionString = String.format("redis://%s:%d/0", hostname, port);
        System.out.println(connectionString);
        RedisClient client = RedisClient.create(connectionString);
        connection = client.connect();
    }

    public String get(String key) {
        return connection.sync().get(key);
    }

    public void put(String key, String value) {
        connection.sync().set(key, value);
    }

//    public static void main(String[] args) {
//        RedisBackedCache underTest = new RedisBackedCache("localhost", 32013);
//        underTest.put("test", "example");
//        String retrieved = underTest.get("test");
//        System.out.println(retrieved);
//    }
}
