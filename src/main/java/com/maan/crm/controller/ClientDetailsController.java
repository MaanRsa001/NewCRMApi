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

import com.maan.crm.repository.ClientDetailsRepository;
import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.ClientDetailsGetAllReq;
import com.maan.crm.req.ClientDetailsGetReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.req.ClientDetailsSaveReq;
import com.maan.crm.req.ClientDetailsUpdateReq;
import com.maan.crm.req.ClientGetAllReq;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.res.ClientDetailsGetAllRes;
import com.maan.crm.res.ClientDetailsGetRes;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.ClientDetailsJsonTemRes;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientSearchCountRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.ClientViewRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.EnquiryGridRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.ClientDetailsService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "CLIENT : Client Details ", description = "API's")
@RequestMapping("/api")
public class ClientDetailsController {

	@Autowired
	private ClientDetailsService clientservice;

	@Autowired
	private CRMValidationService validationService;

	@Autowired
	private PrintReqService reqPrinter;

	// Save list of Client Details
	@PostMapping("/saveclientdetails")
	@ApiOperation(value = "This method is to Save Client Details")
	public ResponseEntity<CommonCrmRes> saveClientInfo(@RequestBody ClientDetailsJsonTemReq req) {

		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = validationService.validateClientDetailsReq(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			CrmClientSuccessRes res = clientservice.saveClientDetails(req);
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
	
	
	// Save list of Client Details
	@PostMapping("/updateclientdetails")
	@ApiOperation(value = "This method is to Update Client Details")
	public ResponseEntity<CommonCrmRes> updateClientInfo(@RequestBody ClientDetailsJsonTemReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = validationService.validateClientUpdateReq(req.getOtherDetails());
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			CrmClientSuccessRes res = clientservice.updateClientDetails(req);
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
	
	
	// Bulk Edit Client Details
	@PostMapping("/bulkedit/clientdetails")
	@ApiOperation(value = "This method is to Update Client Details")
	public ResponseEntity<CommonCrmRes> bulkEditClientInfo(@RequestBody ClientBulkEditReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = validationService.validatebulkEditClientReq(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			SuccessRes res = clientservice.bulkEditClientInfo(req);
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

	@PostMapping("/getclientdetails")
	@ApiOperation(value = "This method is to Get Client Details")
	public ResponseEntity<CommonCrmRes> getClientDetails(@RequestBody ClientDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		
		ClientDetailsJsonTemRes commonRes = clientservice.getClientDetailsById(req);
		data.setCommonResponse(commonRes);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (commonRes.getClientDetails() != null) {
			return new ResponseEntity<>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

/*	
	// Get All Client Details
	@PostMapping("/getallclientdetails")
	@ApiOperation(value = "This method is to GetAll Client Details")
	public ResponseEntity<CommonCrmRes> getAllClientDetails(@RequestBody ClientDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<ClientDetailsGridRes> res = clientservice.getAllClientDetails(req);
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

	*/
	//Get All Client Details with Count
	@PostMapping("/getallclientdetails")
	@ApiOperation(value = "This Method is to Get All Clients")
	public ResponseEntity<CommonCrmRes> getallClients(@RequestBody ClientGetAllReq req){
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
	    ClientDetailsGetAllRes res = clientservice.getallClients(req);
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");
		if(res!=null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	// View Client Details
	@PostMapping("/viewclientdetails")
	@ApiOperation(value = "This method is to View All Client Details By Client Id")

	public ResponseEntity<CommonCrmRes> viewClientDetails(@RequestBody ClientViewReq req) {
		CommonCrmRes data = new CommonCrmRes();

		ClientViewRes res = clientservice.viewClientDetails(req);
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


	@PostMapping("/client/leads")
	@ApiOperation(value="This method is to lead enquiry details")
	public ResponseEntity<CommonCrmRes> getLeadEnquiryDetails(@RequestBody ClientViewReq req){
		CommonCrmRes data = new CommonCrmRes();
		ClientLeadsGridRes res = clientservice.getLeadEnquiryDetails(req);

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
	
	/*
	// Search Client Details
	@PostMapping("/search/clientdetails")
	@ApiOperation(value = "This method is to Search Client Details")
	public ResponseEntity<CommonCrmRes> searchClientDetails(@RequestBody ClientSearchReq req) {
		CommonCrmRes data = new CommonCrmRes();

		List<ClientSearchRes> res = clientservice.searchClientDetails(req);
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
*/
	
	// Search Client Details With Count
		@PostMapping("/search/clientdetails")
		@ApiOperation(value = "This method is to Search All Client Details")
		public ResponseEntity<CommonCrmRes> searchallclients(@RequestBody ClientSearchReq req) {
			CommonCrmRes data = new CommonCrmRes();

			ClientSearchCountRes res = clientservice.searchAllClients(req);
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
