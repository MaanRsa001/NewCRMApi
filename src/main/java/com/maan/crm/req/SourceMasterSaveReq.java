package com.maan.crm.req;

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
public class SourceMasterSaveReq {

	@JsonProperty("SourceId")
	private String sourceId;
	@JsonProperty("SourceName")
	private String sourceName;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("EffectiveDate")
	private String effectiveDate;
	@JsonProperty("AmendId")
	private String amendId;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("SearchYn")
	private String searchYn;	
	@JsonProperty("ReferenceDetailsYn")
	private String referenceDetailsYn;
}
