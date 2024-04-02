package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.TicketAdminSaveReq;
import com.maan.crm.req.TicketMasterGetAllReq;
import com.maan.crm.req.TicketMasterGetReq;
import com.maan.crm.req.TicketMasterReq;
import com.maan.crm.req.TicketViewReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.TicketAdminRes;
import com.maan.crm.res.TicketMasterRes;

public interface TicketMasterService {

	SuccessRes ticketmaster(TicketMasterReq req);

	TicketMasterRes getticketmaster(TicketMasterGetReq req);

	List<TicketMasterRes> getallticketmaster(TicketMasterGetAllReq req);

	// Admin Side Insert
	
	SuccessRes ticketadmin(TicketAdminSaveReq req);

	// Admin View
	
	List<TicketAdminRes> ticketview(TicketViewReq req);



}