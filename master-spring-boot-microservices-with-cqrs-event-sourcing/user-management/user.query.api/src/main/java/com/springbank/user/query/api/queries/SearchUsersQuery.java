package com.springbank.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author vsushko
 */
@Data
@AllArgsConstructor
public class SearchUsersQuery {
    private String filter;
}
