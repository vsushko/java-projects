package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author vsushko
 */
@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;

    @Valid
    @NotNull(message = "no user details were supplied")
    private User user;
}
