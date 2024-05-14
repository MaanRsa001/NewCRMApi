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
@IdClass(ProductCoverDetailsId.class)
@Table(name = "product_cover_details")
public class ProductCoverDetails {

    @Id
    @Column(name = "LEAD_ID")
    private String leadId;

    @Id
    @Column(name = "CLIENT_REF_NO")
    private String clientRefNo;

    @Id
    @Column(name = "Sequence_no")
    private int sequenceNo;

    @Id
    @Column(name = "COVER_ID")
    private int coverId;

    @Column(name = "COVER_NAME")
    private String coverName;

    @Column(name = "BUSINESSTYPE_Id")
    private Integer businessTypeId;

    @Column(name = "CLASS_ID")
    private String classId;

    @Column(name = "POLICYTYPE_ID")
    private Integer policyTypeId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATE_FACTOR")
    private String rateFactor;

    @Column(name = "SUM_INSURED")
    private Double sumInsured;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "PREMIUM")
    private Double premium;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name = "ENTRY_DATE")
    private Date entryDate;

    // Getters and setters
}

