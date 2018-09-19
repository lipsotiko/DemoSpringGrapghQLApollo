package com.vango.demo;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class HelloWorldPublisher {

    private final Flowable<HelloWorld> publisher;

    private HelloWorldQueue helloWorldQueue = HelloWorldQueue.getStreamInstance();

    public HelloWorldPublisher() {
        Observable<HelloWorld> helloWorldObservable = Observable.create(emitter -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
            executorService.scheduleAtFixedRate(newHelloWorlds(emitter), 0, 100, TimeUnit.MILLISECONDS);
        });

        ConnectableObservable<HelloWorld> connectableObservable = helloWorldObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private Runnable newHelloWorlds(ObservableEmitter<HelloWorld> emitter) {
        return () -> {
            List<HelloWorld> helloWorldUpdates = getUpdates();
            emitHello(emitter, helloWorldUpdates);
        };
    }

    private void emitHello(ObservableEmitter<HelloWorld> emitter, List<HelloWorld> helloWorldUpdates) {
        for (HelloWorld helloWorld : helloWorldUpdates) {
            try {
                emitter.onNext(helloWorld);
            } catch (RuntimeException e) {
                emitter.onError(new HelloWorldException(e.getMessage()));
            }
        }

        emitter.onComplete();
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

        while (!helloWorldQueue.isEmpty()) {
            helloWorldUpdates.add(helloWorldQueue.poll());
        }

        if (helloWorldUpdates.size() == 0) {
            return Collections.emptyList();
        }

        return helloWorldUpdates;
    }

}
