package com.springbank.bankacc.core.events;

import lombok.Builder;
import lombok.Data;

/**
 * @author vsushko
 */
@Data
@Builder
public class AccountClosedEvent {

    private String id;
}
