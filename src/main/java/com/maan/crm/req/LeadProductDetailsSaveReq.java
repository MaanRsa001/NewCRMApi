package com.maan.crm.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LeadProductDetailsSaveReq {
	
	@JsonProperty("SequenceNo")
	private int sequenceNo;
	
	@JsonProperty("CoverId")
	private int coverId;
	
	@JsonProperty("CoverName")
	private String coverName;
    
    @JsonProperty("RateFactor")
    private String rateFactor;

    @JsonProperty("SumInsured")
    private Double sumInsured;

    @JsonProperty("Rate")
    private Double rate;

    @JsonProperty("Premium")
    private Double premium;
    
    @JsonProperty("Description")
    private String description;

}
