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
import com.maan.crm.bean.ManuYearMaster;
import com.maan.crm.repository.ManuYearMasterRepository;
import com.maan.crm.req.ManuYearMasterGetAllReq;
import com.maan.crm.req.ManuYearMasterGetReq;
import com.maan.crm.req.ManuYearMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ManuYearMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ManuYearMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ManuYearMasterServiceImpl implements ManuYearMasterService {

	@Autowired
	private ManuYearMasterRepository Repo;
	
	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(ManuYearMasterServiceImpl.class);

	// Manufacture Year Master DropDown
	@Override
	public List<DropDownRes> getManuYearMasterDropdown(){
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			
			Date today  = new Date();
			
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ManuYearMaster> query = cb.createQuery(ManuYearMaster.class);
			List<ManuYearMaster> list = new ArrayList<ManuYearMaster>();
			
			// Find All
			Root<ManuYearMaster>    c = query.from(ManuYearMaster.class);		
			
			// Select
			query.select(c );
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ManuYearMaster> ocpm1 = amendId.from(ManuYearMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("year"),ocpm1.get("year") );
			amendId.where(a1);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(c.get("yearDes")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			query.where(n1,n2,n3).orderBy(orderList);
			
			// Get Result
			TypedQuery<ManuYearMaster> result = em.createQuery(query);			
			list =  result.getResultList();
			

			for (ManuYearMaster data : list) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getYear().toString());
				res.setCodeDesc(data.getYearDes().toString());

				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	
	// GetAll ManuYearMaster
	@Override
	public List<ManuYearMasterDropDownRes> getAllManuYearMaster(ManuYearMasterGetAllReq req) {
		List<ManuYearMasterDropDownRes> resList = new ArrayList<ManuYearMasterDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
						int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
						int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
						
						// Criteria
						CriteriaBuilder cb = em.getCriteriaBuilder();
						CriteriaQuery<ManuYearMaster> query = cb.createQuery(ManuYearMaster.class);
						List<ManuYearMaster> list = new ArrayList<ManuYearMaster>();
						
						// Find All
						Root<ManuYearMaster>    c = query.from(ManuYearMaster.class);		
						
						// Select
						query.select(c );
						
						// Effective Date Max Filter
						Subquery<Long> effectiveDate = query.subquery(Long.class);
						Root<ManuYearMaster> ocpm1 = effectiveDate.from(ManuYearMaster.class);
						effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
						javax.persistence.criteria.Predicate a1 = cb.equal(c.get("year"),ocpm1.get("year") );
						effectiveDate.where(a1);
						
						// Amend Id Max Filter
						Subquery<Long> amendId = query.subquery(Long.class);
						Root<ManuYearMaster> ocpm2 = amendId.from(ManuYearMaster.class);
						amendId.select(cb.max(ocpm2.get("amendId")));
						javax.persistence.criteria.Predicate a2 = cb.equal(c.get("year"),ocpm2.get("year") );
						javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
						amendId.where(a2,a3);
						
						// Order By
						List<Order> orderList = new ArrayList<Order>();
						orderList.add(cb.asc(c.get("yearDes")));
						
					    // Where	
						javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
						javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
						javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
						query.where(n1,n2,n3).orderBy(orderList);
						
						// Get Result
						TypedQuery<ManuYearMaster> result = em.createQuery(query);
						result.setFirstResult(limit * offset);
						result.setMaxResults(offset);
						list =  result.getResultList();  


			for (ManuYearMaster data : list) {
				ManuYearMasterDropDownRes res = new ManuYearMasterDropDownRes();
				res = mapper.map(data, ManuYearMasterDropDownRes.class);
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

	// Get ManuYearMaster
	@Override
	public ManuYearMasterDropDownRes getManuYearMasterById(ManuYearMasterGetReq req) {
		ManuYearMasterDropDownRes res = new ManuYearMasterDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ManuYearMaster> query = cb.createQuery(ManuYearMaster.class);
			List<ManuYearMaster> list = new ArrayList<ManuYearMaster>();
			
			// Find All
			Root<ManuYearMaster>    c = query.from(ManuYearMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ManuYearMaster> ocpm1 = effectiveDate.from(ManuYearMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("year"),ocpm1.get("year") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ManuYearMaster> ocpm2 = amendId.from(ManuYearMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("year"),ocpm2.get("year") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("yearDes")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("year"),req.getYear() );
			query.where(n1,n2,n3,n4).orderBy(orderList);
			
			// Get Result
			TypedQuery<ManuYearMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , ManuYearMasterDropDownRes.class);
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
	public List<Error> validateManuYearMaster(ManuYearMasterSaveReq req) {List<Error> errors = new ArrayList<Error>();
		return errors;
		
	}

	@Override
	public SuccessRes saveManuYearMaster(ManuYearMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		ManuYearMaster saveData = new ManuYearMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer year = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(Integer.toString(req.getYear()))) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<ManuYearMaster> query = cb.createQuery(ManuYearMaster.class);
				
				// Find
				Root<ManuYearMaster>    c = query.from(ManuYearMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("year")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<ManuYearMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				ManuYearMaster data = new ManuYearMaster();
				data =  result.getResultList().get(0);  
				year = data.getYear() + 1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				
			} else {
				// Update
				year = Integer.valueOf(req.getYear());
				List<ManuYearMaster> findDatas = Repo.findByYearAndEffectiveDateOrderByAmendIdDesc(year ,req.getEffectiveDate());
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping
			req.setEffectiveDate(req.getEffectiveDate());
			saveData = mapper.map(req, ManuYearMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setYear(year);
			saveData.setAmendId(amendId);
			
			Repo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
           return null;
		}
		return res;
	}

	}

	

	




