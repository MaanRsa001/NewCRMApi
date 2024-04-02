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

import com.maan.crm.req.BranchMasterReq;
import com.maan.crm.res.BranchMasterRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.BranchMasterService;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Branch Master ", description = "API's")
@RequestMapping("/master")
public class BranchMasterController {

	@Autowired
	private PrintReqService reqPrinter;

	@Autowired
	private BranchMasterService service;
	
	@Autowired
	private CRMValidationService validationService;


	// Get All
	@PostMapping("/getallbranch")
	@ApiOperation(value = "This method is get All Branch Details")
	public ResponseEntity<CommonCrmRes> getallBranch(@RequestBody BranchMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<BranchMasterRes> res = service.getall(req);
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

	// Get 
	@PostMapping("/getbranchid")
	@ApiOperation(value = "This method is get Branch Details")
	public ResponseEntity<CommonCrmRes> getBranch(@RequestBody BranchMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		BranchMasterRes res = service.get(req);
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
	@PostMapping("/insertbranch")
	@ApiOperation(value = "This method is Insert Branch Details")
	public ResponseEntity<CommonCrmRes> insertBranch(@RequestBody BranchMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		List<Error> error = validationService.branchMaster(req);
		if (error.size() != 0) {

			data.setCommonResponse("Validation");
			data.setIsError(false);
			data.setErrorMessage(error);
			data.setMessage("Failed");

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		} else {

			// Get All
			SuccessRes res = service.insertBranch(req);
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
