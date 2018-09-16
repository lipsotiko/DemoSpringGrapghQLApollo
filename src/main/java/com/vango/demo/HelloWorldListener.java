package com.vango.demo;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.time.ZonedDateTime;

public class HelloWorldListener {

    @PostPersist
    public void addSavedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("SAVED %s %s"
                , helloWorld, ZonedDateTime.now().toString()));
    }

    @PostUpdate
    public void addUpdatedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("UPDATED %s %s"
                , helloWorld, ZonedDateTime.now().toString()));
    }

    @PostRemove
    public void addDeletedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("DELETED %s %s"
                , helloWorld, ZonedDateTime.now().toString()));
    }
}
