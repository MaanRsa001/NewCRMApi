package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ProductDetailsGroupReq;
import com.maan.crm.res.ProductMasterGroupRes;
import com.maan.crm.util.error.Error;

public interface ProductDetailsService {

	ProductMasterGroupRes getProcutDetailsGroup(ProductDetailsGroupReq req);

}
