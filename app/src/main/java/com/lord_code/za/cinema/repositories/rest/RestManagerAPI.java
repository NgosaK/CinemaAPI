package com.lord_code.za.cinema.repositories.rest;


import com.lord_code.za.cinema.model.manager;
import com.lord_code.za.cinema.repositories.RestAPI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2015/09/27.
 */
public class RestManagerAPI implements RestAPI<manager,Long> {

    final String BASE_URL="http://jinx-wubbalubba.rhcloud.com/managers/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public manager get(Long id) {

        final String url = BASE_URL+id.toString()+"/managers/";
        HttpEntity<manager> requestEntity = new HttpEntity<manager>(requestHeaders);
        ResponseEntity<manager> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, manager.class);
        manager subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public String post(manager entity) {

        final String url = BASE_URL+"/create";
        HttpEntity<manager> requestEntity = new HttpEntity<manager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(manager entity) {

        final String url = BASE_URL+entity.getId()+"/update/";
        HttpEntity<manager> requestEntity = new HttpEntity<manager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(manager entity) {

        final String url = BASE_URL+entity.getId()+"/delete/";
        HttpEntity<manager> requestEntity = new HttpEntity<manager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<manager> getAll() {

        List<manager> subjects = new ArrayList<manager>();
        final String url = BASE_URL;
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<manager[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, manager[].class);
        manager[] results = responseEntity.getBody();

        for (manager subject : results) {
            subjects.add(subject);
        }
        return subjects;
    }
    }

