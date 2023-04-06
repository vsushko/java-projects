package com.springbank.user.core.events;

import com.springbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;

/**
 * @author vsushko
 */
@Data
@Builder
public class UserRegisteredEvent {

    private String id;

    private User user;
}
