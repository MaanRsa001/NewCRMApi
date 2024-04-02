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

import com.maan.crm.req.SmsConfigMasterReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SmsConfigMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.SmsConfigMasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Sms Config Master ", description = "API's")
@RequestMapping("/master")
public class SmsConfigMasterController {

	@Autowired
	private PrintReqService reqPrinter;

	@Autowired
	private SmsConfigMasterService service;

	@Autowired
	private CRMValidationService validationService;
	
	// Get 
	@PostMapping("/getsmsConfigMaster")
	@ApiOperation(value = "This method is get Sms Config Details")
	public ResponseEntity<CommonCrmRes> getBranch(@RequestBody SmsConfigMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		SmsConfigMasterRes res = service.get(req);
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
	
	// save
	@PostMapping("/insertsmsConfigMaster")
	@ApiOperation(value = "This method is Insert Sms Config Details")
	public ResponseEntity<CommonCrmRes> insertBranch(@RequestBody SmsConfigMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		
		List<com.maan.crm.util.error.Error> error = validationService.smsConfigMaster(req);
		if (error.size() != 0) {

			data.setCommonResponse("Validation");
			data.setIsError(false);
			data.setErrorMessage(error);
			data.setMessage("Failed");

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		} else {

		// Get All
		SuccessRes res = service.insert(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}}
	}

}
