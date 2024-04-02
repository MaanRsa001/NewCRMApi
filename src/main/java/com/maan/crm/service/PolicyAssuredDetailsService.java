package com.maan.crm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.maan.crm.req.PolicyAccountsDetailsGetAllReq;
import com.maan.crm.req.PolicyAssuredDetailsGetAllReq;
import com.maan.crm.req.PolicyAssuredDetailsGetReq;
import com.maan.crm.req.PolicyAssuredDetailsSaveReq;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.PolicyAssuredDetailsRes;
import com.maan.crm.res.SuccessRes;



public interface PolicyAssuredDetailsService {

	PolicyAssuredDetailsRes getPolicyAssuredDetailsById(PolicyAssuredDetailsGetReq req);

	SuccessRes savePolicyAssuredDetails(PolicyAssuredDetailsSaveReq req);

	List<PolicyAssuredDetailsRes> getAllPolicyAssuredDetails(PolicyAssuredDetailsGetAllReq req);






	


}
