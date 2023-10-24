package com.example.app.SpringBootFruitShop;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

// Вхідна точка в програму.
// @SpringBootApplication позначає головний клас програми.
// @SpringBootApplication інкапсулює анотації @Configuration,
// @EnableAutoConfiguration, @ComponentScan з їхніми атрибутами
// за замовчуванням.
// https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/SpringBootApplication.html
@SpringBootApplication
public class FruitShopApplication {

	private static final Logger LOGGER =
			Logger.getLogger(FruitShopApplication.class.getName());

	public static void main(String[] args) {
		new SpringApplicationBuilder(FruitShopApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);

		LOGGER.info("APP is running...");
	}
}
