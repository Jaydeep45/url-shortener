package com.practice.urlshortener.service;


import com.practice.urlshortener.dto.URLRequest;
import com.practice.urlshortener.dto.URLResponse;
import com.practice.urlshortener.model.URLShorten;

public interface URLService {
    URLResponse createShortURL(URLRequest urlRequest);

    URLResponse getShortURL(String shortCode);

    void deleteShortURL(String shortCode);

    URLResponse updateShortURL(String shortCode,URLRequest urlRequest);

    long getHitCount(String shortCode);
}
