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

import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.StateMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;

@RestController
@Api(  tags="MASTER : State Master ", description = "API's")
@RequestMapping("/master")
public class StateMasterController {

	@Autowired
	private StateMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// State Master DropDown

	@GetMapping("/dropdown/statemaster")
	public ResponseEntity<CommonCrmRes> getStateMasterDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getStateMasterDropdown();
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

	// GellAll StateMaster
	@PostMapping("/getallstatemaster")
	public ResponseEntity<CommonCrmRes> getAllStateMaster(@RequestBody StateMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<StateMasterDropDownRes> res = entityService.getAllStateMaster(req);
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

	// Get State Master
	@PostMapping("/getstatemasterbyid")
	public ResponseEntity<CommonCrmRes> getStateMasterById(@RequestBody StateMasterGetReq req ) {
		CommonCrmRes data = new CommonCrmRes();

		StateMasterDropDownRes res = entityService.getStateMasterById(req);
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
	

	//Insert State Master
	@PostMapping("/savestatemaster")
	
	public ResponseEntity<CommonCrmRes> saveStateMaster(@RequestBody StateMasterSaveReq req) {
		reqPrinter.reqPrint(req);

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = entityService.validateStateMaster(req);
		//// validation
		if (validation != null & validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = entityService.saveStateMaster(req);
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