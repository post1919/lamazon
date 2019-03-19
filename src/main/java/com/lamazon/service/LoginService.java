package com.lamazon.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface LoginService {
	public String login(HttpServletRequest request, String user_id, String user_passwd);

	public String login(HttpServletRequest request, String user_id, String user_passwd, String id_mem);

	public String setUser(HttpServletRequest request);
	
	public Map getPartnerInfo(HttpServletRequest request);

	public Map user_project_status_count_info(int uPk);

	public int getUPK(HttpServletRequest request);

	public boolean isLogin(HttpServletRequest request);

	public String getUserId(HttpServletRequest request);

	boolean isAdmin(HttpServletRequest request);

	boolean isLogin(HttpSession session);

	void logout(HttpServletRequest request);

	void logout(Map<?, ?> user);

	Map getUser(HttpSession session);

	int getUserCount();

	String getUNAME(HttpServletRequest request);

	Hashtable<String, Map<String, Object>> getLoginUsers();

	String getUserEmail(HttpServletRequest request);

	int getCPK(HttpServletRequest request);

	String getCID(HttpServletRequest request);

	boolean isUser(HttpServletRequest request);

	boolean isAgent(HttpServletRequest request);

	boolean isUserPhoneOk(HttpServletRequest request);

	boolean isUserCertified(HttpServletRequest request);

	public String user_info_check_id(String id);

	public int user_info_insert(HashMap map);

	public int user_info_update(HashMap<String, String> map);

	boolean isMember(HttpServletRequest request);

	int getPoint(HttpServletRequest request);
}
