package com.thomaskasene.springboot.tomcat.headercount;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PingController {

    private final RestTemplate restTemplate;

    /**
     * Sends a HTTP GET request to {@code /pong} with the specified number of items in the HTTP-header {@code list-header}.
     */
    @GetMapping("/ping/{itemCount}")
    public void ping(@PathVariable("itemCount") int itemCount) {

        List<String> headerItems = LongStream.range(10000000000L, 10000000000L + itemCount)
                .boxed()
                .map(String::valueOf)
                .collect(toList());

        HttpHeaders headers = new HttpHeaders();
        headers.addAll("list-header", headerItems);

        restTemplate.exchange("http://localhost:8080/pong", HttpMethod.GET, new HttpEntity<>(headers), String.class);
    }

    @GetMapping("/pong")
    public String pong() {
        return "Hello, world!";
    }
}
