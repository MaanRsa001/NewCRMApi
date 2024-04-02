package com.maan.crm.req;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

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
public class VehicleTypeMasterSaveReq {

	@JsonProperty("VehTypeId")
	private Integer vehTypeId;
	
	@JsonProperty("VehicleName")
	private String vehTypeDesc;
	
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
