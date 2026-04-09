package com.example.springaitest.controller;

import com.example.springaitest.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiController {

    @Autowired
    private AiService aiService;

    @Autowired
    private AiServiceByChatClient aiServiceByChatClient;

    // 요청 매핑 메서드
    @PostMapping(
            value = "/chat",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_NDJSON_VALUE
    )
    public Flux<String> chatModel(@RequestParam("question") String question) {
//        Flux<String> answer = aiService.generateText(question);
        return aiServiceByChatClient.generateText(question);
    }
}
