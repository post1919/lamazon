package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BlogRestService {

	int admin_emailsubscribe_list_count(HashMap<String, String> map);

	ArrayList<Map> admin_emailsubscribe_list(HashMap<String, String> map);

	int admin_menu_sorting_update(HashMap<String, String> map);

	int admin_menu_update(HashMap<String, String> map);

	int admin_menu_name_update(HashMap<String, String> map);

	int admin_menu_delete(HashMap<String, String> map);

	int rfp_info_update(HashMap<String, String> map);

	int rfp_info_delete(HashMap<String, String> map);

	int rfp_info_name_update(HashMap<String, String> map);

	int rfp_info_sorting_update(HashMap<String, String> map);

	int rfp_info_insert(HashMap<String, String> map);

}
