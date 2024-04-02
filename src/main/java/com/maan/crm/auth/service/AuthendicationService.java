package com.maan.crm.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.maan.crm.auth.dto.ChangePasswordReq;
import com.maan.crm.auth.dto.CommonLoginResponse;
import com.maan.crm.auth.dto.LoginEncryptResponse;
import com.maan.crm.auth.dto.LoginRequest;
import com.maan.crm.auth.dto.LogoutRequest;
import com.maan.crm.auth.dto.PaymentResUrlReq;
import com.maan.crm.req.InsertLoginMasterReq;

import com.maan.crm.req.LoginGetReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LoginGetRes;

import com.maan.crm.req.LoginDetailsGetReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.LoginDetailsGetRes;

import com.maan.crm.res.SuccessRes;

public interface AuthendicationService {

	CommonLoginResponse checkUserLogin(LoginRequest mslogin, HttpServletRequest http);

	CommonCrmRes LoginChangePassword(ChangePasswordReq req);

	CommonCrmRes logout(LogoutRequest mslogin);

	SuccessRes InsertLogin(InsertLoginMasterReq req);

	LoginEncryptResponse getLoginEncryptResponse(PaymentResUrlReq request, HttpServletRequest http);

	CommonCrmRes LoginChangePasswordA(ChangePasswordReq req);


	LoginGetRes getloginid(LoginGetReq req);

	List<LoginDetailsGetRes> getLogintDetails(LoginDetailsGetReq req);


}
