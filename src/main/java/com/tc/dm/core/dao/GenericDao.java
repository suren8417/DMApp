package com.tc.dm.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<K extends Serializable, E>{
    public E create(E value);
    public E find(Class<? extends E> entity, K id);
    public E update(E value);
    public void delete(E value);
    public List<E> findPage(int pageIndex, int pageSize);
    public List<E> findAll();
}

/**
 * TODO rename account to user/delete account
 * TODO rename key_word to item_key_word
 * TODO
 *
 * */