package com.maan.crm.auth.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.maan.crm.auth.dto.ChangePasswordReq;
import com.maan.crm.auth.dto.LoginEncryptResponse;
import com.maan.crm.auth.dto.PaymentResUrlReq;
import com.maan.crm.auth.service.AuthendicationService;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.service.DropDownService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.Error;
import com.maan.crm.auth.service.LoginValidatedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(  tags="Basic Authentication", description = "API's.")
@RequestMapping("/basicauth")
public class BasicAuthController {

	@Autowired
	private DropDownService dropDownService;
	
	@Autowired
	private PrintReqService reqPrinter;
	
	private Logger log = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private AuthendicationService authservice;
	
	@Autowired
	private LoginValidatedService loginValidationComponent;
	
	
	@PostMapping("/getLoginEncryptResponse")   
	private LoginEncryptResponse getLoginEncryptResponse(@RequestBody PaymentResUrlReq request , HttpServletRequest http) {
		return authservice.getLoginEncryptResponse(request , http);
	}
	
	// Client Type
		@GetMapping("/usertypes")
		@ApiOperation(value = "This method is to User Types Drop Down")

		public ResponseEntity<CommonCrmRes> getUserTypes() {

			CommonCrmRes data = new CommonCrmRes();

			// Save
			List<DropDownRes> res = dropDownService.getUserTypes();
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
	
		
		
		@PostMapping("/changepassword")
		@ApiOperation(value="This method is to change Login Password")
		public ResponseEntity<CommonCrmRes> getChangePassword(@RequestBody ChangePasswordReq req) throws Exception {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();
			
			// Validation
			
			List<Error> validation = loginValidationComponent.LoginChangePasswordValidation(req);
			if(validation!= null && validation.size()!=0) {
				data.setCommonResponse(null);
				data.setIsError(true);
				data.setErrorMessage(validation);
				data.setMessage("Failed");
				
				return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);
			}
			else {
				// Save 
				
				CommonCrmRes res = authservice.LoginChangePasswordA(req);
				data.setCommonResponse(res);
				data.setIsError(false);
				data.setErrorMessage(Collections.emptyList());
				data.setMessage("Success");
				
				if(res!=null) {
					return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
				}
				else {
					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
				}
			}
		
		}
}
