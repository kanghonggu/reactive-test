package com.test.reactivetest;

import com.linecorp.armeria.spring.web.reactive.ArmeriaClientAutoConfiguration;
import com.test.reactivetest.controller.ReactiveController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(ReactiveController.class)
@ImportAutoConfiguration(ArmeriaClientAutoConfiguration.class)
public class ReactiveControllerTest {

    private final ReactiveController controller;

    public ReactiveControllerTest(ReactiveController controller) {
        this.controller = controller;
    }

    @Test
    void getHelloWorld() {
        WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, World");
    }
}
