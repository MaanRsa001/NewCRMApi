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

import com.maan.crm.req.SourceMasterGetAllReq;
import com.maan.crm.req.SourceMasterGetReq;
import com.maan.crm.req.SourceMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SourceMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.SourceMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(  tags="MASTER : Source Master ", description = "API's")

@RequestMapping("/master")
public class SourceMasterController {


	@Autowired
	private SourceMasterService sourceService;
	@Autowired
	private PrintReqService reqPrinter;
	
	
	// Source Drop Down Type
	@GetMapping("/dropdown/source")
	@ApiOperation(value="This method is get Source Drop Down")

	public ResponseEntity<CommonCrmRes> getSourceDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = sourceService.getSourceDropdown();
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
	
	// Get All
	
	
	@PostMapping("/getallsource")
	@ApiOperation(value="This method is getall source")

	public ResponseEntity<CommonCrmRes> getallSourceDropdown(@RequestBody SourceMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<SourceMasterDropDownRes> res = sourceService.getallSourceDropdown(req);
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
	
	
	// Source Get By Source Id

	
	@PostMapping("/getsourcebyid")
	@ApiOperation(value="This method is get by source id")

	public ResponseEntity<CommonCrmRes> getSourceId(@RequestBody SourceMasterGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		SourceMasterDropDownRes res = sourceService.getSourceId(req);
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
	
	
	// Insert
	
	@PostMapping("/savesource")
	@ApiOperation(value="This method is Save Source")

	public ResponseEntity<CommonCrmRes> saveSource(@RequestBody SourceMasterSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = sourceService.validateSource(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = sourceService.saveSource(req);
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
