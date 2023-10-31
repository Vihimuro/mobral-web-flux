package mobral.webflux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {


    @Bean
    public RouterFunction<?> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(POST("kafka/sendMessage").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
    }
}
