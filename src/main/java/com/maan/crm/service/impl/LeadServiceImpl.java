package com.maan.crm.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.auth.token.EncryDecryService;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.CrmRedirectDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.LeadDetailsId;
import com.maan.crm.bean.OldPolicyDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.bean.VehicleDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.CrmRedirectDetailsRepository;
import com.maan.crm.repository.EnquiryRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.repository.OldPolicyRepository;
import com.maan.crm.repository.PolicyDetailsRepository;
import com.maan.crm.repository.ProspectDetailsRepository;
import com.maan.crm.repository.ProspectRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.repository.TrackingDetailsRepository;
import com.maan.crm.repository.VehicleDetailsRepository;
import com.maan.crm.req.ClientBasicDetails;
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
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.TrackingReq;
import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientsLeadCountRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.CrmReDirectLinkRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.LeadDetailsJsonTempRes;
import com.maan.crm.res.LeadGetAllCountRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.LeadSearchRes;
import com.maan.crm.res.LeadViewRes;
import com.maan.crm.res.OldPolicyRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.PolicyDetailsRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsGridRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.service.LeadService;
import com.maan.crm.service.VehicleDetailsService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class LeadServiceImpl implements LeadService {

	@Autowired
	private EnquiryRepository enquiryRepo;
	
	@Autowired
	private LeadRepository repository;

	@Autowired
	private VehicleDetailsRepository vehRepo;

	@Autowired
	private OldPolicyRepository repo;

	@Autowired
	private ClientAddressDetailsRepository clientAddRepo;

	@Autowired
	private ProspectDetailsRepository prospectdetailsrepositoryl;

	@Autowired
	private CrmRedirectDetailsRepository redirectRepo;

	@Autowired
	private InsuranceCompanyMasterRepository insRepo;

	@Autowired
	private ClientAddressDetailsRepository clientAddrRepo;

	@Autowired
	private MailThreadServiceImpl mailThreadService;

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private TrackingDetailsRepository trackRepo;

	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	@Autowired
	private ClaimLoginMasterRepository loginRepo;

	@Autowired
	private CriteriaQueryServiceImpl Criteria;

	@Autowired
	private EncryDecryService encrypt;

	@Autowired
	private QuoteDetailsRepository quoteRepo;

	@Autowired
	private ProspectRepository prosRepo;

	@Autowired
	private TrackingServiceImpl trackService;

	@Autowired
	private PolicyDetailsRepository policyRepo;

	private Logger log = LogManager.getLogger(LeadServiceImpl.class);

	// Validation
	@Override
	public List<Error> validateCrmLead(LeadDetailsJsonTempReq tempReq) {
		CrmLeadSaveReq req = tempReq.getLeadDetails();
		List<Error> errors = new ArrayList<Error>();
		try {
			/*
			 * if (StringUtils.isNotBlank(req.getLeadId().toString())) { if
			 * (!StringUtils.isNumeric(req.getLeadId().toString())) { errors.add(new
			 * Error("01", "Lead Id", "Please Enter Valid Lead Id ")); } } if
			 * (req.getClientRefNo() == null || StringUtils.isBlank(req.getClientRefNo())) {
			 * errors.add(new Error("02", "Client Ref No", "Please Enter Client Ref No")); }
			 * else if (req.getClientRefNo().length() > 50) { errors.add(new Error("02",
			 * "Client Ref No", "Please Enter Client Ref No within 50 Characters")); } if
			 * (req.getBusinessType() == null || StringUtils.isBlank(req.getBusinessType()))
			 * { errors.add(new Error("03", "Business Type", "Please Enter Business Type"));
			 * } else if (req.getBusinessType().length() > 100) { errors.add(new Error("03",
			 * "Business Type", "Please Enter Business Type within 100 Characters")); } if
			 * (req.getBusinessTypeId() == null ||
			 * StringUtils.isBlank(req.getBusinessTypeId().toString())) { errors.add(new
			 * Error("04", "Business Type Id", "Please Enter Business Type Id")); } if
			 * (req.getAssigntoGroup() == null ||
			 * StringUtils.isBlank(req.getAssigntoGroup())) { errors.add(new Error("05",
			 * "Assign To Group", "Please Enter Assign to Group")); } else if
			 * (req.getAssigntoGroup().length() > 100) { errors.add(new Error("05",
			 * "Assign to Group", "Please Enter Assign to Group within 100 Characters")); }
			 * if (req.getAssigntoGroupId() == null ||
			 * StringUtils.isBlank(req.getAssigntoGroupId().toString())) { errors.add(new
			 * Error("06", "Assign To Group Id", "Please Enter Assign To Group Id")); } else
			 * if (!StringUtils.isNumeric(req.getAssigntoGroupId().toString())) {
			 * errors.add(new Error("06", "Assign To Group Id",
			 * "Please Enter Assign To Group Id in numbers")); }
			 * 
			 * if (req.getAssigntoUser() == null ||
			 * StringUtils.isBlank(req.getAssigntoUser())) { errors.add(new Error("07",
			 * "Assign To User", "Please Enter Assign to User")); } else if
			 * (req.getAssigntoUser().length() > 100) { errors.add(new Error("07",
			 * "Assign to User", "Please Enter Assign to User within 100 Characters")); } if
			 * (req.getAssigntoUserId() == null ||
			 * StringUtils.isBlank(req.getAssigntoUserId().toString())) { errors.add(new
			 * Error("08", "Assign To User Id", "Please Enter Assign To User Id")); } else
			 * if (!StringUtils.isNumeric(req.getAssigntoUserId().toString())) {
			 * errors.add(new Error("08", "Assign To User Id",
			 * "Please Enter Assign To User Id in numbers")); } if (req.getBrokenPolicy() ==
			 * null || StringUtils.isBlank(req.getBrokenPolicy())) { errors.add(new
			 * Error("09", "Broken Policy", "Please Enter Broken Policy")); } else if
			 * (req.getBrokenPolicy().length() > 100) { errors.add(new Error("09",
			 * "Broken Policy", "Please Enter Broken Policy within 100 Characters")); } if
			 * (req.getClassDesc() == null || StringUtils.isBlank(req.getClassDesc())) {
			 * errors.add(new Error("10", "Class Desc", "Please Enter Class Desc")); } else
			 * if (req.getClassDesc().length() > 100) { errors.add(new Error("10",
			 * "Class Desc", "Please Enter Class Desc within 100 Characters")); } if
			 * (req.getClassId() == null ||
			 * StringUtils.isBlank(req.getClassId().toString())) { errors.add(new
			 * Error("11", "Class Id", "Please Enter Class Id")); } else if
			 * (!StringUtils.isNumeric(req.getClassId().toString())) { errors.add(new
			 * Error("12", "Class Id", "Please Enter Class Id in numbers")); } if
			 * (req.getClassification() == null ||
			 * StringUtils.isBlank(req.getClassification())) { errors.add(new Error("12",
			 * "Classification", "Please Enter Classification")); } else if
			 * (req.getClassification().length() > 100) { errors.add(new Error("12",
			 * "Classification", "Please Enter Classification within 100 Characters")); }
			 * 
			 * if (req.getClassificationId() == null ||
			 * StringUtils.isBlank(req.getClassificationId().toString())) { errors.add(new
			 * Error("13", "Classification Id", "Please Enter Classification Id")); } else
			 * if (!StringUtils.isNumeric(req.getClassificationId().toString())) {
			 * errors.add(new Error("13", "Classification Id",
			 * "Please Classification Id in numbers")); } Date today = new Date(); if
			 * (req.getDueDate() == null ||
			 * StringUtils.isBlank(req.getDueDate().toString())) { errors.add(new
			 * Error("14", "Due Date", "Please Enter Due Date")); } else if
			 * (!req.getDueDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			 * errors.add(new Error("14", "Due Date",
			 * "Due Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"))
			 * ; } else if (req.getDueDate().before(today)) { errors.add(new Error("14",
			 * "Due Date", "Please Enter Due Date as Future Date" )); } if
			 * (req.getLeadGenDate() == null ||
			 * StringUtils.isBlank(req.getLeadGenDate().toString())) { errors.add(new
			 * Error("15", "Lead Gen Date", "Please Enter Lead Gen Date")); } else if
			 * (!req.getLeadGenDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")
			 * ) { errors.add(new Error("15", "Lead Gen Date",
			 * "Lead Gen Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"
			 * )); } if (req.getOthertype() == null ||
			 * StringUtils.isBlank(req.getOthertype())) { errors.add(new Error("16",
			 * "Other Type", "Please Enter Other Type")); } else if
			 * (req.getOthertype().length() > 100) { errors.add(new Error("16",
			 * "Other Type", "Please Enter Other Type within 100 Characters")); } if
			 * (req.getOthertypeId() == null ||
			 * StringUtils.isBlank(req.getOthertypeId().toString())) { errors.add(new
			 * Error("17", "Other Type Id", "Please Enter Other Type Id")); } if
			 * (req.getPolicyType() == null || StringUtils.isBlank(req.getPolicyType())) {
			 * errors.add(new Error("18", "Policy Type", "Please Enter Policy Type")); }
			 * else if (req.getPolicyType().length() > 100) { errors.add(new Error("18",
			 * "Policy Type", "Please Enter Policy Type within 100 Characters")); } if
			 * (req.getPolicyTypeId() == null ||
			 * StringUtils.isBlank(req.getPolicyTypeId().toString())) { errors.add(new
			 * Error("19", "Policy Type Id", "Please Enter Policy Type Id")); } else if
			 * (!StringUtils.isNumeric(req.getPolicyTypeId().toString())) { errors.add(new
			 * Error("19", "Policy Type Id", "Please Enter Policy  Type Id in numbers")); }
			 * if (req.getPos() == null || StringUtils.isBlank(req.getPos())) {
			 * errors.add(new Error("20", "POS", "Please Enter POS")); } else if
			 * (req.getPos().length() > 100) { errors.add(new Error("20", "POS",
			 * "Please Enter POS within 100 Characters")); } if (req.getPosId() == null ||
			 * StringUtils.isBlank(req.getPosId().toString())) { errors.add(new Error("21",
			 * "POS Id", "Please Enter POS Id")); } else if
			 * (!StringUtils.isNumeric(req.getPosId().toString())) { errors.add(new
			 * Error("21", "Assign To POS Id", "Please Enter Assign To POS Id in numbers"));
			 * } if (req.getReferenceName() == null ||
			 * StringUtils.isBlank(req.getReferenceName())) { errors.add(new Error("22",
			 * "Reference Name", "Please Enter Reference Name")); } else if
			 * (req.getReferenceName().length() > 100) { errors.add(new Error("22",
			 * "Reference Name", "Please Enter Reference Name within 100 Characters")); } if
			 * (req.getReferredby() == null || StringUtils.isBlank(req.getReferredby())) {
			 * errors.add(new Error("23", "ReferredBy", "Please Enter Referred By")); } else
			 * if (req.getReferredby().length() > 100) { errors.add(new Error("23",
			 * "ReferredBy", "Please Enter ReferredBy within 100 Characters")); } if
			 * (req.getReferredbyId() == null || StringUtils.isBlank(req.getReferredbyId()))
			 * { errors.add(new Error("24", "Referred By Id",
			 * "Please Enter Referred By Id")); } else if
			 * (!StringUtils.isNumeric(req.getReferredbyId())) { errors.add(new Error("24",
			 * "Referred By Id", "Please Enter Referred By Id in numbers")); } if
			 * (req.getRemarks().length() > 100) { errors.add(new Error("25", "Remarks",
			 * "Please Enter Remarks  within 100 Characters")); } if (req.getSource() ==
			 * null || StringUtils.isBlank(req.getSource())) { errors.add(new Error("26",
			 * "Source", "Please Enter Source")); } else if (req.getSource().length() > 100)
			 * { errors.add(new Error("26", "Source",
			 * "Please Enter Source within 100 Characters")); } if (req.getSourceId() ==
			 * null || StringUtils.isBlank(req.getSourceId().toString())) { errors.add(new
			 * Error("27", "Source Id", "Please Enter Source Id")); } else if
			 * (!StringUtils.isNumeric(req.getSourceId().toString())) { errors.add(new
			 * Error("27", "Source Id", "Please Enter Source Id in numbers")); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

// Save

	@Override
	@Transactional
	public CrmLeadSuccessRes saveCrmLead(LeadDetailsJsonTempReq tempReq) {
		CrmLeadSaveReq req = tempReq.getLeadDetails();
		CrmLeadSuccessRes res = new CrmLeadSuccessRes();
		LeadDetails entity = new LeadDetails();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		ModelMapper mapper = new ModelMapper();
		String leadId = " ";
		Date entryDate = null;
		try {
			String clientRefNo = "";
			if(StringUtils.isNotBlank(req.getClientRefNo())) {
				clientRefNo = req.getClientRefNo();
			}
			LeadDetailsId id = new LeadDetailsId();
			id.setClientRefNo(clientRefNo);
			id.setCreatedBy(req.getCreatedBy());
			id.setInsCompanyId(req.getInsCompanyId());
			id.setLeadId(req.getLeadId());
			ClientDetails clientName = clientrepo.findByClientRefNo(req.getClientRefNo());
			if(clientName ==null) {
				clientName = new ClientDetails();
			}
			Optional<LeadDetails> data1 = repository.findById(id);
			if (data1.isPresent()) {
				// Update
				LeadDetails ent = mapper.map(req, LeadDetails.class);
				ent.setStatus(data1.get().getStatus());
				ent.setLeadId(req.getLeadId());
				ent.setEntryDate(data1.get().getEntryDate());
				ent.setClientRefNo(req.getClientRefNo());
				ent.setInsCompanyId(req.getInsCompanyId());
				ent.setLeadId(req.getLeadId());
				ent.setClientName(clientName.getClientName());
				ent.setVehChassisNo(req.getVehChassisNo());
				ent.setUpdatedDate(new Date());
				ent.setUpdatedBy(req.getCreatedBy());
				ent.setUpdaterUsertype(req.getUserType());
				ent.setBusinessType(req.getBusinessType().replace("\r", "").replace("\n", ""));
				ent.setOldPolicyNo(tempReq.getOldPolicyDetails().getOldPolicyNo());
				repository.saveAndFlush(ent);
				res.setResponse("Updated Successfully ");
				res.setLeadid(req.getLeadId());

				// Tracking
				TrackingReq treq = new TrackingReq();
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(req.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientName.getClientName());
				treq.setLeadId(leadId);
				treq.setStatus("Lead");
				treq.setStatusDescription("Lead Updated Successful");
				trackService.tracking(treq);

			}
			else {

				// Insert
				Long count = repository.count();
				leadId = "L" + (Integer.valueOf(count.toString()) + 10001);

				LeadDetails ent = mapper.map(req, LeadDetails.class);
				ent.setLeadId(leadId);
				ent.setStatus("Y");
				ent.setEntryDate(new Date());
				ent.setClientName(clientName.getClientName());
				ent.setVehChassisNo(req.getVehChassisNo());
				ent.setUpdatedDate(new Date());
				ent.setUpdatedBy(req.getCreatedBy());
				ent.setUpdaterUsertype(req.getUserType());
				repository.saveAndFlush(ent);
				res.setResponse("Inserted Successfully ");
				res.setLeadid(leadId);

				log.info("Saved Details is ---> " + json.toJson(entity));

				// Tracking
				TrackingReq treq = new TrackingReq();
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(req.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientName.getClientName());
				treq.setLeadId(leadId);
				treq.setStatus("Lead");
				treq.setStatusDescription("Lead Inserted Successful");
				trackService.tracking(treq);

			}
			/*
			 * // Save Tracking SimpleDateFormat tra = new
			 * SimpleDateFormat("yyMMddhhmmssSSS");
			 * 
			 * TrackingDetails saveTracking = new TrackingDetails(); Date today = new
			 * Date(); String trackingId = tra.format(today);
			 * saveTracking.setTrackingId(trackingId);
			 * saveTracking.setInsCompanyId(req.getInsCompanyId());
			 * saveTracking.setCreatedBy(req.getCreatedBy()); //
			 * saveTracking.setRemarks(req.getRemarks()); saveTracking.setStatus("Lead");
			 * saveTracking.setEntryDate(today);
			 * 
			 * //trackRepo.save(saveTracking);
			 */
			// Thread To Trigger Mail
			ClientDetails clientDetails = clientrepo.findByClientRefNo(req.getClientRefNo());
			if(clientDetails!=null ) {
				clientDetails.setLastVisitedDate(new Date());
				clientrepo.saveAndFlush(clientDetails);
				InsuranceCompanyMaster companyData = insRepo.findByInsId(req.getInsCompanyId().toString());
				ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());

				List<String> ccMails = new ArrayList<String>();
				ccMails.add(companyData.getInsEmail());
				ccMails.add(loginData.getUserMail());

				List<String> toMails = new ArrayList<String>();
				toMails.add(clientDetails.getEmailId());

				Map<String, Object> keys = new HashMap<String, Object>();
				keys.put("LEAD_ID", leadId == null ? "" : leadId.toString());

				// Set Mail Request
				MailFramingReq mailFrameReq = new MailFramingReq();
				mailFrameReq.setInsId(req.getInsCompanyId().toString());
				mailFrameReq.setNotifTemplateId("LEAD_INFO");
				mailFrameReq.setKeys(keys);
				mailFrameReq.setMailCc(ccMails);
				mailFrameReq.setMailTo(toMails);
				mailFrameReq.setMailRegards(companyData.getRegards());
				mailFrameReq.setStatus(res.getResponse());

				log.info("{ Mail Pushed SuccessFully . LeadId is ---> " + leadId + " ; ClientRefNo is --->"
						+ req.getClientRefNo() + " }");
				// mailFrameService.sendSms(mailReq);
				mailThreadService.threadToSendMail(mailFrameReq);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return res;
		}
		return res;
	}

/// Get All Lead 
	@Override
	public List<CrmLeadRes> getAllLead(LeadDetailsGetAllReq req) {
		List<CrmLeadRes> resList = new ArrayList<CrmLeadRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 1000 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			Page<LeadDetails> leadDetails = repository.findByInsCompanyIdAndBranchCodeAndStatusOrderByEntryDateDesc(
					paging, req.getInsCompanyId(), req.getBranchCode(), "Enquiry");

			for (LeadDetails data : leadDetails) {
				CrmLeadRes res = new CrmLeadRes();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res.setClientName(data.getClientName());
				mapper.map(data, res);

				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}
	
	@Autowired
	private VehicleDetailsService vehicleService;
	
// Get by Lead Id
	@Override
	public LeadDetailsJsonTempRes getLead(LeadDetailsGetReq req) {
		LeadDetailsJsonTempRes commonRes = new LeadDetailsJsonTempRes();
		OldPolicySaveReq oldPolicyRes = new OldPolicySaveReq();
		//ProspectPaymentSaveReq paymentRes = new ProspectPaymentSaveReq();
		VehicleDetailsRes vehicleInformation = new VehicleDetailsRes();
		CrmLeadRes leadRes = new CrmLeadRes();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			LeadDetails data = repository.findByLeadId(req.getLeadId());
			ClientDetails clientdetail = clientrepo.findByClientRefNo(data.getClientRefNo());
			mapper.getConfiguration().setAmbiguityIgnored(true);
			mapper.map(data, leadRes);

			mapper.map(clientdetail, leadRes);
			// mapper.map(clientaddress, res);
			leadRes.setPos(data.getPos());
			leadRes.setEmailId(clientdetail.getEmailId());
			leadRes.setOldPolicyNo(data.getOldPolicyNo());
			leadRes.setClientTypeId(clientdetail.getClientTypeId());
			leadRes.setSource(data.getSource());
			leadRes.setSourceId(data.getSourceId());
			leadRes.setLastVisitedDate(clientdetail.getLastVisitedDate());

			// Old Policy Res
			if (StringUtils.isNotBlank(data.getOldPolicyNo())) {
				OldPolicyDetails oldData = repo.findByOldPolicyNo(data.getOldPolicyNo());

				if (oldData != null) {
					oldPolicyRes = mapper.map(oldData, OldPolicySaveReq.class);
				}
			}
			if(StringUtils.isNotBlank(req.getLeadId())) {
				
				VehicleDetailsGetReq vehicledetail = new VehicleDetailsGetReq();
				vehicledetail.setLeadId(req.getLeadId());
				VehicleDetailsRes vehDetailsById = vehicleService.getVehDetailsById(vehicledetail);
				commonRes.setVehicleDetails(vehDetailsById);			
				
			}

			// Payment Res
			/*
			 * List<ProspectPaymentDetails> opt =
			 * prosRepo.findByProspectId(Integer.valueOf(req.getLeadId())); if
			 * (!opt.isEmpty()) { paymentRes = mapper.map(opt.get(0),
			 * ProspectPaymentSaveReq.class); } commonRes.setProspectPayment(paymentRes);
			 */
			commonRes.setLeadDetails(leadRes);
			commonRes.setOldPolicyDetails(oldPolicyRes);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return commonRes;

	}

	// Validate Old Policy Details

	@Override
	public List<Error> validateOldPolicy(LeadDetailsJsonTempReq tempReq) {
		OldPolicySaveReq req = tempReq.getOldPolicyDetails();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Error> errors = new ArrayList<Error>();
		try {

//			if (tempReq.getLeadDetails().getLeadId() != null
//					|| StringUtils.isNotBlank(tempReq.getLeadDetails().getLeadId())) {
//				if (!StringUtils.isNumeric(tempReq.getLeadDetails().getLeadId())) {
//					errors.add(new Error("01", "Lead Id", "Please Enter Valid Lead Id "));
//				}
//			}

			if (req.getOldComisBasePremium() == null || StringUtils.isBlank(req.getOldComisBasePremium())) {
				errors.add(new Error("02", "Old Commission Base Premium", "Please Enter Old Commission Base Premium"));
			} else if (!req.getOldComisBasePremium().matches("[0-9.]+")) {
				errors.add(new Error("02", "Old Commission Premium",
						"Please Enter Old Commission Premium in Correct Format"));
			}

			if (req.getOldDiscountPercent() == null || StringUtils.isBlank(req.getOldDiscountPercent())) {
				errors.add(new Error("03", "Old Discount Percent", "Please Enter Old Discount Percent"));
			} else if (!req.getOldDiscountPercent().matches("[0-9.]+")) {
				errors.add(
						new Error("03", "Old Discount Percent", "Please Enter Old Discount Percent in Correct Format"));
			}
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();

			// Date a = sdf.parse(req.getOldExpiryDate());
			if (req.getOldExpiryDate() == null || StringUtils.isBlank(req.getOldExpiryDate().toString())) {
				errors.add(new Error("04", "Old Expiry Date", "Please Enter Old Expiry Date"));
			} else if (req.getOldExpiryDate().after(yesterday)) {
				errors.add(new Error("04", "Old Expiry Date", "Please Enter Past Date as Old Expiry Date"));
			}

			if (req.getOldGst() == null || StringUtils.isBlank(req.getOldGst())) {
				errors.add(new Error("05", "Old GST", "Please Enter Old GST"));
			} else if (!req.getOldGst().matches("[0-9.]+")) {
				errors.add(new Error("05", "Old GST", "Please Enter Old GST in Correct Format"));
			}

			if (req.getOldnoClaimBonus() == null || StringUtils.isBlank(req.getOldnoClaimBonus())) {
				errors.add(new Error("06", "Old No Claim Bonus", "Please Enter Old No Claim Bonus"));
			} else if (!req.getOldnoClaimBonus().matches("[0-9.]+")) {
				errors.add(new Error("06", "Old No Claim Bonus", "Please Enter Old No Claim Bonus in Correct Format"));
			}
			if (req.getOldOtherPremium() == null || StringUtils.isBlank(req.getOldOtherPremium())) {
				errors.add(new Error("07", "Old No Claim Bonus", "Please Enter Old Other Premium"));
			}

			else if (!req.getOldOtherPremium().matches("[0-9.]+")) {
				errors.add(new Error("07", "Old Other Premium", "Please Enter Old Other Premium"));
			}
			if (req.getOldPolicyAddiInfo() == null || StringUtils.isBlank(req.getOldPolicyAddiInfo())) {
				errors.add(new Error("08", "Old Policy Addi Info", "Please Enter Old Policy Addi Info"));
			} else if (req.getOldPolicyAddiInfo().length() > 100) {
				errors.add(new Error("08", "Old Policy Addi Info",
						"Please Enter Old Policy Addi Info within 100 Characters"));
			}
			if (req.getOldPolicyNo() == null || StringUtils.isBlank(req.getOldPolicyNo())) {
				errors.add(new Error("09", "Old Policy No", "Please Enter Old Policy No"));
			} else if (req.getOldPolicyNo().length() > 100) {
				errors.add(new Error("09", "Old Policy No", "Please Enter Old Policy No within 100 Characters"));
			}
			if (req.getOldStartDate() == null || StringUtils.isBlank(req.getOldStartDate().toString())) {
				errors.add(new Error("10", "Old Start Date", "Please Enter Old Start Date"));
			}

			if (req.getOldSumInsured() == null || StringUtils.isBlank(req.getOldSumInsured())) {
				errors.add(new Error("11", "Old Sum Insured", "Please Enter Old Sum Insured"));
			}

			else if (!req.getOldSumInsured().matches("[0-9.]+")) {
				errors.add(new Error("11", "Old Sum Insured", "Please Enter Old Old Sum Insured in Correct Format"));
			}
			if (req.getOldTotalpremiumWithgst() == null || StringUtils.isBlank(req.getOldTotalpremiumWithgst())) {
				errors.add(new Error("12", "Old Total Premium With GST", "Please Enter Old Total Premium with GST"));
			} else if (!req.getOldTotalpremiumWithgst().matches("[0-9.]+")) {
				errors.add(new Error("12", "Old Total Premium With GST",
						"Please Enter Old Total Premium with GST in Correct Format"));
			}
			if (req.getOldTotalPremium() == null || StringUtils.isBlank(req.getOldTotalPremium())) {
				errors.add(new Error("13", "Old Total Premium", "Please Enter Old Total Premium"));
			}

			else if (!req.getOldTotalPremium().matches("[0-9.]+")) {
				errors.add(new Error("13", "Old Total Premium", "Please Enter Old Total Premium in Correct Format"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			errors.add(new Error("13", "Common Error", e.getMessage()));
			return errors;
		}
		return errors;
	}

	// Save Old Policy Details

	@Override
	public SuccessRes saveOldPolicy(LeadDetailsJsonTempReq tempReq) {
		OldPolicySaveReq req = tempReq.getOldPolicyDetails();
		SuccessRes res = new SuccessRes();
		OldPolicyDetails entity = new OldPolicyDetails();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");

		ModelMapper mapper = new ModelMapper();
		Date entryDate = null;
		try {
			OldPolicyDetails data = repo.findByOldPolicyNo(req.getOldPolicyNo());
			if (data == null) {
				entryDate = new Date();
				res.setResponse("Saved Successfully ");
				res.setSucessId(req.getOldPolicyNo());
			} else {
				data = repo.findByOldPolicyNo(req.getOldPolicyNo());
				entryDate = data.getEntryDate();
				res.setResponse("Updated Successfully ");
				res.setSucessId(req.getOldPolicyNo());

			}

			entity.setOldPolicyNo(req.getOldPolicyNo());
			entity.setEntryDate(entryDate);
			entity.setOldExpiryDate(req.getOldExpiryDate());
			entity.setOldStartDate(req.getOldStartDate());
			entity.setOldComisBasePremium(Double.valueOf(req.getOldComisBasePremium()));
			entity.setOldDiscountPercent(Integer.valueOf(req.getOldDiscountPercent()));
			entity.setOldGst(Double.valueOf(req.getOldGst()));
			entity.setOldnoClaimBonus(Double.valueOf(req.getOldnoClaimBonus()));
			entity.setOldOtherPremium(Double.valueOf(req.getOldOtherPremium()));
			entity.setOldPolicyAddiInfo(req.getOldPolicyAddiInfo());
			entity.setOldPolicyNo(req.getOldPolicyNo());
			entity.setOldSumInsured(Double.valueOf(req.getOldSumInsured()));
			entity.setOldTotalPremium(Double.valueOf(req.getOldTotalPremium()));
			entity.setOldTotalpremiumWithgst(Double.valueOf(req.getOldTotalpremiumWithgst()));
			entity.setCompanyId(tempReq.getLeadDetails().getInsCompanyId());
			entity.setRegionCode(tempReq.getLeadDetails().getRegionCode());
			entity.setBranchCode(tempReq.getLeadDetails().getBranchCode());
			entity.setCreatedBy(tempReq.getLeadDetails().getCreatedBy());
			entity.setUserType(tempReq.getLeadDetails().getUserType());
			entity.setOldSource(req.getOldSource());
			entity.setOldSourceId(req.getOldSourceId());
			entity.setVehicleTransferCase(req.getVehicleTransferCase());
			entity.setVehicleTransferCaseId(req.getVehicleTransferCaseId());
			entity.setOldInsurer(req.getOldInsurer());
			repo.save(entity);
			log.info("Saved Details is ---> " + json.toJson(entity));

		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}

	// Drop Down Get Policy Details

	@Override
	public List<DropDownRes> getpolicydetails() {
		// TODO Auto-generated method stub
		return null;
	}

	// Get All Policy Details

	@Override
	public List<OldPolicyRes> getallOldPolicyDetails(OldPolicyGetAllReq req) {
		List<OldPolicyRes> resList = new ArrayList<OldPolicyRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// List<OldPolicyDetails> policyDetails = repo.OrderByEntryDateAsc();
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			Page<OldPolicyDetails> policyDetails = repo.findByCompanyIdAndBranchCode(paging, req.getInsId(),
					req.getBranchCode());

			for (OldPolicyDetails data : policyDetails) {
				OldPolicyRes res = new OldPolicyRes();
				mapper.map(data, res);

				res.setOldCommisBasePremium(data.getOldComisBasePremium() == null ? 0 : data.getOldComisBasePremium());
				res.setOldDiscountPercent(
						data.getOldDiscountPercent() == null ? "" : data.getOldDiscountPercent().toString());
				res.setOldExpiryDate(data.getOldExpiryDate() == null ? null : data.getOldExpiryDate());
				res.setOldGst(data.getOldGst() == null ? "" : data.getOldGst().toString());
				res.setOldnoClaimBonus(data.getOldnoClaimBonus() == null ? "" : data.getOldnoClaimBonus().toString());
				res.setOldOtherPremium(data.getOldOtherPremium() == null ? "" : data.getOldOtherPremium().toString());
				res.setOldPolicyAddiInfo(
						data.getOldPolicyAddiInfo() == null ? "" : data.getOldPolicyAddiInfo().toString());
				res.setOldPolicyNo(data.getOldPolicyNo() == null ? "" : data.getOldPolicyNo().toString());
				res.setOldStartDate(data.getOldStartDate() == null ? null : data.getOldStartDate());
				res.setOldSumInsured(data.getOldSumInsured() == null ? "" : data.getOldSumInsured().toString());
				res.setOldTotalPremium(data.getOldTotalPremium() == null ? "" : data.getOldTotalPremium().toString());
				res.setOldTotalpremiumWithgst(
						data.getOldTotalpremiumWithgst() == null ? "" : data.getOldTotalpremiumWithgst().toString());
				res.setEntryDate(data.getEntryDate() == null ? null : data.getEntryDate());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}

	// Get By Policy No

	@Override
	public OldPolicyRes getPolicyNo(OldPolicyGetReq req) {
		OldPolicyRes res = new OldPolicyRes();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			OldPolicyDetails data = repo.findByOldPolicyNo(req.getOldPolicyNo());

			res.setOldCommisBasePremium(data.getOldComisBasePremium() == null ? 0 : data.getOldComisBasePremium());
			res.setOldDiscountPercent(
					data.getOldDiscountPercent() == null ? "" : data.getOldDiscountPercent().toString());
			res.setOldExpiryDate(data.getOldExpiryDate() == null ? null : data.getOldExpiryDate());
			res.setOldGst(data.getOldGst() == null ? "" : data.getOldGst().toString());
			res.setOldnoClaimBonus(data.getOldnoClaimBonus() == null ? "" : data.getOldnoClaimBonus().toString());
			res.setOldOtherPremium(data.getOldOtherPremium() == null ? "" : data.getOldOtherPremium().toString());
			res.setOldPolicyAddiInfo(data.getOldPolicyAddiInfo() == null ? "" : data.getOldPolicyAddiInfo().toString());
			res.setOldPolicyNo(data.getOldPolicyNo() == null ? "" : data.getOldPolicyNo().toString());
			res.setOldStartDate(data.getOldStartDate() == null ? null : data.getOldStartDate());
			res.setOldSumInsured(data.getOldSumInsured() == null ? "" : data.getOldSumInsured().toString());
			res.setOldTotalPremium(data.getOldTotalPremium() == null ? "" : data.getOldTotalPremium().toString());
			res.setOldTotalpremiumWithgst(
					data.getOldTotalpremiumWithgst() == null ? "" : data.getOldTotalpremiumWithgst().toString());
			res.setEntryDate(data.getEntryDate() == null ? null : data.getEntryDate());
			res.setCompanyId(data.getCompanyId());
			res.setRegionCode(data.getRegionCode());
			res.setBranchCode(data.getBranchCode());
			res.setCreatedBy(data.getCreatedBy());
			res.setUserType(data.getUserType());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	// validate vehicle details
	@Override
	public List<Error> validateVehicle(VehicleDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
//			if (req.getCc() == null || StringUtils.isBlank(req.getCc())) {
//				errors.add(new Error("01", "CC", "Please Enter CC"));
//			} else if (req.getCc().length() > 100) {
//				errors.add(new Error("01", "CC", "Please Enter CC within 100 Character"));
//			}
			if (req.getColorId() == null || StringUtils.isBlank(req.getColorId())) {
				errors.add(new Error("02", "Color Id", "Please Enter Color Id"));
			} else if (!StringUtils.isNumeric(req.getColorId())) {
				errors.add(new Error("02", "Color Id", "Please Enter Color Id in numbers"));
			}
			if (req.getColorVariant() == null || StringUtils.isBlank(req.getColorVariant())) {
				errors.add(new Error("03", "Color Variant", "Please Enter Color Variant"));
			} else if (req.getColorVariant().length() > 100) {
				errors.add(new Error("03", "Color Variant", "Please Enter Color Variant within 100 Character"));
			}
			if (req.getEngineNo() == null || StringUtils.isBlank(req.getEngineNo())) {
				errors.add(new Error("04", "Engine No", "Please Enter Engine No"));
			} else if (req.getEngineNo().length() > 100) {
				errors.add(new Error("04", "Engine No", "Please Enter Engine No within 100 Character"));
			}
			if (req.getFueltype() == null || StringUtils.isBlank(req.getFueltype())) {
				errors.add(new Error("05", "Fuel Type", "Please Enter Fuel Type"));
			} else if (req.getFueltype().length() > 100) {
				errors.add(new Error("05", "Fuel Type", "Please Enter Fuel Type within 100 Character"));
			}
			/*
			 * if (req.getFueltypeId() == null || StringUtils.isBlank(req.getFueltypeId()))
			 * { errors.add(new Error("06", "Fuel Type Id", "Please Enter Fuel Type Id")); }
			 */ else if (!StringUtils.isNumeric(req.getFueltypeId())) {
				errors.add(new Error("06", "Fuel Type Id", "Please Enter Fuel Type Id in numbers"));
			}
			if (req.getManufactureYear() == null || StringUtils.isBlank(req.getManufactureYear())) {
				errors.add(new Error("07", "Manufacture Year", "Please Enter Manufature Year"));
			} else if (!StringUtils.isNumeric(req.getManufactureYear())) {
				errors.add(new Error("07", "Manufature Year", "Please Enter Manufacture Year in numbers"));
			} else if (req.getManufactureYear().length() > 4) {
				errors.add(new Error("07", "Manufature Year", "Please Enter Manufature Year in numbers"));
			}
//			if (req.getPlateChar() == null || StringUtils.isBlank(req.getPlateChar())) {
//				errors.add(new Error("08", "Plate Char", "Please Enter Plate Char"));
//			} else if (req.getPlateChar().length() > 100) {
//				errors.add(new Error("08", "Plate Char", "Please Enter Plate Char within 100 Character"));
//			}
//			if (req.getPlateCharId() == null || StringUtils.isBlank(req.getPlateCharId())) {
//				errors.add(new Error("09", "Plate Char", "Please Enter Plate Char"));
//			}
//			if (req.getPlateNo() == null || StringUtils.isBlank(req.getPlateNo())) {
//				errors.add(new Error("10", "Plate No", "Please Enter Plate No"));
//			} else if (!StringUtils.isNumeric(req.getPlateNo())) {
//				errors.add(new Error("10", "Plate No", "Please Enter Plate No in numbers"));
//			}
			if (req.getSeatingCapacity() == null || StringUtils.isBlank(req.getSeatingCapacity())) {
				errors.add(new Error("11", "Seating Capacity", "Please Enter Seating Capacity"));
			} else if (!StringUtils.isNumeric(req.getSeatingCapacity())) {
				errors.add(new Error("11", "Seating Capacity", "Please Enter Seating Capacity in numbers"));
			}
			if (req.getVehBodytype() == null || StringUtils.isBlank(req.getVehBodytype())) {
				errors.add(new Error("12", "Vehicle Body Type", "Please Enter Vehicle Body Type"));
			} else if (req.getVehBodytype().length() > 100) {
				errors.add(new Error("12", "Vehicle Body Type", "Please Enter Vehicle Body Type within 100 Character"));
			}
			if (req.getVehBodytypeId() == null || StringUtils.isBlank(req.getVehBodytypeId())) {
				errors.add(new Error("13", "Vehicle Body Type Id", "Please Enter Vehicle Body Type Id"));
			} else if (!StringUtils.isNumeric(req.getVehBodytypeId())) {
				errors.add(new Error("13", "Vehicle Body Type Id", "Please Enter Vehicle Body Type Id in numbers"));
			}

			if (req.getVehClassificationId() == null || StringUtils.isBlank(req.getVehClassificationId())) {
				errors.add(new Error("14", "Veh Classification Id", "Please Enter Veh Classification Id"));
			} else if (!StringUtils.isNumeric(req.getVehClassificationId())) {
				errors.add(new Error("14", "Veh Classification Id", "Please Enter Veh Classification Id in numbers"));
			}

			if (req.getVehTypeId() == null || StringUtils.isBlank(req.getVehTypeId())) {
				errors.add(new Error("15", "Veh Type Id", "Please Enter Veh Type Id"));
			}
			if (req.getVehMakeId() == null || StringUtils.isBlank(req.getVehMakeId())) {
				errors.add(new Error("16", "Veh Make Id", "Please Enter Veh Make Id"));
			}
			if (req.getVehModelId() == null || StringUtils.isBlank(req.getVehModelId())) {
				errors.add(new Error("17", "Veh Model Id", "Please Enter Veh Model Id"));
			}
			if (req.getGvw() == null || StringUtils.isBlank(req.getGvw())) {
				errors.add(new Error("18", "Gvw", "Please Enter Gvw"));
			}
			if (req.getCarryingPassengers() == null || StringUtils.isBlank(req.getCarryingPassengers().toString())) {
				errors.add(new Error("19", "Passenger Carrying Capacity", "Please Enter Passenger Carrying Capacity"));
			}

			if (req.getVehChassisNo() == null || StringUtils.isBlank(req.getVehChassisNo())) {
				errors.add(new Error("20", "Veh Chassis No", "Please Enter Veh Chassis No"));
			} else if (req.getVehChassisNo().length() > 100) {
				errors.add(new Error("20", "Veh Chassis No", "Please Enter Veh Chassis No within 100 Character"));
			}
			if (req.getHypothication() == null || StringUtils.isBlank(req.getHypothication())) {
				errors.add(new Error("21", "Hypothication", "Please Enter Hypothication"));
			} else if (req.getHypothication().length() > 100) {
				errors.add(new Error("21", "Hypothication", "Please Enter Hypothication within 100 Character"));
			}
//			if (req.getPos() == null || StringUtils.isBlank(req.getPos())) {
//				errors.add(new Error("22", "Pos", "Please Enter Pos"));
//			} else if (req.getPos().length() > 100) {
//				errors.add(new Error("22", "Pos", "Please Enter Pos within 100 Character"));
//			}
//			if (req.getPinCode() == null || StringUtils.isBlank(req.getPinCode())) {
//				errors.add(new Error("23", "Pin Code", "Please Enter Pin Code"));
//			} else if (!StringUtils.isNumeric(req.getPinCode())) {
//				errors.add(new Error("23", "Pin Code", "Please Enter PinCode in numbers"));
//			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Save vehicle Details
	@Override
	public SuccessRes saveVehicle(VehicleDetailsSaveReq req) {
		SuccessRes res = new SuccessRes();
		VehicleDetails entity = new VehicleDetails();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");

		ModelMapper mapper = new ModelMapper();
		Date entryDate = null;
		try {
			VehicleDetails data = vehRepo.findByVehChassisNo(req.getVehChassisNo());
			if (data == null) {
				entryDate = new Date();
				res.setResponse("Saved Successfully ");
			} else {
				data = vehRepo.findByVehChassisNo(req.getVehChassisNo());
				entryDate = data.getEntryDate();
				res.setResponse("Updated Successfully ");
			}

			entity = mapper.map(req, VehicleDetails.class);

			entity.setVehChassisNo(req.getVehChassisNo());
			entity.setEntryDate(entryDate);
			vehRepo.save(entity);
			log.info("Saved Details is ---> " + json.toJson(entity));

		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}

	// Get All Vehicle Details
	@Override
	public List<VehicleDetailsGridRes> getallVehicleDetails(VehicleDetailsGetAllReq req) {
		List<VehicleDetailsGridRes> resList = new ArrayList<VehicleDetailsGridRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 1000 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());
			Page<VehicleDetails> vehicleDetails = vehRepo.findByCompanyIdAndBranchCode(paging, req.getInsId(),
					req.getBranchCode());

			for (VehicleDetails data : vehicleDetails) {
				VehicleDetailsGridRes res = new VehicleDetailsGridRes();
				mapper.map(data, res);

				res.setEntryDate(data.getEntryDate() == null ? null : data.getEntryDate());

				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}

	// Get By Vehicle Details

	@Override
	public VehicleDetailsRes getChassisNo(VehicleDetailsGetReq req) {
		VehicleDetailsRes res = new VehicleDetailsRes();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			VehicleDetails data = vehRepo.findByVehChassisNo(req.getVehChassisNo());

			mapper.map(data, res);
			res.setEntryDate(data.getEntryDate() == null ? null : data.getEntryDate());

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;

	}

	// Drop Down Vehicle Details
	@Override
	public List<DropDownRes> getvehicledetails() {
		// TODO Auto-generated method stub
		return null;
	}

	// View Lead
	@Override
	public LeadViewRes viewLeadDetails(LeadViewReq req) {
		LeadViewRes res = new LeadViewRes();
		ModelMapper modelMapper = new ModelMapper();
		try {

			// Lead
			LeadDetails lead = repository.findByLeadId(req.getLeadId());

			res.setLeadDetails(modelMapper.map(lead, CrmLeadRes.class));

			// Vehicle Details
			VehicleDetails vehDet = vehRepo.findByVehChassisNo(req.getVehChassisNo());

			res.setVehicleDetails(modelMapper.map(vehDet, VehicleDetailsRes.class));

			// Old Policy
			OldPolicyDetails oldPolicy = repo.findByOldPolicyNo(req.getOldPolicyNo());

			res.setOldPolicy(modelMapper.map(oldPolicy, OldPolicyRes.class));

		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;

	}

	@Override
	public List<CrmLeadRes> viewClientLead(ClientLeadReq req) {
		List<CrmLeadRes> resList = new ArrayList<CrmLeadRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<LeadDetails> leadDetails = repository.findByClientRefNoOrderByEntryDateDesc(req.getClientRefNo());

			for (LeadDetails data : leadDetails) {
				CrmLeadRes res = new CrmLeadRes();
				res = mapper.map(data, CrmLeadRes.class);

				// res.setProspectCount(prospectdetailsrepositoryl.countByLeadId(data.getLeadId()).toString());
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return resList;

	}

	// Lead Bulk Edit
	@Override
	public List<Error> validateLead(LeadBulkEditReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessRes bulkEditLead(LeadBulkEditReq req) {
		SuccessRes res = new SuccessRes();
		try {
			if (req.getLeadId() != null && req.getLeadId().size() > 0) {
				List<Integer> leadIds = req.getLeadId();
				// Get List
				List<LeadDetails> getDatas = repository.findByLeadIdIn(leadIds);

				for (LeadDetails data : getDatas) {

					data.setAssigntoGroup(StringUtils.isBlank(req.getChangeAssigntoGroup()) ? data.getAssigntoGroup()
							: req.getChangeAssigntoGroup());
					data.setAssigntoGroupId(
							StringUtils.isBlank(req.getChangeAssignToGroupId()) ? data.getAssigntoGroupId()
									: Integer.valueOf(req.getChangeAssignToGroupId()));
					data.setAssigntoUser(StringUtils.isBlank(req.getChangeAssigntoUser()) ? data.getAssigntoUser()
							: req.getChangeAssigntoUser());
					data.setAssigntoUserId(StringUtils.isBlank(req.getChangeAssigntoUserId()) ? data.getAssigntoUserId()
							: req.getChangeAssigntoUserId());
					data.setPos(StringUtils.isBlank(req.getChangePos()) ? data.getPos() : req.getChangePos());
					data.setPosId(StringUtils.isBlank(req.getChangePosId()) ? data.getPosId()
							: Integer.valueOf(req.getChangePosId()));
					data.setCalssification(StringUtils.isBlank(req.getChangeClassification()) ? data.getCalssification()
							: req.getChangeClassification());
					data.setClassificationId(
							StringUtils.isBlank(req.getChangeClassificationId()) ? data.getClassificationId()
									: Integer.valueOf(req.getChangeClassificationId()));
					data.setSource(
							StringUtils.isBlank(req.getChangeSource()) ? data.getSource() : req.getChangeSource());
					data.setSourceId(StringUtils.isBlank(req.getChangeSourceId()) ? data.getSourceId()
							: Integer.valueOf(req.getChangeSourceId()));
					repository.save(data);
					res.setResponse("Updated Succesfully");
				}

			} else {
				res.setResponse("Not Updated");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	@Override
	public List<LeadSearchRes> searchLeadDetails(ClientSearchReq req) {
		List<LeadSearchRes> resList = new ArrayList<LeadSearchRes>();
		ModelMapper mapper = new ModelMapper();
		List<LeadDetails> leadDetails = new ArrayList<LeadDetails>();
		try {
			String searchValue = req.getSearchValue();// .toLowerCase() ;

			if (StringUtils.isNotBlank(searchValue) && searchValue.matches("[0-9]+")) {
				leadDetails = Criteria.LeadDynamicSearch1(searchValue, req.getCreatedBy());
			}

			if (leadDetails.size() <= 0) {
				leadDetails = Criteria.LeadDynamicSearch2(searchValue, req.getCreatedBy());
			}

			if (leadDetails != null && leadDetails.size() > 0) {
				// Map List
				for (LeadDetails data : leadDetails) {
					LeadSearchRes res = new LeadSearchRes();

					res = mapper.map(data, LeadSearchRes.class);
					ClientDetails clientData = clientrepo.findByClientRefNo(data.getClientRefNo());
					// res.setLeadCount(leadRepo.countByClientRefNo(data.getClientRefNo()).toString());
					res.setUserNameWithMobile(res.getClientName() + "-" + res.getMobileNumber());
					res.setMobileNumber(clientData.getMobileNumber());
					res.setAlternativeNumber(clientData.getAlternativeNumber());
					res.setEmailId(clientData.getEmailId());
					;
					resList.add(res);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			resList = null;
		}
		return resList;
	}

	@Override
	public CrmLeadSuccessRes generateLead(LeadGenerateReq req) {
		CrmLeadSuccessRes res = new CrmLeadSuccessRes();
		LeadDetails entity = new LeadDetails();
		String leadId = "";
		try {
			LeadDetailsId id = new LeadDetailsId();
			id.setClientRefNo(req.getClientRefNo());
			id.setCreatedBy(req.getCreatedBy());
			id.setInsCompanyId(req.getInsCompanyId());
			id.setLeadId(req.getLeadId());
			ClientDetails clientName = clientrepo.findByClientRefNo(req.getClientRefNo());
			// Insert
			Long count = repository.count();
			Random rnd = new Random();
			int number = rnd.nextInt(100);
			String randomNo = String.format("%02d", number);
			leadId = "100";

			Long Id = count + 100;
			leadId = "L" + Id.toString() + randomNo;
			LeadDetails ent = new LeadDetails();
			ent.setLeadId(leadId);
			ent.setEntryDate(new Date());
			ent.setLeadGenDate(new Date());
			ent.setClientName(clientName.getClientName());
			ent.setClientRefNo(req.getClientRefNo());
			ent.setStatus("Y");
			ent.setBranchCode(req.getBranchCode());
			ent.setCreatedBy(req.getCreatedBy());
			ent.setInsCompanyId(req.getInsCompanyId());
			ent.setUserType(req.getUserType());
			ent.setUpdatedDate(new Date());
			ent.setUpdatedBy(req.getCreatedBy());
			ent.setUpdaterUsertype(req.getUserType());
			repository.saveAndFlush(ent);
			res.setResponse("Lead Generated Successfully ");
			res.setLeadid(leadId);

			log.info("Saved Details is ---> " + json.toJson(entity));

			// Thread To Trigger Mail
			ClientDetails clientDetails = clientrepo.findByClientRefNo(req.getClientRefNo());
			clientDetails.setLastVisitedDate(new Date());
			clientrepo.saveAndFlush(clientDetails);
			InsuranceCompanyMaster companyData = insRepo.findByInsId(req.getInsCompanyId().toString());
			ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());

			List<String> ccMails = new ArrayList<String>();
			ccMails.add(companyData.getInsEmail());
			ccMails.add(loginData.getUserMail());

			List<String> toMails = new ArrayList<String>();
			toMails.add(clientDetails.getEmailId());

			Map<String, Object> keys = new HashMap<String, Object>();
			keys.put("LEAD_ID", leadId == null ? "" : leadId);

			// Set Mail Request
			MailFramingReq mailFrameReq = new MailFramingReq();
			mailFrameReq.setInsId(req.getInsCompanyId().toString());
			mailFrameReq.setNotifTemplateId("LEAD_INFO");
			mailFrameReq.setKeys(keys);
			mailFrameReq.setMailCc(ccMails);
			mailFrameReq.setMailTo(toMails);
			mailFrameReq.setMailRegards(companyData.getRegards());
			mailFrameReq.setStatus(res.getResponse());

			log.info("{ Mail Pushed SuccessFully . LeadId is ---> " + leadId + " ; ClientRefNo is --->"
					+ req.getClientRefNo() + " }");
			// mailFrameService.sendSms(mailReq);
			mailThreadService.threadToSendMail(mailFrameReq);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public ClientsLeadCountRes clientsLeadCount(ClientLeadCountReq req) {

		ClientsLeadCountRes finalres = new ClientsLeadCountRes();
		ModelMapper mapper = new ModelMapper();
		try {
			// Lead Count
			List<CrmLeadRes> leadList = new ArrayList<CrmLeadRes>();
			List<LeadDetails> leadCount = repository.findByClientRefNoAndStatusAndInsCompanyIdAndBranchCode(
					req.getClientRefNo(), "Enquiry", req.getInsCompanyId(), req.getBranchCode());
			for (LeadDetails leadDetails : leadCount) {

				CrmLeadRes res = new CrmLeadRes();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res.setClientName(leadDetails.getClientName());
				mapper.map(leadDetails, res);
				leadList.add(res);

			}

			// Prospect Count
			List<CrmLeadRes> prospectList = new ArrayList<CrmLeadRes>();
			List<LeadDetails> prospectCount = repository.findByClientRefNoAndStatusAndInsCompanyIdAndBranchCode(
					req.getClientRefNo(), "Quote", req.getInsCompanyId(), req.getBranchCode());
			for (LeadDetails leadDetails : prospectCount) {
				CrmLeadRes res = new CrmLeadRes();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res.setClientName(leadDetails.getClientName());
				mapper.map(leadDetails, res);
				prospectList.add(res);
			}

			List<PolicyDetailsRes> policyList = new ArrayList<PolicyDetailsRes>();
			List<PolicyDetails> policyCount = policyRepo.findByClientRefNoAndInsCompanyIdAndBranchCode(
					req.getClientRefNo(), req.getInsCompanyId(), req.getBranchCode());
			for (PolicyDetails policyDetails : policyCount) {
				PolicyDetailsRes res = new PolicyDetailsRes();
				mapper.getConfiguration().setAmbiguityIgnored(true);
				mapper.map(policyDetails, res);
				policyList.add(res);
			}

			// Response
			finalres.setLead(leadList);
			finalres.setProspect(prospectList);
			finalres.setPolicy(policyList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return finalres;
	}

	@Override
	public CrmLeadSuccessRes updateLeadStatus(ClientUpdateStatusReq req) {
		CrmLeadSuccessRes res = new CrmLeadSuccessRes();
		try {
			LeadDetails leadData = repository.findByClientRefNoAndLeadId(req.getClientRefNo(), req.getLeadId());
			ClientDetails clientData = clientrepo.findByClientRefNo(req.getClientRefNo());
			leadData.setStatus(req.getStatus());
			leadData.setUpdatedDate(new Date());
			leadData.setUpdatedBy(req.getCreatedBy());
			leadData.setUpdaterUsertype(req.getUserType());
			repository.saveAndFlush(leadData);
			System.out.println(json.toJson(leadData));
			res.setResponse(" Lead Moved To " + req.getStatus());
			res.setLeadid(req.getLeadId());

			// Encrypt Details
			if (leadData.getClassId() != null && leadData.getClassId().toString().equalsIgnoreCase("10")
					&& req.getStatus().equalsIgnoreCase("Quote")) {
				String screenName = "TRAVEL_INFO";
				CrmRedirectDetails redirectData = redirectRepo.findByScreenName(screenName);
				ClientBasicDetails beforeEncrypt = new ClientBasicDetails();
				String unencryptedString = "loginId=" + req.getCreatedBy() + "~password=" + "Admin@03" + "~usertype="
						+ "RSAIssuer" + "~branchcode=" + "08" + "~clientRefNo=" + req.getClientRefNo() + "~leadId="
						+ leadData.getLeadId().toString() + "~clientType=" + clientData.getClientTypeId()
						+ "~referenceNo=" + req.getReferenceNo() + "~policyNo=" + req.getPolicyNo() + "~quoteNo="
						+ req.getQuoteNo();// +"~productId=";
				String afterEncrypt = encrypt.encrypt(unencryptedString);
				res.setEncodedValues(redirectData.getRedirectUrlLink() + afterEncrypt);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	// Get All Lead With Count

	@Override
	public LeadGetAllCountRes getallLeadCount(LeadGetallCountReq req) {

		LeadGetAllCountRes res = new LeadGetAllCountRes();
		List<Tuple> list = new ArrayList<Tuple>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {

			// Limit , Offset
			Integer limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			Integer offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			// Find All
			Page<LeadDetails> leadDetails = repository.findByInsCompanyIdAndBranchCodeOrderByUpdatedDateDesc(
					paging, req.getInsId(), req.getBranchCode());

			List<String> leadIds = leadDetails.getContent().stream().map(LeadDetails::getLeadId)
					.collect(Collectors.toList());

			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

			Root<LeadDetails> l = query.from(LeadDetails.class);
		
			// Enquiry Count SubQuery
			Subquery<Long> enquiryCount = query.subquery(Long.class);
			Root<EnquiryDetails> e = enquiryCount.from(EnquiryDetails.class);
			enquiryCount.select(cb.count(e));
			javax.persistence.criteria.Predicate e1 = cb.equal(e.get("leadId"), l.get("leadId"));
			enquiryCount.where(e1);
			
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.equal(q.get("leadId"), l.get("leadId"));
			quoteCount.where(q1);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 =  cb.equal(p.get("leadId"), l.get("leadId"));
			policyCount.where(p1); 
			
			Expression<String> e0 = l.get("leadId");
			Predicate n1 = e0.in(leadIds);

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(l.get("updatedDate")));

			query.multiselect(l.get("leadId").alias("LeadId"),enquiryCount.alias("EnquiryCount")  , quoteCount.alias("QuoteCount") ,
					policyCount.alias("PolicyCount") );
			
			query.where(n1).groupBy(l.get("leadId"),l.get("updatedDate")).orderBy(orderList);
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();

			List<CrmLeadRes> leadList = new ArrayList<CrmLeadRes>();
			for (LeadDetails data : leadDetails.getContent()) {
				
				CrmLeadRes leadData = new CrmLeadRes();
				leadData = mapper.map(data, CrmLeadRes.class);

				List<Tuple> filterList   =   list.stream().filter(o -> o.get("LeadId").equals(data.getLeadId())).collect(Collectors.toList());
				if(filterList.size()>0    ) {
					Tuple counts = filterList.get(0) ;
					leadData.setEnquiryCount(counts.get("EnquiryCount")==null?"0":counts.get("EnquiryCount").toString());
					leadData.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
					leadData.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
					
				} else {
					leadData.setEnquiryCount("0");
					leadData.setQuoteCount("0");
					leadData.setPolicyCount("0");
				}
				leadList.add(leadData);
			}
			res.setLeadDetails(leadList);
			Long count1 = repository.countByInsCompanyIdAndBranchCode(req.getInsId(), req.getBranchCode());
			res.setLeadCount(count1);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
		/*
		 * // Find String status = "Enquiry"; Page<LeadDetails> leadDetails =
		 * repository.findByInsCompanyIdAndBranchCodeAndStatus(paging, req.getInsId(),
		 * req.getBranchCode(), status); Long count =
		 * repository.countByInsCompanyIdAndBranchCodeAndStatus(req.getInsId(),
		 * req.getBranchCode(), status); List<CrmLeadRes> leadlist = new
		 * ArrayList<CrmLeadRes>(); for (LeadDetails data : leadDetails.getContent()) {
		 * CrmLeadRes leaddata = new CrmLeadRes(); leaddata = mapper.map(data,
		 * CrmLeadRes.class); leadlist.add(leaddata); } res.setLeadDetails(leadlist);
		 * res.setLeadCount(count); }
		 */
	}

	@Override
	public LeadSearchCountRes searchLeadCount(LeadSearchReq req) {
		LeadSearchCountRes resa = new LeadSearchCountRes();
		
		List<Tuple> list = new ArrayList<Tuple>();
		ModelMapper mapper = new ModelMapper();
		List<LeadDetails> leadDetails = new ArrayList<LeadDetails>();
		try {
			String searchValue = req.getSearchValue();// .toLowerCase() ;

			if (StringUtils.isNotBlank(searchValue) ) {
				leadDetails = Criteria.LeadDynamicSearch3(req);
			}

			if (leadDetails.size() <= 0) {
				leadDetails = Criteria.LeadDynamicSearch4(req);
			}
			
			List<String> leadIds = 	leadDetails.stream().map(LeadDetails :: getLeadId ).collect(Collectors.toList())  ;
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

			Root<LeadDetails> l = query.from(LeadDetails.class);
		
			// Enquiry Count SubQuery
			Subquery<Long> enquiryCount = query.subquery(Long.class);
			Root<EnquiryDetails> e = enquiryCount.from(EnquiryDetails.class);
			enquiryCount.select(cb.count(e));
			javax.persistence.criteria.Predicate e1 = cb.equal(e.get("leadId"), l.get("leadId"));
			enquiryCount.where(e1);
			
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.equal(q.get("leadId"), l.get("leadId"));
			quoteCount.where(q1);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 =  cb.equal(p.get("leadId"), l.get("leadId"));
			policyCount.where(p1); 
			
			Expression<String> e0 = l.get("leadId");
			Predicate n1 = e0.in(leadIds);

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(l.get("updatedDate")));

			query.multiselect(l.get("leadId").alias("LeadId"),enquiryCount.alias("EnquiryCount")  , quoteCount.alias("QuoteCount") ,
					policyCount.alias("PolicyCount") );
			
			query.where(n1).groupBy(l.get("leadId"),l.get("updatedDate")).orderBy(orderList);
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			

			if (leadDetails != null && leadDetails.size() > 0) {
				// Map List
				List<CrmLeadRes> leadres = new ArrayList<CrmLeadRes>();
				for (LeadDetails data : leadDetails) {
					CrmLeadRes res = new CrmLeadRes();

					res = mapper.map(data, CrmLeadRes.class);
					
					List<Tuple> filterList   =   list.stream().filter(o -> o.get("LeadId").equals(data.getLeadId())).collect(Collectors.toList());
					if(filterList.size()>0    ) {
						Tuple counts = filterList.get(0) ;
						res.setEnquiryCount(counts.get("EnquiryCount")==null?"0":counts.get("EnquiryCount").toString());
						res.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
						res.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
						
					} else {
						res.setEnquiryCount("0");
						res.setQuoteCount("0");
						res.setPolicyCount("0");
					}
					leadres.add(res);

				}
				// COunt 
				req.setLimit("");
				req.setOffset("");
				Long leadCount = 0L;
				if (StringUtils.isNotBlank(searchValue) ) {
					leadCount = Long.valueOf(Criteria.LeadDynamicSearch3(req).size());
				}

				if (leadDetails.size() <= 0) {
					leadCount = Long.valueOf(Criteria.LeadDynamicSearch4(req).size());
				}
				
				resa.setLeadDetails(leadres);
				resa.setLeadCount(Long.valueOf(leadCount));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return resa;
	}

	@Override
	@Transactional
	public CrmLeadSuccessRes updateLeadQuoteDetails(LeadQuoteDetailsReq req) {
		CrmLeadSuccessRes res = new CrmLeadSuccessRes();
		ModelMapper mapper = new ModelMapper();
		try {

			QuoteDetails quoteDetails = quoteRepo.findByReferenceNo(req.getClientRefNo());
			Date entryDate = null;
			if (quoteDetails != null) {
				entryDate = quoteDetails.getEntryDate();
				res.setResponse("Quote Updated Successfully");
			} else {
				entryDate = new Date();
				res.setResponse("Quote Created Successfully");
			}
			quoteDetails = mapper.map(req, QuoteDetails.class);
			quoteDetails.setStatus("Y");
			quoteDetails.setEntryDate(entryDate);
			quoteRepo.save(quoteDetails);

			log.info("Saved Details is ---> " + quoteDetails);
			res.setLeadid(req.getLeadId());

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public CrmReDirectLinkRes getCrmRedirectLink(CrmRedirectLinkReq req) {
		CrmReDirectLinkRes res = new CrmReDirectLinkRes();
		ModelMapper mapper = new ModelMapper();
		try {
			CrmRedirectDetails redirectData = redirectRepo.findByScreenName(req.getScreenName());
			if (redirectData != null) {
				String unencryptedString = "loginId=" + req.getLoginId() + "~password=" + "Admin@03" + "~usertype="
						+ req.getUserType() + "~branchcode=" + "08" + "~clientRefNo=" + req.getClientRefNo()
						+ "~leadId=" + req.getLeadId() + "~clientType=" + req.getClientType() + "~screenName="
						+ req.getScreenName();// +"~productId=";
				String afterEncrypt = encrypt.encrypt(unencryptedString);
				res.setEncodedValues(redirectData.getRedirectUrlLink() + afterEncrypt);
			}
			// Other Fields
			/*
			 * req.getPassword() req.getCustomerId() req.getProductId() req.getQuoteNo()
			 * req.getReferenceNo()
			 */

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return res;
	}

	//Enquiry List
	@Override
	public ClientLeadsGridRes getEnquiryListDetails(LeadViewReq req) {
		ClientLeadsGridRes res = new ClientLeadsGridRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			List<EnquiryGridRes> enquiryList = new ArrayList<EnquiryGridRes>();
			List<QuoteGridRes> quoteList = new ArrayList<QuoteGridRes>();
			List<PolicyDetailsGetAllRes> policyList = new ArrayList<PolicyDetailsGetAllRes>();
			
			List<EnquiryDetails>  enquirys = enquiryRepo.findByLeadIdOrderByUpdatedDateDesc (req.getLeadId() );		
			Type listType = new TypeToken<List<EnquiryGridRes>>() {}.getType();
			enquiryList = mapper.map(enquirys , listType); 
			
			List<QuoteDetails>  quotes = quoteRepo.findByLeadIdOrderByUpdatedDateDesc (req.getLeadId() );		
			listType = new TypeToken<List<QuoteGridRes>>() {}.getType();
			quoteList = mapper.map(quotes , listType); 
			
			List<PolicyDetails>  policies = policyRepo.findByLeadIdOrderByEntryDateDesc (req.getLeadId() );		
			listType = new TypeToken<List<PolicyDetailsGetAllRes>>() {}.getType();
			policyList = mapper.map(policies , listType); 
		
			res.setEnquiryList(enquiryList);
			res.setQuoteList(quoteList);
			res.setPolicyList(policyList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}

}
