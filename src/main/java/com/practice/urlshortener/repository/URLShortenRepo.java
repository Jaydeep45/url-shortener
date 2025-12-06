package com.practice.urlshortener.repository;

import com.practice.urlshortener.model.URLShorten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface URLShortenRepo extends JpaRepository<URLShorten, UUID> {
    Optional<URLShorten> getURLShortenByShortCode(String shortCode);
}
