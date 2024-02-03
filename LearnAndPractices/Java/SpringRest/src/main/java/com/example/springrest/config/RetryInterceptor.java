package com.example.springrest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.net.SocketTimeoutException;

@Slf4j
public class RetryInterceptor implements ClientHttpRequestInterceptor {
    private final RetryTemplate retryTemplate;

    public RetryInterceptor(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            return retryTemplate.execute(new RetryCallback<ClientHttpResponse, IOException>() {
                @Override
                public ClientHttpResponse doWithRetry(RetryContext context) throws IOException {
                    int currentRetryCount = context.getRetryCount();
                    log.info("Retry count: " + (++currentRetryCount));

                    try {
                        return execution.execute(request, body);
                    } catch (ResourceAccessException ex) {
                        if (ex.getCause() instanceof SocketTimeoutException) {
                            return createEmptyResponse();
                        }
                        throw ex;
                    }
                }
            });
        } catch (Exception ex) {
            return this.createEmptyResponse();
        }
    }

    private ClientHttpResponse createEmptyResponse() {
        return new EmptyClientHttpResponse();
    }

}
