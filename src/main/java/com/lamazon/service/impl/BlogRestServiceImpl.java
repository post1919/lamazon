package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.BlogRestMapper;
import com.lamazon.service.BlogRestService;

@Transactional
@Service
//@Scope("application")
public class BlogRestServiceImpl implements BlogRestService {

	private static final Logger logger = LoggerFactory.getLogger(BlogRestServiceImpl.class);

	@Autowired
	BlogRestMapper blogRestMapper;

	@Override
	public int admin_emailsubscribe_list_count(HashMap<String, String> map) {
		return blogRestMapper.admin_emailsubscribe_list_count(map);
	}

	@Override
	public ArrayList<Map> admin_emailsubscribe_list(HashMap<String, String> map) {
		return blogRestMapper.admin_emailsubscribe_list(map);
	}

	@Override
	public int admin_menu_sorting_update(HashMap<String, String> map) {
		return blogRestMapper.admin_menu_sorting_update(map);
	}

	@Override
	public int admin_menu_update(HashMap<String, String> map) {
		return blogRestMapper.admin_menu_update(map);
	}

	@Override
	public int admin_menu_name_update(HashMap<String, String> map) {
		return blogRestMapper.admin_menu_name_update(map);
	}

	@Override
	public int admin_menu_delete(HashMap<String, String> map) {
		return blogRestMapper.admin_menu_delete(map);
	}

	@Override
	public int rfp_info_update(HashMap<String, String> map) {
		return blogRestMapper.rfp_info_update(map);
	}

	@Override
	public int rfp_info_delete(HashMap<String, String> map) {
		return blogRestMapper.rfp_info_delete(map);
	}

	@Override
	public int rfp_info_name_update(HashMap<String, String> map) {
		return blogRestMapper.rfp_info_name_update(map);
	}

	@Override
	public int rfp_info_sorting_update(HashMap<String, String> map) {
		return blogRestMapper.rfp_info_sorting_update(map);
	}

	@Override
	public int rfp_info_insert(HashMap<String, String> map) {
		return blogRestMapper.rfp_info_insert(map);
	}


}
