package com.example.springaitest.controller;

import com.example.springaitest.service.AiServiceByChatClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Slf4j
public class AiController {

    private final AiServiceByChatClient aiServiceByChatClient;

    @PostMapping(
            value = "/chat",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_NDJSON_VALUE
    )
    public Flux<String> chat(@RequestParam("question") String question) {
        return aiServiceByChatClient.generateText(question);
    }
}
