package com.vango.demo_spring_graphql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HelloWorld {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String myNameIs;

    public String getMyNameIs() {
        return myNameIs;
    }

    public void setMyNameIs(String name) {
        this.myNameIs = name;
    }
}
