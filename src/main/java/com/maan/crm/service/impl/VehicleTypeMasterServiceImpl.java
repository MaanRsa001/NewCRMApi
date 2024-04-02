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
import com.maan.crm.bean.VehicleTypeMaster;
import com.maan.crm.bean.VehicleTypeMaster;

import com.maan.crm.repository.CrmListItemValueRepository;
import com.maan.crm.repository.VehicleTypeMasterRepository;
import com.maan.crm.req.CrmLeadSaveReq;
import com.maan.crm.req.VehicleTypeMasterGetAllReq;
import com.maan.crm.req.VehicleTypeMasterGetReq;
import com.maan.crm.req.VehicleTypeMasterSaveReq;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.DropDownRes;

import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleTypeMasterRes;
import com.maan.crm.service.DropDownService;
import com.maan.crm.service.VehicleTypeMasterService;
import com.maan.crm.service.VehicleTypeMasterService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class VehicleTypeMasterServiceImpl implements VehicleTypeMasterService {

	@Autowired
	private VehicleTypeMasterRepository vehRepo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(VehicleTypeMasterServiceImpl.class);

	// Vehicle Type Master DropDown
	@Override
	public List<DropDownRes> getVehicalTypeMasterDropdown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {

			Date today = new Date();

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleTypeMaster> query = cb.createQuery(VehicleTypeMaster.class);
			List<VehicleTypeMaster> list = new ArrayList<VehicleTypeMaster>();

			// Find All
			Root<VehicleTypeMaster> c = query.from(VehicleTypeMaster.class);

			// Select
			query.select(c);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleTypeMaster> ocpm1 = amendId.from(VehicleTypeMaster.class);
			amendId.select(cb.max(ocpm1.get("amendId")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("vehTypeId"), ocpm1.get("vehTypeId"));
			amendId.where(a1);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("vehTypeDesc")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(c.get("effectiveDate"), today);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			query.where(n1, n2, n3).orderBy(orderList);

			// Get Result
			TypedQuery<VehicleTypeMaster> result = em.createQuery(query);
			list = result.getResultList();

			for (VehicleTypeMaster data : list) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getVehTypeId().toString());
				res.setCodeDesc(data.getVehTypeDesc());
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

	// GetAll VehicleTypeMaster
	@Override
	public List<VehicleTypeMasterRes> getAllVehicleTypeMaster(VehicleTypeMasterGetAllReq req) {
		List<VehicleTypeMasterRes> resList = new ArrayList<VehicleTypeMasterRes>();
		ModelMapper mapper = new ModelMapper();

		try {
			// Limit Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleTypeMaster> query = cb.createQuery(VehicleTypeMaster.class);
			List<VehicleTypeMaster> list = new ArrayList<VehicleTypeMaster>();

			// Find All
			Root<VehicleTypeMaster> c = query.from(VehicleTypeMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<VehicleTypeMaster> ocpm1 = effectiveDate.from(VehicleTypeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("vehTypeId"), ocpm1.get("vehTypeId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleTypeMaster> ocpm2 = amendId.from(VehicleTypeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("vehTypeId"), ocpm2.get("vehTypeId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("vehTypeDesc")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId());

			query.where(n1, n2, n3, n4).orderBy(orderList);

			// Get Result
			TypedQuery<VehicleTypeMaster> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			list = result.getResultList();

			for (VehicleTypeMaster data : list) {
				VehicleTypeMasterRes res = new VehicleTypeMasterRes();
				res = mapper.map(data, VehicleTypeMasterRes.class);
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
	public VehicleTypeMasterRes getVehicleTypeMasterById(VehicleTypeMasterGetReq req) {

		VehicleTypeMasterRes res = new VehicleTypeMasterRes();
		ModelMapper mapper = new ModelMapper();

		try {
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<VehicleTypeMaster> query = cb.createQuery(VehicleTypeMaster.class);
			List<VehicleTypeMaster> list = new ArrayList<VehicleTypeMaster>();

			// Find All
			Root<VehicleTypeMaster> c = query.from(VehicleTypeMaster.class);

			// Select
			query.select(c);

			// Effective Date Max Filter
			Subquery<Long> effectiveDate = query.subquery(Long.class);
			Root<VehicleTypeMaster> ocpm1 = effectiveDate.from(VehicleTypeMaster.class);
			effectiveDate.select(cb.max(ocpm1.get("effectiveDate")));
			javax.persistence.criteria.Predicate a1 = cb.equal(c.get("vehTypeId"), ocpm1.get("vehTypeId"));
			effectiveDate.where(a1);

			// Amend Id Max Filter
			Subquery<Long> amendId = query.subquery(Long.class);
			Root<VehicleTypeMaster> ocpm2 = amendId.from(VehicleTypeMaster.class);
			amendId.select(cb.max(ocpm2.get("amendId")));
			javax.persistence.criteria.Predicate a2 = cb.equal(c.get("vehTypeId"), ocpm2.get("vehTypeId"));
			javax.persistence.criteria.Predicate a3 = cb.equal(c.get("effectiveDate"), ocpm2.get("effectiveDate"));
			amendId.where(a2, a3);

			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(c.get("vehTypeDesc")));

			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(c.get("status"), "Y");
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("effectiveDate"), effectiveDate);
			javax.persistence.criteria.Predicate n3 = cb.equal(c.get("amendId"), amendId);
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("vehTypeId"), req.getVehTypeId());
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("insCompanyId"), req.getInsCompanyId());

			query.where(n1, n2, n3, n4, n5).orderBy(orderList);

			// Get Result
			TypedQuery<VehicleTypeMaster> result = em.createQuery(query);
			list = result.getResultList();
			res = mapper.map(list.get(0), VehicleTypeMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes saveVehicleTypeMaster(VehicleTypeMasterSaveReq req) {
		SuccessRes res = new SuccessRes();
		VehicleTypeMaster saveData = new VehicleTypeMaster();
		ModelMapper mapper = new ModelMapper();
		
		Integer vehTypeId = 0;
		Integer amendId = 0;

		try {
			if (StringUtils.isBlank(req.getVehTypeId().toString())) {
				// Save 
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<VehicleTypeMaster> query = cb.createQuery(VehicleTypeMaster.class);
				
				// Find
				Root<VehicleTypeMaster>    c = query.from(VehicleTypeMaster.class);		
				
				// Select
				query.select(c );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("vehTypeId")));
			 	query.orderBy(orderList);
				
				// Get Result
				TypedQuery<VehicleTypeMaster> result = em.createQuery(query);
				result.setFirstResult(0);
				result.setMaxResults(1);
				VehicleTypeMaster data = new VehicleTypeMaster();
				data =  result.getResultList().get(0);  
				vehTypeId = data.getVehTypeId() + 1  ; 
				amendId = 0;
				res.setResponse("Saved Successfully ");
				
			} else {
				// Update
				vehTypeId = Integer.valueOf(req.getVehTypeId());
				List<VehicleTypeMaster> findDatas = vehRepo.findByVehTypeIdAndEffectiveDateOrderByAmendIdDesc(vehTypeId ,req.getEffectiveDate());
				if (findDatas != null & findDatas.size()>0 ) {
					amendId = findDatas.get(0).getAmendId() + 1 ;
				} else {
					amendId = 0 ;
				}
				res.setResponse("Updated Successfully ");
			}
			// Mapping
			
			saveData = mapper.map(req, VehicleTypeMaster.class ); 
			Date entryDate = new Date();
			saveData.setStatus("Y");
			saveData.setEntryDate(entryDate);
			saveData.setVehTypeId(vehTypeId);
			saveData.setAmendId(amendId);
			
			vehRepo.save(saveData);
			log.info("Saved Details is ---> " + json.toJson(saveData));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
           return null;
		}
		return res;
	}

}
