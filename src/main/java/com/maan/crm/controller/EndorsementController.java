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

import com.maan.crm.req.EndorsementFinancialDetailsGetReq;
import com.maan.crm.req.EndorsementFinancialDetailsSaveReq;
import com.maan.crm.req.EndorsementGeneralDetailsGetReq;
import com.maan.crm.req.EndorsementGeneralDetailsSaveReq;
import com.maan.crm.req.EndorsementPaymentDetailsGetReq;
import com.maan.crm.req.EndorsementPaymentDetailsSaveReq;
import com.maan.crm.req.PolicyAddOnGetReq;
import com.maan.crm.req.PolicyAddOnSaveReq;
import com.maan.crm.req.PolicyDetailsSaveReq;
import com.maan.crm.req.PolicyAdditionalDetailsGetReq;
import com.maan.crm.req.PolicyAdditionalDetailsSaveReq;
import com.maan.crm.req.PolicyNomineeDetailsGetReq;
import com.maan.crm.req.PolicyNomineeDetailsSaveReq;
import com.maan.crm.req.PolicyPaymentDetailsGetReq;
import com.maan.crm.req.PolicyPaymentDetailsSaveReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.EndorsementFinancialDetailsRes;
import com.maan.crm.res.EndorsementGeneralDetailsRes;
import com.maan.crm.res.EndorsementPaymentDetailsRes;
import com.maan.crm.res.EndorsementSuccessRes;
import com.maan.crm.res.PolicyAddOnRes;
import com.maan.crm.res.PolicyDetailsRes;
import com.maan.crm.res.PolicyAdditionalDetailsRes;
import com.maan.crm.res.PolicyNomineeDetailsRes;
import com.maan.crm.res.PolicyPaymentDetailsRes;
import com.maan.crm.res.PolicySuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.EndorsementService;
import com.maan.crm.service.PolicyService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "ENDORSEMENT : Endorsement Details ", description = "API's")
@RequestMapping("/master")
public class EndorsementController {

	@Autowired
	private EndorsementService Service;

	@Autowired
	private CRMValidationService crmvalidation;

	@Autowired
	private PrintReqService reqPrinter;

	
	// Endorsement General Details Insert and Update

	@PostMapping(value = "/saveendorsement")
	@ApiOperation(value = "This method is to Save Endorsement General Details")

	// Insert
	public ResponseEntity<CommonCrmRes> saveEndorsement(@RequestBody EndorsementGeneralDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateEndorsementGeneral(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			EndorsementSuccessRes res = Service.saveEndorsementGeneral(req);
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

	//// Endorsement General Details Get Method

	@PostMapping("/getendorsementdetails")
	@ApiOperation(value = "This method is to Get Endorsement General Details")

	public ResponseEntity<CommonCrmRes> getEndorsementGeneral(@RequestBody EndorsementGeneralDetailsGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		 EndorsementGeneralDetailsRes res = Service.getEndorsementGeneral(req);
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

	
	// Endorsement Payment Details Insert and Update

		@PostMapping(value = "/saveendorsementpayment")
		@ApiOperation(value = "This method is to Save Endorsement Payment Details")

		// Insert
		public ResponseEntity<CommonCrmRes> saveEndorsementPayment(@RequestBody EndorsementPaymentDetailsSaveReq req) {

			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = Service.validateEndorsementPayment(req);
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				/////// save
				EndorsementSuccessRes res = Service.saveEndorsementPayment(req);
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

		//// Endorsement Payment Details Get Method

		@PostMapping("/getendorsementpayment")
		@ApiOperation(value = "This method is to Get Endorsement Payment Details")

		public ResponseEntity<CommonCrmRes> getEndorsementPayment(@RequestBody EndorsementPaymentDetailsGetReq req) {
			CommonCrmRes data = new CommonCrmRes();

			 EndorsementPaymentDetailsRes res = Service.getEndorsementPayment(req);
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

	
		// Endorsement Financial Details Insert and Update

				@PostMapping(value = "/saveendorsementfinancial")
				@ApiOperation(value = "This method is to Save Endorsement Financial Details")

				// Insert
				public ResponseEntity<CommonCrmRes> saveEndorsementFinancial(@RequestBody EndorsementFinancialDetailsSaveReq req) {

					reqPrinter.reqPrint(req);
					CommonCrmRes data = new CommonCrmRes();
					List<Error> validation = Service.validateEndorsementFinancial(req);
					if (validation != null && validation.size() != 0) {
						data.setCommonResponse(null);
						data.setIsError(true);
						data.setErrorMessage(validation);
						data.setMessage("Failed");
						return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

					} else {
						/////// save
						EndorsementSuccessRes res = Service.saveEndorsementFinancial(req);
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

				//// Endorsement Financial Details Get Method

				@PostMapping("/getendorsementfinancial")
				@ApiOperation(value = "This method is to Get Endorsement Financial Details")

				public ResponseEntity<CommonCrmRes> getEndorsementFinancial(@RequestBody EndorsementFinancialDetailsGetReq req) {
					CommonCrmRes data = new CommonCrmRes();

					 EndorsementFinancialDetailsRes res = Service.getEndorsementFinancial(req);
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
