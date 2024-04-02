
package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
import com.maan.crm.bean.PolicyNomineeDetails;
import com.maan.crm.bean.PolicyNomineeDetailsId;
import com.maan.crm.bean.PolicyRiderDetails;
import com.maan.crm.bean.PolicyRiderDetailsId;
import com.maan.crm.repository.PolicyRiderDetailsRepository;
import com.maan.crm.req.PolicyRiderDetailsGetAllReq;
import com.maan.crm.req.PolicyRiderDetailsGetReq;
import com.maan.crm.req.PolicyRiderDetailsSaveReq;
import com.maan.crm.res.PolicyRiderDetailsRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PolicyRiderDetailsService;

@Service
@Transactional
public class PolicyRiderDetailsServiceImpl implements PolicyRiderDetailsService {

	@Autowired
	private PolicyRiderDetailsRepository policyRepo;

	private Logger log = LogManager.getLogger(PolicyRiderDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE
	@Transactional
	@Override
	public SuccessRes savePolicyRiderDetails(PolicyRiderDetailsSaveReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();
		try {

			Date entryDate = null;
			PolicyRiderDetailsId id = new PolicyRiderDetailsId();

			id.setRiderId(req.getRiderId());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyRiderDetails> data = policyRepo.findById(id);

			if (data.isPresent()) {
				// Update
				PolicyRiderDetails ent = mapper.map(req, PolicyRiderDetails.class);

				entryDate = ent.getEntryDate();

				ent.setRiderId(req.getRiderId());
				ent.setEntryDate(entryDate);
				ent.setStatus("Y");

				policyRepo.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert

				Integer riderId = 1000;

				List<PolicyRiderDetails> list = policyRepo.findAllByOrderByRiderIdDesc();

				if (list.size() != 0) {
					riderId = list.get(0).getRiderId() + 1;
				}
				PolicyRiderDetails ent = mapper.map(req, PolicyRiderDetails.class);

				entryDate = new Date();

				ent.setRiderId(riderId);
				ent.setEntryDate(entryDate);
				ent.setStatus("Y");

				policyRepo.save(ent);

				res.setResponse("Inserted Successfully ");
			}
		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Get By ID
	@Override
	public PolicyRiderDetailsRes getPolicyRiderDetailsById(PolicyRiderDetailsGetReq req) {
		PolicyRiderDetailsRes res = new PolicyRiderDetailsRes();

		ModelMapper mapper = new ModelMapper();
		try {

			PolicyRiderDetailsId id = new PolicyRiderDetailsId();
			id.setPolicyId(req.getPolicyId());
			id.setRiderId(req.getRiderId());

			Optional<PolicyRiderDetails> policyOpt = policyRepo.findById(id);

			if (policyOpt.isPresent()) {
				res = mapper.map(policyOpt.get(), PolicyRiderDetailsRes.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// GET ALL
	@Override
	public List<PolicyRiderDetailsRes> getAllPolicyRiderDetails(PolicyRiderDetailsGetAllReq req) {
		List<PolicyRiderDetailsRes> resList = new ArrayList<PolicyRiderDetailsRes>();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<PolicyRiderDetails> policyDetails = policyRepo.findAll(paging);

			// Map
			for (PolicyRiderDetails data : policyDetails.getContent()) {
				PolicyRiderDetailsRes res = new PolicyRiderDetailsRes();

				res = mapper.map(data, PolicyRiderDetailsRes.class);

				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

}
