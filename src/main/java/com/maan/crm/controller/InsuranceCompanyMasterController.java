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

import com.maan.crm.req.InsuranceCompanyMasterGetAllReq;
import com.maan.crm.req.InsuranceCompanyMasterGetReq;
import com.maan.crm.req.InsuranceCompanyMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.InsuranceCompanyMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.InsuranceCompanyMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Insurance Company Master ", description = "API's")
@RequestMapping("/master")
public class InsuranceCompanyMasterController {

	@Autowired
	private InsuranceCompanyMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;
	@Autowired
	private CRMValidationService validationService;

	// DropDown

	@GetMapping("/dropdown/inscompmaster")
	@ApiOperation(value = "This method is to Insurance Company Master Drop Down")

	public ResponseEntity<CommonCrmRes> getInsCompMasterDropDown() {

		CommonCrmRes data = new CommonCrmRes();

		// dropdown
		List<DropDownRes> res = entityService.getInsCompMasterDropDown();
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

	// GetAll Insurance Company Master
	@PostMapping("/getallinscompmaster")
	@ApiOperation(value = "This method is to GetAll Insurance Company Master")
	public ResponseEntity<CommonCrmRes> getAllInsuranceCompanyMaster(@RequestBody InsuranceCompanyMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<InsuranceCompanyMasterRes> res = entityService.getAllInsuranceCompanyMaster(req);
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

	//Get Insurance Company Master
	@PostMapping("/getinscompmasterbyid")
	@ApiOperation(value = "This method is to Get Insurance Company Master")
	public ResponseEntity<CommonCrmRes> getInsuranceCompanyMasterById(@RequestBody InsuranceCompanyMasterGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		InsuranceCompanyMasterRes res = entityService.getInsuranceCompanyMasterById(req);
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
	// Save Insurance Company Master
		@PostMapping("/saveinscompmasterdetails")
		@ApiOperation(value = "This method is to Save Insurance Company Master")
		public ResponseEntity<CommonCrmRes> saveInsuranceCompanyMaster(@RequestBody InsuranceCompanyMasterSaveReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			
			List<Error> error = validationService.insuranceCompanyMaster(req);
			if (error.size() != 0) {

				data.setCommonResponse("Validation");
				data.setIsError(false);
				data.setErrorMessage(error);
				data.setMessage("Failed");

				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			} else {
			
			// Save
			SuccessRes res = entityService.saveInsuranceCompanyMaster(req);
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");

			if (res != null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}}
		}

		

}