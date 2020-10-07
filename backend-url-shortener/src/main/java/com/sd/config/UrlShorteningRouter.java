package com.sd.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sd.handler.ErrorHandler;
import com.sd.handler.UrlShortenerHandler;

@Configuration
public class UrlShorteningRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UrlShortenerHandler handler, ErrorHandler errorHandler) {
        return RouterFunctions.route()
                .onError(Exception.class, errorHandler::handleError)
                .GET("/{shortUrl}", handler::findLongUrlAndRedirect)
                .POST("/shortenUrl", accept(MediaType.APPLICATION_JSON), handler::shortenUrl)
                .build();
    }


}