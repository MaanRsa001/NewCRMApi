package com.maan.crm.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prospect_product_cover")
@IdClass(ProspectProductCoverId.class)
public class ProspectProductCover {

    @Id
    @Column(name = "PROSPECT_ID", nullable = false)
    private String prospectId;

    @Id
    @Column(name = "LEAD_ID", nullable = false)
    private String leadId;

    @Id
    @Column(name = "CLIENT_REF_NO", nullable = false)
    private String clientRefNo;

    @Id
    @Column(name = "Sequence_no", nullable = false)
    private int sequenceNo;

    @Id
    @Column(name = "COVER_ID", nullable = false)
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
    @Column(name = "ENTRY_DATE")
    private Date entryDate;
}
