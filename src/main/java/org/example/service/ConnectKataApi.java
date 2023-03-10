package org.example.service;

import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ConnectKataApi {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final String URL = "http://94.198.50.185:7081/api/users/";

    public ConnectKataApi(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    //  Получение всех пользователей - …/api/users ( GET )
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        headers.add("cookie", responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        return responseEntity.getBody();
    }

//  Добавление пользователя - …/api/users ( POST )
    public ResponseEntity<String> newUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
    }

//  Изменение пользователя - …/api/users ( PUT )
    public ResponseEntity<String> editUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

//  Удаление пользователя - …/api/users /{id} ( DELETE )
    public ResponseEntity<String> deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(URL + id, HttpMethod.DELETE, entity, String.class);
    }
}