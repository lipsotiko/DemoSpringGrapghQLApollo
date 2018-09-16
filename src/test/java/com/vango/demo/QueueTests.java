package com.vango.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = QueueTestConfig.class)
public class QueueTests {

    @Autowired private HelloWorldRepository helloWorldRepository;
    private HelloWorldQueue helloWorldQueue = HelloWorldQueue.getStreamInstance();

    @Test
    public void listenerAddsHelloWorldsToQueue() {
        HelloWorld helloWorld = new HelloWorld("vango");
        HelloWorld created = helloWorldRepository.save(helloWorld);
        created.setName("ΛΕΙΨΟΤΙΣ");
        helloWorldRepository.save(created);
        helloWorldRepository.deleteAll();

        assertThat(helloWorldQueue.get().size()).isEqualTo(3);
        assertThat(helloWorldQueue.poll().getDmlType()).isEqualTo("CREATED");
        assertThat(helloWorldQueue.poll().getDmlType()).isEqualTo("UPDATED");
        assertThat(helloWorldQueue.poll().getDmlType()).isEqualTo("DELETED");
    }
}
