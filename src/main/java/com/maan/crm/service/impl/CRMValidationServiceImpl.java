package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.controller.DashBoardCardsDataCountReq;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
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
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class CRMValidationServiceImpl implements CRMValidationService {

	private Logger log = LogManager.getLogger(ClientDetailsServiceImpl.class);

	@Autowired
	private ClientAddressDetailsRepository clientAddrRepo;

	@Autowired
	private ClientDetailsRepository clientRepo;

	@Override
	public List<Error> validateClientDetailsReq(ClientDetailsJsonTemReq req1) {
		
		ClientDetailsSaveReq req = req1.getClientDetails();
		
		List<Error> errors = new ArrayList<Error>();
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);

		try {
			if(StringUtils.isBlank(req.getCrno()) ||!StringUtils.isNumeric(req.getCrno())){
				errors.add(new Error("14", "Pan", "Please Enter A Valid PAN Number"));
			}else if(StringUtils.isNotBlank(req.getCrno())) {
				List<ClientDetails> byCrno = clientRepo.findByCrno(req.getCrno());
				if(byCrno!=null && byCrno.size()>0) {
					errors.add(new Error("14", "Pan", "The PAN Number Is Already Used"));
				}
				
			}
			if (req.getClientTypeId() == null || StringUtils.isBlank(req.getClientTypeId())) {
				errors.add(new Error("01", "Client Type Id", "Please Enter Client Type Id"));
			}
			if (req.getClientName() == null || StringUtils.isBlank(req.getClientName())) {
				errors.add(new Error("03", "Client Name", "Please Enter Client Name"));
			}
			if (StringUtils.isBlank(req.getMobileNumber())) {
				errors.add(new Error("05", "Mobile Number", "Please Enter Mobile Number"));
			} else if (!req.getMobileNumber().matches("[0-9]+")) {
				errors.add(new Error("07", "Mobile Number", "Mobile Number format should be Only Numbers"));
			} else if (req.getMobileNumber().length()>10  ) {
				errors.add(new Error("07", "Mobile Number", "Mobile Number should be 10 digit  Only Allowd"));
			} else {
				List<ClientDetails> byCrno = clientRepo.findByMobileNumber(req.getMobileNumber());
				if(byCrno!=null && byCrno.size()>0) {
					errors.add(new Error("07", "Mobile Number", "Mobile Number Is Already Used"));
				}
			}
			
			if (req.getEmailId() == null || StringUtils.isBlank(req.getEmailId()))
				errors.add(new Error("09", "Email", "please Enter Email Id"));
			else if (StringUtils.isNotBlank(req.getEmailId())) {
				Matcher matcher3 = pattern.matcher(req.getEmailId());
				if (!matcher3.matches())
					errors.add(new Error("09", "Email Id", "please Enter Valid Email Id"));	
			}
			
			if( StringUtils.isBlank(req.getWillProvideReference()) ) {
				errors.add(new Error("09", "Refernce Yn", "please Enter Refernce Yn"));
				
			} else if (req.getWillProvideReference().equalsIgnoreCase("Y") ) {
				
				if(StringUtils.isBlank(req.getReferencerDetailsYn()) ){
					errors.add(new Error("09", "Refernce Detrails Yn", "please Enter Refernce Detrails Yn"));	
				}	else if (req.getReferencerDetailsYn().equalsIgnoreCase("Y")) {
					if (req.getReferenceName() == null || StringUtils.isBlank(req.getReferenceName())) {
						errors.add(new Error("11", "Reference Name", "Please Enter Reference Name"));
					}
					if (req.getReferencerMobile() == null || StringUtils.isBlank(req.getReferencerMobile())) {
						errors.add(new Error("12", "Referencer Mobile", "Please Enter Referencer Mobile"));
					} else if (!req.getReferencerMobile().matches("[0-9]+")) {
						errors.add(new Error("07", "Referencer Mobile", "Referencer Mobile Number format should be Only Numbers"));
					} else if (req.getReferencerMobile().length()>10  ) {
						errors.add(new Error("07", "Referencer Mobile", "Referencer Mobile Number should be 10 digit  Only Allowd"));
					}
					if (req.getReferencerEmail() == null || StringUtils.isBlank(req.getReferencerEmail())) {
						errors.add(new Error("13", "Referencer Email", "Please Enter Referencer Email"));
					} else if (StringUtils.isNotBlank(req.getReferencerEmail())) {
						Matcher matcher1 = pattern.matcher(req.getReferencerEmail());
						if (!matcher1.matches()) {
							errors.add(new Error("13", "Referencer Email", "please Enter Valid Referencer Email"));
						}
					}
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(new Error("13", "Common Error", e.getMessage()));

		}
		return errors;
	}

	@Override
	public List<Error> validateCreateLead(CrmLeadSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validateVehicle(VehicleDetailsSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validatePolicy(OldPolicySaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validateProspectDetails(ProspectDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

		/*	if (req.getAssigntoGroup() == null || StringUtils.isBlank(req.getAssigntoGroup())) {
				errors.add(new Error("01", "Assign to Group", "Please Enter Assign to Group"));
			}
		*/	
			  if (Integer.valueOf(req.getAssignToGroupId()) == null ||
			  StringUtils.isBlank(Integer.toString(req.getAssignToGroupId()))) {
			  errors.add(new Error("02", "Assign to Group Id",
			  "Please Enter Assign to Group Id")); }
		/*	  if (req.getAssigntoUser() == null || StringUtils.isBlank(req.getAssigntoUser())) {
				errors.add(new Error("03", "Assign to User", "Please Enter Assign to User"));
			}
		*/	if (Integer.valueOf(req.getAssigntoUserId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getAssigntoUserId()))) {
				errors.add(new Error("04", "Assign to User Id", "Please Enter Assign to User Id"));
			}
		/*	if (req.getBusinessType() == null || StringUtils.isBlank(req.getBusinessType())) {
				errors.add(new Error("05", "Business Type", "Please Enter Business Type"));
			}
		*/	if (Integer.valueOf(req.getBusinessTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getBusinessTypeId()))) {
				errors.add(new Error("06", "Business Type Id", "Please Enter Business Type Id"));
			}
		/*	if (req.getClasss() == null || StringUtils.isBlank(req.getClasss())) {
				errors.add(new Error("07", "Classs", "Please Enter Classs"));
			}
		*/	if (Integer.valueOf(req.getClassId()) == null || StringUtils.isBlank(Integer.toString(req.getClassId()))) {
				errors.add(new Error("08", "Class Id", "Please Enter Class Id"));
			}
		/*	if (req.getClassification() == null || StringUtils.isBlank(req.getClassification())) {
				errors.add(new Error("09", "Classification", "Please Enter Classification"));
			}
		*/	if (Integer.valueOf(req.getClassificationId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getClassificationId()))) {
				errors.add(new Error("10", "Classification Id", "Please Enter Classification Id"));
			}
			if (req.getClientRefNo() == null || StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("11", "Client Ref No", "Please Enter Client Ref No"));
			}
			
			if (StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("12", "Client Ref Num", "Please Enter Client Ref Num"));
			}
			if (StringUtils.isBlank(req.getLeadId())) {
				errors.add(new Error("13", "Lead Id", "Please Enter Lead Id"));
			}
//		    if (req.getCoverNoteNumber() == null || StringUtils.isBlank(req.getCoverNoteNumber())) {
//				errors.add(new Error("13", "Cover Note Number", "Please Enter Cover Note Number"));
//			}
//			if (req.getGenerationDate() == null || StringUtils.isBlank(req.getGenerationDate().toString())) {
//				errors.add(new Error("14", "Generation Date", "Please Enter Generation Date"));
//			}
//			else if (!req.getGenerationDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
//				errors.add(new Error("14", "Generation Date",
//						"Generation Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
//			}
			Date today = new Date();
//			if (req.getDueDate() == null || StringUtils.isBlank(req.getDueDate().toString())) {
//				errors.add(new Error("15", "Due Date", "Please Enter Due Date"));
//			}
//			 else if (!req.getDueDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
//					errors.add(new Error("15", "Due Date",
//							"Due Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
//				}		
//				else if (req.getDueDate().before(today)) {
//					errors.add(new Error("15", "Due Date", "Please Enter Due Date as Future Date" ));
//				}
		/*	if (req.getLocation() == null || StringUtils.isBlank(req.getLocation())) {
				errors.add(new Error("16", "Location", "Please Enter Location"));
			}
			if (req.getPolicyType() == null || StringUtils.isBlank(req.getPolicyType())) {
				errors.add(new Error("17", "Policy Type", "Please Enter Policy Type"));
			}
		*/	if (Integer.valueOf(req.getPolicyTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyTypeId()))) {
				errors.add(new Error("18", "Policy Type Id", "Please Enter Policy Type Id"));
			}
			if (req.getPos() == null || StringUtils.isBlank(req.getPos())) {
				errors.add(new Error("19", "Pos", "Please Enter Pos"));
			}
		/*	if (Integer.valueOf(req.getPosId()) == null || StringUtils.isBlank(Integer.toString(req.getPosId()))) {
				errors.add(new Error("20", "Pos Id", "Please Enter Pos Id"));
			}
			if (req.getProspectOwner() == null || StringUtils.isBlank(req.getProspectOwner())) {
				errors.add(new Error("21", "Prospect Owner", "Please Enter Prospect Owner"));
			}
			if (Integer.valueOf(req.getProspectOwnerId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectOwnerId()))) {
				errors.add(new Error("22", "Prospect Owner Id", "Please Enter Prospect Owner Id"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("23", "Remarks", "Please Enter Remarks"));
			}
		*/	else if (req.getRemarks().length()>100) {
			errors.add(new Error("23", "Remarks", "Please Enter Remarks with in 100 Characters"));
		}
		/*	if (req.getSource() == null || StringUtils.isBlank(req.getSource())) {
				errors.add(new Error("24", "Source", "Please Enter Source"));
			}
		*/	if (Integer.valueOf(req.getSourceId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getSourceId()))) {
				errors.add(new Error("25", "Source Id", "Please Enter Source Id"));
			}
			/*
			 * if (Integer.valueOf(req.getVehicleId()) == null ||
			 * StringUtils.isBlank(Integer.toString(req.getVehicleId()))) { errors.add(new
			 * Error("26", "Vehicle Id", "Please Enter Vehicle Id")); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validateProspectOldPolicyDetails(ProspectOldPolicyDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("01", "Prospect Id", "Please Enter Assign to Prospect Id"));
			}

			if (req.getOldPolicyNo() == null || StringUtils.isBlank(req.getOldPolicyNo())) {
				errors.add(new Error("02", "Old Policy No", "Please Enter Old Policy No"));
			}
			if (req.getAnyClaimLastYear() == null || StringUtils.isBlank(req.getAnyClaimLastYear())) {
				errors.add(new Error("03", "Any Claim Last Year", "Please Enter Any Claim Last Year"));
			}
			if (Integer.valueOf(req.getAnyClaimLastYearId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getAnyClaimLastYearId()))) {
				errors.add(new Error("04", "Any Claim Last Year Id", "Please Enter Any Claim Last Year Id"));
			}
			if (Double.valueOf(req.getOldAddOnPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOldAddOnPremium()))) {
				errors.add(new Error("05", "Old Add On Premium", "Please Enter Old Add On Premium"));
			}
			if (Double.valueOf(req.getOldDiscountPercentage()) == null
					|| StringUtils.isBlank(Double.toString(req.getOldDiscountPercentage()))) {
				errors.add(new Error("06", "Old Discount Percentage", "Please Enter Old Discount Percentage"));
			}
			/*
			 * if (req.getOldElectronicAccessoryValue() == null||
			 * StringUtils.isBlank(req.getOldElectronicAccessoryValue())) { errors.add(new
			 * Error("07", "Old Electronic Accessory Value"
			 * ,"Please Enter Old Electronic Accessory Value")); } if
			 * (req.getOldExpiryDate() == null ||
			 * StringUtils.isBlank(req.getOldExpiryDate().toString())) { errors.add(new
			 * Error("08", "Old Expiry Date", "Please Enter Old Expiry Date")); } if
			 * (Double.valueOf(req.getOldGST()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldGST()))) { errors.add(new
			 * Error("09", "Old GST", "Please Enter Old GST")); } if (req.getOldIDV() ==
			 * null || StringUtils.isBlank(req.getOldIDV())) { errors.add(new Error("10",
			 * "Old IDV", "Please Enter Old IDV")); } if (req.getOldInsurancePlan() == null
			 * || StringUtils.isBlank(req.getOldInsurancePlan())) { errors.add(new
			 * Error("11", "Old Insurance Plan", "Please Enter Old Insurance Plan")); } if
			 * (req.getOldInsurer() == null || StringUtils.isBlank(req.getOldInsurer())) {
			 * errors.add(new Error("12", "Old Insurer", "Please Enter Old Insurer")); } if
			 * (Integer.valueOf(req.getOldInsurerId()) == null ||
			 * StringUtils.isBlank(Integer.toString(req.getOldInsurerId()))) {
			 * errors.add(new Error("13", "Old Insurer Id", "Please Enter Old Insurer Id"));
			 * } if (Double.valueOf(req.getOldNoClaimBonus()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldNoClaimBonus()))) {
			 * errors.add(new Error("14", "Old No Claim Bonus",
			 * "Please Enter Old No Claim Bonus")); } if
			 * (req.getOldNonElectronicAccessoryValue() == null ||
			 * StringUtils.isBlank(req.getOldNonElectronicAccessoryValue())) {
			 * errors.add(new Error("15", "Old Non Electronic Accessory Value",
			 * "Please Enter Old Non Electronic Accessory Value")); } if
			 * (Double.valueOf(req.getOldODAddOnCommissionBasePremium()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldODAddOnCommissionBasePremium())
			 * )) { errors.add(new Error("16", "Old OD AddOn Commission Base Premium",
			 * "Please Enter Old OD Add On Commission Base Premium")); } if
			 * (Double.valueOf(req.getOldOwnDamagePremium()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldOwnDamagePremium()))) {
			 * errors.add(new Error("17", "Old Own Damage Premium",
			 * "Please Enter Old Own Damage Premium")); } if
			 * (Double.valueOf(req.getOldPolicyAdditionalInformation()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldPolicyAdditionalInformation()))
			 * ) { errors.add(new Error("18", "Old Policy Additional Information",
			 * "Please Enter Old Policy Additional Information")); } if
			 * (req.getOldPolicyNo() == null || StringUtils.isBlank(req.getOldPolicyNo())) {
			 * errors.add(new Error("19", "Old Policy No", "Please Enter Old Policy No")); }
			 * if (req.getOldPolicyTerm() == null ||
			 * StringUtils.isBlank(req.getOldPolicyTerm())) { errors.add(new Error("20",
			 * "Old Policy Term", "Please Enter Old Policy Term")); } if (req.getOldPOS() ==
			 * null || StringUtils.isBlank(req.getOldPOS())) { errors.add(new Error("21",
			 * "Old POS", "Please Enter Old POS")); } if (Integer.valueOf(req.getOldPOSId())
			 * == null || StringUtils.isBlank(Integer.toString(req.getOldPOSId()))) {
			 * errors.add(new Error("22", "Old POS Id", "Please Enter Old POS Id")); } if
			 * (req.getOldSource() == null || StringUtils.isBlank(req.getOldSource())) {
			 * errors.add(new Error("23", "Old Source", "Please Enter Old Source")); } if
			 * (Integer.valueOf(req.getOldSourceId()) == null ||
			 * StringUtils.isBlank(Integer.toString(req.getOldSourceId()))) { errors.add(new
			 * Error("24", "Old Source Id", "Please Enter Old Source Id")); } if
			 * (req.getOldStartDate().toString() == null ||
			 * StringUtils.isBlank(req.getOldStartDate().toString())) { errors.add(new
			 * Error("25", "Old Start Date", "Please Enter Old Start Date")); }
			 * 
			 * if (Double.valueOf(req.getOldSumInsured()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldSumInsured()))) {
			 * errors.add(new Error("26", "Old Sum Insured",
			 * "Please Enter Old Sum Insured")); } if
			 * (Double.valueOf(req.getOldThirdPartyLiabilityPremium()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldThirdPartyLiabilityPremium())))
			 * { errors.add(new Error("27", "Old Third Party Liability Premium",
			 * "Please Enter Old Third Party Liability Premium")); } if
			 * (Double.valueOf(req.getOldTotalPremium()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldTotalPremium()))) {
			 * errors.add(new Error("28", "Old Total Premium",
			 * "Please Enter Old Total Premium")); } if
			 * (Double.valueOf(req.getOldTotalPremiumwithGST()) == null ||
			 * StringUtils.isBlank(Double.toString(req.getOldTotalPremiumwithGST()))) {
			 * errors.add(new Error("29", "Old Total Premium with GST",
			 * "Please Enter Old Total Premium with GST")); } if
			 * (Integer.valueOf(req.getProspectId()) == null ||
			 * StringUtils.isBlank(Integer.toString(req.getProspectId()))) { errors.add(new
			 * Error("30", "Prospect Id", "Please Enter Prospect Id")); } if
			 * (req.getVehicleTransferCase() == null ||
			 * StringUtils.isBlank(req.getVehicleTransferCase())) { errors.add(new
			 * Error("31", "Vehicle Transfer Case", "Please Enter Vehicle Transfer Case"));
			 * } if (Integer.valueOf(req.getVehicleTransferCaseId()) == null ||
			 * StringUtils.isBlank(Integer.toString(req.getVehicleTransferCaseId()))) {
			 * errors.add(new Error("32", "Vehicle Transfer Case Id",
			 * "Please Enter Vehicle Transfer Case Id")); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validateProspectQuotationPolicyAccounts(ProspectQuotationPolicyAccountsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Double.valueOf(req.getBasicTP()) == null || StringUtils.isBlank(Double.toString(req.getBasicTP()))) {
				errors.add(new Error("01", "Basic TP", "Please Enter Basic TP"));
			}
			if (Double.valueOf(req.getBifuelkit()) == null
					|| StringUtils.isBlank(Double.toString(req.getBifuelkit()))) {
				errors.add(new Error("02", "Bifuel kit", "Please Enter Bifuel kit"));
			}
			if (req.getBifuelkitYN() == null || StringUtils.isBlank(req.getBifuelkitYN())) {
				errors.add(new Error("03", "Bifuel kit YN", "Please Enter Bifuel kit YN"));
			}
			if (Double.valueOf(req.getCommissionBasePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
				errors.add(new Error("04", "Commission Base Premium", "Please Enter Commission Base Premium"));
			}
			if (Double.valueOf(req.getDiscountPercentage()) == null
					|| StringUtils.isBlank(Double.toString(req.getDiscountPercentage()))) {
				errors.add(new Error("05", "Discount Percentage", "Please Enter Discount Percentage"));
			}
			if (Double.valueOf(req.getGeographicalArea()) == null
					|| StringUtils.isBlank(Double.toString(req.getGeographicalArea()))) {
				errors.add(new Error("06", "Geographical Area", "Please Enter Geographical Area"));
			}
			if (req.getGeographicalAreaYN() == null || StringUtils.isBlank(req.getGeographicalAreaYN())) {
				errors.add(new Error("07", "Geographical Area YN", "Please Enter Geographical Area YN"));
			}
			if (Double.valueOf(req.getGST()) == null || StringUtils.isBlank(Double.toString(req.getGST()))) {
				errors.add(new Error("08", "GST", "Please Enter GST"));
			}
			if (Double.valueOf(req.getLLtoPaidDrvEmp()) == null
					|| StringUtils.isBlank(Double.toString(req.getLLtoPaidDrvEmp()))) {
				errors.add(new Error("09", "LL to Paid Drv Emp", "Please Enter LL to Paid Drv Emp"));
			}
			if (req.getLLtoPaidDrvEmpYN() == null || StringUtils.isBlank(req.getLLtoPaidDrvEmpYN())) {
				errors.add(new Error("10", "LL to Paid Drv Emp YN", "Please Enter LL to Paid Drv Emp YN"));
			}
			if (Double.valueOf(req.getNoClaimBonus()) == null
					|| StringUtils.isBlank(Double.toString(req.getNoClaimBonus()))) {
				errors.add(new Error("11", "No Claim Bonus", "Please Enter No Claim Bonus"));
			}
			if (Integer.valueOf(req.getNoofDrvEmp()) == null
					|| StringUtils.isBlank(Integer.toString(req.getNoofDrvEmp()))) {
				errors.add(new Error("12", "No of Drv Emp", "Please Enter No of Drv Emp"));
			}
			if (Double.valueOf(req.getODAddOnCommissionBasePremiumAB()) == null
					|| StringUtils.isBlank(Double.toString(req.getODAddOnCommissionBasePremiumAB()))) {
				errors.add(new Error("13", "OD Add On Commission Base Premium AB",
						"Please Enter OD Add On Commission Base Premium AB"));
			}
			if (Double.valueOf(req.getOtherPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOtherPremium()))) {
				errors.add(new Error("14", "Other Premium", "Please Enter Other Premium"));
			}
			if (Double.valueOf(req.getOwnDamagePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOwnDamagePremium()))) {
				errors.add(new Error("15", "Own Damage Premium", "Please Enter No Own Damage Premium"));
			}
			if (Double.valueOf(req.getCommissionBasePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
				errors.add(new Error("16", "Commission Base Premium", "Please Enter Commission Base Premium"));
			}
			if (Double.valueOf(req.getDiscountPercentage()) == null
					|| StringUtils.isBlank(Double.toString(req.getDiscountPercentage()))) {
				errors.add(new Error("17", "Discount Percentage", "Please Enter Discount Percentage"));
			}
			if (Double.valueOf(req.getOwnerDriverPA()) == null
					|| StringUtils.isBlank(Double.toString(req.getOwnerDriverPA()))) {
				errors.add(new Error("18", "Owner Driver PA", "Please Enter Owner Driver PA"));
			}
			if (req.getOwnerDriverPAYN() == null || StringUtils.isBlank(req.getOwnerDriverPAYN())) {
				errors.add(new Error("19", "Owner Driver PAYN", "Please Enter Owner Driver PAYN"));
			}
			if (Double.valueOf(req.getPAPaidDrv()) == null
					|| StringUtils.isBlank(Double.toString(req.getPAPaidDrv()))) {
				errors.add(new Error("20", "PA Paid Drv", "Please Enter PA Paid Drv"));
			}
			if (req.getPAPaidDrvYN() == null || StringUtils.isBlank(req.getPAPaidDrvYN())) {
				errors.add(new Error("21", "PA Paid Drv YN", "Please Enter PA Paid Drv YN"));
			}
			if (Double.valueOf(req.getPAPaidDrvLimitPerPerson()) == null
					|| StringUtils.isBlank(Double.toString(req.getPAPaidDrvLimitPerPerson()))) {
				errors.add(
						new Error("22", "PA Paid Drv Limit Per Person", "Please Enter PA Paid Drv Limit Per Person"));
			}
			if (Integer.valueOf(req.getPAPaidDrvNoofSeats()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPAPaidDrvNoofSeats()))) {
				errors.add(new Error("23", "PA Paid Drv No of Seats", "Please Enter PA Paid Drv No of Seats"));
			}
			if (Integer.valueOf(req.getPolicyAccId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyAccId()))) {
				errors.add(new Error("24", "Policy Acc Id", "Please Enter Policy Acc Id"));
			}
			if (Double.valueOf(req.getPremiumwithGST()) == null
					|| StringUtils.isBlank(Double.toString(req.getPremiumwithGST()))) {
				errors.add(new Error("25", "Premium with GST", "Please Enter Premium with GST"));
			}
			if (Double.valueOf(req.getSumInsured()) == null
					|| StringUtils.isBlank(Double.toString(req.getSumInsured()))) {
				errors.add(new Error("26", "Sum Insured", "Please Enter Sum Insured"));
			}
			if (Double.valueOf(req.getTotalAddOnCoverPremiumB()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalAddOnCoverPremiumB()))) {
				errors.add(new Error("27", "Total Add On Cover PremiumB", "Please Enter Total Add On Cover PremiumB"));
			}
			if (Double.valueOf(req.getTotalLiabilityPremiumC()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalLiabilityPremiumC()))) {
				errors.add(new Error("28", "Total Liability PremiumC", "Please Enter Total Liability PremiumC"));
			}
			if (Double.valueOf(req.getTotalOwnDamagePremiumA()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalOwnDamagePremiumA()))) {
				errors.add(new Error("29", "Total Own Damage PremiumA", "Please Enter Total Own Damage PremiumA"));
			}
			if (Double.valueOf(req.getTotalPremiumABC()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalPremiumABC()))) {
				errors.add(new Error("30", "Total Premium ABC", "Please Enter Total Premium ABC"));
			}
			if (Double.valueOf(req.getUnnamedPassengerLimitPerPerson()) == null
					|| StringUtils.isBlank(Double.toString(req.getUnnamedPassengerLimitPerPerson()))) {
				errors.add(new Error("31", "Unnamed Passenger Limit Per Person",
						"Please Enter Unnamed Passenger Limit Per Person"));
			}
			if (Double.valueOf(req.getUnnamedPassengerPA()) == null
					|| StringUtils.isBlank(Double.toString(req.getUnnamedPassengerPA()))) {
				errors.add(new Error("32", "Unnamed Passenger PA", "Please Enter Unnamed Passenger PA"));
			}
			if (Double.valueOf(req.getUnnamedPassengerPAYN()) == null
					|| StringUtils.isBlank(Double.toString(req.getUnnamedPassengerPAYN()))) {
				errors.add(new Error("33", "Unnamed Passenger PAYN", "Please Enter Unnamed Passenger PAYN"));
			}
			if (Integer.valueOf(req.getNoofDrvEmp()) == null
					|| StringUtils.isBlank(Integer.toString(req.getNoofDrvEmp()))) {
				errors.add(new Error("34", "No of Drv Emp", "Please Enter No of Drv Emp"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validateProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getAddOnid()) == null || StringUtils.isBlank(Integer.toString(req.getAddOnid()))) {
				errors.add(new Error("01", "Add On id", "Please Enter Add On id"));
			}
			if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("02", "Prospect Id", "Please Enter Prospect Id"));
			}
			if (req.getAddOnsNotOpted() == null || StringUtils.isBlank(req.getAddOnsNotOpted())) {
				errors.add(new Error("03", "Add Ons Not Opted", "Please Enter Add Ons Not Opted"));
			}
			if (req.getAddOnsOpted() == null || StringUtils.isBlank(req.getAddOnsOpted())) {
				errors.add(new Error("04", "Add Ons Opted", "Please Enter Add Ons Opted"));
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validateProspectsQuotationOtherDetails(ProspectsQuotationOtherDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("01", "Prospect Id", "Please Enter Prospect Id"));
			}
			if (req.getIssuedByUser() == null || StringUtils.isBlank(req.getIssuedByUser())) {
				errors.add(new Error("02", "Issued By User", "Please Enter Issued By User"));
			}
			if (req.getPolicyAdditionalInformation() == null
					|| StringUtils.isBlank(req.getPolicyAdditionalInformation())) {
				errors.add(
						new Error("03", "Policy Additional Information", "Please Enter Policy Additional Information"));
			}
			if (req.getRecommendedFlagYN() == null || StringUtils.isBlank(req.getRecommendedFlagYN())) {
				errors.add(new Error("04", "Recommended Flag YN", "Please Enter Recommended Flag YN"));
			}
			if (req.getRenewalNoticeFlagYN() == null || StringUtils.isBlank(req.getRenewalNoticeFlagYN())) {
				errors.add(new Error("05", "RenewalNotice Flag YN", "Please Enter RenewalNotice Flag YN"));
			}
			if (req.getIssueDate().toString() == null || StringUtils.isBlank(req.getIssueDate().toString())) {
				errors.add(new Error("06", "Issue Date", "Please Enter Issue Date"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validatePolicyRiderDetails(PolicyRiderDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("01", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getRiderName() == null || StringUtils.isBlank(req.getRiderName())) {
				errors.add(new Error("02", "Rider Name", "Please Enter Rider Name"));
			}
			if (Double.valueOf(req.getSumAssured()) == null
					|| StringUtils.isBlank(Double.toString(req.getSumAssured()))) {
				errors.add(new Error("03", "Sum Assured", "Please Enter Sum Assured"));
			}
			if (Double.valueOf(req.getPremium()) == null || StringUtils.isBlank(Double.toString(req.getPremium()))) {
				errors.add(new Error("04", "Premium", "Please Enter Premium"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validatePolicyRiderDetails(PolicyAssuredDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("01", "Policy Id", "Please Enter Policy Id"));
			}
			if (Integer.valueOf(req.getGenderId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getGenderId()))) {
				errors.add(new Error("02", "Gender Id", "Please Enter Gender Id"));
			}
			if (req.getGender() == null || StringUtils.isBlank(req.getGender())) {
				errors.add(new Error("03", "Gender", "Please Enter Gender"));
			}
			if (req.getName() == null || StringUtils.isBlank(req.getName())) {
				errors.add(new Error("04", "Name", "Please Enter Name"));
			}
			if (req.getRelation() == null || StringUtils.isBlank(req.getRelation())) {
				errors.add(new Error("05", "Relation", "Please Enter Relation"));
			}
			if (Integer.valueOf(req.getRelationId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getRelationId()))) {
				errors.add(new Error("06", "Relation Id", "Please Enter Relation Id"));
			}
			if (Double.valueOf(req.getSumassured()) == null
					|| StringUtils.isBlank(Double.toString(req.getSumassured()))) {
				errors.add(new Error("07", "Sum Assured", "Please Enter Sum Assured"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validatePolicyAccountsDetails(PolicyAccountsDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("01", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getBifuelkitYN() == null || StringUtils.isBlank(req.getBifuelkitYN())) {
				errors.add(new Error("02", "Bifuel kit YN", "Please Enter Bifuel kit YN"));
			}
			if (Double.valueOf(req.getAnnualizedPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getAnnualizedPremium()))) {
				errors.add(new Error("03", "Annualized Premium", "Please Enter Annualized Premium"));
			}
			if (Double.valueOf(req.getBasicPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getBasicPremium()))) {
				errors.add(new Error("04", "Basic Premium", "Please Enter Basic Premium"));
			}
			if (Double.valueOf(req.getBasicTP()) == null || StringUtils.isBlank(Double.toString(req.getBasicTP()))) {
				errors.add(new Error("05", "Basic TP", "Please Enter Basic TP"));
			}
			if (Double.valueOf(req.getBifuelkit()) == null
					|| StringUtils.isBlank(Double.toString(req.getBifuelkit()))) {
				errors.add(new Error("06", "Bifuel kit", "Please Enter Bifuel kit"));
			}
			if (Double.valueOf(req.getCommissionBasePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
				errors.add(new Error("07", "Commission Base Premium", "Please Enter Commission Base Premium"));
			}
			if (Double.valueOf(req.getAnnualizedPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getAnnualizedPremium()))) {
				errors.add(new Error("08", "Annualized Premium", "Please Enter Annualized Premium"));
			}
			if (Double.valueOf(req.getBasicPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getBasicPremium()))) {
				errors.add(new Error("09", "Basic Premium", "Please Enter Basic Premium"));
			}
			if (Double.valueOf(req.getDiscountPercentage()) == null
					|| StringUtils.isBlank(Double.toString(req.getDiscountPercentage()))) {
				errors.add(new Error("10", "Discount Percentage", "Please Enter Discount Percentage"));
			}
			if (Double.valueOf(req.getFirstYearGST()) == null
					|| StringUtils.isBlank(Double.toString(req.getFirstYearGST()))) {
				errors.add(new Error("11", "First Year GST", "Please Enter First Year GST"));
			}
			if (Double.valueOf(req.getFirstYearPremiumwithGST()) == null
					|| StringUtils.isBlank(Double.toString(req.getFirstYearPremiumwithGST()))) {
				errors.add(new Error("12", "First Year Premium with GST", "Please Enter First Year Premium with GST"));
			}
			if (Double.valueOf(req.getGeographicalArea()) == null
					|| StringUtils.isBlank(Double.toString(req.getGeographicalArea()))) {
				errors.add(new Error("13", "Geographical Area", "Please Enter Geographical Area"));
			}
			if (req.getGeographicalAreaYN() == null || StringUtils.isBlank(req.getGeographicalAreaYN())) {
				errors.add(new Error("14", "Geographical Area YN", "Please Enter Geographical Area YN"));
			}
			if (Double.valueOf(req.getGst()) == null || StringUtils.isBlank(Double.toString(req.getGst()))) {
				errors.add(new Error("15", "Gst", "Please Enter Gst"));
			}
			if (req.getLLtoPaidDrvEmpYn() == null || StringUtils.isBlank(req.getLLtoPaidDrvEmpYn())) {
				errors.add(new Error("16", "LL to Paid Drv Emp Yn", "Please Enter LL to Paid Drv Emp Yn"));
			}
			if (Double.valueOf(req.getLLtoPaidDrvEmp()) == null
					|| StringUtils.isBlank(Double.toString(req.getLLtoPaidDrvEmp()))) {
				errors.add(new Error("17", "LL to Paid Drv Emp", "Please Enter LL to Paid Drv Emp"));
			}
			if (Double.valueOf(req.getNoClaimBonus()) == null
					|| StringUtils.isBlank(Double.toString(req.getNoClaimBonus()))) {
				errors.add(new Error("18", "No Claim Bonus", "Please Enter No Claim Bonus"));
			}
			if (Integer.valueOf(req.getNoofDrvEmp()) == null
					|| StringUtils.isBlank(Integer.toString(req.getNoofDrvEmp()))) {
				errors.add(new Error("19", "No of Drv Emp", "Please Enter No of Drv Emp"));
			}
			if (Double.valueOf(req.getODAddOnCommissionBasePremiumAB()) == null
					|| StringUtils.isBlank(Double.toString(req.getODAddOnCommissionBasePremiumAB()))) {
				errors.add(new Error("20", "OD Add On Commission Base PremiumAB",
						"Please Enter OD Add On Commission Base Premium AB"));
			}
			if (Double.valueOf(req.getOtherPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOtherPremium()))) {
				errors.add(new Error("21", "Other Premium", "Please Enter Other Premium"));
			}
			if (Double.valueOf(req.getOwnDamagePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOwnDamagePremium()))) {
				errors.add(new Error("22", "Own Damage Premium", "Please Enter Own Damage Premium"));
			}
			if (Double.valueOf(req.getOwnerDriverPA()) == null
					|| StringUtils.isBlank(Double.toString(req.getOwnerDriverPA()))) {
				errors.add(new Error("23", "Owner Driver PA", "Please Enter Owner Driver PA"));
			}
			if (req.getOwnerDriverPAYN() == null || StringUtils.isBlank(req.getOwnerDriverPAYN())) {
				errors.add(new Error("24", "Owner Driver PAYN", "Please Enter Owner Driver PAYN"));
			}
			if (req.getPAPaidDrvYN() == null || StringUtils.isBlank(req.getPAPaidDrvYN())) {
				errors.add(new Error("25", "PA Paid Drv YN", "Please Enter PA Paid Drv YN"));
			}
			if (Integer.valueOf(req.getPAPaidDrvNoofSeats()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPAPaidDrvNoofSeats()))) {
				errors.add(new Error("26", "PA Paid Drv No of Seats", "Please Enter PA Paid Drv No of Seats"));
			}
			if (Double.valueOf(req.getPolicyCancelledYnBy()) == null
					|| StringUtils.isBlank(Double.toString(req.getPolicyCancelledYnBy()))) {
				errors.add(new Error("27", "Policy Cancelled Yn By", "Please Enter Policy Cancelled Yn By"));
			}
			if (Double.valueOf(req.getPremiumwithGst()) == null
					|| StringUtils.isBlank(Double.toString(req.getPremiumwithGst()))) {
				errors.add(new Error("28", "Premium with Gst", "Please Enter Premium with Gst"));
			}
			if (Double.valueOf(req.getRiderPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getRiderPremium()))) {
				errors.add(new Error("29", "Rider Premium", "Please Enter Rider Premium"));
			}
			if (Double.valueOf(req.getSumInsured()) == null
					|| StringUtils.isBlank(Double.toString(req.getSumInsured()))) {
				errors.add(new Error("30", "Sum Insured", "Please Enter Sum Insured"));
			}
			if (Double.valueOf(req.getTotalAddOnCoverPremiumB()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalAddOnCoverPremiumB()))) {
				errors.add(new Error("31", "Total Add On Cover PremiumB", "Please Enter Total Add On Cover PremiumB"));
			}
			if (Double.valueOf(req.getTotalLiabilityPremiumC()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalLiabilityPremiumC()))) {
				errors.add(new Error("32", "Total Liability PremiumC", "Please Enter Total Liability PremiumC"));
			}
			if (Double.valueOf(req.getTotalOwnDamagePremiumA()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalOwnDamagePremiumA()))) {
				errors.add(new Error("33", "Total Own Damage PremiumA", "Please Enter Total Own Damage PremiumA"));
			}
			if (Double.valueOf(req.getTotalPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalPremium()))) {
				errors.add(new Error("34", "Total Premium", "Please Enter Total Premium"));
			}
			if (Double.valueOf(req.getTotalPremiumABC()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalPremiumABC()))) {
				errors.add(new Error("35", "Total PremiumABC", "Please Enter Total PremiumABC"));
			}
			if (Double.valueOf(req.getUnnamedPassengerLimitPerPerson()) == null
					|| StringUtils.isBlank(Double.toString(req.getUnnamedPassengerLimitPerPerson()))) {
				errors.add(new Error("36", "Unnamed Passenger Limit Per Person",
						"Please Enter Unnamed Passenger Limit Per Person"));
			}
			if (Integer.valueOf(req.getUnnamedPassengerNoofSeats()) == null
					|| StringUtils.isBlank(Integer.toString(req.getUnnamedPassengerNoofSeats()))) {
				errors.add(
						new Error("37", "Unnamed Passenger No of Seats", "Please Enter Unnamed Passenger No of Seats"));
			}
			if (Double.valueOf(req.getUnnamedPassengerPA()) == null
					|| StringUtils.isBlank(Double.toString(req.getUnnamedPassengerPA()))) {
				errors.add(new Error("38", "Unnamed Passenger PA", "Please Enter Unnamed Passenger PA"));
			}

			if (req.getUnnamedPassengerPAYN() == null || StringUtils.isBlank(req.getUnnamedPassengerPAYN())) {
				errors.add(new Error("39", "Unnamed Passenger PAYN", "Please Enter Unnamed Passenger PAYN"));
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validateClientUpdateReq(ClientDetailsUpdateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validateFollowUpDetailsReq(FollowUpDetailsSaveReq req) {

		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			/*
			 * if (req.getClientid() == null || StringUtils.isBlank(req.getClientid())) {
			 * errors.add(new Error("01", "Client Id", "Please Enter Client Id")); } if
			 * (req.getCollecteddate()== null ||
			 * StringUtils.isBlank(req.getCollecteddate().toString())) { errors.add(new
			 * Error("02", "Collected Date", "Please Enter Collected Date")); } else if
			 * (!req.getCollecteddate().toString().matches(
			 * "([0-9]{2})/([0-9]{2})/([0-9]{4})")) { errors.add(new Error("02",
			 * "Collected Date",
			 * "Collected Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getCollectedtime()== null ||
			 * StringUtils.isBlank(req.getCollectedtime().toString())) { errors.add(new
			 * Error("03", "Collected time", "Please Enter Collected time")); } else if
			 * (!req.getCollectedtime().toString().matches(
			 * "([0-9]{2})/([0-9]{2})/([0-9]{4})")) { errors.add(new Error("03",
			 * "Collected time",
			 * "Collected time format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getCompanyid()== null ||
			 * StringUtils.isBlank(req.getCompanyid())) { errors.add(new Error("04",
			 * "Company Id", "Please Enter Company Id")); } else if
			 * (req.getCompanyid().length() > 100) { errors.add(new Error("04",
			 * "Company id", "Please Enter Company id within 100 Characters")); } if
			 * (req.getEntrydate()== null ||
			 * StringUtils.isBlank(req.getEntrydate().toString())) { errors.add(new
			 * Error("05", "Entry date", "Please Enter Entrydate")); } else if
			 * (!req.getEntrydate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
			 * { errors.add(new Error("05", "Entry date",
			 * "Entry date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getFollowupapplicable()== null ||
			 * StringUtils.isBlank(req.getFollowupapplicable())) { errors.add(new
			 * Error("06", "Follow up applicable", "Please Enter Follow up applicable")); }
			 * else if (req.getFollowupapplicable().length() > 100) { errors.add(new
			 * Error("06", "Follow up applicable",
			 * "Please Enter Follow up applicable within 100 Characters")); } if
			 * (req.getFollowupapplicableid()== null ||
			 * StringUtils.isBlank(req.getFollowupapplicableid().toString())) {
			 * errors.add(new Error("07", "Follow up applicable Id",
			 * "Please Enter Follow up applicable Id")); } if
			 * (req.getFollowupconcludedDesc()== null ||
			 * StringUtils.isBlank(req.getFollowupconcludedDesc())) { errors.add(new
			 * Error("08", "Follow up concluded Desc",
			 * "Please Enter Follow up concluded Desc")); } else if
			 * (req.getFollowupconcludedDesc().length() > 100) { errors.add(new Error("08",
			 * "Follow up concluded Desc",
			 * "Please Enter Follow up concluded Desc within 100 Characters")); } if
			 * (req.getFollowupconcludedid()== null ||
			 * StringUtils.isBlank(req.getFollowupconcludedid().toString())) {
			 * errors.add(new Error("09", "Follow up concluded id",
			 * "Please Enter Follow up concluded id")); } if (req.getFollowupid()== null ||
			 * StringUtils.isBlank(req.getFollowupid().toString())) { errors.add(new
			 * Error("10", "Follow up  id", "Please Enter Follow up id")); }
			 * 
			 * if (req.getLastfollowupdate()== null ||
			 * StringUtils.isBlank(req.getLastfollowupdate().toString())) { errors.add(new
			 * Error("11", "Last follow up date", "Please Enter Last follow up date")); }
			 * else if (!req.getLastfollowupdate().toString().matches(
			 * "([0-9]{2})/([0-9]{2})/([0-9]{4})")) { errors.add(new Error("11",
			 * "Last follow up date",
			 * "Last follow up date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getLaststatusupdatetime()== null ||
			 * StringUtils.isBlank(req.getLaststatusupdatetime().toString())) {
			 * errors.add(new Error("12", "Last status update time",
			 * "Please Enter Last status update time")); } if
			 * (req.getLaststatusupdatedate()== null ||
			 * StringUtils.isBlank(req.getLaststatusupdatedate().toString())) {
			 * errors.add(new Error("12", "Last status update date ",
			 * "Please Enter Last status update date ")); } if (req.getLoginid()== null ||
			 * StringUtils.isBlank(req.getLoginid().toString())) { errors.add(new
			 * Error("13", "Login id", "Please Enter Loginid")); } if
			 * (req.getNextfollowupdate()== null ||
			 * StringUtils.isBlank(req.getNextfollowupdate().toString())) { errors.add(new
			 * Error("14", "Next followup time", "Please Enter Next followup time")); }else
			 * if (!req.getNextfollowupdate().toString().matches(
			 * "([0-9]{2})/([0-9]{2})/([0-9]{4})")) { errors.add(new Error("14",
			 * "Next followup date time"
			 * ,"Next followup date time format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getNextfollowuptime()== null ||
			 * StringUtils.isBlank(req.getNextfollowuptime().toString())) { errors.add(new
			 * Error("14", "Next followup date ", "Please Enter Next followup date ")); }
			 * 
			 * if (req.getRemarks()==null || StringUtils.isBlank(req.getRemarks())) {
			 * errors.add(new Error("15", "Remarks", "Please Enter Remarks")); } if
			 * (req.getStatus()==null || StringUtils.isBlank(req.getStatus())) {
			 * errors.add(new Error("16", "Status", "Please Enter Status")); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public List<Error> validatebulkEditClientReq(ClientBulkEditReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validateProductDetailsGroupReq(ProductDetailsGroupReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			if (StringUtils.isBlank(req.getProductId())) {
				errors.add(new Error("01", "Product Id", "Please Enter Product Id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("02", "Common Error", e.getMessage()));

		}
		return errors;
	}

	@Override
	public List<Error> validateCardsDataCount(DashBoardCardsDataCountReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Error> validateClientAddressDetailsReq(ClientDetailsJsonTemReq req1) {
		
		//ClientAddressDetailsListSaveReq req = req1.getClientAddressDetails();
		
		List<Error> errors = new ArrayList<Error>();
		try {
			if (StringUtils.isBlank(req1.getClientDetails().getClientRefNo())) {
				errors.add(new Error("01", "ClientRefNo", "Please Enter Client Ref No"));
			}

			if (req1.getClientAddressDetails() == null || req1.getClientAddressDetails().size() <= 0) {
				errors.add(new Error("01", "Address List ", "Please Enter Address Details"));
			} else {
				for (ClientAddressDetailsSaveReq data : req1.getClientAddressDetails()) {
					if (StringUtils.isNotBlank(data.getAddresTypeId())
							&& StringUtils.isBlank(data.getClientAddressId())) {
						ClientAddressDetails addrData = clientAddrRepo.findByClientRefNoAndAddresTypeId(
								req1.getClientDetails().getClientRefNo(), Integer.valueOf(data.getAddresTypeId()));
						if (addrData != null) {
							errors.add(new Error("01", data.getAddressType(),
									" Alredy One " + data.getAddressType() + " Available"));
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("02", "Common Error", e.getMessage()));

		}
		return errors;
	}

	@Override
	public List<Error> validateGenerateLead(LeadGenerateReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			if (StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("01", "ClientRefNo", "Please Enter ClientRefNo "));
			}

			if (StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("02", "Insurance Id", "Please Enter Insurance Id "));
			}

			if (StringUtils.isBlank(req.getBranchCode())) {
				errors.add(new Error("03", "Branch Code", "Please Enter Branch Code "));
			}

			if (StringUtils.isBlank(req.getCreatedBy())) {
				errors.add(new Error("04", "Created By ", "Please Enter Created By "));
			}

			if (StringUtils.isBlank(req.getUserType())) {
				errors.add(new Error("04", "User Type", "Please Enter UserType"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("02", "Common Error", e.getMessage()));

		}
		return errors;
	}

	@Override
	public List<Error> validateClientsLeadCount(ClientLeadCountReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {	
			if(StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("01","ClientRefNo","Please Enter Client Ref No"));
			}
		
		} catch ( Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
				errors.add(new Error("02","Common Error", e.getMessage()));
				
			}
			return errors;
		}

	@Override
	public List<Error> validateupdateLeadStatus(ClientUpdateStatusReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			if (StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("01", "ClientRefNo", "Please Enter ClientRefNo "));
			}
			
			if (req.getLeadId()==null ||  StringUtils.isBlank(req.getLeadId().toString() )) {
				errors.add(new Error("02", "LeadId", "Please Enter ClientRefNo "));
			}

			if (StringUtils.isBlank(req.getInsuranceId())) {
				errors.add(new Error("03", "Insurance Id", "Please Enter Insurance Id "));
			}

			if (StringUtils.isBlank(req.getBranchCode())) {
				errors.add(new Error("04", "Branch Code", "Please Enter Branch Code "));
			}

			if (StringUtils.isBlank(req.getCreatedBy())) {
				errors.add(new Error("05", "Created By ", "Please Enter Created By "));
			}

			if (StringUtils.isBlank(req.getUserType())) {
				errors.add(new Error("06", "User Type", "Please Enter UserType"));
			}

			if (StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("07", "Status ", "Please Enter Status"));
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("02","Common Error", e.getMessage()));
		}
		return null;
	}

	@Override
	public List<Error> branchMaster(BranchMasterReq req) {

		List<Error> errors = new ArrayList<Error>();
		
		try {	
			if(req.getAddress1()==null || StringUtils.isBlank(req.getAddress1())) {
				errors.add(new Error("","Address1","Please Enter Address1"));
			}
			if(req.getAddress2()==null || StringUtils.isBlank(req.getAddress2())) {
				errors.add(new Error("","Address2","Please Enter Address2"));
			}
			if(req.getBranchName()==null || StringUtils.isBlank(req.getBranchName())) {
				errors.add(new Error("","BranchName","Please Enter BranchName"));
			}
			if(req.getCity()==null || StringUtils.isBlank(req.getCity())) {
				errors.add(new Error("","City","Please Enter City"));
			} 
			if(req.getCountry()==null || StringUtils.isBlank(req.getCountry())) {
				errors.add(new Error("","Country","Please Enter Country"));
			}
			if(req.getEmail()==null || StringUtils.isBlank(req.getEmail())) {
				errors.add(new Error("","Email","Please Enter Email"));
			}
			if(req.getInsCompanyId()==null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("","CompanyId","Please Enter Ins CompanyId"));
			}
			if(req.getMobileNo()==null || StringUtils.isBlank(req.getMobileNo())) {
				errors.add(new Error("","MobileNo","Please Enter MobileNo"));
			}
			if(req.getPhone()==null || StringUtils.isBlank(req.getPhone())) {
				errors.add(new Error("","Phone","Please Enter Phone"));
			} 
			if(req.getRegionCode()==null || StringUtils.isBlank(req.getRegionCode())) {
				errors.add(new Error("","Region Code","Please Enter Region Code"));
			}
			if(req.getStatus()==null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("","Status","Please Enter Status"));
			}
			if(req.getRemarks()==null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("","Remarks","Please Enter Remarks"));
			} 
		
		} catch ( Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			
		}
		return errors;
		
	
	}

	@Override
	public List<Error> companytype(CompanyTypeMasterReq req) {

		List<Error> errors = new ArrayList<Error>();
		
		try {	
			
			if(req.getInsCompanyId()==null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("","CompanyId","Please Enter Ins CompanyId"));
			} 
			if(req.getStatus()==null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("","Status","Please Enter Status"));
			}
			if(req.getRemarks()==null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("","Remarks","Please Enter Remarks"));
			} 
		
		} catch ( Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			
		}
		return errors;
		
	
	}

	@Override
	public List<Error> documentTypeMaster(DocumentTypeMasterReq req) {

		List<Error> errors = new ArrayList<Error>();

		try {

			if (req.getDocumentDescription() == null || StringUtils.isBlank(req.getDocumentDescription())) {
				errors.add(new Error("", "DocumentDescription", "Please Enter DocumentDescription"));
			} 
			if (req.getInsCompanyId() == null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("", "CompanyId", "Please Enter Ins CompanyId"));
			}
			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("", "Status", "Please Enter Status"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("", "Remarks", "Please Enter Remarks"));
			}
			if (req.getDocApplicable() == null || StringUtils.isBlank(req.getDocApplicable())) {
				errors.add(new Error("", "DocApplicable", "Please Enter DocApplicable"));
			}
			if (req.getDocCons() == null || StringUtils.isBlank(req.getDocCons())) {
				errors.add(new Error("", "DocCons", "Please Enter DocCons"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());

		}
		return errors;

	}

	@Override
	public List<Error> insuranceCompanyMaster(InsuranceCompanyMasterSaveReq req) {

		List<Error> errors = new ArrayList<Error>();
		
		try {	
			if(req.getInsAddress()==null || StringUtils.isBlank(req.getInsAddress())) {
				errors.add(new Error("","InsAddress","Please Enter InsAddress"));
			}
			if(req.getInsCity()==null || StringUtils.isBlank(req.getInsCity().toString())) {
				errors.add(new Error("","InsCity","Please Enter InsCity"));
			}
			if(req.getInsCountry()==null || StringUtils.isBlank(req.getInsCountry().toString())) {
				errors.add(new Error("","InsCountry","Please Enter InsCountry"));
			}
			if(req.getInsEmail()==null || StringUtils.isBlank(req.getInsEmail())) {
				errors.add(new Error("","InsEmail","Please Enter InsEmail"));
			}
			if(req.getInsId()==null || StringUtils.isBlank(req.getInsId())) {
				errors.add(new Error("","InsId","Please Enter InsId"));
			}
			if(req.getInsName()==null || StringUtils.isBlank(req.getInsName())) {
				errors.add(new Error("","InsName","Please Enter InsName"));
			}
			if(req.getInsPhone()==null || StringUtils.isBlank(req.getInsPhone())) {
				errors.add(new Error("","InsPhone","Please Enter InsPhone"));
			}
			if(req.getInsZipcode()==null || StringUtils.isBlank(req.getInsZipcode())) {
				errors.add(new Error("","InsZipcode","Please Enter InsZipcode"));
			}
			if(req.getMobileNo()==null || StringUtils.isBlank(req.getMobileNo())) {
				errors.add(new Error("","MobileNo","Please Enter MobileNo"));
			}
		
		} catch ( Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			
		}
		return errors;
		
	
	}

	@Override
	public List<Error> mailMaster(MailMasterReq req) {
		
		List<Error> errors = new ArrayList<Error>();
		
		try {	

			if(req.getStatus()==null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("","Status","Please Enter Status"));
			}
			if(req.getRemarks()==null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("","Remarks","Please Enter Remarks"));
			}
			
			if(req.getAddress()==null || StringUtils.isBlank(req.getAddress())) {
				errors.add(new Error("","Address","Please Enter Address"));
			}
			if(req.getInsCompanyId()==null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("","InsCompanyId","Please Enter InsCompanyId"));
			}
			if(req.getSmtpHost()==null || StringUtils.isBlank(req.getSmtpHost())) {
				errors.add(new Error("","SmtpHost","Please Enter SmtpHost"));
			}
			if(req.getSmtpPort()==null || StringUtils.isBlank(req.getSmtpPort().toString())) {
				errors.add(new Error("","SmtpPort","Please Enter SmtpHost"));
			}
			if(req.getSmtpPwd()==null || StringUtils.isBlank(req.getSmtpPwd())) {
				errors.add(new Error("","SmtpPwd","Please Enter SmtpPwd"));
			}
			if(req.getSmtpUser()==null || StringUtils.isBlank(req.getSmtpUser())) {
				errors.add(new Error("","SmtpUser","Please Enter SmtpUser"));
			}
		
		} catch ( Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			
		}
		return errors;
		
	
	
		
	}

	@Override
	public List<Error> smsConfigMaster(SmsConfigMasterReq req) {

		List<Error> errors = new ArrayList<Error>();

		try {

			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("", "Status", "Please Enter Status"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("", "Remarks", "Please Enter Remarks"));
			}
			if (req.getInsId() == null || StringUtils.isBlank(req.getInsId())) {
				errors.add(new Error("", "InsId", "Please Enter InsId"));
			}
			if (req.getSecureYn() == null || StringUtils.isBlank(req.getSecureYn())) {
				errors.add(new Error("", "SecureYn", "Please Enter SecureYn"));
			}
			if (req.getSenderid() == null || StringUtils.isBlank(req.getSenderid())) {
				errors.add(new Error("", "Senderid", "Please Enter Senderid"));
			}
			if (req.getSmsPartyUrl() == null || StringUtils.isBlank(req.getSmsPartyUrl())) {
				errors.add(new Error("", "SmsPartyUrl", "Please Enter SmsPartyUrl"));
			}
			if (req.getSmsUserName() == null || StringUtils.isBlank(req.getSmsUserName())) {
				errors.add(new Error("", "SmsUserName", "Please Enter SmsUserName"));
			}
			if (req.getSmsUserPass() == null || StringUtils.isBlank(req.getSmsUserPass())) {
				errors.add(new Error("", "SmsUserPass", "Please Enter SmsUserPass"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());

		}
		return errors;

	}

	@Override
	public List<Error> userTypeMaster(UsertypeMasterReq req) {

		List<Error> errors = new ArrayList<Error>();

		try {

			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("", "Status", "Please Enter Status"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("", "Remarks", "Please Enter Remarks"));
			}
			if (req.getCompanyId() == null || StringUtils.isBlank(req.getCompanyId())) {
				errors.add(new Error("", "InsId", "Please Enter InsId"));
			}
			if (req.getUsertypeDescription() == null || StringUtils.isBlank(req.getUsertypeDescription())) {
				errors.add(new Error("", "Usertype Description", "Please Enter Usertype Description"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());

		}
		return errors;

	}

	@Override
	public List<Error> notificationTemplateMaster(NotifTemplateMasterReq req) {

		List<Error> errors = new ArrayList<Error>();

		try {

			if (req.getStatus() == null || StringUtils.isBlank(req.getStatus())) {
				errors.add(new Error("", "Status", "Please Enter Status"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("", "Remarks", "Please Enter Remarks"));
			}
			if (req.getInsId() == null || StringUtils.isBlank(req.getInsId())) {
				errors.add(new Error("", "InsId", "Please Enter InsId"));
			}
			if (req.getQueryKey() == null || StringUtils.isBlank(req.getQueryKey())) {
				errors.add(new Error("", "QueryKey", "Please Enter QueryKey"));
			}
			if (req.getNotificationApplicable() == null || StringUtils.isBlank(req.getNotificationApplicable())) {
				errors.add(new Error("", "NotificationApplicable", "Please Enter NotificationApplicable"));
			}
			
			if (req.getMailRequired() == null || StringUtils.isBlank(req.getMailRequired())) {
				errors.add(new Error("", "MailRequired", "Please Enter MailRequired"));
			}
			if (req.getMailSubject() == null || StringUtils.isBlank(req.getMailSubject())) {
				errors.add(new Error("", "MailSubject", "Please Enter MailSubject"));
			}
			if (req.getMailBody() == null || StringUtils.isBlank(req.getMailBody())) {
				errors.add(new Error("", "MailBody", "Please Enter MailBody"));
			}
			if (req.getMailRegards() == null || StringUtils.isBlank(req.getMailRegards())) {
				errors.add(new Error("", "MailRegards", "Please Enter MailRegards"));
			}
			
			
			if (req.getSmsRequired() == null || StringUtils.isBlank(req.getSmsRequired())) {
				errors.add(new Error("", "SmsRequired", "Please Enter SmsRequired"));
			}
			if (req.getSmsSubject() == null || StringUtils.isBlank(req.getSmsSubject())) {
				errors.add(new Error("", "SmsSubject", "Please Enter SmsSubject"));
			}
			if (req.getSmsBodyEn() == null || StringUtils.isBlank(req.getSmsBodyEn())) {
				errors.add(new Error("", "SmsBody", "Please Enter Sms Body"));
			}
			if (req.getSmsRegards() == null || StringUtils.isBlank(req.getSmsRegards())) {
				errors.add(new Error("", "SmsRegards", "Please Enter SmsRegards"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("", "Common Error", e.getMessage()));
			
		}
		return errors;

	}

	@Override
	public List<Error> validateLeadQuoteDetails(LeadQuoteDetailsReq req) {
		List<Error> errors = new ArrayList<Error>();

		try {

			if ( StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("01", "ClientRefNo", "Please Enter ClientRefNo"));
			}
		
			if ( StringUtils.isBlank(req.getLeadId())) {
				errors.add(new Error("02", "LeadId", "Please Enter LeadId"));
			}
			if ( StringUtils.isBlank(req.getReferenceNo())) {
				errors.add(new Error("03", "Reference No", "Please Enter Reference No"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("04", "Common Error", e.getMessage()));
		}
		return errors;

	}

	@Override
	public List<Error> validateCrmRedirectLink(CrmRedirectLinkReq req) {
		List<Error> errors = new ArrayList<Error>();

		try {

			if ( StringUtils.isBlank(req.getScreenName())) {
				errors.add(new Error("01", "Screen Name", "Please Enter Redirecting Screen Name"));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("04", "Common Error", e.getMessage()));
		}
		return errors;

	}

	@Override
	public List<Error> validateupdateLeadStatus(UpdateEnquiryStatusReq req) {
		List<Error> errors = new ArrayList<Error>();

		try {

			if ( StringUtils.isBlank(req.getInsuranceId())) {
				errors.add(new Error("01", "Insurance Id", "Please Enter InsuranceId"));
			}	
			if ( StringUtils.isBlank(req.getBranchCode())) {
				errors.add(new Error("02", "Branch Code", "Please Enter Branch Code"));
			}
			if ( StringUtils.isBlank(req.getLeadId())) {
				errors.add(new Error("03", "Lead Id", "Please Enter Lead Id"));
			}
			if ( StringUtils.isBlank(req.getSubStatusCode())) {
				errors.add(new Error("04", "Sub Status Code", "Please Enter Sub Status Code"));
			}
			if ( StringUtils.isBlank(req.getUserType())) {
				errors.add(new Error("05", "User Type", "Please Enter User Type"));
			}
			if ( StringUtils.isBlank(req.getCreatedBy())) {
				errors.add(new Error("06", "Created By", "Please Enter Created By"));
			}
			if ( StringUtils.isBlank(req.getEnquiryDesc())) {
				errors.add(new Error("07", "Enquiry Desc", "Please Enter Enquiry Desc"));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			errors.add(new Error("04", "Common Error", e.getMessage()));
		}
		return errors;

	}

	@Override
	public List<Error> validateupdateQuoteStatus(UpdateQuoteStatusReq req) {
		List<Error> errors = new ArrayList<Error>();
		
		try {

			if ( StringUtils.isBlank(req.getSumInsured())) {
				errors.add(new Error("01", "Sum Insured", "Please Enter Sum Insured"));
			}
			if ( StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("02", "Remarks", "Please Enter Remarks"));
			}
			if ( StringUtils.isBlank(req.getEnquiryId())) {
				errors.add(new Error("03", "Enquiry Id", "Please Enter Enquiry Id"));
			}
			if ( StringUtils.isBlank(req.getLeadId())) {
				errors.add(new Error("04", "Lead Id", "Please Enter Lead Id"));
			}
	} catch (Exception e) {
		e.printStackTrace();
		log.info("Exception is ---> " + e.getMessage());
		errors.add(new Error("04", "Common Error", e.getMessage()));
	}
	return errors;

}

	@Override
	public List<Error> validateDocTypeSaveReq(DocTypeSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		
		try {

			if ( StringUtils.isBlank(req.getDocApplicable())) {
				errors.add(new Error("01", "Doc Applicable", "Please Enter Doc Applicable"));
			}
			if ( StringUtils.isBlank(req.getDocApplicableId())) {
				errors.add(new Error("02", "Doc ApplicableId", "Please Enter Doc ApplicableId"));
			}
			if ( StringUtils.isBlank(req.getDocTypeId())) {
				errors.add(new Error("03", "getDocTypeId", "Please Enter getDocTypeId"));
			}
			if ( StringUtils.isBlank(req.getDocTypeDescription())) {
				errors.add(new Error("04", "DocTypeDescription", "Please Enter DocTypeDescription"));
			}
			
			if ( StringUtils.isBlank(req.getDocStatusCode())) {
				errors.add(new Error("04", "DocStatusCode", "Please Enter StatusCode"));
			}
			
		/*	if ( StringUtils.isBlank(req.getDocStatusDesc())) {
				errors.add(new Error("04", "DocTypeDescription", "Please Enter DocTypeDescription"));
			} */

			if ( StringUtils.isBlank(req.getDocTypeId())) {
				errors.add(new Error("04", "DocTypeId", "Please Enter DocTypeId"));
			}
			if ( StringUtils.isBlank(req.getDocTypeDescription())) {
				errors.add(new Error("04", "DocType Description", "Please Enter DocType Description"));
			}
			
			if ( StringUtils.isBlank(req.getRefNo())) {
				errors.add(new Error("04", "ReferenceNo", "Please Enter DocType ReferenceNo"));
			}
			
	} catch (Exception e) {
		e.printStackTrace();
		log.info("Exception is ---> " + e.getMessage());
		errors.add(new Error("04", "Common Error", e.getMessage()));
	}
	return errors;

}

}
