/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author matson
 */
public class AuthenticationChecker {
    private static final List<String> KEYS = Arrays.asList("secret", "secret2");

    public static boolean isValidKey(String key) {
        return KEYS.contains(key);
    }
}
