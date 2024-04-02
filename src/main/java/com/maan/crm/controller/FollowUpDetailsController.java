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

import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.FollowUpDetailsSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.GetAllFollowUpDetailsRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.FollowUpDetailsService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "FOLLOWUP : FollowUp Details ", description = "API's")
@RequestMapping("/api")
public class FollowUpDetailsController {


	@Autowired
	private PrintReqService reqPrinter;
	
	@Autowired
	private CRMValidationService validationService;
	
	@Autowired
	private FollowUpDetailsService service;

	// Save list of Follow Up Details
	@PostMapping("/savefollowup")
	@ApiOperation(value = "This method is to Save FollowUp Details")
	public ResponseEntity<CommonCrmRes> savefollowup(@RequestBody FollowUpDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = validationService.validateFollowUpDetailsReq(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			CrmClientSuccessRes res = service.save(req);
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
	
	@PostMapping("/getfollowupdetailsid")
	@ApiOperation(value = "This method is to Get FollowUp Details")
	public ResponseEntity<CommonCrmRes> getclientdetailsid(@RequestBody FollowUpDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Save
		FollowUpDetailsRes res = service.getclientdetailsid(req);
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
	
	@PostMapping("/getallfollowupdetails")
	@ApiOperation(value = "This method is to Get FollowUp Details List")
	public ResponseEntity<CommonCrmRes> getclientdetails(@RequestBody FollowUpDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Save
		GetAllFollowUpDetailsRes res = service.getfollowupDetails(req);
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

	@PostMapping("/getfollowuplist")
	@ApiOperation(value = "This method is to Get FollowUp Details List")
	public ResponseEntity<CommonCrmRes> getclientdetailsByUser(@RequestBody FollowUpDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<FollowUpDetailsRes> res = service.getfollowupbypage(req);
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
