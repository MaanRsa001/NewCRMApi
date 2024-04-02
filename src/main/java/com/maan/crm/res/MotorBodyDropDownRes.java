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
public class MotorBodyDropDownRes {

	@JsonProperty("BODYID")
	private int bodyId;
	@JsonProperty("BODYNAMEEN")
	private String bodyNameEn;
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
	@JsonProperty("COMPANYID")
	private String companyId;

	@JsonProperty("SEATING_CAPACITY")
	private int seatingCapacity	;
	
	@JsonProperty("TONNAGE")
	private int tonnage	;
	
	@JsonProperty("CYLINDERS")
	private int cylinders	;
	

	@JsonProperty("InsuranceId")
	private String insCompanyId;
}                                      
  
   