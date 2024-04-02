package com.maan.crm.auth.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.auth.dto.ChangePasswordReq;
import com.maan.crm.auth.dto.CommonLoginResponse;
import com.maan.crm.auth.dto.LoginEncryptResponse;
import com.maan.crm.auth.dto.LoginRequest;
import com.maan.crm.auth.dto.LogoutRequest;
import com.maan.crm.auth.dto.PaymentResUrlReq;
import com.maan.crm.auth.service.AuthendicationService;
import com.maan.crm.auth.service.LoginValidatedService;
import com.maan.crm.req.InsertLoginMasterReq;

import com.maan.crm.req.LoginGetReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LoginGetRes;

import com.maan.crm.req.LoginDetailsGetReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LoginDetailsGetRes;

import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.util.error.CommonValidationException;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(  tags="LOGIN : Login Token Creation", description = "API.")
@RequestMapping("/authentication")
public class LoginController {
	
	@Autowired
	private AuthendicationService authservice;
	@Autowired
	private LoginValidatedService loginValidationComponent;
	@Autowired
	private PrintReqService reqPrinter;

	@PostMapping("/login")
	@ApiOperation(value="This method is to Create Token For Access Other Apis")
	public CommonLoginResponse getloginToken(@RequestBody LoginRequest mslogin, HttpServletRequest http)  {
		CommonLoginResponse res = new CommonLoginResponse();
		CommonCrmRes crmValidation =loginValidationComponent.loginInputValidation(mslogin); 
		if(!CollectionUtils.isEmpty(crmValidation.getErrorMessage() )) {
			res.setErrors(crmValidation.getErrorMessage() );
			res.setChangePasswordYn(crmValidation.getChangePasswordYn() );
			return res;
		}
		return authservice.checkUserLogin(mslogin,http);
	}
	
	@PostMapping("/changepassword")
	@ApiOperation(value="This method is to Change Login Password")
	public CommonCrmRes getChangePwd(@RequestBody ChangePasswordReq req) throws Exception {
		
		CommonCrmRes res = new CommonCrmRes();
		
		List<Error> error = loginValidationComponent.LoginChangePwdValidation(req);
		if (error != null && error.size() > 0) {
			throw new CommonValidationException(error, null);
		}
		res = authservice.LoginChangePassword(req);
		return res;
	}
    
	@PostMapping("/logout")
	@ApiOperation(value="This method is used to Logout From Screen")
	public CommonCrmRes logout(@RequestBody LogoutRequest mslogin)  {		
		return authservice.logout(mslogin);
	}
	
 
	

@PostMapping("/getbyloginid")
@ApiOperation("This method is to get by loginid")
public ResponseEntity<CommonCrmRes> getloginid(@RequestBody LoginGetReq req){
	CommonCrmRes data = new CommonCrmRes();
	LoginGetRes res = authservice.getloginid(req);
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


	
	
	//Login Creation 
	@PostMapping("/insertlogin")
	@ApiOperation(value="This method is used to Create Login")
	public ResponseEntity<CommonCrmRes> Insertlogin(@RequestBody InsertLoginMasterReq req) throws CommonValidationException  {		
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = loginValidationComponent.InsertLoginValidation(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// save

			SuccessRes res =  authservice.InsertLogin(req);
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

	
	




	// Get Login Details
		@PostMapping("/getlogindetails")
		@ApiOperation(value = "This method is to Get Login Details")
		public ResponseEntity<CommonCrmRes> getLoginDetails(@RequestBody LoginDetailsGetReq req) {
			reqPrinter.reqPrint(req);
			CommonCrmRes data = new CommonCrmRes();

			// Get All
			List<LoginDetailsGetRes> res = authservice.getLogintDetails(req);
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
