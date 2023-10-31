package mobral.webflux.service;

import jakarta.annotation.Generated;
import jakarta.annotation.PostConstruct;
import mobral.webflux.domain.Greeting;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.sender.KafkaSender;

@Service
public class GreetingService {
    Flux<Greeting> greetingStream;
    KafkaReceiver<String, Greeting> kafkaGreetingReceiver;

    KafkaSender<String, Greeting> kafkaGreetingSender;

    @Autowired
    public GreetingService(KafkaReceiver<String, Greeting> kafkaGreetingReceiver,
                           KafkaSender<String, Greeting> kafkaGreetingSender) {
        this.kafkaGreetingReceiver = kafkaGreetingReceiver;
        this.kafkaGreetingSender = kafkaGreetingSender;
    }

    @PostConstruct
    public void init() {
        greetingStream = kafkaGreetingReceiver.receive()
                .publish()
                .autoConnect(1)
                .onBackpressureBuffer(5000, BufferOverflowStrategy.DROP_OLDEST)
                .map(ConsumerRecord::value);
    }

    public Flux<Greeting> streamGreeting(String message) {
        return greetingStream.filter(greeting -> greeting.getMessage().equals(message));
    }

//    public Flux<Greeting> sendGreeting(Flux<Greeting> greeting) {
//        kafkaGreetingSender.send(greeting)
//                .subscribe();
//    }
}
