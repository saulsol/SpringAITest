package com.example.springaitest.controller;

import com.example.springaitest.service.AiServicePromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiControllerPromptTemplate {

    @Autowired
    private AiServicePromptTemplate aiServicePromptTemplate;

    @PostMapping(
            value = "/prompt-template",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_NDJSON_VALUE
    )
    public Flux<String> promptTemplate(
            @RequestParam("statement") String statement,
            @RequestParam("language") String language) {
        Flux<String> response = aiServicePromptTemplate.promptTemplate4(statement, language);

        return response;
    }
}
