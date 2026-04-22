package com.example.springaitest.controller;

import com.example.springaitest.service.AiServiceListOutputConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerListOutputConverter {

    @Autowired
    private AiServiceListOutputConverter aiService;

    @PostMapping(
            value = "/list-output-converter",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<String> listOutputConverter(@RequestParam String city){
        return aiService.listOutputConverterHighLevel(city);
    }

}
