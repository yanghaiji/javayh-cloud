package com.javayh.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals((String) charSequence);
    }
}

