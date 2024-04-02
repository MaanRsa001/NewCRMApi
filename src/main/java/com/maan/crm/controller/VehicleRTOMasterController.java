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

import com.maan.crm.req.VehicleRTOGetAllReq;
import com.maan.crm.req.VehicleRTOMasterGetReq;
import com.maan.crm.req.VehicleRTOMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleRTODropDownRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.VehicleRtoMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(  tags="MASTER : Vehicle RTO Master ", description = "API's")

@RequestMapping("/master")
public class VehicleRTOMasterController {


	@Autowired
	private VehicleRtoMasterService Service;
	@Autowired
	private PrintReqService reqPrinter;
	
	
	// RTO Drop Down
	@GetMapping("/dropdown/vehiclerto")
	@ApiOperation(value="This method is get Vehicle RTO Drop Down")

	public ResponseEntity<CommonCrmRes> getVehicleDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = Service.getVehicleDropdown();
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
	
	
	@PostMapping("/getallvehiclerto")
	@ApiOperation(value="This method is getall Vehicle RTO ")

	public ResponseEntity<CommonCrmRes> getallVehicleDropdown(@RequestBody VehicleRTOGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<VehicleRTODropDownRes> res = Service.getallVehicleDropdown(req);
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
	
	
	// Get By rto Code

	
		@PostMapping("/getvehiclerto")
		@ApiOperation(value="This method is get by Vehicle RTO Code ")

		public ResponseEntity<CommonCrmRes> getVehicleRto(@RequestBody VehicleRTOMasterGetReq req ) {
			CommonCrmRes data = new CommonCrmRes();

			VehicleRTODropDownRes res = Service.getVehicleRto(req);
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
		
		@PostMapping("/savevehiclerto")
		@ApiOperation(value="This method is Save Vehicle RTO ")

		public ResponseEntity<CommonCrmRes> saveVehicleRTO(@RequestBody VehicleRTOMasterSaveReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = Service.validateVehicleRTO(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				/////// save

				SuccessRes res = Service.saveVehicleRTO(req);
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
