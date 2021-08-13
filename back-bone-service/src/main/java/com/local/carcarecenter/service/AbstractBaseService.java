package com.local.carcarecenter.service;

import java.util.List;

/**
 * @param <I> InputModel
 * @param <V> ViewModel
 */
public interface AbstractBaseService<I, V> {
   V getById(String id);
   List<V> getAll();
   V create(I model);
   V update(String id, I model);
   boolean delete(String id);
}
