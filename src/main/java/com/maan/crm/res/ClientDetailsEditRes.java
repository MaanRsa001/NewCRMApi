package com.maan.crm.res;

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
public class ClientDetailsEditRes {
	
	@JsonProperty("ClientRefNo")
    private String clientRefNo;

    @JsonProperty("Crno")
    private String crno;

    @JsonProperty("ClientType")
    private String clientType;

    @JsonProperty("ClientTypeId")
    private Integer clientTypeId;

    @JsonProperty("ClientName")
    private String clientName;

    @JsonProperty("GstIn")
    private String gstIn;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("InsCompanyId")
    private String insCompanyId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("TitleId")
    private String titleId;

    @JsonProperty("Gender")
    private String gender;

    @JsonProperty("GenderId")
    private Integer genderId;

    @JsonProperty("DateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date dateOfBirth;

    @JsonProperty("Age")
    private Integer age;

    @JsonProperty("MatirialStatus")
    private String matirialStatus;

    @JsonProperty("MaritalStatusId")
    private Integer maritalStatusId;

    @JsonProperty("DateOfAnniversary")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date dateOfAnniversary;

    @JsonProperty("OccupationId")
    private Integer occupationId;

    @JsonProperty("Occupation")
    private String occupation;

    @JsonProperty("Designation")
    private String designation;

    @JsonProperty("AnnualIncome")
    private String annualIncome;

    @JsonProperty("AnnualIncomeId")
    private Integer annualIncomeId;

    @JsonProperty("PassportNo")
    private String passportNo;

    @JsonProperty("Dmatid")
    private String dmatid;

    @JsonProperty("DefaultUser")
    private String defaultUser;

    @JsonProperty("DefaultUserId")
    private Integer defaultUserId;

    @JsonProperty("IsGroupClient")
    private String isGroupClient;

    @JsonProperty("IsGroupClientId")
    private Integer isGroupClientId;

    @JsonProperty("GroupClientId")
    private Integer groupClientId;

    @JsonProperty("GroupClient")
    private String groupClient;

    @JsonProperty("Pos")
    private String pos;

    @JsonProperty("PosId")
    private Integer posId;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("SourceId")
    private Integer sourceId;

    @JsonProperty("ReferenceName")
    private String referenceName;

    @JsonProperty("ReferenceNameId")
    private Integer referenceNameId;

    @JsonProperty("WillProvideRefId")
    private Integer willProvideRefId;

    @JsonProperty("WillProvideReference")
    private String willProvideReference;

    @JsonProperty("EntryDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date entryDate;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("CompanyType")
    private String companyType;

    @JsonProperty("CompanyTypeId")
    private Integer companyTypeId;

    @JsonProperty("BranchCode")
    private String branchCode;

    @JsonProperty("RegionCode")
    private String regionCode;

    @JsonProperty("AddressYn")
    private String addressYn;

    @JsonProperty("UserType")
    private String userType;

    @JsonProperty("Statuss")
    private String statuss;

    @JsonProperty("StatusDescription")
    private String statusDescription;

    @JsonProperty("MobileNumber")
    private String mobileNumber;

    @JsonProperty("AlternativeNumber")
    private String alternativeNumber;

    @JsonProperty("EmailId")
    private String emailId;

    @JsonProperty("ReferencerDetailsYn")
    private String referencerDetailsYn;

    @JsonProperty("ReferencerMobile")
    private String referencerMobile;

    @JsonProperty("ReferencerEmail")
    private String referencerEmail;

    @JsonProperty("YourReferenceName")
    private String yourReferenceName;

    @JsonProperty("YourReferenceMailid")
    private String yourReferenceMailid;

    @JsonProperty("YourReferenceMobile")
    private String yourReferenceMobile;

    @JsonProperty("LastVisitedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date lastVisitedDate;
}
