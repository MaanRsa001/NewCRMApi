package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.maan.crm.bean.StateMaster;
import com.maan.crm.repository.StateMasterRepository;
import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.StateMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class StateMasterServiceImpl implements StateMasterService {

	@Autowired
	private StateMasterRepository stateRepo;
	
	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(StateMasterServiceImpl.class);

	// State Master DropDown
	@Override
	public List<DropDownRes> getStateMasterDropdown(){
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			
			Date today  = new Date();
			
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<StateMaster> query = cb.createQuery(StateMaster.class);
			List<StateMaster> list = new ArrayList<StateMaster>();
			
			// Find All
			Root<StateMaster>    c = query.from(StateMaster.class);		
			
			// Select
			query.select(c );
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<StateMaster> ocpm1 = amendId.from(StateMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("stateId"),ocpm1.get("stateId") );
			amendId.where(a1);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("stateName")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			query.where(n1,n2,n3).orderBy(orderList);
			
			// Get Result
			TypedQuery<StateMaster> result = em.createQuery(query);			
			list =  result.getResultList();
			

			for (StateMaster data : list) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getStateId().toString());
				res.setCodeDesc(data.getStateName());
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

	// GetAll StateMaster
	@Override
	public List<StateMasterDropDownRes> getAllStateMaster(StateMasterGetAllReq req) {
		List<StateMasterDropDownRes> resList = new ArrayList<StateMasterDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
						int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
						int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
						
						// Criteria
						CriteriaBuilder cb = em.getCriteriaBuilder();
						CriteriaQuery<StateMaster> query = cb.createQuery(StateMaster.class);
						List<StateMaster> list = new ArrayList<StateMaster>();
						
						// Find All
						Root<StateMaster>    c = query.from(StateMaster.class);		
						
						// Select
						query.select(c );
						
						// Effective Date Max Filter
						Subquery<Long> effectiveDate = query.subquery(Long.class);
						Root<StateMaster> ocpm1 = effectiveDate.from(StateMaster.class);
						effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
						javax.persistence.criteria.Predicate a1 = cb.equal(c.get("stateId"),ocpm1.get("stateId") );
						effectiveDate.where(a1);
						
						// Amend Id Max Filter
						Subquery<Long> amendId = query.subquery(Long.class);
						Root<StateMaster> ocpm2 = amendId.from(StateMaster.class);
						amendId.select(cb.max(ocpm2.get("amendId")));
						javax.persistence.criteria.Predicate a2 = cb.equal(c.get("stateId"),ocpm2.get("stateId") );
						javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
						amendId.where(a2,a3);
						
						// Order By
						List<Order> orderList = new ArrayList<Order>();
						orderList.add(cb.asc(c.get("stateName")));
						
					    // Where	
						javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
						javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
						javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
						javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

						query.where(n1,n2,n3,n4).orderBy(orderList);
						
						// Get Result
						TypedQuery<StateMaster> result = em.createQuery(query);
						result.setFirstResult(limit * offset);
						result.setMaxResults(offset);
						list =  result.getResultList();  


			for (StateMaster data : list) {
				StateMasterDropDownRes res = new StateMasterDropDownRes();
				res = mapper.map(data, StateMasterDropDownRes.class);
				res.setEntryDate(sdf.format(data.getEntryDate()));
				res.setEffectiveDate(sdf.format(data.getEffectiveDate()));
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// Get StateMaster
	@Override
	public StateMasterDropDownRes getStateMasterById(StateMasterGetReq req) {
		StateMasterDropDownRes res = new StateMasterDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<StateMaster> query = cb.createQuery(StateMaster.class);
			List<StateMaster> list = new ArrayList<StateMaster>();
			
			// Find All
			Root<StateMaster>    c = query.from(StateMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<StateMaster> ocpm1 = effectiveDate.from(StateMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("stateId"),ocpm1.get("stateId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<StateMaster> ocpm2 = amendId.from(StateMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("stateId"),ocpm2.get("stateId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("stateName")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("stateId"),req.getStateId() );
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;
			query.where(n1,n2,n3,n4,n5).orderBy(orderList);
			
			// Get Result
			TypedQuery<StateMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , StateMasterDropDownRes.class);
			res.setEntryDate(sdf.format(list.get(0).getEntryDate()));
			res.setEffectiveDate(sdf.format(list.get(0).getEffectiveDate()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<Error> validateStateMaster(StateMasterSaveReq req) {List<Error> errors = new ArrayList<Error>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	try {
		
		
		if (req.getStateName() == null || StringUtils.isBlank(req.getStateName())) {
			errors.add(new Error("01", "Occupation Name", "Please Enter Occupation Name"));
		} else if (req.getStateName().length() > 50) {
			errors.add(new Error("01", "Occupation Name", "Please Enter Occupation Name within 50 Characters"));
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();
		Date a = sdf.parse(req.getEffectiveDate());
		if (req.getEffectiveDate() == null || StringUtils.isBlank(req.getEffectiveDate().toString())) {
			errors.add(new Error("03", "Effective Date", "Please Enter Effective Date"));
		} else if (a.before(yesterday)) {
			errors.add(new Error("03", "EffectiveDate", "Please Enter Future Date as EffectiveDate"));
		} else if (!req.getEffectiveDate().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			errors.add(new Error("03", "EffectiveDate",
					"Effective Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
		}
	   
		if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
			errors.add(new Error("04", "Remarks", "Please Enter Remarks"));
		}

		 else if (req.getRemarks().length() > 50) {
			errors.add(new Error("04", "Remarks", "Please Enter Remarks within 50 Characters"));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		log.info("Exception is --->" + e.getMessage());
		return errors;
	}
	return errors;
		
	}

	@Override
	public SuccessRes saveStateMaster(StateMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		StateMaster saveData = new StateMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer stateId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(req.getStateId())) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<StateMaster> query = cb.createQuery(StateMaster.class);
				
				// Find
				Root<StateMaster>    c = query.from(StateMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("StateId")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<StateMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				StateMaster data = new StateMaster();
				data =  result.getResultList().get(0);  
				stateId = data.getStateId() + 1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				
			} else {
				// Update
				stateId = Integer.valueOf(req.getStateId());
				List<StateMaster> findDatas = stateRepo.findByStateIdAndEffectiveDateOrderByAmendIdDesc(stateId ,sdf.parse(req.getEffectiveDate()) );
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping
			req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
			saveData = mapper.map(req, StateMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setStateId(stateId);
			saveData.setAmendId(amendId);
			
			stateRepo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
           return null;
		}
		return res;
	}
	
	}

	

	




