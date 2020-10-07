package com.sd.service;

import com.sd.model.URL;
import com.sd.model.UrlShortenRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface URLService {
    Mono<URL> create(UrlShortenRequest longUrl);
     
    Mono<URL> findByShortUrl(String shortUrl);
    
    Flux<URL> findAll();
 
    Mono<URL> update(URL u);
 
    Mono<Void> delete(URL u);
}
