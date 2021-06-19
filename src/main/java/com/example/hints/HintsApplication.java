package com.example.hints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
public class HintsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HintsApplication.class, args);
    }

}

@RestController
@RequestMapping("/")
class GreetingController {
    @GetMapping
    public Greeting greeting() {
        return new Greeting(UUID.randomUUID(), "Hello!!!");
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Greeting {
    private UUID uid;
    private String content;

}