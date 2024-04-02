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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.CrmListITemValue;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.VehicleDetails;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.req.InsuranceCompanyMasterGetAllReq;
import com.maan.crm.req.InsuranceCompanyMasterGetReq;
import com.maan.crm.req.InsuranceCompanyMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.InsuranceCompanyMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.service.InsuranceCompanyMasterService;

@Service
@Transactional
public class InsuranceCompanyMasterServiceImpl implements InsuranceCompanyMasterService {

	@Autowired
	private InsuranceCompanyMasterRepository repo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(InsuranceCompanyMasterServiceImpl.class);

	// Drop Down Insurance Company Master
	@Override
	public List<DropDownRes> getInsCompMasterDropDown() {
		List<DropDownRes> resList = new ArrayList<DropDownRes>();
		try {
			List<InsuranceCompanyMaster> getList = repo.findByInsIdAndStatusOrderByInsNameAsc("OMAN_COMPANY", "Y");

			for (InsuranceCompanyMaster data : getList) {
				DropDownRes res = new DropDownRes();
				res.setCode(data.getInsId());
				res.setCodeDesc(data.getInsName());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;
	}

	// GetAll Insurance Company Master
	@Override
	public List<InsuranceCompanyMasterRes> getAllInsuranceCompanyMaster(InsuranceCompanyMasterGetAllReq req) {
		List<InsuranceCompanyMasterRes> resList = new ArrayList<InsuranceCompanyMasterRes>();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<InsuranceCompanyMaster> insCompMaster = repo.findAll(paging);

			// Map
			for (InsuranceCompanyMaster data : insCompMaster.getContent()) {
				InsuranceCompanyMasterRes res = new InsuranceCompanyMasterRes();

				res = mapper.map(data, InsuranceCompanyMasterRes.class);

				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Get Insurance Company Master
	@Override
	public InsuranceCompanyMasterRes getInsuranceCompanyMasterById(InsuranceCompanyMasterGetReq req) {
		InsuranceCompanyMasterRes res = new InsuranceCompanyMasterRes();

		ModelMapper mapper = new ModelMapper();
		try {
			String insId = req.getInsId();
			InsuranceCompanyMaster data = repo.findByInsId(insId);

			res = mapper.map(data, InsuranceCompanyMasterRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// Save Insurance Company Master
	@Override
	@Transactional
	public SuccessRes saveInsuranceCompanyMaster(InsuranceCompanyMasterSaveReq req) {

		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();

		try {
			InsuranceCompanyMaster saveIns = new InsuranceCompanyMaster();

			String insId = "";
			Date entryDate = null;

			if (StringUtils.isBlank(req.getInsId())) {

				// Save
				Long totalCount = repo.count();

				insId = totalCount.toString() + 1;

				entryDate = new Date();
				res.setResponse("Saved Succesfully");

			} else {

				// Update
				insId = req.getInsName();
				InsuranceCompanyMaster findData = repo.findByInsId(insId);

				entryDate = findData.getEntryDate();
				res.setResponse("Updated Succesfully");
			}

			// Mapper
			saveIns = mapper.map(req, InsuranceCompanyMaster.class);

			saveIns.setStatus("Y");
			saveIns.setInsId(insId);
			saveIns.setEntryDate(entryDate);

			repo.save(saveIns);
			log.info("Saved Details is ---> " + json.toJson(saveIns));

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}
}
