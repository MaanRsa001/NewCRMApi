package com.maan.crm.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.RequestProposerInfo;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.TravelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "TRAVEL : Travel Api", description = "API's")
@RequestMapping("/travel")
public class TravelApiController {

	@Autowired
	private TravelService Service;

	@Autowired
	private PrintReqService reqPrinter;

	@PostMapping(value = "/gettravelInfo")
	@ApiOperation(value = "This method is to Get Travel Info")
	public ResponseEntity<CommonCrmRes> GetTravelPolicyDetails(@RequestBody RequestProposerInfo req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		Object res = Service.GetTravelPolicyDetails(req);

		if (res != null) {
			
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");
			
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			
			data.setCommonResponse("Failed");
			data.setIsError(true);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Failed");
			
			return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping(value = "/gettravelInfoList")
	@ApiOperation(value = "This method is to Get Travel Info")
	public ResponseEntity<CommonCrmRes> GettravelInfoList(@RequestBody RequestProposerInfo req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		Object res = Service.GettravelInfoList(req);

		if (res != null) {
			
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");
			
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			
			data.setCommonResponse("Failed");
			data.setIsError(true);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Failed");
			
			return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
		}

	}
}
