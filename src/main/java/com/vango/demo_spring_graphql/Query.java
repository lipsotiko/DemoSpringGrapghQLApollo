package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class Query implements GraphQLQueryResolver {

    public List<HelloWorld> getHelloWorlds(int count) {
        List<HelloWorld> helloWorlds = new ArrayList<>();
        for(int i = 1; i <= count; i++) {
            helloWorlds.add(new HelloWorld());
        }
        return helloWorlds;
    }

}
