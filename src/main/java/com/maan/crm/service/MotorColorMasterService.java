package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.VehColorGetAllReq;
import com.maan.crm.req.VehColorGetReq;
import com.maan.crm.req.VehicleColorSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehColorDropDownRes;
import com.maan.crm.util.error.Error;

public interface MotorColorMasterService {

	List<DropDownRes> getMotorColorMasterDropDown();

	List<VehColorDropDownRes> getAllColor(VehColorGetAllReq req);

	VehColorDropDownRes getColorId(VehColorGetReq req);

	List<Error> validateVehColor(VehicleColorSaveReq req);

	SuccessRes saveVehColor(VehicleColorSaveReq req);


}