package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.EnquiryGetReq;
import com.maan.crm.req.EnquiryGridReq;
import com.maan.crm.req.EnquiryListReq;
import com.maan.crm.req.EnquiryReq;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.req.SearchEnquiryReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.EnquiryGetRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.EnquiryPageRes;
import com.maan.crm.res.EnquiryRes;

import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.SearchEnquiryRes;

@Service
public interface EnquiryService {

	EnquiryPageRes getEnquiryGrid(EnquiryGridReq req);

	EnquiryGetRes getEnquiryDetails(EnquiryGetReq req);

	List<EnquiryGridRes> getLeadEnquiryDetails(LeadEnquiryGetReq req);
	
	Long getEquiryCount(EnquiryGridReq req);

	List<SearchEnquiryRes> searchenquiry(SearchEnquiryReq req);

	ClientLeadsGridRes getLeadEnquiryDetails(EnquiryListReq req);



}
