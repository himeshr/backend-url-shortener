package com.sd.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
 
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
@Builder
@Data
public class URL implements Serializable {
 
	private static final long serialVersionUID = 2615352502735655431L;

	@Transient
    public static final String SEQUENCE_NAME = "url_sequence";
	
    @Id
    String id;
    
    @Indexed(expireAfter = "3d")
    private LocalDateTime createdDate;
    
    String shortUrl;
    
    String longUrl;
 }