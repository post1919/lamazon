package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AdminManageRestService {

	int common_code_list_m_count(HashMap<String, String> map);

	ArrayList<Map> common_code_list_m(HashMap<String, String> map);

}
