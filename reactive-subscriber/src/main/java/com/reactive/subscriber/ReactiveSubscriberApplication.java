package com.reactive.subscriber;

import com.reactive.subscriber.client.ReactiveStockSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class ReactiveSubscriberApplication {

	private ReactiveStockSubscriber reactiveStockSubscriber;
	public static void main(String[] args) {

		SpringApplication.run(ReactiveSubscriberApplication.class, args);
	}

	@PostConstruct
	public void subscribeToStockTradingApp() {
		reactiveStockSubscriber.getStockById("1")
				.block();

		log.info("****************************************************************************");
		reactiveStockSubscriber.getAllStocks()
				.blockLast();
	}

}
