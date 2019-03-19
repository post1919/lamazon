package com.lamazon.service.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.LoginMapper;
import com.lamazon.properties.Properties;
import com.lamazon.service.LoginService;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@Transactional
@Service("loginService")
//@Scope("application")
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	LoginMapper loginMapper;

	//이놈은 어디에 쓰려고 만들어놓았지 ?.?
	private static Hashtable<String,Map<String, Object>> loginUsers = new Hashtable<String,Map<String, Object>>();

	@Override
	public Map user_project_status_count_info(int uPk) {
		return loginMapper.user_project_status_count_info(uPk);
	}

	@Override
	public String login(HttpServletRequest request, String user_id, String user_passwd) {

		try {
			user_passwd = Utils.makePassword(user_passwd);
		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("user_passwd", user_passwd);

		Map userinfo = loginMapper.user_info_login(map);

		if(userinfo==null) {
			return Properties.JSON_MESSAGE_FAIL;
		} else {

			//로그인날짜 쌓기 20180828 신동아
			loginMapper.user_info_login_logindate(map);

			this.register(request, userinfo);

			JSONObject o = new JSONObject();
			o.put("result", "success");
			o.put("type", userinfo.get("U_TYPE"));
			//o.put("c_id", this.getCID(request)); //메인화면에서 파트너 로그인시 파트너 회원정보로 바로 이동

			return o.toString();
		}
	}

	/**
	 * 회원만 로그인 할 수 있음. 프로젝트 입찰 등록시에만 사용함.
	 * @param request
	 * @param queryKey
	 * @param arr
	 * @return
	 */
	@Override
	public String login(HttpServletRequest request, String user_id, String user_passwd, String id_mem) {
		String queryKey = "user_info_login";

		try {
			user_passwd = Utils.makePassword(user_passwd);
		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("user_passwd", user_passwd);

		Map userinfo = loginMapper.user_info_login(map);

		if(userinfo==null) {
			return Properties.JSON_MESSAGE_FAIL;
		} else {

			//로그인날짜 쌓기 20180828 신동아
			loginMapper.user_info_login_logindate(map);

			if( "2000".equals(userinfo.get("U_TYPE")) ){//고객일 경우에만 세션 등록
				this.register(request, userinfo);
			}

			JSONObject o = new JSONObject();
			o.put("result", "success");//성공은 했지만, 고객만 참여가능, 나머지는 오류
			o.put("type", userinfo.get("U_TYPE"));

			return o.toString();
		}
	}

	//사용자 세션 재설정
	@Override
	public String setUser(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", getUserId(request));
		Map userinfo = loginMapper.user_info_login_admin(map);

		if(userinfo==null) {
			return Properties.JSON_MESSAGE_FAIL;
		} else {
			this.register(request, userinfo);

			JSONObject o = new JSONObject();
			o.put("result", "success");//성공은 했지만, 고객만 참여가능, 나머지는 오류
			o.put("type", userinfo.get("U_TYPE"));
			o.put("c_id", getCID(request)); //메인화면에서 파트너 로그인시 파트너 회원정보로 바로 이동
			return o.toString();
		}
	}

	//로그인할때 세션설정하는부분
	public void register( HttpServletRequest request, Map user ){
		HttpSession session = request.getSession();
		user.put("session_id", session.getId());

		session.setAttribute("USER", user);

		if( user.get("U_TYPE") != null ){
			int uType = (int)user.get("U_TYPE");

			// 관리자
			if( uType == 1000 ){
				request.getSession().setAttribute(Properties.IS_ADMIN, true);

			// 고객사
			} else if( uType == 2000 ){
				request.getSession().setAttribute(Properties.IS_USER, true);
			}

		}

		loginUsers.put(session.getId(), user);
		session.setAttribute("USER", user);
	}

	@Override
	public boolean isLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("USER")==null) return false;
		return true;
	}

	@Override
	public boolean isLogin(HttpSession session) {
		if(session.getAttribute("USER")==null) return false;
		return true;
	}

	@Override
	public void logout(HttpServletRequest request) {
		Map user = this.getUser(request.getSession());
		logout(user);

		String fromSite = null;

		// FROM_SITE 를 로그아웃 후에도 세션 유지하도록 삽입
		if(request.getSession().getAttribute("FROM_SITE")!=null) {
			fromSite = (String)request.getSession().getAttribute("FROM_SITE");
		}

		request.getSession().invalidate();

		if(fromSite!=null) {
			request.getSession().setAttribute("FROM_SITE", fromSite);
		}
	}

	@Override
	public void logout(Map<?, ?> user) {

		if( user == null ) {
			System.out.println("[DEBUG]logout: user is null");
			return;
		}

		String sessionId = (String)user.get("session_id");

		loginUsers.remove(sessionId);

		try {
			user = null;
		} catch(Exception e) {
			System.out.println("[DEBUG]"+e.getMessage());
		} finally {
			user = null;
		}
	}

	@Override
	public Map getUser(HttpSession session) {

		if(session == null) {
			return null;
		}

		if( session.getAttribute("USER") != null ) {
			return (HashMap)session.getAttribute("USER");
		}

		return loginUsers.get(session.getId());
	}

	@Override
	public int getUserCount() {
		return loginUsers.size();
	}

	@Override
	public Hashtable<String, Map<String, Object>> getLoginUsers() {
		return loginUsers;
	}

	@Override
	public String getUNAME(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		return (String)userinfo.get("U_NAME");
	}

	@Override
	public String getUserId(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		return (String)userinfo.get("U_ID");
	}

	@Override
	public int getUPK(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		if(userinfo==null) return -1;
		return (int)userinfo.get("U_PK");
	}

	@Override
	public int getPoint(HttpServletRequest request) {
		if(!this.isLogin(request)) return -1;
		Map<String, Object> userinfo = this.getUser(request.getSession());
		return (int)userinfo.get("U_POINT");
	}

	@Override
	public String getUserEmail(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		if(userinfo==null) return null;
		return (String)userinfo.get("U_EMAIL");
	}

	@Override
	public int getCPK(HttpServletRequest request) {
		if(!this.isLogin(request)) return -1;
		if(isUser(request)) return -1;
		if(isAdmin(request)) return -1;
		Map<String, Object> userinfo = getPartnerInfo(request);
		return (int)userinfo.get("C_PK");
	}

	@Override
	public String getCID(HttpServletRequest request) {
		if(!this.isLogin(request)) return null;
		if(isUser(request)) return null;
		if(isAdmin(request)) return null;
		Map<String, Object> userinfo = getPartnerInfo(request);
		return (String)userinfo.get("C_ID");
	}

	@Override
	public boolean isMember(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());

		if( userinfo != null ){
			int uType = (int)userinfo.get("U_TYPE");

			if( uType == 1 || uType == 2 || uType == 3 || uType == 5 ){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean isAgent(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		int uType = (int)userinfo.get("U_TYPE");

		if( uType == 5 ){
			return true;
		}

		return false;
	}

	@Override
	public boolean isAdmin(HttpServletRequest request) {
		if(request.getSession().getAttribute(Properties.IS_ADMIN)!=null) return true;
		return false;
	}

	@Override
	public boolean isUser(HttpServletRequest request) {
		if(request.getSession().getAttribute(Properties.IS_USER)!=null) return true;
		return false;
	}

	@Override
	public boolean isUserPhoneOk(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());

		if( !"".equals(userinfo.get("U_PHONE")) ) { // 개인회원 등록 가능
			return true;
		}

		if( !"".equals(userinfo.get("U_MOBILE")) ){
			return true;
		}

		return false;
	}

	@Override
	public boolean isUserCertified(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());

		int u_type = (int)userinfo.get("U_TYPE");

		if( u_type == 1 ){ // 개인회원 등록 가능
			return true;
		}

		if( u_type == 2 ){ // 개인회원 등록 가능
			return true;
		}

		if( u_type == 3 ){ // 개인회원 등록 가능
			return true;
		}

		if( u_type == 4 ){ // 파트너 등록 불가
			return false;
		}

		if( u_type == 11 ){ // 관리자 등록 가능
			return true;
		}

		//개인회원 가입후 법인으로 변경시 의회하기 체크하지 않음
		//2016.10.21

		if( (int)userinfo.get("U_COMPANY_CERITIFY") == 3 ){
			return true;
		}

		return false;
	}

	@Override
	public String user_info_check_id(String id) {
		return loginMapper.user_info_check_id(id);
	}

	@Override
	public Map getPartnerInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int user_info_insert(HashMap map) {
		return loginMapper.user_info_insert(map);
	}

	@Override
	public int user_info_update(HashMap<String, String> map) {
		return loginMapper.user_info_update(map);
	}
}
