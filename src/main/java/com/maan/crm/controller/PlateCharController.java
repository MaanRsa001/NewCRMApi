package com.maan.crm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maan.crm.res.CommonCrmRes;
import com.maan.crm.res.DropDownRes;
import com.maan.crm.service.PlateCharService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "DROPDOWN : Dropdown ", description = "API's")
@RequestMapping("/dropdown")
public class PlateCharController {

	@Autowired
	private PlateCharService plateCharService;

	@GetMapping("/plate-char")
	@ApiOperation(value="This method is get PlateChar Drop Down")
	public ResponseEntity<CommonCrmRes> getCityDropdown() {

		CommonCrmRes data = new CommonCrmRes();

		// Save
		List<DropDownRes> res = plateCharService.getplateCharDropdown();
		data.setCommonResponse(res);
		data.setIsError(false);
		data.setErrorMessage(Collections.emptyList());
		data.setMessage("Success");

		if (res != null) {
			return new ResponseEntity<CommonCrmRes>(data, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
}
