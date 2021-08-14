package com.local.carcarecenter.service;

import com.local.carcarecenter.exception.EntityNotFoundExecution;

import java.util.List;

/**
 * @param <I> InputModel
 * @param <V> ViewModel
 */
public interface AbstractBaseService<I, V> {
   V getById(Long id) throws EntityNotFoundExecution;
   List<V> getAll();
   V save(I model) throws EntityNotFoundExecution;
   V update(Long id, I model) throws EntityNotFoundExecution;
   boolean delete(Long id) throws EntityNotFoundExecution;
}
