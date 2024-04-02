package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

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
public class PolicyTypeRes {
 
	@JsonProperty("PolicyTypeId")
    private String policytypeId;
    
    @JsonProperty("EffectiveDate")
    private String effectiveDate;
 
    @JsonProperty( "AmendId")
    private String amendId ;
    
    @JsonProperty("ClassId")
    private String    classId ;

    @JsonProperty("PolicyTypeName")
    private String    policytypeName ;


    @JsonProperty("EntryDate")
    private String entryDate ;

    @JsonProperty("Status")
    private String    status;

    
    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("InsuranceId")
    private String insCompanyId;
	
}
