package com.maan.crm.service;

import java.util.List;

import com.maan.crm.document.req.OccupationMasterGetReq;
import com.maan.crm.req.OccupationMasterGetAllReq;
import com.maan.crm.req.OccupationSaveReq;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.OccupationDropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;


public interface OccupationMasterService {



	List<DropDownRes> getOccupationDropdown();

	List<OccupationDropDownRes> getallOccupationDropdown(OccupationMasterGetAllReq req);

	OccupationDropDownRes getOccupationId(OccupationMasterGetReq  req);

	List<Error> validateOccupation(OccupationSaveReq req);

	SuccessRes saveCrmCityMaster(OccupationSaveReq req);

	


}
