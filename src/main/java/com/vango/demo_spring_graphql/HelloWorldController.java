package com.vango.demo_spring_graphql;

import graphql.schema.GraphQLSchema;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RestController
public class HelloWorldController {

    @Scheduled(fixedRate = 5000)
    @GetMapping(value = "/subscriptions", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flowable<HelloWorld> emmitHello() {

        Observable<HelloWorld> helloWorldObservable = Observable.create(emitter -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.execute(newHelloWorld(emitter));
        });

        ConnectableObservable<HelloWorld> connectableObservable = helloWorldObservable.share().publish();
        connectableObservable.connect();

        return connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }


    private Runnable newHelloWorld(ObservableEmitter<HelloWorld> emitter) {
        return () -> {
            List<HelloWorld> helloWorldUpdate = getUpdates(1);
            if (helloWorldUpdate != null) {
                System.out.println("Saying Hello to the World");
                emitHello(emitter, helloWorldUpdate);
            }
        };
    }

    private void emitHello(ObservableEmitter<HelloWorld> emitter, List<HelloWorld> helloWorldUpdates) {
        for (HelloWorld stockPriceUpdate : helloWorldUpdates) {
            try {
                emitter.onNext(stockPriceUpdate);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
        }
    }

    private List<HelloWorld> getUpdates(int number) {
        List<HelloWorld> helloWorldUpdates = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            HelloWorld helloWorld = new HelloWorld();
            helloWorld.setMyNameIs("Hello From Controller");
            helloWorldUpdates.add(helloWorld);
        }
        return helloWorldUpdates;
    }
}
