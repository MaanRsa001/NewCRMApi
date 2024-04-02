package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.ClaimOtherDetailsGetReq;
import com.maan.crm.req.ClaimOtherDetailsSaveReq;
import com.maan.crm.req.CommissionMotorDetailsGetReq;
import com.maan.crm.req.CommissionMotorDetailsSaveReq;
import com.maan.crm.req.CommissionNonMotorDetailsGetReq;
import com.maan.crm.req.CommissionNonMotorDetailsSaveReq;
import com.maan.crm.req.EndorsementFinancialDetailsGetReq;
import com.maan.crm.req.EndorsementFinancialDetailsSaveReq;
import com.maan.crm.req.EndorsementGeneralDetailsGetReq;
import com.maan.crm.req.EndorsementGeneralDetailsSaveReq;
import com.maan.crm.req.EndorsementPaymentDetailsGetReq;
import com.maan.crm.req.EndorsementPaymentDetailsSaveReq;
import com.maan.crm.req.PolicyAddOnGetReq;
import com.maan.crm.req.PolicyAddOnSaveReq;
import com.maan.crm.req.PolicyDetailsSaveReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetReq;
import com.maan.crm.req.PolicyAdditionalDetailsSaveReq;
import com.maan.crm.req.PolicyNomineeDetailsGetReq;
import com.maan.crm.req.PolicyNomineeDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentDetailsGetReq;
import com.maan.crm.req.PolicyPaymentDetailsSaveReq;
import com.maan.crm.res.ClaimOtherDetailsRes;
import com.maan.crm.res.ClaimSuccessRes;
import com.maan.crm.res.CommissionMotorDetailsRes;
import com.maan.crm.res.CommissionNonMotorDetailsRes;
import com.maan.crm.res.CommissionSuccessRes;
import com.maan.crm.res.EndorsementFinancialDetailsRes;
import com.maan.crm.res.EndorsementGeneralDetailsRes;
import com.maan.crm.res.EndorsementPaymentDetailsRes;
import com.maan.crm.res.EndorsementSuccessRes;
import com.maan.crm.res.PolicyAddOnRes;
import com.maan.crm.res.PolicyDetailsRes;
import com.maan.crm.res.PolicyAdditionalDetailsRes;
import com.maan.crm.res.PolicyNomineeDetailsRes;
import com.maan.crm.res.PolicyPaymentDetailsRes;
import com.maan.crm.res.PolicySuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

@Service
public interface ClaimService {

	
	// Claim Other Details
	

	List<Error> validateClaimOtherDetails(ClaimOtherDetailsSaveReq req);

	ClaimSuccessRes saveClaimOtherDetails(ClaimOtherDetailsSaveReq req);

	ClaimOtherDetailsRes getClaimOtherDetails(ClaimOtherDetailsGetReq req);

	
	
	
}
