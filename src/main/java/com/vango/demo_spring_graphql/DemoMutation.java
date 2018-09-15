package com.vango.demo_spring_graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class DemoMutation implements GraphQLMutationResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public HelloWorld saveHelloWorld(String name) {
        System.out.println(String.format("SAVING %s Saying Hello to the World %s"
                , name, ZonedDateTime.now().toString()));
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName(name);
        return helloWorldRepository.save(helloWorld);
    }

    public List<HelloWorld> deleteHelloWorlds(String name) {
        System.out.println(String.format("DELETING %s Saying Hello to the World %s"
                , name, ZonedDateTime.now().toString()));
        return helloWorldRepository.removeByName(name);
    }

    public HelloWorld updateHelloWorld(HelloWorldInput helloWorldInput) {
        System.out.println(String.format("UPDATING %s Saying Hello to the World %s"
                , helloWorldInput.getName(), ZonedDateTime.now().toString()));
        HelloWorld helloWorld = new HelloWorld(helloWorldInput);
        return helloWorldRepository.save(helloWorld);
    }

}
