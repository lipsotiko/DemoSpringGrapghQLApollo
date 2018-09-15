package com.vango.demo_spring_graphql;

import org.springframework.data.jpa.repository.JpaRepository;

interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {
}
