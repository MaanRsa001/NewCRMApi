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
import com.maan.crm.bean.PolicyTypeMaster;
import com.maan.crm.repository.ClassTypeMasterRepository;
import com.maan.crm.req.ClassTypeMasterGetAllReq;
import com.maan.crm.req.ClassTypeMasterGetReq;
import com.maan.crm.req.ClassTypeMasterSaveReq;
import com.maan.crm.res.ClassTypeMasterDropDownRes;
import com.maan.crm.res.DropDownRes;

import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClassTypeMasterService;

import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ClassTypeMasterServiceImpl implements ClassTypeMasterService {

	@Autowired
	private ClassTypeMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(ClassTypeMasterServiceImpl.class);

	// ClassTypeMaster Drop Down

	@Override
	public List<DropDownRes> getClassTypeMasterDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			Date today = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(today);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 1);
			today = cal.getTime();

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClassTypeMaster> query = cb.createQuery(ClassTypeMaster.class);
			List<ClassTypeMaster> list = new ArrayList<ClassTypeMaster>();

			// Find All
			Root<ClassTypeMaster> c = query.from(ClassTypeMaster.class);

			// Select
			query.select(c);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ClassTypeMaster> ocpm1 = amendId.from(ClassTypeMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("classId"), ocpm1.get("classId"));
			amendId.where(a1);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("className")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			query.where(n1, n2, n3).orderBy(orderList);

			// Get Result
			TypedQuery<ClassTypeMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (ClassTypeMaster data : list) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getClassId().toString());
				res.setCodeDesc(data.getClassName());
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

	// ClassTypeMaster Get All

	@Override
	public List<ClassTypeMasterDropDownRes> getAllClassTypeMaster(ClassTypeMasterGetAllReq req) {
		List<ClassTypeMasterDropDownRes> resList = new ArrayList<ClassTypeMasterDropDownRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClassTypeMaster> query = cb.createQuery(ClassTypeMaster.class);
			List<ClassTypeMaster> list = new ArrayList<ClassTypeMaster>();

			// Find All
			Root<ClassTypeMaster> c = query.from(ClassTypeMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ClassTypeMaster> ocpm1 = effectiveDate.from(ClassTypeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("classId"), ocpm1.get("classId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ClassTypeMaster> ocpm2 = amendId.from(ClassTypeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("classId"), ocpm2.get("classId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("className")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),req.getInsCompanyId());

			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<ClassTypeMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list = result.getResultList();

			for (ClassTypeMaster data : list) {
				ClassTypeMasterDropDownRes res = new ClassTypeMasterDropDownRes();
				res = mapper.map(data, ClassTypeMasterDropDownRes.class);
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

	// ClassTypeMaster Get By ClassTypeMaster Id

	@Override
	public ClassTypeMasterDropDownRes getClassTypeMasterById(ClassTypeMasterGetReq req) {

		ClassTypeMasterDropDownRes res = new ClassTypeMasterDropDownRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClassTypeMaster> query = cb.createQuery(ClassTypeMaster.class);
			List<ClassTypeMaster> list = new ArrayList<ClassTypeMaster>();

			// Find All
			Root<ClassTypeMaster> c = query.from(ClassTypeMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<ClassTypeMaster> ocpm1 = effectiveDate.from(ClassTypeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("classId"), ocpm1.get("classId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<ClassTypeMaster> ocpm2 = amendId.from(ClassTypeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("classId"), ocpm2.get("classId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("className")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("classId"), req.getClassId());
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId());

			query.where(n1, n2, n3, n4, n5).orderBy(orderList);

			// Get Result
			TypedQuery<ClassTypeMaster> result = em.createQuery(query);
			list = result.getResultList();
			res = mapper.map(list.get(0), ClassTypeMasterDropDownRes.class);
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
	public List<Error> validateClassTypeMaster(ClassTypeMasterSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			if (req.getClassName() == null || StringUtils.isBlank(req.getClassName())) {
				errors.add(new Error("01", "ClassTypeMaster Name", "Please Enter ClassTypeMaster Name"));
			} else if (req.getClassName().length() > 50) {
				errors.add(new Error("01", "ClassTypeMaster Name",
						"Please Enter ClassTypeMaster Name within 50 Characters"));
			}

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			Date a = sdf.parse(req.getEffectiveDate());
			if (req.getEffectiveDate() == null || StringUtils.isBlank(req.getEffectiveDate().toString())) {
				errors.add(new Error("03", "Effective Date", "Please Enter Effective Date"));
			} else if (a.before(yesterday)) {
				errors.add(new Error("03", "EffectiveDate", "Please Enter Future Date as EffectiveDate"));
			} 
			/*else if (!req.getEffectiveDate().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
				errors.add(new Error("03", "EffectiveDate",
						"Effective Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
			}
			*/
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

	/// Save 
	@Override
	public SuccessRes saveClassTypeMaster(ClassTypeMasterSaveReq req) {

		SuccessRes res = new SuccessRes();
		ClassTypeMaster saveData = new ClassTypeMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer classId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(req.getClassId())) {
				// Save
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<ClassTypeMaster> query = cb.createQuery(ClassTypeMaster.class);

				// Find
				Root<ClassTypeMaster> c = query.from(ClassTypeMaster.class);

				// Select
				query.select(c);
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("classId")));
				query.orderBy(orderList);

				// Get Result
				TypedQuery<ClassTypeMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				ClassTypeMaster data = new ClassTypeMaster();
				data = result.getResultList().get(0);
				classId = data.getClassId() + 1;
				amendId = 0;
				res.setResponse("Saved Successfully ");
				res.setSucessId(classId.toString());

			} else {
				// Update
				classId = Integer.valueOf(req.getClassId());
				List<ClassTypeMaster> findDatas = repo.findByClassIdAndEffectiveDateOrderByAmendIdDesc(classId,
						sdf.parse(req.getEffectiveDate()));
				if (findDatas != null & findDatas.size() > 0) {
					amendId = findDatas.get(0).getAmendId() + 1;
				} else {
					amendId = 0;
				}
				res.setResponse("Updated Successfully ");
				res.setSucessId(req.getClassId());
			}
			// Mapping
			req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
			saveData = mapper.map(req, ClassTypeMaster.class);
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setClassId(classId);
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

	@Override
	public List<DropDownRes> getPolicyTypeDropdown(String classTypeId) {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			Date today = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(today);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 1);
			today = cal.getTime();

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PolicyTypeMaster> query = cb.createQuery(PolicyTypeMaster.class);
			List<PolicyTypeMaster> list = new ArrayList<PolicyTypeMaster>();

			// Find All
			Root<PolicyTypeMaster> p = query.from(PolicyTypeMaster.class);

			// Select
			query.select(p);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<PolicyTypeMaster> ocpm1 = amendId.from(PolicyTypeMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(p.get("policytypeId"), ocpm1.get("policytypeId"));
			amendId.where(a1);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(p.get("policytypeName")));

			// Where
			javax.persistence.criteria.Predicate n4 = cb.equal(p.get("classId"), classTypeId);
			javax.persistence.criteria.Predicate n1 = cb.equal(p.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(p.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(p.get("amendId"), amendId);
			query.where(n1, n2, n3,n4).orderBy(orderList);

			// Get Result
			TypedQuery<PolicyTypeMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (PolicyTypeMaster data : list) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getPolicytypeId().toString());
				res.setCodeDesc(data.getPolicytypeName());

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
