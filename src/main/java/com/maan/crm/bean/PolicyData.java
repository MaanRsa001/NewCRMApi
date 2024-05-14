package com.maan.crm.bean;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(PolicyDataId.class)
@Table(name = "policy_data")
public class PolicyData {

    @Id
    @Column(name = "Policy_Id", nullable = false)
    private String policyId;

    @Id
    @Column(name = "POLICY_NUMBER", nullable = false)
    private String policyNumber;

    @Id
    @Column(name = "Prospect_Id", nullable = false)
    private int prospectId;

    @Id
    @Column(name = "LEAD_ID", nullable = false, length = 100)
    private String leadId;

    @Id
    @Column(name = "ClientRefNo", nullable = false, length = 100)
    private String clientRefNo;

    @Column(name = "ClientName", length = 100)
    private String clientName;

    @Column(name = "BusinessType", length = 100)
    private String businessType;

    @Column(name = "Classs", length = 100)
    private String classs;

    @Column(name = "PolicyType", length = 100)
    private String policyType;

    @Column(name = "Insurer", length = 100)
    private String insurer;

    @Column(name = "InsurerBranch", length = 100)
    private String insurerBranch;

    @Column(name = "InsurancePlan", length = 200)
    private String insurancePlan;

    @Column(name = "CoInsurerYN", length = 20)
    private String coInsurerYN;

    @Column(name = "CoInsurerName", length = 300)
    private String coInsurerName;

    @Column(name = "ReinsurerYN", length = 20)
    private String reinsurerYN;

    @Column(name = "ReinsurerName", length = 100)
    private String reinsurerName;

    @Column(name = "VehicleRegNo", length = 50)
    private String vehicleRegNo;

    @Column(name = "CoverNoteNo")
    private Integer coverNoteNo;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "ExpiryDate")
    private Date expiryDate;

    @Column(name = "NextPremiumDueDate")
    private Date nextPremiumDueDate;

    @Column(name = "RenewableFlagYN", length = 2)
    private String renewableFlagYN;

    @Column(name = "SumInsured")
    private Double sumInsured;

    @Column(name = "OtherPremium")
    private Double otherPremium;

    @Column(name = "FinancialCharges")
    private Double financialCharges;

    @Column(name = "InstallmentYN", length = 10)
    private String installmentYN;

    @Column(name = "NoOfInstallments")
    private Integer noOfInstallments;

    @Column(name = "DeductibleAmount")
    private Double deductibleAmount;

    @Column(name = "DeductiblePercent")
    private Double deductiblePercent;

    @Column(name = "Describtion", length = 500)
    private String describtion;

    @Column(name = "TotalPremium")
    private Double totalPremium;

    @Column(name = "PremiumPaymentType", length = 200)
    private String premiumPaymentType;

    @Column(name = "PaymentRefNo", length = 100)
    private String paymentRefNo;

    @Column(name = "PaymentDate")
    private Date paymentDate;

    @Column(name = "BankName", length = 100)
    private String bankName;

    @Column(name = "PaymentAmount")
    private Double paymentAmount;

    @Column(name = "PaymentCollectedDate")
    private Date paymentCollectedDate;

    @Column(name = "PaymentRemarks", length = 500)
    private String paymentRemarks;

    @Column(name = "PremiumDueDate")
    private Date premiumDueDate;

    @Column(name = "Entry_Date")
    private Date entryDate;

    @Column(name = "CreatedBy", length = 100)
    private String createdBy;

    @Column(name = "Status", length = 10)
    private String status;

    @Column(name = "Ins_Company_Id", length = 100)
    private String insCompanyId;

    @Column(name = "Region_code", length = 10)
    private String regionCode;

    @Column(name = "Branch_Code", length = 10)
    private String branchCode;

    // Getters and setters for all fields
}
