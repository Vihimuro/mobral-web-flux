package mobral.webflux.kafka;

import mobral.webflux.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, Greeting> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Greeting> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Greeting data){
        LOGGER.info(String.format("Message sent: %s", data.toString()));
        Message<Greeting> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "mobral")
                .build();

        kafkaTemplate.send(message);
    }
}
