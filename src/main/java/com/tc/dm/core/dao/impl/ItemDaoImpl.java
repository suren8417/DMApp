package com.tc.dm.core.dao.impl;

import com.tc.dm.core.entities.Item;
import com.tc.dm.core.util.CommonUtil;
import com.tc.dm.rest.dto.ItemSearchParam;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDaoImpl extends GenericDaoJpaImpl<Long, Item> {

    @Override
    public List<Item> search(Map<String, String> params) {

        ItemSearchParam itemSearchParam = ItemSearchParam.fromMap(params);

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> rootItem = cq.from(Item.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if(!CommonUtil.isNullOrEmpty(itemSearchParam.getTextToSearch())) {
            predicates.add(cb.like(rootItem.<String>get("title"), "%"+itemSearchParam.getTextToSearch()+"%"));
        }
        if(!CommonUtil.isNullOrEmpty(itemSearchParam.getType())) {
            predicates.add(cb.like(rootItem.<String>get("type"), "%"+itemSearchParam.getType().toString()+"%"));
        }
        if(!CommonUtil.isNullOrEmpty(itemSearchParam.getDateOfOriginFrom())) {
            predicates.add(cb.greaterThanOrEqualTo(rootItem.<Date>get("dateOfOrigin"), itemSearchParam.getDateOfOriginFrom()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Item> tq = em.createQuery(cq);
        return tq.getResultList();
    }
}
