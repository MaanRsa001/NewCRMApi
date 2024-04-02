
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
import com.maan.crm.bean.PolicyAccountsDetails;
import com.maan.crm.bean.PolicyAccountsDetailsId;
import com.maan.crm.repository.PolicyAccountsDetailsRepository;
import com.maan.crm.req.PolicyAccountsDetailsGetAllReq;
import com.maan.crm.req.PolicyAccountsDetailsGetReq;
import com.maan.crm.req.PolicyAccountsDetailsSaveReq;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PolicyAccountsDetailsService;

@Service
@Transactional
public class PolicyAccountsDetailsServiceImpl implements PolicyAccountsDetailsService {

	@Autowired
	private PolicyAccountsDetailsRepository policyRepo;

	private Logger log = LogManager.getLogger(PolicyAccountsDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE
	@Transactional
	@Override
	public SuccessRes savePolicyAccountsDetails(PolicyAccountsDetailsSaveReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();

		try {
			PolicyAccountsDetails savePolicyAccountsDetails = new PolicyAccountsDetails();

			Date entryDate = null;

			PolicyAccountsDetailsId id = new PolicyAccountsDetailsId();
			id.setPolicyAccId(req.getPolicyAccId());
			id.setPolicyid(req.getPolicyId());

			Optional<PolicyAccountsDetails> policyOpt = policyRepo.findById(id);

			// UPDATE
			if (policyOpt.isPresent()) {

				savePolicyAccountsDetails = mapper.map(req, PolicyAccountsDetails.class);

				entryDate = savePolicyAccountsDetails.getEntryDate();

				savePolicyAccountsDetails.setPolicyAccId(req.getPolicyAccId());
				savePolicyAccountsDetails.setStatus("Y");
				savePolicyAccountsDetails.setEntryDate(entryDate);

				policyRepo.save(savePolicyAccountsDetails);
				res.setResponse("Updated Successfully ");

			} else {

				// INSERT
				Integer policyAccId = 1000;

				List<PolicyAccountsDetails> list = policyRepo.findAllByOrderByPolicyAccIdDesc();

				if (list.size() != 0) {
					policyAccId = list.get(0).getPolicyAccId() + 1;
				}

				savePolicyAccountsDetails = mapper.map(req, PolicyAccountsDetails.class);

				entryDate = new Date();

				savePolicyAccountsDetails.setStatus("Y");
				savePolicyAccountsDetails.setEntryDate(entryDate);
				savePolicyAccountsDetails.setPolicyAccId(policyAccId);

				policyRepo.save(savePolicyAccountsDetails);
				res.setResponse("Updated Successfully ");
				log.info("Saved Details is ---> " + json.toJson(savePolicyAccountsDetails));
			}

			/*
			 * savePolicyAccountsDetails.setStatus("Y");
			 * savePolicyAccountsDetails.setPolicyAccId(null);
			 * savePolicyAccountsDetails.setEntryDate(entryDate);
			 * 
			 * policyRepo.save(savePolicyAccountsDetails); log.info("Saved Details is ---> "
			 * + json.toJson(savePolicyAccountsDetails));
			 */

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// Get By ID
	@Override
	public PolicyAccountsDetailsRes getPolicyAccountsDetailsById(PolicyAccountsDetailsGetReq req) {
		PolicyAccountsDetailsRes res = new PolicyAccountsDetailsRes();

		ModelMapper mapper = new ModelMapper();
		try {

			PolicyAccountsDetailsId id = new PolicyAccountsDetailsId();
			id.setPolicyAccId(req.getPolicyAccId());
			id.setPolicyid(req.getPolicyId());

			Optional<PolicyAccountsDetails> policyOpt = policyRepo.findById(id);

			if (policyOpt.isPresent()) {
				res = mapper.map(policyOpt.get(), PolicyAccountsDetailsRes.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	@Override
	public List<PolicyAccountsDetailsRes> getAllPolicyAccountsDetails(PolicyAccountsDetailsGetAllReq req) {
		List<PolicyAccountsDetailsRes>  resList = new ArrayList<PolicyAccountsDetailsRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit().toString())?0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset().toString())?10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			List<PolicyAccountsDetails> policyaccount = policyRepo.findByPolicyAccIdAndPolicyid(paging, req.getPolicyAccId(), req.getPolicyid());
			for(PolicyAccountsDetails data : policyaccount) {
				PolicyAccountsDetailsRes res = new PolicyAccountsDetailsRes();
				res = mapper.map(data, PolicyAccountsDetailsRes.class);
				resList.add(res);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	
}
}
