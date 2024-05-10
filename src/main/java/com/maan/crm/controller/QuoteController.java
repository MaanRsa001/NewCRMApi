package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.EnquiryGetReq;
import com.maan.crm.req.EnquiryGridReq;
import com.maan.crm.req.EnquiryListReq;
import com.maan.crm.req.GetbyEnquiryQuoteReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.QuotationDetailsSaveReq;
import com.maan.crm.req.QuoteGetAllReq;
import com.maan.crm.req.QuoteGetReq;
import com.maan.crm.req.QuoteListReq;
import com.maan.crm.req.QuoteSearchReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.QuotePageRes;
import com.maan.crm.res.QuoteSearchCountRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.QuoteService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/quote")
@Api(tags = "QUOTE: Quote Details")
public class QuoteController {

	@Autowired
	private QuoteService service;
	
	@Autowired
	private PrintReqService reqPrinter;

	@PostMapping("/getall")
	@ApiOperation(value = "This method is to quote details")
	public ResponseEntity<CommonCrmRes> getallQuote(@RequestBody QuoteGetAllReq req) {
		CommonCrmRes data = new CommonCrmRes();
		//Long count = service.getQuoteCount(req);

		QuotePageRes res = service.getallQuote(req);

		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getbyid")
	@ApiOperation(value = "This method is to quote details")
	public ResponseEntity<CommonCrmRes> getquote(@RequestBody QuoteGetReq req) {
		CommonCrmRes data = new CommonCrmRes();
		QuoteGetRes res = service.getQuote(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/enquiryquotes")
	@ApiOperation(value = "This method is to get all quotes by enquiry id")
	public ResponseEntity<CommonCrmRes> getbyenquiryid(@RequestBody GetbyEnquiryQuoteReq req) {
		CommonCrmRes data = new CommonCrmRes();
		List<QuoteGetRes> res = service.getbyenquiryid(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	// Search Quote Details

	@PostMapping("/search/quotedetails")
	@ApiOperation(value = "This method is to Search Quote Count ")
	public ResponseEntity<CommonCrmRes> searchQuoteCount(@RequestBody QuoteSearchReq req) {
		CommonCrmRes data = new CommonCrmRes();

		QuoteSearchCountRes res = service.searchQuoteCount(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/policies")
	@ApiOperation(value="This method is to lead enquiry details")
	public ResponseEntity<CommonCrmRes> getLeadEnquiryDetails(@RequestBody QuoteListReq req){
		CommonCrmRes data = new CommonCrmRes();
		ClientLeadsGridRes res = service.getQuotePolicyDetails(req);

			data.setCommonResponse(res);
			data.setErrorMessage(Collections.emptyList());
			data.setIsError(false);
			data.setMessage("Success");
			
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
	}
	
	@PostMapping("/savequotation")
	@ApiOperation(value="This method is to save quotation details")
	public ResponseEntity<CommonCrmRes> saveQuotationDetails(@RequestBody QuotationDetailsSaveReq req){
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = service.validateQuotationDetails(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			SuccessRes res = service.saveQuotationDetails(req);
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");
			if (res != null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
