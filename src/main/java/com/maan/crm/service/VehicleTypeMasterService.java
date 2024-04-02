package com.maan.crm.service;

import java.util.List;


import com.maan.crm.req.StateMasterGetAllReq;
import com.maan.crm.req.StateMasterGetReq;
import com.maan.crm.req.StateMasterSaveReq;
import com.maan.crm.req.VehicleTypeMasterGetAllReq;
import com.maan.crm.req.VehicleTypeMasterGetReq;
import com.maan.crm.req.VehicleTypeMasterSaveReq;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.DropDownRes;

import com.maan.crm.res.StateMasterDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleTypeMasterRes;
import com.maan.crm.util.error.Error;


public interface VehicleTypeMasterService {

	//Dropdown
	List<DropDownRes> getVehicalTypeMasterDropdown();
	
	//GetAll
	List<VehicleTypeMasterRes> getAllVehicleTypeMaster(VehicleTypeMasterGetAllReq req);

	//Get
	VehicleTypeMasterRes getVehicleTypeMasterById(VehicleTypeMasterGetReq req);

	//Save
	SuccessRes saveVehicleTypeMaster(VehicleTypeMasterSaveReq req);

}