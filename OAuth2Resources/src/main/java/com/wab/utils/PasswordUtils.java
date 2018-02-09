package com.wab.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @author hcq
 * @create 2018-02-01 下午 4:44
 **/

public class PasswordUtils {
    private static PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    public static boolean verification(String password, String encryPass) {
        return passwordEncoder.matches(password, encryPass);
    }

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
