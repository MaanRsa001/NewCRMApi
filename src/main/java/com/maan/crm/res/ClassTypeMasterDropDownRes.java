package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClassTypeMasterDropDownRes {
	
	@JsonProperty("ClassId")
    private String classId;  

	@JsonProperty("AmendId")
    private String amendId ;
    
    @JsonProperty("ClassName")
    private String    className ;

    @JsonProperty("EntryDate")
    private String    entryDate ;
    
    @JsonProperty("Status")
	private String status;
    
	@JsonProperty("EffectiveDate")
	private String effectiveDate;
	
	@JsonProperty("Remarks")
	private String remarks;
	

	@JsonProperty("InsCompanyId")
	private String insCompanyId;

}
