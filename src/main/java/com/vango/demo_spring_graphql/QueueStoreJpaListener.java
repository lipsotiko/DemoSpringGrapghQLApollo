package com.vango.demo_spring_graphql;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.time.ZonedDateTime;

public class QueueStoreJpaListener {

    @PostPersist
    public void addSavedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("SAVED %s Saying Hello to the World %s"
                , helloWorld.getName(), ZonedDateTime.now().toString()));
    }

    @PostUpdate
    public void addUpdatedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("UPDATED %s Saying Hello to the World %s"
                , helloWorld.getName(), ZonedDateTime.now().toString()));
    }

    @PostRemove
    public void addDeletedHelloWorldToQueue(HelloWorld helloWorld) {
        System.out.println(String.format("DELETED %s Saying Hello to the World %s"
                , helloWorld.getName(), ZonedDateTime.now().toString()));
    }
}
