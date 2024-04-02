package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.UsertypeMasterReq;
import com.maan.crm.res.SuccessRes;
import com.maan.crm.res.UsertypeMasterRes;

public interface UserTypeMasterService {

	List<UsertypeMasterRes> getall(UsertypeMasterReq req);

	UsertypeMasterRes get(UsertypeMasterReq req);

	SuccessRes insert(UsertypeMasterReq req);

}
