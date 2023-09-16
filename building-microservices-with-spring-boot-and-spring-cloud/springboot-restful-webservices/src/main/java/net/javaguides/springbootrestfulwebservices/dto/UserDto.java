package net.javaguides.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vsushko
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "User firstName should not be null or empty")
    private String firstName;

    @NotEmpty(message = "User lastName should not be null or empty")
    private String lastName;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "User email address should be valid")
    private String email;
}
