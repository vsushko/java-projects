package com.springbank.bankacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author vsushko
 */
@Data
@Builder
public class DepositFundsCommand {

    @TargetAggregateIdentifier
    private String id;

    private double amount;
}
