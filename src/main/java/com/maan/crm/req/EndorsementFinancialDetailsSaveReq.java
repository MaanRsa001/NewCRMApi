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
public class EndorsementFinancialDetailsSaveReq {


	
	@JsonProperty("EndorsementId")
	private int endorsementId;
	

	@JsonProperty("EndorsmentFinancialId")
	private int endorsmentFinancialId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("StartDate")
	private Date startDate;
	

	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("ExpiryDate")
	private Date expiryDate;
	
	@JsonProperty("SumInsured")
	private Double SumInsured;
	
	
	@JsonProperty("CommissionBasePremium")
	private Double CommissionBasePremium;
	
	@JsonProperty("OtherPremium")
	private Double OtherPremium;

	

	@JsonProperty("TotalPremium")
	private Double TotalPremium;

	@JsonProperty("Gst")
	private Double gst;
	
	@JsonProperty("PremiumWithGst")
	private Double PremiumWithGst; 
	 
	
	 
	 
	 
	 
	
	  
	 
	 	 
	 
	 
	 
	
	 
	  
	 
	
	                                 
}
