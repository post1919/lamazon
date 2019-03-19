package com.lamazon.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lamazon.service.AdminService;
import com.lamazon.service.LoginService;
import com.lamazon.util.CommonUtil;

@Controller
@RequestMapping("/")
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	CommonUtil util;

	@GetMapping
	public String index(HttpServletRequest request, HttpServletResponse response, Model model){

		if(loginService.isLogin(request)) {
			HashMap<String, String> map = new HashMap<String, String>();
			//당월 목표 달성 현황
			//model.addAttribute("MM_GOAL_REPORT", adminService.mm_goal_report(map));//DBManager.getInstance().makeMapArray("mm_goal_report", map, arr));

			//당월 일별 실적 현황
			//model.addAttribute("DD_GOAL_REPORT",adminService.dd_goal_report(map));// DBManager.getInstance().makeMapArray("dd_goal_report", map, arr));


			return "admin/admin_index";
		} else {
			return "login/admin_login";
		}

		//CommonUtil util = new CommonUtil();

		/*
		if( Utils.isLg(request) ){
			Form form = new Form(request,response);

			HashMap<String, String> map = new HashMap<String, String>();
			String arr[] = new String[] {};

			request.setAttribute("CATEGORY_LIST", DBManager.getInstance().categoryListTreeWithoutAll()); // 카테고리 전체 리스트

			request.setAttribute("TOTAL_PARTNER_COUNT", Integer.toString(DBManager.getInstance().countForce("partner_total_number_for_main", map, arr)));
			request.setAttribute("TOTAL_REVIEW_COUNT", Integer.toString(DBManager.getInstance().countForce("partner_reviews_all_count", map, arr)));
			request.setAttribute("TOTAL_PROJECT_COUNT", Integer.toString(DBManager.getInstance().countForce("project_total_number_for_main", map, arr)));


			ArrayList list = DBManager.getInstance().makeMapArray("main_review_partner", map, arr);
			HashMap p = (HashMap)list.get(0);
			String cPk = (String)p.get("C_PK");

			arr = new String[] {cPk};
			ArrayList list2 = DBManager.getInstance().makeMapArray("main_review_roll", map, arr);

			p.put("REVIEWS", list2);

			request.setAttribute("MAIN_REVIEW", p);



			arr = new String[] {};
			map.put("PAGING", "0,6");
			request.setAttribute("PROJECT_LIST", DBManager.getInstance().makeMapArray("project_list", map, arr));

			form.mapping(".index_lg");
		} else {

			Form form = new Form(request,response);

			//공통 쿠키관련 클래스
			//CookieUtil cookieUtil = new CookieUtil(request, response);

			HashMap<String, String> map = new HashMap<String, String>();
			String arr[] = new String[] {};

			request.setAttribute("CATEGORY_LIST", DBManager.getInstance().categoryListTreeWithoutAll()); // 카테고리 전체 리스트

			request.setAttribute("TOTAL_PARTNER_COUNT", Integer.toString(DBManager.getInstance().countForce("partner_total_number_for_main", map, arr)));
			request.setAttribute("TOTAL_PROJECT_PRICE", Integer.toString(DBManager.getInstance().countForce("project_total_price_for_main_new", map, arr)));
			request.setAttribute("TOTAL_PROJECT_COUNT", Integer.toString(DBManager.getInstance().countForce("project_total_number_for_main", map, arr)));

			arr = new String[] {};
			map.put("PAGING", "0,6");
			request.setAttribute("PROJECT_LIST", DBManager.getInstance().makeMapArray("project_list", map, arr));

			//전문업체 TOP5 - 파트너정보
			arr = new String[] {};
			map = new HashMap<String, String>();
			map.put("PAGING", "0,5");

			//1.온라인광고
			//arr = new String[] {"1001"};
			map.put("TOP5", "214712,215070,215627,215676,213607");

			ArrayList<Map> recommend_partner_list = DBManager.getInstance().makeMapArray("top5_list", map, arr);
			request.setAttribute("LIST1", recommend_partner_list);

			for( int i=0; i<recommend_partner_list.size(); i++ ) {
				Map recommendMap = recommend_partner_list.get(i);

				if( recommendMap.get("C_DESCRIPTION") != null && !"".equals(recommendMap.get("C_DESCRIPTION")) ){
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}

			//2.오프라인마케팅
			//arr = new String[] {"1002"};
			map.put("TOP5", "213690,215361,215506,215458,215111");
			recommend_partner_list = DBManager.getInstance().makeMapArray("top5_list", map, arr);
			request.setAttribute("LIST2", recommend_partner_list);

			for( int i=0; i<recommend_partner_list.size(); i++ ) {
				Map recommendMap = recommend_partner_list.get(i);

				if( recommendMap.get("C_DESCRIPTION") != null && !"".equals(recommendMap.get("C_DESCRIPTION")) ){
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}


			//3. 영상광고/제작
			//arr = new String[] {"1007"};
			map.put("TOP5", "215624,214750,215307,215691,213493");
			request.setAttribute("LIST3", DBManager.getInstance().makeMapArray("top5_list", map, arr));

			for( int i=0; i<recommend_partner_list.size(); i++ ) {
				Map recommendMap = recommend_partner_list.get(i);

				if( recommendMap.get("C_DESCRIPTION") != null && !"".equals(recommendMap.get("C_DESCRIPTION")) ){
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}

			//4.기업행사/이벤트
			//arr = new String[] {"1006"};
			map.put("TOP5", "215533,213644,215586,215641,213755");
			recommend_partner_list = DBManager.getInstance().makeMapArray("top5_list", map, arr);
			request.setAttribute("LIST4", recommend_partner_list);

			for( int i=0; i<recommend_partner_list.size(); i++ ) {
				Map recommendMap = recommend_partner_list.get(i);

				if( recommendMap.get("C_DESCRIPTION") != null && !"".equals(recommendMap.get("C_DESCRIPTION")) ){
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}

			//5.웹제작/디자인
			//arr = new String[] {"2080"};
			map.put("TOP5", "213744,213256,215695,215659");
			recommend_partner_list = DBManager.getInstance().makeMapArray("top5_list", map, arr);
			request.setAttribute("LIST5", recommend_partner_list);

			for( int i=0; i<recommend_partner_list.size(); i++ ) {
				Map recommendMap = recommend_partner_list.get(i);

				if( recommendMap.get("C_DESCRIPTION") != null && !"".equals(recommendMap.get("C_DESCRIPTION")) ){
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					recommendMap.put("C_DESCRIPTION", recommendMap.get("C_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}

			map = new HashMap<String, String>();
			map.put("STATUS_RECRUIT_ING", "STATUS_RECRUIT_ING");

			request.setAttribute("PROJECT_CNT", DBManager.getInstance().makeMapArray("project_list_new", map, new String[] {}).size() );

			map.put("PAGING", "0,5");
			//request.setAttribute("PROJECT_LIST", DBManager.getInstance().makeMapArray("project_list_new", map, new String[] {}));


			ArrayList<Map> project_list = DBManager.getInstance().makeMapArray("project_list_new", map, new String[] {});
			request.setAttribute("PROJECT_LIST", project_list);

			for( int i=0; i<project_list.size(); i++ ) {
				Map specialMap = project_list.get(i);
				if( specialMap.get("PR_DESCRIPTION") != null && !"".equals(specialMap.get("PR_DESCRIPTION")) ){
					specialMap.put("PR_DESCRIPTION", specialMap.get("PR_DESCRIPTION").toString().replaceAll("&quot;", "\""));
					specialMap.put("PR_DESCRIPTION", specialMap.get("PR_DESCRIPTION").toString().replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9-]*=[^>]*)?(\\s)*(/)?>", ""));
					specialMap.put("PR_DESCRIPTION", specialMap.get("PR_DESCRIPTION").toString().replaceAll("&nbsp;", " "));
				}
			}

			map = new HashMap<String, String>();
			map.put("JI_PK_IN", "274,319,170,237,188,177,227");
			map.put("ORDERBY", "JI.JI_PK DESC");

			//블로그 컨텐츠 목록
			ArrayList blogList = DBManager.getInstance().makeMapArray("blog_list", map, null);

			ArrayList<Map> newBlogList = new ArrayList<Map>();
			//대표이미지가 실제 서버에 파일이 있는지 체크하기 (NOIMAGE 파일 보여주기위해 했음)
			File file;
			boolean isExist = false;
			Map<String, String> listMap;
			for(int i=0; i<blogList.size(); i++){
				listMap = (HashMap<String,String>)blogList.get(i);
				String JI_MAIN_IMG_FILE_RE = listMap.get("JI_MAIN_IMG_FILE_RE");

				//이미지파일정보가 없는경우
				if( "".equals(JI_MAIN_IMG_FILE_RE) ){
					isExist = false;
				} else {
					file = new File(Properties.PATH_BLOG_MAIN_IMG + JI_MAIN_IMG_FILE_RE);
					isExist = file.exists();
				}

				listMap.put("FILE_ISEXIST", isExist == true ? "Y" : "N");
				newBlogList.add(listMap);
			}
			request.setAttribute("BLOG_LIST", newBlogList);
			//고객추천! 탁월한 팀과 전문가들[e]

			//랜딩쿠키가 있다면 해당 랜딩으로 이동
			//랜딩신청 완료시 쿠키는 삭제해 줌
			String goURL = "";

			Cookie[] cookies = request.getCookies();

			if( cookies != null ){
				for( int i=0;i<cookies.length; i++ ){
					String key = cookies[i].getName();

					if(key.equals("LandingFromSite")){
						String value = cookies[i].getValue();
						request.setAttribute("LANDING_FROM_SITE", value );
					}
				}
			}

			CookieUtil cookieUtil = new CookieUtil(request, response);
			//로그인 안했을경우만
			if( !LoginManager.getInstance().isLogin(request.getSession()) ){
				String name  = "";
				String category = null;
				//쿠키정보 유무(신규/재방문)에 따른 띄울팝업 페이지 선택
				if( cookieUtil.exists("CASTINGN_INFO") ){
					String cookieGubun = cookieUtil.getValue("CASTINGN_INFO", "GUBUN");
					//CATEGORY_CUR(최근카테고리) 값 가져오기
					category = cookieUtil.getValue("CASTINGN_INFO", "CATEGORY_CUR");

					//최근 접근한 카테고리 정보가 있다면
					//재방문
					if( category != null && cookieGubun != null){
						if( category.length() > 4 ){
							category = category.substring(0, 4);
						}

						HashMap<String, String> catemap = new HashMap<String, String>();
						catemap.put("CATE0", "'"+category+"'");

						Map cateNameMap = DBManager.getInstance().makeMap("category_list_depth1", catemap, new String[] {});

						if( cateNameMap != null ){
							if( cateNameMap.containsKey("NAME") ){
								name = cateNameMap.get("NAME").toString();
							}
						}

						if( !"".equals(name) ){
							//마케팅 - A-01, A-02, A-05, A-08
							if( "A-01".equals(category) || "A-02".equals(category) || "A-05".equals(category) || "A-08".equals(category) ){
								request.setAttribute("landingPopupUrl",   "/popup/marketingAutomation/popup_06.jsp");
								request.setAttribute("landingPopupParam", "{\"name\":\""+name+"\", \"width\":\"512\", \"height\":\"847\" }");
							} else {
								//인사총무
								request.setAttribute("landingPopupUrl",   "/popup/marketingAutomation/popup_07.jsp");
								request.setAttribute("landingPopupParam", "{\"name\":\""+name+"\", \"width\":\"512\", \"height\":\"847\" }");
							}
						}
					}
				}
			}

			//로그인상태
			if( LoginManager.getInstance().isLogin(request) ){
				String name = LoginManager.getInstance().getUNAME(request);

				//파트너
				if( LoginManager.getInstance().isUser(request) ){

					map = new HashMap<String, String>();
					String cPk = LoginManager.getInstance().getCPK(request);

					//미처리현황 개수 체크(입찰진행 4건이상 상담업데이트나 견적/제안 하지않은경우 인지 체크)
					Map untreatedCount = DBManager.getInstance().makeMap("get_project_untreated_count", map, new String[]{cPk,cPk,cPk,cPk,cPk,cPk,cPk});

					if( untreatedCount != null ){
						if( untreatedCount.size() > 0 ){
							request.setAttribute("untreatedCount", untreatedCount.get("UNTREATED_CNT"));
							request.setAttribute("u_name", name);
						}
					}

					request.setAttribute("HAS_PERMISSION", "has_permission_partner");

				//사용자
				} else if( LoginManager.getInstance().isUser(request) ){
					String uPk = LoginManager.getInstance().getUPK(request);

					//미처리현황 개수 체크(입찰진행 4건이상 상담업데이트나 견적/제안 하지않은경우 인지 체크)
					Map untreatedCount = DBManager.getInstance().makeMap("get_project_untreated_suggestion_count", map, new String[]{uPk});

					if( untreatedCount != null ){
						if( untreatedCount.size() > 0 ){
							request.setAttribute("untreatedCount", untreatedCount.get("UNTREATED_CNT"));
							request.setAttribute("u_name", name);
						}
					}

					request.setAttribute("HAS_PERMISSION", "has_permission_user");
					request.setAttribute("u_name", name);
				}
			}

			//오늘날짜 가져오기
			request.setAttribute("getYyyymmdd", util.getYyyymmdd());

			//유사 카테고리 클릭시 페이지 초기 로딩 위치
			String viewpoint = form.getString("viewpoint","");
			request.setAttribute("viewpoint", viewpoint);

			if(LoginManager.getInstance().isAdmin(request)) {
				//form.mapping(".admin_main");
				request.setAttribute("admin", "y");
				form.mapping(".index_new");
			} else {
				if( "print".equals(goURL) ){
					form.mapping(".landing_print_form");
				} else {
					form.mapping(".index_new");
				}
			}

		}
		*/



	}
}
