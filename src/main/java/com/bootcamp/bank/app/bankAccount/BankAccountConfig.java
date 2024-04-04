package com.bootcamp.bank.app.bankAccount;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BankAccountConfig {
  @Bean
  CommandLineRunner commandLineRunner(BankAccountRepository repository) {
    return args -> {
      BankAccount vardenis = new BankAccount(
              "Vardenis",
              "Pavardenis",
              "vardenis@gmail.com",
              1000.0
      );
      BankAccount john = new BankAccount(
              "John",
              "Doe",
              "john@gmail.com",
              2000.0
      );

      repository.saveAll(
              List.of(vardenis, john)
      );
    };
  }
}
