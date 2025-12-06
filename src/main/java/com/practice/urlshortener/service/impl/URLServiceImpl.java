package com.practice.urlshortener.service.impl;

import com.practice.urlshortener.dto.URLRequest;
import com.practice.urlshortener.dto.URLResponse;
import com.practice.urlshortener.exception.ResourceNotFoundException;
import com.practice.urlshortener.model.URLShorten;
import com.practice.urlshortener.repository.URLShortenRepo;
import com.practice.urlshortener.service.URLService;
import com.practice.urlshortener.util.ShortCode;
import org.springframework.stereotype.Service;

@Service
public class URLServiceImpl implements URLService {


    final URLShortenRepo urlShortenRepo;

    public URLServiceImpl(URLShortenRepo urlShortenRepo) {
        this.urlShortenRepo = urlShortenRepo;
    }

    @Override
    public URLResponse createShortURL(URLRequest urlRequest) {
        URLShorten urlShorten = new URLShorten();
        urlShorten.setUrl(urlRequest.url());
        urlShorten = urlShortenRepo.save(urlShorten);
        urlShorten.setShortCode(ShortCode.generateShortCode());
        urlShorten = urlShortenRepo.save(urlShorten);
        return new URLResponse(urlShorten.getUrl(), urlShorten.getShortCode());
    }

    @Override
    public URLResponse getShortURL(String shortCode) {
        URLShorten urlShorten = findURLShortenByShortCode(shortCode);
        urlShorten.setHitCount(urlShorten.getHitCount() + 1);
        urlShortenRepo.save(urlShorten);
        return new URLResponse(urlShorten.getUrl(), urlShorten.getShortCode());
    }

    @Override
    public void deleteShortURL(String shortCode) {
        URLShorten urlShorten = findURLShortenByShortCode(shortCode);
        urlShortenRepo.delete(urlShorten);
    }

    @Override
    public URLResponse updateShortURL(String shortCode, URLRequest urlRequest) {
        URLShorten urlShorten = findURLShortenByShortCode(shortCode);
        urlShorten.setUrl(urlRequest.url());
        urlShorten = urlShortenRepo.save(urlShorten);
        return new URLResponse(urlShorten.getUrl(), urlShorten.getShortCode());
    }

    @Override
    public long getHitCount(String shortCode) {
        URLShorten urlShorten = findURLShortenByShortCode(shortCode);
        return urlShorten.getHitCount();
    }

    private URLShorten findURLShortenByShortCode(String shortCode) {
        return urlShortenRepo.getURLShortenByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
    }
}
