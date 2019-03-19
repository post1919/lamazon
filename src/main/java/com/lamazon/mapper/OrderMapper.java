package com.lamazon.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface OrderMapper {

	int order_update(Map map);

	int order_insert(Map map);

	Map getOrderMaster(Map map);

	ArrayList<Map> getOrderDetail(Map map);

	int user_info_u_point_update(HashMap map);

	int order_detail_insert(HashMap map);

	int order_detail_update(HashMap<String, String> map);

	int order_master_cnt(HashMap<String, String> map);

	ArrayList<Map> order_master(HashMap map);

	int order_master_o_status_update(HashMap<String, String> map);

	int order_master_o_status_update2(HashMap<String, String> map);

}
