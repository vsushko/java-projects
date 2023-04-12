package com.springbank.bankacc.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author vsushko
 */
@Data
@AllArgsConstructor
public class FindAccountByHolderId {

    private String accountHolderId;
}
