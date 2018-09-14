package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoMutation implements GraphQLMutationResolver {

    public List<HelloWorld> setHelloWorlds(String name, int count) {
        List<HelloWorld> helloWorlds = new ArrayList<>();
        for(int i = 1; i <= count; i++) {
            HelloWorld hello = new HelloWorld();
            hello.setMyNameIs(name);
            helloWorlds.add(hello);
        }
        return helloWorlds;
    }

}
