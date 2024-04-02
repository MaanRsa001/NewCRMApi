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

import com.maan.crm.req.MotorBodyGetReq;
import com.maan.crm.req.MotorBodySaveReq;
import com.maan.crm.req.MotorBodyTypeGetAllReq;
import com.maan.crm.req.MotorMakeGetReq;
import com.maan.crm.req.MotorMakeSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorBodyDropDownRes;
import com.maan.crm.res.MotorMakeDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.MotorBodyTypeMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Motor BodyType Master ", description = "API's")
@RequestMapping("/master")
public class MotorBodyTypeMasterController {

	@Autowired
	private MotorBodyTypeMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// DropDown

	@GetMapping("/dropdownmotorbodytypemaster")
	public ResponseEntity<CommonCrmRes> getMotorBodyTypeMasterDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getMotorBodyTypeMasterDropdown();
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
	@PostMapping("/getallmotorbodytype")
	@ApiOperation(value="This method is Get all Motor Body Type ")

	public ResponseEntity<CommonCrmRes> getallMotorBodyType(@RequestBody MotorBodyTypeGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		//Get All
		List<MotorBodyDropDownRes> res = entityService.getallMotorBodyType(req);
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

	
		@PostMapping("/getbodyid")
		@ApiOperation(value="This method is get by body Id ")

		public ResponseEntity<CommonCrmRes> getBodyId(@RequestBody MotorBodyGetReq req ) {
			CommonCrmRes data = new CommonCrmRes();

			MotorBodyDropDownRes res = entityService.getBodyId(req);
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
		
		@PostMapping("/savemotorbody")
		@ApiOperation(value="This method is Save Motor  body")

		public ResponseEntity<CommonCrmRes> saveMotorBody(@RequestBody MotorBodySaveReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = entityService.validateMotorBody(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				/////// save

				SuccessRes res = entityService.saveMotorBody(req);
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