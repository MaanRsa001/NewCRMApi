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
import com.maan.crm.bean.CrmListITemValue;
import com.maan.crm.bean.ManuYearMaster;
import com.maan.crm.bean. MotorColorMaster;
import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.repository. MotorColorMasterRepository;
import com.maan.crm.req.VehColorGetAllReq;
import com.maan.crm.req.VehColorGetReq;
import com.maan.crm.req.VehicleColorSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ManuYearMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehColorDropDownRes;
import com.maan.crm.service. MotorColorMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class MotorColorMasterServiceImpl implements  MotorColorMasterService {

	@Autowired
	private  MotorColorMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(MotorColorMasterServiceImpl.class);

	@Override
	public List<DropDownRes> getMotorColorMasterDropDown() {
			List<DropDownRes> resList = new ArrayList<DropDownRes>();
			try {
				List<MotorColorMaster> getList = repo.findAll();
				
				for(MotorColorMaster data : getList ) {
					DropDownRes res = new DropDownRes();
					res.setCode(data.getColorId().toString());
					res.setCodeDesc(data.getColorCode());
					resList.add(res);
				}		
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
				return null;
			}
			return resList;
		}

	
	// Get All
	@Override
	public List<VehColorDropDownRes> getAllColor(VehColorGetAllReq req) {
		List<VehColorDropDownRes> resList = new ArrayList<VehColorDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
						int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
						int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
						
						// Criteria
						CriteriaBuilder cb = em.getCriteriaBuilder();
						CriteriaQuery<MotorColorMaster> query = cb.createQuery(MotorColorMaster.class);
						List<MotorColorMaster> list = new ArrayList<MotorColorMaster>();
						
						// Find All
						Root<MotorColorMaster>    c = query.from(MotorColorMaster.class);		
						
						// Select
						query.select(c );
						
						// Effective Date Max Filter
						Subquery<Long> effectiveDate = query.subquery(Long.class);
						Root<MotorColorMaster> ocpm1 = effectiveDate.from(MotorColorMaster.class);
						effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
						javax.persistence.criteria.Predicate a1 = cb.equal(c.get("colorId"),ocpm1.get("colorId") );
						effectiveDate.where(a1);
						
						// Amend Id Max Filter
						Subquery<Long> amendId = query.subquery(Long.class);
						Root<MotorColorMaster> ocpm2 = amendId.from(MotorColorMaster.class);
						amendId.select(cb.max(ocpm2.get("amendId")));
						javax.persistence.criteria.Predicate a2 = cb.equal(c.get("colorId"),ocpm2.get("colorId") );
						javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
						amendId.where(a2,a3);
						
						// Order By
						List<Order> orderList = new ArrayList<Order>();
						orderList.add(cb.asc(c.get("colorDesc")));
						
					    // Where	
						javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
						javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
						javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
						query.where(n1,n2,n3).orderBy(orderList);
						
						// Get Result
						TypedQuery<MotorColorMaster> result = em.createQuery(query);
						result.setFirstResult(limit * offset);
						result.setMaxResults(offset);
						list =  result.getResultList();  


			for (MotorColorMaster data : list) {
				VehColorDropDownRes res = new VehColorDropDownRes();
				res = mapper.map(data, VehColorDropDownRes.class);
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
	public VehColorDropDownRes getColorId(VehColorGetReq req) {
		VehColorDropDownRes res = new VehColorDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MotorColorMaster> query = cb.createQuery(MotorColorMaster.class);
			List<MotorColorMaster> list = new ArrayList<MotorColorMaster>();
			
			// Find All
			Root<MotorColorMaster>    c = query.from(MotorColorMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<MotorColorMaster> ocpm1 = effectiveDate.from(MotorColorMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("colorId"),ocpm1.get("colorId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<MotorColorMaster> ocpm2 = amendId.from(MotorColorMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("colorId"),ocpm2.get("colorId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("colorDesc")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("colorId"),req.getColorId() );
			query.where(n1,n2,n3,n4).orderBy(orderList);
			
			// Get Result
			TypedQuery<MotorColorMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , VehColorDropDownRes.class);
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
	public List<Error> validateVehColor(VehicleColorSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessRes saveVehColor(VehicleColorSaveReq req) {
		SuccessRes res = new SuccessRes();
		MotorColorMaster  saveData = new MotorColorMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer colorId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(Integer.toString(req.getColorId()))) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<MotorColorMaster> query = cb.createQuery(MotorColorMaster.class);
				
				// Find
				Root<MotorColorMaster>    c = query.from(MotorColorMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("colorId")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<MotorColorMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				MotorColorMaster data = new MotorColorMaster();
				data =  result.getResultList().get(0);  
				colorId = data.getColorId() + 1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				
			} else {
				// Update
				colorId = Integer.valueOf(req.getColorId());
				List<MotorColorMaster> findDatas = repo.findByColorIdAndEffectiveDateOrderByAmendIdDesc(colorId ,req.getEffectiveDate());
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping
			req.setEffectiveDate(req.getEffectiveDate());
			saveData = mapper.map(req, MotorColorMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setColorId(colorId);
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

	


