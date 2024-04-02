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


import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;

import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsGridRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.LeadService;
import com.maan.crm.service.VehicleDetailsService;
import com.maan.crm.util.error.Error;
import com.maan.crm.service.PrintReqService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "VEHICLE DETAILS : Vehicle Details ", description = "API's")
@RequestMapping("/api")
public class VehicleDetailsController {

	@Autowired
	private VehicleDetailsService vehicleservice;

	@Autowired
	private CRMValidationService validationService;

	@Autowired
	private PrintReqService reqPrinter;
	
	@Autowired
	private LeadService entityService;
	
	
	// Vehicle Details Drop Down Type
	@GetMapping("/dropdown/vehicledetails")
	@ApiOperation(value="This method is to Vehicle Details Drop Down")

	public ResponseEntity<CommonCrmRes> getvehicledetails() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getvehicledetails();
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
	
	// Get All Vehicle Details
	
	
	@PostMapping("/getallvehicledetails")
	@ApiOperation(value="This method is to Get All Vehicle Details")

	public ResponseEntity<CommonCrmRes> getallVehicleDetails(@RequestBody VehicleDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<VehicleDetailsGridRes> res = entityService.getallVehicleDetails(req);
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
	
	
	// Vehicle Get By Chassis No

	
	@PostMapping("/getbyvehchassisno")
	@ApiOperation(value="This method is to Get Vehicle Details By Chassis No")

	public ResponseEntity<CommonCrmRes> getChassisNo(@RequestBody VehicleDetailsGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		VehicleDetailsRes res = entityService.getChassisNo(req);
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
	
	// Insert Vehicle Details
	
	@PostMapping("/savevehicledetails")
	@ApiOperation(value="This method is to Save Vehicle Details")

	public ResponseEntity<CommonCrmRes> saveVehicleDetails(@RequestBody VehicleDetailsSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = vehicleservice.validateVehicle(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res = vehicleservice.saveVehDetail(req);
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
	

	// Save Vehicle Details
	@PostMapping("/savevehdetails")
	@ApiOperation(value = "This method is to Save Vehicle Details")
	public ResponseEntity<CommonCrmRes> saveVehDetail(@RequestBody VehicleDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		/*
		 * // Validation List<Error> validation =
		 * validationService.validateVehicleDetailsReq(req); if (validation != null &&
		 * validation.size() != 0) {
		 * 
		 * data.setCommonResponse(null); data.setIsError(true);
		 * data.setErrorMessage(validation); data.setMessage("Failed");
		 * 
		 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
		 * 
		 * } else {
		 */
		// Save
		SuccessRes res = vehicleservice.saveVehDetail(req);
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

	@PostMapping("/getvehdetailsbyid")
	@ApiOperation(value = "This method is to Get Vehicle Details")
	public ResponseEntity<CommonCrmRes> getVehDetailsById(@RequestBody VehicleDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		VehicleDetailsRes res = vehicleservice.getVehDetailsById(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Get All Vehicle Details
	@PostMapping("/getallvehdetails")
	@ApiOperation(value = "This method is to GetAll Vehicle Details")
	public ResponseEntity<CommonCrmRes> getAllVehicleInfo(@RequestBody VehicleDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<VehicleDetailsGridRes> res = vehicleservice.getAllVehicleInfo(req);
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
