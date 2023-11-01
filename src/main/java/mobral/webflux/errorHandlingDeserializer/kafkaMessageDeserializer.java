package mobral.webflux.errorHandlingDeserializer;

import mobral.webflux.domain.Greeting;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

public class kafkaMessageDeserializer extends ErrorHandlingDeserializer<Greeting>
        implements Deserializer<Greeting> {
}
