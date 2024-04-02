package com.maan.crm.res;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFollowUpDetailsRes implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Concluded")
	private List<FollowUpDetailsRes> Concluded;
	
	@JsonProperty("NotConcluded")
	private List<FollowUpDetailsRes> NotConcluded;
	
}
