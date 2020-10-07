package com.sd.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sd.model.URL;

import reactor.core.publisher.Mono;

@Repository
public interface URLRepository extends ReactiveMongoRepository<URL, String> {
	
    Mono<URL> findByShortUrl(String shortUrl);
}