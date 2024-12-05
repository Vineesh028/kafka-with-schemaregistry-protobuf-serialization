package com.kafka.schema.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.schema.model.UserProto.User;
import com.kafka.schema.service.SenderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EventController {

	private final SenderService senderService;

    @PostMapping("/event")
    public ResponseEntity<String> sendMessage(@RequestBody User user) throws InterruptedException, ExecutionException {
    	
    	String response = senderService.send(user);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    	
      
    }
}