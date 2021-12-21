package com.heo.dae.won.slackmessage.main;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
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
    public void responsePost(@RequestParam Map<String,String> request){
        System.out.println("payload >>> " );
        System.out.println("payload >> " + request.get("payload"));
        ObjectMapper mapper = new ObjectMapper();

        String str = request.get("payload");
        System.out.println("str >> " + str);

        try {
            Map<String,Object> result = mapper.readValue(str,Map.class);
            List<Map<String,Object>> actions = (List<Map<String, Object>>) result.get("actions");
            System.out.println("actions >>> " + actions.size());

            System.out.println("actions.get(0) >>> " + actions.get(0));

            System.out.println("actions.get(0).style >>> " + actions.get(0).get("style"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/response2")
    public void responsePost2(Map<String, Object> data){
        System.out.println("response2222 >>> ");
        data.entrySet().forEach(System.out::println);
        System.out.println("post resposne >>>>>>>>>>>>>>>>>>>");
    }

    @GetMapping("/block")
    public void block() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        Map<String,Object> requestBody = new HashMap<>();

        List<Map<String,Object>> blocks = new ArrayList<>();

        Map<String,Object> block = new HashMap<>();
        block.put("type", "actions");
        List<Map<String,Object>> elements = new ArrayList<>();

        Map<String,Object> element = new HashMap<>();


        Map<String, Object> text = new HashMap<>();
        text.put("type", "plain_text");
        text.put("emoji", true);
        text.put("text", "Approve");

        element.put("type", "button");
        element.put("text", text);
        element.put("style", "primary");
        element.put("value", "click_me_123");

        Map<String,Object> element2 = new HashMap<>();


        Map<String, Object> text2 = new HashMap<>();
        text2.put("type", "plain_text");
        text2.put("emoji", true);
        text2.put("text", "Reject");

        element2.put("type", "button");
        element2.put("text", text2);
        element2.put("style", "danger");
        element2.put("value", "click_no222");

        elements.add(element);
        elements.add(element2);
        block.put("elements", elements);
        blocks.add(block);

        ObjectMapper mapper = new ObjectMapper();
        String jStr = mapper.writeValueAsString(blocks);

        System.out.println("json ++>>> " + jStr);

        requestBody.put("blocks", mapper.writeValueAsString(blocks));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(SLACK_WEBHOOK, HttpMethod.POST, entity, String.class);
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
