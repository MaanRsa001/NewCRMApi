package com.maan.crm.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsId implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
 
	private String vehChassisNo;
	private String comapnyId;
}
