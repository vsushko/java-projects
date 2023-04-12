package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderId;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;

/**
 * @author vsushko
 */
public interface AccountQueryHandler {

    AccountLookupResponse findAccountById(FindAccountByIdQuery query);

    AccountLookupResponse findAccountByHolderId(FindAccountByHolderId query);

    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);

    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
