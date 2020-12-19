package com.agisoft.cucumber.testcontainers;

import com.agisoft.cucumber.redis.RedisBackedCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.output.WaitingConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testcontainers.containers.output.OutputFrame.OutputType.STDOUT;

/**
 * Scenario:
 * 1. Start Kafka
 * 2. Start Party Role Service
 * 3. Stop Kafka
 * 4. Create Party Role
 * 5. Verify that Party Role has not been created, is not present in Marklogic
 */
@Slf4j
public class CreatePartyRoleWhenKafkaIsNotAvailableTest {

    private static Network network = Network.newNetwork();

    private Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);

    //1. starts kafka
    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"))
            .withNetwork(network)
            .waitingFor(Wait.forLogMessage(".*service is now ready.*", 1));

    @Before
    public void setup() {
        System.out.println(kafka.getNetwork());
        System.out.println(kafka.getNetworkAliases());
        System.out.println(kafka.getBootstrapServers());
        System.out.println(kafka.getEnvMap());
        System.out.println(kafka.getExposedPorts());
    }

    @Test
    public void createPartyRoleWhenKafkaIsNotAvailable() throws InterruptedException, IOException, TimeoutException {

        final String bootstrapServer = kafka.getNetworkAliases()
                                            .get(0) + ":9092";

        GenericContainer<?> partyRoleContainer = new GenericContainer<>(DockerImageName.parse("agisoft-kafka-poc"))
                .withNetwork(network)
                .withEnv("spring.kafka.consumer.bootstrap-servers", bootstrapServer)
                .withEnv("spring.kafka.producer.bootstrap-servers", bootstrapServer)
                .waitingFor(Wait.forLogMessage(".*Started DemoApplicatio.*\\n", 1));
        partyRoleContainer.start();


        String url = "http://localhost:8082/send/foo/bar";
        Container.ExecResult lsResult = partyRoleContainer.execInContainer("curl", "-X", "POST", url);
        String stdout = lsResult.getStdout();
        System.out.println(stdout);

//        partyRoleContainer.followOutput(logConsumer);
        WaitingConsumer consumer = new WaitingConsumer();
        partyRoleContainer.followOutput(consumer, STDOUT);
//        consumer.waitUntil(frame -> frame.getUtf8String().contains("Consumed msg:"), 30, TimeUnit.SECONDS);
        consumer.waitUntilEnd();

    }

}

