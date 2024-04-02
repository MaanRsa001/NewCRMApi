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

import com.maan.crm.req.ManuYearMasterGetAllReq;
import com.maan.crm.req.ManuYearMasterGetReq;
import com.maan.crm.req.ManuYearMasterSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ManuYearMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.ManuYearMasterService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/master")
@Api(tags = "MASTER : Manufacture Year ", description = "API's")
public class ManuYearMasterController {

	@Autowired
	private ManuYearMasterService manuService;
	@Autowired
	private PrintReqService reqPrinter;

	// ManuYearMaster Drop Down Type
	@GetMapping("/dropdown/manuyear")
	@ApiOperation(value = "This method is to Dropdown")
	public ResponseEntity<CommonCrmRes> getManuYearDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = manuService.getManuYearMasterDropdown();
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
/*
	
	  // Get All
	  
	  
	  @PostMapping("/getallManuYear") 
	  @ApiOperation(value = "This method is to Get All")
	  public ResponseEntity<CommonCrmRes>  getallManuYearMaster(@RequestBody ManuYearMasterGetAllReq req) {
	  reqPrinter.reqPrint(req); CommonCrmRes data = new CommonCrmRes();
	  
	  //Get All 
	  List<ManuYearMasterDropDownRes> res =manuService.getAllManuYearMaster(req);
	  data.setCommonResponse(res);
	  data.setIsError(false); 
	  data.setErrorMessage(Collections.emptyList());
	  data.setMessage("Success");
	  
	  if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	  HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	  HttpStatus.BAD_REQUEST); } }
	  
	  
	  // ManuYearMaster Get By ManuYearMaster Id
	  
	  
	  @PostMapping("/getManuYearbyid") 
	  @ApiOperation(value = "This method is to Get By Id")
	  public ResponseEntity<CommonCrmRes>getManuYearById(@RequestBody ManuYearMasterGetReq req )
	  {
	  CommonCrmRes data =	  new CommonCrmRes();
	  
	  ManuYearMasterDropDownRes res = manuService.getManuYearMasterById(req);
	  
	  
	  
	  data.setCommonResponse(res); 
	  data.setIsError(false);
	  data.setErrorMessage(Collections.emptyList()); 
	  data.setMessage("Success");
	  
	  if (res != null)
	  { 
		  return new ResponseEntity<CommonCrmRes>(data,HttpStatus.CREATED); 
		  } else { return new ResponseEntity<>(null,
	  HttpStatus.BAD_REQUEST); } }
	  
	  
	  // Insert
	  
	  @PostMapping("/savemanuyear")
	  @ApiOperation(value = "This method is to Save Manufacture Year") 
	  public ResponseEntity<CommonCrmRes> saveManuYear(@RequestBody ManuYearMasterSaveReq req) 
	  {
	  reqPrinter.reqPrint(req); 
	  CommonCrmRes data = new CommonCrmRes(); 
	  List<Error>validation = manuService.validateManuYearMaster(req);
	  //// validation 
	  if(validation != null && validation.size() != 0) 
	  {
	  data.setCommonResponse(null); data.setIsError(true);
	  data.setErrorMessage(validation); data.setMessage("Failed"); return new
	  ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
	  
	  } else { /////// save
	  
	  SuccessRes res = manuService.saveManuYearMaster(req);
	  data.setCommonResponse(res); data.setIsError(false);
	  data.setErrorMessage(Collections.emptyList()); data.setMessage("Success"); if
	  (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	  HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	  HttpStatus.BAD_REQUEST); } }
	  
	  }
	  
	  
	*/  
	 }
