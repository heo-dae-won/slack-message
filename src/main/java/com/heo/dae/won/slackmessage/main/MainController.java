package com.heo.dae.won.slackmessage.main;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final RestTemplate restTemplate;

    @Value("${slack.webhook}")
    private String SLACK_WEBHOOK;

    public MainController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/response")
    public void response(){
        System.out.println("resposne >>>>>>>>>>>>>>>>>>>");
    }

    @PostMapping("/response")
    public void responsePost(String callback_id){
        System.out.println("callback id >>> " + callback_id);
        System.out.println("post resposne >>>>>>>>>>>>>>>>>>>");
    }

    @GetMapping("/send")
    public void send(){
//        HttpHeaders headers = requestDataImpl.setRequestHeader(type);
//        Map<String, Object> requestBody = requestDataImpl.setRequestBody(type, msg);
//        String url = "slack_"

        HttpHeaders headers = new HttpHeaders();

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("username", "test-slack-template");
        requestBody.put("text", "test-message");
        List<Map<String,Object>> attachments = new ArrayList<>();
        Map<String, Object> attach = new HashMap<>();
        attach.put("text", "test - text");
        attach.put("fallback", "You are unable to choose a game");
        attach.put("callback_id", "worp_game");
        attach.put("color", "#3AA3E3");
        attach.put("attachment_type", "default");

        List<Map<String,Object>> actions = new ArrayList<>();
        Map<String, Object> action = new HashMap<>();
        action.put("name", "game");
        action.put("text", "game");
        action.put("type", "button");
        action.put("value", "chess");
        action.put("url","https://google.com");

        Map<String,Object> action2 = new HashMap<>();

        action2.put("name", "game");
        action2.put("text", "Thermonuclear War");
        action2.put("type", "button");
        action2.put("value", "war");

        var test = "";

        Map<String, Object> confirm = new HashMap<>();
        confirm.put("title", "are you sure?");
        confirm.put("text", "Wouldn't you prefer a good game of chess?");
        confirm.put("ok_text", "yes");
        confirm.put("dismiss_text", "No");
        confirm.put("url","https://google.com");

        action2.put("confirm", confirm);

        actions.add(action);
        actions.add(action2);

        attach.put("actions", actions);

        attachments.add(attach);
        requestBody.put("attachments", attachments);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(SLACK_WEBHOOK, HttpMethod.POST, entity, String.class);

        System.out.println("response == " + response.getStatusCode());
        System.out.println("url == " + SLACK_WEBHOOK);
    }

}
