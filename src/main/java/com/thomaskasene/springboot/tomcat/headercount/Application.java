package com.thomaskasene.springboot.tomcat.headercount;

import org.springframework.boot.SpringApplication;

public class Application {

    /**
     * Run this method in your IDE to start the application. The endpoint should be available at from your browser at
     * <a href="http://localhost:8080/ping/42">http://localhost:8080/ping/42</a>.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
