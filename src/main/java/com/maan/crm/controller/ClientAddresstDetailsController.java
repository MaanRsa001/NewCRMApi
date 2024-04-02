package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.repository.ClientAddressDetailsRepository;
import com.maan.crm.req.ClientAddressDetailsGetAllReq;
import com.maan.crm.req.ClientAddressDetailsGetReq;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientAddressDetailsGetRes;
import com.maan.crm.res.CommonCrmRes;

import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.ClientAddressDetailsService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "CLIENT : Address Details ", description = "API's")
@RequestMapping("/api")
public class ClientAddresstDetailsController {

	@Autowired
	private ClientAddressDetailsService clientservice;

	@Autowired
	private CRMValidationService validationService;

	@Autowired
	private PrintReqService reqPrinter;

	// Save list of Client Address Details
	@PostMapping("/saveclientaddressdetails")
	@ApiOperation(value = "This method is to Save Client Address Details")
	public ResponseEntity<CommonCrmRes> saveClientAddressDetails(@RequestBody ClientDetailsJsonTemReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		
		  // Validation 
		List<Error> validation = validationService.validateClientAddressDetailsReq(req); 
		  if (validation != null && validation.size() != 0) {
		  
		  data.setCommonResponse(null);
		  data.setIsError(true);
		  data.setErrorMessage(validation); 
		  data.setMessage("Failed");
		  
		  return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
		  
		 } else {
		 
		// Save
		SuccessRes res = clientservice.saveClientAddressDetails(req);
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

	// Get Client Address Details
	// *****************************
	@PostMapping("/getclientadddetails")
	@ApiOperation(value = "This method is to Get Client Details")
	public ResponseEntity<CommonCrmRes> getClientAddressDetails(@RequestBody ClientAddressDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		ClientAddressDetailsGetRes res = clientservice.getClientAddressDetailsById(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Get All Client Address Details
	@PostMapping("/getallclientadddetails")
	@ApiOperation(value = "This method is to GetAll Client Details")
	public ResponseEntity<CommonCrmRes> getAllClientAddressDetails(@RequestBody ClientAddressDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<ClientAddressDetailsGetRes> res = clientservice.getAllClientAddressDetails(req);
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
