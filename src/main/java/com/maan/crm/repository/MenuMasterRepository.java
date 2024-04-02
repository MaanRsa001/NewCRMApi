package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.MenuMaster;



public interface MenuMasterRepository  extends JpaRepository<MenuMaster, Integer>  , JpaSpecificationExecutor<MenuMaster>{


}
