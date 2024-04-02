package com.maan.crm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.maan.crm.req.PolicyAccountsDetailsGetAllReq;
import com.maan.crm.req.PolicyAccountsDetailsGetReq;
import com.maan.crm.req.PolicyAccountsDetailsSaveReq;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.SuccessRes;

public interface PolicyAccountsDetailsService {

	SuccessRes savePolicyAccountsDetails(PolicyAccountsDetailsSaveReq req);

	PolicyAccountsDetailsRes getPolicyAccountsDetailsById(PolicyAccountsDetailsGetReq req);

	List<PolicyAccountsDetailsRes> getAllPolicyAccountsDetails(PolicyAccountsDetailsGetAllReq req);

}
