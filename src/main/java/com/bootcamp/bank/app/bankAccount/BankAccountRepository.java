package com.bootcamp.bank.app.bankAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

  Optional<BankAccount> findBankAccountByEmail(String email);
}
