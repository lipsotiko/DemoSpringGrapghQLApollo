package com.vango.demo;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoMutation implements GraphQLMutationResolver {

    private HelloWorldRepository helloWorldRepository;

    public DemoMutation(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }

    public HelloWorld saveHelloWorld(HelloWorldInput helloWorldInput) {
        HelloWorld helloWorld = new HelloWorld(helloWorldInput);
        return helloWorldRepository.save(helloWorld);
    }

    public List<HelloWorld> deleteHelloWorlds(String name) {
        return helloWorldRepository.removeByName(name);
    }

}
