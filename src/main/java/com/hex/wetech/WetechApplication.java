package com.hex.wetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WetechApplication {

    public static void main(String[] args) {
        SpringApplication.run(WetechApplication.class, args);
    }

}
