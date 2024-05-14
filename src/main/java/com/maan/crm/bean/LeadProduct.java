package com.maan.crm.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(LeadProductId.class)
@Table(name = "lead_product")
public class LeadProduct {

    @Id
    @Column(name = "LEAD_ID")
    private String leadId;

    @Id
    @Column(name = "CLIENT_REF_NO")
    private String clientRefNo;

    @Column(name = "CLIENT_NAME")
    private String clientName;
    
    @Column(name="Ins_Company_Id")
    private String insCompanyId;

    @Column(name = "BUSINESSTYPE")
    private String businessType;

    @Column(name = "BUSINESSTYPE_Id")
    private Integer businessTypeId;

    @Column(name = "CLASS_DESC")
    private String classDesc;

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
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name = "LEAD_GEN_DATE")
    private Date leadGenDate;

    @Column(name = "ASSIGNTO_GROUP")
    private String assignToGroup;

    @Column(name = "ASSIGNTO_GROUP_ID")
    private Integer assignToGroupId;

    @Column(name = "ASSIGNTO_USER")
    private String assignToUser;

    @Column(name = "ASSIGNTO_USER_ID")
    private String assignToUserId;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "OLD_POLICY_NO")
    private String oldPolicyNo;

    @Column(name = "Created_By")
    private String createdBy;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name = "ENTRY_DATE")
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
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

}

