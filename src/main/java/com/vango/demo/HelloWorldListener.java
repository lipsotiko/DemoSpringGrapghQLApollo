package com.vango.demo;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class HelloWorldListener {

    private HelloWorldQueue helloWorldQueue = HelloWorldQueue.getStreamInstance();

    @PostPersist
    public void addSavedHelloWorldToQueue(HelloWorld helloWorld) {
        helloWorld.setDmlType("CREATED");
        helloWorldQueue.add(helloWorld);
    }

    @PostUpdate
    public void addUpdatedHelloWorldToQueue(HelloWorld helloWorld) {
        helloWorld.setDmlType("UPDATED");
        helloWorldQueue.add(helloWorld);
    }

    @PostRemove
    public void addDeletedHelloWorldToQueue(HelloWorld helloWorld) {
        helloWorld.setDmlType("DELETED");
        helloWorldQueue.add(helloWorld);
    }
}
