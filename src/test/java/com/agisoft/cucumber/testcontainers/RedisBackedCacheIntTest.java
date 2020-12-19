package com.agisoft.cucumber.testcontainers;


import com.agisoft.cucumber.redis.RedisBackedCache;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.assertEquals;

public class RedisBackedCacheIntTest {

    private RedisBackedCache underTest;

    @ClassRule
    public static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(
            6379);

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    @Before
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();
        // Now we have an address and port for Redis, no matter where it is running
        underTest = new RedisBackedCache(address, port);
//        System.out.println("START REDIS LOGS======================================");
//        System.out.println(redis.getLogs());
//        System.out.println("END REDIS LOGS======================================");
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");
        String retrieved = underTest.get("test");
        assertEquals("example", retrieved);
    }
}
