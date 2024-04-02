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
public class VehColorDropDownRes {

	
	@JsonProperty("ColorId")
	private int colorId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
	@JsonProperty ("ColorDesc")
	private String colorDesc;
	
	@JsonProperty("Status")
	private String status;
	

	@JsonProperty("CoreAppCode")
	private String coreAppCode;
	
	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("AmendId")
	private int amendId;
	


}
