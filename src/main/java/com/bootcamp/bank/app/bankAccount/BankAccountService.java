package com.bootcamp.bank.app.bankAccount;

import com.bootcamp.bank.app.BankAppApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
  private static final Logger logger = LogManager.getLogger(BankAppApplication.class.getName());
  private final BankAccountRepository bankAccountRepository;

  @Autowired
  public BankAccountService(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  @GetMapping
  public List<BankAccount> getBankAccounts() {
    return bankAccountRepository.findAll();
  }

  public Optional<BankAccount> getBankAccountById(Long bankAccountId) {
    return bankAccountRepository.findById(bankAccountId);
  }

  public void addNewBankAccount(BankAccount bankAccount) {
    Optional<BankAccount> bankAccountOptional =  bankAccountRepository
            .findBankAccountByEmail(bankAccount.getEmail());
    if(bankAccountOptional.isPresent()) {
      throw new IllegalStateException(
              "User with an email " + bankAccount.getEmail() + " already exists"
      );
    }
    bankAccountRepository.save(bankAccount);
  }

  public void deleteBankAccount(Long bankAccountId) {
    boolean exists = bankAccountRepository.existsById(bankAccountId);
    if(!exists) {
      throw new IllegalStateException(
              "Bank account with id " + bankAccountId + " does not exist"
      );
    }
    bankAccountRepository.deleteById(bankAccountId);
  }

  public BankAccount updateBankAccount(Long bankAccountId, BankAccount bankAccount) {
    BankAccount bankAccountToUpdate = bankAccountRepository.findById(bankAccountId)
            .orElseThrow(() -> new IllegalStateException(
                    "Bank account with id " + bankAccountId + " does not exist"
            ));
    bankAccountToUpdate.setFirstName(bankAccount.getFirstName());
    bankAccountToUpdate.setLastName(bankAccount.getLastName());
    bankAccountToUpdate.setEmail(bankAccount.getEmail());
    bankAccountToUpdate.setBalance(bankAccount.getBalance());
    bankAccountRepository.save(bankAccountToUpdate);
    return bankAccountToUpdate;
  }

  @Transactional
  public void transactions(Long bankAccountId, String transactionType, Double amount) {
    BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
            .orElseThrow(() -> new IllegalStateException(
                    "Bank account with id " + bankAccountId + " does not exist"
            ));

    if(amount <= 0) {
      throw new IllegalStateException(
              "Amount cannot be less than or equal to 0. You tried to deposit:" + amount + "eur"
      );
    }

    if (transactionType.equals("deposit")) {
      bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

    if (transactionType.equals("withdraw")) {
      if(bankAccount.getBalance() < amount) {
        throw new IllegalStateException(
                "Insufficient funds. Your current balance is: " + bankAccount.getBalance() + "eur"
        );
      }
      bankAccount.setBalance(bankAccount.getBalance() - amount);
    }
  }

  @Transactional
  public void transfer(Long originAccountId, Long destinationAccountId, Double amount) {
    BankAccount originAccount = bankAccountRepository.findById(originAccountId)
            .orElseThrow(() -> new IllegalStateException(
                    "Bank account with id " + originAccountId + " does not exist"
            ));

    if(originAccount.getBalance() < amount) {
      throw new IllegalStateException(
              "Insufficient funds. Your current balance is: " + originAccount.getBalance() + "eur"
      );
    }

    BankAccount destinationAccount = bankAccountRepository.findById(destinationAccountId)
            .orElseThrow(() -> new IllegalStateException(
                    "Bank account with id " + destinationAccountId + " does not exist"
            ));

    originAccount.setBalance(originAccount.getBalance() - amount);
    destinationAccount.setBalance(destinationAccount.getBalance() + amount);
  }
}
