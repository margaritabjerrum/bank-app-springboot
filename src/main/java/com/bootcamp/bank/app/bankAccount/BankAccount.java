package com.bootcamp.bank.app.bankAccount;

import jakarta.persistence.*;

@Entity
@Table
public class BankAccount {

  @Id
  @SequenceGenerator(
          name = "bankAccount_sequence",
          sequenceName = "bankAccount_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "bankAccount_sequence"
  )
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Double balance;

  public BankAccount() {

  }

  public BankAccount(Long id, String firstName, String lastName, String email, Double balance) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.balance = balance;
  }

  public BankAccount(String firstName, String lastName, String email, Double balance) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.balance = balance;
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

  @Override
  public String toString() {
    return "BankAccount{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", balance=" + balance +
            '}';
  }
}
