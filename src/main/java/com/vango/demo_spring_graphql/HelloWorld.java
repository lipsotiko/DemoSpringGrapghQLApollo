package com.vango.demo_spring_graphql;

import javax.persistence.*;

@Entity
@EntityListeners({HelloWorldListener.class})
public class HelloWorld {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    public HelloWorld() { }

    public HelloWorld(HelloWorldInput helloWorldInput) {
        this.id = helloWorldInput.getId();
        this.name = helloWorldInput.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("HelloWorld(id: %s, name: %s)",this.id, this.name);
    }
}
