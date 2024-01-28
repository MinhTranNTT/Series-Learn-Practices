package com.example.springrest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Slf4j
public class CustomResponseWrapper implements AutoCloseable {
    private final ClientHttpResponse response;
    private final byte[] responseBody;

    public CustomResponseWrapper(ClientHttpResponse response) throws IOException {
        this.response = response;
        this.responseBody = StreamUtils.copyToByteArray(response.getBody());
    }

    public ClientHttpResponse getResponse() {
        return response;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    @Override
    public void close() {
        // Close resources if needed
        response.close();
    }
}
