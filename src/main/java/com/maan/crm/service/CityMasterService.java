package com.maan.crm.service;

import java.util.List;

import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.req.CityMasterGetAllReq;
import com.maan.crm.req.CityMasterGetReq;
import com.maan.crm.req.CityMasterSaveReq;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.res.CityMasterRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface CityMasterService {



	List<DropDownRes> getCityDropdown(Integer stateCode);

	List<CityMasterRes> getallCity(CityMasterGetAllReq req);

	CityMasterRes getCityId(CityMasterGetReq req);

	List<Error> validateCity(CityMasterSaveReq req);

	SuccessRes saveCityMaster(CityMasterSaveReq req);

	


}
