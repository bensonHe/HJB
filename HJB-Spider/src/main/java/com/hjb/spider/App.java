package com.hjb.spider;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

	public static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException, InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
		Start start = ctx.getBean(Start.class);
		logger.debug("start..........");
		start.startUp();

	}
}
