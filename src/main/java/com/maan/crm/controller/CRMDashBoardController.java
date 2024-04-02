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

import com.maan.crm.req.ClientDetailsSaveReq;
import com.maan.crm.req.CrmClientSuccessRes;
import com.maan.crm.res.ActiveUsersCountRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DashBoardCardsDataCountRes;
import com.maan.crm.res.DashBoardQuoteStatusCountRes;
import com.maan.crm.res.DashBoardTotalCountRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.NewlyJoinedUsersCountRes;
import com.maan.crm.res.RevenueTotalCountRes;
import com.maan.crm.service.CRMDashBoardService;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "DASH BOARD : Dash Board ", description = "API's")
@RequestMapping("/dashboard")
public class CRMDashBoardController {
	
		@Autowired
		private CRMDashBoardService entityService ;

		@Autowired
		private CRMValidationService validationService;

		@Autowired
		private PrintReqService reqPrinter;
	
		// Dashboard Graph Api
		@PostMapping("/getcardsdata")
		@ApiOperation(value = "This method is to Get Active Client, Lead, Prospect, Policy Counts")
		public ResponseEntity<CommonCrmRes> getCardsDataCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				DashBoardCardsDataCountRes res = entityService.getCardsDataCount(req);
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
		
		
		// Dashboard Graph Api
		@PostMapping("/gettotalcounts")
		@ApiOperation(value = "This method is to Get Active Client, Lead, Prospect, Policy Counts")
		public ResponseEntity<CommonCrmRes> getTotalDataCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				DashBoardTotalCountRes res = entityService.getTotalDataCount(req);
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
		
		// Dashboard Graph Api
		@PostMapping("/getactiveuserscount")
		@ApiOperation(value = "This method is to Get Active Client, Lead, Prospect, Policy Counts")
		public ResponseEntity<CommonCrmRes> getActiveUsersCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				ActiveUsersCountRes res = entityService.getActiveUsersCount(req);
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
		
		// Dashboard 
		@PostMapping("/getnewuserscount")
		@ApiOperation(value = "This method is to Get Active Client, Lead, Prospect, Policy Counts")
		public ResponseEntity<CommonCrmRes> getNewlyJoinedUsersCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Get
				NewlyJoinedUsersCountRes res = entityService.getNewlyJoinedUsersCount(req);
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
		//*********************************************REVENUE*************************************\\
		// Dashboard Graph Api
		@PostMapping("/getrevenue")
		@ApiOperation(value = "This method is to Get Total Revenue , Next Possibity Revenue  Counts")
		public ResponseEntity<CommonCrmRes> getRevenueTotalCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				RevenueTotalCountRes res = entityService.getRevenueTotalCount(req);
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
		//*********************************************COUNT*************************************\\
		@PostMapping("/getquotestatuscounts")
		@ApiOperation(value = "This method is to Get Bussiness Converted,Bussiness Lost,Followup Counts")
		public ResponseEntity<CommonCrmRes> getQuoteStatusCount(@RequestBody DashBoardCardsDataCountReq req) {

			reqPrinter.reqPrint("Printer Request --->" + req);
			CommonCrmRes data = new CommonCrmRes();

			// Validation
			List<Error> validation = validationService.validateCardsDataCount(req);
			if (validation != null && validation.size() != 0) {

				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");

				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

			} else {
				// Save
				DashBoardQuoteStatusCountRes res = entityService.getTotalQuoteStatusCount(req);
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
