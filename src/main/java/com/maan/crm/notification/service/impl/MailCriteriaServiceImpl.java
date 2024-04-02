package com.maan.crm.notification.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maan.crm.bean.BranchMaster;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.InsuranceCompanyMaster;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.ProspectDetails;
import com.maan.crm.notification.mail.dto.MailFramingReq;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.InsuranceCompanyMasterRepository;

@Service
public class MailCriteriaServiceImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ClientDetailsRepository client;
	

	@Autowired
	private InsuranceCompanyMasterRepository insrepo;
	
	private Logger log = LogManager.getLogger(MailCriteriaServiceImpl.class);
	
	
	// Lead Insertede Criteria
	public List<Map<String, Object>> leadCreatedCreateria(MailFramingReq mReq ) {
		List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			String leadId = mReq.getKeys().get("LEAD_ID")==null?"":mReq.getKeys().get("LEAD_ID").toString();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<ClientDetails>    c = query.from(ClientDetails.class);
			Root<LeadDetails> l = query.from(LeadDetails.class);
			Root<InsuranceCompanyMaster> i = query.from(InsuranceCompanyMaster.class);
			Root<BranchMaster> b = query.from(BranchMaster.class);
			
			// Insurance Company Name
			Subquery<String> insName = query.subquery(String.class);
			insName.select(i.get("insName") );
			javax.persistence.criteria.Predicate i1 = cb.equal(c.get("insCompanyId"), i.get("insId"));
			insName.where(i1);
			
			// Branch Name
			Subquery<String> branchName = query.subquery(String.class);
			branchName.select(b.get("branchName") );
			javax.persistence.criteria.Predicate b1 = cb.equal(c.get("branchCode"), b.get("branchCode"));
			insName.where(b1);
			
			// Select
			query.multiselect(c.get("entryDate").alias("CLIENT_ENTRY_DATE") ,
							  c.get("clientName").alias("CLIENT_NAME")      , 
							  l.get("leadId").alias("LEAD_ID")              ,
							  l.get("entryDate").alias("LEAD_ENTRY_DATE")  ,
							  l.get("clientRefNo").alias("CLIENT_REF_NO")   ,
							  l.get("createdBy").alias("CREATED_BY")        ,
							  l.get("userType").alias("USER_TYPE")        ,
							  i.get("insName").alias("COMPANY_NAME")        ,
							  b.get("branchName").alias("BRANCH_NAME"));
			
			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(l.get("leadId"), leadId );
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("clientRefNo"), l.get("clientRefNo"));
			
			query.where(n1,n2);
				
			TypedQuery<Tuple> result = em.createQuery(query);
			list =  result.getResultList(); 
			Tuple content = list.get(0);
			Map<String, Object> queryRes = new HashMap<String, Object>();
			queryRes.put("CLIENT_ENTRY_DATE"    , content.get("CLIENT_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("CLIENT_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_NAME"          , content.get("CLIENT_NAME")==null?"":content.get("CLIENT_NAME") );
			queryRes.put("LEAD_ID"              , content.get("LEAD_ID")==null?"":content.get("LEAD_ID") );
			queryRes.put("LEAD_ENTRY_DATE"      , content.get("LEAD_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("LEAD_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_REF_NO"        , content.get("CLIENT_REF_NO")==null?"":content.get("CLIENT_REF_NO") );
			queryRes.put("CREATED_BY"           , content.get("CREATED_BY")==null?"":content.get("CREATED_BY") );
			queryRes.put("USER_TYPE"            , content.get("USER_TYPE")==null?"":content.get("USER_TYPE") );
			queryRes.put("COMPANY_NAME"         , content.get("COMPANY_NAME")==null?"":content.get("COMPANY_NAME") );
			queryRes.put("BRANCH_NAME"          , content.get("BRANCH_NAME")==null?"":content.get("BRANCH_NAME") );
			queryRes.put("STATUS"               , mReq.getStatus() );
			queryList.add(queryRes);
			
		//	queryList.add(content);
			return queryList;
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return null;
	}

	//
	// Client Criteria

	public List<Map<String, Object>> clientCreatedCreateria(MailFramingReq mReq) {
		List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			String clientRefNo = mReq.getKeys().get("CLIENT_REF_NO")==null?"":mReq.getKeys().get("CLIENT_REF_NO").toString();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<ClientDetails>    c = query.from(ClientDetails.class);
	//		Root<LeadDetails> l = query.from(LeadDetails.class);
			Root<InsuranceCompanyMaster> i = query.from(InsuranceCompanyMaster.class);
			Root<BranchMaster> b = query.from(BranchMaster.class);
			
			// Insurance Company Name
			Subquery<String> insName = query.subquery(String.class);
			insName.select(i.get("insName") );
			javax.persistence.criteria.Predicate i1 = cb.equal(c.get("insCompanyId"), i.get("insId"));
			insName.where(i1);
			
			// Branch Name
			Subquery<String> branchName = query.subquery(String.class);
			branchName.select(b.get("branchName") );
			javax.persistence.criteria.Predicate b1 = cb.equal(c.get("branchCode"), b.get("branchCode"));
			insName.where(b1);
			
			// Select
			query.multiselect(c.get("clientRefNo").alias("CLIENT_REF_NO") ,
							  c.get("entryDate").alias("CLIENT_ENTRY_DATE") ,
							  c.get("clientName").alias("CLIENT_NAME")      , 
		/*					  l.get("leadId").alias("LEAD_ID")              ,
							  l.get("entryDate").alias("LEAD_ENTRY_DATE")  ,
							  l.get("clientRefNo").alias("CLIENT_REF_NO")   ,
							  l.get("createdBy").alias("CREATED_BY")        ,
							  l.get("userType").alias("USER_TYPE")        ,
		*/					  i.get("insName").alias("COMPANY_NAME")        ,
							  b.get("branchName").alias("BRANCH_NAME"));
			
			// Where
	//		javax.persistence.criteria.Predicate n1 = cb.equal(l.get("leadId"), leadId );
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("clientRefNo"),clientRefNo ); //l.get("clientRefNo")
			
			query.where(n2);
				
			TypedQuery<Tuple> result = em.createQuery(query);
			list =  result.getResultList();  
			Tuple content = list.get(0);
			Map<String, Object> queryRes = new HashMap<String, Object>();
			queryRes.put("CLIENT_ENTRY_DATE"    , content.get("CLIENT_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("CLIENT_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_NAME"          , content.get("CLIENT_NAME")==null?"":content.get("CLIENT_NAME") );
	//		queryRes.put("LEAD_ID"              , content.get("LEAD_ID")==null?"":content.get("LEAD_ID") );
	//		queryRes.put("LEAD_ENTRY_DATE"      , content.get("LEAD_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("LEAD_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_REF_NO"        , content.get("CLIENT_REF_NO")==null?"":content.get("CLIENT_REF_NO") );
			//queryRes.put("CREATED_BY"           , content.get("CREATED_BY")==null?"":content.get("CREATED_BY") );
	//		queryRes.put("USER_TYPE"            , content.get("USER_TYPE")==null?"":content.get("USER_TYPE") );
			queryRes.put("COMPANY_NAME"         , content.get("COMPANY_NAME")==null?"":content.get("COMPANY_NAME") );
			queryRes.put("BRANCH_NAME"          , content.get("BRANCH_NAME")==null?"":content.get("BRANCH_NAME") );
			queryRes.put("STATUS"               , mReq.getStatus() );
			queryList.add(queryRes);
			
	//		queryList.add(content);
			return queryList;
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return null;
	}

	
	// Prospect Criteria 
	
	public List<Map<String, Object>> prospectCreatedCreateria(MailFramingReq mReq) {
		List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			String prospectid = mReq.getKeys().get("PROSPECTID")==null?"":mReq.getKeys().get("PROSPECTID").toString();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<ClientDetails>    c = query.from(ClientDetails.class);
			Root<ProspectDetails> l = query.from(ProspectDetails.class);
			Root<InsuranceCompanyMaster> i = query.from(InsuranceCompanyMaster.class);
			Root<BranchMaster> b = query.from(BranchMaster.class);
			
			// Insurance Company Name
			Subquery<String> insName = query.subquery(String.class);
			insName.select(i.get("insName") );
			javax.persistence.criteria.Predicate i1 = cb.equal(c.get("insCompanyId"), i.get("insId"));
			insName.where(i1);
			
			// Branch Name
			Subquery<String> branchName = query.subquery(String.class);
			branchName.select(b.get("branchName") );
			javax.persistence.criteria.Predicate b1 = cb.equal(c.get("branchCode"), b.get("branchCode"));
			insName.where(b1);
			
			// Select
			query.multiselect(c.get("entryDate").alias("CLIENT_ENTRY_DATE") ,
							  c.get("clientName").alias("CLIENT_NAME")      ,
							  l.get("prospectid").alias("PROSPECTID")              ,
							  l.get("generationDate").alias("Prospect_GenerationDate")  ,
							  l.get("clientRefNo").alias("CLIENT_REF_NO")   ,
							//  l.get("createdBy").alias("CREATED_BY")        ,
							//  l.get("userType").alias("USER_TYPE")        ,
							  i.get("insName").alias("COMPANY_NAME")        ,
							  b.get("branchName").alias("BRANCH_NAME"));
			
			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(l.get("prospectid"), prospectid );
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("clientRefNo"), l.get("clientRefNo"));
			
			query.where(n1,n2);
				
			TypedQuery<Tuple> result = em.createQuery(query);
			list =  result.getResultList(); 
			Tuple content = list.get(0);
			Map<String, Object> queryRes = new HashMap<String, Object>();
			queryRes.put("CLIENT_ENTRY_DATE"    , content.get("CLIENT_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("CLIENT_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_NAME"          , content.get("CLIENT_NAME")==null?"":content.get("CLIENT_NAME") );
			queryRes.put("PROSPECTID"              , content.get("PROSPECTID")==null?"":content.get("PROSPECTID") );
			queryRes.put("Prospect_GenerationDate"      , content.get("Prospect_GenerationDate")==null?"":sdf.format(dbf.parse(content.get("Prospect_GenerationDate").toString())));
			queryRes.put("CLIENT_REF_NO"        , content.get("CLIENT_REF_NO")==null?"":content.get("CLIENT_REF_NO") );
		//	queryRes.put("CREATED_BY"           , content.get("CREATED_BY")==null?"":content.get("CREATED_BY") );
		//	queryRes.put("USER_TYPE"            , content.get("USER_TYPE")==null?"":content.get("USER_TYPE") );
			queryRes.put("COMPANY_NAME"         , content.get("COMPANY_NAME")==null?"":content.get("COMPANY_NAME") );
			queryRes.put("BRANCH_NAME"          , content.get("BRANCH_NAME")==null?"":content.get("BRANCH_NAME") );
			queryRes.put("STATUS"               , mReq.getStatus() );
			queryList.add(queryRes);
			
		//	queryList.add(content);
			return queryList;
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return null;
	}

	
	// Policy Criteria
	public List<Map<String, Object>> policyCreatedCreateria(MailFramingReq mReq) {
		List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat dbf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			String policyid = mReq.getKeys().get("POLICYID")==null?"":mReq.getKeys().get("POLICYID").toString();
			
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<ClientDetails>    c = query.from(ClientDetails.class);
			Root<PolicyDetails> l = query.from(PolicyDetails.class);
			Root<InsuranceCompanyMaster> i = query.from(InsuranceCompanyMaster.class);
			Root<BranchMaster> b = query.from(BranchMaster.class);
			
			// Insurance Company Name
			Subquery<String> insName = query.subquery(String.class);
			insName.select(i.get("insName") );
			javax.persistence.criteria.Predicate i1 = cb.equal(c.get("insCompanyId"), i.get("insId"));
			insName.where(i1);
			
			// Branch Name
			Subquery<String> branchName = query.subquery(String.class);
			branchName.select(b.get("branchName") );
			javax.persistence.criteria.Predicate b1 = cb.equal(c.get("branchCode"), b.get("branchCode"));
			insName.where(b1);
			
			// Select
			query.multiselect(c.get("entryDate").alias("CLIENT_ENTRY_DATE") ,
							  c.get("clientName").alias("CLIENT_NAME")      ,
							  l.get("policyNumber").alias("POLICY_NUMBER")   ,
							  l.get("entryDate").alias("POLICY_ENTRY_DATE")  ,
							  l.get("clientRefNo").alias("CLIENT_REF_NO")   ,
							//  l.get("createdBy").alias("CREATED_BY")        ,
							//  l.get("userType").alias("USER_TYPE")        ,
							  i.get("insName").alias("COMPANY_NAME")        ,
							  b.get("branchName").alias("BRANCH_NAME"));
			
			// Where
			javax.persistence.criteria.Predicate n1 = cb.equal(l.get("policyid"), policyid );
			javax.persistence.criteria.Predicate n2 = cb.equal(c.get("clientRefNo"), l.get("clientRefNo"));
			
			query.where(n1,n2);
				
			TypedQuery<Tuple> result = em.createQuery(query);
			list =  result.getResultList(); 
			Tuple content = list.get(0);
			Map<String, Object> queryRes = new HashMap<String, Object>();
			queryRes.put("CLIENT_ENTRY_DATE"    , content.get("CLIENT_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("CLIENT_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_NAME"          , content.get("CLIENT_NAME")==null?"":content.get("CLIENT_NAME") );
			queryRes.put("POLICY_NUMBER"        , content.get("POLICY_NUMBER")==null?"":content.get("POLICY_NUMBER") );
			queryRes.put("POLICY_ENTRY_DATE"      , content.get("POLICY_ENTRY_DATE")==null?"":sdf.format(dbf.parse(content.get("POLICY_ENTRY_DATE").toString())));
			queryRes.put("CLIENT_REF_NO"        , content.get("CLIENT_REF_NO")==null?"":content.get("CLIENT_REF_NO") );
		//	queryRes.put("CREATED_BY"           , content.get("CREATED_BY")==null?"":content.get("CREATED_BY") );
		//	queryRes.put("USER_TYPE"            , content.get("USER_TYPE")==null?"":content.get("USER_TYPE") );
			queryRes.put("COMPANY_NAME"         , content.get("COMPANY_NAME")==null?"":content.get("COMPANY_NAME") );
			queryRes.put("BRANCH_NAME"          , content.get("BRANCH_NAME")==null?"":content.get("BRANCH_NAME") );
			queryRes.put("STATUS"               , mReq.getStatus() );
			queryList.add(queryRes);
			
		//	queryList.add(content);
			return queryList;
			
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return null;
	}

	public List<Map<String, Object>> campaignInviteMail(MailFramingReq mReq) {
		List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();

		try {
			List<ClientDetails> resList = new ArrayList<ClientDetails>();
				
			InsuranceCompanyMaster im = insrepo.findByInsId(mReq.getInsId());
			
			Map<String, Object> queryRes = new HashMap<String, Object>();
			
			queryRes.put("COMPANY_NAME", im.getInsName()==null?"":im.getInsName());
			queryRes.put("REGARDS", im.getRegards()==null?"":im.getRegards());
			queryList.add(queryRes);
			
				return queryList;
				

		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception is ---> " + e.getMessage());
		}
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
