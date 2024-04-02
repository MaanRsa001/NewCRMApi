package com.maan.crm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.repository.ClientMemberDetailsRepository;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientMemberDetailsGetAllReq;
import com.maan.crm.req.ClientMemberDetailsGetReq;
import com.maan.crm.req.ClientMemberDetailsListSaveReq;
import com.maan.crm.res.ClientMemberDetailsGetRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ClientMemberDetailsService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.impl.ClientMemberDetailsServiceImpl;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "CLIENT : Client Member Details ", description = "API's")
@RequestMapping("/api")
public class ClientMemberDetailsController {

	@Autowired
	private ClientMemberDetailsService entityService;

	private Logger log = LogManager.getLogger(ClientMemberDetailsServiceImpl.class);

	@Autowired
	private PrintReqService reqPrinter;

	// Insert Client Member Details"
	// ******************************
	@PostMapping("/saveclientmem")
	@ApiOperation(value = "This method is to Save Client Member Details")
	public ResponseEntity<CommonCrmRes> saveClientMemberDetails(@RequestBody ClientDetailsJsonTemReq req) {
		
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		// Validation
		List<Error> validation = entityService.validateClientMemberDetails(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

			// save

			SuccessRes res = entityService.saveClientMemberDetails(req);
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

	// Get Client Member Details"
	// ******************************
	@PostMapping("/getclientmemberdetails")
	@ApiOperation(value = "This method is to Get Client Member Details")
	public ResponseEntity<CommonCrmRes> getClientMemDetailsById(@RequestBody ClientMemberDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		ClientMemberDetailsGetRes res = entityService.getClientMemDetailsById(req);
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

	// Get All Client Member Details
	// ******************************
	@PostMapping("/getallclientmemberdetails")
	@ApiOperation(value = "This method is to GetAll Member Details ")
	public ResponseEntity<CommonCrmRes> getAllClientMemDetails(@RequestBody ClientMemberDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<ClientMemberDetailsGetRes> res = entityService.getAllClientMemDetails(req);
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
