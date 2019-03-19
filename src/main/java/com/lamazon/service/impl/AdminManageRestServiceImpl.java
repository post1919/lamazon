package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.AdminManageRestMapper;
import com.lamazon.service.AdminManageRestService;

@Transactional
@Service
public class AdminManageRestServiceImpl implements AdminManageRestService {

	private static final Logger logger = LoggerFactory.getLogger(AdminManageServiceImpl.class);

	@Autowired
	AdminManageRestMapper adminManageRestMapper;

	@Override
	public int common_code_list_m_count(HashMap<String, String> map) {
		return adminManageRestMapper.common_code_list_m_count(map);
	}

	@Override
	public ArrayList<Map> common_code_list_m(HashMap<String, String> map) {
		return adminManageRestMapper.common_code_list_m(map);
	}

}
