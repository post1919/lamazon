package com.lamazon.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AdminMapper {
	int manage_user_count(Map map);

	ArrayList<Map> manage_user_list(Map map);

	ArrayList<Map> agent_user_list(Map map);

	ArrayList<Map> user_tax_bank_list(Map map);

	Map user_tax_info(Map map);

	Map<String, String> user_info_mdeval(Map map);

	int manage_user_info_update(Map map);

	int manage_user_tax_info_update(Map<String, String> map);

	int manage_user_tax_info_insert(Map<String, String> map);

	int manage_user_mdeval_info_insert(Map<String, String> map);

	int manage_user_mdeval_info_update(Map<String, String> map);

	int manage_user_status_change_accept(HashMap<String, String> map);

	int manage_user_delete(HashMap<String, Object> map);

	int manage_user_change_password(HashMap<String, String> map);

	int manage_user_status_change_reject(HashMap<String, String> map);

	int manage_user_status_change_review(HashMap<String, String> map);

	int common_code_detail_delete(HashMap<String, String> map);

	int common_code_detail_insert(HashMap<String, String> map);

	int admin_coupon_count(HashMap<String, String> map);

	ArrayList<Map> admin_coupon_list(HashMap<String, String> map);

	ArrayList<Map> manage_goal_year(HashMap<String, String> map);

	int manage_goal_exist(HashMap<String, String> map);

	int manage_goal_update(HashMap<String, String> map);

	int manage_goal_insert(HashMap<String, String> map);

	String total_saleday_of_month(HashMap<String, String> map);

	String monthly_average_order(HashMap<String, String> map);

	String monthly_average_contract(HashMap<String, String> map);

	int monthly_average_insert(HashMap<String, String> map);

	ArrayList<Map> matching_data(HashMap<String, String> _map);

	int update_company_contract_file_by_cid(HashMap map);

	Map user_info_login(HashMap<String, String> map);

	int user_info_withdraw(HashMap<String, String> map);

	int update_requesttype_manage(HashMap<String, Object> map);

	int update_estimate_complete(HashMap<String, String> map);

	int delete_project(HashMap<String, Object> map);

	int insert_project_participate(HashMap<String, Object> map);

	int update_pmi_auth(HashMap<String, Object> map);

	ArrayList<Map> manage_partner_list_alram(HashMap<String, Object> map);

	int update_project_participate(HashMap<String, Object> map);

	ArrayList<Map> project_contact_request(HashMap<String, String> map);

	int update_project_contact_request_save(HashMap<String, Object> map);

	ArrayList<Map> no_selection_partner_list(HashMap<String, String> map);

	int update_project_participate_noselection(HashMap<String, Object> map);

	int insert_project_contact_request_admin(HashMap<String, Object> map);

	ArrayList<Map> project_contact_list(HashMap<String, String> map);

	int insert_project_contract_admin(HashMap<String, Object> map);

	int update_project_contract_admin(HashMap<String, Object> map);

	ArrayList<Map> manage_project_service_info_list_new(HashMap<String, String> map);

	ArrayList<Map> participation_company_list(HashMap<String, String> map);

	Map manage_project_service_info_pop(HashMap<String, String> map);

	int insert_company_service(HashMap<String, Object> map);

	int update_company_service(HashMap<String, Object> map);

	int delete_company_service(HashMap<String, Object> map);

	ArrayList<Map> estimate_complete_company_list(HashMap<String, String> map);

	ArrayList<Map> project_comment_list(HashMap<String, String> map);

	ArrayList<Map> no_selection_estimate_partner_list(HashMap<String, String> map);

	HashMap<String, Object> contract_wait_company(HashMap<String, String> map);

	int insert_project_participate_test(HashMap<String, String> map);

	int insert_project_contactrequest_test(HashMap<String, String> map);

	int insert_project_apply_test(HashMap<String, String> map);

	ArrayList<Map> project_contact_request_partner(HashMap<String, String> map);

	ArrayList<Map> mm_goal_report(HashMap<String, String> map);

	ArrayList<Map> dd_goal_report(HashMap<String, String> map);

	Map project_contact_detail(HashMap paramMap);

	ArrayList<Map> get_project_participate(HashMap map_);

	Map get_lately_project_contract_request(HashMap paramMap);

	ArrayList<Map> project_apply_complete(HashMap paramMap);

	ArrayList<Map> contract_no_selection_partner_list(HashMap<String, String> map);

	int update_contract_noselection(HashMap<String, Object> map);

	int update_project_apply_noselection(HashMap<String, Object> map);

	int point_update(HashMap<String, String> map);
}
