package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.OrderMapper;
import com.lamazon.service.OrderService;

@Service("orderService")
//@Scope("application")
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderMapper orderMapper;

	@Override
	public int order_update(Map map) {
		return orderMapper.order_update(map);
	}

	@Override
	public int order_insert(Map map) {
		return orderMapper.order_insert(map);
	}

	@Override
	public Map getOrderMaster(Map map) {
		return orderMapper.getOrderMaster(map);
	}

	@Override
	public ArrayList<Map> getOrderDetail(Map map) {
		return orderMapper.getOrderDetail(map);
	}

	@Override
	public int user_info_u_point_update(HashMap map) {
		return orderMapper.user_info_u_point_update(map);
	}

	@Override
	public int order_detail_insert(HashMap map) {
		return orderMapper.order_detail_insert(map);
	}

	@Override
	public int order_detail_update(HashMap<String, String> map) {
		return orderMapper.order_detail_update(map);
	}

	@Override
	public int order_master_cnt(HashMap<String, String> map) {
		return orderMapper.order_master_cnt(map);
	}

	@Override
	public ArrayList<Map> order_master(HashMap map) {
		return orderMapper.order_master(map);
	}

	@Override
	public int order_master_o_status_update(HashMap<String, String> map) {
		return orderMapper.order_master_o_status_update(map);
	}

	@Override
	public int order_master_o_status_update2(HashMap<String, String> map) {
		return orderMapper.order_master_o_status_update2(map);
	}

}
