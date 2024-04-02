package com.maan.crm.res;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDetailsGetRes {

	@JsonProperty("Usertype")
	private String userType;
	
	@JsonProperty("UserTypeDetails")
	private List<LoginDetailsGetListRes> userTypeDetails;

		

	
}
