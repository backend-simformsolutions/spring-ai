package com.demo.springai.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;

@RestController
public class OllamaController {

    /*private final ChatModel chatModel;

    public OllamaController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public String getMessages(@RequestParam String prompt) {
        StopWatch sw = new StopWatch();
        sw.start();

        String response = chatModel.call(prompt);
        sw.stop();

        System.out.println("total_time :"+sw.getTotalTimeSeconds());

        return response;
    }*/
}
