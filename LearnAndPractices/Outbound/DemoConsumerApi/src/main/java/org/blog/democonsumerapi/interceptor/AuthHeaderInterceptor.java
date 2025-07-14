// package org.blog.democonsumerapi.interceptor;
//
// import org.springframework.http.HttpRequest;
// import org.springframework.http.client.ClientHttpRequestExecution;
// import org.springframework.http.client.ClientHttpRequestInterceptor;
// import org.springframework.http.client.ClientHttpResponse;
//
// import java.io.IOException;
//
// public class AuthHeaderInterceptor implements ClientHttpRequestInterceptor {
//
//     private String authToken = "secret-token-123";
//
//     @Override
//     public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//         request.getHeaders().add("Authorization", "Bearer " + authToken);
//         return execution.execute(request, body);
//     }
//
// }
