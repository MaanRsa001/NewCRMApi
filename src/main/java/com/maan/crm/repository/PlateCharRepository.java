package com.maan.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.PlateCharMaster;
import com.maan.crm.bean.PlateCharMasterId;

@Repository
public interface PlateCharRepository extends JpaRepository<PlateCharMaster, PlateCharMasterId>{

}
