package com.test.reactivetest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RestController
public class ReactiveController {

    private final WebClient webClient;

    /**
     * The given {@link Builder} has been configured to have an {@code ArmeriaClientHttpConnector} as
     * its client connector.
     */
    @Inject
    public ReactiveController(WebClient.Builder builder,
                              @Value("${server.port}") int port) {
        this(builder.baseUrl("https://127.0.0.1:" + port).build());
    }

    public ReactiveController(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Returns a string which is retrieved from {@code /hello} using the {@link WebClient}.
     */
    @GetMapping("/")
    Mono<String> index() {
        return webClient.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello, World";
    }
}
