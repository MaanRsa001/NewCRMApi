package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class ManuYearMasterSaveReq {

	@JsonProperty("Year")
	private int year;
	
	@JsonProperty("YearDes")
	private int yearDes;
	

	@JsonProperty("Status")
	private String status;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("AmendId")
	private int amendId;
	

	@JsonProperty("Remarks")
	private String remarks;
		


}
