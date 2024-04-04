package com.bootcamp.bank.app.bankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bankAccount")
public class BankAccountControler {

  private final BankAccountService bankAccountService;

  @Autowired
  public BankAccountControler(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @GetMapping
  public List<BankAccount> getBankAccount() {
    return bankAccountService.getBankAccount();
  }

  @PostMapping
  public void registerNewBankAccount(@RequestBody BankAccount bankAccount) {
    bankAccountService.addNewBankAccount(bankAccount);
  }

  @DeleteMapping(path = "{bankAccountId}")
  public void deleteBankAccount(@PathVariable("bankAccountId") Long bankAccountId) {
    bankAccountService.deleteBankAccount(bankAccountId);
  }

  @PutMapping(path = "{bankAccountId}")
  public void updateBankAccount(
          @PathVariable Long bankAccountId,
          @RequestBody BankAccount bankAccount) {
    bankAccountService.updateBankAccount(bankAccountId, bankAccount);
  }

  @PutMapping(path = "/{transactionType}/{bankAccountId}")
  public void transactions (
          @PathVariable("bankAccountId") Long bankAccountId,
          @PathVariable("transactionType") String transactionType,
          @RequestParam(required = true) Double amount) {
    bankAccountService.transactions(bankAccountId, transactionType, amount);
  }

  @PutMapping(path = "transfer/{originAccountId}/{destinationAccountId}")
  public void transfer (
    @PathVariable("originAccountId") Long originAccountId,
    @PathVariable("destinationAccountId") Long destinationAccountId,
    @RequestParam(required = true) Double amount) {
    bankAccountService.transfer(originAccountId, destinationAccountId, amount);
  }


}
