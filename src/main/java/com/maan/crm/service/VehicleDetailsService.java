package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.VehicleDetailsGetAllReq;
import com.maan.crm.req.VehicleDetailsGetReq;
import com.maan.crm.req.VehicleDetailsSaveReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.VehicleDetailsGridRes;
import com.maan.crm.res.VehicleDetailsRes;
import com.maan.crm.util.error.Error;



public interface VehicleDetailsService {

	SuccessRes saveVehDetail(VehicleDetailsSaveReq req);

	VehicleDetailsRes getVehDetailsById(VehicleDetailsGetReq req);

	List<VehicleDetailsGridRes> getAllVehicleInfo(VehicleDetailsGetAllReq req);

	 List<Error> validateVehicle(VehicleDetailsSaveReq req);


}
