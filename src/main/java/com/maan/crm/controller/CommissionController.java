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

import com.maan.crm.req.CommissionMotorDetailsGetReq;
import com.maan.crm.req.CommissionMotorDetailsSaveReq;
import com.maan.crm.req.CommissionNonMotorDetailsGetReq;
import com.maan.crm.req.CommissionNonMotorDetailsSaveReq;
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
import com.maan.crm.res.CommissionMotorDetailsRes;
import com.maan.crm.res.CommissionNonMotorDetailsRes;
import com.maan.crm.res.CommissionSuccessRes;
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
import com.maan.crm.service.CommissionService;
import com.maan.crm.service.EndorsementService;
import com.maan.crm.service.PolicyService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "COMMISSION : Commission Details ", description = "API's")
@RequestMapping("/master")
public class CommissionController {

	@Autowired
	private CommissionService Service;

	@Autowired
	private CRMValidationService crmvalidation;

	@Autowired
	private PrintReqService reqPrinter;

	
	// Commission Motor Details Insert and Update

	@PostMapping(value = "/savecommissionmotor")
	@ApiOperation(value = "This method is to Save Commission Motor Details")

	// Insert
	public ResponseEntity<CommonCrmRes> saveCommissionMotor(@RequestBody CommissionMotorDetailsSaveReq req) {

		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = Service.validateCommissionMotor(req);
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save
			CommissionSuccessRes res = Service.saveCommissionMotor(req);
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

	// Commission Motor Get Method

	@PostMapping("/getcommissionmotor")
	@ApiOperation(value = "This method is to Get Commission Motor")

	public ResponseEntity<CommonCrmRes> getCommissionMotor(@RequestBody CommissionMotorDetailsGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		 CommissionMotorDetailsRes res = Service.getCommissionMotor(req);
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

	
	// Endorsement Non Motor Insert and Update

		@PostMapping(value = "/savecommissionnonmotor")
		@ApiOperation(value = "This method is to Save Commission Non Motor Details")

		// Insert
		public ResponseEntity<CommonCrmRes> saveCommissionNonMotor(@RequestBody CommissionNonMotorDetailsSaveReq req) {

			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			List<Error> validation = Service.validateCommissionNonMotor(req);
			if (validation != null && validation.size() != 0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				/////// save
				CommissionSuccessRes res = Service.saveCommissionNonMotor(req);
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

		//  Commission Non Motor Get Method

		@PostMapping("/getcommissionnonmotor")
		@ApiOperation(value = "This method is to Get Commission Non Motor")

		public ResponseEntity<CommonCrmRes> getCommissionNonMotor(@RequestBody CommissionNonMotorDetailsGetReq req) {
			CommonCrmRes data = new CommonCrmRes();

			 CommissionNonMotorDetailsRes res = Service.getCommissionNonMotor(req);
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
