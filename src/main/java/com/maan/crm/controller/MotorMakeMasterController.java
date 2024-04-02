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

import com.maan.crm.req.MotorMakeGetAllReq;
import com.maan.crm.req.MotorMakeGetReq;
import com.maan.crm.req.MotorMakeSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorMakeDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.MotorMakeMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Motor Make Master ", description = "API's")
@RequestMapping("/master")
public class MotorMakeMasterController {

	@Autowired
	private MotorMakeMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// DropDown

	@GetMapping("/dropdown/motormakemaster")
	public ResponseEntity<CommonCrmRes> getMotorMakeMasterDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getMotorMakeMasterDropdown();
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
	
	
		@PostMapping("/getallmotormake")
		@ApiOperation(value="This method is Get all Motor Make ")

		public ResponseEntity<CommonCrmRes> getallMotorMake(@RequestBody MotorMakeGetAllReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();

			//Get All
			List<MotorMakeDropDownRes> res = entityService.getallMotorDropdown(req);
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
		
		// Get By Make Id

		
			@PostMapping("/getmakeid")
			@ApiOperation(value="This method is get by Make Id ")

			public ResponseEntity<CommonCrmRes> getMakeId(@RequestBody MotorMakeGetReq req ) {
				CommonCrmRes data = new CommonCrmRes();

				MotorMakeDropDownRes res = entityService.getMakeId(req);
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
			
			@PostMapping("/savemakemotor")
			@ApiOperation(value="This method is Save Make Motor ")

			public ResponseEntity<CommonCrmRes> saveMakeMotor(@RequestBody MotorMakeSaveReq req) {
				reqPrinter.reqPrint(req);
				CommonCrmRes data = new CommonCrmRes();
				List<Error> validation = entityService.validateMakeMotor(req);
				//// validation
				if (validation != null && validation.size() != 0) {
					data.setCommonResponse(null);
					data.setIsError(true);
					data.setErrorMessage(validation);
					data.setMessage("Failed");
					return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

				} else {
					/////// save

					SuccessRes res = entityService.saveMakeMotor(req);
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