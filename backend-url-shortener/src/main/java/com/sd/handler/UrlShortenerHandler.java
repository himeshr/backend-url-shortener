package com.sd.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sd.model.URL;
import com.sd.model.UrlShortenRequest;
import com.sd.model.UrlShortenResponse;
import com.sd.service.URLService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UrlShortenerHandler {
	
	@Value("${short.url.domain}")
	private String shortUrlDomain;
	
	@Autowired
	private URLService urlService;

	
	public Mono<ServerResponse> findLongUrlAndRedirect(ServerRequest serverRequest) {
        String shortUrl = serverRequest.pathVariable("shortUrl");
		return urlService.findByShortUrl(shortUrl).flatMap(urlInfo -> ServerResponse.permanentRedirect(URI.create(urlInfo.getLongUrl())).build())
				.switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("URL not found"));
	}

	public Mono<ServerResponse> shortenUrl(ServerRequest serverRequest) {
        return serverRequest.body(BodyExtractors.toMono(UrlShortenRequest.class))
        		.flatMap(req -> urlService.create(req))
                .flatMap(urlInfo -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(appendUrlDomain(urlInfo)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Unexpected error occurred while shortening URL."));
    }

    private UrlShortenResponse appendUrlDomain(URL urlInfo) {
        
    	if(shortUrlDomain.endsWith("/"))
    		return UrlShortenResponse.builder()
    		.shortUrl(shortUrlDomain.concat(urlInfo.getShortUrl())).build();
        else
        	return UrlShortenResponse.builder()
    		.shortUrl((String.join("/", shortUrlDomain, urlInfo.getShortUrl()))).build();
    }
	
}
