package com.reactive.subscriber.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ReactiveStockSubscriber {

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080").build();

    public Mono<Stock> getStockById(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/stocks/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(Stock.class)
                .doOnSubscribe(subscription -> log.info("Subscribed to getStockById"))
                .doOnNext(stock -> log.info("Received {}", stock))
                .doFinally(signalType -> log.info("SignalType {}", signalType));
    }

    public Flux<Stock> getAllStocks() {
        return webClient.get()
                .uri("/stocks")
                .retrieve()
                .bodyToFlux(Stock.class)
                .doOnSubscribe(subscription -> log.info("Subscribed to getAllStocks"))
                .doOnNext(stock -> log.info("On next stock:  {}", stock))
                .doFinally(signalType -> log.info("On Complete"));
    }
}
