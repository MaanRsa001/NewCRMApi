package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.LeadQuoteDetailsGetReq;
import com.maan.crm.req.ProspectBulkEditReq;
import com.maan.crm.req.ProspectDetailsGetAllReq;
import com.maan.crm.req.ProspectDetailsSaveReq;
import com.maan.crm.req.ProspectOldPolicyDetailsSaveReq;
import com.maan.crm.req.ProspectPaymentGetAllReq;
import com.maan.crm.req.ProspectPaymentGetReq;
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.ProspectQuotaionGetReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationInsurerGetReq;
import com.maan.crm.req.ProspectQuotationInsurerSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectQuotationSaveReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsGetReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsSaveReq;
import com.maan.crm.req.ProspectReq;
import com.maan.crm.req.ProspectSearchReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.res.LeadQuoteDetailsGetRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.ProspectBulkEditRes;
import com.maan.crm.res.ProspectDetailsRes;
import com.maan.crm.res.ProspectGetAllCountRes;
import com.maan.crm.res.ProspectOldPolicyDetailsRes;
import com.maan.crm.res.ProspectPaymentRes;
import com.maan.crm.res.ProspectPaymentSuccessRes;
import com.maan.crm.res.ProspectQuoationRes;
import com.maan.crm.res.ProspectQuotationAddOnRes;
import com.maan.crm.res.ProspectQuotationInsurerRes;
import com.maan.crm.res.ProspectQuotationInsurerSuccessRes;
import com.maan.crm.res.ProspectQuotationPolicyAccountsRes;
import com.maan.crm.res.ProspectQuotationSuccessRes;
import com.maan.crm.res.ProspectQuotationVehicleDetailsRes;
import com.maan.crm.res.ProspectRes;
import com.maan.crm.res.ProspectSearchRes;
import com.maan.crm.res.ProspectsQuotationOtherDetailsRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

@Service
public interface ProspectService {

	// Prospect Payment

	List<Error> validateProspectPayment(LeadDetailsJsonTempReq req);

	ProspectPaymentSuccessRes saveProspectPayment(LeadDetailsJsonTempReq req);

//	List<ProspectPaymentRes> getAllProspectPayment(ProspectPaymentGetAllReq req);

//	ProspectPaymentRes getProspect(ProspectPaymentGetReq req);

	// Prospect Quotation

	List<Error> validateProspectQuotation(ProspectQuotationSaveReq req);

	ProspectQuotationSuccessRes saveProspectQuotation(ProspectQuotationSaveReq req);

//	ProspectQuoationRes getQuotation(ProspectQuotaionGetReq req);

	
	
	SuccessRes saveProspectDetails(ProspectDetailsSaveReq req);

	ProspectDetailsRes getProspectDetails(ProspectDetailsSaveReq req);

	SuccessRes saveProspectOldPolicyDetails(ProspectOldPolicyDetailsSaveReq req);

//	ProspectOldPolicyDetailsRes getProspectOldPolicyDetails(ProspectDetailsSaveReq req);

	SuccessRes saveProspectQuotationPolicyAccounts(ProspectQuotationPolicyAccountsSaveReq req);

	ProspectQuotationPolicyAccountsRes getProspectQuotationPolicyAccounts(ProspectQuotationPolicyAccountsSaveReq req);

	SuccessRes saveProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req);

//	ProspectQuotationAddOnRes getProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req);
	
	// Prospect Quotation Insurer Details
	
	List<Error> validateProspectQuotationInsurer(ProspectQuotationInsurerSaveReq req);

	ProspectQuotationInsurerSuccessRes saveProspectQuotationInsurer(ProspectQuotationInsurerSaveReq req);

	ProspectQuotationInsurerRes getProspectInsurer(ProspectQuotationInsurerGetReq req);

	SuccessRes saveProspectsQuotationOtherDetails(ProspectsQuotationOtherDetailsSaveReq req);

//	ProspectsQuotationOtherDetailsRes getProspectsQuotationOtherDetails(ProspectsQuotationOtherDetailsSaveReq req);

	
	// Prospect Quotation Vehicle Details
	
	List<Error> validateProspectQuotationVehicle(ProspectQuotationVehicleDetailsSaveReq req);

	ProspectQuotationInsurerSuccessRes saveProspectQuotationVehicle(ProspectQuotationVehicleDetailsSaveReq req);

//	ProspectQuotationVehicleDetailsRes getProspectVehicle(ProspectQuotationVehicleDetailsGetReq req);

	List<ProspectPaymentRes> getAllProspect();

	
	// Combined Get 
	ProspectRes getProspect(ProspectReq req);

	// Prospect Details Get All
	
	List<ProspectDetailsRes> getAllProspectDetails(ProspectDetailsGetAllReq req);

	// Prospect Bulk Edit

	List<Error> validateProspect(ProspectBulkEditReq req);

	SuccessRes bulkEditProspect(ProspectBulkEditReq req);

	
	// Prospect Search 
	
	ProspectSearchRes searchProspectDetails(ProspectSearchReq req);

	
	// Propect Get All Count
	ProspectGetAllCountRes getAllProspectCount(ProspectDetailsGetAllReq req);

	
	// Get Lead Quote Details
	List<LeadQuoteDetailsGetRes> leadQuoteDetails(LeadQuoteDetailsGetReq req);

	
	
	


}
