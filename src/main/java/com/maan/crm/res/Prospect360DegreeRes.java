package com.maan.crm.res;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class Prospect360DegreeRes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	

	@JsonProperty("LeadId")
	private Integer leadId;
	
	@JsonProperty("ReferenceNo")
	private String ReferenceNo;
	
	@JsonProperty("QuoteNo")
	private Integer quoteNo;
	
	@JsonProperty("PolicyNo")
	private String PolicyNo;
	
	@JsonProperty("TotalPremium")
	private double totalRemium;
	

	@JsonProperty("CustomerId")
	private String customerId;
	
	@JsonProperty("ProductId")
	private Integer productId;
	

	@JsonProperty("Status")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;

	
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ClientType")
	private String clientType;
	
	@JsonProperty("UserType")
	private String userType;
	
	
	@JsonProperty("ClassId")
	private String classId;
	
	@JsonProperty("ClassDesc")
	private String classDesc;

	@JsonProperty("BusinessTypeId")
	private Integer businessTypeId;
	
	@JsonProperty("BusinessType")
	private String businessType;

	@JsonProperty("PolicyTypeId")
	private Integer policyTypeId;
	
	@JsonProperty("PolicyType")
	private String policyType;

}
