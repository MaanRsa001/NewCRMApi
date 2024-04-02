package com.maan.crm.service.impl;

import java.lang.reflect.Type;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.EnquiryRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.repository.PolicyDetailsRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.repository.StatusMasterRepository;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.EnquiryGetReq;
import com.maan.crm.req.EnquiryGridReq;
import com.maan.crm.req.EnquiryListReq;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.req.SearchEnquiryReq;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.EnquiryDetailsRes;
import com.maan.crm.res.EnquiryGetRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.EnquiryPageRes;
import com.maan.crm.res.LeadSearchRes;
import com.maan.crm.res.PolicyDetailsGetAllRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.SearchEnquiryRes;
import com.maan.crm.service.EnquiryService;

@Service
@Transactional
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private StatusMasterRepository statusrepo;

	@Autowired
	private EnquiryRepository enquiryRepo;
	
	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@Autowired
	private LeadRepository leadRepo ;
	

	@Autowired
	private CriteriaQueryServiceImpl Criteria;
	
	@Autowired
	private ClientDetailsRepository clientRepo ;
	
	@Autowired
	private ClaimLoginMasterRepository loginRepo;
	
	@Autowired
	private PolicyDetailsRepository policyRepo;

	private Logger log = LogManager.getLogger(EnquiryServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public EnquiryPageRes getEnquiryGrid(EnquiryGridReq req) {
		EnquiryPageRes enqRes = new EnquiryPageRes(); 
		List<EnquiryGridRes> resList = new ArrayList<EnquiryGridRes>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			List<EnquiryDetails> enqList = new ArrayList<EnquiryDetails>();
			
			Integer limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf( req.getLimit() );
			Integer offset = StringUtils.isBlank(req.getOffset())?1000 : Integer.valueOf( req.getOffset()) ;
			
			Pageable paging = PageRequest.of(limit,offset);
			Page<EnquiryDetails> enquirys ;

			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
				
				ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EnquiryDetails> query = cb.createQuery(EnquiryDetails.class);
				Root<EnquiryDetails> e = query.from(EnquiryDetails.class);		
							
				javax.persistence.criteria.Predicate n1 = cb.like(e.get("allotedUwId"),"%" +  loginData.getAgencyCode() + "%" );
				javax.persistence.criteria.Predicate n2 = cb.equal(e.get("enqStatus"),req.getSubStatusCode() );
				javax.persistence.criteria.Predicate n3 = cb.equal(e.get("branchCode"),req.getBranchCode() );
				javax.persistence.criteria.Predicate n4 = cb.equal(e.get("insCompanyId"),req.getInsCompanyId() );
				// Order By
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(cb.desc(e.get("updatedDate")));
				query.orderBy(orderList);
				
				query.select(e).where(n1,n2,n3,n4).orderBy(orderList);
				TypedQuery<EnquiryDetails> result = em.createQuery(query);
				result.setFirstResult(limit * offset);
				result.setMaxResults(offset);
				enqList = result.getResultList();
								
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				enquirys = enquiryRepo.findByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
								paging , req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
				enqList = enquirys.getContent() ;
				
			} else {
				enquirys = enquiryRepo.findByEnqStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
						paging , req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
				
				enqList = enquirys.getContent() ;
			}
			
			List<String> enqIds = 	enqList.stream().map(EnquiryDetails :: getEnquiryId ).collect(Collectors.toList())  ;
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

			Root<EnquiryDetails> e = query.from(EnquiryDetails.class);
			List<Tuple> list = new ArrayList<Tuple>();
		
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.equal(q.get("enquiryId"), e.get("enquiryId"));
			quoteCount.where(q1);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 = cb.equal(p.get("enquiryId"), e.get("enquiryId"));
			policyCount.where(p1);
			
			Expression<String> e0 = e.get("enquiryId");
			Predicate n1 = e0.in(enqIds);

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(e.get("updatedDate")));

			query.multiselect(e.get("enquiryId").alias("EnquiryId"),quoteCount.alias("QuoteCount") ,policyCount.alias("PolicyCount") );
			
			query.where(n1).groupBy(e.get("enquiryId")).orderBy(orderList);
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			
			for (EnquiryDetails data : enqList) {
				EnquiryGridRes res = new EnquiryGridRes();
				res = mapper.map(data ,EnquiryGridRes.class );
				
				List<Tuple> filterList   =   list.stream().filter(o -> o.get("EnquiryId").equals(data.getEnquiryId())).collect(Collectors.toList());
				if(filterList.size()>0    ) {
					Tuple counts = filterList.get(0) ;
					res.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
					res.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
					
				} else {
					res.setQuoteCount("0");
					res.setPolicyCount("0");
				}
				resList.add(res);
			}
			
		/*	Type listType = new TypeToken<List<EnquiryGridRes>>() {}.getType();
			resList = mapper.map(enqList , listType); */
			enqRes.setEnquiryList(resList);
			// COunt 
			Long enqCount = 0L;
			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
				
				ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());
				
				CriteriaBuilder cb2 = em.getCriteriaBuilder();
				CriteriaQuery<Long> query2 = cb.createQuery(Long.class);
				Root<EnquiryDetails> e2 = query2.from(EnquiryDetails.class);		
							
				javax.persistence.criteria.Predicate a1 = cb.like(e.get("allotedUwId"),"%" +  loginData.getAgencyCode() + "%" );
				javax.persistence.criteria.Predicate a2 = cb.equal(e.get("enqStatus"),req.getSubStatusCode() );
				javax.persistence.criteria.Predicate a3 = cb.equal(e.get("branchCode"),req.getBranchCode() );
				javax.persistence.criteria.Predicate a4 = cb.equal(e.get("insCompanyId"),req.getInsCompanyId() );
				// Order By
				List<Order> orderList2 = new ArrayList<Order>();
				orderList2.add(cb2.desc(e2.get("updatedDate")));
				query.orderBy(orderList2);
				
				query2.select(cb2.count(e2) ).where(a1,a2,a3,a4).orderBy(orderList);
				TypedQuery<Long> result2 = em.createQuery(query2);
				enqCount = result2.getSingleResult() ;
								
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				enqCount = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
								 req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
				
			} else {
				enqCount = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
						req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
				
			}
			enqRes.setEnquiryCount(enqCount);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return enqRes;
	}

	@Override
	public EnquiryGetRes getEnquiryDetails(EnquiryGetReq req) {
		EnquiryGetRes res = new EnquiryGetRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			EnquiryDetails enq = enquiryRepo.findByEnquiryId(req.getEnquiryId());
			LeadDetails lead = leadRepo.findByLeadId(enq.getLeadId());
			ClientDetails client = clientRepo.findByClientRefNo(enq.getClientRefNo());
			
			Long quoteCount = quoteRepo.countByEnquiryId(req.getEnquiryId());
			
			ClientDetailsGridRes clientRes = mapper.map(client, ClientDetailsGridRes.class );
			LeadSearchRes leadRes = mapper.map(lead, LeadSearchRes.class );
			leadRes.setBusinessType(enq.getBusinessType().replace("\r\n", ""));
			EnquiryDetailsRes enquiryRes = mapper.map(enq, EnquiryDetailsRes.class ); 
			enquiryRes.setBusinessType(enq.getBusinessType().replace("\r\n", ""));
			
			res.setClientDetails(clientRes);
			res.setLeadDetails(leadRes);
			res.setEnquiryDetails(enquiryRes);	
			res.setQuoteCount(quoteCount);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<EnquiryGridRes> getLeadEnquiryDetails(LeadEnquiryGetReq req) {
		List<EnquiryGridRes> resList = new ArrayList<EnquiryGridRes>();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			Integer limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf( req.getLimit() );
			Integer offset = StringUtils.isBlank(req.getOffset())?1000 : Integer.valueOf( req.getOffset()) ;
			
			Pageable paging = PageRequest.of(limit,offset);
			Page<EnquiryDetails>  enquirys = enquiryRepo.findByLeadIdOrderByUpdatedDateDesc (paging ,req.getLeadId() );
				
			Type listType = new TypeToken<List<EnquiryGridRes>>() {}.getType();
			resList = mapper.map(enquirys.getContent() , listType);
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return resList;
	}

	@Override
	public Long getEquiryCount(EnquiryGridReq req) {
		Long count = 0L ;
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
		
				ClaimLoginMaster loginData = loginRepo.findByLoginId(req.getCreatedBy());
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Long> query = cb.createQuery(Long.class);
				Root<EnquiryDetails> e = query.from(EnquiryDetails.class);		
							
				javax.persistence.criteria.Predicate n1 = cb.like(e.get("allotedUwId"),"%" +  loginData.getAgencyCode() + "%" );
				javax.persistence.criteria.Predicate n2 = cb.equal(e.get("enqStatus"),req.getSubStatusCode() );
				javax.persistence.criteria.Predicate n3 = cb.equal(e.get("branchCode"),req.getBranchCode() );
				javax.persistence.criteria.Predicate n4 = cb.equal(e.get("insCompanyId"),req.getInsCompanyId() );
				
				query.select(cb.count(e)  ).where(n1,n2,n3,n4);
				TypedQuery<Long> result = em.createQuery(query);
				count = result.getResultList().get(0);
				
			} else if (req.getUserType().equalsIgnoreCase("SalesManager")) {
				count = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
								req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getUserType());
	
			} else {
				count = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
						req.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId());
				
			}


		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is --->" + e.getMessage());
			return null;
		}
		return count;
	}

	@Override
	public List<SearchEnquiryRes> searchenquiry(SearchEnquiryReq req) {
		List<SearchEnquiryRes> resList = new ArrayList<SearchEnquiryRes>();
		ModelMapper mapper = new ModelMapper();
		List<EnquiryDetails> enquiryDetails = new ArrayList<EnquiryDetails>();
		List<Tuple> quotes = new  ArrayList<Tuple>();
		List<Tuple> policies = new  ArrayList<Tuple>();
	
		try {
				String searchValue = req.getSearchValue();
				Integer limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
				Integer offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
				Pageable paging = PageRequest.of(limit, offset);

			enquiryDetails = Criteria.EnquiryDynamicSearch(searchValue);
			List<String> enqIds = 	enquiryDetails.stream().map(EnquiryDetails :: getEnquiryId ).collect(Collectors.toList())  ;
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

			Root<EnquiryDetails> e = query.from(EnquiryDetails.class);
			List<Tuple> list = new ArrayList<Tuple>();
		
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.equal(q.get("enquiryId"), e.get("enquiryId"));
			quoteCount.where(q1);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 = cb.equal(p.get("enquiryId"), e.get("enquiryId"));
			policyCount.where(p1);
			
			Expression<String> e0 = e.get("enquiryId");
			Predicate n1 = e0.in(enqIds);

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.desc(e.get("updatedDate")));

			query.multiselect(e.get("enquiryId").alias("EnquiryId"),quoteCount.alias("QuoteCount")  );
			
			query.where(n1).groupBy(e.get("enquiryId")).orderBy(orderList);
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
		
			if (enquiryDetails != null && enquiryDetails.size() > 0) {
				// Map List
				for (EnquiryDetails data : enquiryDetails) {
					SearchEnquiryRes res = new SearchEnquiryRes();
					res = mapper.map(data ,SearchEnquiryRes.class );
					
					List<Tuple> filterList   =   list.stream().filter(o -> o.get("EnquiryId").equals(data.getEnquiryId())).collect(Collectors.toList());
					if(filterList.size()>0    ) {
						Tuple counts = filterList.get(0) ;
						res.setQuoteCount(counts.get("QuoteCount")==null?"0":counts.get("QuoteCount").toString());
						res.setPolicyCount(counts.get("PolicyCount")==null?"0":counts.get("PolicyCount").toString());
						
					} else {
						res.setQuoteCount("0");
						res.setPolicyCount("0");
					}
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
	public ClientLeadsGridRes getLeadEnquiryDetails(EnquiryListReq req) {
		ClientLeadsGridRes res = new ClientLeadsGridRes();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		try {
			List<QuoteGridRes> quoteList = new ArrayList<QuoteGridRes>();
			List<PolicyDetailsGetAllRes> policyList = new ArrayList<PolicyDetailsGetAllRes>();
			
			List<QuoteDetails>  quotes = quoteRepo.findByEnquiryIdOrderByUpdatedDateDesc (req.getEnquiryId() );		
			Type listType = new TypeToken<List<QuoteGridRes>>() {}.getType();
			quoteList = mapper.map(quotes , listType); 
			
			List<PolicyDetails>  policies = policyRepo.findByEnquiryIdOrderByEntryDateDesc (req.getEnquiryId() );		
			listType = new TypeToken<List<PolicyDetailsGetAllRes>>() {}.getType();
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
	
	
	
	/*
	 	@Override
	public List<EnquiryRes> getenquiry(EnquiryReq req) {
		List<EnquiryRes> resList = new ArrayList<EnquiryRes>();
		try {
			String status = "Enquiry";
			List<StatusMaster> statusmaster = statusrepo.findByStatusCodeOrderByOrderId(status);
			for (StatusMaster data : statusmaster) {

				EnquiryRes res = new EnquiryRes();
				res.setSubStatusCode(data.getSubStatusCode());
				res.setSubStatusName(data.getSubStatusName());
				Long enquiryCount = 0L;

				if (req.getUserType().equalsIgnoreCase("UnderWritter")) {
					enquiryCount = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdAndAllotedUw(
							data.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getCreatedBy());
				} else {
					enquiryCount = enquiryRepo.countByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedBy(
							data.getSubStatusCode(), req.getBranchCode(), req.getInsCompanyId(), req.getCreatedBy());
				}
				res.setCount(enquiryCount);

				resList.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details" + e.getMessage());
			return null;
		}
		return resList;
	}
	 */
}
