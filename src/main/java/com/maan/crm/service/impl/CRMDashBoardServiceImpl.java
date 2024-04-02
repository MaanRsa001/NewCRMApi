package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
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
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Distinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maan.crm.bean.CityMaster;
import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.FollowUpDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.bean.SessionDetails;
import com.maan.crm.bean.UsertypeMaster;
import com.maan.crm.controller.DashBoardCardsDataCountReq;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.res.ActiveUsersCountRes;
import com.maan.crm.res.DashBoardCardsDataCountRes;
import com.maan.crm.res.DashBoardQuoteStatusCountRes;
import com.maan.crm.res.DashBoardTotalCountRes;
import com.maan.crm.res.NewlyJoinedUsersCountRes;
import com.maan.crm.res.RevenueTotalCountRes;
import com.maan.crm.service.CRMDashBoardService;

@Service
public class CRMDashBoardServiceImpl implements CRMDashBoardService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ClientDetailsRepository clientRepo;
	
	private Logger log=LogManager.getLogger(CRMDashBoardServiceImpl.class);

	@Override
	public DashBoardCardsDataCountRes getCardsDataCount(DashBoardCardsDataCountReq req) {
		DashBoardCardsDataCountRes dashBoardRes = new DashBoardCardsDataCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			
			
			String insuranceId = req.getInsId();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
			
			Long activePolicyCount = 2L ;
			Long activeClientCount = 3L ;
			Long openLeadCount = 36L ;
			Long openProspectCount = 2L ;
			
			dashBoardRes.setActivePolicyCount(activePolicyCount);
			dashBoardRes.setActiveClientCount(activeClientCount);
			dashBoardRes.setOpenLeadCount(openLeadCount);
			dashBoardRes.setOpenProspectCount(openProspectCount);
			

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return dashBoardRes;
	}

	@Override
	public DashBoardTotalCountRes getTotalDataCount(DashBoardCardsDataCountReq req) {
		DashBoardTotalCountRes dashBoardRes = new DashBoardTotalCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			// Filter
			String insuranceId = req.getInsId();
			String branchCode = req.getBranchCode();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
		
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<ClientDetails>  c = query.from(ClientDetails.class);
			
			// Lead Count SubQuery
			Subquery<Long> leadCount = query.subquery(Long.class);
			Root<LeadDetails> l = leadCount.from(LeadDetails.class);
			leadCount.select(cb.count(l));
			javax.persistence.criteria.Predicate l1 = cb.notEqual(l.get("status"),"N");
			javax.persistence.criteria.Predicate l2 = cb.greaterThanOrEqualTo(l.get("entryDate"),startDate  );
			javax.persistence.criteria.Predicate l3 = cb.lessThanOrEqualTo(l.get("entryDate"),endDate );
			javax.persistence.criteria.Predicate l4 = cb.equal(l.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate l5 = cb.equal(l.get("branchCode"),branchCode );
			leadCount.where(l1,l2,l3,l4,l5);
			
			// Enquiry Count SubQuery
			Subquery<Long> enquiryCount = query.subquery(Long.class);
			Root<EnquiryDetails> e = enquiryCount.from(EnquiryDetails.class);
			enquiryCount.select(cb.count(e));
			javax.persistence.criteria.Predicate e1 = cb.notEqual(e.get("enqStatus"),"N");
			javax.persistence.criteria.Predicate e2 = cb.greaterThanOrEqualTo(e.get("entryDate"),startDate  );
			javax.persistence.criteria.Predicate e3 = cb.lessThanOrEqualTo(e.get("entryDate"),endDate );
			javax.persistence.criteria.Predicate e4 = cb.equal(e.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate e5 = cb.equal(e.get("branchCode"),branchCode );
			enquiryCount.where(e1,e2,e3,e4,e5);
			
			// Quote Count SubQuery
			Subquery<Long> quoteCount = query.subquery(Long.class);
			Root<QuoteDetails> q = quoteCount.from(QuoteDetails.class);
			quoteCount.select(cb.count(q));
			javax.persistence.criteria.Predicate q1 = cb.notEqual(q.get("quoteStatus"),"N");
			javax.persistence.criteria.Predicate q2 = cb.greaterThanOrEqualTo(q.get("entryDate"),startDate  );
			javax.persistence.criteria.Predicate q3 = cb.lessThanOrEqualTo(q.get("entryDate"),endDate );
			javax.persistence.criteria.Predicate q4 = cb.equal(q.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate q5 = cb.equal(q.get("branchCode"),branchCode );
			quoteCount.where(q1,q2,q3,q4,q5);
			
			// Policy Count SubQuery
			Subquery<Long> policyCount = query.subquery(Long.class);
			Root<PolicyDetails> p = policyCount.from(PolicyDetails.class);
			policyCount.select(cb.count(p));
			javax.persistence.criteria.Predicate p1 = cb.notEqual(p.get("status"),"N");
			javax.persistence.criteria.Predicate p2 = cb.greaterThanOrEqualTo(p.get("entryDate"),startDate  );
			javax.persistence.criteria.Predicate p3 = cb.lessThanOrEqualTo(p.get("entryDate"),endDate );
			javax.persistence.criteria.Predicate p4 = cb.equal(p.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate p5 = cb.equal(p.get("branchCode"),branchCode );
			policyCount.where(p1,p2,p3,p4,p5); 
						
			// Select
			query.multiselect(cb.count(c).alias("ClientCount") , leadCount.alias("LeadCount") , enquiryCount.alias("EnquiryCount") ,
						       quoteCount.alias("QuoteCount")   ,policyCount.alias("PolicyCount")  );
		
			// Where
		    javax.persistence.criteria.Predicate n1 = cb.notEqual(c.get("status"), "N");
			javax.persistence.criteria.Predicate n2 = cb.greaterThanOrEqualTo(c.get("entryDate"),startDate  );
			javax.persistence.criteria.Predicate n3 = cb.lessThanOrEqualTo(c.get("entryDate"),endDate );
			javax.persistence.criteria.Predicate n4 = cb.equal(c.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate n5 = cb.equal(c.get("branchCode"),branchCode );
			query.where(n1,n2,n3,n4,n5); 
			
			// Get Result
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			
			if (list !=null && list.size()>0 ) {
				Tuple data = list.get(0);
				
				dashBoardRes.setTotalClientCount(data.get("ClientCount")==null?0 :Long.valueOf(data.get("ClientCount").toString()));
				dashBoardRes.setTotalLeadCount(data.get("LeadCount")==null?0 :Long.valueOf(data.get("LeadCount").toString()));
				dashBoardRes.setTotalEnquiryCount(data.get("EnquiryCount")==null?0 :Long.valueOf(data.get("EnquiryCount").toString()));
				dashBoardRes.setTotalQuoteCount(data.get("QuoteCount")==null?0 :Long.valueOf(data.get("QuoteCount").toString()));
				dashBoardRes.setTotalPolicyCount(data.get("PolicyCount")==null?0 :Long.valueOf(data.get("PolicyCount").toString()));
			} 
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return dashBoardRes;
	}

	@Override
	public ActiveUsersCountRes getActiveUsersCount(DashBoardCardsDataCountReq req) {
		ActiveUsersCountRes res = new ActiveUsersCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			// Filter
			String insuranceId = req.getInsId();
			String branchCode = req.getBranchCode();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
		
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<UsertypeMaster>  u = query.from(UsertypeMaster.class);
			
			// Lead Count SubQuery
			Subquery<Long> sessionCount = query.subquery(Long.class);
			Root<SessionDetails> s = sessionCount.from(SessionDetails.class);
			Expression<Long> ds1 =  cb.countDistinct( s.get("loginId") );
			sessionCount.select( ds1 ) ;
			javax.persistence.criteria.Predicate s1 = cb.equal(s.get("usertype"),u.get("usertypeDescription") );
			javax.persistence.criteria.Predicate s2 = cb.equal(s.get("companyId"),insuranceId );
			
			javax.persistence.criteria.Predicate s3 = cb.greaterThanOrEqualTo(s.get("entryDate"), startDate );
			javax.persistence.criteria.Predicate s4 = cb.lessThanOrEqualTo(s.get("entryDate"),endDate );
			sessionCount.where(s1,s2,s3,s4);
			
			// Select
			query.multiselect(u.get("usertypeDescription").alias("UserType") , sessionCount.alias("ActiveCount")   );
		
			javax.persistence.criteria.Predicate u1 = cb.equal(u.get("status"), "Y" );
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(u.get("usertypeDescription")));
			
			query.where(u1).orderBy(orderList).groupBy( u.get("usertypeDescription") ) ;
			
			// Get Result
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			
			// Client Revisited Count
			Long clientCount =  clientRepo.countByLastVisitedDateGreaterThanEqualAndLastVisitedDateLessThanEqualAndInsCompanyId(startDate,endDate ,insuranceId );
			
			if (list !=null && list.size()>0 ) {
				res.setAdminCount( list.get(0)!=null &&  list.get(0).get("ActiveCount") !=null ? Long.valueOf(list.get(0).get("ActiveCount").toString()) : 0L );
				res.setCallCentreEmpCount( list.get(1)!=null &&  list.get(1).get("ActiveCount") !=null ? Long.valueOf(list.get(1).get("ActiveCount").toString()) : 0L );
				res.setSalesManagerCount( list.get(2)!=null &&  list.get(2).get("ActiveCount") !=null ? Long.valueOf(list.get(2).get("ActiveCount").toString()) : 0L );
				res.setUnderWritterCount( list.get(3)!=null &&  list.get(3).get("ActiveCount") !=null ? Long.valueOf(list.get(3).get("ActiveCount").toString()) : 0L );
				res.setRevisitedClientsCount(clientCount);
				
			}  else {
				res.setAdminCount(0L);
				res.setCallCentreEmpCount(0L);
				res.setSalesManagerCount(0L);
				res.setUnderWritterCount(0L);
				res.setRevisitedClientsCount(clientCount);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public NewlyJoinedUsersCountRes getNewlyJoinedUsersCount(DashBoardCardsDataCountReq req) {
		NewlyJoinedUsersCountRes res = new NewlyJoinedUsersCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			// Filter
			String insuranceId = req.getInsId();
			String branchCode = req.getBranchCode();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
		
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<UsertypeMaster>  u = query.from(UsertypeMaster.class);
			
			// Login Count SubQuery
			Subquery<Long> loginCount = query.subquery(Long.class);
			Root<ClaimLoginMaster> lm = loginCount.from(ClaimLoginMaster.class);
			loginCount.select( cb.count(lm) ) ;
			javax.persistence.criteria.Predicate s1 = cb.equal(lm.get("userType"),u.get("usertypeDescription") );
			javax.persistence.criteria.Predicate s2 = cb.equal(lm.get("companyId"),insuranceId );
			javax.persistence.criteria.Predicate s3 = cb.greaterThanOrEqualTo(lm.get("entryDate"), startDate );
			javax.persistence.criteria.Predicate s4 = cb.lessThanOrEqualTo(lm.get("entryDate"),endDate );
			loginCount.where(s1,s2,s3,s4);
			
			// Select
			query.multiselect(u.get("usertypeDescription").alias("UserType") , loginCount.alias("NewlyJoinedCount")   );
		
			javax.persistence.criteria.Predicate u1 = cb.equal(u.get("status"), "Y" );
			// Order By
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(cb.asc(u.get("usertypeDescription")));
			
			query.where(u1).orderBy(orderList).groupBy( u.get("usertypeDescription") ) ;
			
			// Get Result
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			
			// Client Revisited Count
			Long clientCount =  clientRepo.countByEntryDateGreaterThanEqualAndEntryDateLessThanEqualAndInsCompanyId(startDate,endDate ,insuranceId );
			
			if (list !=null && list.size()>0 ) {
				res.setAdminCount( list.get(0)!=null &&  list.get(0).get("NewlyJoinedCount") !=null ? Long.valueOf(list.get(0).get("NewlyJoinedCount").toString()) : 0L );
				res.setCallCentreEmpCount( list.get(1)!=null &&  list.get(1).get("NewlyJoinedCount") !=null ? Long.valueOf(list.get(1).get("NewlyJoinedCount").toString()) : 0L );
				res.setSalesManagerCount( list.get(2)!=null &&  list.get(2).get("NewlyJoinedCount") !=null ? Long.valueOf(list.get(2).get("NewlyJoinedCount").toString()) : 0L );
				res.setUnderWritterCount( list.get(3)!=null &&  list.get(3).get("NewlyJoinedCount") !=null ? Long.valueOf(list.get(3).get("NewlyJoinedCount").toString()) : 0L );
				res.setClientsCount(clientCount);
				
			}  else {
				res.setAdminCount(0L);
				res.setCallCentreEmpCount(0L);
				res.setSalesManagerCount(0L);
				res.setUnderWritterCount(0L);
				res.setClientsCount(clientCount);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public RevenueTotalCountRes getRevenueTotalCount(DashBoardCardsDataCountReq req) {
		RevenueTotalCountRes res = new RevenueTotalCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			// Filter
			String insuranceId = req.getInsId();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
		
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<QuoteDetails>  q = query.from(QuoteDetails.class);
			
			
			// Enquiry Count SubQuery
			Subquery<Long> revenueCount = query.subquery(Long.class);
			Root<EnquiryDetails> e = revenueCount.from(EnquiryDetails.class);
			revenueCount.select( cb.sum(e.get("suggestPremium")))  ;
			javax.persistence.criteria.Predicate s1 = cb.notEqual(e.get("enqStatus"),"REJ");
			javax.persistence.criteria.Predicate s2 = cb.equal(e.get("insCompanyId"),insuranceId );
			javax.persistence.criteria.Predicate s3 = cb.greaterThanOrEqualTo(e.get("updatedDate"), startDate );
			javax.persistence.criteria.Predicate s4 = cb.lessThanOrEqualTo(e.get("updatedDate"),endDate );
			revenueCount.where(s1,s2,s3,s4);
			
			// Select
			query.multiselect(cb.sum( q.get("suggestPremium") ).alias("RevenueWitoutTax"),cb.sum( q.get("totalPremiumIncludeTax") ).alias("RevenueWithTax") , revenueCount.alias("NextRevenue")   );
		
			javax.persistence.criteria.Predicate n1 = cb.equal(q.get("quoteStatus"), "BC");
			javax.persistence.criteria.Predicate n2 = cb.greaterThanOrEqualTo(q.get("updatedDate"),startDate  );
			javax.persistence.criteria.Predicate n3 = cb.lessThanOrEqualTo(q.get("updatedDate"),endDate );
			javax.persistence.criteria.Predicate n4 = cb.equal(q.get("insCompanyId"),insuranceId );
			query.where(n1,n2,n3,n4);//.groupBy( q.get("quoteStatus") ); 
		
			
			// Get Result
			TypedQuery<Tuple> result = em.createQuery(query);                                                                   
			list = result.getResultList();
		
			
			if (list !=null && list.size()>0 ) {
				Tuple data = list.get(0);
				res.setTotalRevenueWithTax(data.get("RevenueWithTax") !=null ? data.get("RevenueWithTax").toString() : "0" );
				res.setTotalRevenueWithoutTax(data.get("RevenueWitoutTax") !=null ? data.get("RevenueWitoutTax").toString() : "0" );
				res.setNextPossibityRevenue(data.get("NextRevenue") !=null ? data.get("NextRevenue").toString() : "0" );
			
				
			}  else {
				res.setTotalRevenueWithTax("0" );
				res.setTotalRevenueWithoutTax("0" );
				res.setNextPossibityRevenue("0" );
		
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	//*****************************Quote Status Count ***************************\\
	@Override
	public DashBoardQuoteStatusCountRes getTotalQuoteStatusCount(DashBoardCardsDataCountReq req) {
		DashBoardQuoteStatusCountRes res=new DashBoardQuoteStatusCountRes();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			// Filter
			String insuranceId = req.getInsId();
			Date startDate = sdf.parse(req.getStartDate());
			Date endDate =  sdf.parse(req.getEndDate()); 
			Calendar cal = new GregorianCalendar(); 
			cal.setTime(startDate); cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
			startDate = cal.getTime();
			cal.setTime(endDate); cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59);
			endDate = cal.getTime();
		
			// Criteria
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
			List<Tuple> list = new ArrayList<Tuple>();
			
			// Find All
			Root<QuoteDetails>  q = query.from(QuoteDetails.class);
			
			// Login Count SubQuery
			Subquery<Long> followupCount = query.subquery(Long.class);
			Root<FollowUpDetails> f = followupCount.from(FollowUpDetails.class);
			followupCount.select( cb.count(f) ) ;
			javax.persistence.criteria.Predicate s1 = cb.equal(f.get("followUpStatus"),"Y" );
			javax.persistence.criteria.Predicate s2 = cb.equal(f.get("companyid"),insuranceId );
			javax.persistence.criteria.Predicate s3 = cb.greaterThanOrEqualTo(f.get("entrydate"), startDate );
			javax.persistence.criteria.Predicate s4 = cb.lessThanOrEqualTo(f.get("entrydate"),endDate );
			followupCount.where(s1,s2,s3,s4);
			
			// Select
			query.multiselect(q.get("quoteStatus").alias("QuoteStatus")
								,cb.count(q).alias("StatusCount")
								,followupCount.alias("FollowUpCount"));
			//javax.persistence.criteria.Predicate n4 = cb.equal(q.get("status"),"ACP" );
			javax.persistence.criteria.Predicate n1 = cb.greaterThanOrEqualTo(q.get("updatedDate"),startDate  );
			javax.persistence.criteria.Predicate n2 = cb.lessThanOrEqualTo(q.get("updatedDate"),endDate );
			javax.persistence.criteria.Predicate n3 = cb.equal(q.get("insCompanyId"),insuranceId );
			query.where(n1,n2,n3).groupBy( q.get("quoteStatus") ); 
			
			// Get Result
			TypedQuery<Tuple> result = em.createQuery(query);
			list = result.getResultList();
			
			Map<String, List<Tuple>>  groupByStatusCode = list.stream() .collect(Collectors.groupingBy(w ->   w.get("QuoteStatus").toString()  )) ;
			
			Long bc=0l;
			Long bl=0l;
			Long fq=0l; 
			
			// Count
			for(String id : groupByStatusCode.keySet() ) {
							
					for (Tuple data :groupByStatusCode.get(id)  ) {
								
						// BUSSINESS CONVERTED
						if (data.get("QuoteStatus").equals("BC"))
							bc = bc + Long.valueOf(data.get("StatusCount") == null ? "0" : data.get("StatusCount").toString()); 
						// BUSSINESS LOSS
						else if (data.get("QuoteStatus").equals("BL"))
							bl = bl + Long.valueOf(data.get("StatusCount") == null ? "0" : data.get("StatusCount").toString());
						// FRESH QUOTE
						else if (data.get("QuoteStatus").equals("FQ"))
							fq = fq + Long.valueOf(data.get("StatusCount") == null ? "0" : data.get("StatusCount").toString());
						// Progress
						// else
						// totalProgCount = totalProgCount + Long.valueOf(
						// data.get("STATUS_COUNT")==null?"0": data.get("STATUS_COUNT").toString());
									
								
					}
			}
			
			if (list !=null && list.size()>0 ) {
				res.setBusinessConverted(bc.toString());
				res.setBusinessLostCount(bl.toString());
				res.setFreshQuote(fq.toString());
				res.setTotalFollowupsCount( list.get(3)!=null &&  list.get(0).get("FollowUpCount") !=null ? list.get(0).get("FollowUpCount").toString() : "0" );
			
				
			}  else {
				res.setBusinessConverted("0");
				res.setBusinessLostCount("0");
				res.setTotalFollowupsCount("0");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;

	}

}
