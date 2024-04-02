package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="old_policy_details")
public class OldPolicyDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="OLD_POLICY_NO", length = 100)
	    private String     oldPolicyNo ;

	    //--- ENTITY DATA FIELDS 	    
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="OLD_START_DATE", nullable=false)
	    private Date       oldStartDate ;

	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="OLD_EXPIRY_DATE", nullable=false)
	    private Date       oldExpiryDate ;

	    
	    @Column(name="OLD_SUM_INSURED", nullable=false)
	    private Double     oldSumInsured ;

	    @Column(name="OLDNO_CLAIM_BONUS", nullable=false)
	    private Double     oldnoClaimBonus ;
	    
	    @Column(name="OLD_DISCOUNT_PERCENT", nullable=false)
	    private Integer     oldDiscountPercent ;
	    
	    @Column(name="OLD_COMMIS_BASE_PREMIUM", nullable=false)
	    private Double     oldComisBasePremium ;
	    
	    @Column(name="OLD_OTHER_PREMIUM", nullable=false)
	    private Double     oldOtherPremium ;
	    
	    @Column(name="OLD_TOTAL_PREMIUM", nullable=false)
	    private Double     oldTotalPremium ;
	    
	    @Column(name="OLD_GST", nullable=false)
	    private Double     oldGst ;
	    
	    @Column(name="OLD_TOTALPREMIUM_WITHGST", nullable=false)
	    private Double     oldTotalpremiumWithgst ;
	    	    
	    @Column(name="OLD_POLICY_ADDI_INFO", length = 100)
	    private String oldPolicyAddiInfo ;
	    
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;

	    @Column(name="COMPANY_ID")
	    private String companyId ;
	    
	    @Column(name="REGION_CODE")
	    private String regionCode ;
	    
	    @Column(name="BRANCH_CODE")
	    private String branchCode ;
	    
	    @Column(name="CREATED_BY")
	    private String createdBy ;
	    
	    @Column(name="USER_TYPE")
	    private String userType ;

				
	    @Column(name="OLD_INSURER", length = 100)
	    private String oldInsurer ;
	    
	    @Column(name = "OLDSOURCE")
		private String OldSource;
		
		@Column(name = "OLDSOURCEID")
		private Integer OldSourceId;
		
		@Column(name = "VEHICLETRANSFERCASE")
		private String VehicleTransferCase;
		
		@Column(name = "VEHICLETRANSFERCASEID")
		private Integer VehicleTransferCaseId;
}


	    


