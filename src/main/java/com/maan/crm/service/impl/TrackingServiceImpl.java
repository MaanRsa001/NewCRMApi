package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maan.crm.bean.TrackingDetails;
import com.maan.crm.repository.TrackingDetailsRepository;
import com.maan.crm.req.TrackingGetReq;
import com.maan.crm.req.TrackingReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.TrackingGetRes;
import com.maan.crm.res.TrackingGetallRes;
import com.maan.crm.service.TrackingService;

@Service
public class TrackingServiceImpl implements TrackingService {

	@Autowired
	private TrackingDetailsRepository trackRepo;
	
	
	private Logger log = LogManager.getLogger(TrackingServiceImpl.class);

	
	
	public SuccessRes tracking(TrackingReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();
		try {
			// Save Tracking
			SimpleDateFormat tra = new SimpleDateFormat("yyMMddhhmmssSSS");

			TrackingDetails saveTracking = new TrackingDetails();  
			Date today = new Date();
			String trackingId =  tra.format(today);
			saveTracking.setTrackingId(trackingId);
			saveTracking.setInsCompanyId(req.getInsCompanyId());
			saveTracking.setCreatedBy(req.getCreatedBy());	
			saveTracking.setStatus(req.getStatus());
			saveTracking.setEntryDate(today);
			saveTracking.setClientRefNo(req.getClientRefNo());
			saveTracking.setClientName(req.getClientName());
			saveTracking.setBranchCode(req.getBranchCode());
			saveTracking.setRegionCode(req.getRegionCode());
			saveTracking.setLeadId(req.getLeadId());
			saveTracking.setProspectId(req.getProspectId());
			saveTracking.setPolicyId(req.getPolicyId());
			saveTracking.setStatusDescription(req.getStatusDescription());
			trackRepo.save(saveTracking);
			res.setResponse("Successful");
			res.setSucessId(trackingId);;
			

			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;
	}



	@Override
	public List<TrackingGetallRes> getalltracking() {
		List<TrackingGetallRes> resList = new ArrayList<TrackingGetallRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<TrackingDetails> trackingdetails = trackRepo.OrderByEntryDateDesc();
			for(TrackingDetails data : trackingdetails) {
				TrackingGetallRes res = new TrackingGetallRes();
				res = mapper.map(data, TrackingGetallRes.class);
				resList.add(res);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("Log Details"+e.getMessage());
			return null;
		}
		return resList;
	
	}



	@Override
	public List<TrackingGetRes> gettracking(TrackingGetReq req) {
		List<TrackingGetRes> resList = new ArrayList<TrackingGetRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<TrackingDetails> trackingdetails = trackRepo.findByClientRefNo(req.getClientRefNo());
			for(TrackingDetails data : trackingdetails)
			{
				TrackingGetRes res = new TrackingGetRes();
			
			res = mapper.map(data, TrackingGetRes.class);
			resList.add(res);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("Log Details"+ e.getMessage());
			return null;
		}
		
		return resList;
	}
}


