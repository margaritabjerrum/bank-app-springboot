package com.bootcamp.bank.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppApplication {
	private static final Logger logger = LogManager.getLogger(BankAppApplication.class.getName());
	public static void main(String[] args) {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");
		SpringApplication.run(BankAppApplication.class, args);
	}


}
