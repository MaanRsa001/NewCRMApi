package com.maan.crm.service;

import com.maan.crm.req.MailMasterReq;
import com.maan.crm.res.MailMasterRes;
import com.maan.crm.res.SuccessRes;

public interface MailMasterService {

	MailMasterRes get(MailMasterReq req);

	SuccessRes insert(MailMasterReq req);

}
