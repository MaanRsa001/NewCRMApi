package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.ManuYearMasterGetAllReq;
import com.maan.crm.req.ManuYearMasterGetReq;
import com.maan.crm.req.ManuYearMasterSaveReq;
import com.maan.crm.req.VehColorGetAllReq;
import com.maan.crm.req.VehColorGetReq;
import com.maan.crm.req.VehicleColorSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ManuYearMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehColorDropDownRes;
import com.maan.crm.service.InsuranceCompanyMasterService;
import com.maan.crm.service.MotorColorMasterService;
import com.maan.crm.service.PrintReqService;

import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "MASTER :Motor Color Master ", description = "API's")
@RequestMapping("/master")
public class MotorColorMasterController {

	@Autowired
	private MotorColorMasterService entityService;
	@Autowired
	private PrintReqService reqPrinter;

	// DropDown

	@GetMapping("/dropdownmotorcolormaster")
	@ApiOperation(value="This method is toMotor Color Master Drop Down")

	public ResponseEntity<CommonCrmRes> getMotorColorMasterDropDown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = entityService.getMotorColorMasterDropDown();
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
	  
	  
	  @PostMapping("/getallcolor") 
	  public ResponseEntity<CommonCrmRes>  getallColor(@RequestBody VehColorGetAllReq req) {
	  reqPrinter.reqPrint(req); CommonCrmRes data = new CommonCrmRes();
	  
	  //Get All 
	  List<VehColorDropDownRes> res =entityService.getAllColor(req);
	  data.setCommonResponse(res);
	  data.setIsError(false); 
	  data.setErrorMessage(Collections.emptyList());
	  data.setMessage("Success");
	  
	  if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	  HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	  HttpStatus.BAD_REQUEST); } }
	  
	  
	  //GEt 
	  
	  @PostMapping("/getbycolorid") 
	  public ResponseEntity<CommonCrmRes>getColorId(@RequestBody VehColorGetReq req )
	  {
	  CommonCrmRes data =	  new CommonCrmRes();
	  
	  VehColorDropDownRes res = entityService.getColorId(req);
	  
	  
	  
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
	  
	  @PostMapping("/savevehcolor") 
	  public ResponseEntity<CommonCrmRes> saveVehColor(@RequestBody VehicleColorSaveReq req) 
	  {
	  reqPrinter.reqPrint(req); 
	  CommonCrmRes data = new CommonCrmRes(); 
	  List<Error>validation = entityService.validateVehColor(req);
	  //// validation 
	  if(validation != null && validation.size() != 0) 
	  {
	  data.setCommonResponse(null); data.setIsError(true);
	  data.setErrorMessage(validation); data.setMessage("Failed"); return new
	  ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
	  
	  } else { /////// save
	  
	  SuccessRes res = entityService.saveVehColor(req);
	  data.setCommonResponse(res); data.setIsError(false);
	  data.setErrorMessage(Collections.emptyList()); data.setMessage("Success"); if
	  (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	  HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	  HttpStatus.BAD_REQUEST); } }
	  
	  }
	  
	  
	  
	 }
