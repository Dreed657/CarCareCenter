package com.local.carcarecenter.service;

import java.util.List;

/**
 * @param <I> InputModel
 * @param <V> ViewModel
 */
public interface AbstractBaseService<I, V> {
   V getById(Integer id);
   List<V> getAll();
   V save(I model);
   V update(Integer id, I model);
   boolean delete(Integer id);
}
