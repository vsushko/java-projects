package com.springbank.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vsushko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "users")
public class User {

    @Id
    private String id;

    private String firstname;

    private String lastname;

    private String emailAddress;

    private Account account;
}
