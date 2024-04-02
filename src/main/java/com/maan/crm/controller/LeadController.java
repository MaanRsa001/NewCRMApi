package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.req.ClientLeadCountReq;
import com.maan.crm.req.ClientLeadReq;
import com.maan.crm.req.ClientSearchReq;
import com.maan.crm.req.ClientUpdateStatusReq;
import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.CrmLeadSaveReq;
import com.maan.crm.req.CrmRedirectLinkReq;
import com.maan.crm.req.LeadBulkEditReq;
import com.maan.crm.req.LeadDetailsGetAllReq;
import com.maan.crm.req.LeadDetailsGetReq;
import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.LeadEnquiryGetReq;
import com.maan.crm.req.LeadGenerateReq;
import com.maan.crm.req.LeadGetallCountReq;
import com.maan.crm.req.LeadQuoteDetailsReq;
import com.maan.crm.req.LeadSearchReq;
import com.maan.crm.req.LeadViewReq;
import com.maan.crm.req.OldPolicyGetAllReq;
import com.maan.crm.req.OldPolicyGetReq;
import com.maan.crm.req.OldPolicySaveReq;
import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.res.ClientLeadsGridRes;
import com.maan.crm.res.ClientSearchRes;
import com.maan.crm.res.ClientsLeadCountRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.CrmLeadRes;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.CrmReDirectLinkRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.LeadDetailsJsonTempRes;
import com.maan.crm.res.LeadGetAllCountRes;
import com.maan.crm.res.LeadSearchCountRes;
import com.maan.crm.res.LeadSearchRes;
import com.maan.crm.res.LeadViewRes;
import com.maan.crm.res.OldPolicyRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.LeadService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.impl.LeadServiceImpl;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(  tags="LEAD : Lead Details ", description = "API's")
@RequestMapping("/api")
public class LeadController {

	private Logger log = LogManager.getLogger(LeadServiceImpl.class);

	@Autowired
	private LeadService entityService;
	
	@Autowired
	private CRMValidationService validationService;
	
	@Autowired
	private PrintReqService reqPrinter;
	

	@PostMapping(value = "/savelead")
	@ApiOperation(value="This method is to Save Lead Details")

	// Insert
	public ResponseEntity<CommonCrmRes> saveCrmLead( @RequestBody LeadDetailsJsonTempReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = entityService.validateCrmLead(req );
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


		} else {
			/////// save

			CrmLeadSuccessRes res = entityService.saveCrmLead(req);
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
/*
	////////Get All
	
	@PostMapping("/getalllead")
	@ApiOperation(value="This method is to Get All Lead Details")

	public ResponseEntity<CommonCrmRes> getAllLead(@RequestBody LeadDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<CrmLeadRes> res = entityService.getAllLead(req);
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
	
	
	//// Get By Lead Id
	
	@PostMapping("/getbyleadid")
	@ApiOperation(value="This method is to Get By Lead Id")

	public ResponseEntity<CommonCrmRes> getLead(@RequestBody LeadDetailsGetReq req) {
		CommonCrmRes data = new CommonCrmRes();
		
		LeadDetailsJsonTempRes res = entityService.getLead(req);
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

	

		
		
	//View Lead Details
		@PostMapping("/viewleaddetails")
		@ApiOperation(value="This method is to View All Lead Details By Client Id")

		public ResponseEntity<CommonCrmRes> viewLeadDetails(@RequestBody LeadViewReq req) {
			CommonCrmRes data = new CommonCrmRes();
			
			LeadViewRes res = entityService.viewLeadDetails(req);
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

		
		//Client Lead Details
		@PostMapping("/clientleaddetails")
		@ApiOperation(value="This method is to View All Lead Details By Client Id")

		public ResponseEntity<CommonCrmRes> viewClientLead(@RequestBody ClientLeadReq req) {
			CommonCrmRes data = new CommonCrmRes();
			
			List<CrmLeadRes> res = entityService.viewClientLead(req);
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

		// Bulk Edit Lead Details
		@PostMapping("/bulkedit/leaddetails")
		@ApiOperation(value = "This method is to Bulk Edit Lead Details")
		public ResponseEntity<CommonCrmRes> bulkEditLead(@RequestBody LeadBulkEditReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = entityService.validateLead(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				SuccessRes res = entityService.bulkEditLead(req);
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

		
	/*	
		@PostMapping("/search/leaddetails")
		@ApiOperation(value = "This method is to Search Lead Details")
		public ResponseEntity<CommonCrmRes> searchLeadDetails(@RequestBody ClientSearchReq req) {
			CommonCrmRes data = new CommonCrmRes();

			List<LeadSearchRes> res = entityService.searchLeadDetails(req);
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
		@PostMapping(value = "/generatelead")
		@ApiOperation(value="This method is to Search Lead Details")
		// Insert
		public ResponseEntity<CommonCrmRes> generateLead( @RequestBody LeadGenerateReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = validationService.validateGenerateLead(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


			} else {
				/////// save

				CrmLeadSuccessRes res = entityService.generateLead(req);
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
		
		
		@PostMapping(value = "/clientleadscount")
		@ApiOperation(value="This method is to Search Lead Details")
		// Insert
		public ResponseEntity<CommonCrmRes> clientsLeadCount( @RequestBody ClientLeadCountReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = validationService.validateClientsLeadCount(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


			} else {
				
				ClientsLeadCountRes res = entityService.clientsLeadCount(req);
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
		
		
		@PostMapping(value = "/updateleadstatus")
		@ApiOperation(value="This method is to Search Lead Details")
		// Insert
		public ResponseEntity<CommonCrmRes> updateLeadStatus( @RequestBody ClientUpdateStatusReq req) {
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

				CrmLeadSuccessRes res = entityService.updateLeadStatus(req);
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

		
// Get All Lead With Count
		@PostMapping("/getalllead")
		@ApiOperation("This method is to Get all Lead with count")
		public ResponseEntity<CommonCrmRes> getallLeadCount(@RequestBody LeadGetallCountReq req)
		{
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			LeadGetAllCountRes res = entityService.getallLeadCount(req);
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setMessage("Success");
			data.setErrorMessage(Collections.emptyList());
			if(res!=null) {
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
			}
		
		}
		
		
		// Search Lead Count
		
		@PostMapping("/search/leaddetails")
		@ApiOperation(value = "This method is to Search Lead Count ")
		public ResponseEntity<CommonCrmRes> searchLeadCount(@RequestBody LeadSearchReq req) {
			CommonCrmRes data = new CommonCrmRes();

			LeadSearchCountRes res = entityService.searchLeadCount(req);
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

		
		@PostMapping(value = "/updatequotedetails")
		@ApiOperation(value="This method is to Search Lead Details")
		// Insert
		public ResponseEntity<CommonCrmRes> updateLeadQuoteDetails( @RequestBody LeadQuoteDetailsReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = validationService.validateLeadQuoteDetails(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


			} else {
				/////// save

				CrmLeadSuccessRes res = entityService.updateLeadQuoteDetails(req);
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
		
		
		@PostMapping(value = "/crmredirectlink")
		@ApiOperation(value="This method is to Search Lead Details")
		// Insert
		public ResponseEntity<CommonCrmRes> getCrmRedirectLink( @RequestBody CrmRedirectLinkReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = validationService.validateCrmRedirectLink(req);
			//// validation
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);


			} else {
				/////// save
				CrmReDirectLinkRes res = entityService.getCrmRedirectLink(req);
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
		
		//Enquiry List
		@PostMapping("/lead/enqirylist")
		@ApiOperation(value="This method is to lead enquiry details")
		public ResponseEntity<CommonCrmRes> getEnquiryListDetails(@RequestBody LeadViewReq req){
			CommonCrmRes data = new CommonCrmRes();
			ClientLeadsGridRes res = entityService.getEnquiryListDetails(req);

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
}


