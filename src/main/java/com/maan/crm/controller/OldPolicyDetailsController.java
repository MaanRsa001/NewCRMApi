package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.OldPolicyGetAllReq;
import com.maan.crm.req.OldPolicyGetReq;
import com.maan.crm.req.OldPolicySaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OldPolicyRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.LeadService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Old : Policy Details ", description = "API's")
@RequestMapping("/api")
public class OldPolicyDetailsController {

	@Autowired
	private LeadService entityService;
	
	@Autowired
	private PrintReqService reqPrinter;
	
	// Insert Policy
	
	@PostMapping("/saveoldpolicydetails")
	@ApiOperation(value="This method is to Save Old policy Details")

	public ResponseEntity<CommonCrmRes> saveOldPolicyDetails(@RequestBody LeadDetailsJsonTempReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = entityService.validateOldPolicy(req );
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = entityService.saveOldPolicy(req );
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
	
	

	
	// Policy Details Drop Down Type
	@GetMapping("/dropdown/policydetails")
	@ApiOperation(value="This method is to Drop Down Policy Details")

	public ResponseEntity<CommonCrmRes> getpolicydetails() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getpolicydetails();
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
	
	// Get All Policy
	
	
	@PostMapping("/getalloldpolicydetails")
	@ApiOperation(value="This method is to Get All Old Policy Details")

	public ResponseEntity<CommonCrmRes> getallOldPolicyDetails(@RequestBody OldPolicyGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<OldPolicyRes> res = entityService.getallOldPolicyDetails(req);
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
	
	
	// Policy Get By Policy No

	
	@PostMapping("/getoldpolicydetails")
	@ApiOperation(value="This method is to Get Old Policy Details")

	public ResponseEntity<CommonCrmRes> getPolicyNo(@RequestBody OldPolicyGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		OldPolicyRes res = entityService.getPolicyNo(req);
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
