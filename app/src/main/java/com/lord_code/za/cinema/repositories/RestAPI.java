package com.lord_code.za.cinema.repositories;

import java.util.List;

/**
 * Created by ADRIAN on 23/9/2015.
 */
public interface RestAPI<S,ID> {

    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();

}
