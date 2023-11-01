package mobral.webflux.kafka;

import mobral.webflux.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, Greeting> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Greeting> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Greeting> sendMessage(Mono<Greeting> data){
        return data.doOnNext(greeting -> {
            LOGGER.info(String.format("Message sent: %s", greeting.toString()));
            kafkaTemplate.send("mobral", greeting);
        });
    }
}
