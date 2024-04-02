package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.repository.ClientCorContactRepository;
import com.maan.crm.req.ClientCorContactGetAllReq;
import com.maan.crm.req.ClientCorContactReq;
import com.maan.crm.req.ClientCrConGetReq;
import com.maan.crm.req.ClientCrConListSaveReq;
import com.maan.crm.req.ClientCrContactGetAllReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientCrContactGetRes;

import com.maan.crm.res.ClientCorContactListGeAlltRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.ClientCorContactService;

import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.impl.ClientCorContactServiceImpl;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
//@Api(  tags="CLIENT DETAILS: Client Corporate Contact ", description = "API's")
@RequestMapping("/api")
@Api(tags = "CLIENT : Client Corporate Contact Details ", description = "API's")
public class ClientCorContactController {

	@Autowired
	private ClientCorContactService entityService;

	@Autowired
	private CRMValidationService validationService;

	@Autowired
	private PrintReqService reqPrinter;

	// Insert/Save Client corporate Contact Details
	// *********************************************
	@PostMapping("/saveclientcor")
	@ApiOperation(value = "This method is to Save Client Corporate Contact")
	public ResponseEntity<CommonCrmRes> saveClientCorContact(@RequestBody ClientDetailsJsonTemReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		/*
		 * // Validation List<Error> validation =
		 * validationService.validateClientCorContact(req); if (validation != null &&
		 * validation.size() != 0) {
		 * 
		 * data.setCommonResponse(null); data.setIsError(true);
		 * data.setErrorMessage(validation); data.setMessage("Failed");
		 * 
		 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
		 * 
		 * } else {
		 */
		// save

		SuccessRes res = entityService.saveClientCorContact(req);
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

	// Get Client Corporate Contact
	// *****************************
	@PostMapping("/getclientcorcon")
	@ApiOperation(value = "This method is to Get Client Corporate Contact")
	public ResponseEntity<CommonCrmRes> getClientCorContactById(@RequestBody ClientCrConGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		ClientCrContactGetRes res = entityService.getClientCorContactById(req);
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

	// Get All Client Corporate Contact
	// *********************************
	@PostMapping("/getallclientcorcon")
	@ApiOperation(value = "This method is to GetAll Client Corporate ")
	public ResponseEntity<CommonCrmRes> getAllClientCorContact(@RequestBody ClientCrContactGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<ClientCrContactGetRes> res = entityService.getAllClientCorContact(req);
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
