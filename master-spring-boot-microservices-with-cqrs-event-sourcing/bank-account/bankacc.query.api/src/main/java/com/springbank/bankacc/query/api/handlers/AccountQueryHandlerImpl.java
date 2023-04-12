package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.core.models.BankAccount;
import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.dto.EqualityType;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderId;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import com.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author vsushko
 */
@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        var response = bankAccount.isPresent() ? new AccountLookupResponse("Bank account successfully returned!", bankAccount.get()) : new AccountLookupResponse("No bank account found for id - " + query.getId());

        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderId query) {
        var bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
        var response = bankAccount.isPresent() ? new AccountLookupResponse("Bank account successfully returned!", bankAccount.get()) : new AccountLookupResponse("No bank account found for holder id - " + query.getAccountHolderId());

        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();

        if (!bankAccountIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No bank accounss were found");
        }
        var bankAccounts = new ArrayList<BankAccount>();

        bankAccountIterator.forEach(bankAccounts::add);
        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully returned " + count + " bank account(s)!", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        var bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN
                ? (ArrayList<BankAccount>) accountRepository.findByBalanceGreaterThan(query.getBalance())
                : (ArrayList<BankAccount>) accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccounts != null && bankAccounts.size() > 0
                ? new AccountLookupResponse("Successfully returned " + bankAccounts.size() + " bank account(s)!", bankAccounts)
                : new AccountLookupResponse("No bank accounts were found!");
    }
}
