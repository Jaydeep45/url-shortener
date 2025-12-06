package com.practice.urlshortener.controller;

import com.practice.urlshortener.dto.URLRequest;
import com.practice.urlshortener.dto.URLResponse;
import com.practice.urlshortener.service.URLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
@CrossOrigin(origins = "*")
@Tag(name = "URL Shortener", description = "URL shortening operations")
public class URLController {

    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    @Operation(summary = "Shorten URL", description = "Create a shortened URL")
    public ResponseEntity<URLResponse> createShortURL(@RequestBody URLRequest urlRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlService.createShortURL(urlRequest));
    }

    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<URLResponse> getShortURL(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getShortURL(shortCode));
    }

    @DeleteMapping("/shorten/{shortCode}")
    public ResponseEntity<String> deleteShortURL(@PathVariable String shortCode) {
        urlService.deleteShortURL(shortCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/shorten/{shortCode}")
    public ResponseEntity<URLResponse> updateShortURL(@PathVariable String shortCode, @RequestBody URLRequest urlRequest) {
        return ResponseEntity.ok(urlService.updateShortURL(shortCode, urlRequest));
    }

    @GetMapping("/shorten/count/{shortCode}")
    public ResponseEntity<Long> getHitCount(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getHitCount(shortCode));
    }
}
