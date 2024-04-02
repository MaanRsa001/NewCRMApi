package com.maan.crm.req;

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
public class ClaimOtherDetailsGetReq {

	@JsonProperty("ClaimOtherDetailsId")
	private int claimOtherDetailsId;

	@JsonProperty("claimid")
	private int claimid;
}
