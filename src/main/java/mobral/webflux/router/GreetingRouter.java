package mobral.webflux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {


    @Bean
    public RouterFunction<?> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(POST("kafka/sendMessage").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
    }
}
