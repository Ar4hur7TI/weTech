package com.hex.wetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hex.wetech")
public class WetechApplication {

    public static void main(String[] args) {
        SpringApplication.run(WetechApplication.class, args);
    }

}
