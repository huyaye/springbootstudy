package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.app.Frontend;

@EnableAutoConfiguration
@ComponentScan
public class App {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = SpringApplication.run(App.class, args)) {
			Frontend frontEnd = context.getBean(Frontend.class);
			frontEnd.run();
		}

	}
}
