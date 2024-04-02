
package com.maan.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maan.crm.bean.FollowUpDetails;
import com.maan.crm.bean.FollowUpDetailsId;
import com.maan.crm.repository.FollowUpDetailsRepository;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.FollowUpDetailsSaveReq;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.GetAllFollowUpDetailsRes;
import com.maan.crm.service.FollowUpDetailsService;

@Service
@Transactional
public class FollowUpDetailsServiceImpl implements FollowUpDetailsService {

	private Logger log = LogManager.getLogger(FollowUpDetailsServiceImpl.class);
	
	@Autowired
	private FollowUpDetailsRepository repository;

	@Override
	public CrmClientSuccessRes save(FollowUpDetailsSaveReq req) {
		
		CrmClientSuccessRes res = new CrmClientSuccessRes();
		
		try {
			FollowUpDetailsId id = new FollowUpDetailsId();
			id.setClientid(req.getClientid());
			id.setCompanyid(req.getCompanyid());
			id.setFollowupapplicableid(req.getFollowupapplicableid());
			id.setFollowupid(req.getFollowupid());
			id.setLoginid(req.getLoginid());
			
			Optional<FollowUpDetails> data = repository.findById(id);
			if (data.isPresent()) {
				// Update
				ModelMapper mapper = new ModelMapper();
				FollowUpDetails ent = mapper.map(req, FollowUpDetails.class);
				ent.setEntrydate(req.getEntrydate());
				
				ent.setNextfollowupdate(dateConversion(req.getNextfollowupdate(),req.getNextfollowuptime()));
				ent.setLaststatusupdatedate((dateConversion(req.getLaststatusupdatedate(),req.getLaststatusupdatetime())));
				ent.setCollecteddate(dateConversion(req.getCollecteddate(),req.getCollectedtime()));
				
				repository.save(ent);
				res.setResponse("Updated Successfully");

			} else {

				// Insert
				List<FollowUpDetails> list = new ArrayList<FollowUpDetails>();
				Integer paymentDetailsId = 1000;
				list = repository.findByCompanyidAndClientidAndFollowupapplicableAndFollowupapplicableid(req.getCompanyid(),req.getClientid(),req.getFollowupapplicableid(),req.getFollowupapplicable());
				if (list.size() == 0) {
					list = repository.findAllByOrderByFollowupidDesc();
					if (list.size() != 0) {
						paymentDetailsId = list.get(0).getFollowupid() + 1;
					}
				}else {
					paymentDetailsId = list.get(0).getFollowupid();
				}
				
				ModelMapper mapper = new ModelMapper();
				FollowUpDetails ent = mapper.map(req, FollowUpDetails.class);
				ent.setEntrydate(new Date());
				ent.setFollowupid(paymentDetailsId);
				ent.setFollowupconcludedDesc(req.getFollowupconcludedDesc()==null?"NO":req.getFollowupconcludedDesc());
				ent.setNextfollowupdate(dateConversion(req.getNextfollowupdate(),req.getNextfollowuptime()));
				ent.setLaststatusupdatedate((dateConversion(req.getLaststatusupdatedate(),req.getLaststatusupdatetime())));
				ent.setCollecteddate(dateConversion(req.getCollecteddate(),req.getCollectedtime()));
				
				repository.save(ent);
				

				res.setResponse("Inserted Successfully ");
			}

		}catch (Exception e) {
			log.error(e);
		}
		
		return res;
	}

	private Date dateConversion(Date nextfollowupdate, String nextfollowuptime) {
		
		Date date = nextfollowupdate;
		if(nextfollowuptime!=null) {
			String[] split = nextfollowuptime.split(":");
			date.setHours(Integer.valueOf(split[0]));
			date.setMinutes(Integer.valueOf(split[1]));
		}
		return date;
	}

	@Override
	public GetAllFollowUpDetailsRes getfollowupDetails(FollowUpDetailsSaveReq req) {
		
		GetAllFollowUpDetailsRes reslist = new GetAllFollowUpDetailsRes();
		
		try {
			
			List<FollowUpDetails> entlist = repository.findByCompanyid(req.getCompanyid());
			
			List<FollowUpDetailsRes> con = new ArrayList<FollowUpDetailsRes>();
			List<FollowUpDetailsRes> Notcon = new ArrayList<FollowUpDetailsRes>();
			
			for (FollowUpDetails followUpDetails : entlist) {
				ModelMapper mapper = new ModelMapper();
				FollowUpDetailsRes res = mapper.map(followUpDetails, FollowUpDetailsRes.class);
				res.setNextfollowupdate(followUpDetails.getNextfollowupdate());
				
				if(res.getFollowupconcludedDesc().equals("YES")) {
					con.add(res);
				}else {
					Notcon.add(res);
				}								
			}
			
			reslist.setConcluded(con);
			reslist.setNotConcluded(Notcon);
			
		}catch (Exception e) {
			log.error(e);
		}
		
		return reslist;
	}

	@Override
	public FollowUpDetailsRes getclientdetailsid(FollowUpDetailsSaveReq req) {
		
		FollowUpDetailsRes res= new FollowUpDetailsRes();
		
		try {
			
			FollowUpDetailsId id = new FollowUpDetailsId();
			id.setClientid(req.getClientid());
			id.setCompanyid(req.getCompanyid());
			id.setFollowupapplicableid(req.getFollowupapplicableid());
			id.setFollowupid(req.getFollowupid());
			id.setLoginid(req.getLoginid());
			
			Optional<FollowUpDetails> opt = repository.findById(id );
			
			FollowUpDetails followUpDetails;
			if(opt.isPresent()) {
				followUpDetails = opt.get();
				ModelMapper mapper = new ModelMapper();
				res = mapper.map(followUpDetails, FollowUpDetailsRes.class);
			}
			
		}catch (Exception e) {
			log.error(e);
		}
		
		return res;
	}

	@Override
	public List<FollowUpDetailsRes> getfollowupbypage(FollowUpDetailsSaveReq req) {
		
		List<FollowUpDetailsRes> reslist = new ArrayList<FollowUpDetailsRes>();
		
		try {
			
			List<FollowUpDetails> entlist = repository.findByCompanyidAndClientidAndFollowupapplicableAndFollowupapplicableid(req.getCompanyid(),req.getClientid(),req.getFollowupapplicable(),req.getFollowupapplicableid());
			
			for (FollowUpDetails followUpDetails : entlist) {
				ModelMapper mapper = new ModelMapper();
				FollowUpDetailsRes res = mapper.map(followUpDetails, FollowUpDetailsRes.class);
				reslist.add(res);
			}
			
		}catch (Exception e) {
			log.error(e);
		}
		
		return reslist;
	}

}

