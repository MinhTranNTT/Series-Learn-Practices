package com.example.springrest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        byte[] responseBody = StreamUtils.copyToByteArray(response.getBody());
        logResponse(response, responseBody);
        return new CustomClientHttpResponse(response, responseBody);
    }

    private void logRequest(HttpRequest request, byte[] body) {

        // if (log.isDebugEnabled()) {
        //     log.debug("===========================request begin================================================");
        //     log.debug("URI         : {}", request.getURI());
        //     log.debug("Method      : {}", request.getMethod());
        //     log.debug("Headers     : {}", request.getHeaders());
        //     log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        //     log.debug("==========================request end================================================");
        // }

        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.info("==========================request end================================================");
    }

    private void logResponse(ClientHttpResponse response, byte[] responseBody) throws IOException {

        // if (log.isDebugEnabled()) {
        //     log.debug("============================response begin==========================================");
        //     log.debug("Status code  : {}", response.getStatusCode());
        //     log.debug("Status text  : {}", response.getStatusText());
        //     log.debug("Headers      : {}", response.getHeaders());
        //     log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        //     log.debug("=======================response end=================================================");
        // }

        log.info("============================response begin==========================================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        // log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.info("Response body: {}", new String(responseBody, Charset.defaultCharset()));
        log.info("=======================response end=================================================");
    }
}
