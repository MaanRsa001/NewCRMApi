package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.ClientDetailsGetAllReq;
import com.maan.crm.req.ClientDetailsGetReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientDetailsUpdateReq;
import com.maan.crm.req.ClientGetAllReq;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.res.ClientDetailsGetAllRes;
import com.maan.crm.res.ClientDetailsGetRes;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientDetailsJsonTemRes;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientSearchCountRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.ClientViewRes;
import com.maan.crm.res.SuccessRes;


public interface ClientDetailsService {

	//Save
	CrmClientSuccessRes saveClientDetails(ClientDetailsJsonTemReq req);

	//Get All
	List<ClientDetailsGridRes> getAllClientDetails(ClientDetailsGetAllReq req);

	//Get By Id
	ClientDetailsJsonTemRes getClientDetailsById(ClientDetailsGetReq req );
	
	//View
	ClientViewRes viewClientDetails(ClientViewReq req);

	// Search
	List<ClientSearchRes> searchClientDetails(ClientSearchReq req);

	CrmClientSuccessRes updateClientDetails(ClientDetailsJsonTemReq req);

	SuccessRes bulkEditClientInfo(ClientBulkEditReq req);

	
	// Get All With Count
	
	ClientDetailsGetAllRes getallClients(ClientGetAllReq req);

	// Search With Count
	
	ClientSearchCountRes searchAllClients(ClientSearchReq req);

	ClientLeadsGridRes getLeadEnquiryDetails(ClientViewReq req);




}
