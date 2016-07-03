package com.tc.dm.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<K extends Serializable, E>{
    public E create(E value);
    public E find(Class<? extends E> entity, K id);
    public E update(E value);
    public void delete(E value);
    public List<E> findPage(int startIndex, int pageSize, String order);
    public E findLast();
    public List<E> findAll();
    public List<E> search(Map<String, String> params);
}

/**
 *
 * TODO rename key_word to item_key_word
 * TODO
 *
 * */