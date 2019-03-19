package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface OrderService {
	public int order_update(Map map);

	public int order_insert(Map map);

	public Map getOrderMaster(Map map);

	public ArrayList<Map> getOrderDetail(Map map);

	public int user_info_u_point_update(HashMap map);

	public int order_detail_insert(HashMap map);

	public int order_detail_update(HashMap<String, String> map);

	public int order_master_cnt(HashMap<String, String> map);

	public ArrayList<Map> order_master(HashMap map);

	public int order_master_o_status_update(HashMap<String, String> map);

	public int order_master_o_status_update2(HashMap<String, String> map);
}
