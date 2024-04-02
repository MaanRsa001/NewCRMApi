package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.InsuranceCompanyMasterGetAllReq;
import com.maan.crm.req.InsuranceCompanyMasterGetReq;
import com.maan.crm.req.InsuranceCompanyMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.InsuranceCompanyMasterRes;
import com.maan.crm.res.SuccessRes;

public interface InsuranceCompanyMasterService {

	// Drop down InsuranceCompanyMaster
	List<DropDownRes> getInsCompMasterDropDown();

	// Get All InsuranceCompanyMaster
	List<InsuranceCompanyMasterRes> getAllInsuranceCompanyMaster(InsuranceCompanyMasterGetAllReq req);

	// Get InsuranceCompanyMaster
	InsuranceCompanyMasterRes getInsuranceCompanyMasterById(InsuranceCompanyMasterGetReq req);

	// Save InsuranceCompanyMaster
	SuccessRes saveInsuranceCompanyMaster(InsuranceCompanyMasterSaveReq req);
}
