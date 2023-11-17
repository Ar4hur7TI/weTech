package com.hex.wetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.hex.wetech")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WetechApplication {

    public static void main(String[] args) {
        SpringApplication.run(WetechApplication.class, args);
    }

}
