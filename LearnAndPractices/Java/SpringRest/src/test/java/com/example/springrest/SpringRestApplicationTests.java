package com.example.springrest;

import com.example.springrest.domain.Hello;
import com.example.springrest.domain.User;
import com.example.springrest.enums.HelloReturnCode;
import com.example.springrest.utils.JsonCustomUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringRestApplicationTests {
    @Autowired private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        String uri = "https://jsonplaceholder.typicode.com/todos";
        // 1 property not sort ui
        // String response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", String.class);
        // List<User> users = JsonCustomUtils.str2list(response, User.class);

        // 2 property sort ui
        // ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos", User[].class);
        // User[] users = response.getBody();

        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", "Bearer token");
        // HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        // ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, User[].class);
        // User[] userArray = response.getBody();
        // List<User> users = Arrays.asList(userArray);

        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", "Bearer token");
        // HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        // ResponseEntity<String> response = restTemplate.exchange("http://localhost:8089/hello", HttpMethod.GET, requestEntity, String.class);
        // String str = response.getBody();

        // 3
        // String response = restTemplate.getForObject("http://localhost:8089/hello", String.class);

        // ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8089/hello/", HttpMethod.GET, entityWithAcceptJsonSuffix(), String.class);
        // String response = exchange.getBody();


        String response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos", String.class).getBody();
        // List<User> users = JsonCustomUtils.str2list(response, User.class);
        List<User> users = JsonCustomUtils.str2list2(response, User.class);
        System.out.println(users);
    }



    private static HttpEntity<?> entityWithAcceptJsonSuffix() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/vnd.api.v1+json");
        return new HttpEntity<>(headers);
    }

    @Test
    public void testHelloCustom() {
        String uri = "http://localhost:8089/hello/improve";

        // Hello[] body = restTemplate.getForEntity(uri, Hello[].class).getBody();
        // List<Hello> helloList = Arrays.asList(body);

        String response = restTemplate.getForEntity(uri, String.class).getBody();
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type type = new TypeToken<List<Hello>>() {}.getType();
        List<Hello> helloList = gson.fromJson(response, type);
        boolean isValid = helloList.stream().allMatch(Hello.validateHello);
        System.out.println("Is valid: " + isValid);


    }

    @Test
    public void testHelloCustomAdvance() {
        String uri = "http://localhost:8089/hello";
        Hello[] body = restTemplate.getForEntity(uri, Hello[].class).getBody();
        List<Hello> helloList = Arrays.asList(body);

        // Gson gson = new GsonBuilder().serializeNulls().create();
        // Type type = new TypeToken<List<Hello>>() {}.getType();
        // List<Hello> helloList = gson.fromJson(response, type);

        for (Hello hello : helloList) {
            HelloReturnCode helloReturnCode = hello.checkValidation();
            if (helloReturnCode != HelloReturnCode.OK) {
                System.out.println("FAIL: " + helloReturnCode.getMessage());
            }
        }

    }

}
