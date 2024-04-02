package com.maan.crm.service;

import java.util.List;

import com.maan.crm.controller.DashBoardCardsDataCountReq;
import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientDetailsSaveReq;
import com.maan.crm.req.ClientDetailsUpdateReq;
import com.maan.crm.req.ClientLeadCountReq;
import com.maan.crm.req.ClientUpdateStatusReq;
import com.maan.crm.req.CompanyTypeMasterReq;
import com.maan.crm.req.CrmLeadSaveReq;
import com.maan.crm.req.CrmRedirectLinkReq;
import com.maan.crm.req.DocTypeSaveReq;
import com.maan.crm.req.DocumentTypeMasterReq;
import com.maan.crm.req.FollowUpDetailsSaveReq;
import com.maan.crm.req.InsuranceCompanyMasterSaveReq;
import com.maan.crm.req.LeadGenerateReq;
import com.maan.crm.req.LeadQuoteDetailsReq;
import com.maan.crm.req.MailMasterReq;
import com.maan.crm.req.NotifTemplateMasterReq;
import com.maan.crm.req.OldPolicySaveReq;
import com.maan.crm.req.PolicyAccountsDetailsSaveReq;
import com.maan.crm.req.PolicyAssuredDetailsSaveReq;
import com.maan.crm.req.PolicyRiderDetailsSaveReq;
import com.maan.crm.req.ProductDetailsGroupReq;
import com.maan.crm.req.ProspectDetailsSaveReq;
import com.maan.crm.req.ProspectOldPolicyDetailsSaveReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.req.SmsConfigMasterReq;
import com.maan.crm.req.UpdateEnquiryStatusReq;
import com.maan.crm.req.UpdateQuoteStatusReq;
import com.maan.crm.req.UsertypeMasterReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.util.error.Error;

public interface CRMValidationService {

	List<Error> validateClientDetailsReq(ClientDetailsJsonTemReq req);

	List<Error> validateCreateLead(CrmLeadSaveReq req);

	List<Error> validateVehicle(VehicleDetailsSaveReq req);

	List<Error> validatePolicy(OldPolicySaveReq req);

	List<Error> validateProspectDetails(ProspectDetailsSaveReq req);

	List<Error> validateProspectOldPolicyDetails(ProspectOldPolicyDetailsSaveReq req);

	List<Error> validateProspectQuotationPolicyAccounts(ProspectQuotationPolicyAccountsSaveReq req);

	List<Error> validateProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req);

	List<Error> validateProspectsQuotationOtherDetails(ProspectsQuotationOtherDetailsSaveReq req);

	List<Error> validatePolicyRiderDetails(PolicyRiderDetailsSaveReq req);

	List<Error> validatePolicyRiderDetails(PolicyAssuredDetailsSaveReq req);

	List<Error> validatePolicyAccountsDetails(PolicyAccountsDetailsSaveReq req);

	List<Error> validateClientUpdateReq(ClientDetailsUpdateReq req);

	List<Error> validateFollowUpDetailsReq(FollowUpDetailsSaveReq req);

	List<Error> validatebulkEditClientReq(ClientBulkEditReq req);

	List<Error> validateProductDetailsGroupReq(ProductDetailsGroupReq req);

	List<Error> validateCardsDataCount(DashBoardCardsDataCountReq req);

	List<Error> validateClientAddressDetailsReq(ClientDetailsJsonTemReq req);

	List<Error> validateGenerateLead(LeadGenerateReq req);
	
	List<Error> validateClientsLeadCount(ClientLeadCountReq req);

	List<Error> validateupdateLeadStatus(ClientUpdateStatusReq req);

	List<Error> branchMaster(BranchMasterReq req);

	List<Error> companytype(CompanyTypeMasterReq req);

	List<Error> documentTypeMaster(DocumentTypeMasterReq req);

	List<Error> insuranceCompanyMaster(InsuranceCompanyMasterSaveReq req);

	List<Error> mailMaster(MailMasterReq req);

	List<Error> smsConfigMaster(SmsConfigMasterReq req);

	List<Error> userTypeMaster(UsertypeMasterReq req);

	List<Error> notificationTemplateMaster(NotifTemplateMasterReq req);

	List<Error> validateLeadQuoteDetails(LeadQuoteDetailsReq req);

	List<Error> validateCrmRedirectLink(CrmRedirectLinkReq req);

	List<Error> validateupdateLeadStatus(UpdateEnquiryStatusReq req);

	List<Error> validateupdateQuoteStatus(UpdateQuoteStatusReq req);

	List<Error> validateDocTypeSaveReq(DocTypeSaveReq req);



}
