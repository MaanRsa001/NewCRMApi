package com.maan.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.TicketMasterAdmin;
import com.maan.crm.bean.TicketMasterDetails;
import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.repository.TicketMasterAdminRepository;
import com.maan.crm.repository.TicketMasterRepository;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.TicketAdminSaveReq;
import com.maan.crm.req.TicketMasterGetAllReq;
import com.maan.crm.req.TicketMasterGetReq;
import com.maan.crm.req.TicketMasterReq;
import com.maan.crm.req.TicketViewReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.TicketAdminRes;
import com.maan.crm.res.TicketAdminSaveRes;
import com.maan.crm.res.TicketMasterRes;
import com.maan.crm.service.TicketMasterService;

@Service
@Transactional
public class TicketMasterServiceImpl implements TicketMasterService {

	@Autowired
	private ClientDetailsRepository clientrepo;

	@Autowired
	private TicketMasterRepository ticketrepo;
	
	@Autowired 
	private TicketMasterAdminRepository ticketadminrepo;

	private Logger log = LogManager.getLogger(ClientDetailsServiceImpl.class);

	Gson json = new Gson();

	// Ticket Master Details Insert
	@Override
	public SuccessRes ticketmaster(TicketMasterReq req) {
		SuccessRes res = new SuccessRes();
		ModelMapper mapper = new ModelMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			String ticketID = "";
			Date entryDate = null;
			Long newId = ticketrepo.count() + 1001;
			Random rnd = new Random();
			int number = rnd.nextInt(100);
			String randomNo = String.format("%02d", number);
			ticketID = "TID-" + newId + "/" + randomNo;
			entryDate = new Date();
			ClientDetails data = clientrepo.findByClientRefNo(req.getClientRefNo());
			TicketMasterDetails ticket = mapper.map(data, TicketMasterDetails.class);
			ticket.setTicketId(ticketID);
			ticket.setEntryDate(new Date());
			ticket.setStatus("OPEN");
			ticket.setIssue(req.getIssue());
			ticket.setIssueId(req.getIssueId());
			ticket.setRemarks(req.getRemarks());
			ticketrepo.save(ticket);
			res.setSucessId(ticketID);
			res.setResponse("Registered Successful");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
		return res;

	}

	@Override
	public TicketMasterRes getticketmaster(TicketMasterGetReq req) {
		TicketMasterRes res = new TicketMasterRes();
		ModelMapper mapper = new ModelMapper();
		try {
			TicketMasterDetails data = ticketrepo.findByTicketId(req.getTicketId());
			res = mapper.map(data, TicketMasterRes.class);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<TicketMasterRes> getallticketmaster(TicketMasterGetAllReq req) {
		List<TicketMasterRes> resList = new ArrayList<TicketMasterRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<TicketMasterDetails> ticketmasterdetails = ticketrepo
					.findByBranchCodeAndRegionCodeAndInsCompanyIdOrderByEntryDateDesc(req.getBranchCode(),
							req.getRegionCode(), req.getInsCompanyId());
			for (TicketMasterDetails data : ticketmasterdetails) {
				TicketMasterRes res = new TicketMasterRes();
				res = mapper.map(data, TicketMasterRes.class);
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
		return resList;
	}

	
	// From Admin Side Insert Process
	@Override
	public SuccessRes ticketadmin(TicketAdminSaveReq req) {
		SuccessRes res = new SuccessRes();
		
		ModelMapper mapper = new ModelMapper();
		try {
			TicketMasterDetails data = ticketrepo.findByTicketId(req.getTicketId());
			TicketMasterAdmin ticket = new TicketMasterAdmin();
			ticket=mapper.map(data, TicketMasterAdmin.class);
			ticket.setIssuerId(req.getIssuerId());
			ticket.setIssuerName(req.getIssuerName());
			ticket.setStatusId(req.getStatusId());
			ticket.setStatus(req.getStatus());
			Long count = ticketadminrepo.count();
			Integer sno = Integer.valueOf(count.toString())+1;
			ticket.setSno(sno);
			ticketadminrepo.save(ticket);
			res.setSucessId(sno.toString());
			res.setResponse("Success");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("Log Details",e.getMessage());
			return null;
		}
		return res;
	}

	@Override
	public List<TicketAdminRes> ticketview(TicketViewReq req) {
		List<TicketAdminRes> resList = new ArrayList<TicketAdminRes>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<TicketMasterAdmin> ticket = ticketadminrepo.findByStatusIdAndStatus(req.getStatusId(),req.getStatus());
			for(TicketMasterAdmin data : ticket) {
				TicketAdminRes res = new TicketAdminRes();
				res = mapper.map(data,TicketAdminRes.class);
				resList.add(res);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Log Details",e.getMessage());
			return null;
		}
		return resList;
	}


	
	
}
