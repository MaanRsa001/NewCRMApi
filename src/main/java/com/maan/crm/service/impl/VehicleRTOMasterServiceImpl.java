package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.VehicleRTOMaster;
import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.repository.OccupationMasterRepository;
import com.maan.crm.repository.VehicleRTOMasterRepository;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.req.VehicleRTOGetAllReq;
import com.maan.crm.req.VehicleRTOMasterGetReq;
import com.maan.crm.req.VehicleRTOMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleRTODropDownRes;
import com.maan.crm.service.OccupationMasterService;
import com.maan.crm.service.VehicleRtoMasterService;
import com.maan.crm.util.error.Error;


@Service
@Transactional
public class VehicleRTOMasterServiceImpl implements VehicleRtoMasterService{
	
	@Autowired
	private VehicleRTOMasterRepository repo;
	
	@PersistenceContext
	private EntityManager em;

	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(VehicleRTOMasterServiceImpl.class);

	
	// Get Vehicle Drop Down
	@Override
	public List<DropDownRes> getVehicleDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			Date today  = new Date();
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(today);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 1);
			today   = cal.getTime();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleRTOMaster> query = cb.createQuery(VehicleRTOMaster.class);
			List<VehicleRTOMaster> list = new ArrayList<VehicleRTOMaster>();
			
			// Find All
			Root<VehicleRTOMaster>    c = query.from(VehicleRTOMaster.class);		
			
			// Select
			query.select(c );
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleRTOMaster> ocpm1 = amendId.from(VehicleRTOMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("rtoCode"),ocpm1.get("rtoCode") );
			amendId.where(a1);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("rtoDesc")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			query.where(n1,n2,n3).orderBy(orderList);
			
			// Get Result
			TypedQuery<VehicleRTOMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			
			for(VehicleRTOMaster data : list ) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getRtoCode());
				res.setCodeDesc(data.getRtoDesc());
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
	public List<VehicleRTODropDownRes> getallVehicleDropdown(VehicleRTOGetAllReq req) {
		List<VehicleRTODropDownRes> resList = new ArrayList<VehicleRTODropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleRTOMaster> query = cb.createQuery(VehicleRTOMaster.class);
			List<VehicleRTOMaster> list = new ArrayList<VehicleRTOMaster>();
			
			// Find All
			Root<VehicleRTOMaster>    c = query.from(VehicleRTOMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<VehicleRTOMaster> ocpm1 = effectiveDate.from(VehicleRTOMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("rtoCode"),ocpm1.get("rtoCode") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleRTOMaster> ocpm2 = amendId.from(VehicleRTOMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("rtoCode"),ocpm2.get("rtoCode") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("rtoDesc")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

			query.where(n1,n2,n3, n4).orderBy(orderList);
			
			// Get Result
			TypedQuery<VehicleRTOMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list =  result.getResultList();  

			for (VehicleRTOMaster data : list) {
				VehicleRTODropDownRes res = new VehicleRTODropDownRes();
				res = mapper.map(data, VehicleRTODropDownRes.class);
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

// Get Vehicle RTO

	@Override
	public VehicleRTODropDownRes getVehicleRto(VehicleRTOMasterGetReq req) {
		VehicleRTODropDownRes res = new VehicleRTODropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleRTOMaster> query = cb.createQuery(VehicleRTOMaster.class);
			List<VehicleRTOMaster> list = new ArrayList<VehicleRTOMaster>();
			
			// Find All
			Root<VehicleRTOMaster>    c = query.from(VehicleRTOMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<VehicleRTOMaster> ocpm1 = effectiveDate.from(VehicleRTOMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("rtoCode"),ocpm1.get("rtoCode") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleRTOMaster> ocpm2 = amendId.from(VehicleRTOMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("rtoCode"),ocpm2.get("rtoCode") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("rtoDesc")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("rtoCode"),req.getRtoCode()) ;
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

			query.where(n1,n2,n3,n4, n5).orderBy(orderList);
			
			// Get Result
			TypedQuery<VehicleRTOMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , VehicleRTODropDownRes.class);
			res.setEntryDate(sdf.format(list.get(0).getEntryDate()));
			res.setEffectiveDate(sdf.format(list.get(0).getEffectiveDate()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	// Insert Vehicle RTO

	@Override
	public List<Error> validateVehicleRTO(VehicleRTOMasterSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public SuccessRes saveVehicleRTO(VehicleRTOMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		VehicleRTOMaster saveData = new VehicleRTOMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer amendId = 0;

		try {
			if (StringUtils.isNotBlank(req.getRtoCode())) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<VehicleRTOMaster> query = cb.createQuery(VehicleRTOMaster.class);
				
				// Find
				Root<VehicleRTOMaster>    c = query.from(VehicleRTOMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("rtoCode")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<VehicleRTOMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				VehicleRTOMaster data = new VehicleRTOMaster();
				data =  result.getResultList().get(0);  
				 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				
			} else {
				// Update
				
				List<VehicleRTOMaster> findDatas = repo.findByRtoCodeAndEffectiveDateOrderByAmendIdDesc(req.getRtoCode() ,sdf.parse(req.getEffectiveDate()) );
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping
			req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
			saveData = mapper.map(req, VehicleRTOMaster.class ); 
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
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


	
	
	