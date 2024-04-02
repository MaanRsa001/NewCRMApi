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
import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.repository.CityMasterRepository;
import com.maan.crm.repository.OccupationMasterRepository;
import com.maan.crm.req.CityMasterGetAllReq;
import com.maan.crm.req.CityMasterGetReq;
import com.maan.crm.req.CityMasterSaveReq;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.res.CityMasterRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CityMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class CityMasterServiceImpl implements CityMasterService {

	@Autowired
	private CityMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(CityMasterServiceImpl.class);

	// Class Type Drop Down

	@Override
	public List<DropDownRes> getCityDropdown(Integer stateCode) {
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
			CriteriaQuery<CityMaster> query = cb.createQuery(CityMaster.class);
			List<CityMaster> list = new ArrayList<CityMaster>();

			// Find All
			Root<CityMaster> c = query.from(CityMaster.class);

			// Select
			query.select(c);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<CityMaster> ocpm1 = amendId.from(CityMaster.class);			
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("cityId"), ocpm1.get("cityId"));
			amendId.where(a1);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("cityName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("stateCode"), stateCode);
			
			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<CityMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (CityMaster data : list) {
				// Response
				DropDownRes res = new DropDownRes();
				res.setCode(data.getCityId().toString());
				res.setCodeDesc(data.getCityName());
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

	// City Get All

	@Override
	public List<CityMasterRes> getallCity(CityMasterGetAllReq req) {
		List<CityMasterRes> resList = new ArrayList<CityMasterRes>();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CityMaster> query = cb.createQuery(CityMaster.class);
			List<CityMaster> list = new ArrayList<CityMaster>();

			// Find All
			Root<CityMaster> c = query.from(CityMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<CityMaster> ocpm1 = effectiveDate.from(CityMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("cityId"), ocpm1.get("cityId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<CityMaster> ocpm2 = amendId.from(CityMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("cityId"), ocpm2.get("cityId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("cityName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId());
			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<CityMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list = result.getResultList();

			for (CityMaster data : list) {
				CityMasterRes res = new CityMasterRes();
				res = mapper.map(data, CityMasterRes.class);
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

	// City Get By City Id

	@Override
	public CityMasterRes getCityId(CityMasterGetReq req) {
		CityMasterRes res = new CityMasterRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CityMaster> query = cb.createQuery(CityMaster.class);
			List<CityMaster> list = new ArrayList<CityMaster>();

			// Find All
			Root<CityMaster> c = query.from(CityMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<CityMaster> ocpm1 = effectiveDate.from(CityMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("cityId"), ocpm1.get("cityId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<CityMaster> ocpm2 = amendId.from(CityMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("cityId"), ocpm2.get("cityId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("cityName")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("cityId"), req.getCityId());
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId());

			query.where(n1, n2, n3, n4, n5).orderBy(orderList);

			// Get Result
			TypedQuery<CityMaster> result = em.createQuery(query);
			list = result.getResultList();
			res = mapper.map(list.get(0), CityMasterRes.class);
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
	public List<Error> validateCity(CityMasterSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			if (req.getCityName() == null || StringUtils.isBlank(req.getCityName())) {
				errors.add(new Error("01", "City Name", "Please Enter City Name"));
			} else if (req.getCityName().length() > 50) {
				errors.add(new Error("01", "City Name", "Please Enter City Name within 50 Characters"));
			}
			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("02", "Status", "Please Enter Status"));
			}

			else if (req.getStatus().length() > 1) {
				errors.add(new Error("02", "Status", "Please Enter Status within 1 Character"));
			} else if (!(req.getStatus().equals("Y") || req.getStatus().equals("N"))) {
				errors.add(new Error("02", "Status", "Please Enter Status Properly Yes-Y or No-N"));
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
			if (req.getStateCode() == null || StringUtils.isBlank(req.getStateCode())) {
				errors.add(new Error("05", "State Code", "Please Enter StateCode"));
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
	public SuccessRes saveCityMaster(CityMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		CityMaster saveData = new CityMaster();
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ModelMapper mapper = new ModelMapper();
		Integer cityId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(req.getCityId())) {
				// Save
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<CityMaster> query = cb.createQuery(CityMaster.class);

				// Find
				Root<CityMaster> c = query.from(CityMaster.class);

				// Select
				query.select(c);
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("cityId")));
				query.orderBy(orderList);

				// Get Result
				TypedQuery<CityMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				CityMaster data = new CityMaster();
				data = result.getResultList().get(0);
				cityId = data.getCityId() + 1;
				amendId = 0;
				res.setResponse("Saved Successfully ");
				res.setSucessId(cityId.toString());

			} else {
				// Update
				cityId = Integer.valueOf(req.getCityId());
				List<CityMaster> findDatas = repo.findByCityIdAndEffectiveDateOrderByAmendIdDesc(cityId,
						sdf.parse(req.getEffectiveDate()));
				if (findDatas != null & findDatas.size() > 0) {
					amendId = findDatas.get(0).getAmendId() + 1;
				} else {
					amendId = 0;
				}
				res.setResponse("Updated Successfully ");
				res.setSucessId(cityId.toString());

			}
			// Mapping
			req.setEffectiveDate(dbf.format(sdf.parse(req.getEffectiveDate())));
			saveData = mapper.map(req, CityMaster.class);
			Date entryDate = new Date();
			saveData.setEntryDate(entryDate);
			saveData.setCityId(cityId);
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
