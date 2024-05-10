package com.maan.crm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotation_details")
@IdClass(QuotationDetailsId.class)
public class QuotationDetails {
	@Id
	@Column(name = "QUOTE_NO", nullable = false)
	private String quoteNo;

	@Id
	@Column(name = "CLIENT_ID", nullable = false, length = 100)
	private String clientId;

	@Column(name = "LEAD_ID")
	private Integer leadId;
	
	@Column(name = "PROSPECT_ID")
	private Integer prospectId;

	@Column(name = "POLICY_TYPE_ID")
	private Integer policyTypeId;

	@Column(name = "POLICY_TYPE", length = 100)
	private String policyType;

	@Column(name = "CLASS_ID")
	private Integer classId;

	@Column(name = "CLASS_DESC", length = 100)
	private String classDesc;

	@Column(name = "CLIENT_NAME", length = 100)
	private String clientName;

	@Column(name = "OLD_POLICY_NO", length = 100)
	private String oldPolicyNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "INSURER", length = 100)
	private String insurer;

	@Column(name = "INSURER_BRANCH", length = 100)
	private String insurerBranch;

	@Column(name = "INSURANCE_PLAN", length = 100)
	private String insurancePlan;

	@Column(name = "SUM_INSURED")
	private Double sumInsured;

	@Column(name = "DISCOUNT_PERCENT")
	private Double discountPercent;

	@Column(name = "NO_CLAIM_BONUS")
	private Double noClaimBonus;

	@Column(name = "COMMISSION_BASE_PREMIUM")
	private Double commissionBasePremium;

	@Column(name = "OTHER_PREMIUM")
	private Double otherPremium;

	@Column(name = "GST")
	private Double gst;

	@Column(name = "PREMIUM_WITH_GST")
	private Double premiumWithGst;

	@Column(name = "POLICY_ADDI_INFO", length = 100)
	private String policyAddiInfo;

	@Temporal(TemporalType.DATE)
	@Column(name = "ISSUE_DATE")
	private Date issueDate;

	@Column(name = "ISSUED_BY_USER", length = 100)
	private String issuedByUser;

	@Column(name = "RENEWAL_NOTICEFLAG_YN", length = 1)
	private Character renewalNoticeFlagYn;

	@Column(name = "RECOMMENDED_FLAG_YN", length = 1)
	private Character recommendedFlagYn;
}
