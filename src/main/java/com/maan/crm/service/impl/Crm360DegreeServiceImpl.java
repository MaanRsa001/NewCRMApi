package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.FollowUpDetails;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.FollowUpDetailsRepository;
import com.maan.crm.repository.LeadRepository;
import com.maan.crm.repository.QuoteDetailsRepository;
import com.maan.crm.req.Client360DegreeReq;
import com.maan.crm.req.FollowUpDetailsReq;
import com.maan.crm.req.Lead360DegreeReq;
import com.maan.crm.req.Prospect360DegreeReq;
import com.maan.crm.res.Client360DegreeRes;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.Lead360DegreeRes;
import com.maan.crm.res.Prospect360DegreeRes;
import com.maan.crm.service.Crm360DegreeService;

@Service
@Transactional
public class Crm360DegreeServiceImpl implements Crm360DegreeService {

	


	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private LeadRepository leadRepo;

	@Autowired
	private QuoteDetailsRepository quoteRepo;
	
	@Autowired
	private FollowUpDetailsRepository followuprepo;

	
	
	Gson json = new Gson();

	private Logger log = LogManager.getLogger(Crm360DegreeServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Client360DegreeRes clientView(Client360DegreeReq req) {
		Client360DegreeRes res = new Client360DegreeRes();
		ModelMapper mapper = new ModelMapper();
		try {
		List<ClientDetails> clientdetails = clientrepo.findByClientRefNoOrderByEntryDateDesc(req.getClientRefNo());
		for(ClientDetails data : clientdetails) {
			res=mapper.map(data, Client360DegreeRes.class);
		}
	} catch (Exception e)
	{
		e.printStackTrace();
		log.info("Exception Error",e.getMessage());
		return null;
	}
	return res;
}
	@Override
	public List<Lead360DegreeRes> leadview(Lead360DegreeReq req) {
		List<Lead360DegreeRes> resList = new ArrayList<Lead360DegreeRes>();
		ModelMapper mapper = new ModelMapper();
		int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
		int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
		Pageable paging = PageRequest.of(limit, offset);

		
		try {
			String status = "Enquiry";
			Page<LeadDetails> leaddetails = leadRepo.findByClientRefNoAndStatusOrderByUpdatedDate(paging,req.getClientRefNo(),status);
		for(LeadDetails data : leaddetails.getContent()) {
			Lead360DegreeRes res = new Lead360DegreeRes();
			res=mapper.map(data, Lead360DegreeRes.class);
			resList.add(res);
		}
	} catch (Exception e)
	{
		e.printStackTrace();
		log.info("Exception Error",e.getMessage());
		return null;
	}
	return resList;
}
	
	@Override
	public List<Prospect360DegreeRes> prospectview(Prospect360DegreeReq req) {
		List<Prospect360DegreeRes> resList = new ArrayList<Prospect360DegreeRes>();
		ModelMapper mapper = new ModelMapper();

		try {
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
		
			Pageable paging = PageRequest.of(limit, offset);


			String status = "Quote";
		Page<LeadDetails> prospectdetails = leadRepo.findByClientRefNoAndStatusOrderByUpdatedDate(paging,req.getClientRefNo(),status);

		List<QuoteDetails> quotedetails = quoteRepo.findByClientRefNo(req.getClientRefNo());
		for(QuoteDetails data : quotedetails) {
			Prospect360DegreeRes res = new Prospect360DegreeRes();
			res=mapper.map(data, Prospect360DegreeRes.class);
			res.setUserType(prospectdetails.getContent().get(0).getUserType());
			res.setCreatedBy(prospectdetails.getContent().get(0).getCreatedBy());
			res.setClassId(prospectdetails.getContent().get(0).getClassId());
			res.setClassDesc(prospectdetails.getContent().get(0).getClassDesc());
			res.setBusinessType(prospectdetails.getContent().get(0).getBusinessType());
			res.setBusinessTypeId(prospectdetails.getContent().get(0).getBusinesstypeId());
			res.setPolicyTypeId(prospectdetails.getContent().get(0).getPolicyTypeId());
			res.setPolicyType(prospectdetails.getContent().get(0).getPolicyType());	
			
			resList.add(res);
		}
	} catch (Exception e)
	{
		e.printStackTrace();
		log.info("Exception Error",e.getMessage());
		return null;
	}
	return resList;
}
	@Override
	public List<FollowUpDetailsRes> getfollowup(FollowUpDetailsReq req) {
		List<FollowUpDetailsRes> resList = new ArrayList<FollowUpDetailsRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			int limit = StringUtils.isBlank(req.getLimit()) ? 0 : Integer.valueOf(req.getLimit());
			int offset = StringUtils.isBlank(req.getOffset()) ? 10 : Integer.valueOf(req.getOffset());
			Pageable paging = PageRequest.of(limit, offset);

			Page<FollowUpDetails> followupdetails = followuprepo.findByClientid(paging,req.getClientRefNo());
			for(FollowUpDetails data : followupdetails.getContent()) {
				FollowUpDetailsRes res = new FollowUpDetailsRes();
				res=mapper.map(data, FollowUpDetailsRes.class);
				resList.add(res);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

}
