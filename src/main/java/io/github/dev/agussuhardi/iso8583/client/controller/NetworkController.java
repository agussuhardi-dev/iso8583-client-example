package io.github.dev.agussuhardi.iso8583.client.controller;

import io.github.dev.agussuhardi.iso8583.client.service.NetworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agussuhardii
 * {@code @created} 5/2/24 :10:11 AM
 * {@code @project} svc-bni
 */
@RestController
@RequestMapping(path = "/api/network", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class NetworkController {
    private final NetworkService networkService;


    @PostMapping(value = "echo")
    public ResponseEntity<Object> echo() {
        return new ResponseEntity<>(networkService.echo(), HttpStatus.OK);
    }
}
