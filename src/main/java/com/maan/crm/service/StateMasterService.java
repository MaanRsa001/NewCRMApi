package com.maan.crm.service;

import java.util.List;


import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;

import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.DropDownRes;

import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface StateMasterService {

	List<StateMasterDropDownRes> getAllStateMaster(StateMasterGetAllReq req);

	List<DropDownRes> getStateMasterDropdown();

	StateMasterDropDownRes getStateMasterById(StateMasterGetReq req);

	List<Error> validateStateMaster(StateMasterSaveReq req);

	SuccessRes saveStateMaster(StateMasterSaveReq req);


}