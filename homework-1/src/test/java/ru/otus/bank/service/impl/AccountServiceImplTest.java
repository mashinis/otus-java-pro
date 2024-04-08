package ru.otus.bank.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.bank.dao.AccountDao;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.exception.AccountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    public void testTransfer() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
        assertEquals(new BigDecimal(20), destinationAccount.getAmount());
    }

    @Test
    public void testSourceNotFound() {
        when(accountDao.findById(any())).thenReturn(Optional.empty());

        AccountException result = assertThrows(AccountException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));
            }
        });
        assertEquals("No source account", result.getLocalizedMessage());
    }


    @Test
    public void testTransferWithVerify() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));
        destinationAccount.setId(2L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        ArgumentMatcher<Account> sourceMatcher =
                argument -> argument.getId().equals(1L) && argument.getAmount().equals(new BigDecimal(90));

        ArgumentMatcher<Account> destinationMatcher =
                argument -> argument.getId().equals(2L) && argument.getAmount().equals(new BigDecimal(20));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        verify(accountDao).save(argThat(sourceMatcher));
        verify(accountDao).save(argThat(destinationMatcher));
    }

    @Test
    void testGetAccountsByAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(1L);

        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new Account());

        when(accountDao.findByAgreementId(eq(1L))).thenReturn(expectedAccounts);

        List<Account> actualAccounts = accountServiceImpl.getAccounts(agreement);
        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    void testAddAccount() {
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        String accountNumber = "1234567890";
        Integer type = 1;
        BigDecimal amount = BigDecimal.valueOf(100);

        Account expectedAccount = new Account();
        expectedAccount.setAgreementId(agreement.getId());
        expectedAccount.setNumber(accountNumber);
        expectedAccount.setType(type);
        expectedAccount.setAmount(amount);

        when(accountDao.save(any(Account.class))).thenReturn(expectedAccount);

        Account actualAccount = accountServiceImpl.addAccount(agreement, accountNumber, type, amount);

        assertEquals(expectedAccount, actualAccount);
        verify(accountDao, times(1)).save(any(Account.class));
    }

    @Test
    void testCharge() {
        Long accountId = 1L;
        BigDecimal initialAmount = BigDecimal.valueOf(100);
        BigDecimal chargeAmount = BigDecimal.valueOf(50);

        Account account = new Account();
        account.setId(accountId);
        account.setAmount(initialAmount);

        when(accountDao.findById(accountId)).thenReturn(Optional.of(account));

        boolean result = accountServiceImpl.charge(accountId, chargeAmount);

        assertTrue(result);
        assertEquals(initialAmount.subtract(chargeAmount), account.getAmount());
        verify(accountDao, times(1)).findById(accountId);
        verify(accountDao, times(1)).save(account);
    }
}
