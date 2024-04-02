package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.google.gson.Gson;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.PolicyAccountsDetails;
import com.maan.crm.bean.PolicyAddOn;
import com.maan.crm.bean.PolicyAddOnId;
import com.maan.crm.bean.PolicyAdditionalDetails;
import com.maan.crm.bean.PolicyAdditionalDetailsId;
import com.maan.crm.bean.PolicyAssuredDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.PolicyDetailsId;
import com.maan.crm.bean.PolicyNomineeDetails;
import com.maan.crm.bean.PolicyNomineeDetailsId;
import com.maan.crm.bean.PolicyPaymentDetails;
import com.maan.crm.bean.PolicyPaymentDetailsId;
import com.maan.crm.bean.PolicyRiderDetails;
import com.maan.crm.bean.ProspectDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.PolicyAccountsDetailsRepository;
import com.maan.crm.repository.PolicyAddOnRepository;
import com.maan.crm.repository.PolicyAdditionalDetailsRepository;
import com.maan.crm.repository.PolicyAssuredDetailsRepository;
import com.maan.crm.repository.PolicyDetailsRepository;
import com.maan.crm.repository.PolicyNomineeDetailsRepository;
import com.maan.crm.repository.PolicyPaymentDetailsRepository;
import com.maan.crm.repository.PolicyRiderDetailsRepository;
import com.maan.crm.req.PolicyAddOnGetAllReq;
import com.maan.crm.req.PolicyAddOnGetReq;
import com.maan.crm.req.PolicyAddOnSaveReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetAllReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetReq;
import com.maan.crm.req.PolicyAdditionalDetailsSaveReq;
import com.maan.crm.req.PolicyBulkEditReq;
import com.maan.crm.req.PolicyDetailsGetAllReq;
import com.maan.crm.req.PolicyDetailsSaveReq;
import com.maan.crm.req.PolicyNomineeDetailsGetAllReq;
import com.maan.crm.req.PolicyNomineeDetailsGetReq;
import com.maan.crm.req.PolicyNomineeDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentDetailsGetReq;
import com.maan.crm.req.PolicyPaymentDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentGetAllReq;
import com.maan.crm.req.PolicyReq;
import com.maan.crm.req.PolicySearchReq;
import com.maan.crm.req.TrackingReq;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.PolicyAddOnGetAllRes;
import com.maan.crm.res.PolicyAddOnRes;
import com.maan.crm.res.PolicyAdditionalDetailsGetAllRes;
import com.maan.crm.res.PolicyAdditionalDetailsRes;
import com.maan.crm.res.PolicyAssuredDetailsRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.PolicyDetailsRes;
import com.maan.crm.res.PolicyGetAllCountRes;
import com.maan.crm.res.PolicyNomineeDetailsGetAllRes;
import com.maan.crm.res.PolicyNomineeDetailsRes;
import com.maan.crm.res.PolicyPaymentDetailsRes;
import com.maan.crm.res.PolicyPaymentGetAllRes;
import com.maan.crm.res.PolicyRes;
import com.maan.crm.res.PolicyRiderDetailsRes;
import com.maan.crm.res.PolicySearchRes;
import com.maan.crm.res.PolicySuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PolicyService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyNomineeDetailsRepository policydetailsrepository;

	@Autowired
	private PolicyAddOnRepository policyaddonrepository;

	@Autowired
	private PolicyPaymentDetailsRepository policypaymentdetailsrepository;

	@Autowired
	private PolicyDetailsRepository policydetailsrep;

	@Autowired
	private PolicyAdditionalDetailsRepository policyadditionaldetailsrepository;

	@Autowired
	private PolicyRiderDetailsRepository policyriderrepository;

	@Autowired
	private PolicyAssuredDetailsRepository policyassuredrepository;

	@Autowired
	private PolicyAccountsDetailsRepository policyaccountrepository;

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private InsuranceCompanyMasterRepository insRepo;

	@Autowired
	private ClaimLoginMasterRepository loginRepo;

	@Autowired
	private MailThreadServiceImpl mailThreadService;

	@Autowired
	private ClientAddressDetailsRepository clientAddRepo;

	@Autowired
	private CriteriaQueryServiceImpl Criteria;

	@Autowired
	private TrackingServiceImpl trackService;

	Gson json = new Gson();

	private Logger log = LogManager.getLogger(PolicyServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Policy Nominee Details Validate

	@Override
	public List<Error> validatePolicyNominee(PolicyNomineeDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Double.valueOf(req.getBenefit()) == null || StringUtils.isBlank(Double.toString(req.getBenefit()))) {
				errors.add(new Error("01", "Benefit", "Please Enter Benefit"));
			}
			if (req.getDob() == null || StringUtils.isBlank(req.getDob().toString())) {
				errors.add(new Error("02", "DOB", "Please Enter Dob"));
			}
			if (req.getGender() == null || StringUtils.isBlank(req.getGender())) {
				errors.add(new Error("03", "Gender", "Please Enter Gender"));
			}
			if (Integer.valueOf(req.getGenderId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getGenderId()))) {
				errors.add(new Error("04", "Gender Id", "Please Enter Gender Id"));
			}
			if (req.getName() == null || StringUtils.isBlank(req.getName())) {
				errors.add(new Error("05", "Name", "PLease Enter Name"));
			}
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("06", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getRelation() == null || StringUtils.isBlank(req.getRelation())) {
				errors.add(new Error("07", "Relation", "Please Enter Relation"));
			}
			if (Integer.valueOf(req.getRelationId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getRelationId()))) {
				errors.add(new Error("08", "Relation Id", "Please Enter Relation Id"));
			}
		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	// Policy Nominee Details Save
	@Override
	public PolicySuccessRes savePolicyNominee(PolicyNomineeDetailsSaveReq req) {
		PolicySuccessRes res = new PolicySuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			PolicyNomineeDetailsId id = new PolicyNomineeDetailsId();

			id.setNomineeId(req.getNomineeId());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyNomineeDetails> data = policydetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				PolicyNomineeDetails ent = mapper.map(req, PolicyNomineeDetails.class);
				ent.setNomineeId(req.getNomineeId());

				policydetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<PolicyNomineeDetails> list = policydetailsrepository.findAllByOrderByNomineeIdDesc();
				Integer nomineeId = 1000;

				if (list.size() != 0) {
					nomineeId = list.get(0).getNomineeId() + 1;
				}
				PolicyNomineeDetails ent = mapper.map(req, PolicyNomineeDetails.class);
				ent.setNomineeId(nomineeId);
				;
				policydetailsrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	/// Policy Nominee Details Get by nominee id
	@Override
	public PolicyNomineeDetailsRes getPolicyNominee(PolicyNomineeDetailsGetReq req) {
		PolicyNomineeDetailsRes res = new PolicyNomineeDetailsRes();
		try {

			PolicyNomineeDetailsId id = new PolicyNomineeDetailsId();
			id.setNomineeId(req.getNomineeId());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyNomineeDetails> opt = policydetailsrepository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(opt.get(), PolicyNomineeDetailsRes.class);

			}
		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	// Policy Add On Validation
	@Override
	public List<Error> validatePolicyAddOn(PolicyAddOnSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPolicyid()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyid()))) {
				errors.add(new Error("01", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getAddOnsNotOpted() == null || StringUtils.isBlank(req.getAddOnsNotOpted())) {
				errors.add(new Error("02", "Add Ons Not Opted", "Please Enter Policy Add Ons Opted"));
			}
			if (req.getAddOnsOpted() == null || StringUtils.isBlank(req.getAddOnsOpted())) {
				errors.add(new Error("03", "Add Ons Opted", "Please Enter Add Ons Opted"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	// Policy Add On Save

	@Override
	public PolicySuccessRes savePolicyAddOn(PolicyAddOnSaveReq req) {
		PolicySuccessRes res = new PolicySuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			PolicyAddOnId id = new PolicyAddOnId();

			id.setAddOnid(req.getAddOnid());
			id.setPolicyid(req.getPolicyid());
			Optional<PolicyAddOn> data = policyaddonrepository.findById(id);
			if (data.isPresent()) {
				// Update
				PolicyAddOn ent = mapper.map(req, PolicyAddOn.class);
				ent.setAddOnid(req.getAddOnid());
				policyaddonrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<PolicyAddOn> list = policyaddonrepository.findAllByOrderByAddOnidDesc();
				Integer addOnid = 1000;

				if (list.size() != 0) {
					addOnid = list.get(0).getAddOnid() + 1;
				}
				PolicyAddOn ent = mapper.map(req, PolicyAddOn.class);
				ent.setAddOnid(addOnid);
				policyaddonrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Policy Add On Get

	@Override
	public PolicyAddOnRes getPolicyAddOn(PolicyAddOnGetReq req) {
		PolicyAddOnRes res = new PolicyAddOnRes();
		try {

			PolicyAddOnId id = new PolicyAddOnId();
			id.setAddOnid(req.getAddOnid());
			id.setPolicyid(req.getPolicyid());

			Optional<PolicyAddOn> opt = policyaddonrepository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(opt.get(), PolicyAddOnRes.class);

			}
		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	// Policy Payment Details

	@Override
	public List<Error> validatePolicyPayment(PolicyPaymentDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPaymentdetailsid()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPaymentdetailsid()))) {
				errors.add(new Error("01", "Policy Payment Details Id", "Please Enter Policy Payment Details Id"));
			}
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("02", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getBankName() == null || StringUtils.isBlank(req.getBankName())) {
				errors.add(new Error("03", "Bank Name", "Please Enter Bank Name"));
			}
			if (req.getPaymentAmount() == null || StringUtils.isBlank(req.getPaymentAmount())) {
				errors.add(new Error("04", "Payment Amount", "Please Enter Payment Amount"));
			}
			if (req.getPaymentCollectedDate().toString() == null
					|| StringUtils.isBlank(req.getPaymentCollectedDate().toString())) {
				errors.add(new Error("05", "Payment Collected Date", "Please Enter Payment Collected Date"));
			}
			if (req.getPaymentDate().toString() == null || StringUtils.isBlank(req.getPaymentDate().toString())) {
				errors.add(new Error("06", "Payment Date", "Please Enter Payment Date"));
			}
			if (req.getPaymentRefNo() == null || StringUtils.isBlank(req.getPaymentRefNo())) {
				errors.add(new Error("07", "Payment Ref No", "Please Enter Payment Ref No"));

			}
			if (req.getPaymentType() == null || StringUtils.isBlank(req.getPaymentType())) {
				errors.add(new Error("08", "Payment Type", "Please Enter Payment  Type"));
			}

			if (Integer.valueOf(req.getPaymentTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPaymentTypeId()))) {
				errors.add(new Error("09", "Payment Type Id", "Please Enter Payment Type Id"));

			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("10", "Remarks", "Please Enter Remarks"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

// Policy Payment Save
	@Override
	public PolicySuccessRes savePolicyPayment(PolicyPaymentDetailsSaveReq req) {
		PolicySuccessRes res = new PolicySuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			PolicyPaymentDetailsId id = new PolicyPaymentDetailsId();

			id.setPaymentdetailsid(req.getPaymentdetailsid());
			id.setPolicyId(req.getPolicyId());
			Date entryDate = null;
			Optional<PolicyPaymentDetails> data = policypaymentdetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				PolicyPaymentDetails ent = mapper.map(req, PolicyPaymentDetails.class);
				ent.setPolicyPaymentDetailId(req.getPolicyPaymentDetailId());
				ent.setEntryDate(req.getEntryDate());
				policypaymentdetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<PolicyPaymentDetails> list = policypaymentdetailsrepository
						.findAllByOrderByPolicyPaymentDetailIdDesc();
				Integer policyPaymentDetailId = 1000;

				if (list.size() != 0) {
					policyPaymentDetailId = list.get(0).getPolicyPaymentDetailId() + 1;
				}
				PolicyPaymentDetails ent = mapper.map(req, PolicyPaymentDetails.class);
				ent.setPolicyPaymentDetailId(policyPaymentDetailId);
				ent.setEntryDate(new Date());
				policypaymentdetailsrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {

			log.error(ex);
			return null;
		}
		return res;
	}

// Policy Payment Get Req
	@Override
	public PolicyPaymentDetailsRes getPolicyPayment(PolicyPaymentDetailsGetReq req) {
		PolicyPaymentDetailsRes res = new PolicyPaymentDetailsRes();
		try {

			PolicyPaymentDetailsId id = new PolicyPaymentDetailsId();
			id.setPaymentdetailsid(req.getPaymentdetailsid());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyPaymentDetails> opt = policypaymentdetailsrepository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				res = modelMapper.map(opt.get(), PolicyPaymentDetailsRes.class);

			}
		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

// Policy Details Validation
	@Override
	public List<Error> validatePolicyDetails(PolicyDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getBusinessTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getBusinessTypeId()))) {
				errors.add(new Error("01", "Business Type Id", "Please Enter Business Type Id"));
			}
			if (req.getBusinessType() == null || StringUtils.isBlank(req.getBusinessType())) {
				errors.add(new Error("02", "Business Type", "Please Enter Business Type"));
			}
			if (req.getAutoDebitYN() == null || StringUtils.isBlank(req.getAutoDebitYN())) {
				errors.add(new Error("03", "Auto Debit YN", "Please Enter Auto Debit YN"));
			}
			if (req.getClasss() == null || StringUtils.isBlank(req.getClasss())) {
				errors.add(new Error("04", "Classs", "Please Enter Classs"));
			}
			if (Integer.valueOf(req.getClassId()) == null || StringUtils.isBlank(Integer.toString(req.getClassId()))) {
				errors.add(new Error("05", "Class Id", "Please Enter Class Id"));
			}
			if (Integer.valueOf(req.getClientId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getClientId()))) {
				errors.add(new Error("06", "Client Id", "Please Enter Client Id"));
			}
			if (req.getClientRefNo() == null || StringUtils.isBlank(req.getClientRefNo())) {
				errors.add(new Error("07", "Client Ref No", "Please Enter Client Ref No"));
			}
			if (req.getCoInsurerDetails() == null || StringUtils.isBlank(req.getCoInsurerDetails())) {
				errors.add(new Error("08", "Co Insurer Details", "Please Enter Co Insurer Details"));
			}
			if (req.getCommencementDate().toString() == null
					|| StringUtils.isBlank(req.getCommencementDate().toString())) {
				errors.add(new Error("09", "Commencement Date", "Please Enter Commencement Date"));
			}
			if (Double.valueOf(req.getCommissionBasePremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getCommissionBasePremium()))) {
				errors.add(new Error("10", "Commission Base Premium", "Please Enter Commission Base Premium"));
			}
			if (req.getCoverNoteNumber() == null || StringUtils.isBlank(req.getCoverNoteNumber())) {
				errors.add(new Error("11", "Cover Note Number", "Please Enter Cover Note Number"));
			}
			if (Double.valueOf(req.getDiscountPercentage()) == null
					|| StringUtils.isBlank(Double.toString(req.getDiscountPercentage()))) {
				errors.add(new Error("12", "Discount Percentage", "Please Enter Discount Percentage"));
			}
			if (req.getExpiryDate().toString() == null || StringUtils.isBlank(req.getExpiryDate().toString())) {
				errors.add(new Error("13", "Expiry Date", "Please Enter Expiry Date"));
			}
			if (Double.valueOf(req.getGST()) == null || StringUtils.isBlank(Double.toString(req.getGST()))) {
				errors.add(new Error("14", "GST", "Please Enter GST"));
			}
			if (req.getInsurancePlan() == null || StringUtils.isBlank(req.getInsurancePlan())) {
				errors.add(new Error("15", "Insurance Plan", "Please Enter Insurance Plan"));
			}
			if (req.getInsurer() == null || StringUtils.isBlank(req.getInsurer())) {
				errors.add(new Error("16", "Insurer", "Please Enter Insurer"));
			}
			if (req.getInsurerBranch() == null || StringUtils.isBlank(req.getInsurerBranch())) {
				errors.add(new Error("17", "Insurer Branch", "Please Enter Insurer Branch"));
			}
			if (Integer.valueOf(req.getInsurerBranchId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getInsurerBranchId()))) {
				errors.add(new Error("18", "Insurer Branch Id", "Please Enter Insurer Branch Id"));
			}
			if (Integer.valueOf(req.getInsurerId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getInsurerId()))) {
				errors.add(new Error("19", "Insurer Id", "Please Enter Insurer Id"));
			}
			if (req.getMaturityDate().toString() == null || StringUtils.isBlank(req.getMaturityDate().toString())) {
				errors.add(new Error("20", "Maturity Date", "Please Enter Maturity Date"));
			}
			if (req.getNextPremiumDueDate().toString() == null
					|| StringUtils.isBlank(req.getNextPremiumDueDate().toString())) {
				errors.add(new Error("21", "Next Premium Due Date", "Please Enter Next Premium Due Date"));
			}
			if (Double.valueOf(req.getNoClaimBonus()) == null
					|| StringUtils.isBlank(Double.toString(req.getNoClaimBonus()))) {
				errors.add(new Error("22", "No Claim Bonus", "Please Enter No Claim Bonus"));
			}
			if (Double.valueOf(req.getOtherPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getOtherPremium()))) {
				errors.add(new Error("23", "Other Premium", "Please Enter Other Premium"));
			}
			if (Integer.valueOf(req.getPolicyTermInYears()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyTermInYears()))) {
				errors.add(new Error("24", "Policy Term In Years", "Please Enter Policy Term In Years"));
			}
			if (req.getPolicyNumber() == null || StringUtils.isBlank(req.getPolicyNumber())) {
				errors.add(new Error("25", "Policy Number", "Please Enter Policy Number"));
			}
			if (req.getPolicyType() == null || StringUtils.isBlank(req.getPolicyType())) {
				errors.add(new Error("26", "Policy Type", "Please Enter Policy Type"));
			}
			if (Integer.valueOf(req.getPolicyTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyTypeId()))) {
				errors.add(new Error("27", "Policy Type Id", "Please Enter Policy Type Id"));
			}
			if (req.getPremiumDueDate().toString() == null || StringUtils.isBlank(req.getPremiumDueDate().toString())) {
				errors.add(new Error("28", "Premium Due Date", "Please Enter Premium Due Date"));
			}

			if (Integer.valueOf(req.getPremiumPayingTermInYears()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPremiumPayingTermInYears()))) {
				errors.add(
						new Error("29", "Premium Paying Term In Years", "Please Enter Premium Paying Term In Years"));
			}
			if (req.getPremiumPaymentFrequency() == null || StringUtils.isBlank(req.getPremiumPaymentFrequency())) {
				errors.add(new Error("30", "Premium Payment Frequency", "Please Enter Premium Payment Frequency"));
			}
			if (Integer.valueOf(req.getPremiumPaymentTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPremiumPaymentTypeId()))) {
				errors.add(new Error("31", "Premium Payment Type Id", "Please Enter Premium Payment Type Id"));
			}
			if (req.getPremiumPaymentType() == null || StringUtils.isBlank(req.getPremiumPaymentType())) {
				errors.add(new Error("32", "PremiumPaymentType", "Please Enter Premium Payment Type"));
			}
			if (Double.valueOf(req.getPremiumWithGST()) == null
					|| StringUtils.isBlank(Double.toString(req.getPremiumWithGST()))) {
				errors.add(new Error("33", "Premium With GST", "Please Enter Premium With GST"));
			}
			if (req.getStartDate().toString() == null || StringUtils.isBlank(req.getStartDate().toString())) {
				errors.add(new Error("34", "Start Date", "Please Enter Start Date"));
			}
			if (req.getRenewableFlagYN() == null || StringUtils.isBlank(req.getRenewableFlagYN())) {
				errors.add(new Error("35", "Renewable Flag YN", "Please Enter Renewable Flag YN"));
			}
			if (Double.valueOf(req.getSumInsured()) == null
					|| StringUtils.isBlank(Double.toString(req.getSumInsured()))) {
				errors.add(new Error("36", "Sum Insured", "Please Enter Sum Insured"));
			}
			if (Double.valueOf(req.getTotalPremium()) == null
					|| StringUtils.isBlank(Double.toString(req.getTotalPremium()))) {
				errors.add(new Error("37", "Total Premium", "Please Enter Total Premium"));
			}
			if (Integer.valueOf(req.getVehicleId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getVehicleId()))) {
				errors.add(new Error("38", "Vehicle Id", "Please Enter Vehicle Id"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	// Policy Additional Details Validation
	@Override
	public List<Error> validatePolicyAdditional(PolicyAdditionalDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (Integer.valueOf(req.getPolicyId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPolicyId()))) {
				errors.add(new Error("01", "Policy Id", "Please Enter Policy Id"));
			}
			if (req.getCommissionGeneration() == null || StringUtils.isBlank(req.getCommissionGeneration())) {
				errors.add(new Error("02", "Commission Generation", "Please Enter Commission Generation"));
			}
			if (Integer.valueOf(req.getCommissionGenerationId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getCommissionGenerationId()))) {
				errors.add(new Error("03", "Commission Generation Id", "Please Enter Commission Generation Id"));
			}
			if (req.getDeductibleDetails() == null || StringUtils.isBlank(req.getDeductibleDetails())) {
				errors.add(new Error("04", "Deductible Details", "Please Enter Deductible Details"));
			}
			if (Integer.valueOf(req.getFileTypeId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getFileTypeId()))) {
				errors.add(new Error("05", "File Type Id", "Please Enter File Type Id"));
			}
			if (req.getFileType() == null || StringUtils.isBlank(req.getFileType())) {
				errors.add(new Error("06", "File Type", "PLease Enter File Type"));
			}
			if (req.getIssueDate().toString() == null || StringUtils.isBlank(req.getIssueDate().toString())) {
				errors.add(new Error("07", "Issue Date", "Please Enter Issue Date"));

			}
			if (req.getLocation() == null || StringUtils.isBlank(req.getLocation())) {
				errors.add(new Error("08", "Location", "Please Enter Location"));

			}
			if (req.getOtherReferenceNumber() == null || StringUtils.isBlank(req.getOtherReferenceNumber())) {
				errors.add(new Error("09", "Other Reference Number", "Please Enter Other Reference Number"));
			}
			if (req.getPolicyAdditionalInformation() == null
					|| StringUtils.isBlank(req.getPolicyAdditionalInformation())) {
				errors.add(new Error("10", "Additional Information", "Please Enter Policy Additional Information"));
			}
			if (req.getPolicyCancelledYn() == null || StringUtils.isBlank(req.getPolicyCancelledYn())) {
				errors.add(new Error("11", "Policy Cancelled YN", "Please Enter Policy Cancelled YN"));
			}
			if (req.getPOS() == null || StringUtils.isBlank(req.getPOS())) {
				errors.add(new Error("12", "POS", "Please Enter POS"));
			}

			if (Integer.valueOf(req.getPOSID()) == null || StringUtils.isBlank(Integer.toString(req.getPOSID()))) {
				errors.add(new Error("13", "POS Id", "Please Enter POS Id"));
			}

			if (req.getPreviousInsurancePlan() == null || StringUtils.isBlank(req.getPreviousInsurancePlan())) {
				errors.add(new Error("14", "Previous Insurance Plan", "Please Enter Previous Insurance Plan"));
			}

			if (req.getPreviousInsurer() == null || StringUtils.isBlank(req.getPreviousInsurer())) {
				errors.add(new Error("15", "Previous Insurer", "Please Enter Previous Insurer"));
			}
			if (Integer.valueOf(req.getPreviousInsurerId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPreviousInsurerId()))) {
				errors.add(new Error("16", "Previous Insurer Id", "Please Enter Previous Insurer Id"));
			}
			if (req.getPreviousPolicyNo() == null || StringUtils.isBlank(req.getPreviousPolicyNo())) {
				errors.add(new Error("17", "Previous Policy No", "Please Enter Previous Policy No"));
			}
			if (req.getPreviousPOS() == null || StringUtils.isBlank(req.getPreviousPOS())) {
				errors.add(new Error("18", "Previous POS", "Please Enter Previous POS"));
			}
			if (Integer.valueOf(req.getPreviousPOSId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPreviousPOSId()))) {
				errors.add(new Error("19", "Previous POS Id", "Please Enter Previous POS Id"));
			}
			if (req.getPreviousSource() == null || StringUtils.isBlank(req.getPreviousSource())) {
				errors.add(new Error("20", "Previous Source", "Please Enter Previous Source"));
			}
			if (Integer.valueOf(req.getPreviousSourceId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getPreviousSourceId()))) {
				errors.add(new Error("21", "Previous Source Id", "Please Enter Previous Source Id"));
			}
			if (req.getUser() == null || StringUtils.isBlank(req.getUser())) {
				errors.add(new Error("22", "User", "Please Enter User"));
			}
			if (Integer.valueOf(req.getUserId()) == null || StringUtils.isBlank(Integer.toString(req.getUserId()))) {
				errors.add(new Error("23", "User Id", "Please Enter User Id"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public SuccessRes savePolicyDetails(PolicyDetailsSaveReq req) {

		SuccessRes res = new SuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {

			PolicyDetails data = policydetailsrep.findByPolicyidOrderByPolicyidAsc(req.getPolicyid());
			ClientDetails clientdetails = clientrepo.findByClientRefNo(req.getClientRefNo());
			InsuranceCompanyMaster companyData = insRepo.findByInsId(clientdetails.getInsCompanyId());
			ClaimLoginMaster loginData = loginRepo.findByLoginId(clientdetails.getCreatedBy());

			PolicyDetailsId id = new PolicyDetailsId();
			Integer policyid = 0;
			Date entryDate = new Date();
			if (data != null) {
				// Update
				policyid = req.getPolicyid();
				entryDate = req.getEntryDate();
				PolicyDetails ent = mapper.map(req, PolicyDetails.class);

				policydetailsrep.save(ent);
				res.setResponse("Updated Successfully ");
				res.setSucessId(policyid.toString());
				
				// Tracking
				TrackingReq treq = new TrackingReq();
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(clientdetails.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientdetails.getClientName());
				treq.setStatus("Policy");
				treq.setStatusDescription("Policy Updated Successful");
				treq.setPolicyId(policyid.toString());
				
				trackService.tracking(treq);

			} else {
				// Save
				policyid = 1000;
				Integer count = Integer.valueOf(String.valueOf(policydetailsrep.count()));
				policyid = 1000 + count;

				PolicyDetails ent = mapper.map(req, PolicyDetails.class);
				ent.setPolicyid(policyid);
				ent.setEntryDate(new Date());
				policydetailsrep.saveAndFlush(ent);
				res.setResponse("Inserted Successfully ");
				res.setSucessId(policyid.toString());

				// Tracking
				TrackingReq treq = new TrackingReq();
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(clientdetails.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientdetails.getClientName());
				treq.setStatus("Policy");
				treq.setStatusDescription("Policy Inserted Successful");
				treq.setPolicyId(policyid.toString());
				
				trackService.tracking(treq);

			}

			// Thread to Trigger Mail
			clientdetails.setLastVisitedDate(new Date());
			clientrepo.saveAndFlush(clientdetails);
			

			List<String> ccMails = new ArrayList<String>();
			ccMails.add(companyData.getInsEmail());
			ccMails.add(loginData.getUserMail());

			List<String> toMails = new ArrayList<String>();
			toMails.add(clientdetails.getEmailId());

			Map<String, Object> keys = new HashMap<String, Object>();
			keys.put("POLICYID", Integer.valueOf(policyid) == null ? "" : policyid);

			// Set Mail Request

			MailFramingReq mailFrameReq = new MailFramingReq();
			mailFrameReq.setInsId(clientdetails.getInsCompanyId());
			mailFrameReq.setNotifTemplateId("POLICY_INFO");
			mailFrameReq.setKeys(keys);
			mailFrameReq.setMailCc(ccMails);
			mailFrameReq.setMailRegards(companyData.getRegards());
			mailFrameReq.setStatus(res.getResponse());
			mailFrameReq.setMailTo(toMails);
			log.info("{ Mail Pushed Successfully . Policy Id is --->" + policyid + "}");

			mailThreadService.threadToSendMail(mailFrameReq);

	
		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;

	}
	// Policy Additional Details Save

	@Override
	public PolicySuccessRes savePolicyAdditional(PolicyAdditionalDetailsSaveReq req) {
		PolicySuccessRes res = new PolicySuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			PolicyAdditionalDetailsId id = new PolicyAdditionalDetailsId();
			id.setAdditionalDetailsId(req.getAdditionalDetailsId());
			id.setPolicyId(req.getPolicyId());
			Date entryDate = null;
			Optional<PolicyAdditionalDetails> data = policyadditionaldetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				PolicyAdditionalDetails ent = mapper.map(req, PolicyAdditionalDetails.class);
				ent.setAdditionalDetailsId(req.getAdditionalDetailsId());
				ent.setEntryDate(req.getEntryDate());
				policyadditionaldetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<PolicyAdditionalDetails> list = policyadditionaldetailsrepository
						.findAllByOrderByAdditionalDetailsIdDesc();
				Integer additionalDetailsId = 1000;

				if (list.size() != 0) {
					additionalDetailsId = list.get(0).getAdditionalDetailsId() + 1;
				}
				PolicyAdditionalDetails ent = mapper.map(req, PolicyAdditionalDetails.class);
				ent.setAdditionalDetailsId(additionalDetailsId);
				ent.setEntryDate(new Date());
				policyadditionaldetailsrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;

	}

	@Override
	public PolicyPaymentDetailsRes savePolicyDetails(PolicyDetailsRes req) {

		PolicyPaymentDetailsRes res = new PolicyPaymentDetailsRes();
		ModelMapper mapper = new ModelMapper();
		try {
			PolicyPaymentDetailsId id = new PolicyPaymentDetailsId();
			Optional<PolicyPaymentDetails> data = policypaymentdetailsrepository.findById(id);
			if (data.isPresent()) {
				res = mapper.map(data.get(), PolicyPaymentDetailsRes.class);
			}
		} catch (Exception e) {
			res = null;
		}
		return res;

	}

	// Policy Additional Details Get

	@Override
	public PolicyAdditionalDetailsRes getPolicyAdditional(PolicyAdditionalDetailsGetReq req) {
		PolicyAdditionalDetailsRes res = new PolicyAdditionalDetailsRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			PolicyAdditionalDetailsId id = new PolicyAdditionalDetailsId();
			id.setAdditionalDetailsId(req.getAdditionalDetailsId());
			id.setPolicyId(req.getPolicyId());

			Optional<PolicyAdditionalDetails> opt = policyadditionaldetailsrepository.findById(id);

			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				PolicyAdditionalDetails a = opt.get();
				res.setAdditionalDetailsId(Integer.valueOf(a.getAdditionalDetailsId().toString()));
				res.setCommissionGeneration(a.getCommissionGeneration());
				res.setCommissionGenerationId(Integer.valueOf(a.getCommissionGenerationId().toString()));
				res.setDeductibleDetails(a.getDeductibleDetails());
				res.setEntryDate(a.getEntryDate());
				res.setFileType(a.getFileType());
				res.setFileTypeId(Integer.valueOf(a.getFileTypeId()));
				res.setIssueDate(a.getIssueDate());
				res.setLocation(a.getLocation());
				res.setOtherReferenceNumber(a.getOtherReferenceNumber());
				res.setPolicyAdditionalInformation(a.getPolicyAdditionalInformation());
				res.setPolicyCancelledYn(a.getPolicyCancelledYn());
				res.setPolicyCancelledYnBy(a.getPolicyCancelledYnBy());
				res.setPolicyId(Integer.valueOf(a.getPolicyId()));
				res.setPolicyReceivedYN(a.getPolicyReceivedYN());
				res.setPolicyStatus(a.getPolicyStatus());
				res.setPolicyStatusId(Integer.valueOf(a.getPolicyStatusId()));
				res.setPolicyVerifiedBy(a.getPolicyVerifiedBy());
				res.setPolicyVerifiedYN(a.getPolicyVerifiedYN());
				res.setPOS(a.getPOS());
				res.setPOSID(Integer.valueOf(a.getPOSId()));
				res.setPreviousInsurancePlan(a.getPreviousInsurancePlan());
				res.setPreviousInsurer(a.getPreviousInsurer());
				res.setPreviousPolicyNo(a.getPreviousPolicyNo());
				res.setPreviousPOS(a.getPreviousPOS());
				res.setPreviousPOSId(Integer.valueOf(a.getPreviousPOSId()));
				res.setPreviousSource(a.getPreviousSource());
				res.setPreviousSourceId(Integer.valueOf(a.getPreviousSourceId()));
				res.setPrviousInsurerId(Integer.valueOf(a.getPreviousInsurerId()));
				res.setReferenceNumber(a.getReferenceNumber());
				res.setSource(a.getSource());
				res.setSourceId(Integer.valueOf(a.getSourceId()));
				res.setUser(a.getUser());
				res.setUserId(Integer.valueOf(a.getUserId()));

			}
		} catch (Exception e) {

			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	@Override
	public PolicyRes getPolicy(PolicyReq req) {
		PolicyRes res = new PolicyRes();
		try {

			List<PolicyAddOn> opt1 = policyaddonrepository.findByPolicyid(req.getPolicyId());
			if (!opt1.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyAddOn(modelMapper.map(opt1.get(0), PolicyAddOnRes.class));
			}

			List<PolicyPaymentDetails> opt2 = policypaymentdetailsrepository.findByPolicyId(req.getPolicyId());
			if (!opt2.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyPaymentDetails(modelMapper.map(opt2.get(0), PolicyPaymentDetailsRes.class));
			}

			List<PolicyNomineeDetails> opt3 = policydetailsrepository.findByPolicyId(req.getPolicyId());
			if (!opt3.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyNomineeDetails(modelMapper.map(opt3.get(0), PolicyNomineeDetailsRes.class));
			}

			List<PolicyAdditionalDetails> opt4 = policyadditionaldetailsrepository.findByPolicyId(req.getPolicyId());
			if (!opt4.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setAmbiguityIgnored(true);
				res.setPolicyAdditionalDetails(modelMapper.map(opt4.get(0), PolicyAdditionalDetailsRes.class));
			}

			List<PolicyDetails> opt = policydetailsrep.findByPolicyid(req.getPolicyId());
			if (!opt.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyDetails(modelMapper.map(opt.get(0), PolicyDetailsRes.class));
			}

			List<PolicyRiderDetails> opt5 = policyriderrepository.findByPolicyId(req.getPolicyId());
			if (!opt5.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyRiderDetails(modelMapper.map(opt5.get(0), PolicyRiderDetailsRes.class));
			}

			List<PolicyAssuredDetails> opt6 = policyassuredrepository.findByPolicyId(req.getPolicyId());
			if (!opt6.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyAssuredDetails(modelMapper.map(opt6.get(0), PolicyAssuredDetailsRes.class));
				modelMapper.getConfiguration().setAmbiguityIgnored(true);

			}

			List<PolicyAccountsDetails> opt7 = policyaccountrepository.findByPolicyid(req.getPolicyId());
			if (!opt7.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPolicyAccountDetails(modelMapper.map(opt7.get(0), PolicyAccountsDetailsRes.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			res = null;

		}
		return res;
	}

	// Policy Bulk Edit
	@Override
	public List<Error> validatePolicy(PolicyBulkEditReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessRes bulkEditPolicy(PolicyBulkEditReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	// Get All Policy Details
	@Override
	public PolicyGetAllCountRes getAllPolicyDetails(PolicyDetailsGetAllReq req) {
		PolicyGetAllCountRes resList = new PolicyGetAllCountRes();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<PolicyDetails> policyDetails = policydetailsrep.findByInsCompanyIdAndBranchCode(paging,
					req.getInsCompanyId(), req.getBranchCode());
			// Map
			List<PolicyDetailsGetAllRes> policylist = new ArrayList<PolicyDetailsGetAllRes>();
			for (PolicyDetails data : policyDetails.getContent()) {
				PolicyDetailsGetAllRes res = new PolicyDetailsGetAllRes();
				res = mapper.map(data, PolicyDetailsGetAllRes.class);
				policylist.add(res);
			}
			Long count = policydetailsrep.countByInsCompanyIdAndBranchCode(req.getInsCompanyId(), req.getBranchCode());
			resList.setPolicyDetails(policylist);
			resList.setPolicyCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Policy Nominee Details Get All Req
	@Override
	public List<PolicyNomineeDetailsGetAllRes> getallPolicyNomineeDetails(PolicyNomineeDetailsGetAllReq req) {
		List<PolicyNomineeDetailsGetAllRes> resList = new ArrayList<PolicyNomineeDetailsGetAllRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit().toString()) ? 0 : req.getLimit();
			int offset = StringUtils.isBlank(req.getOffset().toString()) ? 10 : req.getOffset();
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find

			Page<PolicyNomineeDetails> nomineeDetails = policydetailsrepository.findByNomineeIdAndPolicyId(paging,
					req.getNomineeId(), req.getPolicyId());

			// Map
			for (PolicyNomineeDetails data : nomineeDetails.getContent()) {
				PolicyNomineeDetailsGetAllRes res = new PolicyNomineeDetailsGetAllRes();
				res = mapper.map(data, PolicyNomineeDetailsGetAllRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<PolicyAddOnGetAllRes> getallPolicyAddon(PolicyAddOnGetAllReq req) {
		List<PolicyAddOnGetAllRes> resList = new ArrayList<PolicyAddOnGetAllRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("addOnid").descending());
			// Find
			Page<PolicyAddOn> policyaddon = policyaddonrepository.findByAddOnidAndPolicyid(paging, req.getAddOnid(),
					req.getPolicyid());
			// Map
			for (PolicyAddOn data : policyaddon.getContent()) {
				PolicyAddOnGetAllRes res = new PolicyAddOnGetAllRes();
				res = mapper.map(data, PolicyAddOnGetAllRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<PolicyPaymentGetAllRes> getallPolicyPayment(PolicyPaymentGetAllReq req) {
		List<PolicyPaymentGetAllRes> resList = new ArrayList<PolicyPaymentGetAllRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit().toString()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset().toString()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			Page<PolicyPaymentDetails> policypayment = policypaymentdetailsrepository
					.findByPolicyIdAndPaymentdetailsid(paging, req.getPolicyId(), req.getPaymentdetailsid());
			for (PolicyPaymentDetails data : policypayment.getContent()) {
				PolicyPaymentGetAllRes res = new PolicyPaymentGetAllRes();
				res = mapper.map(data, PolicyPaymentGetAllRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public List<PolicyAdditionalDetailsGetAllRes> getallPolicyAdditionalDetails(PolicyAdditionalDetailsGetAllReq req) {
		List<PolicyAdditionalDetailsGetAllRes> resList = new ArrayList<PolicyAdditionalDetailsGetAllRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit().toString()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset().toString()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			Page<PolicyAdditionalDetails> policyadditional = policyadditionaldetailsrepository
					.findByAdditionalDetailsIdAndPolicyId(paging, req.getAdditionalDetailsId(), req.getPolicyId());
			for (PolicyAdditionalDetails data : policyadditional) {
				PolicyAdditionalDetailsGetAllRes res = new PolicyAdditionalDetailsGetAllRes();
				res = mapper.map(data, PolicyAdditionalDetailsGetAllRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public PolicySearchRes searchPolicyDetails(PolicySearchReq req) {
		PolicySearchRes res = new PolicySearchRes();
		ModelMapper mapper = new ModelMapper();
		List<PolicyDetails> policydetails = new ArrayList<>();
		try {
			String searchvalue = req.getSearchValue();
			if (StringUtils.isNotBlank(searchvalue) && searchvalue.matches("[0-9]+") && searchvalue.length() <= 5) {
				policydetails = Criteria.PolicyDynamicSearch1(req);
			}
			if (policydetails.size() <= 0) {
				policydetails = Criteria.PolicyDynamicSearch2(req);
			}
			if (policydetails != null && policydetails.size() > 0) {
				List<PolicyDetailsGetAllRes> policyres = new ArrayList<PolicyDetailsGetAllRes>();
				for (PolicyDetails data : policydetails) {
					PolicyDetailsGetAllRes resa = new PolicyDetailsGetAllRes();
					resa = mapper.map(data, PolicyDetailsGetAllRes.class);

					policyres.add(resa);
				}
				res.setProspectDetails(policyres);
				;
				res.setPolicyCount(Long.valueOf(policydetails.size()));
			}
		} catch (Exception e) {
			e.getStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return res;
	}
	/*
	 * @Override public SuccessRes listPolicyDetails(List<PolicyAddOnSaveReq> req) {
	 * 
	 * SuccessRes res = new SuccessRes(); ModelMapper mapper = new ModelMapper();
	 * try { for(PolicyAddOnSaveReq data : req) {
	 * 
	 * PolicyAddOn policy = new PolicyAddOn(); policy = mapper.map(data,
	 * PolicyAddOn.class); policyaddonrepository.save(policy);
	 * 
	 * } res.setResponse("Suucess"); } catch (Exception e ) { e.printStackTrace();
	 * log.info("Log Details",e.getMessage()); return null; } return res; }
	 */
}
