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
@IdClass(PolicyAccountsDetailsId.class)
@Table(name = "POLICY_AMOUNTS_DETAILS")
public class PolicyAccountsDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	// --- ENTITY PRIMARY KEY
	@Id
	@Column(name = "POLICY_ACCID", nullable = false)
	private Integer policyAccId;

	@Id
	@Column(name = "POLICYID", nullable = false)
	private Integer policyid;

	// --- ENTITY DATA FIELDS

	@Column(name = "SUMINSURED")
	private Double sumInsured;

	@Column(name = "OWNDAMAGEPREMIUM")
	private Double ownDamagePremium;

	@Column(name = "DISCOUNTPERCENTAGE")
	private Double discountPercentage;

	@Column(name = "NOCLAIMBONUS")
	private Double noClaimBonus;

	@Column(name = "TOTALOWNDAMAGEPREMIUMA")
	private Double totalOwnDamagePremiumA;

	@Column(name = "TOTALADDONCOVERPREMIUMB")
	private Double totalAddOnCoverPremiumB;

	@Column(name = "ODADDONCOMMISSIONBASEPREMIUMAB")
	private Double oDAddOnCommissionBasePremiumAB;

	@Column(name = "BASICTP")
	private Double basicTP;

	@Column(name = "OWNERDRIVERPAYN", length = 200)
	private String ownerDriverPAYN;

	@Column(name = "BIFUELKITYN", length = 200)
	private String bifuelkitYN;

	@Column(name = "GEOGRAPHICALAREAYN", length = 200)
	private String geographicalAreaYN;

	@Column(name = "OWNERDRIVERPA")
	private Double ownerDriverPA;

	@Column(name = "BIFUELKIT")
	private Double bifuelkit;

	@Column(name = "GEOGRAPHICALAREA")
	private Double geographicalArea;
	
	@Column(name = "UNNAMEDPASSENGERPAYN",length=3)
	private String unnamedPassengerPAYN;

	@Column(name = "UNNAMEDPASSENGERPA")
	private Double unnamedPassengerPA;

	@Column(name = "UNNAMEDPASSENGERLIMITPERPERSON")
	private Double unnamedPassengerLimitPerPerson;

	@Column(name = "UNNAMEDPASSENGERNOOFSEATS")
	private Integer unnamedPassengerNoofSeats;

	@Column(name = "LLTOPAIDDRVEMP")
	private Double lLtoPaidDrvEmp;

	@Column(name = "LLTOPAIDDRVEMPYN", length = 3)
	private String lLtoPaidDrvEmpYn;

	@Column(name = "NOOFDRVEMP")
	private Integer noofDrvEmp;

	@Column(name = "TOTALLIABILITYPREMIUMC")
	private Double totalLiabilityPremiumC;

	@Column(name = "TOTALPREMIUMABC")
	private Double totalPremiumABC;

	@Column(name = "GST")
	private Double gst;
	
	@Column(name = "PREMIUMWITHGST")
	private Double PremiumwithGst;
	
	@Column(name = "COMMISSIONBASEPREMIUM")
	private Double commissionBasePremium;
	
	@Column(name = "PAPAIDDRV")
	private Double pAPaidDrv;

	@Column(name = "PAPAIDDRVYN", length = 100)
	private String pAPaidDrvYN;

	@Column(name = "PAPAIDDRVNOOFSEATS")
	private Integer pAPaidDrvNoofSeats;

	@Column(name = "PAPAIDDRVLIMITPERPERSON")
	private Double policyCancelledYnBy;

	@Column(name = "OTHERPREMIUM")
	private Double otherPremium;

	@Column(name = "TOTALPREMIUM")
	private Double totalPremium;

	@Column(name = "BASICPREMIUM")
	private Double basicPremium;
	
	@Column(name = "RIDERPREMIUM")
	private Double riderPremium;
	
	@Column(name = "FIRSTYEARGST")
	private Double firstYearGST;
	
	@Column(name = "FIRSTYEARPREMIUMWITHGST")
	private Double firstYearPremiumwithGST;
	
	@Column(name = "ANNUALIZEDPREMIUM")
	private Double annualizedPremium;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "STATUS", length = 3)
	private String status;
}
