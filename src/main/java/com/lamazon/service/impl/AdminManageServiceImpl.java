package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.AdminManageMapper;
import com.lamazon.service.AdminManageService;

@Transactional
@Service
public class AdminManageServiceImpl implements AdminManageService {

	private static final Logger logger = LoggerFactory.getLogger(AdminManageServiceImpl.class);

	@Autowired
	AdminManageMapper adminManageMapper;

	@Override
	public int business_date_find(HashMap<String, String> map) {
		return adminManageMapper.business_date_find(map);
	}

	@Override
	public int business_date_update(HashMap<String, String> map) {
		return adminManageMapper.business_date_update(map);
	}

	@Override
	public int business_date_insert(HashMap<String, String> map) {
		return adminManageMapper.business_date_insert(map);
	}

	@Override
	public Map common_code_master_detail(HashMap<String, String> map) {
		return adminManageMapper.common_code_master_detail(map);
	}

	@Override
	public ArrayList<Map> common_code_detail_list(HashMap<String, String> map) {
		return adminManageMapper.common_code_detail_list(map);
	}

	@Override
	public Map kakao_detail(Map map) {
		return adminManageMapper.kakao_detail(map);
	}

	@Override
	public ArrayList mail_info_template_list(HashMap<String, String> map) {
		return adminManageMapper.mail_info_template_list(map);
	}

	@Override
	public Map mail_info_detail(HashMap<String, String> map) {
		return adminManageMapper.mail_info_detail(map);
	}

	@Override
	public Map mail_info_template_detail(HashMap<String, String> map) {
		return adminManageMapper.mail_info_template_detail(map);
	}

	@Override
	public int common_code_detail_insert(HashMap map) {
		return adminManageMapper.common_code_detail_insert(map);
	}

	@Override
	public int common_code_detail_update(HashMap map) {
		return adminManageMapper.common_code_detail_update(map);
	}

	@Override
	public int common_code_master_insert(HashMap map) {
		return adminManageMapper.common_code_master_insert(map);
	}

	@Override
	public int common_code_master_update(HashMap map) {
		return adminManageMapper.common_code_master_update(map);
	}

	@Override
	public int update_kakao_msg_mst(HashMap<String, String> map) {
		return adminManageMapper.update_kakao_msg_mst(map);
	}

	@Override
	public int mail_info_update(HashMap<String, String> map) {
		return adminManageMapper.mail_info_update(map);
	}

	@Override
	public int mail_info_template_update(HashMap<String, String> map) {
		return adminManageMapper.mail_info_template_update(map);
	}

}
