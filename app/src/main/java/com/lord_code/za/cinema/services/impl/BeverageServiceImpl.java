package com.lord_code.za.cinema.services.impl;

import com.lord_code.za.cinema.model.beverage;
import com.lord_code.za.cinema.repositories.RestAPI;
import com.lord_code.za.cinema.repositories.rest.RestBeverageAPI;
import com.lord_code.za.cinema.services.BeverageService;

import java.util.List;

/**
 * Created by User on 2015/09/27.
 */
public class BeverageServiceImpl implements BeverageService {

    final RestAPI<beverage,Long> rest = new RestBeverageAPI();


    @Override
    public beverage findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(beverage entity) {
        return rest.post(entity);
    }

    @Override
    public String update(beverage entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(beverage entity) {
        return rest.delete(entity);
    }

    @Override
    public List<beverage> findAll() {
        return rest.getAll();
    }
}
