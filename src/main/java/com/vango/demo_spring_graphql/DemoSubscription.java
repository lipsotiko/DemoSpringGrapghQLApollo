package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DemoSubscription implements GraphQLSubscriptionResolver {

    private HelloWorldPublisher helloWorldPublisher;

    DemoSubscription(HelloWorldPublisher helloWorldPublisher) {
        this.helloWorldPublisher = helloWorldPublisher;
    }

    public Publisher<HelloWorld> sayHelloToTheWorldByName(String name) {
        return helloWorldPublisher.getPublisher(Collections.singletonList(name));
    }

    public Publisher<HelloWorld> sayHelloToTheWorld() {
        return helloWorldPublisher.getPublisher();
    }

}
