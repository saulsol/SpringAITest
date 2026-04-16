package com.example.springaitest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AiServiceMultiMessage {

    private final ChatClient chatClient;

    public AiServiceMultiMessage(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String multiMessage(String question, List<Message> chatMemory) {
        SystemMessage systemMessage = SystemMessage.builder()
                .text("""
                        당신은 AI 비서입니다.
                        제공되는 지난 대화 내용을 보고 우선적으로 답변해 주세요
                        """)
                .build();

        // 대화를 처음 시작할 경우, 시스템 메시지 저장
        if(chatMemory.size() == 0) {
            chatMemory.add(systemMessage);
        }

        // 이전 대화 내용 출력
        log.info(chatMemory.toString());

        // LLM에게 요청하고 응답받기
        ChatResponse chatResponse = chatClient.prompt()
                .messages(chatMemory)  // 이전 대화 내용 추가
                .user(question)        // 사용자 메시지 추가
                .call()                // 동기 방식으로 답변 얻기
                .chatResponse();       // ChatResponse로 반환하기

        // 대화 메세지 저장
        UserMessage userMessage = UserMessage.builder()
                .text(question)
                .build();
        chatMemory.add(userMessage);

        AssistantMessage assistantMessage = chatResponse.getResult().getOutput();
        chatMemory.add(assistantMessage);

        // LLM의 텍스트 답변 변환
        String text = assistantMessage.getText();

        return text;
    }



}
