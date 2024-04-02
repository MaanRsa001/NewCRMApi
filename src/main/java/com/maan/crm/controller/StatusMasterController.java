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

import com.maan.crm.req.StatusMasterReq;
import com.maan.crm.req.UpdateEnquiryStatusReq;
import com.maan.crm.req.UpdateQuoteStatusReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownResA;
import com.maan.crm.res.EnquirySuccessRes;
import com.maan.crm.res.QuoteSuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.StatusMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "STATUS : STATUS MASTER ")
@RequestMapping("/api")
public class StatusMasterController {

	@Autowired
	private StatusMasterService entityService;

	@Autowired
	private PrintReqService reqPrinter;
	
	@Autowired
	private CRMValidationService validationService;
	
	// Status Master

	@PostMapping("/dropdown/status")
	@ApiOperation(value = "This method is to StateMaster Drop Down")

	public ResponseEntity<CommonCrmRes> getstatusmaster(@RequestBody StatusMasterReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownResA> res = entityService.getstatusmaster(req);
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

	
	@PostMapping(value = "/update/enquirystatus")
	@ApiOperation(value="This method is to Update Enquiry Details")
	// Insert
	public ResponseEntity<CommonCrmRes> updateEnquiryStatus( @RequestBody UpdateEnquiryStatusReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = validationService.validateupdateLeadStatus(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


		} else {
			/////// save

			EnquirySuccessRes res = entityService.updateEnquiryStatus(req);
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
	
	@PostMapping(value = "/update/quotestatus")
	@ApiOperation(value="This method is to Update Quote Details")
	// Insert
	public ResponseEntity<CommonCrmRes> updateQuoteStatus( @RequestBody UpdateQuoteStatusReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = validationService.validateupdateQuoteStatus(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


		} else {
			/////// save

			QuoteSuccessRes res = entityService.updateQuoteStatus(req);
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
