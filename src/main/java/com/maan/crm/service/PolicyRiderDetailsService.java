package com.maan.crm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.maan.crm.req.PolicyRiderDetailsGetAllReq;
import com.maan.crm.req.PolicyRiderDetailsGetReq;
import com.maan.crm.req.PolicyRiderDetailsSaveReq;
import com.maan.crm.res.PolicyRiderDetailsRes;
import com.maan.crm.res.SuccessRes;



public interface PolicyRiderDetailsService {

	SuccessRes savePolicyRiderDetails(PolicyRiderDetailsSaveReq req);

	PolicyRiderDetailsRes getPolicyRiderDetailsById(PolicyRiderDetailsGetReq req);

	List<PolicyRiderDetailsRes> getAllPolicyRiderDetails(PolicyRiderDetailsGetAllReq req);


	


}
