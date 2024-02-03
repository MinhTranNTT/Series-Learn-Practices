package com.example.springrest.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.InputStream;

public class EmptyClientHttpResponse implements ClientHttpResponse {
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.OK;
    }

    @Override
    public String getStatusText() {
        return "Gateway Timeout";
    }

    @Override
    public void close() {
    }

    @Override
    public InputStream getBody() {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        return new HttpHeaders();
    }
}
