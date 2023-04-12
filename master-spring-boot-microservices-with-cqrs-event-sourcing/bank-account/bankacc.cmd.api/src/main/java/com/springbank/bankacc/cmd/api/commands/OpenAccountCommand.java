package com.springbank.bankacc.cmd.api.commands;

import com.springbank.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author vsushko
 */
@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "no account holder id was supplied")
    private String accountHolderId;

    @NotNull(message = "no account type id was supplied")
    private AccountType accountType;

    @Min(value = 0, message = "opening balance must be at least 50")
    private double openingBalance;
}
