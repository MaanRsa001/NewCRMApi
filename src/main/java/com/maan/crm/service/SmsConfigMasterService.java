package com.maan.crm.service;

import com.maan.crm.req.SmsConfigMasterReq;
import com.maan.crm.res.SmsConfigMasterRes;
import com.maan.crm.res.SuccessRes;

public interface SmsConfigMasterService {

	SmsConfigMasterRes get(SmsConfigMasterReq req);

	SuccessRes insert(SmsConfigMasterReq req);


}
