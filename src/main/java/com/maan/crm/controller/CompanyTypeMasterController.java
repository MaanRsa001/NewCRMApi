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

import com.maan.crm.req.CompanyTypeMasterReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.CompanyTypeMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.CompanyTypeMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Company Type Master ", description = "API's")
@RequestMapping("/master")
public class CompanyTypeMasterController {

	@Autowired
	private PrintReqService reqPrinter;

	@Autowired
	private CompanyTypeMasterService service;

	@Autowired
	private CRMValidationService validationService;
	
	// Get All
	@PostMapping("/getallcompanytype")
	@ApiOperation(value = "This method is get All Company Type Details")
	public ResponseEntity<CommonCrmRes> getallCompanyType(@RequestBody CompanyTypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<CompanyTypeMasterRes> res = service.getall(req);
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
	@PostMapping("/getcompanytypeid")
	@ApiOperation(value = "This method is get CompanyType Details")
	public ResponseEntity<CommonCrmRes> getCompanyType(@RequestBody CompanyTypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		CompanyTypeMasterRes res = service.get(req);
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
	@PostMapping("/insertcompanytype")
	@ApiOperation(value = "This method is Insert CompanyType Details")
	public ResponseEntity<CommonCrmRes> insertBranch(@RequestBody CompanyTypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		List<Error> error = validationService.companytype(req);
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
			}
		}
	}

}
