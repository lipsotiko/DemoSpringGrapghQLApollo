package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
class DemoQuery implements GraphQLQueryResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<HelloWorld> getHelloWorlds() {
        System.out.println(String.format("SEARCH ALL Saying Hello to the World %s"
                , ZonedDateTime.now().toString()));
        return helloWorldRepository.findAll();
    }

    public List<HelloWorld> getHelloWorldsByName(String name) {
        System.out.println(String.format("SEARCH %s Saying Hello to the World %s"
                , name, ZonedDateTime.now().toString()));
        return helloWorldRepository.findByName(name);
    }

}
