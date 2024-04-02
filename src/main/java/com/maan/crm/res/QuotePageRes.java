package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuotePageRes {

	@JsonProperty("QuoteCount")
	private Long quoteCount;

	@JsonProperty("QuoteList")
	private Object quoteList;
}
