package com.tc.dm.core.dao.impl;

import com.tc.dm.core.dao.GenericDao;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericDaoJpaImpl<K extends Serializable, E> implements GenericDao<K, E> {

    protected Class<E> persistentClass;

    @PersistenceContext
    protected EntityManager em;

//    protected FullTextEntityManager ftem;

    public GenericDaoJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.persistentClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];

//        ftem = Search.getFullTextEntityManager(em);
//        try {
//            ftem.createIndexer().startAndWait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
    public List<E> findPage(int startIndex, int pageSize, String order) {
        order = (!StringUtils.isEmpty(order) && ("asc".equals(order) || "desc".equals(order)))?" ".concat(order):"";
        String query = "Select t from " + persistentClass.getSimpleName() + " t order by t.id" + order;
        List<E> results = em.createQuery(query).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
        return (results==null)?new ArrayList<E>():results;
    }

    @Override
    public E findLast() {
        String query = "Select t from " + persistentClass.getSimpleName() + " t order by t.id desc";
        List<E> results = em.createQuery(query).setMaxResults(1).getResultList();
        return (results==null)?null:(E)results.get(0);
    }

    @Override
    public List<E> findAll() {
        String query = "Select t from " + persistentClass.getSimpleName() + " t";
        List<E> results = em.createQuery(query).getResultList();
        return (results==null)?new ArrayList<E>():results;
    }

    @Override
    public List<E> search(Map<String, String> params) {
        List<E> results = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(this.persistentClass);
        Root<E> entityRoot = cq.from(this.persistentClass);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for(String attr : params.keySet()) {
            if(entityRoot.get(attr) != null){
                predicates.add(cb.like((Expression) entityRoot.get(attr), "%" + params.get(attr) + "%" ));
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<E> q = em.createQuery(cq);

        results = q.getResultList();
        return results==null?new ArrayList<E>():results;
    }
}
