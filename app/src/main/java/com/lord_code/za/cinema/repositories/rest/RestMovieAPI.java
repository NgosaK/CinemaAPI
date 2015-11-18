package com.lord_code.za.cinema.repositories.rest;


import com.lord_code.za.cinema.model.movie;
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
public class RestMovieAPI implements RestAPI<movie,Long> {

    final String BASE_URL="http://jinx-wubbalubba.rhcloud.com/movies/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public movie get(Long id) {
        final String url = BASE_URL+"movie/"+id.toString();
        HttpEntity<movie> requestEntity = new HttpEntity<movie>(requestHeaders);
        ResponseEntity<movie> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, movie.class);
        movie subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public String post(movie entity) {
        final String url = BASE_URL+"create";
        HttpEntity<movie> requestEntity = new HttpEntity<movie>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(movie entity) {
        final String url = BASE_URL+"update/"+entity.getId();
        HttpEntity<movie> requestEntity = new HttpEntity<movie>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(movie entity) {
        final String url = BASE_URL+"delete/"+entity.getId();
        HttpEntity<movie> requestEntity = new HttpEntity<movie>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<movie> getAll() {
        List<movie> subjects = new ArrayList<movie>();
        final String url = BASE_URL;
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<movie[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, movie[].class);
        movie[] results = responseEntity.getBody();

        for (movie subject : results) {
            subjects.add(subject);
        }
        return subjects;
    }
}
