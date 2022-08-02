package com.example.reactivewebflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFluxtest {

    @Test
    public void testMono(){
        Mono<String>  mono = Mono.just("Sonia"); // one element //publisher

        mono.subscribe(System.out::println);
    }
}
