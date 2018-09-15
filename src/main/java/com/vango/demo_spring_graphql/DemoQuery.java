package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class DemoQuery implements GraphQLQueryResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<HelloWorld> getHelloWorlds() {
        return helloWorldRepository.findAll();
    }

    public List<HelloWorld> getHelloWorldsByName(String name) {
        return helloWorldRepository.findAll();
    }

}
