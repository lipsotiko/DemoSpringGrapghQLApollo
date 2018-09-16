package com.vango.demo;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class DemoMutation implements GraphQLMutationResolver {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public HelloWorld persistHelloWorld(HelloWorldInput helloWorldInput) {
        if (helloWorldInput.getId() == null) {
            System.out.println(String.format("SAVING %s %s"
                    , helloWorldInput, ZonedDateTime.now().toString()));
        } else {
            System.out.println(String.format("UPDATING %s %s"
                    , helloWorldInput, ZonedDateTime.now().toString()));
        }

        HelloWorld helloWorld = new HelloWorld(helloWorldInput);
        return helloWorldRepository.save(helloWorld);
    }

    public List<HelloWorld> deleteHelloWorlds(String name) {
        System.out.println(String.format("DELETING %s %s"
                , name, ZonedDateTime.now().toString()));
        return helloWorldRepository.removeByName(name);
    }

}
