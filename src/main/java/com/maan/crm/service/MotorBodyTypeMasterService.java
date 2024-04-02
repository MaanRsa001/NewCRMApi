package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.MotorBodyGetReq;
import com.maan.crm.req.MotorBodySaveReq;
import com.maan.crm.req.MotorBodyTypeGetAllReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorBodyDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;

public interface MotorBodyTypeMasterService {

	List<DropDownRes> getMotorBodyTypeMasterDropdown();

	List<MotorBodyDropDownRes> getallMotorBodyType(MotorBodyTypeGetAllReq req);

	MotorBodyDropDownRes getBodyId(MotorBodyGetReq req);

	List<Error> validateMotorBody(MotorBodySaveReq req);

	SuccessRes saveMotorBody(MotorBodySaveReq req);


}