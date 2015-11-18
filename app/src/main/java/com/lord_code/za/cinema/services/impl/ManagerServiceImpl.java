package com.lord_code.za.cinema.services.impl;

import com.lord_code.za.cinema.model.manager;
import com.lord_code.za.cinema.repositories.rest.RestManagerAPI;
import com.lord_code.za.cinema.repositories.RestAPI;
import com.lord_code.za.cinema.services.ManagerService;

import java.util.List;

/**
 * Created by User on 2015/09/27.
 */
public class ManagerServiceImpl implements ManagerService {

    final RestAPI<manager,Long> rest = new RestManagerAPI();
    @Override
    public manager findById(Long id) {
        return rest.get(id);
    }

    @Override
    public String save(manager entity) {
        return rest.post(entity);
    }

    @Override
    public String update(manager entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(manager entity) {
        return rest.delete(entity);
    }

    @Override
    public List<manager> findAll() {
        return rest.getAll();
    }
}
