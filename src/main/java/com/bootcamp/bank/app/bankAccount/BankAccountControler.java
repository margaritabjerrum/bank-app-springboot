package com.bootcamp.bank.app.bankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/bankAccount")
public class BankAccountControler {

  private final BankAccountService bankAccountService;

  @Autowired
  public BankAccountControler(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @GetMapping
  public List<BankAccountDTO> getBankAccount() {
    return bankAccountService.getBankAccounts().stream()
            .map(BankAccountDTO::new).collect(Collectors.toList());
  }

  @GetMapping(path = "{bankAccountId}")
  public Optional<BankAccount> getBankAccountById(@PathVariable("bankAccountId") Long bankAccountId) {
    return bankAccountService.getBankAccountById(bankAccountId);
  }

  @PostMapping
  public BankAccount registerNewBankAccount(@RequestBody BankAccount bankAccount) {
    bankAccountService.addNewBankAccount(bankAccount);
    return bankAccount;
  }

  @DeleteMapping(path = "{bankAccountId}")
  public Optional<BankAccount> deleteBankAccount(@PathVariable("bankAccountId") Long bankAccountId) {
    Optional<BankAccount> bankAccount = bankAccountService.getBankAccountById(bankAccountId);
    bankAccountService.deleteBankAccount(bankAccountId);
    return bankAccount;
  }

  @PutMapping(path = "{bankAccountId}")
  public BankAccount updateBankAccount(
          @PathVariable Long bankAccountId,
          @RequestBody BankAccount bankAccount) {
    return bankAccountService.updateBankAccount(bankAccountId, bankAccount);
  }

  @PutMapping(path = "/{transactionType}/{bankAccountId}")
  public Optional<BankAccount> transactions (
          @PathVariable("bankAccountId") Long bankAccountId,
          @PathVariable("transactionType") String transactionType,
          @RequestParam(required = true) Double amount) {
    bankAccountService.transactions(bankAccountId, transactionType, amount);
    return bankAccountService.getBankAccountById(bankAccountId);
  }

  @PutMapping(path = "transfer/{originAccountId}/{destinationAccountId}")
  public void transfer (
    @PathVariable("originAccountId") Long originAccountId,
    @PathVariable("destinationAccountId") Long destinationAccountId,
    @RequestParam(required = true) Double amount) {
    bankAccountService.transfer(originAccountId, destinationAccountId, amount);
  }


}
