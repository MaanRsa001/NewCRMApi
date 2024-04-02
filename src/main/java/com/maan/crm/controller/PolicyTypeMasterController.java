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

import com.maan.crm.req.PolicyMasterGetAllReq;
import com.maan.crm.req.PolicyMasterGetReq;
import com.maan.crm.req.PolicyTypeMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.PolicyTypeRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PolicyTypeMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Policy Type Master", description = "API's")
@RequestMapping("/master")
public class PolicyTypeMasterController {

	@Autowired
	private PolicyTypeMasterService policyService;
	@Autowired
	private PrintReqService reqPrinter;

	// Insert

	@PostMapping("/savepolicytype")
	public ResponseEntity<CommonCrmRes> savePolicyType(@RequestBody PolicyTypeMasterSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = policyService.validatePolicyTypeMaster(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = policyService.savePolicyTypeMaster(req);
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

	
	//  Get All Policy Type Master
	
	@PostMapping("/getallpolicytype")
	@ApiOperation("This method is getall policy")
	public ResponseEntity<CommonCrmRes> getallpolicy(@RequestBody PolicyMasterGetAllReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		reqPrinter.reqPrint(req);
		
		List<PolicyTypeRes> res = policyService.getallpolicy(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!= null) {
			return new ResponseEntity<CommonCrmRes> (data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// Get By Policy Id
	
	@PostMapping("/getbypolicyid")
	@ApiOperation("This Method is to get by policy id")
	public ResponseEntity<CommonCrmRes> getpolicyid(@RequestBody PolicyMasterGetReq req)
	{
	CommonCrmRes data = new CommonCrmRes();
	PolicyTypeRes res = policyService.getpolicyid(req);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
