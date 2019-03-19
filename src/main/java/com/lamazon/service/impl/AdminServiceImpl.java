package com.lamazon.service.impl;

/*
 * DBManager.java
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.AdminMapper;
import com.lamazon.service.AdminService;

@Transactional
@Service
//@Scope("application")
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminMapper adminMapper;

	@Override
	public int manage_user_count(Map map) throws IOException {
		return adminMapper.manage_user_count(map);
	}

	@Override
	public ArrayList<Map> manage_user_list(Map map) throws IOException {
		return adminMapper.manage_user_list(map);
	}

	@Override
	public ArrayList<Map> agent_user_list(Map map) {
		return adminMapper.agent_user_list(map);
	}

	@Override
	public ArrayList<Map> user_tax_bank_list(Map map) {
		return adminMapper.user_tax_bank_list(map);
	}

	@Override
	public Map user_tax_info(Map map) {
		return adminMapper.user_tax_info(map);
	}

	@Override
	public Map<String, String> user_info_mdeval(Map map) {
		return adminMapper.user_info_mdeval(map);
	}

	@Override
	public int manage_user_info_update(Map map) {
		return adminMapper.manage_user_info_update(map);
	}

	@Override
	public int manage_user_tax_info_update(Map<String, String> map) {
		return adminMapper.manage_user_tax_info_update(map);
	}

	@Override
	public int manage_user_tax_info_insert(Map<String, String> map) {
		return adminMapper.manage_user_tax_info_insert(map);
	}

	@Override
	public int manage_user_mdeval_info_insert(Map<String, String> map) {
		return adminMapper.manage_user_mdeval_info_insert(map);
	}

	@Override
	public int manage_user_mdeval_info_update(Map<String, String> map) {
		return adminMapper.manage_user_mdeval_info_update(map);
	}

	@Override
	public int manage_user_status_change_accept(HashMap<String, String> map) {
		return adminMapper.manage_user_status_change_accept(map);
	}

	@Override
	public int manage_user_delete(HashMap<String, Object> map) {
		return adminMapper.manage_user_delete(map);
	}

	@Override
	public int manage_user_change_password(HashMap<String, String> map) {
		return adminMapper.manage_user_change_password(map);
	}

	@Override
	public int manage_user_status_change_reject(HashMap<String, String> map) {
		return adminMapper.manage_user_status_change_reject(map);
	}

	@Override
	public int manage_user_status_change_review(HashMap<String, String> map) {
		return adminMapper.manage_user_status_change_review(map);
	}

	@Override
	public int common_code_detail_delete(HashMap<String, String> map) {
		return adminMapper.common_code_detail_delete(map);
	}

	@Override
	public int common_code_detail_insert(HashMap<String, String> map) {
		return adminMapper.common_code_detail_insert(map);
	}

	@Override
	public int admin_coupon_count(HashMap<String, String> map) {
		return adminMapper.admin_coupon_count(map);
	}

	@Override
	public ArrayList<Map> admin_coupon_list(HashMap<String, String> map) {
		return adminMapper.admin_coupon_list(map);
	}

	@Override
	public int manage_goal_exist(HashMap<String, String> map) {
		return adminMapper.manage_goal_exist(map);
	}

	@Override
	public int manage_goal_update(HashMap<String, String> map) {
		return adminMapper.manage_goal_update(map);
	}

	@Override
	public int manage_goal_insert(HashMap<String, String> map) {
		return adminMapper.manage_goal_insert(map);
	}

	@Override
	public String total_saleday_of_month(HashMap<String, String> map) {
		return adminMapper.total_saleday_of_month(map);
	}

	@Override
	public String monthly_average_order(HashMap<String, String> map) {
		return adminMapper.monthly_average_order(map);
	}

	@Override
	public String monthly_average_contract(HashMap<String, String> map) {
		return adminMapper.monthly_average_contract(map);
	}

	@Override
	public int monthly_average_insert(HashMap<String, String> map) {
		return adminMapper.monthly_average_insert(map);
	}

	@Override
	public ArrayList<Map> manage_goal_year(HashMap<String, String> map) {
		return adminMapper.manage_goal_year(map);
	}

	@Override
	public ArrayList<Map> matching_data(HashMap<String, String> _map) {
		return adminMapper.matching_data(_map);
	}

	@Override
	public int update_company_contract_file_by_cid(HashMap map) {
		return adminMapper.update_company_contract_file_by_cid(map);
	}

	@Override
	public Map user_info_login(HashMap<String, String> map) {
		return adminMapper.user_info_login(map);
	}

	@Override
	public int user_info_withdraw(HashMap<String, String> map) {
		return adminMapper.user_info_withdraw(map);
	}

	@Override
	public int update_requesttype_manage(HashMap<String, Object> map) {
		return adminMapper.update_requesttype_manage(map);
	}

	@Override
	public int update_estimate_complete(HashMap<String, String> map) {
		return adminMapper.update_estimate_complete(map);
	}

	@Override
	public int delete_project(HashMap<String, Object> map) {
		return adminMapper.delete_project(map);
	}

	@Override
	public int insert_project_participate(HashMap<String, Object> map) {
		return adminMapper.insert_project_participate(map);
	}

	@Override
	public int update_pmi_auth(HashMap<String, Object> map) {
		return adminMapper.update_pmi_auth(map);
	}

	@Override
	public ArrayList<Map> manage_partner_list_alram(HashMap<String, Object> map) {
		return adminMapper.manage_partner_list_alram(map);
	}

	@Override
	public int update_project_participate(HashMap<String, Object> map) {
		return adminMapper.update_project_participate(map);
	}

	@Override
	public int update_project_contact_request_save(HashMap<String, Object> map) {
		return adminMapper.update_project_contact_request_save(map);
	}

	@Override
	public ArrayList<Map> no_selection_partner_list(HashMap<String, String> map) {
		return adminMapper.no_selection_partner_list(map);
	}

	@Override
	public int update_project_participate_noselection(HashMap<String, Object> map) {
		return adminMapper.update_project_participate_noselection(map);
	}

	@Override
	public int insert_project_contact_request_admin(HashMap<String, Object> map) {
		return adminMapper.insert_project_contact_request_admin(map);
	}

	@Override
	public ArrayList<Map> project_contact_list(HashMap<String, String> map) {
		return adminMapper.project_contact_list(map);
	}

	@Override
	public int insert_project_contract_admin(HashMap<String, Object> map) {
		return adminMapper.insert_project_contract_admin(map);
	}

	@Override
	public int update_project_contract_admin(HashMap<String, Object> map) {
		return adminMapper.update_project_contract_admin(map);
	}

	@Override
	public ArrayList<Map> manage_project_service_info_list_new(HashMap<String, String> map) {
		return adminMapper.manage_project_service_info_list_new(map);
	}

	@Override
	public ArrayList<Map> participation_company_list(HashMap<String, String> map) {
		return adminMapper.participation_company_list(map);
	}

	@Override
	public Map manage_project_service_info_pop(HashMap<String, String> map) {
		return adminMapper.manage_project_service_info_pop(map);
	}

	@Override
	public int insert_company_service(HashMap<String, Object> map) {
		return adminMapper.insert_company_service(map);
	}

	@Override
	public int update_company_service(HashMap<String, Object> map) {
		return adminMapper.update_company_service(map);
	}

	@Override
	public int delete_company_service(HashMap<String, Object> map) {
		return adminMapper.delete_company_service(map);
	}

	@Override
	public ArrayList<Map> estimate_complete_company_list(HashMap<String, String> map) {
		return adminMapper.estimate_complete_company_list(map);
	}

	@Override
	public ArrayList<Map> project_comment_list(HashMap<String, String> map) {
		return adminMapper.project_comment_list(map);
	}

	@Override
	public ArrayList<Map> no_selection_estimate_partner_list(HashMap<String, String> map) {
		return adminMapper.no_selection_estimate_partner_list(map);
	}

	@Override
	public ArrayList<Map> project_contact_request(HashMap<String, String> map) {
		return adminMapper.project_contact_request(map);
	}

	@Override
	public HashMap<String, Object> contract_wait_company(HashMap<String, String> map) {
		return adminMapper.contract_wait_company(map);
	}

	@Override
	public int insert_project_participate_test(HashMap<String, String> map) {
		return adminMapper.insert_project_participate_test(map);
	}

	@Override
	public int insert_project_contactrequest_test(HashMap<String, String> map) {
		return adminMapper.insert_project_contactrequest_test(map);
	}

	@Override
	public int insert_project_apply_test(HashMap<String, String> map) {
		return adminMapper.insert_project_apply_test(map);
	}

	@Override
	public ArrayList<Map> project_contact_request_partner(HashMap<String, String> map) {
		return adminMapper.project_contact_request_partner(map);
	}

	@Override
	public ArrayList<Map> mm_goal_report(HashMap<String, String> map) {
		return adminMapper.mm_goal_report(map);
	}

	@Override
	public ArrayList<Map> dd_goal_report(HashMap<String, String> map) {
		return adminMapper.dd_goal_report(map);
	}

	@Override
	public Map project_contact_detail(HashMap paramMap) {
		return adminMapper.project_contact_detail(paramMap);
	}

	@Override
	public ArrayList<Map> get_project_participate(HashMap map_) {
		return adminMapper.get_project_participate(map_);
	}

	@Override
	public Map get_lately_project_contract_request(HashMap paramMap) {
		return adminMapper.get_lately_project_contract_request(paramMap);
	}

	@Override
	public ArrayList<Map> project_apply_complete(HashMap paramMap) {
		return adminMapper.project_apply_complete(paramMap);
	}

	@Override
	public ArrayList<Map> contract_no_selection_partner_list(HashMap<String, String> map) {
		return adminMapper.contract_no_selection_partner_list(map);
	}

	@Override
	public int update_contract_noselection(HashMap<String, Object> map) {
		return adminMapper.update_contract_noselection(map);
	}

	@Override
	public int update_project_apply_noselection(HashMap<String, Object> map) {
		return adminMapper.update_project_apply_noselection(map);
	}

	@Override
	public int point_update(HashMap<String, String> map) {
		return adminMapper.point_update(map);
	}

}
