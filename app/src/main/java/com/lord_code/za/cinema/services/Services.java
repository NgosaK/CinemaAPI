package com.lord_code.za.cinema.services;

import java.util.List;

/**
 * Created by ADRIAN on 23/9/2015.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();

}
