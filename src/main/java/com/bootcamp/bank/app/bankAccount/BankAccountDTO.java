package com.bootcamp.bank.app.bankAccount;

public class BankAccountDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Double balance;

//  public BankAccountDTO() {
//  }

//  public BankAccountDTO(Long id, String firstName, String lastName, String email, Double balance) {
//    this.id = id;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.email = email;
//    this.balance = balance;
//  }

  public BankAccountDTO(BankAccount bankAccount) {
    this.setId(bankAccount.getId());
    this.setFirstName(bankAccount.getFirstName());
    this.setLastName(bankAccount.getLastName());
    this.setEmail(bankAccount.getEmail());
    this.setBalance(bankAccount.getBalance());

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
