package com.vango.demo_spring_graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {
    List<HelloWorld> findByName(String name);

    @Transactional
    List<HelloWorld> removeByName(String name);

}
