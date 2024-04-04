package com.bootcamp.bank.app;

import com.bootcamp.bank.app.bankAccount.BankAccount;
import com.bootcamp.bank.app.bankAccount.BankAccountRepository;
import com.bootcamp.bank.app.bankAccount.BankAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BankAccountServiceTest {

	@Mock
	private BankAccountRepository bankAccountRepository;

	@InjectMocks
	private BankAccountService bankAccountService;

	@Test
	public void testAddNewBankAccount_Success() {
		// Arrange
		BankAccount bankAccount = new BankAccount("John", "Doe", "john@example.com", 100.0);
		when(bankAccountRepository.findBankAccountByEmail("john@example.com")).thenReturn(Optional.empty());

		// Act
		bankAccountService.addNewBankAccount(bankAccount);

		// Assert
		verify(bankAccountRepository, times(1)).save(bankAccount);
	}

	@Test
	public void testAddNewBankAccount_EmailExists() {
		// Arrange
		BankAccount bankAccount = new BankAccount("John", "Doe", "john@example.com", 100.0);
		when(bankAccountRepository.findBankAccountByEmail("john@example.com")).thenReturn(Optional.of(bankAccount));

		// Act & Assert
		assertThrows(IllegalStateException.class, () -> {
			bankAccountService.addNewBankAccount(bankAccount);
		});
	}

	@Test
	public void testDeleteBankAccount_Success() {
		// Arrange
		Long bankAccountId = 1L;
		when(bankAccountRepository.existsById(bankAccountId)).thenReturn(true);

		// Act
		bankAccountService.deleteBankAccount(bankAccountId);

		// Assert
		verify(bankAccountRepository, times(1)).deleteById(bankAccountId);
	}

	@Test
	public void testDeleteBankAccount_AccountNotFound() {
		// Arrange
		Long bankAccountId = 1L;
		when(bankAccountRepository.existsById(bankAccountId)).thenReturn(false);

		// Act & Assert
		assertThrows(IllegalStateException.class, () -> {
			bankAccountService.deleteBankAccount(bankAccountId);
		});
	}

	@Test
	public void testUpdateBankAccount_Success() {
		// Arrange
		Long bankAccountId = 1L;
		BankAccount bankAccount = new BankAccount(bankAccountId, "John", "Doe", "john@example.com", 100.0);
		when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.of(bankAccount));

		// Act
		bankAccountService.updateBankAccount(bankAccountId, bankAccount);

		// Assert
		verify(bankAccountRepository, times(1)).save(bankAccount);
	}

	// Similarly, you can write tests for scenarios like updating a non-existing bank account, invalid transaction amount, insufficient funds, etc.

	@Test
	public void testTransactions_Deposit_Success() {
		// Arrange
		Long bankAccountId = 1L;
		BankAccount bankAccount = new BankAccount(bankAccountId, "John", "Doe", "john@example.com", 100.0);
		when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.of(bankAccount));

		// Act
		bankAccountService.transactions(bankAccountId, "deposit", 50.0);

		// Assert
		assertEquals(150.0, bankAccount.getBalance());
	}

	@Test
	public void testTransfer_Success() {
		// Arrange
		Long originAccountId = 1L;
		Long destinationAccountId = 2L;
		BankAccount originAccount = new BankAccount(originAccountId, "John", "Doe", "john@example.com", 100.0);
		BankAccount destinationAccount = new BankAccount(destinationAccountId, "Jane", "Doe", "jane@example.com", 50.0);
		when(bankAccountRepository.findById(originAccountId)).thenReturn(Optional.of(originAccount));
		when(bankAccountRepository.findById(destinationAccountId)).thenReturn(Optional.of(destinationAccount));

		// Act
		bankAccountService.transfer(originAccountId, destinationAccountId, 50.0);

		// Assert
		assertEquals(50.0, originAccount.getBalance());
		assertEquals(100.0, destinationAccount.getBalance());
	}
}