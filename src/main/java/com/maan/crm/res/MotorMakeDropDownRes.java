package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MotorMakeDropDownRes {

	@JsonProperty("MAKEID")
	private int makeId;
	@JsonProperty("MAKENAMEEN")
	private String makeNameEn;
	@JsonProperty("STATUS")
	private String status;
	
	@JsonProperty("ENTRY_DATE")
	private Date entryDate;
	@JsonProperty("EFFECTIVE_DATE")
	private Date effectiveDate;
	@JsonProperty("AMEND_ID")
	private int amendId	;
	@JsonProperty("REMARKS")
	private String remarks;
	@JsonProperty("InsuranceId")
	private String insCompanyId;

}                                      
  
   