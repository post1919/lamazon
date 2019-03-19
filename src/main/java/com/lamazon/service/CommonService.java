package com.lamazon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lamazon.model.AdminMenu;

public interface CommonService {

	public List<Map> categoryListTree() throws IOException;

	public List<Map> jongcodeListTree() throws IOException;

	public List<AdminMenu> adminMenuListTree(int parent, String status) throws IOException;

	public AdminMenu getAdminMenuFullname(String requestURI);

	List<AdminMenu> adminMenuListTreeAll() throws IOException;

	void setPQ_sort(String pq_sort, HashMap<String, String> map, String defaultOrdering);

	public int sejong_lms_insert(Map map);

	public List<Map> commonCodeList(Map map) throws IOException;

	Map<String, String> getUserByPK(String pk);

	public int user_notification_insert(HashMap<String, String> map);

	String getCategoryCode(int depth, int length);

	List<Map> categoryListByFather(Map map) throws IOException;

	void initCategoryInfo() throws IOException;

	Map makeMapInMap(List<? extends Map> list, String keyName);

	List<Map> categoryListTreeWithoutAll();

	void initJongCodeInfo() throws IOException;

	List<Map> jongcodeListByFather() throws IOException;

	void lockId_insert(String id, HashMap<String, Object> info);

	boolean lockIdIsMine(String id, HashMap<String, Object> info);

	boolean dupCheckU_ID(String id);

	boolean lockIdisValid(String id, HashMap<String, Object> info);

	void lockIdClean();

	void lockIdRemove(String ip, String sessionId);

	ArrayList categoryFilterListTree(String catecode);

	ArrayList categoryFilterInfo(String catecode) throws IOException;

	void initBlogCategoryInfo() throws IOException;

	void initCategoryInfoWithoutAll() throws IOException;

	Map category(String code) throws IOException;

	Map jongcode(String code) throws IOException;

	Map blog_category(String code) throws IOException;

	Map categoryByPk(String pk) throws IOException;

	Map upjongByPk(String pk) throws IOException;

	Map rfpByPk(String catecode, String pk) throws IOException;

	Map rfpTitleByCategory(String catecode) throws IOException;

	Map rfpContentByPk(String pk) throws IOException;

	ArrayList categoryListByFather(String father) throws IOException;

	ArrayList upjongListByFather(String father) throws IOException;

	ArrayList upjongListBy3th(String father) throws IOException;

	ArrayList blogCategoryListByFather(String father) throws IOException;

	ArrayList categoryFilterListByCateCode(String catecode) throws IOException;

	ArrayList categoryFilterContentByRtPk(String rt_pk) throws IOException;

	ArrayList blogCategoryListByFather(String father, String up_code) throws IOException;

	ArrayList categoryTree(String code) throws IOException;

	void syncUserProjectInfo(String uPk);

	void syncPartnerProjectInfo(String cPk);

	void syncProjectNum(String categoryCode);

	int getPartnerPk(String cid);

	void updateCategoryPartnerNumUpdate(String codes);

	int getProjectDuplChk(String uPk);

	Map getPartnerByPK(String pk);

	Map getPartnerByPK4Manager(String pk);

	Map getPartnerByID(String id);

	Map getPartnerByUPK(String id);

	Map getPartnerByCPK(String id);

	Map getProjectByID(String id);

	Map getProjectByPk(String pk);

	Map getProjectApplyByPK(String pk);

	Map getProjectApplyByID(String id);

	Map getProjectApplyByIDWithCPk(String id, String cpk);

	Map getReviewByIDForManager(String id);

	Map getPromotionById(String id);

	Map getPromotionByPk(String pk);

	Map getCompanyPersonByPAID(String pa_id);

	int getTotalNumOfProject();

	void syncAllProject() throws IOException;

	Map getPartnerStatementByCPK(String C_PK);

	int getTotalNumOfSales();

	Map getPartnerOutsourcingByCPK(String id);

	Map getPartnerOutsourcingFile(String CO_PK);

	public Map<String, String> mail_info_detail(Map<String, String> map);

	public Map<String, String> mail_info_template_detail(Map<String, String> map);

	public int xmail_save_info(Map<String, String> map);

	public ArrayList<Map<String, String>> user_notification_message_texts(HashMap<String, String> map);

	public String company_check_registration_number(String number);

	ArrayList<Map> category_list_by_father(Map map);

	ArrayList<Map> category_list_by_father_new(Map map);

	ArrayList<Map> category_list_depth2_by_father(Map map);

	ArrayList<Map> category_list_depth3_by_father(Map map);

	ArrayList<Map> category_list_depth1(Map map);

	ArrayList<Map> category_list_depth3(Map map);

	ArrayList<Map> category_list_depth2(Map map);

	List<Map> common_code_list(Map map) throws IOException;

	int getNewPk(String tableName, String[] map);

	int getNewPk(String tableName, Map<String, String[]> map, String defaul);

	int getNewPk(String tableName, Map<String, String> map);

	/**
	 * 입찰,사용자 정보
	 * @param code -> PR_PK
	 * @return
	 */
	public Map user_info_project_by_pk(int code);

	/**
	 * 카카오 알림 템블릿 가져오기
	 * @param titleKey
	 * @return
	 */
	public Map kakao_msg_mst_info(String titleKey);

	public void kakao_insert(HashMap<String, String> map);

	public Map get_project_allinfo(String prPk);

	/**
	 * 카테고리
	 * @param father
	 * @return
	 */
	public String category_max_depth(String father);

	public String category_max_pk();

	public void manage_category_update_fullname_all1();

	public void manage_category_update_fullname_all2();

	public void manage_category_update_fullname_all3();

	public int update_project_pr_status(HashMap<String, Object> map);

	public Map get_project_allinfo_by_prpk(String prPk);

	public Map user_info_all(int parseInt);

	public Map get_user_info_by_u_pk(Map map);

}
