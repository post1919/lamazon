package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AdminManageService {

	public int business_date_find(HashMap<String, String> map);

	public int business_date_update(HashMap<String, String> map);

	public int business_date_insert(HashMap<String, String> map);

	public Map common_code_master_detail(HashMap<String, String> map);

	public ArrayList<Map> common_code_detail_list(HashMap<String, String> map);

	public Map kakao_detail(Map map);

	public ArrayList mail_info_template_list(HashMap<String, String> map);

	public Map mail_info_detail(HashMap<String, String> map);

	public Map mail_info_template_detail(HashMap<String, String> map);

	public int common_code_detail_insert(HashMap map);

	public int common_code_detail_update(HashMap map);

	public int common_code_master_insert(HashMap map);

	public int common_code_master_update(HashMap map);

	public int update_kakao_msg_mst(HashMap<String, String> map);

	public int mail_info_update(HashMap<String, String> map);

	public int mail_info_template_update(HashMap<String, String> map);

}
