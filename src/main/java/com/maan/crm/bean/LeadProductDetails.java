package com.maan.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@IdClass(LeadProductDetailsId.class)
@Table(name = "lead_product_details")
public class LeadProductDetails {

    @Id
    @Column(name = "LEAD_ID")
    private String leadId;

    @Id
    @Column(name = "CLIENT_REF_NO")
    private String clientRefNo;

    @Id
    @Column(name = "Sequence_no")
    private int sequenceNo;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "BUSINESSTYPE")
    private String businessType;

    @Column(name = "BUSINESSTYPE_Id")
    private Integer businessTypeId;

    @Column(name = "CLASS_DESC")
    private String classDescription;

    @Column(name = "CLASS_ID")
    private String classId;

    @Column(name = "POLICYTYPE")
    private String policyType;

    @Column(name = "POLICYTYPE_ID")
    private Integer policyTypeId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "SOURCE_ID")
    private Integer sourceId;

    @Column(name = "LEAD_GEN_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date leadGenDate;

    @Column(name = "ASSIGNTO_GROUP")
    private String assignToGroup;

    @Column(name = "ASSIGNTO_GROUP_ID")
    private Integer assignToGroupId;

    @Column(name = "ASSIGNTO_USER")
    private String assignToUser;

    @Column(name = "ASSIGNTO_USER_ID")
    private String assignToUserId;

    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date dueDate;

    @Column(name = "RATE_FACTOR")
    private String rateFactor;

    @Column(name = "SUM_INSURED")
    private Double sumInsured;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "PREMIUM")
    private Double premium;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OLD_POLICY_NO")
    private String oldPolicyNo;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "ENTRY_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date entryDate;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "Branch_Code")
    private String branchCode;

    @Column(name = "Region_Code")
    private String regionCode;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    private Date updatedDate;
}
