package com.example.springrest.config;

import com.example.springrest.utils.FolderUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        if (response instanceof EmptyClientHttpResponse) {
            logResponseTimeOut(response, responseBody);
            return new CustomClientHttpResponse(response, responseBody);
        }

        logResponse(response, responseBody);
        return new CustomClientHttpResponse(response, responseBody);

        // try (CustomResponseWrapper responseWrapper = new CustomResponseWrapper(execution.execute(request, body))) {
        //     logResponse(responseWrapper);
        //     return new CustomClientHttpResponse(responseWrapper.getResponse(), responseWrapper.getResponseBody());
        // }
    }

    private void logResponseTimeOut(ClientHttpResponse response, byte[] responseBody) throws IOException {
        log.info("============================response begin==========================================");
        log.info("Status code  : {}", "502 Gateway Timeout");
        log.info("Status text  : Gateway Timeout");
        log.info("Headers      : {}", response.getHeaders());
        log.info("=======================response end=================================================");
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

        // write log to file
        String fullPathTarget = FolderUtils.getFullPathTarget();
        String fileName = FolderUtils.formatDateTime() + ".txt";
        System.out.println("fullPathTarget: " + fullPathTarget);
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("===========================request begin================================================");
        sb.append(System.lineSeparator());
        sb.append("URI         : " + request.getURI());
        sb.append(System.lineSeparator());
        sb.append("Method      : " + request.getMethod());
        sb.append(System.lineSeparator());
        sb.append("Headers     : " + request.getHeaders());
        sb.append(System.lineSeparator());
        sb.append("Request body: " + new String(body, StandardCharsets.UTF_8));
        sb.append(System.lineSeparator());
        sb.append("==========================request end================================================");
        sb.append(System.lineSeparator());

        Path finalPath = Paths.get(fullPathTarget, fileName);
        if(Files.exists(finalPath)) {
            FolderUtils.appendToFile(finalPath, sb.toString());
            System.out.println("Content appended to existing file.");
        } else {
            FolderUtils.createAndWriteToFile(finalPath, sb.toString());
            System.out.println("File created and content written.");
        }
    }

    // way 1
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

        // write log to file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new String(responseBody, Charset.defaultCharset()));
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String fullPathTarget = FolderUtils.getFullPathTarget();
        String fileName = FolderUtils.formatDateTime() + ".txt";
        System.out.println("fullPathTarget: " + fullPathTarget);
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("===========================response begin================================================");
        sb.append(System.lineSeparator());
        sb.append("Status code  : " + response.getStatusCode());
        sb.append(System.lineSeparator());
        sb.append("Status text  : " + response.getStatusText());
        sb.append(System.lineSeparator());
        sb.append("Headers      : " + response.getHeaders());
        sb.append(System.lineSeparator());
        sb.append("Response body: " + objectMapper.writeValueAsString(jsonNode));
        sb.append(System.lineSeparator());
        sb.append("==========================response end================================================");
        sb.append(System.lineSeparator());

        Path finalPath = Paths.get(fullPathTarget, fileName);
        if(Files.exists(finalPath)) {
            FolderUtils.appendToFile(finalPath, sb.toString());
            System.out.println("Content appended to existing file.");
        } else {
            FolderUtils.createAndWriteToFile(finalPath, sb.toString());
            System.out.println("File created and content written.");
        }
    }

    // way 2
    // private void logResponse(CustomResponseWrapper responseWrapper) throws IOException {
    //     log.info("============================response begin==========================================");
    //     log.info("Status code  : {}", responseWrapper.getResponse().getStatusCode());
    //     log.info("Status text  : {}", responseWrapper.getResponse().getStatusText());
    //     log.info("Headers      : {}", responseWrapper.getResponse().getHeaders());
    //     log.info("Response body: {}", new String(responseWrapper.getResponseBody(), StandardCharsets.UTF_8));
    //     log.info("=======================response end=================================================");
    // }

}
