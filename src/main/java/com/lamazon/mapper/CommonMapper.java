package com.lamazon.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lamazon.model.AdminMenu;

public interface CommonMapper {

	List<Map> category_all(Map categoryAllMap);

	List<AdminMenu> adminMenuListByParent(Map<String, Object> map);

	AdminMenu getAdminMenuFullname(String requestURI);

	ArrayList<Map> category_list_by_father(Map<String, String> map);

	List<Map> common_code_all();

	List<Map> category_without_all();

	List<Map> category_for_office();

	Map user_id_lock_select(String id);

	int user_id_lock_insert(Map map);

	int user_id_dup_select(String id);

	Map category_by_pk(int pk);
	Map upjong_by_pk(int pk);
	Map rfp_by_pk(Map map);
	Map rfp_title_by_catecode(String catecode);
	Map rfp_content_by_pk(int pk);
	ArrayList category_list_by_father(int father);
	ArrayList upjong_list_by_father(int father);
	ArrayList common_code_list(Map map);
	ArrayList category_filter_list_by_catecode(String catecode);
	ArrayList category_filter_list_by_rtpk(int rt_pk);
	ArrayList common_code_list_group(Map map);

	int project_count_by_upk(int uPk);
	int project_count_contract_num_by_upk(int uPk);
	int project_count_ing_num_by_upk(int uPk);
	int project_count_done_num_by_upk(int uPk);
	int project_sum_price_by_upk(int uPk);
	int user_info_update_project_num(Map map);

	int project_apply_count_by_cpk(int cPk);
	int project_contract_by_cpk(int cPk);
	int project_sum_price_by_cpk(int cPk);
	int project_contract_update_partner(Map map);

	int project_count_by_category(String categoryCode);
	int project_update_category_num(Map map);

	int partner_pk_by_cid(Map map);
	int category_count_partner_num_by_code(String code);

	int category_count_partner_num_update(Map map);

	int getProjectDuplChk(int uPk);

	Map user_info_all(int pk);
	Map partner_by_pk(int pk);
	Map partner_by_pk_for_manager(int pk);

	Map partner_by_cid(String id);
	Map partner_basicinfo_by_upk(int id);
	Map partner_basicinfo_by_cpk(int id);
	Map project_by_pid(String id);

	Map project_by_pk(int pk);
	Map project_apply_by_pk(int pk);

	Map project_apply_by_id(String id);

	Map project_apply_by_id_with_cpk(Map map);

	Map manage_review_by_pk(int id);

	Map promotion(String id);

	Map promotion_by_pk(int pk);

	Map company_person_by_paid(String pa_id);

	int project_total_number_for_main(Map map);

	ArrayList sync_list_all_project_u_pk();
	ArrayList sync_list_all_project_apply_c_pk();

	Map partner_statement_by_cpk(int C_PK);

	int sales_total_number_for_main(Map map);

	Map manage_partner_outsourcing_detail(int id);

	Map manage_partner_outsourcing_file_info(int CO_PK);

	ArrayList upjong_list_by_3th(int father);

	int sejong_lms_insert(Map map);

	int user_notification_insert(Map<String, String> map);

	Map<String, String> mail_info_detail(Map<String, String> map);

	Map<String, String> mail_info_template_detail(Map<String, String> map);

	int xmail_save_info(Map<String, String> map);

	ArrayList<Map<String, String>> user_notification_message_texts(HashMap<String, String> map);

	String company_check_registration_number(String number);

	ArrayList<Map> category_list_by_father_new(Map map);

	ArrayList<Map> category_list_depth2_by_father(Map map);

	ArrayList<Map> category_list_depth3_by_father(Map map);

	ArrayList<Map> category_list_depth1(Map map);

	ArrayList<Map> category_list_depth3(Map map);

	ArrayList<Map> category_list_depth2(Map map);

	void getNewPk(Map<String, String> paramMap);

	Map user_info_project_by_pk(int code);

	Map kakao_msg_mst_info(String titleKey);

	void kakao_insert(HashMap<String, String> map);

	Map get_project_allinfo(String prPk);

	String category_max_depth(String father);

	String category_max_pk();

	void manage_category_update_fullname_all1();

	void manage_category_update_fullname_all2();

	void manage_category_update_fullname_all3();

	int update_project_pr_status(HashMap<String, Object> map);

	Map get_project_allinfo_by_prpk(String prPk);

	Map get_user_info_by_u_pk(Map map);
}
