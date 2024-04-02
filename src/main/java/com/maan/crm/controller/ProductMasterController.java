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

import com.maan.crm.req.ClientViewReq;
import com.maan.crm.req.ProductMasterGetAllReq;
import com.maan.crm.req.ProductMasterGetReq;
import com.maan.crm.req.ProductMasterListSaveReq;
import com.maan.crm.res.ClientViewRes;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.ProductMasterGetAllRes;
import com.maan.crm.res.ProductMasterRes;
import com.maan.crm.res.ProductMasterViewRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.service.PrintReqService;
import com.maan.crm.service.ProductMasterService;
import com.maan.crm.util.error.Error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "PRODUCT MASTER : Product Master ", description = "API's")
@RequestMapping("/api")
public class ProductMasterController {

	@Autowired
	private ProductMasterService productService;

	@Autowired
	private PrintReqService reqPrinter;

	// Save Product master with list of product details
	@PostMapping("/saveproduct")
	@ApiOperation(value = "This method is to Save Product master with list of product details")
	public ResponseEntity<CommonCrmRes> saveProductMaster(@RequestBody ProductMasterListSaveReq req) {

		reqPrinter.reqPrint("Printer Request --->" + req);
		CommonCrmRes data = new CommonCrmRes();

		// Validation
		List<Error> validation = productService.validateProductMaster(req);
		if (validation != null && validation.size() != 0) {

			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(validation);
			data.setMessage("Failed");

			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.OK);

		} else {

			// Save
			SuccessRes res = productService.saveProductMaster(req);
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

	@PostMapping("/getproductmaster")

	@ApiOperation(value = "This method is to Get Product Master")

	public ResponseEntity<CommonCrmRes> getProductMaster(@RequestBody ProductMasterGetReq req) {

		reqPrinter.reqPrint(req);

		CommonCrmRes data = new CommonCrmRes();

		ProductMasterRes res = productService.getProductMasterRes(req);
		if (res != null) {
			data.setCommonResponse(res);
			data.setIsError(false);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Success");
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			data.setCommonResponse(null);
			data.setIsError(true);
			data.setErrorMessage(Collections.emptyList());
			data.setMessage("Failed");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// Get All

	@PostMapping("/getallproductmaster")
	public ResponseEntity<CommonCrmRes> getallProductMaster(@RequestBody ProductMasterGetAllReq req) {
		reqPrinter.reqPrint(req);
		CommonCrmRes data = new CommonCrmRes();

		// Get All
		List<ProductMasterGetAllRes> res = productService.getallProductMaster(req);
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

	// View Product Master
	@PostMapping("view/productdetails")
	@ApiOperation(value = "This method is to View Product Master By Product Id")

	public ResponseEntity<CommonCrmRes> viewProductMaster(@RequestBody ProductMasterGetReq req) {
		CommonCrmRes data = new CommonCrmRes();

		ProductMasterViewRes res = productService.viewProductMaster(req);
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
