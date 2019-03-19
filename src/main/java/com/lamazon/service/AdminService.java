package com.lamazon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AdminService {

	public int manage_user_count(Map map) throws IOException;

	public ArrayList<Map> manage_user_list(Map map) throws IOException;

	public ArrayList<Map> agent_user_list(Map map);

	ArrayList<Map> user_tax_bank_list(Map map);

	public Map user_tax_info(Map map);

	public Map<String, String> user_info_mdeval(Map map);

	public int manage_user_info_update(Map map);

	public int manage_user_tax_info_update(Map<String, String> map);

	public int manage_user_tax_info_insert(Map<String, String> map);

	public int manage_user_mdeval_info_insert(Map<String, String> map);

	public int manage_user_mdeval_info_update(Map<String, String> map);

	public int manage_user_status_change_accept(HashMap<String, String> map);

	public int manage_user_delete(HashMap<String, Object> map);

	public int manage_user_change_password(HashMap<String, String> map);

	int manage_user_status_change_reject(HashMap<String, String> map);

	int manage_user_status_change_review(HashMap<String, String> map);

	public int common_code_detail_delete(HashMap<String, String> map);

	public int common_code_detail_insert(HashMap<String, String> map);

	public int admin_coupon_count(HashMap<String, String> map);

	public ArrayList<Map> admin_coupon_list(HashMap<String, String> map);

	public ArrayList<Map> manage_goal_year(HashMap<String, String> map);

	public int manage_goal_exist(HashMap<String, String> map);

	public int manage_goal_update(HashMap<String, String> map);

	public int manage_goal_insert(HashMap<String, String> map);

	public String total_saleday_of_month(HashMap<String, String> map);

	public String monthly_average_order(HashMap<String, String> map);

	public String monthly_average_contract(HashMap<String, String> map);

	public int monthly_average_insert(HashMap<String, String> map);

	public ArrayList matching_data(HashMap<String, String> _map);

	public int update_company_contract_file_by_cid(HashMap map);

	public Map user_info_login(HashMap<String, String> map);

	public int user_info_withdraw(HashMap<String, String> map);

	public int update_estimate_complete(HashMap<String, String> map);

	int update_requesttype_manage(HashMap<String, Object> map);

	public int delete_project(HashMap<String, Object> map);

	public int insert_project_participate(HashMap<String, Object> map);

	public int update_pmi_auth(HashMap<String, Object> map);

	public ArrayList<Map> manage_partner_list_alram(HashMap<String, Object> map);

	public int update_project_participate(HashMap<String, Object> map);

	public ArrayList<Map> project_contact_request(HashMap<String, String> map);

	public int update_project_contact_request_save(HashMap<String, Object> map);

	public ArrayList<Map> no_selection_partner_list(HashMap<String, String> map);

	public int update_project_participate_noselection(HashMap<String, Object> map);

	public int insert_project_contact_request_admin(HashMap<String, Object> map);

	public ArrayList<Map> project_contact_list(HashMap<String, String> map);

	public int insert_project_contract_admin(HashMap<String, Object> map);

	public int update_project_contract_admin(HashMap<String, Object> map);

	public ArrayList<Map> manage_project_service_info_list_new(HashMap<String, String> map);

	public ArrayList<Map> participation_company_list(HashMap<String, String> map);

	public Map manage_project_service_info_pop(HashMap<String, String> map);

	public int insert_company_service(HashMap<String, Object> map);

	public int update_company_service(HashMap<String, Object> map);

	public int delete_company_service(HashMap<String, Object> map);

	public ArrayList<Map> estimate_complete_company_list(HashMap<String, String> map);

	public ArrayList<Map> project_comment_list(HashMap<String, String> map);

	public ArrayList<Map> no_selection_estimate_partner_list(HashMap<String, String> map);

	public HashMap<String, Object> contract_wait_company(HashMap<String, String> map);

	public int insert_project_participate_test(HashMap<String, String> map);

	public int insert_project_contactrequest_test(HashMap<String, String> map);

	public int insert_project_apply_test(HashMap<String, String> map);

	public ArrayList<Map> project_contact_request_partner(HashMap<String, String> map);

	public ArrayList<Map> mm_goal_report(HashMap<String, String> map);

	public ArrayList<Map> dd_goal_report(HashMap<String, String> map);

	public Map project_contact_detail(HashMap paramMap);

	public ArrayList<Map> get_project_participate(HashMap map_);

	public Map get_lately_project_contract_request(HashMap paramMap);

	public ArrayList<Map> project_apply_complete(HashMap paramMap);

	public ArrayList<Map> contract_no_selection_partner_list(HashMap<String, String> map);

	public int update_contract_noselection(HashMap<String, Object> map);

	public int update_project_apply_noselection(HashMap<String, Object> map);

	public int point_update(HashMap<String, String> map);

}
