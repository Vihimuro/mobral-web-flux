package mobral.webflux.handler;

import mobral.webflux.domain.Greeting;
import mobral.webflux.kafka.JsonKafkaConsumer;
import mobral.webflux.kafka.JsonKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
    private final JsonKafkaProducer kafkaProducer;

    public GreetingHandler(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
    }

    public Mono<ServerResponse> send(ServerRequest request)  {
        Mono<Greeting> greeting = request.bodyToMono(Greeting.class);
        return ServerResponse.ok().body(kafkaProducer.sendMessage(greeting), Greeting.class);
    }
}
