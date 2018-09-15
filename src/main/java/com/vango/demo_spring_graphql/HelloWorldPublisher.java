package com.vango.demo_spring_graphql;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class HelloWorldPublisher {

    private final Flowable<HelloWorld> publisher;

    public HelloWorldPublisher() {
        Observable<HelloWorld> helloWorldObservable = Observable.create(emitter -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
            executorService.scheduleAtFixedRate(newHelloWorlds(emitter), 0, 10, TimeUnit.SECONDS);
        });

        ConnectableObservable<HelloWorld> connectableObservable = helloWorldObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private Runnable newHelloWorlds(ObservableEmitter<HelloWorld> emitter) {
        return () -> {
            List<HelloWorld> helloWorldUpdate = getUpdates();
            if (helloWorldUpdate != null) {
                emitHello(emitter, helloWorldUpdate);
            }
        };
    }

    private void emitHello(ObservableEmitter<HelloWorld> emitter, List<HelloWorld> helloWorldUpdates) {
        for (HelloWorld helloWorld : helloWorldUpdates) {
            try {
                System.out.println(String.format("%s Saying Hello to the World %s"
                        , helloWorld.getName(), ZonedDateTime.now().toString()));
                emitter.onNext(helloWorld);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Flowable<HelloWorld> getPublisher() {
        return publisher;
    }

    public Flowable<HelloWorld> getPublisher(List<String> name) {
        if (name != null) {
            return publisher.filter(helloWorldUpdate -> name.contains(helloWorldUpdate.getName()));
        }
        return publisher;
    }

    private List<HelloWorld> getUpdates() {
        List<HelloWorld> helloWorldUpdates = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            HelloWorld helloWorld = new HelloWorld();
            helloWorld.setName(String.format("Vango %s",i));
            helloWorldUpdates.add(helloWorld);
        }
        return helloWorldUpdates;
    }

}
