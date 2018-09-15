package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class DemoQuery implements GraphQLQueryResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<HelloWorld> getHelloWorlds() {
        System.out.println(String.format("SEARCHING ALL %s"
                , ZonedDateTime.now().toString()));
        return helloWorldRepository.findAll();
    }

    public List<HelloWorld> getHelloWorldsByName(String name) {
        System.out.println(String.format("SEARCHING %s %s"
                , name, ZonedDateTime.now().toString()));
        return helloWorldRepository.findByName(name);
    }

}
