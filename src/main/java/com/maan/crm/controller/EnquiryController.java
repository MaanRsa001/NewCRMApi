package com.maan.crm.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.EnquiryGetReq;
import com.maan.crm.req.EnquiryGridReq;
import com.maan.crm.req.EnquiryListReq;
import com.maan.crm.req.EnquiryReq;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.req.SearchEnquiryReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.EnquiryGetRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.EnquiryPageRes;
import com.maan.crm.res.EnquiryRes;
import com.maan.crm.res.QuoteGetRes;
import com.maan.crm.res.QuoteGridRes;
import com.maan.crm.res.SearchEnquiryRes;
import com.maan.crm.service.EnquiryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/api")
@Api(tags= "ENQUIRY: Enquiry Details")
public class EnquiryController {

	@Autowired
	private EnquiryService service;
	
	
	
	

	@PostMapping("/enquiry/getall")
	@ApiOperation(value="This method is to enquiry details")
	public ResponseEntity<CommonCrmRes> getEnquiryGrid(@RequestBody EnquiryGridReq req){
		CommonCrmRes data = new CommonCrmRes();
		//EnquiryPageRes res = new EnquiryPageRes(); 
		//Long enquiryCount = service.getEquiryCount(req);	
		EnquiryPageRes res = service.getEnquiryGrid(req);
		
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res.getEnquiryList() !=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/enquiry/getbyid")
	@ApiOperation(value="This method is to enquiry details")
	public ResponseEntity<CommonCrmRes> getEnquiryDetails(@RequestBody EnquiryGetReq req){
		CommonCrmRes data = new CommonCrmRes();
		EnquiryGetRes res = service.getEnquiryDetails(req);

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
	
	
	@PostMapping("/lead/enquiries")
	@ApiOperation(value="This method is to lead enquiry details")
	public ResponseEntity<CommonCrmRes> getLeadEnquiryDetails(@RequestBody LeadEnquiryGetReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<EnquiryGridRes> res = service.getLeadEnquiryDetails(req);

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
	
	@PostMapping("/searchapi")
	@ApiOperation(value="This method is to Search Enquiry Details")
	public ResponseEntity<CommonCrmRes> searchenquiry(@RequestBody SearchEnquiryReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<SearchEnquiryRes> res = service.searchenquiry(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
	if(res!=null) {
		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
	}
	else {
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}
	}
	
	@PostMapping("/enquiry/quotes")
	@ApiOperation(value="This method is to lead enquiry details")
	public ResponseEntity<CommonCrmRes> getLeadEnquiryDetails(@RequestBody EnquiryListReq req){
		CommonCrmRes data = new CommonCrmRes();
		ClientLeadsGridRes res = service.getLeadEnquiryDetails(req);

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
	/*
	 @PostMapping("/enquirycount")
	@ApiOperation(value="This method is to enquiry details")
	public ResponseEntity<CommonCrmRes> getenquiry(@RequestBody EnquiryReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<EnquiryRes> res = service.getenquiry(req);
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
	 */
}
