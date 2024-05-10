package com.maan.crm.service.impl;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuotationDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.bean.SequenceMaster;
import com.maan.crm.repository.PolicyDetailsRepository;
import com.maan.crm.repository.QuotationDetailsRepo;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.repository.SequenceMasterRepository;
import com.maan.crm.req.EnquiryGetReq;
import com.maan.crm.req.EnquiryGridReq;
import com.maan.crm.req.EnquiryListReq;
import com.maan.crm.req.GetbyEnquiryQuoteReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.QuotationDetailsSaveReq;
import com.maan.crm.req.QuoteGetAllReq;
import com.maan.crm.req.QuoteGetReq;
import com.maan.crm.req.QuoteListReq;
import com.maan.crm.req.QuoteSearchReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.QuotePageRes;
import com.maan.crm.res.QuoteSearchCountRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.QuoteService;
import com.maan.crm.util.error.Error;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

	private Logger log = LogManager.getLogger(QuoteServiceImpl.class);

	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@Autowired
	private CriteriaQueryServiceImpl Criteria;

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PolicyDetailsRepository policyRepo;
	
	@Autowired 
	private QuotationDetailsRepo quotationRepo;
	
	@Autowired
	private SequenceMasterRepository sequenceMasterRepo;
	
	@Override
	public QuotePageRes getallQuote(QuoteGetAllReq req) {
		QuotePageRes quRes = new QuotePageRes();
		List<QuoteGridRes> resList = new ArrayList<QuoteGridRes>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {

			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			// Find

			Page<QuoteDetails> quotes;

			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
				quotes = quoteRepo.findByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedByOrderByUpdatedDateDesc(paging,
								req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getCreatedBy());
				
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				quotes = quoteRepo.findByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(
								paging, req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(),
								req.getUserType());
			} else {
				quotes = quoteRepo.findByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(paging,
						req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
			}
			Type listType = new TypeToken<List<QuoteGridRes>>() {}.getType();
			resList = mapper.map(quotes.getContent(), listType);
			
			if(resList.size()>0 ) {
			resList.get(0).setClientName("Samilla");
			resList.get(0).setPolicyCount("0");
			}
			quRes.setQuoteList(resList);
			// Count 
			Long quCount = 0L ;
			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
				quCount = quoteRepo.countByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedByOrderByUpdatedDateDesc(
								req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getCreatedBy());
				
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				quCount = quoteRepo.countByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(
								 req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
				
			} else {
				quCount = quoteRepo.countByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
						req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
			}
			quRes.setQuoteCount(quCount);			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return quRes;
	}

	@Override
	public QuoteGetRes getQuote(QuoteGetReq req) {
		QuoteGetRes res = new QuoteGetRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			QuoteDetails quote = quoteRepo.findByQuoteNo(req.getQuoteNo());
			mapper.map(quote, res);
			// res.setBusinessType(quote.getBusinessType().replace("\r\n", ""));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<QuoteGetRes> getbyenquiryid(GetbyEnquiryQuoteReq req) {
		List<QuoteGetRes> resList = new ArrayList<QuoteGetRes>();
		ModelMapper mapper = new ModelMapper();
		try {

			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			Page<QuoteDetails> quotedetails = quoteRepo.findByEnquiryIdOrderByUpdatedDateAsc(paging,
					req.getEnquiryId());
			for (QuoteDetails data : quotedetails.getContent()) {
				QuoteGetRes res = new QuoteGetRes();
				res = mapper.map(data, QuoteGetRes.class);
				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return resList;

	}

	@Override
	public Long getQuoteCount(QuoteGetAllReq req) {
		Long count = 0L;
		try {
			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
				count = quoteRepo
						.countByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
								req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				count = quoteRepo
						.countByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(
								req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
			} else {
				count = quoteRepo.countByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
						req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return count;
	}

	// Search Count
	@Override
	public QuoteSearchCountRes searchQuoteCount(QuoteSearchReq req) {
		QuoteSearchCountRes res = new QuoteSearchCountRes();

		List<Tuple> enquirys = new ArrayList<Tuple>();
		List<Tuple> policy = new ArrayList<Tuple>();

		ModelMapper mapper = new ModelMapper();
		List<QuoteDetails> quoteDetails = new ArrayList<QuoteDetails>();
		try {
			// Limit , Offset
			Integer limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			Integer offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			// Find All
			String searchValue = req.getSearchValue();// .toLowerCase() ;

			if (StringUtils.isNotBlank(searchValue) && searchValue.matches("[0-9]+") && searchValue.length() <= 5) {
				quoteDetails = Criteria.QuoteDynamicSearch1(req);
			}

			if (quoteDetails.size() <= 0) {
				quoteDetails = Criteria.QuoteDynamicSearch2(req);
			}
			
			List<String> leadIds = 	quoteDetails.stream().map(QuoteDetails :: getLeadId ).collect(Collectors.toList())  ;

			// Policy Counts
			{
				// Criteria
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

				Root<LeadDetails> l = query.from(LeadDetails.class);
				Root<PolicyDetails> p = query.from(PolicyDetails.class);

				Expression<String> e0 = l.get("leadId");
				Predicate n1 = e0.in(leadIds);

				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(l.get("updatedDate")));
				query.multiselect(l.get("leadId").alias("LeadId"), cb.count(p).alias("PolicyCount"));

				Predicate n2 = cb.equal(l.get("leadId"), p.get("leadId"));
				query.where(n1, n2).groupBy(l.get("leadId")).orderBy(orderList);
				TypedQuery<Tuple> result = em.createQuery(query);
				policy = result.getResultList();

			}

			List<QuoteGetRes> quoteList = new ArrayList<QuoteGetRes>();
			for (QuoteDetails data : quoteDetails) {
				String  policyCount = "0";

				QuoteGetRes quoteData = new QuoteGetRes();
				quoteData = mapper.map(data, QuoteGetRes.class);

			
				List<Tuple> filterLead = policy.stream().filter(o -> o.get("LeadId").equals(data.getLeadId())).collect(Collectors.toList());

				if (filterLead.size() > 0) {
					policyCount = filterLead.get(0).get("PolicyCount") == null ? "": filterLead.get(0).get("PolicyCount").toString();
				}

			
				quoteData.setPolicyCount(policyCount);
				quoteList.add(quoteData);
			}
			res.setQuoteDetails(quoteList);
			Long count1 = quoteRepo.countByInsCompanyIdAndBranchCode(req.getInsId(), req.getBranchCode());
			res.setQuoteCount(count1);

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}
	
	
	

	@Override
	public ClientLeadsGridRes getQuotePolicyDetails(QuoteListReq req) {
		ClientLeadsGridRes res = new ClientLeadsGridRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			List<QuoteGridRes> quoteList = new ArrayList<QuoteGridRes>();
			List<PolicyDetailsGetAllRes> policyList = new ArrayList<PolicyDetailsGetAllRes>();
			
			List<PolicyDetails>  policies = policyRepo.findByQuoteNoOrderByEntryDateDesc (Integer.valueOf(req.getQuoteNo()) );		
			Type listType = new TypeToken<List<PolicyDetailsGetAllRes>>() {}.getType();
			policyList = mapper.map(policies , listType); 
		
			res.setQuoteList(quoteList);
			res.setPolicyList(policyList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<Error> validateQuotationDetails(QuotationDetailsSaveReq req) {
		List<Error> errors = new ArrayList<Error>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());

			return errors;
		}
		return errors;
	}

	@Override
	public SuccessRes saveQuotationDetails(QuotationDetailsSaveReq req) {
		SuccessRes res = new SuccessRes();

		ModelMapper mapper = new ModelMapper();
		try {
			String quoteNo = "Q";
			if(req.getQuoteNo() != null && StringUtils.isNotBlank(String.valueOf(req.getQuoteNo()))) {
				QuotationDetails quote = quotationRepo.findByQuoteNoAndClientId(req.getQuoteNo(),req.getClientId());
				if(quote!=null) {
					quote = mapper.map(req, QuotationDetails.class);
					quotationRepo.save(quote);
					res.setResponse("Updated Successfully");
					res.setSucessId(String.valueOf(req.getQuoteNo()));
					return res;
				}
				
			}
				SequenceMaster sequence = sequenceMasterRepo.findBySequenceName("QUOTE_NO");
				quoteNo = quoteNo + sequence.getSequenceValue();
				req.setQuoteNo(quoteNo);
				QuotationDetails quote = mapper.map(req, QuotationDetails.class);
				quotationRepo.save(quote);
				res.setResponse("Inserted Successfully");
				res.setSucessId(String.valueOf(req.getQuoteNo()));
				
				//updating the sequence
				sequence.setSequenceValue(sequence.getSequenceValue() +1);
				sequenceMasterRepo.save(sequence);				
			
						
		} catch (Exception ex) {
			log.error(ex);
			return null;
		}
		return res;
	
	}

}
