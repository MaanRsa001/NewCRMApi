package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maan.crm.bean.PlateCharMaster;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.service.PlateCharService;

@Service
public class PlateCharServiceImpl implements PlateCharService{

	@Autowired
	private EntityManager em;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<DropDownRes> getplateCharDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {

			List<PlateCharMaster> list = new ArrayList<PlateCharMaster>();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PlateCharMaster> query = cb.createQuery(PlateCharMaster.class);
			Root<PlateCharMaster> c = query.from(PlateCharMaster.class);
			
			

			// Find All
			

			// Select
			query.select(c);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<PlateCharMaster> ocpm1 = amendId.from(PlateCharMaster.class);			
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("plateDefinedBy"), ocpm1.get("plateDefinedBy"));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("plateCharId"), ocpm1.get("plateCharId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("status"), ocpm1.get("status"));
			
			amendId.where(a1,a2,a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("plateCharId")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("amendId"), amendId);
			
			query.where(n1, n2).orderBy(orderList);

			// Get Result
			TypedQuery<PlateCharMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (PlateCharMaster data : list) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getPlateCharId().toString());
				res.setCodeDesc(data.getPlateCharEn());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

}
