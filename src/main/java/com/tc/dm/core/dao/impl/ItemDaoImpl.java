package com.tc.dm.core.dao.impl;

import com.tc.dm.core.entities.Item;
import com.tc.dm.core.util.CommonUtil;
import com.tc.dm.rest.dto.SearchParam;
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
import static com.tc.dm.core.util.CommonUtil.*;

@Repository
public class ItemDaoImpl extends GenericDaoJpaImpl<Long, Item> {

    @Override
    public List<Item> search(Map<String, String> params) {

        SearchParam searchParam = SearchParam.fromMap(params);

        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> rootItem = cq.from(Item.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if(!isNullOrEmpty(searchParam.getTextToSearch())) {
            Predicate hasTitle = cb.like(rootItem.<String>get("title"), "%" + searchParam.getTextToSearch() + "%");
            Predicate hasDesc = cb.like(rootItem.<String>get("description"), "%" + searchParam.getTextToSearch() + "%");
            Predicate hasKeyword = cb.like(rootItem.<String>get("keywords"), "%" + searchParam.getTextToSearch() + "%");
            predicates.add(cb.or(hasTitle, hasDesc, hasKeyword));
        }
        if(!isNullOrEmpty(searchParam.getTypes())) {
            predicates.add(rootItem.<String>get("type").in(searchParam.getTypesAsStrings()));
        }
        if(!isNullOrEmpty(searchParam.getDateOfOriginFrom())) {
            predicates.add(cb.greaterThanOrEqualTo(rootItem.<Date>get("dateOfOrigin"), searchParam.getDateOfOriginFrom()));
        }
        if(!isNullOrEmpty(searchParam.getDateOfOriginTo())) {
            predicates.add(cb.lessThanOrEqualTo(rootItem.<Date>get("dateOfOrigin"), searchParam.getDateOfOriginTo()));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Item> tq = em.createQuery(cq);
        List<Item> resultList = tq.getResultList();
        return resultList==null?new ArrayList<Item>():resultList;
    }
}
