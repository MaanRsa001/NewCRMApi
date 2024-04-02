package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ManuYearMasterGetAllReq;
import com.maan.crm.req.ManuYearMasterGetReq;
import com.maan.crm.req.ManuYearMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.ManuYearMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface VehColorService {

	List<DropDownRes> getManuYearMasterDropdown();

	List<ManuYearMasterDropDownRes> getAllManuYearMaster(ManuYearMasterGetAllReq req);

	ManuYearMasterDropDownRes getManuYearMasterById(ManuYearMasterGetReq req);

	List<Error> validateManuYearMaster(ManuYearMasterSaveReq req);

	SuccessRes saveManuYearMaster(ManuYearMasterSaveReq req);




}