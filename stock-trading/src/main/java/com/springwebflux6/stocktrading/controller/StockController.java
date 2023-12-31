package com.springwebflux6.stocktrading.controller;

import com.springwebflux6.stocktrading.model.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @GetMapping("/{id}")
    public Mono<Stock> getOneStock(@PathVariable String id) {
        return Mono.just(Stock.builder()
                .name("Stock- " + id)
                .build());
    }

    @GetMapping
    public Flux<Stock> getAllStocks() {
        return Flux.range(1,5)
                .map(count -> Stock.builder()
                        .name("Stock- " + count)
                        .build());
    }
}
