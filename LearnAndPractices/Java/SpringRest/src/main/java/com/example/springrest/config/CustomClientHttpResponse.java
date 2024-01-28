package com.example.springrest.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClientHttpResponse implements ClientHttpResponse {
    private final ClientHttpResponse delegate;
    private final byte[] capturedBody;

    public CustomClientHttpResponse(ClientHttpResponse delegate, byte[] capturedBody) {
        this.delegate = delegate;
        this.capturedBody = capturedBody;
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return delegate.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return delegate.getStatusText();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(capturedBody);
    }

    @Override
    public HttpHeaders getHeaders() {
        return delegate.getHeaders();
    }
}
