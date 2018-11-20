package com.vango.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(
        basePackageClasses = { HelloWorldRepository.class, HelloWorldQueue.class, HelloWorldListener.class } ,
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = { HelloWorldPublisher.class } )
        } )
class QueueTestConfig {
}
