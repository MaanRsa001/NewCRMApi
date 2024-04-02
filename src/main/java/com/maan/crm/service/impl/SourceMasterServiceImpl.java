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
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.SourceMaster;
import com.maan.crm.repository.SourceMasterRepository;
import com.maan.crm.req.SourceMasterGetAllReq;
import com.maan.crm.req.SourceMasterGetReq;
import com.maan.crm.req.SourceMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SourceMasterDropDownRes;
import com.maan.crm.res.SourceMasterSuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.SourceMasterService;
import com.maan.crm.util.error.Error;


@Service
@Transactional
public class SourceMasterServiceImpl implements SourceMasterService{
	
	@Autowired
	private SourceMasterRepository repo;
	
	@PersistenceContext
	private EntityManager em;

	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(SourceMasterServiceImpl.class);

	// Drop Down
		
	@Override
	public List<DropDownRes> getSourceDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			Date today  = new Date();
			
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SourceMaster> query = cb.createQuery(SourceMaster.class);
			List<SourceMaster> list = new ArrayList<SourceMaster>();
			
			// Find All
			Root<SourceMaster>    c = query.from(SourceMaster.class);		
			
			// Select
			query.select(c );
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<SourceMaster> ocpm1 = amendId.from(SourceMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("sourceId"),ocpm1.get("sourceId") );
			amendId.where(a1);
	
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("sourceName")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;

			query.where(n1,n2,n3).orderBy(orderList);
			
			// Get Result
			TypedQuery<SourceMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			
			for(SourceMaster data : list ) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getSourceId().toString());
				res.setCodeDesc(data.getSourceName());
				res.setCodeStatus(data.getReferenceDetailsYn());
				res.setInsuranceId(data.getInsCompanyId());;
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
	public List<SourceMasterDropDownRes> getallSourceDropdown(SourceMasterGetAllReq req) {
		List<SourceMasterDropDownRes> resList = new ArrayList<SourceMasterDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
						int limit = StringUtils.isBlank(req.getLimit())?0:Integer.valueOf(req.getLimit());
						int offset = StringUtils.isBlank(req.getOffset())?100:Integer.valueOf(req.getOffset());
						
						// Criteria
						CriteriaBuilder cb = em.getCriteriaBuilder();
						CriteriaQuery<SourceMaster> query = cb.createQuery(SourceMaster.class);
						List<SourceMaster> list = new ArrayList<SourceMaster>();
						
						// Find All
						Root<SourceMaster>    c = query.from(SourceMaster.class);		
						
						// Select
						query.select(c );
						
						// Effective Date Max Filter
						Subquery<Long> effectiveDate = query.subquery(Long.class);
						Root<SourceMaster> ocpm1 = effectiveDate.from(SourceMaster.class);
						effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
						javax.persistence.criteria.Predicate a1 = cb.equal(c.get("sourceId"),ocpm1.get("sourceId") );
						effectiveDate.where(a1);
						
						// Amend Id Max Filter
						Subquery<Long> amendId = query.subquery(Long.class);
						Root<SourceMaster> ocpm2 = amendId.from(SourceMaster.class);
						amendId.select(cb.max(ocpm2.get("amendId")));
						javax.persistence.criteria.Predicate a2 = cb.equal(c.get("sourceId"),ocpm2.get("sourceId") );
						javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
						amendId.where(a2,a3);
						
						// Order By
						List<Order> orderList = new ArrayList<Order>();
						orderList.add(cb.asc(c.get("sourceName")));
						
					    // Where	
						javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
						javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
						javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
						javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

						query.where(n1,n2,n3, n4).orderBy(orderList);
						
						// Get Result
						TypedQuery<SourceMaster> result = em.createQuery(query);
						result.setFirstResult(limit * offset);
						result.setMaxResults(offset);
						list =  result.getResultList();  

						for (SourceMaster data : list) {
							SourceMasterDropDownRes res = new SourceMasterDropDownRes();
							res = mapper.map(data, SourceMasterDropDownRes.class);
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

	
	// Get By Source Id

	@Override
	public SourceMasterDropDownRes getSourceId(SourceMasterGetReq req) {
		SourceMasterDropDownRes res = new SourceMasterDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			
			// Criteria
						CriteriaBuilder cb = em.getCriteriaBuilder();
						CriteriaQuery<SourceMaster> query = cb.createQuery(SourceMaster.class);
						List<SourceMaster> list = new ArrayList<SourceMaster>();
						
						// Find All
						Root<SourceMaster>    c = query.from(SourceMaster.class);		
						
						// Select
						query.select(c );
						
						// Effective Date Max Filter
						Subquery<Long> effectiveDate = query.subquery(Long.class);
						Root<SourceMaster> ocpm1 = effectiveDate.from(SourceMaster.class);
						effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
						javax.persistence.criteria.Predicate a1 = cb.equal(c.get("sourceId"),ocpm1.get("sourceId") );
						effectiveDate.where(a1);
						
						// Amend Id Max Filter
						Subquery<Long> amendId = query.subquery(Long.class);
						Root<SourceMaster> ocpm2 = amendId.from(SourceMaster.class);
						amendId.select(cb.max(ocpm2.get("amendId")));
						javax.persistence.criteria.Predicate a2 = cb.equal(c.get("sourceId"),ocpm2.get("sourceId") );
						javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
						amendId.where(a2,a3);
						
						// Order By
						List<Order> orderList = new ArrayList<Order>();
						orderList.add(cb.asc(c.get("sourceName")));
						
					    // Where	
						javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
						javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
						javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
						javax.persistence.criteria.Predicate n4 = cb.equal(c.get("sourceId"),req.getSourceId()) ;
						javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

						query.where(n1,n2,n3,n4, n5).orderBy(orderList);
						
						// Get Result
						TypedQuery<SourceMaster> result = em.createQuery(query);			
						list =  result.getResultList();  
						res = mapper.map(list.get(0) , SourceMasterDropDownRes.class);
						res.setEntryDate(sdf.format(list.get(0).getEntryDate()));
						res.setEffectiveDate(sdf.format(list.get(0).getEffectiveDate()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	
	/// Validation 
	@Override
	public List<Error> validateSource(SourceMasterSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if(req.getSearchYn()==null || StringUtils.isBlank(req.getSearchYn()))
			{
				errors.add(new Error("01", "Search Y N", "Please Enter Search Y N"));
			} else if (req.getSearchYn().length() > 1) {
				errors.add(new Error("01", "Search Y N", "Please Enter Search Y N within 1 Character"));
			}
			
			
			if (req.getSourceName() == null || StringUtils.isBlank(req.getSourceName())) {
				errors.add(new Error("02", "Source Name", "Please Enter Source Name"));
			} else if (req.getSourceName().length() > 50) {
				errors.add(new Error("02", "Source Name", "Please Enter Source Name within 50 Characters"));
			}
			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("03", "Status", "Please Enter Status"));
			}

			 else if (req.getStatus().length() > 1) {
				errors.add(new Error("03", "Status", "Please Enter Status within 1 Character"));
			} else if (!(req.getStatus().equals("Y") || req.getStatus().equals("N"))) {
				errors.add(new Error("03", "Status", "Please Enter Status Properly Yes-Y or No-N"));
			}
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			Date a = sdf.parse(req.getEffectiveDate());
			if (req.getEffectiveDate() == null || StringUtils.isBlank(req.getEffectiveDate().toString())) {
				errors.add(new Error("04", "Effective Date", "Please Enter Effective Date"));
			} else if (a.before(yesterday)) {
				errors.add(new Error("04", "EffectiveDate", "Please Enter Future Date as EffectiveDate"));
			} else if (!req.getEffectiveDate().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
				errors.add(new Error("04", "EffectiveDate",
						"Effective Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
			}
		   
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("05", "Remarks", "Please Enter Remarks"));
			}

			 else if (req.getRemarks().length() > 50) {
				errors.add(new Error("05", "Remarks", "Please Enter Remarks within 50 Characters"));
			}
			
	//		if (req.getAmendId() == null || StringUtils.isBlank(req.getAmendId())) {
	//			errors.add(new Error("06", "AmendId", "Please Enter Amend"));
	//		}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	
	
	/// Save
	@Override
	public SuccessRes saveSource(SourceMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		SourceMaster saveData = new SourceMaster();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");

		ModelMapper mapper = new ModelMapper();
		Integer amendId = 0;
		Integer sourceId = 0;

		try {
			if (StringUtils.isBlank(req.getSourceId())) {

			// Save 
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SourceMaster> query = cb.createQuery(SourceMaster.class);
			
			// Find
			Root<SourceMaster>    c = query.from(SourceMaster.class);		
			
			// Select
			query.select(c );
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(c.get("sourceId")));
		 	query.orderBy(orderList);
			
			// Get Result
			TypedQuery<SourceMaster> result = em.createQuery(query);
			result.setFirstResult(0);
			result.setMaxResults(1);
			SourceMaster data = new SourceMaster();
			data =  result.getResultList().get(0);  
			sourceId = data.getSourceId() + 1  ; 
			amendId = 0;
			res.setResponse("Saved Successfully ");
			
		} else {
			// Update
			sourceId = Integer.valueOf(req.getSourceId());
			List<SourceMaster> findDatas = repo.findBySourceIdAndEffectiveDateOrderByAmendIdDesc(sourceId ,sdf.parse(req.getEffectiveDate()) );
			if (findDatas != null & findDatas.size()>0 ) {
				amendId = findDatas.get(0).getAmendId() + 1 ;
			} else {
				amendId = 0 ;
			}
			res.setResponse("Updated Successfully ");
		}
		// Mapping
		req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
		saveData = mapper.map(req, SourceMaster.class ); 
		Date entryDate = new Date();
		saveData.setEntryDate(entryDate);
		saveData.setSourceId(sourceId);
		saveData.setAmendId(amendId);
		
		repo.save(saveData);
		log.info("Saved Details is ---> " + json.toJson(saveData));
	}
	catch (Exception e) {
		e.printStackTrace();
		log.info("Exception is --->" + e.getMessage());
       return null;
	}
	return res;
}



	
}


	
	
	