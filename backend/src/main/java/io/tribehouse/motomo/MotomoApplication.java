package io.tribehouse.motomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class MotomoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MotomoApplication.class, args);
    }

}