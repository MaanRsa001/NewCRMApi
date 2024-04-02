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

import com.maan.crm.req.VehicleTypeMasterGetAllReq;
import com.maan.crm.req.VehicleTypeMasterGetReq;
import com.maan.crm.req.VehicleTypeMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleTypeMasterRes;
import com.maan.crm.service.PrintReqService;

import com.maan.crm.service.VehicleTypeMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Vehicle Type Master ", description = "API's")
@RequestMapping("/master")
public class VehicleTypeMasterController {

	@Autowired
	private VehicleTypeMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// Vehicle Master DropDown

	@GetMapping("/dropdown/vehicletypemaster")
	@ApiOperation(value = "This method is Vehicle Type Master Drop Down")
	public ResponseEntity<CommonCrmRes> getVehicalTypeMasterDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getVehicalTypeMasterDropdown();
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

	// GellAll Vehicle Type Master
	@PostMapping("/getallvehicletypemaster")
	@ApiOperation(value = "This method is Get All Vehicle Type Master")
	public ResponseEntity<CommonCrmRes> getAllVehicalTypeMaster(@RequestBody VehicleTypeMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<VehicleTypeMasterRes> res = entityService.getAllVehicleTypeMaster(req);
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

	// Get Vehicle Type Master
	@PostMapping("/getvehicletypemasterbyid")
	@ApiOperation(value = "This method is get Vehicle Type Master ")
	public ResponseEntity<CommonCrmRes> getVehicleTypeMasterById(@RequestBody VehicleTypeMasterGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		VehicleTypeMasterRes res = entityService.getVehicleTypeMasterById(req);
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

	// Insert Vehicle Type Master
	@PostMapping("/savevehicletypemaster")

	public ResponseEntity<CommonCrmRes> saveVehicalTypeMaster(@RequestBody VehicleTypeMasterSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		/*
		 * List<Error> validation = entityService.validateVehicalTypeMaster(req); //
		 * validation if (validation != null & validation.size() != 0) {
		 * data.setCommonResponse(null); data.setIsError(true);
		 * data.setErrorMessage(validation); data.setMessage("Failed"); return new
		 * ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
		 * 
		 * } else {
		 */
		// save

		SuccessRes res = entityService.saveVehicleTypeMaster(req);
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