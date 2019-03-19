package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.ExcelMapper;
import com.lamazon.service.ExcelService;

@Transactional
@Service
//@Scope("application")
public class ExcelServiceImpl implements ExcelService {

	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Autowired
	ExcelMapper excelMapper;

	@Override
	public ArrayList manage_partner_updatelist(HashMap<String, String> map) {
		return excelMapper.manage_partner_updatelist(map);
	}

	@Override
	public int insert_lms_reservation(Map map) {
		return excelMapper.insert_lms_reservation(map);
	}

}
