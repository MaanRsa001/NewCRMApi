package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.internal.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.MenuMaster;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.bean.StatusMaster;
import com.maan.crm.bean.SubStatusMaster;
import com.maan.crm.repository.EnquiryRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.repository.StatusMasterRepository;
import com.maan.crm.repository.SubStatusMasterRepository;
import com.maan.crm.req.StatusMasterReq;
import com.maan.crm.req.UpdateEnquiryStatusReq;
import com.maan.crm.req.UpdateQuoteStatusReq;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.res.EnquirySuccessRes;
import com.maan.crm.res.QuoteSuccessRes;
import com.maan.crm.service.StatusMasterService;

@Service
@Transactional
public class StatusMasterServiceImpl implements StatusMasterService {

	private Logger log = LogManager.getLogger(StatusMasterServiceImpl.class);

	@Autowired
	private StatusMasterRepository statusrepo;

	@Autowired
	private LeadRepository leadRepo;

	@Autowired
	private EnquiryRepository enquiryRepo;

	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@Autowired
	private SubStatusMasterRepository subStatusRepo ;
	
	@PersistenceContext
	private EntityManager em;

	Gson json = new Gson();

	@Override
	public List<DropDownResA> getstatusmaster(StatusMasterReq req) {
		List<DropDownResA> resList = new ArrayList<DropDownResA>();
		try {
			String subStatus = StringUtils.isBlank(req.getSubStatusCode())?"Lead" :req.getSubStatusCode() ;
			
			// Menu List 
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SubStatusMaster> query = cb.createQuery(SubStatusMaster.class);
			Root<SubStatusMaster> s = query.from(SubStatusMaster.class);		
						
			javax.persistence.criteria.Predicate n1 = cb.like(s.get("userType"),"%" + req.getUserType() + "%" );
			javax.persistence.criteria.Predicate n2 = cb.equal(s.get("statusCode"),req.getStatusCode() );
			javax.persistence.criteria.Predicate n3 = cb.equal(s.get("subStatusCode"),subStatus );
			
			query.select(s).where(n1,n2,n3);
			TypedQuery<SubStatusMaster> result = em.createQuery(query);
			List<SubStatusMaster> list = result.getResultList();
			SubStatusMaster subStatusData = null; 
			if(list.size()>0 ) {
				subStatusData = list.get(0);
			}
			
			if(subStatusData != null ) {
				List<String> nextSubStatuses = new ArrayList<String>(Arrays.asList(subStatusData.getNextStatus().split(",")));
				List<StatusMaster> statusMaster = statusrepo.findByStatusCodeAndSubStatusCodeInOrderByOrderIdAsc(req.getStatusCode() , nextSubStatuses);
				
				for (StatusMaster data : statusMaster) {
					DropDownResA res = new DropDownResA();
					res.setCode(data.getSubStatusCode());
					res.setCodeDesc(data.getSubStatusName());
					resList.add(res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public EnquirySuccessRes updateEnquiryStatus(UpdateEnquiryStatusReq req) {
		EnquirySuccessRes res = new EnquirySuccessRes();
		ModelMapper mapper = new ModelMapper();
		try {

			StatusMaster statusData = statusrepo.findByStatusCodeAndSubStatusCode("Enquiry", req.getSubStatusCode());
			LeadDetails lead = leadRepo.findByLeadId(req.getLeadId());
			EnquiryDetails enq = new EnquiryDetails();
			String enqId = "";

			if (StringUtils.isBlank(req.getEnquiryId())) {
				// Insert
				Long count = enquiryRepo.count();
				Random rnd = new Random();
				int number = rnd.nextInt(100);
				String randomNo = String.format("%02d", number);
				Long Id = count + 100;
				enqId = "E" + Id.toString() + randomNo;

				mapper.map(lead, enq);
				enq.setEntryDate(new Date());
				enq.setCreatedBy(req.getCreatedBy());
				enq.setCreatedUsertype(req.getUserType());
				enq.setLeadId(req.getLeadId());
				enq.setClientRefNo(req.getClientRefNo());

				res.setResponse("Enquiry Saved Successfully");
				res.setEnquiryId(enqId);
			} else {
				// Update
				enqId = req.getEnquiryId();
				EnquiryDetails findEnq = enquiryRepo.findByEnquiryId(enqId);
				enq = findEnq;

				res.setResponse("Enquiry Updated Successfully");
				res.setEnquiryId(enqId);
			}

			// Restricted
			if (!req.getUserType().equalsIgnoreCase("UnderWritter")) {

				if (req.getSubStatusCode().equalsIgnoreCase("PFE")) {
					enq.setAllotedUw(req.getAllotedUW());
					enq.setAllotedUwId(req.getAlloterUWId());
				}
				enq.setEnqPremiumRate(
						StringUtil.isBlank(req.getPremiumRate()) ? 0D : Double.valueOf(req.getPremiumRate()));
				enq.setEnquiryDescription(req.getEnquiryDesc());
				enq.setSuggestPremium(
						StringUtil.isBlank(req.getSuggestedPremium()) ? 0D : Double.valueOf(req.getSuggestedPremium()));
				enq.setSumInsured(StringUtil.isBlank(req.getSumInsured()) ? 0D : Double.valueOf(req.getSumInsured()));
			}

			// Common Save
			enq.setEnquiryId(enqId);
			enq.setEnqStatus(req.getSubStatusCode());
			enq.setEnqStatusDesc(statusData.getSubStatusName());
			enq.setUpdatedBy(req.getCreatedBy());
			enq.setUpdatedDate(new Date());
			enq.setUpdaterUsertype(req.getUserType());
			enq.setLastRemarks(req.getRemarks());
			enq.setBroughtBy(req.getBroughtBy());
			enq.setBroughtCode(req.getBroughtCode());
			enq.setBroughtName(req.getBroughtName());
			
			enquiryRepo.save(enq);
			log.info("Saved Details is ---> " + json.toJson(enq));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public QuoteSuccessRes updateQuoteStatus(UpdateQuoteStatusReq req) {
		QuoteSuccessRes res = new QuoteSuccessRes();
		ModelMapper mapper = new ModelMapper();
		try {

		//	StatusMaster statusData = statusrepo.findByStatusCodeAndSubStatusCode("Quote", req.getSubStatusCode());
		//	LeadDetails lead = leadRepo.findByLeadId(req.getLeadId());
			EnquiryDetails enqu = enquiryRepo.findByEnquiryId(req.getEnquiryId());
			QuoteDetails qut = new QuoteDetails();
			String quoteNo = "";

			if (StringUtils.isBlank(req.getQuoteNo())) {
				// Insert
				Long count = quoteRepo.count();
				Random rnd = new Random();
				int number = rnd.nextInt(100);
				String randomNo = String.format("%02d", number);
				Long Id = count + 100;
				quoteNo = Id.toString() + randomNo;
				mapper.map(enqu, qut);
				qut.setEntryDate(new Date());
				qut.setQuoteNo(quoteNo);
				qut.setReferenceNo("Q" + quoteNo);
				qut.setCreatedBy(req.getCreatedBy());
				qut.setCreatedUsertype(req.getUserType() );;
				res.setResponse("Quote Saved Successfully");
				res.setQuoteNo(quoteNo);
			} else {
				// Update
				quoteNo = req.getQuoteNo();
				QuoteDetails findEnq = quoteRepo.findByQuoteNo(quoteNo);
				qut = findEnq;

				res.setResponse("Quote Updated Successfully");
				res.setQuoteNo(quoteNo);
			}

			// Restricted
			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {

				qut.setGrossPremium(StringUtil.isBlank(req.getTotalPremium()) ? 0D : Double.valueOf(req.getTotalPremium()));
				qut.setTotalPremium(StringUtil.isBlank(req.getTotalPremium()) ? 0D : Double.valueOf(req.getTotalPremium()));
				qut.setQuoteDesc(req.getQuoteDesc());
				qut.setTaxPercent(StringUtil.isBlank(req.getTax()) ? 0 : Integer.valueOf(req.getTax()));
				//qut.setSuggestPremium(StringUtil.isBlank(req.getSuggestedPremium()) ? 0D : Double.valueOf(req.getSuggestedPremium()));
				qut.setSumInsured(StringUtil.isBlank(req.getSumInsured()) ? 0D : Double.valueOf(req.getSumInsured()));
				qut.setPremiumRate(StringUtil.isBlank(req.getPremiumRate()) ? 0D : Double.valueOf(req.getPremiumRate()));
				qut.setAddInfoDiscount(StringUtil.isBlank(req.getAddInfoDiscount()) ? 0 : Integer.valueOf(req.getAddInfoDiscount()));
			}

			// Common Save
			qut.setQuoteNo(quoteNo);
			qut.setEnquiryId(req.getEnquiryId());
			qut.setUpdatedBy(req.getCreatedBy());
			qut.setUpdatedDate(new Date());
			qut.setUpdaterUsertype(req.getUserType());
			qut.setLastRemarks(req.getRemarks());
			qut.setQuoteStatus(req.getSubStatusCode());
			qut.setQuoteStatusDesc(req.getQuoteStatusDesc());
			qut.setEnqCreatedBy(enqu.getCreatedBy());
			qut.setEnqCreaterUsertype(enqu.getCreatedUsertype());
			quoteRepo.save(qut);
			log.info("Saved Details is ---> " + json.toJson(qut));

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
			return null;
		}
		return res;
	}

}
