package com.example;

import java.util.Map;

/**
 * Sample controller for monitor-triggered agent audit.
 */
public class HelloController {

    public String hello(String name) {
        // Intentionally simple sample code
        if (name == null || name.isEmpty()) {
            return "hello";
        }
        return "hello, " + name;
    }

    public Map<String, Object> echo(Map<String, Object> body) {
        return body;
    }
}
