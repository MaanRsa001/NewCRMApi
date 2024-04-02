package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.MotorMakeModelMasterDropDownReq;
import com.maan.crm.req.MotorMakeModelReq;
import com.maan.crm.req.MotorModelGetAllReq;
import com.maan.crm.req.MotorModelGetReq;
import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;
import com.maan.crm.req.VehColorGetReq;
import com.maan.crm.req.VehicleTypeMasterGetAllReq;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.MotorMakeModelRes;
import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleTypeMasterRes;
import com.maan.crm.util.error.Error;


public interface MotorMakeModelMasterService {

	List<DropDownRes> getMotorMakeMasterModelDropdown(MotorMakeModelMasterDropDownReq req);

	List<MotorMakeModelRes> getAllColor(MotorModelGetAllReq req);

	MotorMakeModelRes getColorId(MotorModelGetReq req);

	List<Error> validateMotorModel(MotorMakeModelReq req);

	SuccessRes saveMotorModel(MotorMakeModelReq req);

}