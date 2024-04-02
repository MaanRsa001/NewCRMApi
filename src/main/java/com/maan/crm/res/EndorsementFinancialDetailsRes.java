package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class EndorsementFinancialDetailsRes {


	
	@JsonProperty("EndorsementId")
	private int endorsementId;
	

	@JsonProperty("EndorsmentFinancialId")
	private int endorsmentFinancialId;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("StartDate")
	private Date startDate;
	

	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
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
