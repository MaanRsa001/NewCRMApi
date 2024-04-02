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

import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.OccupationMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Occupation Master ", description = "API's")

@RequestMapping("/master")
public class OccupationMasterController {

	@Autowired
	private OccupationMasterService occupationService;
	@Autowired
	private PrintReqService reqPrinter;

	// Occupation Drop Down Type
	@GetMapping("/dropdown/occupation")
	@ApiOperation(value = "This method is get Occupation Drop Down")

	public ResponseEntity<CommonCrmRes> getOccupationDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = occupationService.getOccupationDropdown();
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

	@PostMapping("/getalloccupation")
	@ApiOperation(value = "This method is getall Occupation ")

	public ResponseEntity<CommonCrmRes> getallOccupationDropdown(@RequestBody OccupationMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<OccupationDropDownRes> res = occupationService.getallOccupationDropdown(req);
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

	// Occupation Get By Occupation Id

	@PostMapping("/getoccupationbyid")
	@ApiOperation(value = "This method is get by Occupation Id ")

	public ResponseEntity<CommonCrmRes> getOccupationId(@RequestBody OccupationMasterGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		OccupationDropDownRes res = occupationService.getOccupationId(req);
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

	@PostMapping("/saveoccupation")
	@ApiOperation(value = "This method is Save Occupation ")

	public ResponseEntity<CommonCrmRes> saveOccupation(@RequestBody OccupationSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = occupationService.validateOccupation(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = occupationService.saveCrmCityMaster(req);
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
