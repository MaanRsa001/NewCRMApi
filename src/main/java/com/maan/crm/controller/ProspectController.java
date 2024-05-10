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

import com.maan.crm.req.ClientBulkEditReq;
import com.maan.crm.req.LeadDetailsJsonTempReq;
import com.maan.crm.req.LeadQuoteDetailsGetReq;
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
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LeadQuoteDetailsGetRes;
import com.maan.crm.res.LeadSearchCountRes;
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
import com.maan.crm.res.LeadDetailsGetAllRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.ProspectService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "PROSPECT : Prospect Details ", description = "API's")
@RequestMapping("/prospect")
public class ProspectController {

	@Autowired
	private ProspectService Service;

	@Autowired
	private CRMValidationService crmvalidation;

	@Autowired
	private PrintReqService reqPrinter;

	@GetMapping("/getall")
	@ApiOperation(value = "This method is to Get prospect Payment By Prospect Id")

	public ResponseEntity<CommonCrmRes> getAllProspect() {
		CommonCrmRes data = new CommonCrmRes();

		List<ProspectPaymentRes> res = Service.getAllProspect();
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

	// Prospect Payment Details Save

	@PostMapping(value = "/saveprospectpayment")
	@ApiOperation(value = "This method is to Save prospect Payment Details")
	// Insert
	public ResponseEntity<CommonCrmRes> saveProspectPayment(@RequestBody LeadDetailsJsonTempReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateProspectPayment(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			ProspectPaymentSuccessRes res = Service.saveProspectPayment(req);
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

////Get By Prospect Id

	@PostMapping("/getbyprospectid")
	@ApiOperation(value = "This method is to Get prospect Details By Prospect Id")

	public ResponseEntity<CommonCrmRes> getProspect(@RequestBody ProspectReq req) {
		CommonCrmRes data = new CommonCrmRes();

		ProspectRes res = Service.getProspect(req);
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
	 * ////////Get All
	 * 
	 * @PostMapping("/getallprospect")
	 * 
	 * @ApiOperation(value="This method is to Get All prospect Payments") public
	 * ResponseEntity<CommonCrmRes> getAllLead(@RequestBody ProspectPaymentGetAllReq
	 * req) { reqPrinter.reqPrint(req); CommonCrmRes data = new CommonCrmRes();
	 * 
	 * // Get All List<ProspectPaymentRes> res = Service.getAllProspectPayment(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */
	// Prospect Quotation Basic Info Save

	@PostMapping(value = "/saveprospectquotation")
	@ApiOperation(value = "This method is to Save prospect Quotation Basic Info Details")
	// Insert
	public ResponseEntity<CommonCrmRes> saveProspectQuotation(@RequestBody ProspectQuotationSaveReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateProspectQuotation(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			ProspectQuotationSuccessRes res = Service.saveProspectQuotation(req);
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
	 * ////Get By Quotation Id
	 * 
	 * @PostMapping("/getbyquotationid")
	 * 
	 * @ApiOperation(
	 * value="This method is to Get prospect Quotation Basic Info By Quotation Id")
	 * public ResponseEntity<CommonCrmRes> getQuotation(@RequestBody
	 * ProspectQuotaionGetReq req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectQuoationRes res = Service.getQuotation(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */

	@PostMapping("/saveprospectdetails")
	@ApiOperation(value = "This method is to Save prospect Details")

	public ResponseEntity<CommonCrmRes> saveProspectDetails(@RequestBody ProspectDetailsSaveReq req) {

		reqPrinter.reqPrint(req);

		CommonCrmRes data = new CommonCrmRes();

		List<Error> validation = crmvalidation.validateProspectDetails(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

			SuccessRes res = Service.saveProspectDetails(req);
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
	 * @PostMapping("/getProspectDetails")
	 * 
	 * @ApiOperation(value="This method is to Get prospect  Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> getProspectDetails(@RequestBody
	 * ProspectDetailsSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectDetailsRes res = Service.getProspectDetails(req); if (res != null) {
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED); } else {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Failed");
	 * return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); } }
	 */

	/*
	 * @PostMapping("/saveProspectOldPolicyDetails")
	 * 
	 * @ApiOperation(value = "This method is to Save prospect Old Policy Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> saveProspectOldPolicyDetails(@RequestBody
	 * ProspectOldPolicyDetailsSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * List<Error> validation = crmvalidation.validateProspectOldPolicyDetails(req);
	 * if (validation != null && validation.size() != 0) {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(validation); data.setMessage("Failed"); return new
	 * ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
	 * 
	 * } else {
	 * 
	 * SuccessRes res = Service.saveProspectOldPolicyDetails(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success"); if
	 * (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } } }
	 * 
	 * /*
	 * 
	 * @PostMapping("/getProspectOldPolicyDetails")
	 * 
	 * @ApiOperation(value="This method is to Get prospect Old Policy Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> getProspectOldPolicyDetails(@RequestBody
	 * ProspectDetailsSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectOldPolicyDetailsRes res = Service.getProspectOldPolicyDetails(req);
	 * if (res != null) { data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED); } else {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Failed");
	 * return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); } }
	 */
	@PostMapping("/saveProspectQuotationPolicyAccounts")
	@ApiOperation(value = "This method is to Save prospect Quotation Policy Amounts")

	public ResponseEntity<CommonCrmRes> saveProspectQuotationPolicyAccounts(
			@RequestBody ProspectQuotationPolicyAccountsSaveReq req) {

		reqPrinter.reqPrint(req);

		CommonCrmRes data = new CommonCrmRes();

		List<Error> validation = crmvalidation.validateProspectQuotationPolicyAccounts(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

			SuccessRes res = Service.saveProspectQuotationPolicyAccounts(req);
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
	 * @PostMapping("/getProspectQuotationPolicyAccounts")
	 * 
	 * @ApiOperation(
	 * value="This method is to Get prospect Quotation Policy Accounts")
	 * 
	 * public ResponseEntity<CommonCrmRes>
	 * getProspectQuotationPolicyAccounts(@RequestBody
	 * ProspectQuotationPolicyAccountsSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectQuotationPolicyAccountsRes res =
	 * Service.getProspectQuotationPolicyAccounts(req); if (res != null) {
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED); } else {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Failed");
	 * return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); } }
	 */
	@PostMapping("/saveProspectQuotationAddOn")
	@ApiOperation(value = "This method is to Save prospect Quotation AddOn")

	public ResponseEntity<CommonCrmRes> saveProspectQuotationAddOn(@RequestBody ProspectQuotationAddOnSaveReq req) {

		reqPrinter.reqPrint(req);

		CommonCrmRes data = new CommonCrmRes();

		List<Error> validation = crmvalidation.validateProspectQuotationAddOn(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

			SuccessRes res = Service.saveProspectQuotationAddOn(req);
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
	 * @PostMapping("/getProspectQuotationAddOn")
	 * 
	 * @ApiOperation(value="This method is to Get prospect Quotation Add On")
	 * 
	 * public ResponseEntity<CommonCrmRes> getProspectQuotationAddOn(@RequestBody
	 * ProspectQuotationAddOnSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectQuotationAddOnRes res = Service.getProspectQuotationAddOn(req); if
	 * (res != null) { data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED); } else {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Failed");
	 * return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	// Prospect Quotation Insurer Details Save

	@PostMapping(value = "/saveprospectinsurer")
	@ApiOperation(value = "This method is to Save prospect Quotation Insurer Details")

	// Insert
	public ResponseEntity<CommonCrmRes> saveProspectQuotationInsurer(@RequestBody ProspectQuotationInsurerSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateProspectQuotationInsurer(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			ProspectQuotationInsurerSuccessRes res = Service.saveProspectQuotationInsurer(req);
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
	 * ////Get By Insurer Id
	 * 
	 * @PostMapping("/getbyinsurerid")
	 * 
	 * @ApiOperation(value="This method is to Get prospect Quotation Insurer ")
	 * 
	 * public ResponseEntity<CommonCrmRes> getProspectInsurer(@RequestBody
	 * ProspectQuotationInsurerGetReq req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectQuotationInsurerRes res = Service.getProspectInsurer(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */

	@PostMapping("/saveProspectsQuotationOtherDetails")
	@ApiOperation(value = "This method is to Save prospect Quotation Other Details")

	public ResponseEntity<CommonCrmRes> saveProspectsQuotationOtherDetails(
			@RequestBody ProspectsQuotationOtherDetailsSaveReq req) {

		reqPrinter.reqPrint(req);

		CommonCrmRes data = new CommonCrmRes();

		List<Error> validation = crmvalidation.validateProspectsQuotationOtherDetails(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			SuccessRes res = Service.saveProspectsQuotationOtherDetails(req);
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
	 * @PostMapping("/getProspectsQuotationOtherDetails")
	 * 
	 * @ApiOperation(value="This method is to Get prospect Quotation Other Details")
	 * 
	 * public ResponseEntity<CommonCrmRes>
	 * getProspectsQuotationOtherDetails(@RequestBody
	 * ProspectsQuotationOtherDetailsSaveReq req) {
	 * 
	 * reqPrinter.reqPrint(req);
	 * 
	 * CommonCrmRes data = new CommonCrmRes();
	 * 
	 * ProspectsQuotationOtherDetailsRes res =
	 * Service.getProspectsQuotationOtherDetails(req); if (res != null) {
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED); } else {
	 * data.setCommonResponse(null); data.setIsError(true);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Failed");
	 * return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); } }
	 */
	// Prospect Quotation Vehicle Details

	@PostMapping(value = "/saveprospectvehicle")
	@ApiOperation(value = "This method is to Save prospect Quotation Vehicle Details")

	// Insert
	public ResponseEntity<CommonCrmRes> saveProspectQuotationVehicle(
			@RequestBody ProspectQuotationVehicleDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateProspectQuotationVehicle(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			ProspectQuotationInsurerSuccessRes res = Service.saveProspectQuotationVehicle(req);
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

	// Get All Prospect Details
	@PostMapping("/getallprospectdetails")
	@ApiOperation(value = "This method is to GetAll Prospect Details")
	public ResponseEntity<CommonCrmRes> getAllProspectCount(@RequestBody ProspectDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		ProspectGetAllCountRes res = Service.getAllProspectCount(req);
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
	 * ////Prospect Quotation Vehicle Details Get By Vehicle Id
	 * 
	 * @PostMapping("/getbyvehicleid")
	 * 
	 * @ApiOperation(
	 * value="This method is to Get prospect Quotation Vehicle Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> getProspectVehicle(@RequestBody
	 * ProspectQuotationVehicleDetailsGetReq req) { CommonCrmRes data = new
	 * CommonCrmRes();
	 * 
	 * ProspectQuotationVehicleDetailsRes res = Service.getProspectVehicle(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */

	// Bulk Edit Prospect Details
	@PostMapping("/bulkedit/prospectdetails")
	@ApiOperation(value = "This method is to Bulk Edit Prospect Details")
	public ResponseEntity<CommonCrmRes> bulkEditProspect(@RequestBody ProspectBulkEditReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = Service.validateProspect(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			SuccessRes res = Service.bulkEditProspect(req);
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

//  Search Prospect Details

	@PostMapping("/search/prospectdetails")
	@ApiOperation(value = "This method is to Search Prospect Details")
	public ResponseEntity<CommonCrmRes> searchProspectDetails(@RequestBody ProspectSearchReq req) {
		CommonCrmRes data = new CommonCrmRes();

		ProspectSearchRes res = Service.searchProspectDetails(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	
	
	// Lead Quote Details
	
	@PostMapping("/leadquotedetails")
	@ApiOperation(value = "This Method is to display lead quote details")
	public ResponseEntity<CommonCrmRes> leadQuoteDetails(@RequestBody LeadQuoteDetailsGetReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		List<LeadQuoteDetailsGetRes> res = Service.leadQuoteDetails(req);
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
	
	@PostMapping("/getlead/byclientrefno")
	@ApiOperation(value = "This Method is to get all leads by a client")
	public ResponseEntity<CommonCrmRes> leadDetailsList(@RequestBody LeadQuoteDetailsGetReq req){
		
		CommonCrmRes data = new CommonCrmRes();
		LeadDetailsGetAllRes res = Service.getLeadDetailsList(req);
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
