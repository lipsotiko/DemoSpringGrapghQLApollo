package com.vango.demo;

public class HelloWorldException extends Exception {
    public HelloWorldException(String message) {
        super("Hello World Exception... Γαμώτο!!!! " + message);
    }
}
