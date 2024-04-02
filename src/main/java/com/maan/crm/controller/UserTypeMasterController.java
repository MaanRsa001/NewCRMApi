package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.bean.ClaimLoginMaster;
import com.maan.crm.repository.ClaimLoginMasterRepository;
import com.maan.crm.req.CamundaReq;
import com.maan.crm.req.CamundaReq1;
import com.maan.crm.req.UsertypeMasterReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.UsertypeMasterRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.UserTypeMasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : User Type Master ", description = "API's")
@RequestMapping("/master")
public class UserTypeMasterController {

	@Autowired
	private PrintReqService reqPrinter;

	@Autowired
	private UserTypeMasterService service;

	@Autowired
	private CRMValidationService validationService;
	
	@Autowired
	private ClaimLoginMasterRepository claimloginmasterrepository;

	
	// Get All
	@PostMapping("/getallUsertype")
	@ApiOperation(value = "This method is get All User Type Details")
	public ResponseEntity<CommonCrmRes> getall(@RequestBody UsertypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<UsertypeMasterRes> res = service.getall(req);
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
	@PostMapping("/getUsertypeid")
	@ApiOperation(value = "This method is get User Type Details")
	public ResponseEntity<CommonCrmRes> get(@RequestBody UsertypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		UsertypeMasterRes res = service.get(req);
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
	
	// save
	@PostMapping("/insertUsertype")
	@ApiOperation(value = "This method is Insert User Type Details")
	public ResponseEntity<CommonCrmRes> insert(@RequestBody UsertypeMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		List<com.maan.crm.util.error.Error> error = validationService.userTypeMaster(req);
		if (error.size() != 0) {

			data.setCommonResponse("Validation");
			data.setIsError(false);
			data.setErrorMessage(error);
			data.setMessage("Failed");

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		} else {

			// Get All
			SuccessRes res = service.insert(req);
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
	
	@PostMapping("/camundaLoginMaster")
	@ApiOperation(value = "This method is get All User Type Details")
	public ResponseEntity<CommonCrmRes> camundaLoginMaster(@RequestBody CamundaReq req) {
		
		CommonCrmRes data = new CommonCrmRes();
		
		ClaimLoginMaster res = claimloginmasterrepository.save(req.getData());		
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		
		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/camundaLoginUserDetails")
	@ApiOperation(value = "This method is get All User Type Details")
	public ResponseEntity<CommonCrmRes> camundaLoginUserDetails(@RequestBody CamundaReq1 req) {
		
		CommonCrmRes data = new CommonCrmRes();
		
	//	ClaimLoginUserDetails res = loginuserdetailsrepository.save(req.getData());		
		data.setCommonResponse(null);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		
		return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		
	}
}
