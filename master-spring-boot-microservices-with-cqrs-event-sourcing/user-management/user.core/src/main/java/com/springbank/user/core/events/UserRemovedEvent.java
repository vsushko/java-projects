package com.springbank.user.core.events;

import lombok.Data;

/**
 * @author vsushko
 */
@Data
public class UserRemovedEvent {
    private String id;
}