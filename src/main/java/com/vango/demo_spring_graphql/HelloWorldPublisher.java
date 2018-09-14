package com.vango.demo_spring_graphql;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

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
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newHelloWorld(emitter), 0, 2, TimeUnit.SECONDS);
        });

        ConnectableObservable<HelloWorld> connectableObservable = helloWorldObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private Runnable newHelloWorld(ObservableEmitter<HelloWorld> emitter) {
        return () -> {
            List<HelloWorld> helloWorldUpdate = getUpdates(5);
            if (helloWorldUpdate != null) {
                System.out.println("Saying Hello to the World");
                emitHello(emitter, helloWorldUpdate);
            }
        };
    }

    private void emitHello(ObservableEmitter<HelloWorld> emitter, List<HelloWorld> helloWorldUpdates) {
        for (HelloWorld helloWorld : helloWorldUpdates) {
            try {
                emitter.onNext(helloWorld);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        }
    }

    public Flowable<HelloWorld> getPublisher() {
        return publisher;
    }

    public Flowable<HelloWorld> getPublisher(List<String> name) {
        if (name != null) {
            return publisher.filter(helloWorldUpdate -> name.contains(helloWorldUpdate.getMyNameIs()));
        }
        return publisher;
    }

    private List<HelloWorld> getUpdates(int number) {
        List<HelloWorld> helloWorldUpdates = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            helloWorldUpdates.add(new HelloWorld());
        }
        return helloWorldUpdates;
    }

}
