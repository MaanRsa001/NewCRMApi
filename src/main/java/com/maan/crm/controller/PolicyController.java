
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

import com.maan.crm.req.PolicyAccountsDetailsGetAllReq;
import com.maan.crm.req.PolicyAccountsDetailsGetReq;
import com.maan.crm.req.PolicyAccountsDetailsSaveReq;
import com.maan.crm.req.PolicyAddOnGetAllReq;
import com.maan.crm.req.PolicyAddOnSaveReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetAllReq;
import com.maan.crm.req.PolicyAdditionalDetailsSaveReq;
import com.maan.crm.req.PolicyAssuredDetailsGetAllReq;
import com.maan.crm.req.PolicyAssuredDetailsGetReq;
import com.maan.crm.req.PolicyAssuredDetailsSaveReq;
import com.maan.crm.req.PolicyBulkEditReq;
import com.maan.crm.req.PolicyDataReq;
import com.maan.crm.req.PolicyDetailsGetAllReq;
import com.maan.crm.req.PolicyDetailsSaveReq;
import com.maan.crm.req.PolicyNomineeDetailsGetAllReq;
import com.maan.crm.req.PolicyNomineeDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentGetAllReq;
import com.maan.crm.req.PolicyReq;
import com.maan.crm.req.PolicyRiderDetailsGetAllReq;
import com.maan.crm.req.PolicyRiderDetailsGetReq;
import com.maan.crm.req.PolicyRiderDetailsSaveReq;
import com.maan.crm.req.PolicySearchReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.PolicyAccountsDetailsRes;
import com.maan.crm.res.PolicyAddOnGetAllRes;
import com.maan.crm.res.PolicyAdditionalDetailsGetAllRes;
import com.maan.crm.res.PolicyAssuredDetailsRes;
import com.maan.crm.res.PolicyGetAllCountRes;
import com.maan.crm.res.PolicyNomineeDetailsGetAllRes;
import com.maan.crm.res.PolicyPaymentGetAllRes;
import com.maan.crm.res.PolicyRes;
import com.maan.crm.res.PolicyRiderDetailsRes;
import com.maan.crm.res.PolicySearchRes;
import com.maan.crm.res.PolicySuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PolicyAccountsDetailsService;
import com.maan.crm.service.PolicyAssuredDetailsService;
import com.maan.crm.service.PolicyRiderDetailsService;
import com.maan.crm.service.PolicyService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "POLICY : Policy Details ", description = "API's")
@RequestMapping("/api")
public class PolicyController {

	@Autowired
	private PolicyService Service;

	@Autowired
	private CRMValidationService crmvalidation;

	@Autowired
	private PolicyAssuredDetailsService policyService;

	@Autowired
	private PolicyAccountsDetailsService policyaccountService;

	@Autowired
	private PolicyRiderDetailsService policyriderService;

	@Autowired
	private PrintReqService reqPrinter;

	// Policy Nominee Details

	@PostMapping(value = "/savepolicynominee")
	@ApiOperation(value = "This method is to Save Policy Nominee Details")

	// Insert
	public ResponseEntity<CommonCrmRes> savePolicyNomineeDetails(@RequestBody PolicyNomineeDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validatePolicyNominee(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			PolicySuccessRes res = Service.savePolicyNominee(req);
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

	//// Policy Get

	@PostMapping("/getbypolicyid")
	@ApiOperation(value = "This method is to Get Policy All Details")

	public ResponseEntity<CommonCrmRes> getPolicy(@RequestBody PolicyReq req) {
		CommonCrmRes data = new CommonCrmRes();

		PolicyRes res = Service.getPolicy(req);
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

	// Policy Add On Save

	@PostMapping(value = "/savepolicyaddon")
	@ApiOperation(value = "This method is to Save Policy Add On Details")

	// Insert
	public ResponseEntity<CommonCrmRes> savePolicyAddOn(@RequestBody PolicyAddOnSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validatePolicyAddOn(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			PolicySuccessRes res = Service.savePolicyAddOn(req);
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
	 * //// Policy Add On Get by add On id and Policy Id
	 * 
	 * @PostMapping("/getpolicyaddon")
	 * 
	 * @ApiOperation(value = "This method is to Get Policy Add On Get By Id")
	 * 
	 * public ResponseEntity<CommonCrmRes> getPolicyAddOn(@RequestBody
	 * PolicyAddOnGetReq req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * PolicyAddOnRes res = Service.getPolicyAddOn(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */
	// Policy Payment Details Insert and Update

	@PostMapping(value = "/savepolicypayment")
	@ApiOperation(value = "This method is to Save Policy Payment Details")

	// Insert
	public ResponseEntity<CommonCrmRes> savePolicyPayment(@RequestBody PolicyPaymentDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validatePolicyPayment(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			PolicySuccessRes res = Service.savePolicyPayment(req);
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
	 * //// Policy Payment Get Method
	 * 
	 * @PostMapping("/getpolicypayment")
	 * 
	 * @ApiOperation(value = "This method is to Get Policy Payment")
	 * 
	 * public ResponseEntity<CommonCrmRes> getPolicyPayment(@RequestBody
	 * PolicyPaymentDetailsGetReq req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * PolicyPaymentDetailsRes res = Service.getPolicyPayment(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */

	// Policy Additional Details Save

	@PostMapping(value = "/savepolicyadditional")
	@ApiOperation(value = "This method is to Save Policy Additional Details")

	// Insert
	public ResponseEntity<CommonCrmRes> savePolicyAdditional(@RequestBody PolicyAdditionalDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validatePolicyAdditional(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			PolicySuccessRes res = Service.savePolicyAdditional(req);
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
	 * 
	 * //// Policy Additional Details Get by Additional id and Policy Id
	 * 
	 * @PostMapping("/getpolicyadditional")
	 * 
	 * @ApiOperation(value = "This method is to Get Policy Additional Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> getPolicyAdditional(@RequestBody
	 * PolicyAdditionalDetailsGetReq req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * PolicyAdditionalDetailsRes res = Service.getPolicyAdditional(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */
	@PostMapping(value = "/savePolicyDetails")
	@ApiOperation(value = "This method is to Save Policy Details")

	// Insert
	public ResponseEntity<CommonCrmRes> savePolicyDetails(@RequestBody PolicyDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validatePolicyDetails(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			SuccessRes res = Service.savePolicyDetails(req);
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

	// Bulk Edit Prospect Details
	@PostMapping("/bulkedit/policydetails")
	@ApiOperation(value = "This method is to Bulk Edit Policy Details")
	public ResponseEntity<CommonCrmRes> bulkEditPolicy(@RequestBody PolicyBulkEditReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = Service.validatePolicy(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			SuccessRes res = Service.bulkEditPolicy(req);
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
	 * @PostMapping("/getPolicyDetails")
	 * 
	 * @ApiOperation(value="This method is to Get Policy Details")
	 * 
	 * public ResponseEntity<CommonCrmRes> savePolicyDetails(@RequestBody
	 * PolicyDetailsRes req) { CommonCrmRes data = new CommonCrmRes();
	 * 
	 * PolicyPaymentDetailsRes res = Service.savePolicyDetails(req);
	 * data.setCommonResponse(res); data.setIsError(false);
	 * data.setErrorMessage(Collections.emptyList()); data.setMessage("Success");
	 * 
	 * if (res != null) { return new ResponseEntity<CommonCrmRes>(data,
	 * HttpStatus.CREATED); } else { return new ResponseEntity<>(null,
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 */

	// Save list of Policy Assured Details
	@PostMapping("/savepolicyassureddetails")
	@ApiOperation(value = "This method is to Save Policy Assured Details")
	public ResponseEntity<CommonCrmRes> savePolicyAssuredDetails(@RequestBody PolicyAssuredDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation

		List<Error> validation = crmvalidation.validatePolicyRiderDetails(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			SuccessRes res = policyService.savePolicyAssuredDetails(req);
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

	// Get Policy Assured Details
	@PostMapping("/getpolicyassureddetails")
	@ApiOperation(value = "This method is to GetPolicy Assured Details")
	public ResponseEntity<CommonCrmRes> getPolicyAssuredDetails(@RequestBody PolicyAssuredDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		PolicyAssuredDetailsRes res = policyService.getPolicyAssuredDetailsById(req);
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

	// Get All Policy Assured Details
	@PostMapping("/getallpolicyassureddetails")
	@ApiOperation(value = "This method is to GetAll Policy Assured Details")
	public ResponseEntity<CommonCrmRes> getAllPolicyAssuredDetails(@RequestBody PolicyAssuredDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<PolicyAssuredDetailsRes> res = policyService.getAllPolicyAssuredDetails(req);
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

	// Save list of Policy Accounts Details
	@PostMapping("/savepolicyaccountsdetails")
	@ApiOperation(value = "This method is to Save Policy Accounts Details")
	public ResponseEntity<CommonCrmRes> savePolicyAccountsDetails(@RequestBody PolicyAccountsDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation

		List<Error> validation = crmvalidation.validatePolicyAccountsDetails(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			// Save
			SuccessRes res = policyaccountService.savePolicyAccountsDetails(req);
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

	// Get Policy Accounts Details
	@PostMapping("/getpolicyaccountsdetails")
	@ApiOperation(value = "This method is to GetPolicy Accounts Details")
	public ResponseEntity<CommonCrmRes> getPolicyAccountsDetails(@RequestBody PolicyAccountsDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		PolicyAccountsDetailsRes res = policyaccountService.getPolicyAccountsDetailsById(req);
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

	// Get All Policy Accounts Details
	@PostMapping("/getallpolicyaccountsdetails")
	@ApiOperation(value = "This method is to GetAll Policy Accounts Details")
	public ResponseEntity<CommonCrmRes> getAllPolicyAccountsDetails(@RequestBody PolicyAccountsDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<PolicyAccountsDetailsRes> res = policyaccountService.getAllPolicyAccountsDetails(req);
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

	// Save list of Policy Rider Details
	@PostMapping("/savepolicyriderdetails")
	@ApiOperation(value = "This method is to Save Policy Rider Details")
	public ResponseEntity<CommonCrmRes> savePolicyRiderDetails(@RequestBody PolicyRiderDetailsSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation

		List<Error> validation = crmvalidation.validatePolicyRiderDetails(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else { // Save
			SuccessRes res = policyriderService.savePolicyRiderDetails(req);
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

	// Get Policy Rider Details
	@PostMapping("/getpolicyriderdetails")
	@ApiOperation(value = "This method is to GetPolicy Rider Details")
	public ResponseEntity<CommonCrmRes> getPolicyRiderDetails(@RequestBody PolicyRiderDetailsGetReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		PolicyRiderDetailsRes res = policyriderService.getPolicyRiderDetailsById(req);
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

	// Get All Policy Rider Details
	@PostMapping("/getallpolicyriderdetails")
	@ApiOperation(value = "This method is to GetAll Policy Rider Details")
	public ResponseEntity<CommonCrmRes> getAllPolicyRiderDetails(@RequestBody PolicyRiderDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<PolicyRiderDetailsRes> res = policyriderService.getAllPolicyRiderDetails(req);
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

	// Get All Policy Details
	@PostMapping("/getallpolicydetails")
	@ApiOperation(value = "This method is to GetAll Policy Details")
	public ResponseEntity<CommonCrmRes> getAllPolicyDetails(@RequestBody PolicyDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		PolicyGetAllCountRes res = Service.getAllPolicyDetails(req);
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

	// Policy Nominee Details Get All

	@PostMapping("/getallpolicynomineedetails")
	@ApiOperation(value = "This Method is to get all policy nominee details")
	public ResponseEntity<CommonCrmRes> getallPolicyNomineeDetails(@RequestBody PolicyNomineeDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<PolicyNomineeDetailsGetAllRes> res = Service.getallPolicyNomineeDetails(req);
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

	// Policy Add On Get All

	@PostMapping("/getallpolicyaddon")
	@ApiOperation(value = "This Method is to get all Policy add on")
	public ResponseEntity<CommonCrmRes> getallPolicyAddon(@RequestBody PolicyAddOnGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<PolicyAddOnGetAllRes> res = Service.getallPolicyAddon(req);
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

	// Policy Payment Get All
	@PostMapping("/getallpolicypayment")
	@ApiOperation(value = "This Method to GetAll Policy Payment")
	public ResponseEntity<CommonCrmRes> getallPolicyPayment(@RequestBody PolicyPaymentGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<PolicyPaymentGetAllRes> res = Service.getallPolicyPayment(req);
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

	// Policy Additional Details Get All
	@PostMapping("/getallpolicyadditionaldetails")
	@ApiOperation(value = "This Method is to get all policy additional details")
	public ResponseEntity<CommonCrmRes> getallPolicyAdditionalDetails(
			@RequestBody PolicyAdditionalDetailsGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<PolicyAdditionalDetailsGetAllRes> res = Service.getallPolicyAdditionalDetails(req);
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

	// Search Policy
	
	@PostMapping("search/policydetails")
	@ApiOperation("This Method is to Search Policy Details")
	public ResponseEntity<CommonCrmRes> searchPolicyDetails(@RequestBody PolicySearchReq req)
	{
		CommonCrmRes data = new CommonCrmRes();
		PolicySearchRes res = Service.searchPolicyDetails(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
	
		if(res!= null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

/*	
	
	// Policy List Save
	@PostMapping("/listpolicydetails")
	@ApiOperation("This Method is to insert Policy Details")
	public ResponseEntity<CommonCrmRes> listPolicyDetails(@RequestBody List<PolicyAddOnSaveReq> req)
	{
		CommonCrmRes data = new CommonCrmRes();
		SuccessRes res = Service.listPolicyDetails(req);
		data.setCommonResponse(res);
		data.setErrorMessage(Collections.emptyList());
		data.setIsError(false);
		data.setMessage("Success");
	
		if(res!= null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
*/
	// Save list of Policy Rider Details
		@PostMapping("/savepolicydata")
		@ApiOperation(value = "This method is to Save Policy Details")
		public ResponseEntity<CommonCrmRes> savePolicyData(@RequestBody PolicyDataReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation

			List<Error> validation = crmvalidation.validatePolicyData(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else { // Save
				SuccessRes res = Service.savePolicyData(req);
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
