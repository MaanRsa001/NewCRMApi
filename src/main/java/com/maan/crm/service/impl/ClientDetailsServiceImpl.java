
package com.maan.crm.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientCorContact;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.ClientMemberDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;

import com.maan.crm.bean.TrackingDetails;

import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.notification.service.impl.MailThreadServiceImpl;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.repository.ClientCorContactRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.ClientMemberDetailsRepository;
import com.maan.crm.repository.EnquiryRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;
import com.maan.crm.repository.LeadRepository;

import com.maan.crm.repository.TrackingDetailsRepository;

import com.maan.crm.repository.PolicyDetailsRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.ClientCorContactReq;
import com.maan.crm.req.ClientDetailsGetAllReq;
import com.maan.crm.req.ClientDetailsGetReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientDetailsSaveReq;
import com.maan.crm.req.ClientDetailsUpdateReq;
import com.maan.crm.req.ClientGetAllReq;
import com.maan.crm.req.ClientMemberDetailsReq;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.TrackingReq;
import com.maan.crm.res.ClientAddressDetailsGetRes;
import com.maan.crm.res.ClientCrContactGetRes;
import com.maan.crm.res.ClientDetailsGetAllRes;
import com.maan.crm.res.ClientDetailsGetRes;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientDetailsJsonTemRes;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.ClientSearchCountRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.ClientViewRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClientDetailsService;
import javax.persistence.criteria.Predicate;

@Service
@Transactional
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private ClientCorContactRepository clientCorRepo;

	@Autowired
	private ClientAddressDetailsRepository clientAddressRepo;

	@Autowired
	private ClientMemberDetailsRepository clientMemRepo;


	@Autowired
	private LeadRepository leadRepo;
	
	@Autowired
	private PolicyDetailsRepository policyRepo;
	
	@Autowired
	private CriteriaQueryServiceImpl Criteria;
	
	@Autowired
	private InsuranceCompanyMasterRepository insRepo;
	
	@Autowired
	private MailThreadServiceImpl mailThreadService ;
	
	@Autowired
	private ClaimLoginMasterRepository loginRepo;

	@Autowired
	private TrackingDetailsRepository trackRepo;
	
	@Autowired
	private EnquiryRepository enquiryRepo;
	
	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TrackingServiceImpl trackService ;
	
	
	private Logger log = LogManager.getLogger(ClientDetailsServiceImpl.class);

	Gson json = new Gson();

	// SAVE CLIENT DETAILS
	@Override
	@Transactional
	public CrmClientSuccessRes saveClientDetails(ClientDetailsJsonTemReq req1) {
		
		ClientDetailsSaveReq req = req1.getClientDetails();
		
		CrmClientSuccessRes res = new CrmClientSuccessRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			ClientDetails saveClientDetails = new ClientDetails();

			String clientRefNo = "";
			Date entryDate = null;
			if (StringUtils.isBlank(req.getClientRefNo())) {

				// Save
				Long newId = clientrepo.count() + 1001;
				Random rnd = new Random();
				int number = rnd.nextInt(100);
				String randomNo = String.format("%02d", number);
				clientRefNo = "C/" + newId + "/" + randomNo;

				entryDate = new Date();
				res.setResponse("Saved Succesfully");
				saveClientDetails = mapper.map(req, ClientDetails.class);
				saveClientDetails.setDateOfAnniversary(req.getDateOfAnniversary()==null?null :StringUtils.isBlank(req.getDateOfAnniversary().toString() )?null : req.getDateOfAnniversary() );
				saveClientDetails.setDateOfBirth(req.getDateOfBirth()==null?null :StringUtils.isBlank(req.getDateOfBirth().toString() )?null : req.getDateOfBirth() );	
				saveClientDetails.setTitleId(StringUtils.isBlank(req.getTitleid())?null : req.getTitleid() );			
				
				// Tracking
				TrackingReq treq  = new TrackingReq(); 
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(req.getRegionCode());
				treq.setClientRefNo(clientRefNo);
				treq.setClientName(req.getClientName());
				treq.setStatus("Client");
				treq.setStatusDescription("Client Inserted Successfully");
				trackService.tracking(treq);
			
				
			} else {
				// Update
				clientRefNo = req.getClientRefNo();
				ClientDetails findData = clientrepo.findByClientRefNo(clientRefNo);

				entryDate = findData.getEntryDate();
				saveClientDetails = mapper.map(req, ClientDetails.class);
				saveClientDetails.setDateOfAnniversary(req.getDateOfAnniversary()==null?null :StringUtils.isBlank(req.getDateOfAnniversary().toString() )?null : req.getDateOfAnniversary() );
				saveClientDetails.setDateOfBirth(req.getDateOfBirth()==null?null :StringUtils.isBlank(req.getDateOfBirth().toString() )?null : req.getDateOfBirth() );	
				saveClientDetails.setTitleId(StringUtils.isBlank(req.getTitleid())?null : req.getTitleid() );		
				
				saveClientDetails.setCompanyType(findData.getCompanyType());
				saveClientDetails.setCompanyTypeId(findData.getCompanyTypeId());
				saveClientDetails.setDefaultUser(findData.getDefaultUser());
				saveClientDetails.setDefaultUserId(findData.getDefaultUserId());
				saveClientDetails.setGroupClient(findData.getGroupClient());
				saveClientDetails.setGroupClientId(findData.getGroupClientId());
				saveClientDetails.setIsGroupClient(findData.getIsGroupClient());
				saveClientDetails.setIsGroupClientId(findData.getIsGroupClientId());
				saveClientDetails.setPos(findData.getPos());
				saveClientDetails.setPosId(findData.getPosId());
				saveClientDetails.setDateOfAnniversary(req.getDateOfAnniversary());
				saveClientDetails.setDateOfBirth(req.getDateOfBirth());	
			
				res.setResponse("Updated Succesfully");
				
				
				// Tracking
				TrackingReq treq  = new TrackingReq(); 
				treq.setCreatedBy(req.getCreatedBy());
				treq.setBranchCode(req.getBranchCode());
				treq.setEntryDate(entryDate);
				treq.setInsCompanyId(req.getInsCompanyId());
				treq.setRegionCode(req.getRegionCode());
				treq.setClientRefNo(clientRefNo);
				treq.setClientName(req.getClientName());
				treq.setStatus("Client");
				treq.setStatusDescription("Client Updated Successfully");
				trackService.tracking(treq);
			
			}

			// Mapper	
			saveClientDetails.setStatus("Y");
			saveClientDetails.setClientRefNo(clientRefNo);
			saveClientDetails.setEntryDate(entryDate);
			saveClientDetails.setLastVisitedDate(new Date());
			
			clientrepo.saveAndFlush(saveClientDetails);
		
			log.info("Saved Details is ---> " + json.toJson(saveClientDetails));
			
			//Thread To Trigger Mail
			InsuranceCompanyMaster companyData =  insRepo.findByInsId(req.getInsCompanyId());
			ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());
			
			List<String>  ccMails = new ArrayList<String>();
			ccMails.add(companyData.getInsEmail());
			ccMails.add(loginData.getUserMail());
					
			List<String>  toMails = new ArrayList<String>();
			toMails.add(req.getEmailId());
			  

			Map<String, Object>  keys = new HashMap<String, Object>();
			keys.put("CLIENT_REF_NO", req.getClientRefNo()==null?"":req.getClientRefNo());
						
			// Set Mail Request 
			MailFramingReq mailFrameReq = new MailFramingReq(); 
			mailFrameReq.setInsId(req.getInsCompanyId());
			mailFrameReq.setNotifTemplateId("CLIENT_INFO");
			mailFrameReq.setKeys(keys);	
			mailFrameReq.setMailCc(ccMails);
			mailFrameReq.setMailTo(toMails);
			mailFrameReq.setMailRegards(companyData.getRegards());
			mailFrameReq.setStatus(res.getResponse());
			
			log.info("{ Mail Pushed SuccessFully .  ClientRefNo is --->" + req.getClientRefNo() + " }"); 
		//			mailFrameService.sendSms(mailReq);
			mailThreadService.threadToSendMail(mailFrameReq); 
		

		
			
			res.setClientRefNo(clientRefNo);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	// GET ALL CLIENT DETAILS
	@Override
	public List<ClientDetailsGridRes> getAllClientDetails(ClientDetailsGetAllReq req) {
		List<ClientDetailsGridRes> resList = new ArrayList<ClientDetailsGridRes>();

		ModelMapper mapper = new ModelMapper();
		try {
			// Limit , Offset
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset, Sort.by("entryDate").descending());

			// Find
			Page<ClientDetails> clientDetails = clientrepo.findByInsCompanyIdAndBranchCodeAndCreatedByOrderByEntryDateDesc(paging, req.getInsId(),
					req.getBranchCode(),req.getCreatedBy() );

			// Map
			
			for (ClientDetails data : clientDetails.getContent()) {
				ClientDetailsGridRes res = new ClientDetailsGridRes();

				res = mapper.map(data, ClientDetailsGridRes.class);
				/*
				 * res.setDateOfAnniversary( data.getDateOfAnniversary() == null ? "" :
				 * sdf.format(data.getDateOfAnniversary()));
				 * res.setDateOfBirth(data.getDateOfBirth() == null ? "" :
				 * sdf.format(data.getDateOfBirth())); res.setEntryDate(data.getEntryDate() ==
				 * null ? "" : sdf.format(data.getEntryDate()));
				 */
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return resList;
	}

	// Get Client Details By ID
	@Override
	public ClientDetailsJsonTemRes getClientDetailsById(ClientDetailsGetReq req) {
		ClientDetailsJsonTemRes commonRes = new ClientDetailsJsonTemRes();

		ModelMapper mapper = new ModelMapper();
		try {
			String clientRefNo = req.getClientRefNo();
			ClientDetails data = clientrepo.findByClientRefNo(req.getClientRefNo());
			mapper.getConfiguration().setAmbiguityIgnored(true);

			// Address Res
			List<ClientAddressDetails> clientAddress = clientAddressRepo.findByClientRefNoOrderByAddresTypeIdAsc(req.getClientRefNo());
			List<ClientAddressDetailsSaveReq> addressList = new ArrayList<ClientAddressDetailsSaveReq>();
			for (ClientAddressDetails adrData : clientAddress) {
				ClientAddressDetailsSaveReq adrRes = new ClientAddressDetailsSaveReq();
				adrRes = mapper.map(adrData, ClientAddressDetailsSaveReq.class);
				mapper.getConfiguration().setAmbiguityIgnored(true);
				adrRes.setClientAddressId(adrData.getClientAddressId().toString());
				addressList.add(adrRes);
			}
			
			// Cor Con  Res
			 List<ClientCorContactReq>  conList = new ArrayList<ClientCorContactReq>();
			List<ClientCorContact> corContact = clientCorRepo.findByClientRefNoOrderByClientcorContactIdAsc(req.getClientRefNo());
			for (ClientCorContact conData : corContact) {
				ClientCorContactReq res = new ClientCorContactReq();
				res = mapper.map(conData, ClientCorContactReq.class);
				conList.add(res);
			}
	
			// Member Res
			List<ClientMemberDetailsReq> memList = new ArrayList<ClientMemberDetailsReq>();
			List<ClientMemberDetails> clientMem = clientMemRepo.findByClientRefNoOrderByClientMemberIdAsc(req.getClientRefNo());
			for (ClientMemberDetails memData : clientMem) {
				ClientMemberDetailsReq res = new ClientMemberDetailsReq();

				res= mapper.map(memData, ClientMemberDetailsReq.class);
				memList.add(res);
			}
			
			// Response 
			
			commonRes.setClientDetails(mapper.map(data, ClientDetailsSaveReq.class));
			commonRes.setOtherDetails(mapper.map(data, ClientDetailsUpdateReq.class));
			commonRes.setClientAddressDetailsList(addressList);
			commonRes.setClientDetailsList(conList);
			commonRes.setClientMemberDetailsList(memList);		
			
				
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return commonRes;
	}

	// View All Client Details

	@Override
	public ClientViewRes viewClientDetails(ClientViewReq req) {
		ClientViewRes res = new ClientViewRes();
		ModelMapper modelMapper = new ModelMapper();
		try {

			// Client Details
			ClientDetails clientList = clientrepo.findByClientRefNo(req.getClientRefNo());

			res.setClientDetails(modelMapper.map(clientList, ClientDetailsGetRes.class));

			if (clientList.getClientTypeId() != null && clientList.getClientTypeId().equals(1)) {

				// Individual

				List<ClientMemberDetailsGetRes> indiReslist = new ArrayList<ClientMemberDetailsGetRes>();

				List<ClientMemberDetails> clientMem = clientMemRepo.findByClientRefNo(req.getClientRefNo());

				if (!clientMem.isEmpty()) {
					for (ClientMemberDetails data : clientMem) {

						ClientMemberDetailsGetRes indiRes = new ClientMemberDetailsGetRes();

						indiRes = modelMapper.map(data, ClientMemberDetailsGetRes.class);
						indiReslist.add(indiRes);
					}
					res.setClientIndiv(indiReslist);
				}

			} else if (clientList.getClientTypeId() != null && clientList.getClientTypeId().equals(2)) {

				// Corporate

				List<ClientCrContactGetRes> corResList = new ArrayList<ClientCrContactGetRes>();

				List<ClientCorContact> corList = clientCorRepo.findByClientRefNo(req.getClientRefNo());

				for (ClientCorContact data : corList) {

					ClientCrContactGetRes corRes = new ClientCrContactGetRes();

					corRes = modelMapper.map(data, ClientCrContactGetRes.class);

					corResList.add(corRes);
				}

				res.setClientCorpContact(corResList);
			}

			if (clientList.getIsGroupClient() != null && clientList.getIsGroupClient().matches("Y")) {
				// Group

				List<ClientMemberDetailsGetRes> grpResList = new ArrayList<ClientMemberDetailsGetRes>();

				List<ClientMemberDetails> clientGroup = clientMemRepo.findByClientRefNo(req.getClientRefNo());

				for (ClientMemberDetails data : clientGroup) {

					ClientMemberDetailsGetRes grpRes = new ClientMemberDetailsGetRes();

					grpRes = modelMapper.map(data, ClientMemberDetailsGetRes.class);

					grpResList.add(grpRes);
				}
				res.setClientGroup(grpResList);
			}

			if (clientList.getAddressYn() != null && clientList.getAddressYn().matches("Y")) {
				// Client Address

				List<ClientAddressDetailsGetRes> addResList = new ArrayList<ClientAddressDetailsGetRes>();

				List<ClientAddressDetails> clientAdd = clientAddressRepo.findByClientRefNo(req.getClientRefNo());

				for (ClientAddressDetails data : clientAdd) {

					ClientAddressDetailsGetRes addRes = new ClientAddressDetailsGetRes();
					addRes = modelMapper.map(data, ClientAddressDetailsGetRes.class);
					addResList.add(addRes);				}
				res.setClientAddress(addResList);

			}

		} catch (

		Exception e) {
			res = null;
			log.info("Exception Error", e.getMessage());
		}
		return res;
	}

	@Override
	public List<ClientSearchRes> searchClientDetails(ClientSearchReq req) {

		List<ClientSearchRes> resList = new ArrayList<ClientSearchRes>();
		ModelMapper mapper = new ModelMapper();
		List<ClientDetails> clientDetails = new ArrayList<ClientDetails>();

		try {
			String searchValue = req.getSearchValue();// .toLowerCase() ;
			
			clientDetails = Criteria.ClientDynamicSearch(searchValue , req.getCreatedBy() );
			
			if (clientDetails != null && clientDetails.size() > 0) {
				// Map List
				for (ClientDetails data : clientDetails) {
					ClientSearchRes res = new ClientSearchRes();

					res = mapper.map(data, ClientSearchRes.class);
					//res.setLeadCount(leadRepo.countByClientRefNo(data.getClientRefNo()).toString());
					res.setUserNameWithMobile(res.getClientName() + "-" + res.getMobileNumber());
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
	
	
	// Address Table
/*	List<ClientAddressDetails> addressList = Criteria.ClientAddressDynamicSearch(searchValue);
	for (ClientAddressDetails clientAddressDetails : addressList) {
		clientRef.add(clientAddressDetails.getClientRefNo());
	}
	
	// Contact Person
	List<ClientCorContact> clientcorcontactperList = Criteria.ClientCorContactDynamicSearch(searchValue);
	for (ClientCorContact clientCorContact : clientcorcontactperList) {
		clientRef.add(clientCorContact.getClientRefNo());
	}

	if (clientRef.size() != 0) {
		clientDetails = Criteria.ClientDynamicSearch1(clientRef);
	} else {
		clientDetails = Criteria.ClientDynamicSearch(searchValue);
	} */

	@Override
	public CrmClientSuccessRes updateClientDetails(ClientDetailsJsonTemReq req1) {
		
		ClientDetailsUpdateReq req = req1.getOtherDetails();
		
		CrmClientSuccessRes res = new CrmClientSuccessRes();
		ModelMapper mapper = new ModelMapper();

		try {
			String clientRefNo = "";
			Date entryDate = null;
			
			// Update
			clientRefNo = req1.getClientDetails().getClientRefNo();
			ClientDetails updateData = clientrepo.findByClientRefNo(clientRefNo);
			entryDate = updateData.getEntryDate();
			res.setResponse("Updated Succesfully");

			// Mapper
			updateData.setCompanyType(req.getCompanyType());
			updateData.setCompanyTypeId(req.getCompanyTypeId()==null?null:StringUtils.isBlank(req.getDefaultUserid())?null:Integer.valueOf(req.getDefaultUserid()));
			updateData.setDefaultUser(req.getDefaultUser());
			updateData.setDefaultUserId(req.getDefaultUserid()==null?null:StringUtils.isBlank(req.getDefaultUserid())?null:Integer.valueOf(req.getDefaultUserid()));
			updateData.setGroupClient(req.getGroupClient());
			updateData.setGroupClientId(req.getGroupClientId()==null?null:StringUtils.isBlank(req.getGroupClientId())?null: Integer.valueOf(req.getGroupClientId()));
			updateData.setIsGroupClient(req.getIsGroupClient());
			updateData.setIsGroupClientId(req.getIsGroupClientid()==null?null:StringUtils.isBlank(req.getIsGroupClientid())?null:Integer.valueOf(req.getIsGroupClientid()));
			updateData.setPos(req.getPos());
			updateData.setPosId(req.getPosId()==null?null:StringUtils.isBlank(req.getPosId())?null:Integer.valueOf(req.getPosId()));
			updateData.setLastVisitedDate(new Date() );
		/*	updateData.setSource(req.getSource());
			updateData.setSourceId(req.getSourceid()==null?null:Integer.valueOf(req.getSourceid()));
			updateData.setWillProvideReference(req.getWillProvideReference());
			updateData.setWillProvideRefId(Integer.valueOf(req.getWillProvideReferenceId()));;
			updateData.setReferenceName(req.getReferenceName());
			updateData.setReferenceNameId(req.getReferenceNameId()==null?null:Integer.valueOf(req.getReferenceNameId()));;
			*/
			clientrepo.save(updateData);
			log.info("Saved Details is ---> " + json.toJson(updateData));
			
			//Thread To Trigger Mail
			InsuranceCompanyMaster companyData =  insRepo.findByInsId(updateData.getInsCompanyId());
			ClaimLoginMaster loginData = loginRepo.findByLoginId(updateData.getCreatedBy());
			
			List<String>  ccMails = new ArrayList<String>();
			ccMails.add(companyData.getInsEmail());
			ccMails.add(loginData.getUserMail());
					
			List<String>  toMails = new ArrayList<String>();
			toMails.add(updateData.getEmailId());
			  

			Map<String, Object>  keys = new HashMap<String, Object>();
			keys.put("CLIENT_REF_NO", req1.getClientDetails().getClientRefNo() ==null?"":req1.getClientDetails().getClientRefNo());
						
			// Set Mail Request 
			MailFramingReq mailFrameReq = new MailFramingReq(); 
			mailFrameReq.setInsId(updateData.getInsCompanyId());
			mailFrameReq.setNotifTemplateId("CLIENT_INFO");
			mailFrameReq.setKeys(keys);	
			mailFrameReq.setMailCc(ccMails);
			mailFrameReq.setMailTo(toMails);
			mailFrameReq.setMailRegards(companyData.getRegards());
			mailFrameReq.setStatus(res.getResponse());
			
			log.info("{ Mail Pushed SuccessFully .  ClientRefNo is --->" + req1.getClientDetails().getClientRefNo() + " }"); 
		//			mailFrameService.sendSms(mailReq);
			mailThreadService.threadToSendMail(mailFrameReq); 
			
			res.setClientRefNo(clientRefNo);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	@Override
	public SuccessRes bulkEditClientInfo(ClientBulkEditReq req) {
		SuccessRes res = new SuccessRes();
		try {
			if(req.getClientRefNos()!=null && req.getClientRefNos().size()>0 ) {
				List<Integer> clientIds = req.getClientRefNos();
				
				// Get List  
				List<ClientDetails> getDatas = clientrepo.findByClientRefNoIn(clientIds);
				
				for (ClientDetails data : getDatas) {
					data.setPos(StringUtils.isBlank(req.getPos())?data.getPos() :req.getPos());				
					data.setPosId(StringUtils.isBlank(req.getPosId())?data.getPosId() :Integer.valueOf(req.getPosId()));
					data.setSource(StringUtils.isBlank(req.getSource())?data.getSource() :req.getSource());
					data.setSourceId(StringUtils.isBlank(req.getSourceid())?data.getSourceId() :Integer.valueOf(req.getSourceid()));
					data.setDefaultUser(StringUtils.isBlank(req.getDefaultUser())?data.getDefaultUser() :req.getDefaultUser());
					data.setDefaultUserId(StringUtils.isBlank(req.getDefaultUserid())?data.getDefaultUserId() :Integer.valueOf(req.getDefaultUserid()));
					data.setWillProvideReference(StringUtils.isBlank(req.getWillProvideReference())?data.getWillProvideReference() :req.getWillProvideReference());
					data.setWillProvideRefId(StringUtils.isBlank(req.getWillProvideReferenceId().toString())?data.getWillProvideRefId() :Integer.valueOf(req.getWillProvideReferenceId()));
					data.setReferenceName(StringUtils.isBlank(req.getReferenceName())?data.getReferenceName() :req.getReferenceName());
					data.setReferenceNameId(StringUtils.isBlank(req.getReferenceNameId())?data.getReferenceNameId() :Integer.valueOf(req.getReferenceNameId()));
					
					clientrepo.save(data);
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
	public ClientDetailsGetAllRes getallClients(ClientGetAllReq req) {
		ClientDetailsGetAllRes res = new ClientDetailsGetAllRes();
		ModelMapper mapper = new ModelMapper();
		try {
			
			// Limit , Offset
			Integer limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			Integer offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			// Find All
			Page<ClientDetails> clientDetails = clientrepo.findByInsCompanyIdAndBranchCodeOrderByLastVisitedDateDesc(paging, req.getInsId(),
							req.getBranchCode());
			
			List<String> clienIds = 	clientDetails.getContent().stream().map(ClientDetails :: getClientRefNo ).collect(Collectors.toList())  ;
			
			//Criteria 
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);	
			
			Root<ClientDetails>  c = query.from(ClientDetails.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Lead Count SubQuery
			Subquery<Long> leadCount = query.subquery(Long.class);
			Root<LeadDetails> l = leadCount.from(LeadDetails.class);
			leadCount.select(cb.count(l));
			javax.persistence.criteria.Predicate l1 = cb.equal(l.get("clientRefNo"), c.get("clientRefNo"));
			leadCount.where(l1);
			
			// Enquiry Count SubQuery
			Subquery<Long> enquiryCount = query.subquery(Long.class);
			Root<EnquiryDetails> e = enquiryCount.from(EnquiryDetails.class);
			enquiryCount.select(cb.count(e));
			javax.persistence.criteria.Predicate e1 = cb.equal(e.get("clientRefNo"), c.get("clientRefNo"));
			enquiryCount.where(e1);
			
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.equal(q.get("clientRefNo"), c.get("clientRefNo"));
			quoteCount.where(q1);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 =  cb.equal(p.get("clientRefNo"), c.get("clientRefNo"));
			policyCount.where(p1); 
			
			Expression<String>e0= c.get("clientRefNo");
			Predicate n1 = e0.in(clienIds);	
			
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(c.get("lastVisitedDate")));
			query.multiselect(c.get("clientRefNo").alias("ClientRefNo") , leadCount.alias("LeadCount") , enquiryCount.alias("EnquiryCount") ,
					                    quoteCount.alias("QuoteCount") ,  policyCount.alias("PolicyCount")) ;
			
			query.where(n1).groupBy(c.get("clientRefNo")).orderBy(orderList);
			TypedQuery<Tuple> result = em.createQuery(query);
			list =  result.getResultList(); 
			
			List<ClientDetailsGridRes> clientList = new ArrayList<ClientDetailsGridRes>();
			for (ClientDetails data : clientDetails.getContent()) {
				ClientDetailsGridRes clientData = new ClientDetailsGridRes();
				clientData = mapper.map(data, ClientDetailsGridRes.class);
				
				List<Tuple> filterList   =  list.stream().filter(o -> o.get("ClientRefNo").equals(data.getClientRefNo()) ).collect(Collectors.toList());
				if(filterList.size()>0    ) {
					Tuple counts = filterList.get(0) ;
					clientData.setLeadCount(counts.get("LeadCount")==null?"0":counts.get("LeadCount").toString());
					clientData.setEnquieryCount(counts.get("EnquiryCount")==null?"0":counts.get("EnquiryCount").toString());
					clientData.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
					clientData.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
					
				} else {
					clientData.setLeadCount("0");
					clientData.setEnquieryCount("0");
					clientData.setQuoteCount("0");
					clientData.setPolicyCount("0");
				}
				clientList.add(clientData);
			}
			res.setClientDetails(clientList);
			Long count1 = clientrepo.countByInsCompanyIdAndBranchCode(req.getInsId(),req.getBranchCode());
			res.setClientCount(count1);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}

	@Override
	public ClientSearchCountRes searchAllClients(ClientSearchReq req) {
		ClientSearchCountRes searchres = new ClientSearchCountRes();
		ModelMapper mapper = new ModelMapper();
		List<ClientDetails> clientDetails = new ArrayList<ClientDetails>();
		List<Tuple> list = new ArrayList<Tuple>();
		try {
			String searchValue = req.getSearchValue();// .toLowerCase() ;
			
			clientDetails = Criteria.ClientDynamicSearchCount(req);
		//	Long count = clientrepo.countByInsCompanyIdAndBranchCode(req.getInsId(),req.getBranchCode());			
			if(clientDetails.size() > 0) {
				List<String> clienIds = 	clientDetails.stream().map(ClientDetails :: getClientRefNo ).collect(Collectors.toList())  ;
				//Criteria 
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);	
				
				Root<ClientDetails>  c = query.from(ClientDetails.class);
				
				// Lead Count SubQuery
				Subquery<Long> leadCount = query.subquery(Long.class);
				Root<LeadDetails> l = leadCount.from(LeadDetails.class);
				leadCount.select(cb.count(l));
				javax.persistence.criteria.Predicate l1 = cb.equal(l.get("clientRefNo"), c.get("clientRefNo"));
				leadCount.where(l1);
				
				// Enquiry Count SubQuery
				Subquery<Long> enquiryCount = query.subquery(Long.class);
				Root<EnquiryDetails> e = enquiryCount.from(EnquiryDetails.class);
				enquiryCount.select(cb.count(e));
				javax.persistence.criteria.Predicate e1 = cb.equal(e.get("clientRefNo"), c.get("clientRefNo"));
				enquiryCount.where(e1);
				
				// Quote Count SubQuery
				Subquery<Long> quoteCount = query.subquery(Long.class);
				Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
				quoteCount.select(cb.count(q));
				javax.persistence.criteria.Predicate q1 = cb.equal(q.get("clientRefNo"), c.get("clientRefNo"));
				quoteCount.where(q1);
				
				// Policy Count SubQuery
				Subquery<Long> policyCount = query.subquery(Long.class);
				Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
				policyCount.select(cb.count(p));
				javax.persistence.criteria.Predicate p1 =  cb.equal(p.get("clientRefNo"), c.get("clientRefNo"));
				policyCount.where(p1); 
				
				Expression<String>e0= c.get("clientRefNo");
				Predicate n1 = e0.in(clienIds);	
				
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(c.get("lastVisitedDate")));
				query.multiselect(c.get("clientRefNo").alias("ClientRefNo") , leadCount.alias("LeadCount") , enquiryCount.alias("EnquiryCount") ,
						                    quoteCount.alias("QuoteCount") ,  policyCount.alias("PolicyCount")) ;
				
				query.where(n1).groupBy(c.get("clientRefNo")).orderBy(orderList);
				TypedQuery<Tuple> result = em.createQuery(query);
				list =  result.getResultList(); 
			
			}
			// Map 
			if (clientDetails != null && clientDetails.size() > 0) {
				// Map List
				List<ClientSearchRes> clientList = new ArrayList<ClientSearchRes>();
				for (ClientDetails data : clientDetails) {
					ClientSearchRes res = new ClientSearchRes();
					res = mapper.map(data, ClientSearchRes.class);
					
					List<Tuple> filterList   =  list.stream().filter(o -> o.get("ClientRefNo").equals(data.getClientRefNo()) ).collect(Collectors.toList());
					if(filterList.size()>0    ) {
						Tuple counts = filterList.get(0) ;
						res.setLeadCount(counts.get("LeadCount")==null?"0":counts.get("LeadCount").toString());
						res.setEnquiryCount(counts.get("EnquiryCount")==null?"0":counts.get("EnquiryCount").toString());
						res.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
						res.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
						
					} else {
						res.setLeadCount("0");
						res.setEnquiryCount("0");
						res.setQuoteCount("0");
						res.setPolicyCount("0");
					}
					
					clientList.add(res);	
				}
				
				searchres.setClientDetails(clientList);
				// COunt 
				req.setLimit("");
				req.setOffset("");
				Long clientCount = Long.valueOf(Criteria.ClientDynamicSearchCount(req).size());
				searchres.setClientCount(clientCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Error", e.getMessage());
			return null;
		}
		return searchres;
	}

	@Override
	public ClientLeadsGridRes getLeadEnquiryDetails(ClientViewReq req) {
		ClientLeadsGridRes res = new ClientLeadsGridRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			List<CrmLeadRes> leadList  = new ArrayList<CrmLeadRes>();
			List<EnquiryGridRes> enquiryList = new ArrayList<EnquiryGridRes>();
			List<QuoteGridRes> quoteList = new ArrayList<QuoteGridRes>();
			List<PolicyDetailsGetAllRes> policyList = new ArrayList<PolicyDetailsGetAllRes>();
			
			List<LeadDetails>  leads = leadRepo.findByClientRefNoOrderByUpdatedDateDesc(req.getClientRefNo() );		
			Type listType = new TypeToken<List<CrmLeadRes>>() {}.getType();
			leadList = mapper.map(leads , listType);
			
			List<EnquiryDetails>  enquirys = enquiryRepo.findByClientRefNoOrderByUpdatedDateDesc (req.getClientRefNo() );		
			listType = new TypeToken<List<EnquiryGridRes>>() {}.getType();
			enquiryList = mapper.map(enquirys , listType); 
			
			List<QuoteDetails>  quotes = quoteRepo.findByClientRefNoOrderByUpdatedDateDesc (req.getClientRefNo() );		
			listType = new TypeToken<List<QuoteGridRes>>() {}.getType();
			quoteList = mapper.map(quotes , listType); 
			
			List<PolicyDetails>  policies = policyRepo.findByClientRefNoOrderByEntryDateDesc (req.getClientRefNo() );		
			listType = new TypeToken<List<PolicyDetailsGetAllRes>>() {}.getType();
			policyList = mapper.map(policies , listType); 
		
			res.setLeadList(leadList);
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
