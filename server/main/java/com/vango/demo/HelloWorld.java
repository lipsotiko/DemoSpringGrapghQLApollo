package com.vango.demo;

import javax.persistence.*;

@Entity
@EntityListeners({HelloWorldListener.class})
public class HelloWorld {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Transient
    private String dmlType;

    public HelloWorld() { }

    public HelloWorld(HelloWorldInput helloWorldInput) {
        this.id = helloWorldInput.getId();
        this.name = helloWorldInput.getName();
    }

    public HelloWorld(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDmlType() {
        return dmlType;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    @Override
    public String toString() {
        return String.format("HelloWorld(id: %s, name: %s, dmlType: %s)", this.id, this.name, this.dmlType);
    }

}
