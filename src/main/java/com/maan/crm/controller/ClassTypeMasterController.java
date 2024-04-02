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


import com.maan.crm.req.ClassTypeMasterGetAllReq;
import com.maan.crm.req.ClassTypeMasterGetReq;

import com.maan.crm.req.ClassTypeMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ClassTypeMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClassTypeMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "MASTER : Class Type Master ", description = "API's")
@RequestMapping("/master")
public class ClassTypeMasterController {


	@Autowired
	private ClassTypeMasterService classService;
	@Autowired
	private PrintReqService reqPrinter;
	
	
	// ClassTypeMaster Drop Down Type
	@GetMapping("/dropdown/classtype")
	@ApiOperation(value = "This method is Class Type DropDown")
	public ResponseEntity<CommonCrmRes> getClassTypeDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = classService.getClassTypeMasterDropdown();
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
	
	
	@PostMapping("/getallclasstype")
	@ApiOperation(value = "This method is to Get All Class Type Master")
	public ResponseEntity<CommonCrmRes> getallClassTypeMaster(@RequestBody ClassTypeMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<ClassTypeMasterDropDownRes> res = classService.getAllClassTypeMaster(req);
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
	
	
	// ClassTypeMaster Get By ClassTypeMaster Id

	
	@PostMapping("/getclasstypebyid")
	@ApiOperation(value = "This method is to Get By Id")
	public ResponseEntity<CommonCrmRes> getClassTypeById(@RequestBody ClassTypeMasterGetReq req ) {
		CommonCrmRes data = new CommonCrmRes();

		ClassTypeMasterDropDownRes res = classService.getClassTypeMasterById(req);
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
	
	@PostMapping("/saveclasstype")
	@ApiOperation(value = "This method is to Save Class Type Master")
	public ResponseEntity<CommonCrmRes> saveClassType(@RequestBody ClassTypeMasterSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = classService.validateClassTypeMaster(req);
		// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// save

			SuccessRes res = classService.saveClassTypeMaster(req);
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
	
	
	// ClassTypeMaster Drop Down Type
	@GetMapping("/dropdown/policytype/{classtypeid}")
	@ApiOperation(value = "This method is to DropDown")
	public ResponseEntity<CommonCrmRes> getPolicyTypeDropdown(@PathVariable ("classtypeid") String classTypeId ) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = classService.getPolicyTypeDropdown(classTypeId);
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
