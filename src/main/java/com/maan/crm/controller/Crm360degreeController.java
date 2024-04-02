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

import com.maan.crm.req.Client360DegreeReq;
import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.FollowUpDetailsReq;
import com.maan.crm.req.FollowUpDetailsSaveReq;
import com.maan.crm.req.Lead360DegreeReq;
import com.maan.crm.req.LeadQuoteDetailsGetReq;
import com.maan.crm.req.Prospect360DegreeReq;
import com.maan.crm.req.ProspectBulkEditReq;
import com.maan.crm.req.ProspectDetailsGetAllReq;
import com.maan.crm.req.ProspectDetailsSaveReq;
import com.maan.crm.req.ProspectOldPolicyDetailsSaveReq;
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationInsurerSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectQuotationSaveReq;
import com.maan.crm.req.ProspectQuotationVehicleDetailsSaveReq;
import com.maan.crm.req.ProspectReq;
import com.maan.crm.req.ProspectSearchReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.res.Client360DegreeRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.FollowUpDetailsRes;
import com.maan.crm.res.Lead360DegreeRes;
import com.maan.crm.res.LeadQuoteDetailsGetRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.Prospect360DegreeRes;
import com.maan.crm.res.ProspectBulkEditRes;
import com.maan.crm.res.ProspectDetailsRes;
import com.maan.crm.res.ProspectGetAllCountRes;
import com.maan.crm.res.ProspectPaymentRes;
import com.maan.crm.res.ProspectPaymentSuccessRes;
import com.maan.crm.res.ProspectQuotationInsurerSuccessRes;
import com.maan.crm.res.ProspectQuotationSuccessRes;
import com.maan.crm.res.ProspectRes;
import com.maan.crm.res.ProspectSearchRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.Crm360DegreeService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.ProspectService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "CRM 360 Degree : CRM 360 Degree Details ", description = "API's")
@RequestMapping("/api")
public class Crm360degreeController {

	@Autowired
	private Crm360DegreeService Service;

	@Autowired
	private PrintReqService reqPrinter;

	
	//360 Degree Client Details
	
	@PostMapping("/clientview")
	@ApiOperation(value = "This is 360 view Client Details")
	public ResponseEntity<CommonCrmRes> clientView(@RequestBody Client360DegreeReq req){
		CommonCrmRes data = new CommonCrmRes();
		Client360DegreeRes res = Service.clientView(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		}
		}
	
	@PostMapping("/leadview")
	@ApiOperation(value="This is 360 view Lead Details")
	public ResponseEntity<CommonCrmRes> leadview(@RequestBody Lead360DegreeReq req){
		CommonCrmRes data = new CommonCrmRes();
		List<Lead360DegreeRes> res = Service.leadview(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes> (data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/prospectview")
	@ApiOperation(value="This is 360 view Prospect Details")
	public ResponseEntity<CommonCrmRes> prospectview(@RequestBody Prospect360DegreeReq req) {
		CommonCrmRes data = new CommonCrmRes();
		List<Prospect360DegreeRes> res = Service.prospectview(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
		
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/followupdetails")
	@ApiOperation(value = "This method is to Get FollowUp Details")
	public ResponseEntity<CommonCrmRes> getfollowup(@RequestBody FollowUpDetailsReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<FollowUpDetailsRes> res = Service.getfollowup(req);
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
