package com.maan.crm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sequence_master")
public class SequenceMaster {

    @Id
    @Column(name = "SNO")
    private int sno;

    @Column(name = "SEQUENCE_NO")
    private String sequenceNo;

    @Column(name = "SEQUENCE_NAME")
    private String sequenceName;

    @Column(name = "SEQUENCE_VALUE")
    private Integer sequenceValue;

    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REMARKS")
    private String remarks;
}
