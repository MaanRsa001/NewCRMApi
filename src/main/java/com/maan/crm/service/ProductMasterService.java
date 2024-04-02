package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ProductMasterGetAllReq;
import com.maan.crm.req.ProductMasterGetReq;
import com.maan.crm.req.ProductMasterListSaveReq;
import com.maan.crm.res.ProductMasterGetAllRes;
import com.maan.crm.res.ProductMasterRes;
import com.maan.crm.res.ProductMasterViewRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

public interface ProductMasterService {

	// VALIDATE
	List<Error> validateProductMaster(ProductMasterListSaveReq req);

	//SAVE
	SuccessRes saveProductMaster(ProductMasterListSaveReq req);

	// Get 
	ProductMasterRes getProductMasterRes(ProductMasterGetReq req);

	// Get All
	List<ProductMasterGetAllRes> getallProductMaster(ProductMasterGetAllReq req);

	//View
	ProductMasterViewRes viewProductMaster(ProductMasterGetReq req);

}
