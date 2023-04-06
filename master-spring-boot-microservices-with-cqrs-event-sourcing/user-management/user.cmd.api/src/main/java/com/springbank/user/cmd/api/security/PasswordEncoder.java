package com.springbank.user.cmd.api.security;

/**
 * @author vsushko
 */
public interface PasswordEncoder {

    String hashPassword(String password);
}
