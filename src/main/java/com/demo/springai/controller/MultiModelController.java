package com.demo.springai.controller;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModelController {


    @Autowired
    private OpenAiImageModel openAiImageModel;

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @Autowired
    private OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @GetMapping("/image/{prompt}")
    public String generateImage(@PathVariable("prompt") String prompt) {

        ImageResponse response = openAiImageModel.call(
                new ImagePrompt(prompt, OpenAiImageOptions.builder()
                        .withQuality("hd")
                        .withN(1)
                        .withHeight(1024)
                        .withWidth(1024).build()));

        return response.getResult().getOutput().getUrl();
    }

    @GetMapping("/image-to-text")
    public String generateImageToText() {
        String response = ChatClient.create(chatModel).prompt()
                .user(promptUserSpec -> promptUserSpec.text("Explain what do you see in this Image")
                        .media(MimeTypeUtils.IMAGE_PNG,
                                new FileSystemResource("C:\\Sudhir\\Goal\\spring-ai\\src\\main\\resources\\db_image.png")))
                .call()
                .content();

        return response;
    }

    @GetMapping("/audio-to-text")
    public String generateTranscription() {
        OpenAiAudioTranscriptionOptions options
                = OpenAiAudioTranscriptionOptions.builder()
                .withLanguage("en")
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .withTemperature(0f)  // Temperature controls the randomness or creativity of the AI's output. It accepts values between 0.0 and 1.0.
                .build();

        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(
                new FileSystemResource("C:\\Sudhir\\Goal\\spring-ai\\src\\main\\resources\\harvard.wav"), options
        );

        AudioTranscriptionResponse response = openAiAudioTranscriptionModel.call(prompt);

        return response.getResult().getOutput();

    }

    @GetMapping("/text-to-audio/{prompt}")
    public ResponseEntity<Resource> generateAudio(@PathVariable("prompt") String prompt) {
        OpenAiAudioSpeechOptions options
                = OpenAiAudioSpeechOptions.builder()
                .withModel("tts-1")      // "tts-1" is one of OpenAI's default TTS models.
                .withSpeed(1.0f)        // Speed of the speech (1.0 is normal speed)
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.SHIMMER)
                .build();

        SpeechPrompt speechPrompt
                = new SpeechPrompt(prompt, options);

        SpeechResponse response
                = openAiAudioSpeechModel.call(speechPrompt);

        byte[] responseBytes = response.getResult().getOutput();

        ByteArrayResource byteArrayResource
                = new ByteArrayResource(responseBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(byteArrayResource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("ai_audio.mp3")
                                .build().toString())
                .body(byteArrayResource);
    }

}
