package com.heo.dae.won.slackmessage.main;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.heo.dae.won.slackmessage.message.BlockService;
import com.heo.dae.won.slackmessage.message.RestTempService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    private final RestTemplate restTemplate;
    private final BlockService blockService;
    private final RestTempService restTempService;

    public MainController(RestTemplateBuilder restTemplateBuilder
                            , BlockService blockService
                            , RestTempService restTempService){
        this.restTemplate = restTemplateBuilder.build();
        this.blockService = blockService;
        this.restTempService = restTempService;
    }

    @GetMapping("/block")
    public ResponseEntity<String> block() throws JsonProcessingException {
        ResponseEntity<String> response = restTempService.restExchange();

        return response;
    }
}
