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
import com.maan.crm.bean.MotorBodyTypeMaster;
import com.maan.crm.bean.MotorColorMaster;
import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.bean.MotorMakeModelMaster;
import com.maan.crm.repository.MotorMakeMasterRepository;
import com.maan.crm.repository.MotorMakeModelMasterRepository;
import com.maan.crm.req.MotorMakeModelMasterDropDownReq;
import com.maan.crm.req.MotorMakeModelReq;
import com.maan.crm.req.MotorModelGetAllReq;
import com.maan.crm.req.MotorModelGetReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorBodyDropDownRes;
import com.maan.crm.res.MotorMakeModelRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.MotorMakeMasterService;
import com.maan.crm.service.MotorMakeModelMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class MotorMakeModelMasterServiceImpl implements MotorMakeModelMasterService {

	@Autowired
	private MotorMakeModelMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(MotorMakeModelMasterServiceImpl.class);

	// DropDown
	@Override

	public List<DropDownRes> getMotorMakeMasterModelDropdown(MotorMakeModelMasterDropDownReq req) {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			Integer makeId=0;
			makeId=req.getMakeId();
			List<MotorMakeModelMaster> getList = repo.findByMakeId(makeId);

			for (MotorMakeModelMaster data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getMakeId().toString());
				res.setCodeDesc(data.getModelNameEn());
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
	public List<MotorMakeModelRes> getAllColor(MotorModelGetAllReq req) {
		List<MotorMakeModelRes> resList = new ArrayList<MotorMakeModelRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorMakeModelMaster> query = cb.createQuery(MotorMakeModelMaster.class);
			List<MotorMakeModelMaster> list = new ArrayList<MotorMakeModelMaster>();
			
			// Find All
			Root<MotorMakeModelMaster>    c = query.from(MotorMakeModelMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<MotorMakeModelMaster> ocpm1 = effectiveDate.from(MotorMakeModelMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("makeId"),ocpm1.get("makeId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorMakeModelMaster> ocpm2 = amendId.from(MotorMakeModelMaster.class);
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
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;
			
			query.where(n1,n2,n3, n4).orderBy(orderList);
			
			// Get Result
			TypedQuery<MotorMakeModelMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list =  result.getResultList();  

			for (MotorMakeModelMaster data : list) {
				MotorMakeModelRes res = new MotorMakeModelRes();
				res = mapper.map(data, MotorMakeModelRes.class);
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
	public MotorMakeModelRes getColorId(MotorModelGetReq req) {
		MotorMakeModelRes res = new MotorMakeModelRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorMakeModelMaster> query = cb.createQuery(MotorMakeModelMaster.class);
			List<MotorMakeModelMaster> list = new ArrayList<MotorMakeModelMaster>();
			
			// Find All
			Root<MotorMakeModelMaster>    c = query.from(MotorMakeModelMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<MotorMakeModelMaster> ocpm1 = effectiveDate.from(MotorMakeModelMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("modelId"),ocpm1.get("modelId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorMakeModelMaster> ocpm2 = amendId.from(MotorMakeModelMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("modelId"),ocpm2.get("modelId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("bodyNameEn")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("modelId"),req.getModelId()) ;
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;
			
			query.where(n1,n2,n3,n4, n5).orderBy(orderList);
			
			// Get Result
			TypedQuery<MotorMakeModelMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , MotorMakeModelRes.class);
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
	public List<Error> validateMotorModel(MotorMakeModelReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessRes saveMotorModel(MotorMakeModelReq req) {
		SuccessRes res = new SuccessRes();
		MotorMakeModelMaster saveData = new MotorMakeModelMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer modelId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(Integer.toString(req.getModelId()))) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<MotorMakeModelMaster> query = cb.createQuery(MotorMakeModelMaster.class);
				
				// Find
				Root<MotorMakeModelMaster>    c = query.from(MotorMakeModelMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("modelId")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<MotorMakeModelMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				MotorMakeModelMaster data = new MotorMakeModelMaster();
				data =  result.getResultList().get(0);  
				modelId = data.getBodyId()+  1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				res.setSucessId(req.getMakeId().toString());
				} else {
				// Update
				modelId = Integer.valueOf(req.getBodyId());
				List<MotorMakeModelMaster> findDatas = repo.findByModelIdAndEffectiveDateOrderByAmendIdDesc(modelId ,req.getEffectiveDate()) ;
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
				res.setSucessId(modelId.toString());
			}
			// Mapping
			req.setEffectiveDate(req.getEffectiveDate());
			saveData = mapper.map(req, MotorMakeModelMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setModelId(modelId);
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
