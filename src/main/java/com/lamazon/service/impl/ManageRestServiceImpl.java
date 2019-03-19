package com.lamazon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.ManageRestMapper;
import com.lamazon.service.ManageRestService;

@Transactional
@Service
//@Scope("application")
public class ManageRestServiceImpl implements ManageRestService {
	private static final Logger logger = LoggerFactory.getLogger(ManageRestServiceImpl.class);

	@Autowired
	ManageRestMapper manageRestMapper;

	@Override
	public String category_code_all_by_self_with_pipe(HashMap<String, String> map) {
		return manageRestMapper.category_code_all_by_self_with_pipe(map);
	}

	@Override
	public int manage_partner_count(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_count(map);
	}

	@Override
	public ArrayList<Map<String, String>> manage_partner_list(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_list(map);
	}

	@Override
	public int manage_review_count(HashMap<String, String> map) {
		return manageRestMapper.manage_review_count(map);
	}

	@Override
	public ArrayList<Map> manage_review_list(HashMap<String, String> map) {
		return manageRestMapper.manage_review_list(map);
	}

	@Override
	public int manage_partner_outsourcing_count(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_outsourcing_count(map);
	}

	@Override
	public ArrayList<Map<String, String>> manage_partner_outsourcing_list(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_outsourcing_list(map);
	}

	@Override
	public ArrayList<Map> manage_partner_outsourcing_category(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_outsourcing_category(map);
	}

	@Override
	public Map partner_by_pk_for_manager(HashMap<String, String> map) {
		return manageRestMapper.partner_by_pk_for_manager(map);
	}

	@Override
	public int manage_partner_status_change_accept(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_status_change_accept(map);
	}

	@Override
	public int manage_user_status_change_accept(HashMap<String, String> map) {
		return manageRestMapper.manage_user_status_change_accept(map);
	}

	@Override
	public int manage_partner_status_change_reject(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_status_change_reject(map);
	}

	@Override
	public int manage_user_status_change_reject(HashMap<String, String> map) {
		return manageRestMapper.manage_user_status_change_reject(map);
	}

	@Override
	public int manage_user_change_password(HashMap<String, String> map) {
		return manageRestMapper.manage_user_change_password(map);
	}

	@Override
	public int manage_partner_change_status(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_change_status(map);
	}

	@Override
	public Map partner_manager(HashMap<String, String> map) {
		return manageRestMapper.partner_manager(map);
	}

	@Override
	public int partner_project_count(HashMap<String, String> map) {
		return manageRestMapper.partner_project_count(map);
	}

	@Override
	public ArrayList<Map> partner_project_list(HashMap<String, String> map) {
		return manageRestMapper.partner_project_list(map);
	}

	@Override
	public int manage_partner_change_id(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_change_id(map);
	}

	@Override
	public int manage_partner_delete(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_delete(map);
	}

	@Override
	public int manage_user_delete(HashMap<String, String> map) {
		return manageRestMapper.manage_user_delete(map);
	}

	@Override
	public int manage_partner_updatelist_count(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_updatelist_count(map);
	}

	@Override
	public ArrayList<Map> manage_partner_updatelist(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_updatelist(map);
	}

	@Override
	public int manage_partner_call_050_list_count(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_call_050_list_count(map);
	}

	@Override
	public ArrayList<Map> manage_partner_call_050_list(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_call_050_list(map);
	}

	@Override
	public int matching_data_count(HashMap<String, String> map) {
		return manageRestMapper.matching_data_count(map);
	}

	@Override
	public ArrayList<Map> matching_data(HashMap<String, String> map) {
		return manageRestMapper.matching_data(map);
	}

	@Override
	public int admin_hometax_list_count(HashMap<String, String> map) {
		return manageRestMapper.admin_hometax_list_count(map);
	}

	@Override
	public ArrayList<Map> admin_hometax_list(HashMap<String, String> map) {
		return manageRestMapper.admin_hometax_list(map);
	}

	@Override
	public int admin_hometax_faillist_count(HashMap<String, String> map) {
		return manageRestMapper.admin_hometax_faillist_count(map);
	}

	@Override
	public ArrayList<Map> admin_hometax_faillist(HashMap<String, String> map) {
		return manageRestMapper.admin_hometax_faillist(map);
	}

	@Override
	public ArrayList<Map> manage_category_list_by_father(HashMap<String, String> map) {
		return manageRestMapper.manage_category_list_by_father(map);
	}

	@Override
	public int manage_promotion_count(HashMap<String, String> map) {
		return manageRestMapper.manage_promotion_count(map);
	}

	@Override
	public ArrayList<Map> manage_promotion_list(HashMap<String, String> map) {
		return manageRestMapper.manage_promotion_list(map);
	}

	@Override
	public int admin_job_count(HashMap<String, String> map) {
		return manageRestMapper.admin_job_count(map);
	}

	@Override
	public ArrayList<Map> admin_job_list(HashMap<String, String> map) {
		return manageRestMapper.admin_job_list(map);
	}

	@Override
	public int kakao_list_count(HashMap<String, String> map) {
		return manageRestMapper.kakao_list_count(map);
	}

	@Override
	public ArrayList<Map> kakao_list(HashMap<String, String> map) {
		return manageRestMapper.kakao_list(map);
	}

	@Override
	public int mail_list_count(HashMap<String, String> map) {
		return manageRestMapper.mail_list_count(map);
	}

	@Override
	public ArrayList<Map> mail_template_list(HashMap<String, String> map) {
		return manageRestMapper.mail_template_list(map);
	}

	@Override
	public int mail_template_list_count(HashMap<String, String> map) {
		return manageRestMapper.mail_template_list_count(map);
	}

	@Override
	public ArrayList<Map> mail_list(HashMap<String, String> map) {
		return manageRestMapper.mail_list(map);
	}

	@Override
	public int partnerreq_log_list_count(HashMap<String, String> map) {
		return manageRestMapper.partnerreq_log_list_count(map);
	}

	@Override
	public ArrayList<Map> partnerreq_log_list(HashMap<String, String> map) {
		return manageRestMapper.partnerreq_log_list(map);
	}

	@Override
	public int search_collection_list_count(HashMap<String, String> map) {
		return manageRestMapper.search_collection_list_count(map);
	}

	@Override
	public ArrayList<Map> search_collection_list(HashMap<String, String> map) {
		return manageRestMapper.search_collection_list(map);
	}

	@Override
	public String category_code_all_by_self(HashMap<String, String> map) {
		return manageRestMapper.category_code_all_by_self(map);
	}

	@Override
	public ArrayList<Map> status_list_depth2(HashMap<String, String> map) {
		return manageRestMapper.status_list_depth2(map);
	}

	/**
	 * 매칭 여부
	 */


	@Override
	public void make_matching_rank(HashMap<String, String> map) {
		manageRestMapper.make_matching_rank(map);
	}

	@Override
	public int count_matching_partner_rank(HashMap<String, String> map) {
		return manageRestMapper.count_matching_partner_rank(map);
	}

	@Override
	public ArrayList<Map> select_matching_rank(HashMap<String, String> map) {
		return manageRestMapper.select_matching_rank(map);
	}

	@Override
	public int backup_matching_rank(HashMap<String, String> map) {
		return manageRestMapper.backup_matching_rank(map);
	}

	@Override
	public int delete_matching_rank(HashMap<String, String> map) {
		return manageRestMapper.delete_matching_rank(map);
	}

	@Override
	public ArrayList<Map> make_matching_data(HashMap<String, String> map) {
		return manageRestMapper.make_matching_data(map);
	}

	@Override
	public void make_matching_data_temp(HashMap<String, String> map) {
		manageRestMapper.make_matching_data_temp(map);
	}

	@Override
	public void set_sequence(HashMap<String, String> map) {
		manageRestMapper.set_sequence(map);
	}

	@Override
	public String get_sequence(HashMap<String, String> map) {
		return manageRestMapper.get_sequence(map);
	}

	/**
	 * 매칭 리스트
	 */
	@Override
	public void update_matching_partner_rank(HashMap<String, String> map) {
		 manageRestMapper.update_matching_partner_rank(map);
	}

	@Override
	public ArrayList<Map> select_matching_partner_rank(HashMap<String, String> map) {
		return manageRestMapper.select_matching_partner_rank(map);
	}

	@Override
	public Map partner_manager_alram_list(HashMap<String, String> map) {
		return manageRestMapper.partner_manager_alram_list(map);
	}

	@Override
	public int alram_insert(HashMap<String, String> map) {
		return manageRestMapper.alram_insert(map);
	}

	@Override
	public void update_company_reqcnt(HashMap<String, String> map) {
		manageRestMapper.update_company_reqcnt(map);
	}

	/**
	 * 관리자 입찰 승인
	 */
	@Override
	public void manage_project_status_change_accept(HashMap<String, String> map) {
		manageRestMapper.manage_project_status_change_accept(map);
	}

	@Override
	public void manage_project_status_change_reject(HashMap<String, String> map) {
		manageRestMapper.manage_project_status_change_reject(map);
	}

	@Override
	public void manage_project_apply_status_change_reject_partner(HashMap<String, String> map) {
		manageRestMapper.manage_project_apply_status_change_reject_partner(map);
	}

	@Override
	public void manage_project_apply_status_change_accept_partner(HashMap<String, String> map) {
		manageRestMapper.manage_project_apply_status_change_accept_partner(map);
	}

	@Override
	public Map manage_project_apply_count_info(HashMap<String, String> map) {
		return manageRestMapper.manage_project_apply_count_info(map);
	}

	@Override
	public void manage_project_status_change_order_confirm_partner(HashMap<String, String> map) {
		manageRestMapper.manage_project_status_change_order_confirm_partner(map);
	}

	/**
	 * 의뢰 취소
	 */
	@Override
	public void manage_project_status_change_cancel(HashMap<String, String> map) {
		manageRestMapper.manage_project_status_change_cancel(map);
	}

	@Override
	public void project_cancel_pmi_update_by_prpk(HashMap<String, String> map) {
		manageRestMapper.project_cancel_pmi_update_by_prpk(map);	}

	@Override
	public ArrayList<Map> get_project_apply_popup(HashMap map) {
		return manageRestMapper.get_project_apply_popup(map);
	}

	/**
	 * 그냥 삭제
	 */
	@Override
	public void manage_project_status_change_delete(HashMap<String, String> map) {
		manageRestMapper.manage_project_status_change_delete(map);
	}

	@Override
	public void manage_project_survey_req(HashMap<String, String> map) {
		manageRestMapper.manage_project_survey_req(map);
	}

	/**
	 * 의뢰 삭제
	 */
	@Override
	public void manage_project_delete(HashMap<String, String> map) {
		manageRestMapper.manage_project_delete(map);
	}

	/**
	 * 알람표시 제외
	 */
	@Override
	public void project_md_eval_p_md_alram_displayexcept_insert(HashMap<String, String> map) {
		manageRestMapper.project_md_eval_p_md_alram_displayexcept_insert(map);
	}

	@Override
	public void project_md_eval_p_md_alram_displayexcept_update(HashMap<String, String> map) {
		manageRestMapper.project_md_eval_p_md_alram_displayexcept_update(map);
	}

	/**
	 * 파트너 담당자 리스트
	 */
	@Override
	public ArrayList<Map> manage_partner_manager_list(HashMap<String, String> map) {
		return manageRestMapper.manage_partner_manager_list(map);
	}

	@Override
	public int check_tmplcode(HashMap map) {
		return manageRestMapper.check_tmplcode(map);
	}

	/**
	 * 카테고리 관리 - 삭제
	 */
	@Override
	public void manage_category_delete(HashMap<String, String> map) {
		manageRestMapper.manage_category_delete(map);
	}

	@Override
	public void manage_category_recover(HashMap<String, String> map) {
		manageRestMapper.manage_category_recover(map);
	}

	@Override
	public void manage_category_change_ordering(HashMap map) {
		manageRestMapper.manage_category_change_ordering(map);
	}

	@Override
	public void manage_category_update_is_main(HashMap<String, String> map) {
		manageRestMapper.manage_category_update_is_main(map);
	}

	@Override
	public int delete_mail_template(HashMap<String, String> map) {
		return manageRestMapper.delete_mail_template(map);
	}

	@Override
	public int delete_mail_info(HashMap<String, String> map) {
		return manageRestMapper.delete_mail_info(map);
	}

	@Override
	public Map<String, String> get_payment_info(HashMap<String, String> map) {
		return manageRestMapper.get_payment_info(map);
	}

	@Override
	public ArrayList<Map> partner_matching_v2(HashMap<String, String> map) {
		return manageRestMapper.partner_matching_v2(map);
	}

	@Override
	public int partner_matching_v2_cnt(HashMap<String, String> map) {
		return manageRestMapper.partner_matching_v2_cnt(map);
	}

}
