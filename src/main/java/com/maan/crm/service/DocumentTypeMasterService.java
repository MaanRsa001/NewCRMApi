package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.DocumentTypeMasterReq;
import com.maan.crm.res.DocumentTypeMasterRes;
import com.maan.crm.res.SuccessRes;

public interface DocumentTypeMasterService {

	List<DocumentTypeMasterRes> getall(DocumentTypeMasterReq req);

	DocumentTypeMasterRes get(DocumentTypeMasterReq req);

	SuccessRes insert(DocumentTypeMasterReq req);

}
