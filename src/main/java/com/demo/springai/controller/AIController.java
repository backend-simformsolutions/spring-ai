package com.demo.springai.controller;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
public class AIController {

    ChatClient chatClient;

    public AIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt")
    public String askAI(@RequestParam String input) {
        return chatClient.prompt(input).call().content();
    }

    @GetMapping("/flux-prompt")
    public Flux<String> getFluxResponse(@RequestParam String input) {
        Flux<String> output = chatClient.prompt()
                .user(input)
                .stream()
                .content();

        return output;
    }

    @GetMapping("/response")
    public String getResponse(@RequestParam String sports) {
        /*String promptMessage = """
                List of 5 most popular sports personalities in {sports} along
                with their career achievements.
                Show the details in the proper Readable format.
                """;

        PromptTemplate template = new PromptTemplate(promptMessage);
        Prompt prompt = template.create(Map.of("sports", sports));
        return chatClient.prompt(prompt).call().content();*/

        var systemMessage = new SystemMessage("""
                Your primary function is to share the information about the sports personalities.
                If someone asks about anything else, you can say you only share about sports categories
                """);

        var userMessage = new UserMessage(
                String.format("List of 5 most popular sports personalities in %s along\n" +
                        " with their career achievements.\n" +
                        " Show the details in the proper Readable format.", sports));
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return chatClient.prompt(prompt).call().content();
    }


}
