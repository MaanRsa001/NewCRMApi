package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotorMakeSaveReq {

	@JsonProperty("MakeId")
	private Integer makeId;
	
	@JsonProperty("MakeNameEn")
	private String makeNameEn;
	
	@JsonProperty("AmendId")
	private Integer amendId;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	
	  
		
	  

}
