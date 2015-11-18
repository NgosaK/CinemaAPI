package com.lord_code.za.cinema.services.impl;

import com.lord_code.za.cinema.repositories.RestAPI;
import com.lord_code.za.cinema.repositories.rest.RestMovieAPI;
import com.lord_code.za.cinema.model.movie;
import com.lord_code.za.cinema.services.MovieService;

import java.util.List;

/**
 * Created by User on 2015/09/27.
 */
public class MovieServiceImpl implements MovieService {

    final RestAPI<movie,Long> rest = new RestMovieAPI();

    @Override
    public movie findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(movie entity) {
        return rest.post(entity);
    }

    @Override
    public String update(movie entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(movie entity) {
        return rest.delete(entity);
    }

    @Override
    public List<movie> findAll() {
        return rest.getAll();
    }
}
