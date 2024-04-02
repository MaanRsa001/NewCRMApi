package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehicleTypeMasterRes {

	@JsonProperty("VehTypeId")
	private Integer vehTypeId;
	
	@JsonProperty("VehicleName")
	private String vehTypeDesc;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	
	@JsonProperty("AmendId")
	private Integer amendId;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("InsuranceId")
	private String insCompanyId;

}
