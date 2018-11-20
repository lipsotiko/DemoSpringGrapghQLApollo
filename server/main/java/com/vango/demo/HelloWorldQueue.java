package com.vango.demo;

import java.util.LinkedList;
import java.util.Queue;

public class HelloWorldQueue {

    private static final Queue<HelloWorld> queue = new LinkedList<HelloWorld>();
    private static HelloWorldQueue queueInstance = null;
    public static HelloWorldQueue getStreamInstance() {

        if (queueInstance == null) {
            queueInstance = new HelloWorldQueue();
        }
        return queueInstance;
    }

    Queue<HelloWorld> get() {
        return queue;
    }

    void add(HelloWorld helloWorld) {
        synchronized (queue) {
            queue.add(helloWorld);
        }
    }

    HelloWorld poll() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }

}
