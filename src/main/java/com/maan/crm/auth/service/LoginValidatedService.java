package com.maan.crm.auth.service;

import java.util.List;

import com.maan.crm.auth.dto.ChangePasswordReq;
import com.maan.crm.auth.dto.LoginRequest;
import com.maan.crm.req.InsertLoginMasterReq;
import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.util.error.Error;

public interface LoginValidatedService {

	CommonCrmRes loginInputValidation(LoginRequest mslogin);

	List<Error> LoginChangePwdValidation(ChangePasswordReq req);

	List<Error> InsertLoginValidation(InsertLoginMasterReq req);

	List<Error> LoginChangePasswordValidation(ChangePasswordReq req);

}
