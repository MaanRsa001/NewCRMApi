package com.maan.crm.res;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class ProspectSearchRes  implements Serializable {

	@JsonProperty("ProspectCount")
	private Long prospectCount;
	
	@JsonProperty("ProspectDetails")
	private  List<CrmLeadRes> prospectDetails;
	
}
