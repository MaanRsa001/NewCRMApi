package com.maan.crm.req;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProspectQuotationAddOnSaveReq implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@JsonProperty("ProspectId")
	private int prospectId;

	@JsonProperty("AddOnid")
	private int addOnid;

	@JsonProperty("AddOnsOpted")
	private String addOnsOpted;

	@JsonProperty("AddOnsNotOpted")
	private String addOnsNotOpted;

}
