package com.vango.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {
    List<HelloWorld> findByName(String name);

    @Transactional
    List<HelloWorld> removeByName(String name);

}
