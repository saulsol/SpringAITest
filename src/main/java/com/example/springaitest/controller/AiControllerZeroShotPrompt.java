package com.example.springaitest.controller;

import com.example.springaitest.service.AiServiceZeroShotPrompt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerZeroShotPrompt {

    @Autowired
    private AiServiceZeroShotPrompt aiServiceZeroShotPrompt;

    @PostMapping(
            value = "/zero-shot-prompt",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String zeroShotPrompt(@RequestParam("review") String review) {
        return aiServiceZeroShotPrompt.zeroShotPrompt(review);
    }


}
