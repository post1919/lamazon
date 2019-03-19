package com.lamazon.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lamazon.properties.Properties;
import com.lamazon.service.LoginService;

//사용안함~~~ => LoginServiceImpl로 옮김
//@Component
public class LoginManager {

	//@Autowired
	LoginService loginService;

	//이놈은 어디에 쓰려고 만들어놓았지~~~~~~~~~
	private static Hashtable<String,Map<String, Object>> loginUsers = new Hashtable<String,Map<String, Object>>();

	//로그인할때 세션설정하는부분
	public void register( HttpServletRequest request, Map user ){
		HttpSession session = request.getSession();
		user.put("session_id", session.getId());

		//고객일경우 의뢰상태별 건수 조회함
		if( isUser(request) ){
			int uPk = (int)user.get("U_PK");
			HashMap<String, String> map = new HashMap<String, String>();
			Map userProjectInfoMap = loginService.user_project_status_count_info(uPk);

			session.setAttribute("USER_PROJECT_INFO", userProjectInfoMap);
		}

		if( user.get("U_TYPE") != null ){
			int uType = (int)user.get("U_TYPE");

			// 관리자
			if( uType == 1000 ){
				request.getSession().setAttribute(Properties.IS_ADMIN, true);

			// 개인
			} else if( uType == 2000 ){
				request.getSession().setAttribute(Properties.IS_USER, true);
			}

		}

		loginUsers.put(session.getId(), user);
		session.setAttribute("USER", user);
	}

	public boolean isLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("USER")==null) return false;
		return true;
	}

	public boolean isLogin(HttpSession session) {
		if(session.getAttribute("USER")==null) return false;
		return true;
	}

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

	public Map getUser(HttpSession session) {
		if(session == null) {
			return null;
		}

		if( session.getAttribute("USER") != null ) {
			return (HashMap)session.getAttribute("USER");
		}

		return loginUsers.get(session.getId());
	}

	public int getUserCount() {
		return loginUsers.size();
	}

	public Hashtable<String, Map<String, Object>> getLoginUsers() {
		return loginUsers;
	}

	/**
	 * 회원만 로그인 할 수 있음. 프로젝트 입찰 등록시에만 사용함.
	 * @param request
	 * @param queryKey
	 * @param arr
	 * @return
	 */
	/*
	private String login(HttpServletRequest request, String queryKey, String arr[], String id_mem) {
		HashMap<String, String> map = new HashMap<String, String>();

		Map userinfo = null;
		try {
			userinfo = DBManager.getInstance().makeMap(queryKey, map, arr);
		} catch (IOException e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}

		if(userinfo==null) {
			return Properties.JSON_MESSAGE_FAIL;
		} else {

			//로그인날짜 쌓기 20180828 신동아
			try {
				DBManager.getInstance().insert_table(queryKey+"_logindate", map, arr);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if( "1".equals(userinfo.get("U_TYPE")) ){//고객일 경우에만 세션 등록
				this.register(request, userinfo);
			}

			JSONObject o = new JSONObject();
			o.put("result", "success");//성공은 했지만, 고객만 참여가능, 나머지는 오류
			o.put("type", userinfo.get("U_TYPE"));

			return o.toString();
		}
	}
	*/

	public String getUNAME(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		return (String)userinfo.get("U_NAME");
	}

	public String getUserId(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		return (String)userinfo.get("U_ID");
	}

	public int getUPK(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		if(userinfo==null) return -1;
		return (int)userinfo.get("U_PK");
	}

	public String getUserEmail(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());
		if(userinfo==null) return null;
		return (String)userinfo.get("U_EMAIL");
	}

	public boolean isMember(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());

		if( userinfo != null ){
			int uType = (int)userinfo.get("U_TYPE");

			if( uType == 1000 || uType == 2000 ){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isAdmin(HttpServletRequest request) {
		if(request.getSession().getAttribute(Properties.IS_ADMIN)!=null) return true;
		return false;
	}

	public boolean isUser(HttpServletRequest request) {
		if(request.getSession().getAttribute(Properties.IS_USER)!=null) return true;
		return false;
	}

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

	public boolean isUserCertified(HttpServletRequest request) {
		Map<String, Object> userinfo = this.getUser(request.getSession());

		int u_type = (int)userinfo.get("U_TYPE");

		if( u_type == 2000 ){ // 개인회원 등록 가능
			return true;
		}

		if( u_type == 1000 ){ // 관리자 등록 가능
			return true;
		}

		return false;
	}
}