package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SourceMasterGetAllRes {

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

}
