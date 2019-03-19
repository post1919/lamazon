package com.lamazon.mapper;

import java.util.HashMap;
import java.util.Map;

public interface LoginMapper {
	Map user_info_login(Map<String, String> map);
	int user_info_login_logindate(Map<String, String> map);

	Map user_info_login_admin(Map<String, String> map);

	Map user_info_login_office(Map<String, String> map);
	Map user_info_login_office_logindate(Map<String, String> map);

	Map user_info_login_mk(Map<String, String> map);
	Map user_info_login_mk_logindate(Map<String, String> map);

	Map user_info_login_sns(Map<String, String> map);
	Map user_info_login_sns_logindate(Map<String, String> map);


	Map user_project_status_count_info(int uPk);
	String user_info_check_id(String id);
	int user_info_insert(HashMap map);
	int user_info_update(HashMap<String, String> map);
}
