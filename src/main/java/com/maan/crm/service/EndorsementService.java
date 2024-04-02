package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
public interface EndorsementService {

	
	// Endorsement General Details
	
	List<Error> validateEndorsementGeneral(EndorsementGeneralDetailsSaveReq req);

	EndorsementSuccessRes saveEndorsementGeneral(EndorsementGeneralDetailsSaveReq req);

	EndorsementGeneralDetailsRes getEndorsementGeneral(EndorsementGeneralDetailsGetReq req);

	
	//Endorsement Payment Details 
	
	List<Error> validateEndorsementPayment(EndorsementPaymentDetailsSaveReq req);

	EndorsementSuccessRes saveEndorsementPayment(EndorsementPaymentDetailsSaveReq req);

	EndorsementPaymentDetailsRes getEndorsementPayment(EndorsementPaymentDetailsGetReq req);

	
	// Endorsement Financial
	List<Error> validateEndorsementFinancial(EndorsementFinancialDetailsSaveReq req);

	EndorsementSuccessRes saveEndorsementFinancial(EndorsementFinancialDetailsSaveReq req);

	EndorsementFinancialDetailsRes getEndorsementFinancial(EndorsementFinancialDetailsGetReq req);

	
	
	
}
