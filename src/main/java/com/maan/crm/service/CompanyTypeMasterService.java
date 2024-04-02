package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.CompanyTypeMasterReq;
import com.maan.crm.res.CompanyTypeMasterRes;
import com.maan.crm.res.SuccessRes;

public interface CompanyTypeMasterService {

	List<CompanyTypeMasterRes> getall(CompanyTypeMasterReq req);

	CompanyTypeMasterRes get(CompanyTypeMasterReq req);

	SuccessRes insert(CompanyTypeMasterReq req);



}
