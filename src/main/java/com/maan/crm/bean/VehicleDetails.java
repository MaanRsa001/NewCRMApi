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
@IdClass(VehicleDetailsId.class)
@Table(name = "VEHICLE_DETAILS")
public class VehicleDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@Id
	@Column(name = "VEH_CHASSIS_NO" ,nullable=false,length = 100)
	private String vehChassisNo;
	
	@Id
	@Column(name = "COMAPNY_ID",nullable=false, length = 100)
	private String comapnyId;

	//ENTITY
	@Column(name = "VEH_CODE",nullable=false, length = 100)
	private Integer vehCode;
	
	
	@Column(name = "VEH_REGN_NO", length = 100)
	private String vehRegnNo;

	@Column(name = "VEH_MAKE_ID")
	private Integer vehMakeId;

	@Column(name = "VEH_MAKE", length = 100)
	private String vehMake;

	@Column(name = "VEH_MODEL_ID")
	private Integer vehModelId;

	@Column(name = "VEH_MODEL", length = 100)
	private String vehModel;

	@Column(name = "VEH_BODYTYPE_ID")
	private Integer vehBodytypeId;

	@Column(name = "VEH_BODYTYPE", length = 100)
	private String vehBodytype;

	@Column(name = "COLOR_ID")
	private Integer colorlId;

	@Column(name = "COLOR_VARIANT", length = 100)
	private String colorVariant;

	@Column(name = "FUELTYPE_ID")
	private Integer fueltypeId;

	@Column(name = "FUELTYPE", length = 100)
	private String fueltype;

	@Column(name = "CC", length = 100)
	private String cc;

	@Column(name = "SEATING_CAPACITY")
	private Integer seatingCapacity;
	
	@Column(name = "CARRYING_PASSENGERS")
	private Integer carryingPassengers;

	@Column(name = "MANUFACTURE_YEAR")
	private Integer manufactureYear;

	@Column(name = "ENGINE_NO", length = 100)
	private String engineNo;

	@Column(name = "VEH_REMARKS", length = 200)
	private String vehRemarks;

	@Column(name = "PLATE_NO")
	private Integer plateNo;

	@Column(name = "PLATE_CHAR_ID")
	private Integer plateCharId;

	@Column(name = "PLATE_CHAR", length = 100)
	private String plateChar;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "VEH_REGN_DATE")
	private Date vehRegnDate;
	
	@Column(name="REGION_CODE")
    private String regionCode ;
    
    @Column(name="BRANCH_CODE")
    private String branchCode ;
    
    @Column(name="CREATED_BY")
    private String createdBy ;
    
    @Column(name="VEH_TYPE_ID")
    private String vehTypeId ;
    
    @Column(name="VEH_TYPE")
    private String vehType ;
    
    @Column(name="VEH_CLASSIFICATION_ID")
    private String vehClassificationId ;
    
    @Column(name="VEH_CLASSIFICATION")
    private String vehClassification ;
    
    @Column(name="GVW")
    private String gvw ;
    
    @Column(name="HYPOTHICATION")
    private String hypothication ;
    
    @Column(name="POS_ID")
    private String posId ; 
    
    @Column(name="POS")
    private String pos ;
    
    @Column(name="RTO_ID")
    private String rtoId ;
    
    @Column(name="RTO")
    private String rto ;
    
    @Column(name="ZONE_ID")
    private String zoneId; ;
    
    @Column(name="ZONE")
    private String zone ;
    
    @Column(name="VEH_REGN_ADDRESS")
    private String vehRegnAddress;
    
    @Column(name="STATE_ID")
    private String stateId;
    
    @Column(name="STATE")
    private String state;
    
    
    @Column(name="CITY_ID")
    private String cityId ;
    
    @Column(name="CITY")
    private String city ;
    
    @Column(name="VEH_USER_NAME")
    private String vehUserName ;
    
    @Column(name="VEH_USER_CONTACT")
    private String vehUserContact ;

    @Column(name="PIN_CODE")
    private String pinCode ;
    
}
