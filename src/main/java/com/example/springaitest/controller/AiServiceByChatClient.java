package com.example.springaitest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class AiServiceByChatClient {


    private final ChatClient chatClient;

    public AiServiceByChatClient(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public Flux<String> generateText(String question) {
        Flux<String> answer = chatClient.prompt()
                .system("사용자 질문에 대해 한국어로 답변을 해야 합니다")
                .user(question)
                .options(ChatOptions.builder()
                        .temperature(0.3)
                        .maxTokens(1000)
                        .build()
                )
                .stream()
                .content();

        return answer;
    }




}
