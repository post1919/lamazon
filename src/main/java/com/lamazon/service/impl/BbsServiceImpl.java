package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.BbsMapper;
import com.lamazon.service.BbsService;

@Transactional
@Service("bbsService")
//@Scope("application")
public class BbsServiceImpl implements BbsService {

	private static final Logger logger = LoggerFactory.getLogger(BbsServiceImpl.class);

	@Autowired
	BbsMapper bbsMapper;

	@Override
	public int bbs_cnt(HashMap map) {
		return bbsMapper.bbs_cnt(map);
	}

	@Override
	public ArrayList<Map> bbs_list(HashMap map) {
		return bbsMapper.bbs_list(map);
	}

	@Override
	public Map bbs(Map map) {
		return bbsMapper.bbs(map);
	}

	@Override
	public ArrayList<Map> bbs_reply(Map map) {
		return bbsMapper.bbs_reply(map);
	}

	@Override
	public int bbs_insert(HashMap map) {
		return bbsMapper.bbs_insert(map);
	}

	@Override
	public int bbs_update(HashMap map) {
		return bbsMapper.bbs_update(map);
	}

	@Override
	public int bbs_reply_update(HashMap map) {
		return bbsMapper.bbs_reply_update(map);
	}

	@Override
	public int bbs_reply_insert(HashMap map) {
		return bbsMapper.bbs_reply_insert(map);
	}
}
