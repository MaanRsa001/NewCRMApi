package com.maan.crm.service.impl;

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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.maan.crm.auth.dto.LoginRequest;
import com.maan.crm.auth.token.passwordEnc;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientAddressDetails;
import com.maan.crm.bean.ClientCorContact;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.PolicySearchReq;
import com.maan.crm.req.ProspectSearchReq;

import com.maan.crm.req.SearchEnquiryReq;

import com.maan.crm.req.QuoteSearchReq;

import com.maan.crm.service.CriteriaQueryService;

@Component
public class CriteriaQueryServiceImpl implements CriteriaQueryService {

	private Logger log = LogManager.getLogger(CriteriaQueryServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	public List<ClaimLoginMaster> isvalidUser(LoginRequest req) {
		List<ClaimLoginMaster> data = new ArrayList<ClaimLoginMaster>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClaimLoginMaster> query = cb.createQuery(ClaimLoginMaster.class);

			Root<ClaimLoginMaster> login = query.from(ClaimLoginMaster.class);

			passwordEnc passEnc = new passwordEnc();
			String password = passEnc.crypt(req.getPassword().trim());

			Predicate p1 = cb.equal(login.get("loginId"), req.getUserId());
			Predicate p3 = cb.equal(login.get("password"), password);
			Predicate p2 = cb.equal(login.get("status"), "Y");

			Predicate p4 = cb.equal(login.get("userType"), req.getUserType());

			query.select(login).where(p1, p2, p3, p4);

			TypedQuery<ClaimLoginMaster> result = em.createQuery(query);
			data = result.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public List<ClientDetails> ClientDynamicSearch(String searchValue , String createdBy ) {

		List<ClientDetails> data = new ArrayList<ClientDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClientDetails> query = cb.createQuery(ClientDetails.class);

			Root<ClientDetails> ClientDetails = query.from(ClientDetails.class);
			Predicate p2 = cb.equal(ClientDetails.get("createdBy"), createdBy );
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(ClientDetails.get("mobileNumber")), "%" + searchValue + "%");
			query.select(ClientDetails).where(p1, p2);
			TypedQuery<ClientDetails> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(ClientDetails.get("emailId")), "%" + searchValue + "%");
			query.select(ClientDetails).where(p1, p2);
			result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(ClientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2);
			result = em.createQuery(query);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(ClientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2);
			result = em.createQuery(query);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(ClientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2);
			result = em.createQuery(query);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public List<ClientDetails>  ClientDynamicSearch1(List<String>  searchValue) {
		
		List<ClientDetails> data = new ArrayList<ClientDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClientDetails> query = cb.createQuery(ClientDetails.class);
			Root<ClientDetails> ClientDetails = query.from(ClientDetails.class);		
			
			Path<Object> e1 = ClientDetails.get("clientRefNo");
			//clientRefNo
			Predicate p1 = e1.in(searchValue);
			
			query.select(ClientDetails).where(p1);
			TypedQuery<ClientDetails> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public List<ClientAddressDetails>  ClientAddressDynamicSearch(String  searchValue) {
		
		List<ClientAddressDetails> data = new ArrayList<ClientAddressDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClientAddressDetails> query = cb.createQuery(ClientAddressDetails.class);
			Root<ClientAddressDetails> clientaddressdetails = query.from(ClientAddressDetails.class);		
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientaddressdetails.get("mobileNo")), "%" + searchValue + "%");
			query.select(clientaddressdetails).where(p1);
			TypedQuery<ClientAddressDetails> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientaddressdetails.get("emailId")), "%" + searchValue + "%");
			query.select(clientaddressdetails).where(p1);
			result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public List<ClientCorContact>  ClientCorContactDynamicSearch(String  searchValue) {
		
		List<ClientCorContact> data = new ArrayList<ClientCorContact>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClientCorContact> query = cb.createQuery(ClientCorContact.class);
			Root<ClientCorContact> clientaddressdetails = query.from(ClientCorContact.class);		
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientaddressdetails.get("mobileNo")), "%" + searchValue + "%");
			query.select(clientaddressdetails).where(p1);
			TypedQuery<ClientCorContact> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientaddressdetails.get("email")), "%" + searchValue + "%");
			query.select(clientaddressdetails).where(p1);
			result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
		public List<LeadDetails>  LeadDynamicSearch1(String  searchValue, String createdBy) {
		
		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<LeadDetails> query = cb.createQuery(LeadDetails.class);
			Root<LeadDetails> leadDetails = query.from(LeadDetails.class);		
			Predicate p2 = cb.equal(leadDetails.get("createdBy"), createdBy);
			
			//Lead No
			Predicate p1 = cb.equal(leadDetails.get("leadId"), Integer.valueOf(searchValue) );
			query.select(leadDetails).where(p1,p2);
			TypedQuery<LeadDetails> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}

		
	public List<LeadDetails> LeadDynamicSearch2(String searchValue , String createdBy ) {

		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<LeadDetails> l = cb.createQuery(LeadDetails.class);

			Root<ClientDetails> clientDetails = l.from(ClientDetails.class);
			Root<LeadDetails> leadDetails = l.from(LeadDetails.class);
			Predicate p2 = cb.equal(cb.upper(leadDetails.get("createdBy")), createdBy );
			Predicate p3 = cb.equal(cb.upper(leadDetails.get("clientRefNo")), clientDetails.get("clientRefNo"));
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientDetails.get("mobileNumber")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3);
			TypedQuery<LeadDetails> result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientDetails.get("emailId")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(clientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1,  p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(clientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(clientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}	
		

public List<LeadDetails>  ProspectDynamicSearch1(ProspectSearchReq req) {
		
		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<LeadDetails> query = cb.createQuery(LeadDetails.class);
			Root<LeadDetails> leadDetails = query.from(LeadDetails.class);		
			Predicate p2 = cb.equal(leadDetails .get("insCompanyId"), req.getInsId());
			Predicate p3 = cb.equal(leadDetails .get("branchCode"), req.getBranchCode());
			Predicate p4 = cb.equal(leadDetails .get("status"), "Quote");
			
			//Lead No
			Predicate p1 = cb.equal(leadDetails.get("leadId"), Integer.valueOf(req.getSearchValue()) );
			query.select(leadDetails).where(p1,p2,p3,p4);
			TypedQuery<LeadDetails> result = em.createQuery(query);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
				log.info("Exception is--->" + e.getMessage());
			}
		
		return data;
	}

		
	public List<LeadDetails> ProspectDynamicSearch2(ProspectSearchReq req) {

		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<LeadDetails> p = cb.createQuery(LeadDetails.class);
			String searchValue = req.getSearchValue();
			Root<ClientDetails> clientDetails = p.from(ClientDetails.class);
			Root<LeadDetails> leadDetails = p.from(LeadDetails.class);
			Predicate p2 = cb.equal(cb.upper(leadDetails .get("clientRefNo")), clientDetails.get("clientRefNo"));
			Predicate p3 = cb.equal(leadDetails .get("insCompanyId"), req.getInsId());
			Predicate p4 = cb.equal(leadDetails .get("branchCode"), req.getBranchCode());
			Predicate p5 = cb.equal(leadDetails .get("status"), "Quote");
			
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientDetails.get("mobileNumber")), "%" + searchValue + "%");
			p.select(leadDetails ).where(p1, p2,p3,p4,p5);
			TypedQuery<LeadDetails> result = em.createQuery(p);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientDetails.get("emailId")), "%" + searchValue + "%");
			p.select(leadDetails).where(p1, p2,p3,p4,p5);
			result = em.createQuery(p);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(clientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			p.select(leadDetails).where(p1, p2,p3,p4,p5);
			result = em.createQuery(p);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(clientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			p.select(leadDetails).where(p1, p2,p3,p4,p5);
			result = em.createQuery(p);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(clientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			p.select(leadDetails).where(p1, p2,p3,p4,p5);
			result = em.createQuery(p);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}


	public List<ClientDetails> ClientDynamicSearchCount(ClientSearchReq req) {
		List<ClientDetails> data = new ArrayList<ClientDetails>();
		try {
			String searchValue = req.getSearchValue();
			int limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf(req.getLimit());
			int offset =StringUtils.isBlank(req.getOffset())?100 : Integer.valueOf(req.getOffset());
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ClientDetails> query = cb.createQuery(ClientDetails.class);

			Root<ClientDetails> ClientDetails = query.from(ClientDetails.class);
			Predicate p2 = cb.equal(ClientDetails.get("branchCode"), req.getBranchCode());
			Predicate p3 = cb.equal(ClientDetails.get("insCompanyId"), req.getInsId() );
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(ClientDetails.get("mobileNumber")), "%" + searchValue + "%");
			query.select(ClientDetails).where(p1, p2, p3);
			TypedQuery<ClientDetails> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(ClientDetails.get("emailId")), "%" + searchValue + "%");
			query.select(ClientDetails).where(p1, p2, p3);
			result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(ClientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2, p3);
			result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(ClientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2, p3);
			result = em.createQuery(query);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(ClientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			query.select(ClientDetails).where(p1, p2, p3);
			result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	
	// Lead Search With Count
	
	public List<LeadDetails> LeadDynamicSearch3(LeadSearchReq req) {
		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			int limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf(req.getLimit());
			int offset =StringUtils.isBlank(req.getOffset())?100 : Integer.valueOf(req.getOffset());
			String searchValue = req.getSearchValue();
			CriteriaQuery<LeadDetails> query = cb.createQuery(LeadDetails.class);
			Root<LeadDetails> leadDetails = query.from(LeadDetails.class);		
			Predicate p2 = cb.equal(leadDetails.get("insCompanyId"), req.getInsCompanyId());
			Predicate p3 = cb.equal(leadDetails.get("branchCode"), req.getBranchCode());

			//Lead No
			Predicate p1 = cb.equal(leadDetails.get("leadId"), searchValue );
			query.select(leadDetails).where(p1,p2,p3);
			TypedQuery<LeadDetails> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);

			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}


	public List<LeadDetails> LeadDynamicSearch4(LeadSearchReq req) {

		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			String searchValue = req.getSearchValue();
			CriteriaQuery<LeadDetails> l = cb.createQuery(LeadDetails.class);

			Root<ClientDetails> clientDetails = l.from(ClientDetails.class);
			Root<LeadDetails> leadDetails = l.from(LeadDetails.class);
			Predicate p2 = cb.equal(cb.upper(leadDetails.get("insCompanyId")), req.getInsCompanyId() );
			Predicate p3 = cb.equal(cb.upper(leadDetails.get("clientRefNo")), clientDetails.get("clientRefNo"));

			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientDetails.get("mobileNumber")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3);
			TypedQuery<LeadDetails> result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientDetails.get("emailId")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(clientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1,  p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(clientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(clientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}

	public List<LeadDetails> ProspectDynamicSearch5(ProspectSearchReq req) {
		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			int limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf(req.getLimit());
			int offset =StringUtils.isBlank(req.getOffset())?100 : Integer.valueOf(req.getOffset());
			String searchValue = req.getSearchValue();
			CriteriaQuery<LeadDetails> query = cb.createQuery(LeadDetails.class);
			Root<LeadDetails> leadDetails = query.from(LeadDetails.class);		
			Predicate p2 = cb.equal(leadDetails.get("insCompanyId"), req.getInsId());
			Predicate p3 = cb.equal(leadDetails.get("branchCode"), req.getBranchCode());
			Predicate p4 = cb.equal(leadDetails.get("status"),"Quote");


			//Lead No
			Predicate p1 = cb.equal(leadDetails.get("leadId"), Integer.valueOf(searchValue) );
			query.select(leadDetails).where(p1,p2,p3,p4);
			TypedQuery<LeadDetails> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);

			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}


	public List<LeadDetails> ProspectDynamicSearch6(ProspectSearchReq req) {
		List<LeadDetails> data = new ArrayList<LeadDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			String searchValue = req.getSearchValue();
			CriteriaQuery<LeadDetails> l = cb.createQuery(LeadDetails.class);

			Root<ClientDetails> clientDetails = l.from(ClientDetails.class);
			Root<LeadDetails> leadDetails = l.from(LeadDetails.class);
			Predicate p2 = cb.equal(cb.upper(leadDetails.get("insCompanyId")), req.getInsId());
			Predicate p3 = cb.equal(cb.upper(leadDetails.get("clientRefNo")), clientDetails.get("clientRefNo"));
			Predicate p4 = cb.equal(leadDetails.get("status"),"Quote");

			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientDetails.get("mobileNumber")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3, p4);
			TypedQuery<LeadDetails> result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientDetails.get("emailId")), "%" + searchValue + "%");
			l.select(leadDetails).where(p1, p2,p3, p4);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(clientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1,  p2,p3,p4);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(clientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3,p4);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(clientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			l.select(leadDetails).where(p1, p2,p3,p4);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}

	public List<PolicyDetails> PolicyDynamicSearch1(PolicySearchReq req) {
		List<PolicyDetails> data = new ArrayList<PolicyDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			int limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf(req.getLimit());
			int offset =StringUtils.isBlank(req.getOffset())?100 : Integer.valueOf(req.getOffset());
			String searchValue = req.getSearchValue();
			CriteriaQuery<PolicyDetails> query = cb.createQuery(PolicyDetails.class);
			Root<PolicyDetails> policyDetails = query.from(PolicyDetails.class);		
			Predicate p2 = cb.equal(policyDetails.get("insCompanyId"), req.getInsId());
			Predicate p3 = cb.equal(policyDetails.get("branchCode"), req.getBranchCode());
			

			//Lead No
			Predicate p1 = cb.equal(policyDetails.get("policyId"), Integer.valueOf(searchValue) );
			query.select(policyDetails).where(p1,p2,p3);
			TypedQuery<PolicyDetails> result = em.createQuery(query);
			result.setFirstResult(limit * offset);
			result.setMaxResults(offset);

			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}

	public List<PolicyDetails> PolicyDynamicSearch2(PolicySearchReq req) {
		List<PolicyDetails> data = new ArrayList<PolicyDetails>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			String searchValue = req.getSearchValue();
			CriteriaQuery<PolicyDetails> l = cb.createQuery(PolicyDetails.class);

			Root<ClientDetails> clientDetails = l.from(ClientDetails.class);
			Root<PolicyDetails> policyDetails = l.from(PolicyDetails.class);
			Predicate p2 = cb.equal(cb.upper(policyDetails.get("insCompanyId")), req.getInsId());
			Predicate p3 = cb.equal(cb.upper(policyDetails.get("clientRefNo")), clientDetails.get("clientRefNo"));
			
			//clientRefNo
			Predicate p1 = cb.like(cb.upper(clientDetails.get("mobileNumber")), "%" + searchValue + "%");
			l.select(policyDetails).where(p1, p2,p3);
			TypedQuery<PolicyDetails> result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb.like(cb.upper(clientDetails.get("emailId")), "%" + searchValue + "%");
			l.select(policyDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

			// clientRefNo
			p1 = cb.like(clientDetails.get("clientRefNo"), "%" + searchValue.toUpperCase() + "%");
			l.select(policyDetails).where(p1,  p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// clientName
			p1 = cb.like(cb.upper(clientDetails.get("clientName")), "%" + searchValue.toUpperCase() + "%");
			l.select(policyDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

			// crno
			p1 = cb.like(cb.upper(clientDetails.get("crno")), "%" + searchValue.toUpperCase() + "%");
			l.select(policyDetails).where(p1, p2,p3);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}


	public List<EnquiryDetails> EnquiryDynamicSearch(String searchValue) {
		List<EnquiryDetails> data = new ArrayList<EnquiryDetails>();
		List<Tuple> quotes = new  ArrayList<Tuple>();
		List<Tuple> policies = new  ArrayList<Tuple>();
	
		try {
			CriteriaBuilder cb1 = em.getCriteriaBuilder();
			
			CriteriaQuery<EnquiryDetails> l = cb1.createQuery(EnquiryDetails.class);
			// Find All
			
			Root<EnquiryDetails> enquiryDetails = l.from(EnquiryDetails.class);
			Predicate p2 = cb1.equal(cb1.upper(enquiryDetails.get("clientRefNo")), enquiryDetails.get("clientRefNo"));
			
			//clientRefNo
			Predicate p1 = cb1.like(cb1.upper(enquiryDetails.get("enquiryId")), "%" + searchValue + "%");
			l.select(enquiryDetails).where(p1, p2);
			TypedQuery<EnquiryDetails> result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			
			p1 = cb1.like(cb1.upper(enquiryDetails.get("clientRefNo")), "%" + searchValue + "%");
			l.select(enquiryDetails).where(p1, p2);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}
			

			p1 = cb1.like(cb1.upper(enquiryDetails.get("leadId")), "%" + searchValue + "%");
			l.select(enquiryDetails).where(p1, p2);
			result = em.createQuery(l);
			if(result.getResultList().size()!=0) {
				data = result.getResultList();
				return data;
			}

		
			// clientName
			p1 = cb1.like(cb1.upper(enquiryDetails.get("sumInsured")), "%" + searchValue.toUpperCase() + "%");
			l.select(enquiryDetails).where(p1, p2);
			result = em.createQuery(l);
			if (result.getResultList().size() != 0) {
				data = result.getResultList();
				return data;
			}

		

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return data;
	}

	// Quote Search With Count
	
		public List<QuoteDetails> QuoteDynamicSearch1(QuoteSearchReq req) {
			List<QuoteDetails> data = new ArrayList<QuoteDetails>();
			try {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				int limit = StringUtils.isBlank(req.getLimit())?0 : Integer.valueOf(req.getLimit());
				int offset =StringUtils.isBlank(req.getOffset())?100 : Integer.valueOf(req.getOffset());
				
				String searchValue = req.getSearchValue();
				
				CriteriaQuery<QuoteDetails> query = cb.createQuery(QuoteDetails.class);
				
				Root<QuoteDetails> quoteDetails = query.from(QuoteDetails.class);
				
				Predicate p2 = cb.equal(quoteDetails.get("insCompanyId"), req.getInsId());
				Predicate p3 = cb.equal(quoteDetails.get("branchCode"), req.getBranchCode());
	
				//Quote No
				Predicate p1 = null ;
				if (searchValue.matches("[0-9]+") ) {
					p1 = cb.equal(quoteDetails.get("quoeNo"), Integer.valueOf(searchValue) );	
				}
				
				query.select(quoteDetails).where(p1,p2,p3);
				TypedQuery<QuoteDetails> result = em.createQuery(query);
				result.setFirstResult(limit * offset);
				result.setMaxResults(offset);

				if(result.getResultList().size()!=0) {
					data = result.getResultList();
					return data;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			}
			return data;
		}
		public List<QuoteDetails> QuoteDynamicSearch2(QuoteSearchReq req) {

			List<QuoteDetails> data = new ArrayList<QuoteDetails>();
			try {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				Integer limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
				Integer offset = StringUtils.isBlank(req.getOffset()) ? 100 : Integer.valueOf(req.getOffset());
				
				String searchValue = req.getSearchValue();
				
				CriteriaQuery<QuoteDetails> q = cb.createQuery(QuoteDetails.class);
				Root<ClientDetails> clientDetails = q.from(ClientDetails.class);
				Root<QuoteDetails> quoteDetails = q.from(QuoteDetails.class);
				Predicate p3 = null ;
				
				//Quote No
				Predicate p1 = cb.equal(cb.upper(quoteDetails.get("insCompanyId")), req.getInsId() );
				Predicate p2 = cb.equal(cb.upper(quoteDetails.get("branchCode")),req.getBranchCode());
				
				p3 = cb.equal(quoteDetails.get("quoteNo") ,"%" + searchValue + "%");
				q.select(quoteDetails).where(p1, p2,p3);
				
				TypedQuery<QuoteDetails> result = em.createQuery(q);
				if(result.getResultList().size()!=0) {
					data = result.getResultList();
					result.setFirstResult(limit * offset);
					result.setMaxResults(offset);
					return data;
				}
				
				//clientRefNo
				 p1 = cb.equal(cb.upper(quoteDetails.get("insCompanyId")), req.getInsId() );
				p2 = cb.equal(cb.upper(quoteDetails.get("branchCode")),req.getBranchCode());
				p3 = cb.equal(cb.upper(quoteDetails.get("clientRefNo")),"%" + searchValue + "%");
				q.select(quoteDetails).where(p1, p2,p3);
				
				result = em.createQuery(q);
				if(result.getResultList().size()!=0) {
					data = result.getResultList();
					result.setFirstResult(limit * offset);
					result.setMaxResults(offset);
					return data;
				}
		
				// SumInsured
				p3 = cb.greaterThanOrEqualTo(cb.upper(quoteDetails.get("sumInsured")), "%" + searchValue + "%");
				Predicate p4 = cb.lessThanOrEqualTo(cb.upper(quoteDetails.get("sumInsured")), "%" + searchValue + "%");
				q.select(quoteDetails).where(p1, p2,p3,p4);
				result = em.createQuery(q);
				if (result.getResultList().size() != 0) {
					data = result.getResultList();
					result.setFirstResult(limit * offset);
					result.setMaxResults(offset);
					return data;
				}
				
				//Enquiry Id
				p3 = cb.like(cb.upper(quoteDetails.get("enquiryId")), "%" + searchValue + "%");
				q.select(quoteDetails).where(p1, p2,p3);
				result = em.createQuery(q);
				if(result.getResultList().size()!=0) {
					data = result.getResultList();
					result.setFirstResult(limit * offset);
					result.setMaxResults(offset);
					return data;
				}
			
				//Lead Id
				p3 = cb.like(cb.upper(quoteDetails.get("leadId")), "%" + searchValue + "%");
				q.select(quoteDetails).where(p1, p2,p3);
				result = em.createQuery(q);
				if(result.getResultList().size()!=0) {
					data = result.getResultList();
					result.setFirstResult(limit * offset);
					result.setMaxResults(offset);
					return data;
				}

			
				

				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception is ---> " + e.getMessage());
			}
			return data;
		}

		
}

