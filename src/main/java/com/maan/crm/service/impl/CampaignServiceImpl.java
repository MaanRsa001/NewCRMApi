package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.maan.crm.bean.CampaignDetails;
import com.maan.crm.bean.CampaignDetailsId;
import com.maan.crm.bean.CampaignMaster;
import com.maan.crm.bean.CampaignMasterId;
import com.maan.crm.bean.CampaignOfferDetails;
import com.maan.crm.bean.CampaignTemplate;
import com.maan.crm.bean.CampaignTemplateForm;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.CampaignDetailsRepository;
import com.maan.crm.repository.CampaignMasterRepository;
import com.maan.crm.repository.CampaignOfferDetailsRepository;
import com.maan.crm.repository.CampaignTemplateFormRepository;
import com.maan.crm.repository.CampaignTemplateRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.req.CampaignDetailsGetAllReq;
import com.maan.crm.req.CampaignDetailsGetReq;
import com.maan.crm.req.CampaignDetailsReq;
import com.maan.crm.req.CampaignDetailsSaveReq;
import com.maan.crm.req.CampaignFilterReq;
import com.maan.crm.req.CampaignGetReq;
import com.maan.crm.req.CampaignInviteReq;
import com.maan.crm.req.CampaignMasterSaveReq;
import com.maan.crm.req.CampaignTemplateFormSaveReq;
import com.maan.crm.req.CampaignTemplateReq;
import com.maan.crm.req.ClientGetAllReq;
import com.maan.crm.req.ClientList;
import com.maan.crm.req.InviteDefaultFilterReq;
import com.maan.crm.req.InviteMailReq;
import com.maan.crm.res.CampaignCountRes;
import com.maan.crm.res.CampaignDetailsRes;
import com.maan.crm.res.CampaignFilterRes;
import com.maan.crm.res.CampaignInviteRes;
import com.maan.crm.res.CampaignMasterSaveRes;
import com.maan.crm.res.CampaignRes;
import com.maan.crm.res.CampaignSaveRes;
import com.maan.crm.res.CampaignTemplateFormGetRes;
import com.maan.crm.res.CampaignTemplateRes;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientReferenceGetAllRes;
import com.maan.crm.res.InviteDefaultFilterRes;
import com.maan.crm.res.InviteMailRes;
import com.maan.crm.service.CampaignService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

	private Logger log = LogManager.getLogger(CampaignServiceImpl.class);

	Gson json = new Gson();

	@Autowired
	private CampaignDetailsRepository repo;

	@Autowired
	private CampaignMasterRepository masterrepo;

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private LeadRepository leadrepo;

	@Autowired
	private InsuranceCompanyMasterRepository insRepo;

	@Autowired
	private MailThreadServiceImpl mailThreadService;

	@Autowired
	private CampaignTemplateRepository campRepo;

	@Autowired
	private CampaignTemplateFormRepository campaignTemFormRepo;

	@Autowired
	private CampaignOfferDetailsRepository campOfferRepo;

	// Get All Campaign Master
	@Override
	public List<CampaignDetailsRes> campaignDetails(CampaignDetailsReq req) {
		List<CampaignDetailsRes> resList = new ArrayList<CampaignDetailsRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			Date today = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(today);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			today = cal.getTime();
			String status = "Y";
			List<CampaignMaster> campaigndetails = masterrepo
					.findByEndDateGreaterThanEqualAndStatusOrderByStartDateAsc(today, status);
			for (CampaignMaster data : campaigndetails) {
				CampaignDetailsRes res = new CampaignDetailsRes();
				res = mapper.map(data, CampaignDetailsRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return resList;
	}

	// Validation for Campaign Master
	@Override
	public List<Error> validateCampaignMaster(CampaignMasterSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			if (req.getBranchCode() == null || StringUtils.isBlank(req.getBranchCode())) {
				errors.add(new Error("01", "Branch Code", "Please Enter Branch Code"));
			} else if (req.getBranchCode().length() > 20) {
				errors.add(new Error("01", "Branch Code", "Please Enter Branch Code within 20 Characters"));
			}
			/*
			 * if(req.getCampaignId()==null ||
			 * StringUtils.isBlank(req.getCampaignId().toString())) { errors.add(new
			 * Error("02","Campaign Id", "Please Enter Campaign Id")); }
			 */ if (req.getCampaignName() == null || StringUtils.isBlank(req.getCampaignName())) {
				errors.add(new Error("03", "Campaign Name", "Please Enter Campaign Name"));
			} else if (req.getCampaignName().length() > 20) {
				errors.add(new Error("03", "Campaign Name", "Please Enter Campaign Name within 20 Characters"));
			}
			if (req.getClassTypeId() == null || StringUtils.isBlank(req.getClassTypeId().toString())) {
				errors.add(new Error("04", "Class Type Id", "Please Enter Class Type Id"));
			}
			if (req.getCreatedBy() == null || StringUtils.isBlank(req.getCreatedBy())) {
				errors.add(new Error("05", "Created By", "Please Enter Created By"));
			} else if (req.getCreatedBy().length() > 100) {
				errors.add(new Error("05", "Created By", "Please Enter Created By within 100 Characters"));
			}
			if (req.getDescription() == null || StringUtils.isBlank(req.getDescription())) {
				errors.add(new Error("06", "Description", "Please Enter Description"));
			} else if (req.getDescription().length() > 300) {
				errors.add(new Error("06", "Description", "Please Enter Description within 300 Characters"));
			}
			if (StringUtils.isBlank(req.getEndDate().toString())) {
				errors.add(new Error("07", "End Date", "Please Enter End Date"));
			}
			if (req.getInsCompanyId() == null || StringUtils.isBlank(req.getInsCompanyId())) {
				errors.add(new Error("08", "Insurance Id", "Please Enter Insurance Id"));
			} else if (req.getInsCompanyId().length() > 10) {
				errors.add(new Error("08", "Insurance Id", "Please Enter Insurance Id within 10 Characters"));
			}
			/*
			 * if (req.getOffer() == null || StringUtils.isBlank(req.getOffer())) {
			 * errors.add(new Error("09", "Offer", "Please Enter Offer")); } else if
			 * (req.getOffer().length() > 10) { errors.add(new Error("09", "Offer",
			 * "Please Enter Offer within 10 Characters")); }
			 */
			if (req.getPolicyTypeId() == null || StringUtils.isBlank(req.getPolicyTypeId().toString())) {
				errors.add(new Error("10", "Policy Type Id", "Please Enter Policy Type Id"));
			}
			/*
			 * if (req.getProduct() == null || StringUtils.isBlank(req.getProduct())) {
			 * errors.add(new Error("11", "Product", "Please Enter Product")); } else if
			 * (req.getProduct().length() > 100) { errors.add(new Error("11", "Product",
			 * "Please Enter Product within 100 Characters")); }
			 */ if (req.getPromoCode() == null || StringUtils.isBlank(req.getPromoCode())) {
				errors.add(new Error("12", "Promo Code", "Please Enter Promocode"));
			} else if (req.getPromoCode().length() > 100) {
				errors.add(new Error("12", "PromoCode", "Please Enter Promocode within 100 Characters"));
			}
			if (StringUtils.isBlank(req.getStartDate().toString())) {
				errors.add(new Error("13", "Start Date", "Please Enter Start Date"));
			}
			if (req.getLandingPageLink() == null || StringUtils.isBlank(req.getLandingPageLink())) {
				errors.add(new Error("14", "Landing Page Link", "Please Enter Landing Page Link"));
			} else if (req.getLandingPageLink().length() > 1000) {
				errors.add(
						new Error("14", "Landing Page Link", "Please Enter Landing Page Link within 1000 Characters"));
			}
			if (req.getBusinessTypeId() == null || StringUtils.isBlank(req.getBusinessTypeId().toString())) {
				errors.add(new Error("15", "Business Type Id", "Please Enter Business Type Id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			errors.add(new Error("14", "Common Error", e.getMessage()));
			return errors;
		}
		return errors;
	}

	// Save Campaign Master
	@Override
	public CampaignSaveRes saveCampaignMaster(CampaignMasterSaveReq req) {
		CampaignSaveRes res = new CampaignSaveRes();
		ModelMapper mapper = new ModelMapper();
		CampaignMaster entity = new CampaignMaster();
		Date EntryDate = null;
		try {
			String campId = "";
			CampaignMasterId id = new CampaignMasterId();
			id.setCampaignId(req.getCampaignId());

			Optional<CampaignMaster> data = masterrepo.findById(id);
			// update
			if (data.isPresent()) {
				CampaignMaster ent = mapper.map(req, CampaignMaster.class);
				ent.setCampaignId(req.getCampaignId());
				campId = req.getCampaignId();
				masterrepo.save(ent);
				res.setCode(req.getCampaignId().toString());
				res.setResponse("Updated Successful");
			}
			// Insert
			else {
				Long count = masterrepo.count();
				Long Id = 1001 + count;
				campId = "Camp-" + Id;
				CampaignMaster ent = mapper.map(req, CampaignMaster.class);
				ent.setCampaignId(campId);
				ent.setEntryDate(new Date());
				masterrepo.save(ent);
				res.setCode(campId.toString());
				res.setResponse("Inserted Successful");
				;
			}

			List<CampaignTemplateForm> getOldFields = campaignTemFormRepo.findByCampaignIdOrderBySnoAsc(campId);
			if (getOldFields != null && getOldFields.size() > 0) {
				campaignTemFormRepo.deleteAll(getOldFields);
			}

			List<CampaignOfferDetails> getOldOffers = campOfferRepo.findByCampaignIdOrderByOfferIdAsc(campId);
			if (getOldOffers != null && getOldOffers.size() > 0) {
				campOfferRepo.deleteAll(getOldOffers);
			}
			Integer offerId = 0;
			if (req.getOffer() != null && req.getOffer().size() > 0) {
				for (String offer : req.getOffer()) {
					CampaignOfferDetails offerSave = new CampaignOfferDetails();

					offerSave.setCampaignId(campId);
					offerId = offerId + 1;
					offerSave.setOfferId(offerId);
					offerSave.setOfferDesc(offer);
					offerSave.setStatus("Y");
					offerSave.setEntryDate(new Date());
					campOfferRepo.save(offerSave);
				}
			}

			Integer sno = 0;
			if (req.getCampTemplateFormDetails() != null && req.getCampTemplateFormDetails().size() > 0) {
				for (CampaignTemplateFormSaveReq formData : req.getCampTemplateFormDetails()) {
					CampaignTemplateForm saveFormField = new CampaignTemplateForm();

					saveFormField.setCampaignId(campId);
					sno = sno + 1;
					saveFormField.setSno(sno);
					saveFormField.setFieldId(Integer.valueOf(formData.getFieldId()));
					saveFormField.setFieldName(formData.getFieldName());
					saveFormField.setDispalyName(formData.getDisplayName());
					saveFormField.setStatus("Y");
					saveFormField.setEntryDate(new Date());
					campaignTemFormRepo.save(saveFormField);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return res;
	}

	// Get Campaign Master
	@Override
	public CampaignDetailsRes campaign(CampaignDetailsGetReq req) {
		CampaignDetailsRes res = new CampaignDetailsRes();
		ModelMapper mapper = new ModelMapper();
		try {
			Date today = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(today);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			today = cal.getTime();
			String status = "Y";
			CampaignMaster data = masterrepo.findByCampaignId(req.getCampaignId());
			res = mapper.map(data, CampaignDetailsRes.class);

			List<CampaignTemplateFormGetRes> campTempFormList = new ArrayList<CampaignTemplateFormGetRes>();
			List<CampaignTemplateForm> getCampFields = campaignTemFormRepo
					.findByCampaignIdOrderBySnoAsc(req.getCampaignId());
			for (CampaignTemplateForm getField : getCampFields) {
				CampaignTemplateFormGetRes campTempRes = new CampaignTemplateFormGetRes();

				campTempRes.setFieldId(getField.getFieldId() == null ? "" : getField.getFieldId().toString());
				campTempRes.setFieldName(getField.getFieldName());
				campTempRes.setDisplayName(getField.getDispalyName());
				campTempFormList.add(campTempRes);

			}
			res.setCampTemplateFormDetails(campTempFormList);

			List<String> offers = new ArrayList<String>();
			List<CampaignOfferDetails> getOffers = campOfferRepo.findByCampaignIdOrderByOfferIdAsc(req.getCampaignId());
			for (CampaignOfferDetails offer : getOffers) {
				offers.add(offer.getOfferDesc());
			}
			res.setOffer(offers);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return res;
	}

	// Validation for Campaign Details
	@Override
	public List<Error> validateCampaignDetails(CampaignDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		try {
			if (req.getClientName() == null || StringUtils.isBlank(req.getClientName())) {
				errors.add(new Error("01", "Branch Code", "Please Enter Branch Code"));
			} else if (req.getClientName().length() > 100) {
				errors.add(new Error("01", "Client Name", "Please Enter Client Name within 100 Characters"));
			}
			/*
			 * if(req.getClientRefNo() ==null || StringUtils.isBlank(req.getClientRefNo()))
			 * { errors.add(new Error("02","Client Ref No", "Please Enter Client Ref No"));
			 * } else if(req.getClientRefNo().length()>100) { errors.add(new
			 * Error("02","Client Ref No","Please Enter Client Ref No within 100 Characters"
			 * )); } if(req.getCrno()==null || StringUtils.isBlank(req.getCrno())) {
			 * errors.add(new Error("03","Crno", "Please Enter Crno")); } else
			 * if(req.getCrno().length()>100) { errors.add(new
			 * Error("03","Crno","Please Enter Crno within 100 Characters" )); }
			 * 
			 */
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);

			if (req.getEmailId() == null || StringUtils.isBlank(req.getEmailId())) {
				errors.add(new Error("04", "EmailId", "Please Enter EmailId"));
			} else if (req.getEmailId().length() > 100) {
				errors.add(new Error("04", "EmailId", "Please Enter EmailId within 100 Characters"));
			} else if (StringUtils.isNotBlank(req.getEmailId())) {
				Matcher matcher3 = pattern.matcher(req.getEmailId());
				if (!matcher3.matches())
					errors.add(new Error("04", "Email Id", "please Enter Valid Email Id"));
			}

			CampaignDetails search = repo.findByMobileNumberAndCampaignId(req.getMobileNumber(), req.getCampaignId());

			if (req.getMobileNumber() == null || StringUtils.isBlank(req.getMobileNumber())) {
				errors.add(new Error("05", "MobileNumber", "Please Enter MobileNumber"));
			} else if (req.getMobileNumber().length() > 10) {
				errors.add(new Error("05", "MobileNumber", "Please Enter MobileNumber within 10 Characters"));
			} else if (req.getMobileNumber().equalsIgnoreCase(search.getMobileNumber())) {
				errors.add(
						new Error("05", "Mobile Number", "This mobile Number is already register for this campaign"));
			}
			if (req.getOccupation() == null || StringUtils.isBlank(req.getOccupation())) {
				errors.add(new Error("06", "Occupation", "Please Enter Occupation"));
			} else if (req.getOccupation().length() > 100) {
				errors.add(new Error("06", "Occupation", "Please Enter Occupation within 100 Characters"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return errors;
		}
		return errors;
	}

	// Save Campaign Details
	@Override
	@Transactional
	public CampaignMasterSaveRes saveCampaignDetails(CampaignDetailsSaveReq req) {
		CampaignMasterSaveRes res = new CampaignMasterSaveRes();
		ModelMapper mapper = new ModelMapper();
		CampaignDetails entity = new CampaignDetails();

		try {
			String clientRefNo = "";
			List<ClientDetails> dataList = clientrepo.findByMobileNumber(req.getMobileNumber());
			ClientDetails data = dataList !=null ? dataList.get(0):new ClientDetails();
			CampaignMaster campdata = masterrepo.findByCampaignId(req.getCampaignId());

			// CLient Save
			if (data == null) {
				Long newId = clientrepo.count() + 1001;
				Random rnd = new Random();
				int number = rnd.nextInt(100);
				String randomNo = String.format("%02d", number);
				clientRefNo = "C/" + newId + "/" + randomNo;

				ClientDetails ent = mapper.map(req, ClientDetails.class);
				ent.setBranchCode(campdata.getBranchCode());
				ent.setInsCompanyId(campdata.getInsCompanyId());
				ent.setCreatedBy(campdata.getCreatedBy());
				ent.setClientRefNo(clientRefNo);
				ent.setCrno(req.getCrno());
				clientrepo.save(ent);
				res.setClientRefNo(clientRefNo);
				res.setResponse("Inserted Successful");
			} else {
				clientRefNo = data.getClientRefNo();
				res.setClientRefNo(clientRefNo);
			}

			// Lead Save
			Long count = leadrepo.count();
			Random rnd1 = new Random();
			int number1 = rnd1.nextInt(100);
			String randomNo1 = String.format("%02d", number1);
			String leadId = "100";

			Long Id = count + 100;
			leadId = Id.toString() + randomNo1;
			LeadDetails lead = mapper.map(campdata, LeadDetails.class);
			lead.setLeadId(leadId);
			lead.setClientRefNo(clientRefNo);
			leadrepo.save(lead);
			res.setLeadId(leadId);
			res.setResponse("Inserted Successful");

			// Campaign Save
			CampaignDetailsId id = new CampaignDetailsId();
			String customerId = "";
			Long newId2 = repo.count() + 1001;
			customerId = newId2.toString();

			CampaignDetails ent = mapper.map(req, CampaignDetails.class);
			ent.setCustomerId(customerId);
			ent.setClientRefNo(clientRefNo);
			ent.setLeadId(leadId);

			repo.save(ent);
			res.setCampaigncode(customerId);
			res.setResponse("Inserted Successful");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return res;
	}

	// Get Campaign Details
	@Override
	public CampaignRes getcampaign(CampaignGetReq req) {
		CampaignRes res = new CampaignRes();
		ModelMapper mapper = new ModelMapper();
		try {
			CampaignDetails data = repo.findByMobileNumber(req.getMobileNumber());
			res = mapper.map(data, CampaignRes.class);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return res;
	}

	// Get All Campaign Details
	@Override
	public CampaignRes getallcampaign(CampaignDetailsGetAllReq req) {
		CampaignRes resa = new CampaignRes();
		ModelMapper mapper = new ModelMapper();
		try {
			List<CampaignDetails> campaigndetails = repo.findByCampaignId(req.getCampaignId());
			//Long count = repo.count();
			Long count= 0L;
			List<CampaignCountRes> resList = new ArrayList<CampaignCountRes>();

			for (CampaignDetails data : campaigndetails) {
				CampaignCountRes res = new CampaignCountRes();
				res = mapper.map(data, CampaignCountRes.class);		
				resList.add(res);
				count++;
			}
		resa.setClientCount(count);	
		resa.setCampaignRes(resList);
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return resa;
	}

	// Filter Option

	@Override
	public CampaignFilterRes filter(CampaignFilterReq req) {
		CampaignFilterRes resData = new CampaignFilterRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<ClientDetails> clientDetails = new ArrayList<ClientDetails>();
		try {
			
			List<ClientDetails> fD = new ArrayList<ClientDetails>();
			CampaignMaster camp = masterrepo.findByCampaignId(req.getCampaignId());
			String info = camp.getBusinessType();
			
			List<ClientDetails> cD1 = new ArrayList<ClientDetails>();//clientrepo.findAll();
			if (info.equalsIgnoreCase("Old-Client")) {
				cD1 = clientrepo.findAll();
				
				
			} else if (info.equalsIgnoreCase("New-Client")) {
				
			} else if (info.equalsIgnoreCase("Renewal-Client")) {

			}
			
			
			
			
			
			
			
			
			if (req.getOccupationIds().size() > 0) {
				
				List<ClientDetails> cD2 = new ArrayList<ClientDetails>();
				for (Integer ids :  req.getOccupationIds() ) {
					List<ClientDetails> occuList = new ArrayList<ClientDetails>();
					occuList = cD1.stream().filter(o -> o.getOccupationId().equals(ids)).collect(Collectors.toList());		
					if ( occuList.size()>0 ) {
						cD2.addAll(occuList);
					}
				
				}		
				//		List<ClientDetails> occupation = clientrepo.findByOccupationIdIn(req.getOccupationIds());
				clientDetails.addAll(cD2);
			}
			if (req.getAnnualIncomeIds().size() > 0) {
				List<ClientDetails> income = clientrepo.findByAnnualIncomeIdIn(req.getAnnualIncomeIds());
				clientDetails.addAll(income);
			}
			if (StringUtils.isNotBlank(req.getAgeFrom().toString())
					&& StringUtils.isNotBlank(req.getAgeTo().toString())) {
				{
					Integer agefrom = req.getAgeFrom();
					Integer ageto = req.getAgeTo();
					Date today = new Date();
					Calendar cal = new GregorianCalendar();
					cal.setTime(today);
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.YEAR, -agefrom);
					Date startDate = cal.getTime();
					cal.setTime(today);
					cal.set(Calendar.HOUR, 23);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.YEAR, -ageto);
					Date endDate = cal.getTime();

					List<ClientDetails> dateofbirth = clientrepo
							.findByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqualOrderByEntryDateDesc(startDate,
									endDate);
					clientDetails.addAll(dateofbirth);
				}
				List<ClientDetailsGridRes> clientlist = new ArrayList<ClientDetailsGridRes>();
				for (ClientDetails data : clientDetails) {
					ClientDetailsGridRes res = new ClientDetailsGridRes();
					res = mapper.map(data, ClientDetailsGridRes.class);
					List<ClientDetailsGridRes> filterData = clientlist.stream()
							.filter(o -> o.getClientRefNo().equalsIgnoreCase(data.getClientRefNo()))
							.collect(Collectors.toList());
					if (filterData != null && filterData.size() == 0) {
						clientlist.add(res);
					}

				}
				resData.setClientCount(clientlist.size());
				resData.setClientDetails(clientlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resData;
	}

	@Override
	public List<ClientReferenceGetAllRes> getallReferenceClients(ClientGetAllReq req) {
		List<ClientReferenceGetAllRes> resList = new ArrayList<ClientReferenceGetAllRes>();

		ModelMapper mapper = new ModelMapper();
		try {

			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			// Find
			Page<ClientDetails> clientDetails = clientrepo.findByInsCompanyIdAndBranchCodeOrderByLastVisitedDateDesc(paging,
					req.getInsId(), req.getBranchCode());

			for (ClientDetails data : clientDetails.getContent()) {
				ClientReferenceGetAllRes res = new ClientReferenceGetAllRes();
				res.setYourReferenceName(data.getYourReferenceName());
				;
				res.setYourReferenceMailid(data.getYourReferenceMailid());
				res.setYourReferenceMobile(data.getYourReferenceMobile());
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
	public List<CampaignTemplateRes> campaignTemplate(CampaignTemplateReq req) {
		List<CampaignTemplateRes> resList = new ArrayList<CampaignTemplateRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<CampaignTemplate> campaigntemplate = campRepo.findByStatusOrderByFieldIdAsc("Y");
			for (CampaignTemplate data : campaigntemplate) {
				CampaignTemplateRes res = new CampaignTemplateRes();
				res = mapper.map(data, CampaignTemplateRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public CampaignInviteRes invitecampaign(CampaignInviteReq req) {
		CampaignInviteRes res = new CampaignInviteRes();
		String customerId;
		ModelMapper mapper = new ModelMapper();
		try {
			CampaignDetails ent = new CampaignDetails();
			mapper.map(req, ent);

			if (StringUtils.isNotBlank(ent.getMobileNumber())) {
				CampaignDetails data = repo.findByMobileNumber(ent.getMobileNumber());
				if (data != null) {
					customerId = data.getCustomerId();
					ent.setCustomerId(customerId);

				} else {
					customerId = "";
					Long newId2 = repo.count() + 1001;
					customerId = newId2.toString();
					ent.setCustomerId(customerId);
				}
			} else {
				customerId = "";
				Long newId2 = repo.count() + 1001;
				customerId = newId2.toString();
				ent.setCustomerId(customerId);
			}
			repo.save(ent);
			res.setCustomerId(customerId);
			res.setMessage("Saved Successful");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<InviteDefaultFilterRes> invitefilter(InviteDefaultFilterReq req) {
		List<InviteDefaultFilterRes> resList = new ArrayList<InviteDefaultFilterRes>();
		try {
			List<ClientDetails> clientDetails = clientrepo.findAll();
			CampaignMaster camp = masterrepo.findByCampaignId(req.getCampaignId());
			String info = camp.getBusinessType();
			if (info.equalsIgnoreCase("Old-Client")) {
				
				for (ClientDetails data : clientDetails) {
					InviteDefaultFilterRes res = new InviteDefaultFilterRes();
					res.setClientName(data.getClientName());
					res.setMailId(data.getEmailId());
					res.setMobileNumber(data.getMobileNumber());
					resList.add(res);
				}
			} else if (info.equalsIgnoreCase("New-Client")) {
				
				
				
				
				
				for (ClientDetails data : clientDetails) {
					InviteDefaultFilterRes res = new InviteDefaultFilterRes();
					res.setClientName(data.getYourReferenceName());
					res.setMailId(data.getYourReferenceMailid());
					res.setMobileNumber(data.getYourReferenceMobile());
					
					resList.add(res);
				}
			} else if (info.equalsIgnoreCase("Renewal-Client")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details", e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public InviteMailRes invitemail(InviteMailReq req) {
	InviteMailRes res = new InviteMailRes();
	try {
		//Thread To Trigger Mail
		InsuranceCompanyMaster companyData =  insRepo.findByInsId(req.getInsCompanyId());
		
		List<String>  ccMails = new ArrayList<String>();
		ccMails.add(companyData.getInsEmail());
				
		List<String>  toMails = new ArrayList<String>();
		List<ClientList> mail = req.getClientlist();
		for(ClientList data : mail) {
		toMails.add(data.getMailId());
		}
		Map<String, Object>  keys = new HashMap<String, Object>();
		keys.put("INVITE_MAIL",req.getInsCompanyId());
					
		// Set Mail Request 
		MailFramingReq mailFrameReq = new MailFramingReq(); 
		mailFrameReq.setInsId(req.getInsCompanyId().toString());
		mailFrameReq.setNotifTemplateId("INVITE_MAIL");
		mailFrameReq.setKeys(keys);	
		mailFrameReq.setMailCc(ccMails);
		mailFrameReq.setMailTo(toMails);
		mailFrameReq.setMailRegards(companyData.getRegards());
		mailFrameReq.setStatus("Y");
		
		log.info("{ Mail Pushed SuccessFully"); 
	//			mailFrameService.sendSms(mailReq);
		mailThreadService.threadToSendMail(mailFrameReq); 
		
		res.setResponse("SUCCESS");

	}
	catch(Exception e) {
		e.printStackTrace();
		log.info("Log Details", e.getMessage());
		return null;
	}
	return res;
	}

}
