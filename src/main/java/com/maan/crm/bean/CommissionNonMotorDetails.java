package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CommissionNonMotorDetailsId.class)
@Table(name = "COMMISSION_NONMOTOR")
public class CommissionNonMotorDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	// --- ENTITY PRIMARY KEY

	@Id
	@Column(name = "COMMISSIONID", nullable = false)
	private Integer commissionId;

	@Id
	@Column(name = "AMENDID", nullable = false)
	private Integer amendId;

	@Id
	@Column(name = "POLICYID", nullable = false)
	private Integer policyId;

	// --- ENTITY DATA FIELDS

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "COMMPROCESSEDDATE", nullable = false)
	private Date commProcessedDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "COMMGENERATIONDATE", nullable = false)
	private Date commGenerationDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRYDATE", nullable = false)
	private Date entryDate;

	@Column(name = "COMMISSIONBASEPREMIUM")
	private Double commissionBasePremium;

	@Column(name = "COMMISSIONAMOUNTPER")
	private Double commissionAmountPer;

	@Column(name = "COMMISSIONAMOUNT")
	private Double commissionAmount;

	@Column(name = "FLATCOMMISSIONAMOUNT")
	private Double flatCommissionAmount;

	@Column(name = "TOTALCOMMISSIONAMOUNT")
	private Double totalCommissionAmount;

	@Column(name = "COMMISSIONGST")
	private Double commissionGST;

	@Column(name = "TOTALCOMMISSIONWITHGST")
	private Double totalCommissionWithGST;

	@Column(name = "PAYOUTBASEPREMIUM")
	private Double payoutBasePremium;

	@Column(name = "PAYOUTAMOUNTPER")
	private Double payoutAmountPer;

	@Column(name = "PAYOUTAMOUNT")
	private Double payoutAmount;

	@Column(name = "FLATPAYOUTAMOUNT")
	private Double flatPayoutAmount;

	@Column(name = "TOTALPAYOUTAMOUNT")
	private Double totalPayoutAmount;

	@Column(name = "PAYOUTGST")
	private Double payoutGST;

	@Column(name = "TOTALPAYOUTWITHGST")
	private Double totalPayoutWithGST;

}
