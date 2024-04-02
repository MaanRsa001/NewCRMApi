package com.maan.crm.req;

import java.io.Serializable;
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

public class UsertypeMasterReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("UsertypeId")
	private Integer usertypeId;

	@JsonProperty("companyId")
	private String companyId;

	@JsonProperty("UsertypeDescription")
	private String usertypeDescription;

	@JsonProperty("Status")
	private String status;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
}
