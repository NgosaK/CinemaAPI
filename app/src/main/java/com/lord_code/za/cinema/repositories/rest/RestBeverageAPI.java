package com.lord_code.za.cinema.repositories.rest;

import com.lord_code.za.cinema.model.beverage;
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
public class RestBeverageAPI implements RestAPI<beverage,Long> {

    final String BASE_URL="http://jinx-wubbalubba.rhcloud.com/beverages/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public beverage get(Long id) {
        final String url = BASE_URL+id.toString()+"/beverages/";
        HttpEntity<beverage> requestEntity = new HttpEntity<beverage>(requestHeaders);
        ResponseEntity<beverage> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, beverage.class);
        beverage subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public String post(beverage entity) {
        final String url = BASE_URL+"/create";
        HttpEntity<beverage> requestEntity = new HttpEntity<beverage>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(beverage entity) {
        final String url = BASE_URL+entity.getCode()+"/update/";
        HttpEntity<beverage> requestEntity = new HttpEntity<beverage>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(beverage entity) {
        final String url = BASE_URL+entity.getCode()+"/delete/";
        HttpEntity<beverage> requestEntity = new HttpEntity<beverage>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<beverage> getAll() {
        List<beverage> subjects = new ArrayList<beverage>();
        final String url = BASE_URL+"beverage/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<beverage[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, beverage[].class);
        beverage[] results = responseEntity.getBody();

        for (beverage subject : results) {
            subjects.add(subject);
        }
        return subjects;
    }
}
