package com.sd.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlShortenResponse {

	String shortUrl;
}
