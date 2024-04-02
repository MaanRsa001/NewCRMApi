package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ClassTypeMasterGetAllReq;
import com.maan.crm.req.ClassTypeMasterGetReq;
import com.maan.crm.req.ClassTypeMasterSaveReq;
import com.maan.crm.res.ClassTypeMasterDropDownRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.util.error.Error;




public interface ClassTypeMasterService {

	List<DropDownRes> getClassTypeMasterDropdown();

	List<ClassTypeMasterDropDownRes> getAllClassTypeMaster(ClassTypeMasterGetAllReq req);

	ClassTypeMasterDropDownRes getClassTypeMasterById(ClassTypeMasterGetReq req);

	List<Error> validateClassTypeMaster(ClassTypeMasterSaveReq req);

	SuccessRes saveClassTypeMaster(ClassTypeMasterSaveReq req);

	List<DropDownRes> getPolicyTypeDropdown(String classTypeId);






	

	


}
