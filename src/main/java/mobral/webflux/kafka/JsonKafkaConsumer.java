package mobral.webflux.kafka;

import mobral.webflux.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "mobral", groupId = "mobral")
    public void consume(Greeting greeting){
        LOGGER.info(String.format("Json message received: %s", greeting.toString()));
    }
}
