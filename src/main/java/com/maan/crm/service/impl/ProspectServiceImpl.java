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

import com.google.gson.Gson;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.OldPolicyDetails;
import com.maan.crm.bean.ProspectDetails;
import com.maan.crm.bean.ProspectDetailsId;
import com.maan.crm.bean.ProspectOldPolicyDetails;
import com.maan.crm.bean.ProspectOldPolicyDetailsId;
import com.maan.crm.bean.ProspectPaymentDetails;
import com.maan.crm.bean.ProspectPaymentDetailsId;
import com.maan.crm.bean.ProspectQuotationAddOn;
import com.maan.crm.bean.ProspectQuotationAddOnId;
import com.maan.crm.bean.ProspectQuotationBasicInfo;
import com.maan.crm.bean.ProspectQuotationInsurerDetails;
import com.maan.crm.bean.ProspectQuotationInsurerDetailsId;
import com.maan.crm.bean.ProspectQuotationPolicyAccounts;
import com.maan.crm.bean.ProspectQuotationPolicyAccountsId;
import com.maan.crm.bean.ProspectQuotationVehicleDetails;
import com.maan.crm.bean.ProspectQuotationVehicleDetailsId;
import com.maan.crm.bean.ProspectsQuotationOtherDetails;
import com.maan.crm.bean.ProspectsQuotationOtherDetailsId;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.repository.OldPolicyRepository;
import com.maan.crm.repository.ProspectDetailsRepository;
import com.maan.crm.repository.ProspectOldPolicyDetailsRepository;
import com.maan.crm.repository.ProspectQuoationBasicInfoRepository;
import com.maan.crm.repository.ProspectQuotationAddOnRepository;
import com.maan.crm.repository.ProspectQuotationInsurerDetailsRepository;
import com.maan.crm.repository.ProspectQuotationPolicyAccountsRepository;
import com.maan.crm.repository.ProspectQuotationVehicelDetailsRepository;
import com.maan.crm.repository.ProspectRepository;
import com.maan.crm.repository.ProspectsQuotationOtherDetailsRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.LeadQuoteDetailsGetReq;
import com.maan.crm.req.ProspectBulkEditReq;
import com.maan.crm.req.ProspectDetailsGetAllReq;
import com.maan.crm.req.ProspectDetailsSaveReq;
import com.maan.crm.req.ProspectOldPolicyDetailsSaveReq;
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationInsurerGetReq;
import com.maan.crm.req.ProspectQuotationInsurerSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectQuotationSaveReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsSaveReq;
import com.maan.crm.req.ProspectReq;
import com.maan.crm.req.ProspectSearchReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.req.TrackingReq;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.LeadQuoteDetailsGetRes;
import com.maan.crm.res.ProspectDetailsRes;
import com.maan.crm.res.ProspectGetAllCountRes;
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
import com.maan.crm.service.ProspectService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class ProspectServiceImpl implements ProspectService {

	@Autowired
	private ProspectRepository repository;

	@Autowired
	private ProspectQuoationBasicInfoRepository repo;

	@Autowired
	private ProspectDetailsRepository prospectdetailsrepositoryl;

	@Autowired
	private ProspectOldPolicyDetailsRepository prospectoldpolicydetailsrepository;

	@Autowired
	private ProspectQuotationPolicyAccountsRepository prospectquotationpolicyaccountsrepository;

	@Autowired
	private ProspectsQuotationOtherDetailsRepository prospectsquotationotherdetailsrepository;

	@Autowired
	private ProspectQuotationAddOnRepository prospectquotationaddonrepository;

	@Autowired
	private ProspectQuotationInsurerDetailsRepository prospectquotationinsurerdetailsrepository;

	@Autowired
	private ProspectQuotationVehicelDetailsRepository prospectquotationvehiceldetailsrepository;

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private InsuranceCompanyMasterRepository insRepo;
	
	@Autowired
	private ClaimLoginMasterRepository loginRepo;
	
	@Autowired
	private MailThreadServiceImpl mailThreadService ;
	
	@Autowired
	private ClientAddressDetailsRepository clientAddRepo;
	
	@Autowired
	private CriteriaQueryServiceImpl Criteria;
	
	@Autowired
	private OldPolicyRepository oldPolicyRepo;
	
	@Autowired
	private LeadRepository leadRepo;

	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@Autowired
	private TrackingServiceImpl trackService ;
	
	
	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(ProspectServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Validate
	@Override
	public List<Error> validateProspectPayment(LeadDetailsJsonTempReq tempReq   ) {
		ProspectPaymentSaveReq req = tempReq.getProspectPayment();
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (req.getBankName() == null || StringUtils.isBlank(req.getBankName())) {
				errors.add(new Error("01", "Bank Name", "Please Enter Bank Name"));
			} else if (req.getBankName().length() > 100) {
				errors.add(new Error("01", "Bank Name", "Please Enter Bank Name within 100 Characters"));
			}
			if (req.getPaymentAmount() == null || StringUtils.isBlank(req.getPaymentAmount())) {
				errors.add(new Error("02", "Payment Amount", "Please Enter Payment Amount"));
			} else if (req.getPaymentAmount().length() > 100) {
				errors.add(new Error("02", "Payment Amount", "Please Enter Payment Amount within 100 Characters"));
			}
			
			if (req.getPaymentCollectedDate() == null
					|| StringUtils.isBlank(req.getPaymentCollectedDate().toString())) {
				errors.add(new Error("03", "Payment Collected Date", "Please Enter Payment Collected Date"));
			} //else if (!req.getPaymentCollectedDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
//				errors.add(new Error("03", "Payment Collected Date",
//						"Payment Collected Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
//			}

			if (req.getPaymentDate() == null || StringUtils.isBlank(req.getPaymentDate().toString())) {
				errors.add(new Error("04", "Payment Date", "Please Enter Payment Date"));
			}

//			else if (!req.getPaymentDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
//				errors.add(new Error("04", "Payment Date",
//						"Payment Date format should be dd/MM/yyyy only allowed. Example :- 08/06/2022"));
//			}
 
//			if (Integer.valueOf(req.getPaymentDetailsId()) == null
//					|| StringUtils.isBlank(Integer.toString(req.getPaymentDetailsId()))) {
//				errors.add(new Error("05", "Payment Details Id", "Please Enter Payment Details Id"));
//			} else if (!StringUtils.isNumeric(Integer.toString(req.getPaymentDetailsId()))) {
//				errors.add(new Error("05", "Payment Details Id", "Please Enter Payment Details Id in numbers"));
//			}
			if (req.getPaymentRefNo() == null || StringUtils.isBlank(req.getPaymentRefNo())) {
				errors.add(new Error("06", "Payment Ref No", "Please Enter Payment Ref No"));
			} else if (req.getPaymentRefNo().length() > 100) {
				errors.add(new Error("06", "Payment Ref No", "Please Enter Payment Ref No within 100 Characters"));
			}
		/*	if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("07", "Prospect Id", "Please Enter prospect Id"));
			}
			if (req.getPaymentType() == null || StringUtils.isBlank(req.getPaymentType())) {
				errors.add(new Error("07", "Prospect Type", "Please Enter Prospect Type"));
			}
			else if (req.getPaymentType().length() > 100) {
				errors.add(new Error("07", "Prospect Type", "Please Enter Prospect Type within 100 Characters"));
			}
		*/	if (Integer.valueOf(req.getPaymentTypeId()) == null ) {
				errors.add(new Error("08", "Payment Type Id", "Please Enter Payment Type Id"));
			} 
		/*	else if (!StringUtils.isNumeric(req.getPaymentTypeId())) {
				errors.add(new Error("08", "Prospect Type Id", "Please Enter Prospect Type Id"));
			}
			if (req.getRemarks() == null || StringUtils.isBlank(req.getRemarks())) {
				errors.add(new Error("09", "Remarks", "Please Enter Remarks"));
			}
		*/	 else if (req.getRemarks().length() > 100) {
				errors.add(new Error("10", "Remarks", "Please Enter Remarks within 100 Characters"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Save
	@Override
	public ProspectPaymentSuccessRes saveProspectPayment(LeadDetailsJsonTempReq tempReq ) {
		ProspectPaymentSaveReq req = tempReq.getProspectPayment();
		if(StringUtils.isNotBlank(req.getProspectId())) {
			// Remove the first character "L"
			// Convert the numeric string to an integer
			String prospectId = req.getProspectId();
			req.setProspectId(prospectId.substring(1));
		}
		ProspectPaymentSuccessRes res = new ProspectPaymentSuccessRes();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");
		Date entryDate = null;

		ModelMapper mapper = new ModelMapper();
		try {
			ProspectPaymentDetailsId id = new ProspectPaymentDetailsId();
	//		id.setProspectId(tempReq.getLeadDetails().getLeadId());
			id.setPaymentDetailsId(req.getPaymentDetailsId());

			Optional<ProspectPaymentDetails> data = repository.findById(id);
			if (data.isPresent()) {
				// Update
				ProspectPaymentDetails ent = mapper.map(req, ProspectPaymentDetails.class);
	//			ent.setProspectId(tempReq.getLeadDetails().getLeadId());
				ent.setEntryDate(req.getEntryDate());
				ent.setPaymentCollectedDate(req.getEntryDate());
				ent.setPaymentDetailsId(req.getPaymentDetailsId());
				repository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<ProspectPaymentDetails> list = repository.findAllByOrderByPaymentDetailsIdDesc();
				Integer paymentDetailsId = 1000;

				if (list.size() != 0) {
					paymentDetailsId = list.get(0).getPaymentDetailsId() + 1;
				}
				ProspectPaymentDetails ent = mapper.map(req, ProspectPaymentDetails.class);
				ent.setPaymentDetailsId(paymentDetailsId);
				ent.setEntryDate(new Date());
				repository.save(ent);

				res.setResponse("Inserted Successfully ");
				
				
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Get Prospect Id

	/*
	 * @Override public ProspectPaymentRes getProspect(ProspectPaymentGetReq req) {
	 * ProspectPaymentRes res = new ProspectPaymentRes(); try {
	 * 
	 * ProspectPaymentDetailsId id = new ProspectPaymentDetailsId();
	 * id.setPaymentDetailsId(req.getPaymentDetailsId());
	 * id.setProspectId(req.getProspectId()); Optional<ProspectPaymentDetails> opt =
	 * repository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectPaymentRes.class);
	 * 
	 * } } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 */

	// Prospect Quotation Basic Info Validate

	@Override
	public List<Error> validateProspectQuotation(ProspectQuotationSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (req.getClientName() == null || StringUtils.isBlank(req.getClientName())) {
				errors.add(new Error("01", "Client Name", "Please Enter Client Name"));
			} else if (req.getClientName().length() > 100) {
				errors.add(new Error("01", "Client Name", "Please Enter Client Name within 100 Characters"));
			}
			if (req.getClasss() == null || StringUtils.isBlank(req.getClasss())) {
				errors.add(new Error("02", "Class", "Please Enter Class"));
			} else if (req.getClasss().length() > 100) {
				errors.add(new Error("02", "Class", "Please Enter Class within 100 Characters"));
			}
			if (req.getExpiryDate() == null || StringUtils.isBlank(req.getExpiryDate())) {
				errors.add(new Error("03", "Expiry Date", "Please Enter Expiry Date"));
			} else if (!req.getExpiryDate().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
				errors.add(new Error("03", "Expiry Date",
						"Expiry Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
			}
			if (req.getOldPolicyNo() == null || StringUtils.isBlank(req.getOldPolicyNo())) {
				errors.add(new Error("04", "Old Policy No", "Please Enter Old policy No"));
			} else if (req.getOldPolicyNo().length() > 100) {
				errors.add(new Error("04", "Old Policy No", "Please Enter Old Policy No within 100 Characters"));
			}
			if (req.getPolicyTerm() == null || StringUtils.isBlank(req.getPolicyTerm())) {
				errors.add(new Error("05", "Policy Term", "Please Enter Policy Term"));
			} else if (req.getPolicyTerm().length() > 100) {
				errors.add(new Error("05", "Policy Term", "Please Enter Policy Term within 100 Characters"));
			}
			if (req.getPolicyTermId() == null || StringUtils.isBlank(req.getPolicyTermId())) {
				errors.add(new Error("06", "Policy Term Id", "Please Enter Policy Term Id"));
			}
			if (req.getPolicyType() == null || StringUtils.isBlank(req.getPolicyType())) {
				errors.add(new Error("07", "Policy Type", "Please Enter Policy Type"));
			} else if (req.getPolicyType().length() > 100) {
				errors.add(new Error("07", "Policy Type", "Please Enter Policy Type within 100 Characters"));
			}
			if (req.getPremiumCoverType() == null || StringUtils.isBlank(req.getPremiumCoverType())) {
				errors.add(new Error("08", "Policy Cover Type", "Please Enter Policy Cover Type "));
			}
			if (req.getPremiumCoverTypeId() == null || StringUtils.isBlank(req.getPremiumCoverTypeId())) {
				errors.add(new Error("09", "Premium Cover Type Id", "Please Enter Premium  Cover Type Id"));
			}
			if (req.getProspectId() == null || StringUtils.isBlank(req.getProspectId())) {
				errors.add(new Error("10", "Prospect Id", "Please Enter Prospect Id"));
			}
			if (req.getStartDate() == null || StringUtils.isBlank(req.getStartDate())) {
				errors.add(new Error("11", "Start Date", "Please Enter Start Date"));
			}

			else if (!req.getStartDate().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
				errors.add(new Error("11", "Start Date",
						"Start Date format should be dd/MM/yyyy only allowed . Example :- 15/12/2022"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Prospect Quotation Basic Info Save

	@Override
	public ProspectQuotationSuccessRes saveProspectQuotation(ProspectQuotationSaveReq req) {
		ProspectQuotationSuccessRes res = new ProspectQuotationSuccessRes();
		ProspectQuotationBasicInfo entity = new ProspectQuotationBasicInfo();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd");

		ModelMapper mapper = new ModelMapper();
		Integer quotationId = 0;
		Date entryDate = null;
		try {
			List<ProspectQuotationBasicInfo> data = repo.findByProspectId(Integer.valueOf(req.getProspectId()));
			if (data == null) {
				Long totalquotationId = repo.count();
				quotationId = Integer.valueOf(totalquotationId.toString()) + 10001;

				entryDate = new Date();
				res.setResponse("Saved Successfully ");
				res.setQuotationId(Integer.valueOf(quotationId).toString());
				entity.setEntryDate(entryDate);
				entity.setQuotationId(quotationId);

			} else {

				// update
				quotationId = Integer.valueOf(req.getQuotationId());
				data = repo.findByProspectId(Integer.valueOf(req.getProspectId()));
				res.setResponse("Updated Successfully ");
				res.setQuotationId(Integer.valueOf(quotationId).toString());
				entity.setEntryDate(entryDate);
				entity.setQuotationId(quotationId);
			}
			entity.setProspectId(Integer.valueOf(req.getProspectId()));
			entity.setEntryDate(entryDate);
			entity.setExpiryDate(sdf.parse(req.getExpiryDate().toString()));
			entity.setStartDate(sdf.parse(req.getStartDate().toString()));
			entity.setClasss(req.getClasss());
			entity.setClientName(req.getClientName());
			entity.setOldPolicyNo(req.getOldPolicyNo().toString());
			entity.setPolicyTerm(req.getPolicyTerm());
			entity.setPolicyType(req.getPolicyType());
			entity.setPremiumCoverType(req.getPremiumCoverType());
			entity.setPremiumCoverTypeId(Integer.valueOf(req.getPremiumCoverTypeId()));
			entity.setPolicyTermId(Integer.valueOf(req.getPolicyTermId()));
			repo.save(entity);
			log.info("Saved Details is ---> " + json.toJson(entity));

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Prospect Quotation Get By Quotation Id

	/*
	 * @Override public ProspectQuoationRes getQuotation(ProspectQuotaionGetReq req)
	 * { ProspectQuoationRes res = new ProspectQuoationRes(); ModelMapper mapper =
	 * new ModelMapper(); try { SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd/MM/yyyy"); ProspectQuotationBasicInfo data =
	 * repo.findByQuotationId(Integer.valueOf(req.getQuotationId()));
	 * 
	 * res.setClasss(data.getClasss() == null ? "" : data.getClasss());
	 * res.setClientName(data.getClientName() == null ? "" : data.getClientName());
	 * res.setEntryDate(data.getEntryDate() == null ? "" :
	 * sdf.format(data.getEntryDate())); res.setExpiryDate(data.getExpiryDate() ==
	 * null ? "" : sdf.format(data.getEntryDate()));
	 * res.setOldPolicyNo(data.getOldPolicyNo() == null ? "" :
	 * data.getOldPolicyNo()); res.setPolicyTerm(data.getPolicyTerm() == null ? "" :
	 * data.getPolicyTerm()); res.setPolicyTermId(data.getPolicyTermId() == null ?
	 * "" : data.getPolicyTermId().toString());
	 * res.setPolicyType(data.getPolicyType() == null ? "" : data.getPolicyType());
	 * res.setPremiumCoverType(data.getPremiumCoverType() == null ? "" :
	 * data.getPremiumCoverType());
	 * 
	 * res.setPremiumCoverTypeId( data.getPremiumCoverTypeId() == null ? "" :
	 * data.getPremiumCoverTypeId().toString());
	 * res.setProspectId(data.getProspectId() == null ? "" :
	 * data.getProspectId().toString()); res.setQuotationId(data.getQuotationId() ==
	 * null ? "" : data.getQuotationId().toString());
	 * res.setStartDate(data.getStartDate() == null ? "" :
	 * sdf.format(data.getStartDate())); } catch (Exception e) {
	 * e.printStackTrace(); log.info("Exception is ---> " + e.getMessage()); return
	 * null; } return res;
	 * 
	 * }
	 */
	@Override
	public SuccessRes saveProspectDetails(ProspectDetailsSaveReq req) {

		SuccessRes res = new SuccessRes();
		try {
			ClientDetails clientdetails = clientrepo.findByClientRefNo(req.getClientRefNo());
			InsuranceCompanyMaster companyData = insRepo.findByInsId(clientdetails.getInsCompanyId());
			ClaimLoginMaster loginData = loginRepo.findByLoginId(clientdetails.getCreatedBy());
			
			ProspectDetailsId id = new ProspectDetailsId();
			id.setClientRefNo(req.getClientRefNo());
			//id.setProspectid(req.getProspectId());
			// id.setLeadId(req.getLeadId());
			Integer prospectid=0;
			ProspectDetails opt  = null ;
			if(req.getProspectId() !=null && StringUtils.isNotBlank(req.getProspectId().toString()) && req.getProspectId() != 0){
				opt = prospectdetailsrepositoryl.findByProspectidOrderByProspectidAsc(req.getProspectId());			
			}
			// Update
			if (opt!=null) {
				prospectid = req.getProspectId();
				ModelMapper modelMapper = new ModelMapper();
				ProspectDetails ent = modelMapper.map(req, ProspectDetails.class);
				ent.setAssignToGroupId(req.getAssignToGroupId());
				ent.setGenerationDate(opt.getGenerationDate() );
				ent.setEntryDate(opt.getEntryDate()  );
				ProspectDetails response = prospectdetailsrepositoryl.save(ent);
				res.setResponse("Updated Sucessfully");
				res.setSucessId(String.valueOf(response.getProspectid()));
				
				// Tracking
				TrackingReq treq  = new TrackingReq(); 
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(clientdetails.getBranchCode());
				treq.setEntryDate(new Date());
				treq.setInsCompanyId(clientdetails.getInsCompanyId());
				treq.setRegionCode(clientdetails.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientdetails.getClientName());
				treq.setProspectId(prospectid.toString());
				treq.setStatus("Prospect");
				treq.setStatusDescription("Prospect Updated Successful");
				trackService.tracking(treq);
			
			}
			// Insert
			else {

				prospectid = 1000;

				List<ProspectDetails> list = prospectdetailsrepositoryl.findAllByOrderByProspectidDesc();
				if (list.size() != 0) {
					prospectid = list.get(0).getProspectid() + 1;
				}
				ModelMapper modelMapper = new ModelMapper();
				ProspectDetails ent = modelMapper.map(req, ProspectDetails.class);
				ent.setProspectid(prospectid);
				ent.setGenerationDate(new Date());
				ent.setEntryDate(new Date());
				ent.setAssignToGroupId(req.getAssignToGroupId());
				ProspectDetails response = prospectdetailsrepositoryl.saveAndFlush(ent);
				res.setResponse("Inserted Sucessfully");
				res.setSucessId(String.valueOf(response.getProspectid()));
				
				// Tracking
				TrackingReq treq  = new TrackingReq(); 
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(clientdetails.getBranchCode());
				treq.setEntryDate(new Date());
				treq.setLeadId(req.getLeadId());
				treq.setInsCompanyId(clientdetails.getInsCompanyId());
				treq.setRegionCode(clientdetails.getRegionCode());
				treq.setClientRefNo(req.getClientRefNo());
				treq.setClientName(clientdetails.getClientName());
				treq.setProspectId(prospectid.toString());
				treq.setStatus("Prospect");
				treq.setStatusDescription("Prospect Inserted Successful");
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
			keys.put("PROSPECTID", Integer.valueOf(prospectid) ==null?"": prospectid);
			
			// Set Mail Request
			
			MailFramingReq mailFrameReq  = new MailFramingReq();
			mailFrameReq.setInsId(clientdetails.getInsCompanyId());
			mailFrameReq.setNotifTemplateId("PROSPECT_INFO");
			mailFrameReq.setKeys(keys);
			mailFrameReq.setMailCc(ccMails);
			mailFrameReq.setMailRegards(companyData.getRegards());
			mailFrameReq.setStatus(res.getResponse());
			mailFrameReq.setMailTo(toMails);			
			log.info("{ Mail Pushed Successfully . Prospect Id is --->" + prospectid + "}");
			
			mailThreadService.threadToSendMail(mailFrameReq);
		
				
			
			} catch (Exception e) {
				e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			res = null;
		}
		return res;
	}

	/*
	 * @Override public ProspectDetailsRes getProspectDetails(ProspectDetailsSaveReq
	 * req) {
	 * 
	 * ProspectDetailsRes res = new ProspectDetailsRes(); try {
	 * 
	 * ProspectDetailsId id = new ProspectDetailsId();
	 * id.setClientRefNo(req.getClientRefNo());
	 * id.setProspectid(req.getProspectId());
	 * 
	 * Optional<ProspectDetails> opt = prospectdetailsrepositoryl.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectDetailsRes.class);
	 * 
	 * } } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 */
	@Override
	public SuccessRes saveProspectOldPolicyDetails(ProspectOldPolicyDetailsSaveReq req) {

		SuccessRes res = new SuccessRes();
		try {

			ProspectOldPolicyDetailsId id = new ProspectOldPolicyDetailsId();
			id.setOldPolicyId(req.getOldPolicyId());
			id.setProspectId(req.getProspectId());
			Optional<ProspectOldPolicyDetails> opt = prospectoldpolicydetailsrepository.findById(id);

			// Update
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ProspectOldPolicyDetails ent = modelMapper.map(req, ProspectOldPolicyDetails.class);
				prospectoldpolicydetailsrepository.save(ent);
				res.setResponse("Updated Sucessfully");

			}
			// Insert
			else {

				int oldid = 1000;
				List<ProspectOldPolicyDetails> list = prospectoldpolicydetailsrepository
						.findAllByOrderByOldPolicyIdDesc();
				if (list.size() != 0) {
					oldid = list.get(0).getOldPolicyId() + 1;
				}

				ModelMapper modelMapper = new ModelMapper();
				ProspectOldPolicyDetails ent = modelMapper.map(req, ProspectOldPolicyDetails.class);
				ent.setOldPolicyId(oldid);
				prospectoldpolicydetailsrepository.save(ent);
				res.setResponse("Inserted Sucessfully");
			}

		} catch (Exception e) {
			log.info("Exception Error", e.getMessage());
			res = null;
		}
		return res;
	}

	/*
	 * @Override public ProspectOldPolicyDetailsRes
	 * getProspectOldPolicyDetails(ProspectDetailsSaveReq req) {
	 * 
	 * ProspectOldPolicyDetailsRes res = new ProspectOldPolicyDetailsRes(); try {
	 * 
	 * ProspectOldPolicyDetailsId id = new ProspectOldPolicyDetailsId();
	 * Optional<ProspectOldPolicyDetails> opt =
	 * prospectoldpolicydetailsrepository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectOldPolicyDetailsRes.class); }
	 * 
	 * } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 * 
	 */ @Override
	public SuccessRes saveProspectQuotationPolicyAccounts(ProspectQuotationPolicyAccountsSaveReq req) {

		SuccessRes res = new SuccessRes();
		try {

			ProspectQuotationPolicyAccountsId id = new ProspectQuotationPolicyAccountsId();
			id.setPolicyAccId(req.getPolicyAccId());
			id.setProspectId(req.getProspectId());
			Optional<ProspectQuotationPolicyAccounts> opt = prospectquotationpolicyaccountsrepository.findById(id);

			// Update
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ProspectQuotationPolicyAccounts ent = modelMapper.map(req, ProspectQuotationPolicyAccounts.class);
				prospectquotationpolicyaccountsrepository.save(ent);
				res.setResponse("Updated Sucessfully");

			}
			// Insert
			else {

				ModelMapper modelMapper = new ModelMapper();
				ProspectQuotationPolicyAccounts ent = modelMapper.map(req, ProspectQuotationPolicyAccounts.class);

				int policyAccId = 1000;
				List<ProspectQuotationPolicyAccounts> list = prospectquotationpolicyaccountsrepository
						.findAllByOrderByPolicyAccIdDesc();
				if (list.size() != 0) {
					policyAccId = list.get(0).getPolicyAccId() + 1;
				}
				ent.setPolicyAccId(policyAccId);

				prospectquotationpolicyaccountsrepository.save(ent);
				res.setResponse("Inserted Sucessfully");
				
			}
			
			

		} catch (Exception e) {
			log.info("Exception Error", e.getMessage());
			res = null;
		}
		return res;
	}

	/*
	 * @Override public ProspectQuotationPolicyAccountsRes
	 * getProspectQuotationPolicyAccounts( ProspectQuotationPolicyAccountsSaveReq
	 * req) {
	 * 
	 * ProspectQuotationPolicyAccountsRes res = new
	 * ProspectQuotationPolicyAccountsRes(); try {
	 * 
	 * ProspectQuotationPolicyAccountsId id = new
	 * ProspectQuotationPolicyAccountsId(); id.setPolicyAccId(req.getPolicyAccId());
	 * id.setProspectId(req.getProspectId());
	 * Optional<ProspectQuotationPolicyAccounts> opt =
	 * prospectquotationpolicyaccountsrepository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectQuotationPolicyAccountsRes.class); } } catch (Exception e) { res =
	 * null; log.info("Exception Error", e.getMessage()); }
	 * 
	 * return res;
	 * 
	 * }
	 */
	@Override
	public SuccessRes saveProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req) {

		SuccessRes res = new SuccessRes();
		try {

			ProspectQuotationAddOnId id = new ProspectQuotationAddOnId();
			id.setAddOnid(req.getAddOnid());
			id.setProspectId(req.getProspectId());

			Optional<ProspectQuotationAddOn> opt = prospectquotationaddonrepository.findById(id);

			// Update
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ProspectQuotationAddOn ent = modelMapper.map(req, ProspectQuotationAddOn.class);
				prospectquotationaddonrepository.save(ent);
				res.setResponse("Updated Sucessfully");

			}
			// Insert
			else {

				ModelMapper modelMapper = new ModelMapper();
				ProspectQuotationAddOn ent = modelMapper.map(req, ProspectQuotationAddOn.class);

				int addon = 1000;
				List<ProspectQuotationAddOn> list = prospectquotationaddonrepository.findAllByOrderByAddOnidDesc();
				if (list.size() != 0) {
					addon = list.get(0).getAddOnid() + 1;
				}
				ent.setAddOnid(addon);

				prospectquotationaddonrepository.save(ent);
				res.setResponse("Inserted Sucessfully");
			}

		} catch (Exception e) {
			log.info("Exception Error", e.getMessage());
			res = null;
		}
		return res;
	}

	/*
	 * @Override public ProspectQuotationAddOnRes
	 * getProspectQuotationAddOn(ProspectQuotationAddOnSaveReq req) {
	 * 
	 * ProspectQuotationAddOnRes res = new ProspectQuotationAddOnRes(); try {
	 * 
	 * ProspectQuotationAddOnId id = new ProspectQuotationAddOnId();
	 * id.setAddOnid(req.getAddOnid()); id.setProspectId(req.getProspectId());
	 * 
	 * Optional<ProspectQuotationAddOn> opt =
	 * prospectquotationaddonrepository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectQuotationAddOnRes.class);
	 * 
	 * } } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 */
	// Prospect Quotation Insurer Details Validation
	@Override
	public List<Error> validateProspectQuotationInsurer(ProspectQuotationInsurerSaveReq req) {

		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (req.getInsurancePlan() == null || StringUtils.isBlank(req.getInsurancePlan())) {
				errors.add(new Error("01", "Insurance Plan", "Please Enter Insurance Plan"));
			}
			if (req.getInsurer() == null || StringUtils.isBlank(req.getInsurer())) {
				errors.add(new Error("02", "Insurer", "Please Enter Insurer"));
			}
			if (req.getInsurerBranch() == null || StringUtils.isBlank(req.getInsurerBranch())) {
				errors.add(new Error("03", "Insurer Branch", "Please Enter Insurer Branch"));
			}
			if (Integer.valueOf(req.getInsurerBranchId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getInsurerBranchId()))) {
				errors.add(new Error("04", "Insurer Branch Id", "Please Enter Insurer Branch Id"));
			}
			if (Integer.valueOf(req.getInsurerId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getInsurerId()))) {
				errors.add(new Error("05", "Insurer Id", "Please Enter Insurer Id"));
			}
			if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("06", "Prospect Id", "Please Enter Prospect Id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Prospect Quotation Insurer Details Save
	@Override
	public ProspectQuotationInsurerSuccessRes saveProspectQuotationInsurer(ProspectQuotationInsurerSaveReq req) {

		ProspectQuotationInsurerSuccessRes res = new ProspectQuotationInsurerSuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			ProspectQuotationInsurerDetailsId id = new ProspectQuotationInsurerDetailsId();

			id.setInsurer(req.getInsurer());
			id.setInsurerDetailsId(req.getInsurerDetailsId());
			id.setInsurerId(req.getInsurerId());
			id.setProspectId(req.getProspectId());

			Optional<ProspectQuotationInsurerDetails> data = prospectquotationinsurerdetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				ProspectQuotationInsurerDetails ent = mapper.map(req, ProspectQuotationInsurerDetails.class);
				prospectquotationinsurerdetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<ProspectQuotationInsurerDetails> list = prospectquotationinsurerdetailsrepository
						.findAllByOrderByInsurerDetailsIdDesc();
				Integer insurerDetailsId = 1000;

				if (list.size() != 0) {
					insurerDetailsId = list.get(0).getInsurerDetailsId() + 1;
				}
				ProspectQuotationInsurerDetails ent = mapper.map(req, ProspectQuotationInsurerDetails.class);
				ent.setInsurerDetailsId(insurerDetailsId);
				ent.setEntryDate(new Date());
				prospectquotationinsurerdetailsrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	@Override
	public SuccessRes saveProspectsQuotationOtherDetails(ProspectsQuotationOtherDetailsSaveReq req) {

		SuccessRes res = new SuccessRes();
		try {

			ProspectsQuotationOtherDetailsId id = new ProspectsQuotationOtherDetailsId();
			id.setOtherDetailsId(req.getOtherDetailsId());
			id.setProspectId(req.getProspectId());
			Optional<ProspectsQuotationOtherDetails> opt = prospectsquotationotherdetailsrepository.findById(id);

			// Update
			if (opt.isPresent()) {

				ModelMapper modelMapper = new ModelMapper();
				ProspectsQuotationOtherDetails ent = modelMapper.map(req, ProspectsQuotationOtherDetails.class);
				prospectsquotationotherdetailsrepository.save(ent);
				res.setResponse("Updated Sucessfully");
			}
			// Insert
			else {

				ModelMapper modelMapper = new ModelMapper();
				ProspectsQuotationOtherDetails ent = modelMapper.map(req, ProspectsQuotationOtherDetails.class);

				int count = 1000;
				List<ProspectsQuotationOtherDetails> list = prospectsquotationotherdetailsrepository
						.findAllByOrderByOtherDetailsIdDesc();
				if (list.size() != 0) {
					count = list.get(0).getOtherDetailsId() + 1;
				}
				ent.setOtherDetailsId(count);

				prospectsquotationotherdetailsrepository.save(ent);
				res.setResponse("Inserted Sucessfully");
			}

		} catch (Exception e) {
			log.info("Exception Error", e.getMessage());
			res = null;
		}
		return res;
	}

	// Prospect Quotation Insurer Get By Insurer Id

	/*
	 * @Override public ProspectQuotationInsurerRes
	 * getProspectInsurer(ProspectQuotationInsurerGetReq req) {
	 * ProspectQuotationInsurerRes res = new ProspectQuotationInsurerRes(); try {
	 * 
	 * ProspectQuotationInsurerDetailsId id = new
	 * ProspectQuotationInsurerDetailsId(); id.setInsurer(req.getInsurer());
	 * id.setInsurerDetailsId(req.getInsurerDetailsId());
	 * id.setInsurerId(req.getInsurerId()); id.setProspectId(req.getProspectId());
	 * 
	 * Optional<ProspectQuotationInsurerDetails> opt =
	 * prospectquotationinsurerdetailsrepository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectQuotationInsurerRes.class);
	 * 
	 * } } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 */
	/*
	 * @Override public ProspectsQuotationOtherDetailsRes
	 * getProspectsQuotationOtherDetails( ProspectsQuotationOtherDetailsSaveReq req)
	 * {
	 * 
	 * ProspectsQuotationOtherDetailsRes res = new
	 * ProspectsQuotationOtherDetailsRes(); try {
	 * 
	 * ProspectsQuotationOtherDetailsId id = new ProspectsQuotationOtherDetailsId();
	 * id.setOtherDetailsId(req.getOtherDetailsId());
	 * id.setProspectId(req.getProspectId());
	 * Optional<ProspectsQuotationOtherDetails> opt =
	 * prospectsquotationotherdetailsrepository.findById(id);
	 * 
	 * // Update if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectsQuotationOtherDetailsRes.class);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res;
	 * 
	 * }
	 * 
	 */ // Prospect Quotation Vehicle Details Validation

	@Override
	public List<Error> validateProspectQuotationVehicle(ProspectQuotationVehicleDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (req.getChassisNumber() == null || StringUtils.isBlank(req.getChassisNumber())) {
				errors.add(new Error("01", "Chassis Number", "Please Enter Chassis Number"));
			}
			if (req.getCubicCapacity() == null || StringUtils.isBlank(req.getCubicCapacity())) {
				errors.add(new Error("02", "Cubic Capacity", "Please Enter Cubic Capacity"));
			}
			if (req.getElectronicAccessoryValue() == null || StringUtils.isBlank(req.getElectronicAccessoryValue())) {
				errors.add(new Error("03", "Electronic Accessory Value", "Please Enter Electronic Accessory Value"));
			}
			if (req.getEngineNumber() == null || StringUtils.isBlank(req.getEngineNumber())) {
				errors.add(new Error("04", "Engine Number", "Please Enter Engine Number"));
			}
			if (req.getIdv() == null || StringUtils.isBlank(req.getIdv())) {
				errors.add(new Error("05", "Idv", "Please Enter IDV"));
			}
			if (req.getMakeModelVariant() == null || StringUtils.isBlank(req.getMakeModelVariant())) {
				errors.add(new Error("06", "Make Model Variant", "Please Enter Make Model Variant"));
			}
			if (Integer.valueOf(req.getOtherthanElecAccValue()) == null
					|| StringUtils.isBlank(Integer.toString(req.getOtherthanElecAccValue()))) {
				errors.add(new Error("07", "Other Than Elec Acc Value", "Please Enter Other Than Elec Acc Value"));
			}
			if (req.getOtherthanElecAccValueYn() == null || StringUtils.isBlank(req.getOtherthanElecAccValueYn())) {
				errors.add(
						new Error("08", "Other than Elec Acc Value YN", "Please Enter Other than Elec Acc Value YN"));
			}
			if (Integer.valueOf(req.getProspectId()) == null
					|| StringUtils.isBlank(Integer.toString(req.getProspectId()))) {
				errors.add(new Error("09", "Prospect Id", "Please Enter Prospect Id"));
			}
			if (req.getRegistrationNumber() == null || StringUtils.isBlank(req.getRegistrationNumber())) {
				errors.add(new Error("10", "Registration Number", "Please Enter Registration  Number"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return errors;
		}
		return errors;
	}

	// Prospect Quotation Vehicle Details Save

	@Override
	public ProspectQuotationInsurerSuccessRes saveProspectQuotationVehicle(ProspectQuotationVehicleDetailsSaveReq req) {
		ProspectQuotationInsurerSuccessRes res = new ProspectQuotationInsurerSuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			ProspectQuotationVehicleDetailsId id = new ProspectQuotationVehicleDetailsId();

			id.setQuotationVechid(req.getQuotationVehicleId());
			id.setProspectId(req.getProspectId());

			Optional<ProspectQuotationVehicleDetails> data = prospectquotationvehiceldetailsrepository.findById(id);
			if (data.isPresent()) {
				// Update
				ProspectQuotationVehicleDetails ent = mapper.map(req, ProspectQuotationVehicleDetails.class);
				ent.setQuotationVechid(req.getQuotationVehicleId());
				prospectquotationvehiceldetailsrepository.save(ent);
				res.setResponse("Updated Successfully ");

			} else {

				// Insert
				List<ProspectQuotationVehicleDetails> list = prospectquotationvehiceldetailsrepository
						.findAllByOrderByQuotationVechidDesc();
				Integer quotationVehicleId = 1000;

				if (list.size() != 0) {
					quotationVehicleId = list.get(0).getQuotationVechid() + 1;
				}
				ProspectQuotationVehicleDetails ent = mapper.map(req, ProspectQuotationVehicleDetails.class);
				ent.setQuotationVechid(quotationVehicleId);
				;
				ent.setEntryDate(new Date());
				prospectquotationvehiceldetailsrepository.save(ent);

				res.setResponse("Inserted Successfully ");
			}

		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	}

	// Prospect Quotation Vehicle Details Get

	/*
	 * @Override public ProspectQuotationVehicleDetailsRes
	 * getProspectVehicle(ProspectQuotationVehicleDetailsGetReq req) {
	 * ProspectQuotationVehicleDetailsRes res = new
	 * ProspectQuotationVehicleDetailsRes(); try {
	 * 
	 * ProspectQuotationVehicleDetailsId id = new
	 * ProspectQuotationVehicleDetailsId(); id.setProspectId(req.getProspectId());
	 * id.setQuotationVechid(req.getQuotationVechId());
	 * Optional<ProspectQuotationVehicleDetails> opt =
	 * prospectquotationvehiceldetailsrepository.findById(id);
	 * 
	 * if (opt.isPresent()) {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); res = modelMapper.map(opt.get(),
	 * ProspectQuotationVehicleDetailsRes.class);
	 * 
	 * } } catch (Exception e) { res = null; log.info("Exception Error",
	 * e.getMessage()); } return res; }
	 */
	@Override
	public List<ProspectPaymentRes> getAllProspect() {
		List<ProspectPaymentRes> res = new ArrayList<ProspectPaymentRes>();
		try {

			List<ProspectPaymentDetails> opt = repository.findAll();

			for (ProspectPaymentDetails prospectPaymentDetails : opt) {

				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(prospectPaymentDetails, ProspectPaymentRes.class));

			}

		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	@Override
	public ProspectRes getProspect(ProspectReq req) {
		ProspectRes res = new ProspectRes();
		try {

			List<ProspectPaymentDetails> opt = repository.findByProspectId(req.getProspectId());
			if (!opt.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setPaymentRes(modelMapper.map(opt.get(0), ProspectPaymentRes.class));
			}

			List<ProspectQuotationInsurerDetails> opt1 = prospectquotationinsurerdetailsrepository
					.findByProspectId(req.getProspectId());
			if (!opt1.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setInsurerDetails(modelMapper.map(opt1.get(0), ProspectQuotationInsurerRes.class));
			}

			List<ProspectQuotationBasicInfo> opt2 = repo.findByProspectId(req.getProspectId());
			if (!opt2.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setQuotationBasic(modelMapper.map(opt2.get(0), ProspectQuoationRes.class));
			}

			List<ProspectQuotationVehicleDetails> opt3 = prospectquotationvehiceldetailsrepository
					.findByProspectId(req.getProspectId());
			if (!opt3.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setVehicleDetails(modelMapper.map(opt3.get(0), ProspectQuotationVehicleDetailsRes.class));
			}

			//Tracking_Details Tables -> Prospect_id,Lead_Id 
			LeadDetails opt4 = leadRepo.findByLeadId(req.getProspectId().toString());
			if (opt4!=null) {
				ClientDetails clientData = clientrepo.findByClientRefNo(opt4.getClientRefNo());
				ModelMapper modelMapper = new ModelMapper();
				ProspectDetailsRes details = modelMapper.map(opt4, ProspectDetailsRes.class);
		//		details.setProspectId(opt4.getLeadId());
				details.setClientName(clientData.getClientName());
				details.setClientRefNo(clientData.getClientRefNo());
				details.setClientType(clientData.getClientType()); 
				details.setCrno(clientData.getCrno() );
				details.setWillProvideReference(clientData.getWillProvideReference());
				details.setGroupClient(clientData.getGroupClient());
				details.setIsGroupClient(clientData.getIsGroupClient());
				res.setProspectDetails(details);

			}
			OldPolicyDetails opt5 = new OldPolicyDetails(); 
			if (StringUtils.isNotBlank(opt4.getOldPolicyNo())) {
				opt5 = oldPolicyRepo.findByOldPolicyNo(opt4.getOldPolicyNo() );
			}
					
			

			List<ProspectQuotationAddOn> opt6 = prospectquotationaddonrepository.findByProspectId(req.getProspectId());
			if (!opt6.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setProspectQuotationAddOn(modelMapper.map(opt6.get(0), ProspectQuotationAddOnRes.class));
			}

			List<ProspectQuotationPolicyAccounts> opt7 = prospectquotationpolicyaccountsrepository
					.findByProspectId(req.getProspectId());
			if (!opt7.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setProspectQuotationPolicyAccount(
						modelMapper.map(opt7.get(0), ProspectQuotationPolicyAccountsRes.class));
			}

			List<ProspectsQuotationOtherDetails> opt8 = prospectsquotationotherdetailsrepository
					.findByProspectId(req.getProspectId());
			if (!opt8.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				res.setProspectQuotationOtherDetail(
						modelMapper.map(opt8.get(0), ProspectsQuotationOtherDetailsRes.class));
			}

		} catch (Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	@Override
	public ProspectDetailsRes getProspectDetails(ProspectDetailsSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProspectQuotationPolicyAccountsRes getProspectQuotationPolicyAccounts(
			ProspectQuotationPolicyAccountsSaveReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProspectQuotationInsurerRes getProspectInsurer(ProspectQuotationInsurerGetReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProspectDetailsRes> getAllProspectDetails(ProspectDetailsGetAllReq req) {
		List<ProspectDetailsRes> resList = new ArrayList<ProspectDetailsRes>();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 1000 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("dueDate").descending());

			// Find
			Page<LeadDetails> leadDetails = leadRepo.findByInsCompanyIdAndBranchCodeAndStatusOrderByUpdatedDateDesc(paging, req.getInsId(),req.getBranchCode(), "Quote");
			// Map

			for (LeadDetails data : leadDetails.getContent()) {
				ProspectDetailsRes res = new ProspectDetailsRes();
				ClientDetails clientName = clientrepo.findByClientRefNo(data.getClientRefNo());
				mapper.getConfiguration().setAmbiguityIgnored(true);
				res = mapper.map(data, ProspectDetailsRes.class);
	//			res.setProspectId(data.getLeadId());

				res.setClientName(clientName.getClientName());
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Prospect Bulk Edit Validation

	@Override
	public List<Error> validateProspect(ProspectBulkEditReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	// Prospect Bulk Edit Save
	@Override
	public SuccessRes bulkEditProspect(ProspectBulkEditReq req) {
		SuccessRes res = new SuccessRes();
		try {
			if (req.getProspectid() != null && req.getProspectid().size() > 0) {
				List<Integer> prospectIds = req.getProspectid();
				// Get List
				List<ProspectDetails> getDatas = prospectdetailsrepositoryl.findByProspectidIn(prospectIds);

				for (ProspectDetails data : getDatas) {

					data.setAssigntoGroup(StringUtils.isBlank(req.getChangeAssigntoGroup()) ? data.getAssigntoGroup()
							: req.getChangeAssigntoGroup());
					data.setAssignToGroupId(
							StringUtils.isBlank(req.getChangeAssignToGroupId()) ? data.getAssignToGroupId()
									: Integer.valueOf(req.getChangeAssignToGroupId()));
					data.setAssigntoUser(StringUtils.isBlank(req.getChangeAssigntoUser()) ? data.getAssigntoUser()
							: req.getChangeAssigntoUser());
					data.setAssigntoUserId(StringUtils.isBlank(req.getChangeAssigntoUserId()) ? data.getAssigntoUserId()
							: Integer.valueOf(req.getChangeAssigntoUserId()));
					data.setPos(StringUtils.isBlank(req.getChangePos()) ? data.getPos() : req.getChangePos());
					data.setPosId(StringUtils.isBlank(req.getChangePosId()) ? data.getPosId()
							: Integer.valueOf(req.getChangePosId()));
					data.setProspectOwner(StringUtils.isBlank(req.getChangeProspectOwner()) ? data.getProspectOwner()
							: req.getChangeProspectOwner());
					data.setProspectOwnerId(
							StringUtils.isBlank(req.getChangeProspectOwnerId()) ? data.getProspectOwnerId()
									: Integer.valueOf(req.getChangeProspectOwnerId()));
					data.setClassification(StringUtils.isBlank(req.getChangeClassification()) ? data.getClassification()
							: req.getChangeClassification());
					data.setClassificationId(
							StringUtils.isBlank(req.getChangeClassificationId()) ? data.getClassificationId()
									: Integer.valueOf(req.getChangeClassificationId()));
					data.setSource(
							StringUtils.isBlank(req.getChangeSource()) ? data.getSource() : req.getChangeSource());
					data.setSourceId(StringUtils.isBlank(req.getChangeSourceId()) ? data.getSourceId()
							: Integer.valueOf(req.getChangeSourceId()));
					data.setLocation(StringUtils.isBlank(req.getChangeLocation()) ? data.getLocation()
							: req.getChangeLocation());
					prospectdetailsrepositoryl.save(data);
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
	public ProspectGetAllCountRes getAllProspectCount(ProspectDetailsGetAllReq req) {
		ProspectGetAllCountRes res = new ProspectGetAllCountRes();
		ModelMapper mapper = new ModelMapper(); 
		try {

			// Limit, Offset
			int limit = StringUtils.isBlank(req.getLimit())? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset())? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("updatedDate").descending());
				
			// Find
			String status = "Quote";
			Page<LeadDetails> leadDetails = leadRepo.findByInsCompanyIdAndBranchCodeAndStatus(paging, req.getInsId(),req.getBranchCode(),status);
			Long count = leadRepo.countByInsCompanyIdAndBranchCodeAndStatus(req.getInsId(), req.getBranchCode(),status);
			List<CrmLeadRes> leadlist = new ArrayList<CrmLeadRes>();
			for(LeadDetails data : leadDetails.getContent())
			{
				CrmLeadRes leaddata = new CrmLeadRes();
				leaddata = mapper.map(data, CrmLeadRes.class);
				leadlist.add(leaddata);
			}
			res.setProspectDetails(leadlist);
			res.setProspectCount(count);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return res;
		}

	@Override
	public ProspectSearchRes searchProspectDetails(ProspectSearchReq req) {
		ProspectSearchRes resa = new ProspectSearchRes();
		ModelMapper mapper = new ModelMapper();
		List<LeadDetails> leadDetails = new ArrayList<LeadDetails>();
		try {
			String searchValue = req.getSearchValue();// .toLowerCase() ;
			
			if (StringUtils.isNotBlank(searchValue) && searchValue.matches("[0-9]+" ) && searchValue.length()<=5) {
				leadDetails = Criteria.ProspectDynamicSearch5(req);		}
			
			if ( leadDetails.size()<=0 ) {
				leadDetails = Criteria.ProspectDynamicSearch6(req);
			}
		
			
			if (leadDetails != null && leadDetails.size() > 0) {
				// Map List
				List<CrmLeadRes> leadres = new ArrayList<CrmLeadRes>();
				for (LeadDetails data : leadDetails) {
					CrmLeadRes res = new CrmLeadRes();

					res = mapper.map(data, CrmLeadRes.class);
					ClientDetails clientData = clientrepo.findByClientRefNo(data.getClientRefNo());
					//	res.setLeadCount(leadRepo.countByClientRefNo(data.getClientRefNo()).toString());
					res.setUserNameWithMobile(res.getClientName() + "-" + clientData.getMobileNumber());
					res.setMobileNumber(clientData.getMobileNumber());
					res.setAlternativeNumber(clientData.getAlternativeNumber());
					res.setEmailId(clientData.getEmailId());
					leadres.add(res);

				}
				resa.setProspectCount(Long.valueOf(leadDetails.size()));
				resa.setProspectDetails(leadres);
				} 

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return resa;
	}
//
	@Override
	public List<LeadQuoteDetailsGetRes> leadQuoteDetails(LeadQuoteDetailsGetReq req) {
	List<LeadQuoteDetailsGetRes> resList = new ArrayList<LeadQuoteDetailsGetRes>();
	ModelMapper mapper = new ModelMapper();
	try {
		List<QuoteDetails> leadquotedetails = quoteRepo.findByClientRefNoAndLeadId(req.getClientRefNo(), req.getLeadId());
			for(QuoteDetails data : leadquotedetails)
			{
			LeadQuoteDetailsGetRes res = new LeadQuoteDetailsGetRes();
			res = mapper.map(data, LeadQuoteDetailsGetRes.class);
			resList.add(res);
			}
	}
	catch(Exception e) {
		e.printStackTrace();
		log.info("Exception Error",e.getMessage());
		return null;
	}
	return resList;
	}

}
