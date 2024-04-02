package com.maan.crm.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.VehicleRTOMaster;
import com.maan.crm.repository.MotorMakeMasterRepository;
import com.maan.crm.req.MotorMakeGetAllReq;
import com.maan.crm.req.MotorMakeGetReq;
import com.maan.crm.req.MotorMakeSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorMakeDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleRTODropDownRes;
import com.maan.crm.service.MotorMakeMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class MotorMakeMasterServiceImpl implements MotorMakeMasterService {

	@Autowired
	private MotorMakeMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(MotorMakeMasterServiceImpl.class);

	// DropDown
	@Override
	public List<DropDownRes> getMotorMakeMasterDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {

			Date today = new Date();

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorMakeMaster> query = cb.createQuery(MotorMakeMaster.class);
			List<MotorMakeMaster> list = new ArrayList<MotorMakeMaster>();

			// Find All
			Root<MotorMakeMaster> c = query.from(MotorMakeMaster.class);

			// Select
			query.select(c);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorMakeMaster> ocpm1 = amendId.from(MotorMakeMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("makeId"), ocpm1.get("makeId"));
			amendId.where(a1);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("makeNameEn")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			query.where(n1, n2, n3).orderBy(orderList);

			// Get Result
			TypedQuery<MotorMakeMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (MotorMakeMaster data : list) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getMakeId().toString());
				res.setCodeDesc(data.getMakeNameEn());
				res.setCodeStatus(data.getInsCompanyId());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<MotorMakeDropDownRes> getallMotorDropdown(MotorMakeGetAllReq req) {
		List<MotorMakeDropDownRes> resList = new ArrayList<MotorMakeDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorMakeMaster> query = cb.createQuery(MotorMakeMaster.class);
			List<MotorMakeMaster> list = new ArrayList<MotorMakeMaster>();
			
			// Find All
			Root<MotorMakeMaster>    c = query.from(MotorMakeMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<MotorMakeMaster> ocpm1 = effectiveDate.from(MotorMakeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("makeId"),ocpm1.get("makeId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorMakeMaster> ocpm2 = amendId.from(MotorMakeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("makeId"),ocpm2.get("makeId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("makeNameEn")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId()) ;

			query.where(n1,n2,n3, n4).orderBy(orderList);
			
			// Get Result
			TypedQuery<MotorMakeMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list =  result.getResultList();  

			for (MotorMakeMaster data : list) {
				MotorMakeDropDownRes res = new MotorMakeDropDownRes();
				res = mapper.map(data, MotorMakeDropDownRes.class);
				res.setEntryDate(data.getEntryDate());
				res.setEffectiveDate(data.getEffectiveDate());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}



	@Override
	public MotorMakeDropDownRes getMakeId(MotorMakeGetReq req) {
		MotorMakeDropDownRes res = new MotorMakeDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorMakeMaster> query = cb.createQuery(MotorMakeMaster.class);
			List<MotorMakeMaster> list = new ArrayList<MotorMakeMaster>();
			
			// Find All
			Root<MotorMakeMaster>    c = query.from(MotorMakeMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<MotorMakeMaster> ocpm1 = effectiveDate.from(MotorMakeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("makeId"),ocpm1.get("makeId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorMakeMaster> ocpm2 = amendId.from(MotorMakeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("makeId"),ocpm2.get("makeId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("makeNameEn")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("makeId"),req.getMakeId()) ;
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

			query.where(n1,n2,n3,n4, n5).orderBy(orderList);
			
			// Get Result
			TypedQuery<MotorMakeMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , MotorMakeDropDownRes.class);
			res.setEntryDate(list.get(0).getEntryDate());
			res.setEffectiveDate(list.get(0).getEffectiveDate());

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}


	@Override
	public List<Error> validateMakeMotor(MotorMakeSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessRes saveMakeMotor(MotorMakeSaveReq req) {
		SuccessRes res = new SuccessRes();
		MotorMakeMaster saveData = new MotorMakeMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer makeId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(Integer.toString(req.getMakeId()))) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<MotorMakeMaster> query = cb.createQuery(MotorMakeMaster.class);
				
				// Find
				Root<MotorMakeMaster>    c = query.from(MotorMakeMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("makeId")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<MotorMakeMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				MotorMakeMaster data = new MotorMakeMaster();
				data =  result.getResultList().get(0);  
				makeId = data.getMakeId()+ 1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				res.setSucessId(makeId.toString());
				
			} else {
				// Update
				makeId = Integer.valueOf(req.getMakeId());
				List<MotorMakeMaster> findDatas = repo.findByMakeIdAndEffectiveDateOrderByAmendIdDesc(makeId ,req.getEffectiveDate()) ;
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
				res.setSucessId(makeId.toString());

			}
			// Mapping
			req.setEffectiveDate(req.getEffectiveDate());
			saveData = mapper.map(req, MotorMakeMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setMakeId(makeId);
			saveData.setAmendId(amendId);
			
			repo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
           return null;
		}
		return res;
	}
	
	
}
