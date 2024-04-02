package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.SourceMasterGetAllReq;
import com.maan.crm.req.SourceMasterGetReq;
import com.maan.crm.req.SourceMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SourceMasterDropDownRes;
import com.maan.crm.res.SourceMasterGetRes;
import com.maan.crm.res.SourceMasterSuccessRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface SourceMasterService {



	List<DropDownRes> getSourceDropdown();

	List<SourceMasterDropDownRes> getallSourceDropdown(SourceMasterGetAllReq req);

	List<Error> validateSource(SourceMasterSaveReq req);

	SuccessRes saveSource(SourceMasterSaveReq req);

	SourceMasterDropDownRes getSourceId(SourceMasterGetReq req);

	


}
