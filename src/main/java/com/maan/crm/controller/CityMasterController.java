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

import com.maan.crm.req.CityMasterGetAllReq;
import com.maan.crm.req.CityMasterGetReq;
import com.maan.crm.req.CityMasterSaveReq;
import com.maan.crm.res.CityMasterRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CityMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(  tags="MASTER : City Master ", description = "API's")
@RequestMapping("/master")
public class CityMasterController {


	@Autowired
	private CityMasterService citymasterService;
	@Autowired
	private PrintReqService reqPrinter;
	
	
	// City Drop Down 
	@GetMapping("/dropdown/city/{stateCode}")
	@ApiOperation(value="This method is get City Drop Down")
	public ResponseEntity<CommonCrmRes> getCityDropdown(@PathVariable("stateCode") Integer stateCode) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = citymasterService.getCityDropdown(stateCode);
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
	@PostMapping("/getallcity")
	@ApiOperation(value="This method is get All Cities Details")
	public ResponseEntity<CommonCrmRes> getallCity(@RequestBody CityMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<CityMasterRes> res = citymasterService.getallCity(req);
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
	
	
	// City Get By City Id

	
	@PostMapping("/getcityid")
	@ApiOperation(value="This method is get City Details")
	public ResponseEntity<CommonCrmRes> getCityId(@RequestBody CityMasterGetReq req ) {
		CommonCrmRes data = new CommonCrmRes();

		CityMasterRes res = citymasterService.getCityId(req);
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
	
	@PostMapping("/savecity")
	public ResponseEntity<CommonCrmRes> saveCity(@RequestBody CityMasterSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = citymasterService.validateCity(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = citymasterService.saveCityMaster(req);
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
