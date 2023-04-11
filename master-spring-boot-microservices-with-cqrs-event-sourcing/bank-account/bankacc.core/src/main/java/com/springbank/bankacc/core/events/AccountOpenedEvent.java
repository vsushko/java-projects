package com.springbank.bankacc.core.events;

import com.springbank.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author vsushko
 */
@Data
@Builder
public class AccountOpenedEvent {

    private String id;

    private String accountHolderId;

    private AccountType accountType;

    private Date creatingDate;

    private double openingBalance;

}
