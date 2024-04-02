package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManuYearMasterDropDownRes {

	
	@JsonProperty("Year")
	private int year;
	
	@JsonProperty("YearDes")
	private int yearDes;
	

	@JsonProperty("Status")
	private String status;
	

	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("AmendId")
	private int amendId;
	

	@JsonProperty("Remarks")
	private String remarks;
		

}
