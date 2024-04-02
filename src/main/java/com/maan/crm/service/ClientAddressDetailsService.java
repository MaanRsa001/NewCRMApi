package com.maan.crm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.maan.crm.req.ClientAddressDetailsGetAllReq;
import com.maan.crm.req.ClientAddressDetailsGetReq;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
import com.maan.crm.req.ClientDetailsGetAllReq;
import com.maan.crm.req.ClientDetailsGetReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientAddressDetailsGetRes;
import com.maan.crm.res.ClientDetailsGetRes;
import com.maan.crm.res.ClientDetailsGridRes;
import com.maan.crm.res.CrmLeadSuccessRes;
import com.maan.crm.res.SuccessRes;


public interface ClientAddressDetailsService {

	List<ClientAddressDetailsGetRes> getAllClientAddressDetails(ClientAddressDetailsGetAllReq req);

	ClientAddressDetailsGetRes getClientAddressDetailsById(ClientAddressDetailsGetReq req);

	SuccessRes saveClientAddressDetails(ClientDetailsJsonTemReq req);




}
