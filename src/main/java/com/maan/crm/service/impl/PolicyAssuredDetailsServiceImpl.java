
package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.maan.crm.bean.PolicyAccountsDetails;
import com.maan.crm.bean.PolicyAssuredDetails;
import com.maan.crm.bean.PolicyAssuredDetailsId;
import com.maan.crm.repository.PolicyAssuredDetailsRepository;
import com.maan.crm.req.PolicyAccountsDetailsGetAllReq;
import com.maan.crm.req.PolicyAssuredDetailsGetAllReq;
import com.maan.crm.req.PolicyAssuredDetailsGetReq;
import com.maan.crm.req.PolicyAssuredDetailsSaveReq;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.PolicyAssuredDetailsRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PolicyAssuredDetailsService;

@Service
@Transactional
public class PolicyAssuredDetailsServiceImpl implements PolicyAssuredDetailsService {

	@Autowired
	private PolicyAssuredDetailsRepository policyRepo;

	private Logger log = LogManager.getLogger(PolicyAssuredDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE
	@Transactional
	@Override
	public SuccessRes savePolicyAssuredDetails(PolicyAssuredDetailsSaveReq req) {

		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();

		try {
			Date entryDate=null;

			PolicyAssuredDetails savePolicyAssuredDetails = new PolicyAssuredDetails();

			PolicyAssuredDetailsId id = new PolicyAssuredDetailsId();
			id.setAssuredId(req.getAssuredId());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyAssuredDetails> opt = policyRepo.findById(id);

			// Update
			if (opt.isPresent()) {

				savePolicyAssuredDetails = mapper.map(req, PolicyAssuredDetails.class);
				
				entryDate=savePolicyAssuredDetails.getEntryDate();
				
				savePolicyAssuredDetails.setEntryDate(entryDate);
				savePolicyAssuredDetails.setStatus("Y");
				
				policyRepo.save(savePolicyAssuredDetails);

				log.info("Updated Details is ---> " + json.toJson(savePolicyAssuredDetails));

			}
			// Insert
			else {

				savePolicyAssuredDetails = mapper.map(req, PolicyAssuredDetails.class);

				int policyId = 1000;
				List<PolicyAssuredDetails> count = policyRepo.findAllByOrderByAssuredIdDesc();
				if (count.size() != 0) {
					policyId = count.get(0).getAssuredId() + 1;
				}
			
				
				entryDate=new Date();
				
				savePolicyAssuredDetails.setAssuredId(policyId);
				savePolicyAssuredDetails.setEntryDate(entryDate);
				savePolicyAssuredDetails.setStatus("Y");
				
				res.setResponse("Saved Succesfully");
				
				policyRepo.save(savePolicyAssuredDetails);

				log.info("Saved Details is ---> " + json.toJson(savePolicyAssuredDetails));

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// Get By ID
	@Override
	public PolicyAssuredDetailsRes getPolicyAssuredDetailsById(PolicyAssuredDetailsGetReq req) {
		PolicyAssuredDetailsRes res = new PolicyAssuredDetailsRes();
		ModelMapper mapper = new ModelMapper();

		try {
			PolicyAssuredDetailsId id =new PolicyAssuredDetailsId();
			id.setAssuredId(req.getAssuredId());
			id.setPolicyId(req.getPolicyId());
			
			Optional<PolicyAssuredDetails> policyOpt =policyRepo.findById(id);
			
			if(policyOpt.isPresent()) {
				res=mapper.map(policyOpt.get(), PolicyAssuredDetailsRes.class);
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
	public List<PolicyAssuredDetailsRes> getAllPolicyAssuredDetails(PolicyAssuredDetailsGetAllReq req) {
		List<PolicyAssuredDetailsRes> resList = new ArrayList<PolicyAssuredDetailsRes>();
		
		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<PolicyAssuredDetails> policyDetails = policyRepo.findAll(paging);

			// Map
			for (PolicyAssuredDetails data : policyDetails.getContent()) {
				PolicyAssuredDetailsRes res = new PolicyAssuredDetailsRes();

				res = mapper.map(data, PolicyAssuredDetailsRes.class);
				

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
