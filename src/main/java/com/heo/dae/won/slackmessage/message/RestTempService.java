package com.heo.dae.won.slackmessage.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestTempService {

    private final RestTemplate restTemplate;

    @Value("${slack.webhook}")
    private String SLACK_WEBHOOK;

    public RestTempService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> restExchange() throws JsonProcessingException {
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
        text.put("text", "확인");

        element.put("type", "button");
        element.put("text", text);
        element.put("style", "primary");
        element.put("value", "click_me_123");

        elements.add(element);
        block.put("elements", elements);
        blocks.add(block);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBlock = mapper.writeValueAsString(blocks);

        requestBody.put("blocks", jsonBlock);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(SLACK_WEBHOOK, HttpMethod.POST, entity, String.class);

        return response;
    }
}
