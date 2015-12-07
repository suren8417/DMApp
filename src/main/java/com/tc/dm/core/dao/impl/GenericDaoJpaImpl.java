package com.tc.dm.core.dao.impl;

import com.tc.dm.core.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GenericDaoJpaImpl<K extends Serializable, E> implements GenericDao<K, E> {

    private Class<E> persistentClass;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.persistentClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
    }


    @Override
    public E create(E value) {
        //em.getTransaction().begin();
        em.persist(value);
        //em.flush();
        //em.getTransaction().commit();
        return value;
    }

    @Override
    public E find(Class<? extends E> entity, K id) {
        return em.find(entity, id);
    }

    @Override
    public E update(E value) {
        return em.merge(value);
    }

    @Override
    public void delete(E value) {
        em.remove(em.contains(value) ? value : em.merge(value));
        //em.remove(value);
    }

    @Override
    public List<E> findPage(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<E> findAll() {
        String query = "Select t from " + persistentClass.getSimpleName() + " t";
        List<E> results = em.createQuery(query).getResultList();
        return (results==null)?new ArrayList<E>():results;
    }
}
