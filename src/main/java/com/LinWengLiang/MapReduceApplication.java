package com.LinWengLiang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Configuration
@Component
public class MapReduceApplication{
    public static void main(String[] args) {
        SpringApplication.run(MapReduceApplication.class, args);
    }
}
