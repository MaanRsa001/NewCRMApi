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

import com.maan.crm.req.NotifTemplateMasterReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.NotifTemplateMasterRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.NotificationTemplateMasterService;
import com.maan.crm.service.PrintReqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER : Notification Template Master ", description = "API's")
@RequestMapping("/master")
public class NotificationTemplateMasterController {

	@Autowired
	private PrintReqService reqPrinter;

	@Autowired
	private NotificationTemplateMasterService service;
	
	@Autowired
	private CRMValidationService validationService;

	// Get All
	@PostMapping("/getallnotificationtemplate")
	@ApiOperation(value = "This method is get All Notification Template Details")
	public ResponseEntity<CommonCrmRes> getallCompanyType(@RequestBody NotifTemplateMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<NotifTemplateMasterRes> res = service.getall(req);
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
	@PostMapping("/getnotificationtemplateid")
	@ApiOperation(value = "This method is get Notification Template Details")
	public ResponseEntity<CommonCrmRes> getCompanyType(@RequestBody NotifTemplateMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		NotifTemplateMasterRes res = service.get(req);
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
	@PostMapping("/insertnotificationtemplate")
	@ApiOperation(value = "This method is Notification Template Details")
	public ResponseEntity<CommonCrmRes> insert(@RequestBody NotifTemplateMasterReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		List<com.maan.crm.util.error.Error> error = validationService.notificationTemplateMaster(req);
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

}
