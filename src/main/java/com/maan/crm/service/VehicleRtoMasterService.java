package com.maan.crm.service;

import java.util.List;

import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.req.VehicleRTOGetAllReq;
import com.maan.crm.req.VehicleRTOMasterGetReq;
import com.maan.crm.req.VehicleRTOMasterSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleRTODropDownRes;
import com.maan.crm.util.error.Error;


public interface VehicleRtoMasterService {



	List<DropDownRes> getVehicleDropdown();

	List<VehicleRTODropDownRes> getallVehicleDropdown(VehicleRTOGetAllReq req);

	VehicleRTODropDownRes getVehicleRto(VehicleRTOMasterGetReq req);

	List<Error> validateVehicleRTO(VehicleRTOMasterSaveReq req);

	SuccessRes saveVehicleRTO(VehicleRTOMasterSaveReq req);

	
	


}
