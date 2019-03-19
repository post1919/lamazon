package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ManageRestService {

	String category_code_all_by_self_with_pipe(HashMap<String, String> map);

	/**
	 * getChildCode()
	 * @param map
	 * @return
	 */
	String category_code_all_by_self(HashMap<String, String> map);

	int manage_partner_count(HashMap<String, String> map);

	ArrayList<Map<String, String>> manage_partner_list(HashMap<String, String> map);

	int manage_review_count(HashMap<String, String> map);

	ArrayList<Map> manage_review_list(HashMap<String, String> map);

	int manage_partner_outsourcing_count(HashMap<String, String> map);

	ArrayList<Map<String, String>> manage_partner_outsourcing_list(HashMap<String, String> map);

	ArrayList<Map> manage_partner_outsourcing_category(HashMap<String, String> map);

	Map partner_by_pk_for_manager(HashMap<String, String> map);

	int manage_partner_status_change_accept(HashMap<String, String> map);

	int manage_user_status_change_accept(HashMap<String, String> map);

	int manage_partner_status_change_reject(HashMap<String, String> map);

	int manage_user_status_change_reject(HashMap<String, String> map);

	int manage_user_change_password(HashMap<String, String> map);

	int manage_partner_change_status(HashMap<String, String> map);

	Map partner_manager(HashMap<String, String> map);

	int partner_project_count(HashMap<String, String> map);

	ArrayList<Map> partner_project_list(HashMap<String, String> map);

	int manage_partner_change_id(HashMap<String, String> map);

	int manage_partner_delete(HashMap<String, String> map);

	int manage_user_delete(HashMap<String, String> map);

	int manage_partner_updatelist_count(HashMap<String, String> map);

	ArrayList<Map> manage_partner_updatelist(HashMap<String, String> map);

	int manage_partner_call_050_list_count(HashMap<String, String> map);

	ArrayList<Map> manage_partner_call_050_list(HashMap<String, String> map);

	int matching_data_count(HashMap<String, String> map);

	ArrayList<Map> matching_data(HashMap<String, String> map);

	int admin_hometax_list_count(HashMap<String, String> map);

	ArrayList<Map> admin_hometax_list(HashMap<String, String> map);

	int admin_hometax_faillist_count(HashMap<String, String> map);

	ArrayList<Map> admin_hometax_faillist(HashMap<String, String> map);

	ArrayList<Map> manage_category_list_by_father(HashMap<String, String> map);

	int manage_promotion_count(HashMap<String, String> map);

	ArrayList<Map> manage_promotion_list(HashMap<String, String> map);

	int admin_job_count(HashMap<String, String> map);

	ArrayList<Map> admin_job_list(HashMap<String, String> map);

	int kakao_list_count(HashMap<String, String> map);

	ArrayList<Map> kakao_list(HashMap<String, String> map);

	int mail_list_count(HashMap<String, String> map);

	ArrayList<Map> mail_template_list(HashMap<String, String> map);

	int mail_template_list_count(HashMap<String, String> map);

	ArrayList<Map> mail_list(HashMap<String, String> map);

	int partnerreq_log_list_count(HashMap<String, String> map);

	ArrayList<Map> partnerreq_log_list(HashMap<String, String> map);

	int search_collection_list_count(HashMap<String, String> map);

	ArrayList<Map> search_collection_list(HashMap<String, String> map);

	ArrayList<Map> status_list_depth2(HashMap<String, String> map);

	/**
	 * 매칭 여부
	 * @param map_
	 * @return
	 */
	int count_matching_partner_rank(HashMap<String, String> map);

	ArrayList<Map> select_matching_rank(HashMap<String, String> map);

	int backup_matching_rank(HashMap<String, String> map);

	int delete_matching_rank(HashMap<String, String> map);

	void make_matching_rank(HashMap<String, String> map);

	ArrayList<Map> make_matching_data(HashMap<String, String> map);

	void make_matching_data_temp(HashMap<String, String> map);

	void set_sequence(HashMap<String, String> map);

	String get_sequence(HashMap<String, String> map);

	void update_matching_partner_rank(HashMap<String, String> map);

	ArrayList<Map> select_matching_partner_rank(HashMap<String, String> map);

	/**
	 * 매칭 sms 발송
	 * @param map
	 * @return
	 */
	Map partner_manager_alram_list(HashMap<String, String> map);

	int alram_insert(HashMap<String, String> map);

	void update_company_reqcnt(HashMap<String, String> map);

	void manage_project_status_change_accept(HashMap<String, String> map);

	void manage_project_status_change_reject(HashMap<String, String> map);

	void manage_project_apply_status_change_reject_partner(HashMap<String, String> map);

	void manage_project_apply_status_change_accept_partner(HashMap<String, String> map);

	Map manage_project_apply_count_info(HashMap<String, String> map);

	void manage_project_status_change_order_confirm_partner(HashMap<String, String> map);

	void manage_project_status_change_cancel(HashMap<String, String> map);

	void project_cancel_pmi_update_by_prpk(HashMap<String, String> map);

	ArrayList<Map> get_project_apply_popup(HashMap map);

	void manage_project_status_change_delete(HashMap<String, String> map);

	void manage_project_survey_req(HashMap<String, String> map);

	void manage_project_delete(HashMap<String, String> map);

	void project_md_eval_p_md_alram_displayexcept_insert(HashMap<String, String> map);

	void project_md_eval_p_md_alram_displayexcept_update(HashMap<String, String> map);

	ArrayList<Map> manage_partner_manager_list(HashMap<String, String> map);

	int check_tmplcode(HashMap map);

	/**
	 * 카테고리 관리, 삭제
	 * @param map
	 */
	void manage_category_delete(HashMap<String, String> map);

	void manage_category_recover(HashMap<String, String> map);

	void manage_category_change_ordering(HashMap map);

	void manage_category_update_is_main(HashMap<String, String> map);

	int delete_mail_template(HashMap<String, String> map);

	int delete_mail_info(HashMap<String, String> map);

	Map<String, String> get_payment_info(HashMap<String, String> map);

	ArrayList<Map> partner_matching_v2(HashMap<String, String> map);

	int partner_matching_v2_cnt(HashMap<String, String> map);

}
