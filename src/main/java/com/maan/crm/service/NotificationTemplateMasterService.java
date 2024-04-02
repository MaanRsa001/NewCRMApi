package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.NotifTemplateMasterReq;
import com.maan.crm.res.NotifTemplateMasterRes;
import com.maan.crm.res.SuccessRes;

public interface NotificationTemplateMasterService {

	List<NotifTemplateMasterRes> getall(NotifTemplateMasterReq req);

	NotifTemplateMasterRes get(NotifTemplateMasterReq req);

	SuccessRes insert(NotifTemplateMasterReq req);

}
