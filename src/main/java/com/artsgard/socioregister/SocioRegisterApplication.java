package com.artsgard.socioregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class SocioRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocioRegisterApplication.class, args);
    }
}
