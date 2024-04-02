package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.PolicyMasterGetAllReq;
import com.maan.crm.req.PolicyMasterGetReq;
import com.maan.crm.req.PolicyTypeMasterSaveReq;
import com.maan.crm.res.PolicyTypeRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

public interface PolicyTypeMasterService {

	List<Error> validatePolicyTypeMaster(PolicyTypeMasterSaveReq req);

	SuccessRes savePolicyTypeMaster(PolicyTypeMasterSaveReq req);

	List<PolicyTypeRes> getallpolicy(PolicyMasterGetAllReq req);

	PolicyTypeRes getpolicyid(PolicyMasterGetReq req);

}
