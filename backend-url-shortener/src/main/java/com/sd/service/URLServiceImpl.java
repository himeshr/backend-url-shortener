package com.sd.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.sd.dao.URLRepository;
import com.sd.model.URL;
import com.sd.model.UrlShortenRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class URLServiceImpl implements URLService {

	@Autowired
	URLRepository urlRepo;
	
	public Mono<URL> create(UrlShortenRequest request) {
		final String shortUrl = Hashing.murmur3_32().hashString(String.join(" : ", LocalDateTime.now().toString(), request.getLongUrl()), StandardCharsets.UTF_8).toString();
		
		return urlRepo.insert(URL.builder()
				.longUrl(request.getLongUrl())
				.shortUrl(shortUrl)
				.createdDate(LocalDateTime.now())
				.build());
	}

	public Mono<URL> findByShortUrl(String shortUrl) {
		return urlRepo.findByShortUrl(shortUrl);
	}

	public Flux<URL> findAll() {
		return urlRepo.findAll();
	}

	public Mono<URL> update(URL u) {
		return urlRepo.save(u);
	}

	public Mono<Void> delete(URL u) {
		return urlRepo.delete(u);
	}

}