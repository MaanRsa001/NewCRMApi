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

import com.maan.crm.req.ClientDetailsGetAllReq;
import com.maan.crm.req.MotorMakeModelMasterDropDownReq;
import com.maan.crm.req.MotorMakeModelReq;
import com.maan.crm.req.MotorModelGetAllReq;
import com.maan.crm.req.MotorModelGetReq;
import com.maan.crm.req.VehColorGetAllReq;
import com.maan.crm.req.VehColorGetReq;
import com.maan.crm.req.VehicleColorSaveReq;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorMakeModelRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehColorDropDownRes;
import com.maan.crm.service.MotorMakeModelMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Motor MakeModel Master ", description = "API's")
@RequestMapping("/master")
public class MotorMakeModelMasterController {

	@Autowired
	private MotorMakeModelMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// DropDown

	@PostMapping("/dropdown/motormakemodelmaster")
	@ApiOperation(value = "This method is Dropdown for Mortor Make Model")
	public ResponseEntity<CommonCrmRes> getMotorMakeModelMasterDropdown(
			@RequestBody MotorMakeModelMasterDropDownReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getMotorMakeMasterModelDropdown(req);
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

	@PostMapping("/getallmodel")
	@ApiOperation(value = "This method is GetAll for Mortor Make Model")
	public ResponseEntity<CommonCrmRes> getallColor(@RequestBody MotorModelGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<MotorMakeModelRes> res = entityService.getAllColor(req);
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

	// Get

	@PostMapping("/getbymakeid")
	@ApiOperation(value = "This method is Get Mortor Make Model By Id")
	public ResponseEntity<CommonCrmRes> getColorId(@RequestBody MotorModelGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		MotorMakeModelRes res = entityService.getColorId(req);

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

	@PostMapping("/savemotormodel")
	@ApiOperation(value = "This method is Save Mortor Make Model")
	public ResponseEntity<CommonCrmRes> saveMotorModel(@RequestBody MotorMakeModelReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = entityService.validateMotorModel(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else { /////// save

			SuccessRes res = entityService.saveMotorModel(req);
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