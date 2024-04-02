/*
 * Created on 2022-01-05 ( 16:10:40 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.maan.crm.bean;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import java.math.BigDecimal;

/**
 * Composite primary key for entity "BranchMaster" ( stored in table
 * "BRANCH_MASTER" )
 *
 * @author Telosys
 *
 */

@Getter
@Setter
public class UnderWritterRelationId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uwCode;
	private Long classId;
	private Long policyTypeId;


}
