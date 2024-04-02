package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ClientLeadCountReq;
import com.maan.crm.req.ClientLeadReq;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.ClientUpdateStatusReq;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.CrmLeadSaveReq;
import com.maan.crm.req.CrmRedirectLinkReq;
import com.maan.crm.req.LeadBulkEditReq;
import com.maan.crm.req.LeadDetailsGetAllReq;
import com.maan.crm.req.LeadDetailsGetReq;
import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.req.LeadGenerateReq;
import com.maan.crm.req.LeadGetallCountReq;
import com.maan.crm.req.LeadQuoteDetailsReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.LeadViewReq;
import com.maan.crm.req.OldPolicyGetAllReq;
import com.maan.crm.req.OldPolicyGetReq;
import com.maan.crm.req.OldPolicySaveReq;
import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.ClientsLeadCountRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.CrmReDirectLinkRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.LeadDetailsJsonTempRes;
import com.maan.crm.res.LeadGetAllCountRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.LeadSearchRes;
import com.maan.crm.res.LeadViewRes;
import com.maan.crm.res.OldPolicyRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsGridRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.util.error.Error;


public interface LeadService {

	// Lead 
	List<Error> validateCrmLead(LeadDetailsJsonTempReq req);

	CrmLeadSuccessRes saveCrmLead(LeadDetailsJsonTempReq req);
	
	List<CrmLeadRes> getAllLead(LeadDetailsGetAllReq req);

	LeadDetailsJsonTempRes getLead(LeadDetailsGetReq req);


	// Policy 

	List<Error> validateOldPolicy(LeadDetailsJsonTempReq req);

	SuccessRes saveOldPolicy(LeadDetailsJsonTempReq req);

	List<DropDownRes> getpolicydetails();

	List<OldPolicyRes> getallOldPolicyDetails(OldPolicyGetAllReq req);

	OldPolicyRes getPolicyNo(OldPolicyGetReq req);

	// Vehicle 
	
	List<DropDownRes> getvehicledetails();

	List<VehicleDetailsGridRes> getallVehicleDetails(VehicleDetailsGetAllReq req);

	VehicleDetailsRes getChassisNo(VehicleDetailsGetReq req);

	List<Error> validateVehicle(VehicleDetailsSaveReq req);

	SuccessRes saveVehicle(VehicleDetailsSaveReq req);

	//View lead
	LeadViewRes viewLeadDetails(LeadViewReq req);

	// View Lead for Client Ref No
	
	List<CrmLeadRes> viewClientLead(ClientLeadReq req);

	
	// Lead Bulk Edit
	
	List<Error> validateLead(LeadBulkEditReq req);

	SuccessRes bulkEditLead(LeadBulkEditReq req);

	List<LeadSearchRes> searchLeadDetails(ClientSearchReq req);

	CrmLeadSuccessRes generateLead(LeadGenerateReq req);

	ClientsLeadCountRes clientsLeadCount(ClientLeadCountReq req);

	CrmLeadSuccessRes updateLeadStatus(ClientUpdateStatusReq req);

	// Get All Lead With Count
	
	LeadGetAllCountRes getallLeadCount(LeadGetallCountReq req);

	// Search Lead With Count
	LeadSearchCountRes searchLeadCount(LeadSearchReq req);

	CrmLeadSuccessRes updateLeadQuoteDetails(LeadQuoteDetailsReq req);

	CrmReDirectLinkRes getCrmRedirectLink(CrmRedirectLinkReq req);

	//Enquiry List
	ClientLeadsGridRes getEnquiryListDetails(LeadViewReq req);




}
