//package mobral.webflux.client;
//
//import mobral.webflux.domain.Greeting;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Component
//public class GreetingClient {
//    private final WebClient client;
//
//    public GreetingClient(WebClient.Builder builder) {
//        this.client = builder.baseUrl("http://localhost:8080").build();
//    }
//
//    public Mono<String> message() {
//        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(Greeting.class)
//                .map(Greeting::getMessage);
//    }
//
//    public Flux<String> getMessage() {
//        return this.client.post().uri("/kafka/sendMessage").accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Greeting.class)
//                .map(Greeting::getMessage);
//    }
//}
