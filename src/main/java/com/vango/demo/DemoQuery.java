package com.vango.demo;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoQuery implements GraphQLQueryResolver {

    private HelloWorldRepository helloWorldRepository;

    public DemoQuery(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }

    public List<HelloWorld> getHelloWorlds() {
        return helloWorldRepository.findAll();
    }

    public List<HelloWorld> getHelloWorldsByName(String name) {
        return helloWorldRepository.findByName(name);
    }

}
