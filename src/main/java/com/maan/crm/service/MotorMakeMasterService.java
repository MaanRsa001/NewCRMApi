package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.MotorMakeGetAllReq;
import com.maan.crm.req.MotorMakeGetReq;
import com.maan.crm.req.MotorMakeSaveReq;
import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;
import com.maan.crm.req.VehicleTypeMasterGetAllReq;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorMakeDropDownRes;
import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleTypeMasterRes;
import com.maan.crm.util.error.Error;


public interface MotorMakeMasterService {

	List<DropDownRes> getMotorMakeMasterDropdown();

	List<MotorMakeDropDownRes> getallMotorDropdown(MotorMakeGetAllReq req);

	MotorMakeDropDownRes getMakeId(MotorMakeGetReq req);

	List<Error> validateMakeMotor(MotorMakeSaveReq req);

	SuccessRes saveMakeMotor(MotorMakeSaveReq req);


}