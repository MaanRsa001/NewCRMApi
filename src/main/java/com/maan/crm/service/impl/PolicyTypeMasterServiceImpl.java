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
import com.maan.crm.bean.ClassTypeMaster;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.PolicyTypeMaster;
import com.maan.crm.repository.ClassTypeMasterRepository;
import com.maan.crm.repository.PolicyTypeMasterRepository;
import com.maan.crm.req.ClassTypeMasterGetAllReq;
import com.maan.crm.req.ClassTypeMasterGetReq;
import com.maan.crm.req.ClassTypeMasterSaveReq;
import com.maan.crm.req.PolicyMasterGetAllReq;
import com.maan.crm.req.PolicyMasterGetReq;
import com.maan.crm.req.PolicyTypeMasterSaveReq;
import com.maan.crm.res.ClassTypeMasterDropDownRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.PolicyTypeRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClassTypeMasterService;
import com.maan.crm.service.PolicyTypeMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class PolicyTypeMasterServiceImpl implements PolicyTypeMasterService {

	@Autowired
	private PolicyTypeMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(PolicyTypeMasterServiceImpl.class);


	/// Save Class Type Master
	@Override
	public SuccessRes savePolicyTypeMaster(PolicyTypeMasterSaveReq req) {

		SuccessRes res = new SuccessRes();
		PolicyTypeMaster saveData = new PolicyTypeMaster();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Integer policyTypeId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(req.getPolicytypeId())) {
				// Save
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<PolicyTypeMaster> query = cb.createQuery(PolicyTypeMaster.class);

				// Find
				Root<PolicyTypeMaster> c = query.from(PolicyTypeMaster.class);

				// Select
				query.select(c);
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("classId")));
				query.orderBy(orderList);

				// Get Result
				TypedQuery<PolicyTypeMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				PolicyTypeMaster data = new PolicyTypeMaster();
				data = result.getResultList().get(0);
				policyTypeId = data.getClassId() + 1;
				amendId = 0;
				res.setResponse("Saved Successfully ");
				res.setSucessId(policyTypeId.toString());
			} else {
				// Update
				policyTypeId = Integer.valueOf(req.getClassId());
				List<PolicyTypeMaster> findDatas = repo.findByPolicytypeIdAndEffectiveDateOrderByAmendIdDesc(policyTypeId,
						sdf.parse(req.getEffectiveDate()));
				if (findDatas != null & findDatas.size() > 0) {
					amendId = findDatas.get(0).getAmendId() + 1;
				} else {
					amendId = 0;
				}
				res.setResponse("Updated Successfully ");
				res.setSucessId(req.getPolicytypeId());
			}
			// Mapping
		
			req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
			saveData = mapper.map(req, PolicyTypeMaster.class);
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setAmendId(amendId);
			saveData.setInsCompanyId(req.getInsCompanyId());
			saveData.setPolicytypeId(policyTypeId);
			repo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}


	@Override
	public List<Error> validatePolicyTypeMaster(PolicyTypeMasterSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			if (req.getPolicytypeName() == null || StringUtils.isBlank(req.getPolicytypeName())) {
				errors.add(new Error("01", "ClassTypeMaster Name", "Please Enter PolicyTypeMaster Name"));
			} else if (req.getPolicytypeName().length() > 50) {
				errors.add(new Error("01", "PolicyTypeMaster Name",
						"Please Enter PolicyTypeMaster Name within 50 Characters"));
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
	public List<PolicyTypeRes> getallpolicy(PolicyMasterGetAllReq req) {
	List<PolicyTypeRes> resList = new ArrayList<PolicyTypeRes>();
	ModelMapper mapper = new ModelMapper();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
	try {
	// Limit Offset
		
	int limit = StringUtils.isBlank(req.getLimit())? 0 : Integer.valueOf(req.getLimit());
	int offset = StringUtils.isBlank(req.getOffset())? 100 : Integer.valueOf(req.getOffset());
	
	// Criteria
	
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<PolicyTypeMaster> query = cb.createQuery(PolicyTypeMaster.class);
	List<PolicyTypeMaster> list = new ArrayList<PolicyTypeMaster>();
	
	// Find All
	
	Root<PolicyTypeMaster> c = query.from(PolicyTypeMaster.class);
	
	// Select
	query.select(c);
	
	// Effective Date Max Filter
	
	Subquery<Long> effectiveDate = query.subquery(Long.class);
	Root<PolicyTypeMaster> ocpm1 = effectiveDate.from(PolicyTypeMaster.class);
	effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
	javax.persistence.criteria.Predicate a1 = cb.equal(c.get("policytypeId"), ocpm1.get("policytypeId"));
	effectiveDate.where(a1);
	
	// Amend Id Max Filter
	
	Subquery<Long> amendId = query.subquery(Long.class);
	Root<PolicyTypeMaster> ocpm2 = amendId.from(PolicyTypeMaster.class);
	amendId.select(cb.max(ocpm2.get("amendId")));
	javax.persistence.criteria.Predicate a2 = cb.equal(c.get("policytypeId"), ocpm2.get("policytypeId"));
	javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate"));
	amendId.where(a2, a3);
	
	// Order By
		
	List<Order> orderList = new ArrayList<Order>();
	orderList.add(cb.asc(c.get("policytypeName")));
	
	// Where
	javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
	javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
	javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
	javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

	query.where(n1,n2,n3, n4).orderBy(orderList);
	
	// Get Result
	TypedQuery<PolicyTypeMaster> result = em.createQuery(query);
	result.setFirstResult(limit * offset);
	result.setMaxResults(offset);
	list =  result.getResultList();  

	for (PolicyTypeMaster data : list) {
		PolicyTypeRes res = new PolicyTypeRes();
		res = mapper.map(data, PolicyTypeRes.class);
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

	
	
		

	@Override
	public PolicyTypeRes getpolicyid(PolicyMasterGetReq req) {
		PolicyTypeRes res = new PolicyTypeRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PolicyTypeMaster> query = cb.createQuery(PolicyTypeMaster.class);
			List<PolicyTypeMaster> list = new ArrayList<PolicyTypeMaster>();
			
			// Find All
			Root<PolicyTypeMaster>    c = query.from(PolicyTypeMaster.class);		
			
			// Select
			query.select(c );
			
			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<PolicyTypeMaster> ocpm1 = effectiveDate.from(PolicyTypeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("policytypeId"),ocpm1.get("policytypeId") );
			effectiveDate.where(a1);
			
			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<PolicyTypeMaster> ocpm2 = amendId.from(PolicyTypeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("policytypeId"),ocpm2.get("policytypeId") );
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"),ocpm2.get("effectiveDate") );
			amendId.where(a2,a3);
			
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("policytypeName")));
			
		    // Where	
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"),amendId) ;
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("policytypeId"),req.getPolicytypeId()) ;
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId()) ;

			query.where(n1,n2,n3,n4, n5).orderBy(orderList);
			
			// Get Result
			TypedQuery<PolicyTypeMaster> result = em.createQuery(query);			
			list =  result.getResultList();  
			res = mapper.map(list.get(0) , PolicyTypeRes.class);
			res.setEntryDate(sdf.format(list.get(0).getEntryDate()));
			res.setEffectiveDate(sdf.format(list.get(0).getEffectiveDate()));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}


	
}
