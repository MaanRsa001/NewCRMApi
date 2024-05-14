package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ProspectProductId.class)
@Table(name = "prospect_product")
public class ProspectProduct {

    @Id
    @Column(name = "PROSPECT_ID", nullable = false, length = 50)
    private String prospectId;

    @Id
    @Column(name = "LEAD_ID", nullable = false, length = 100)
    private String leadId;

    @Id
    @Column(name = "CLIENT_REF_NO", nullable = false, length = 100)
    private String clientRefNo;

    @Column(name = "CLIENT_NAME", length = 100)
    private String clientName;

    @Column(name = "Ins_Company_Id", length = 100)
    private String insCompanyId;

    @Column(name = "BUSINESSTYPE", length = 100)
    private String businessType;

    @Column(name = "CLASS_DESC", length = 100)
    private String classDesc;

    @Column(name = "POLICYTYPE", length = 100)
    private String policyType;

    @Column(name = "Created_By", length = 100)
    private String createdBy;

    @Column(name = "ENTRY_DATE")
    private Date entryDate;

    @Column(name = "REMARKS", length = 100)
    private String remarks;

    @Column(name = "STATUS", length = 10)
    private String status;

    @Column(name = "Branch_Code", length = 10)
    private String branchCode;

    @Column(name = "Region_Code", length = 10)
    private String regionCode;

    @Column(name = "UPDATED_BY", length = 100)
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
}
