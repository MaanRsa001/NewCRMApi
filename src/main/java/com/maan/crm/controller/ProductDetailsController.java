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

import com.maan.crm.req.ProductDetailsGroupReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.ProductMasterGroupRes;
import com.maan.crm.service.CRMValidationService;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.ProductDetailsService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "PRODUCT DETAILS : Product Details", description = "API's")
@RequestMapping("/master")
public class ProductDetailsController {

	@Autowired
	private ProductDetailsService entityService;
	
	@Autowired
	private CRMValidationService validationService;
	@Autowired
	private PrintReqService reqPrinter;
	
	
	@PostMapping("/group/productdetails")
	public ResponseEntity<CommonCrmRes> getProcutDetailsGroup(@RequestBody ProductDetailsGroupReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();
		List<Error> validation = validationService.validateProductDetailsGroupReq(req);
		//// validation
		if (validation != null && validation.size() != 0) {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {
			/////// Get Product

			ProductMasterGroupRes res = entityService.getProcutDetailsGroup(req);
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
