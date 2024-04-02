package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.PolicyAddOnGetAllReq;
import com.maan.crm.req.PolicyAddOnGetReq;
import com.maan.crm.req.PolicyAddOnSaveReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetAllReq;
import com.maan.crm.req.PolicyDetailsSaveReq;
import com.maan.crm.req.PolicyNomineeDetailsGetAllReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetReq;
import com.maan.crm.req.PolicyAdditionalDetailsSaveReq;
import com.maan.crm.req.PolicyBulkEditReq;
import com.maan.crm.req.PolicyDetailsGetAllReq;
import com.maan.crm.req.PolicyNomineeDetailsGetReq;
import com.maan.crm.req.PolicyNomineeDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentDetailsGetReq;
import com.maan.crm.req.PolicyPaymentDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentGetAllReq;
import com.maan.crm.req.PolicyReq;
import com.maan.crm.req.PolicySearchReq;
import com.maan.crm.res.PolicyAddOnGetAllRes;
import com.maan.crm.res.PolicyAddOnRes;
import com.maan.crm.res.PolicyAdditionalDetailsGetAllRes;
import com.maan.crm.res.PolicyDetailsRes;
import com.maan.crm.res.PolicyGetAllCountRes;
import com.maan.crm.res.PolicyNomineeDetailsGetAllRes;
import com.maan.crm.res.PolicyAdditionalDetailsRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.PolicyNomineeDetailsRes;
import com.maan.crm.res.PolicyPaymentDetailsRes;
import com.maan.crm.res.PolicyPaymentGetAllRes;
import com.maan.crm.res.PolicyRes;
import com.maan.crm.res.PolicySearchRes;
import com.maan.crm.res.PolicySuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

@Service
public interface PolicyService {

	
	// Policy Nominee Details
	
	List<Error> validatePolicyNominee(PolicyNomineeDetailsSaveReq req);

	PolicySuccessRes savePolicyNominee(PolicyNomineeDetailsSaveReq req);

	PolicyNomineeDetailsRes getPolicyNominee(PolicyNomineeDetailsGetReq req);

	List<PolicyNomineeDetailsGetAllRes> getallPolicyNomineeDetails(PolicyNomineeDetailsGetAllReq req);

	
	// Policy Add On
	
	List<Error> validatePolicyAddOn(PolicyAddOnSaveReq req);

	PolicySuccessRes savePolicyAddOn(PolicyAddOnSaveReq req);

	PolicyAddOnRes getPolicyAddOn(PolicyAddOnGetReq req);

	List<PolicyAddOnGetAllRes> getallPolicyAddon(PolicyAddOnGetAllReq req);

	// Policy Payment 
	
	List<Error> validatePolicyPayment(PolicyPaymentDetailsSaveReq req);

	PolicySuccessRes savePolicyPayment(PolicyPaymentDetailsSaveReq req);

	PolicyPaymentDetailsRes getPolicyPayment(PolicyPaymentDetailsGetReq req);

	List<PolicyPaymentGetAllRes> getallPolicyPayment(PolicyPaymentGetAllReq req);

	
	// Policy Details 
	
	List<Error> validatePolicyDetails(PolicyDetailsSaveReq req);

	SuccessRes savePolicyDetails(PolicyDetailsSaveReq req);

	PolicyPaymentDetailsRes savePolicyDetails(PolicyDetailsRes req);

	PolicyGetAllCountRes getAllPolicyDetails(PolicyDetailsGetAllReq req);


	// Policy Additional Details
	
	List<Error> validatePolicyAdditional(PolicyAdditionalDetailsSaveReq req);

	PolicySuccessRes savePolicyAdditional(PolicyAdditionalDetailsSaveReq req);

	PolicyAdditionalDetailsRes getPolicyAdditional(PolicyAdditionalDetailsGetReq req);

	List<PolicyAdditionalDetailsGetAllRes> getallPolicyAdditionalDetails(PolicyAdditionalDetailsGetAllReq req);

	
	// Policy All Get by Policy Id
	
	PolicyRes getPolicy(PolicyReq req);

	
	// Bulk Edit Policy
	
	List<Error> validatePolicy(PolicyBulkEditReq req);

	SuccessRes bulkEditPolicy(PolicyBulkEditReq req);

	
	//  Policy Search 
	
	PolicySearchRes searchPolicyDetails(PolicySearchReq req);

	
	// Policy Insert List
	
//	SuccessRes listPolicyDetails(List<PolicyAddOnSaveReq> req);

	


	

	
}
