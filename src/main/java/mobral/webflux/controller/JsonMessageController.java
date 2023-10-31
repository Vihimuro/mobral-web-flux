package mobral.webflux.controller;

import mobral.webflux.domain.Greeting;
import mobral.webflux.kafka.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class JsonMessageController {
    public JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Greeting greeting) {
        kafkaProducer.sendMessage(greeting);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
