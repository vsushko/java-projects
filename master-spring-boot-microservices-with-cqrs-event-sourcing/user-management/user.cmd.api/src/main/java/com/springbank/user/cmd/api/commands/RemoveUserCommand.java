package com.springbank.user.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author vsushko
 */
@Data
@AllArgsConstructor
@Builder
public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;
}
