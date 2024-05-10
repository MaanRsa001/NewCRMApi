package com.maan.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maan.crm.req.GetbyEnquiryQuoteReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.QuotationDetailsSaveReq;
import com.maan.crm.req.QuoteGetAllReq;
import com.maan.crm.req.QuoteGetReq;
import com.maan.crm.req.QuoteListReq;
import com.maan.crm.req.QuoteSearchReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.QuotePageRes;
import com.maan.crm.res.QuoteSearchCountRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

@Service
public interface QuoteService {

	QuotePageRes getallQuote(QuoteGetAllReq req);

	QuoteGetRes getQuote(QuoteGetReq req);

	List<QuoteGetRes> getbyenquiryid(GetbyEnquiryQuoteReq req);

	Long getQuoteCount(QuoteGetAllReq req);

	//Search Count
	QuoteSearchCountRes searchQuoteCount(QuoteSearchReq req);

	ClientLeadsGridRes getQuotePolicyDetails(QuoteListReq req);

	List<Error> validateQuotationDetails(QuotationDetailsSaveReq req);

	SuccessRes saveQuotationDetails(QuotationDetailsSaveReq req);


}
