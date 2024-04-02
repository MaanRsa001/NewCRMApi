
package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotorBodyTypeMasterId implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Integer bodyId;
	private Date effectiveDate;
	private Integer amendId;
	private String insCompanyId;

}