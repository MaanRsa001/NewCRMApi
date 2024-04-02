package com.maan.crm.service;

import java.util.List;

import com.maan.crm.req.ClientCorContactGetAllReq;
import com.maan.crm.req.ClientCrConGetReq;
import com.maan.crm.req.ClientCrConListSaveReq;
import com.maan.crm.req.ClientCrContactGetAllReq;
import com.maan.crm.req.ClientDetailsJsonTemReq;
import com.maan.crm.res.ClientCorContactListGeAlltRes;
import com.maan.crm.res.ClientCrContactGetRes;
import com.maan.crm.res.SuccessRes;


public interface ClientCorContactService {

	SuccessRes saveClientCorContact(ClientDetailsJsonTemReq req);

	ClientCrContactGetRes getClientCorContactById(ClientCrConGetReq req);

	List<ClientCrContactGetRes> getAllClientCorContact(ClientCrContactGetAllReq req);

}
