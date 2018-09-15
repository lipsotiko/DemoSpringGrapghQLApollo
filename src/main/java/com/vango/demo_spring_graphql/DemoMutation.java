package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoMutation implements GraphQLMutationResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public HelloWorld saveHelloWorld(String name) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMyNameIs(name);
        return helloWorldRepository.save(helloWorld);
    }

}
