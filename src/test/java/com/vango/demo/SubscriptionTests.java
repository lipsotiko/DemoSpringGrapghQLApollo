package com.vango.demo;

import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionTests {

    @Autowired private DemoSubscription demoSubscription;
    @Autowired private HelloWorldRepository helloWorldRepository;

    @Test
    public void publishes_hello_worlds() throws InterruptedException {
        TestSubscriber<HelloWorld> observer = new TestSubscriber<>();
        Publisher<HelloWorld> publisher = demoSubscription.helloWorldUpdates();
        publisher.subscribe(observer);
        helloWorldRepository.save(new HelloWorld("vango"));
        Thread.sleep(1000);
        observer.assertSubscribed();
        assertThat(observer.values().get(0).getName()).isEqualTo("vango");
    }

}
