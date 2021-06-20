package com.example.hints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@NativeHint(options = {"--enable-https"})
@SpringBootApplication
public class HintsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HintsApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(@Value("classpath:/greeting.txt") Resource resource) {
        return args -> {
            try (InputStream in = resource.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                log.info("There are {} lines", lines.size());
            }
        };
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