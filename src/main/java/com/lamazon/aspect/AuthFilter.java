package com.lamazon.aspect;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lamazon.model.AdminMenu;
import com.lamazon.properties.Properties;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;

//import lamazon.util.DBManager;

@Aspect
@Component
//@Scope("application")
public class AuthFilter {

	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	//@Autowired(required=false)
	//DataSource dataSource;

	@Autowired
	//@Qualifier("partnerService")
	CommonService commonService;

	@Autowired
	//@Qualifier("partnerService")
	LoginService loginService;

	public String go_login(HttpServletRequest request, HttpServletResponse response, String returnURL) throws IOException {
		String url = "/login/adminLogin?returnURL="+returnURL;
		//response.sendRedirect(url);
		return "redirect:"+url;
	}

	public String go_mlogin(HttpServletRequest request, HttpServletResponse response, String returnURL) throws IOException {
		String url = "/mlogin.cast?returnURL="+returnURL;
		//response.sendRedirect(url);
		return "redirect:"+url;
	}

	@Around("execution(* *..*Controller.*(..))")
//	@ModelAttribute(value="adminMenu")
	public Object log(ProceedingJoinPoint jp) throws Throwable {

		logger.info("메서드 시작 [AuthFilter] : " + jp.getSignature());

		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

			HttpServletRequest request   = servletRequestAttributes.getRequest();
			HttpServletResponse response = servletRequestAttributes.getResponse();

			String requestURI = request.getRequestURI();

			String uri   = request.getRequestURI();
            String query = request.getQueryString();

            //로그인 queryString 처리 & , amp; 가 중복으로 발생되는 경우 있어 방지
            if( query != null ){
            	query = query.replace("amp;", "");
            }

            String currentURL = uri;
            if(query!=null && query.length()>0) {
            	currentURL += "?"+query;
            }

            request.setCharacterEncoding("UTF-8");// 인코딩 문제로 AuthFilter에서 먼저 호출

            String returnURL = URLEncoder.encode(currentURL, "UTF-8");

            //request.setAttribute("CATEGORY_LIST", commonService.categoryListTree()); // 카테고리 전체 리스트

            //Map 쌓고 있는데 가지고 있을 필요있나 메모리잡아먹음. 모니터링해야함
            //request.setAttribute("JONGCODE_LIST", commonService.jongcodeListTree()); // 공통코드 전체 리스트

            // 로봇 회원가입으로 회원가입시 referer 체크 2016.10.16
            // 방화벽 도입시 특정아이피 막을수 있도록 함
			if( request.getHeader("referer") != null ){

	            String sReferer = request.getHeader("referer").substring(10);
	            String sMakeReferer = "";

	            // indexOf를 이용한 방법
	            if(sReferer.indexOf("/") > -1){
	            	sMakeReferer = sReferer.substring(sReferer.indexOf("/"));
	            }

	            if("/join/user/done".equals(uri)) {
	            	if("/join/user/form".equals(sMakeReferer) || "/join/user/mform".equals(sMakeReferer) || "/landingEnroll?landingtype=landing_gifticon_join".equals(sMakeReferer)
	            		|| "/join/user/form?LandingType=landing_gifticon_join".equals(sMakeReferer) || "/event/view?code=E-06".equals(sMakeReferer) ){
	            		;
	            	} else {
	            		return "/";
	            	}
	            } else if("/join/partner/done".equals(uri)) {
	            	//관리자 파트너 등록
	            	if("/join/partner/form".equals(sMakeReferer) || "/manage/partner/form".equals(sMakeReferer) ) {
	            		;
	            	} else {
	            		return "/";
	            	}
	            }
            }

            //어드민 메뉴
			List<AdminMenu> adminMenuList = commonService.adminMenuListTree(0, "1");
			request.setAttribute("ADMIN_MENU", adminMenuList);

			if( request.getAttribute("MENU_FULL_NAME") == null ) {
				//현재화면에 해당하는 URI의 FULLNAME 가져오기
				AdminMenu menuFullName = commonService.getAdminMenuFullname(requestURI);
				request.setAttribute("MENU_FULL_NAME", menuFullName);
			}

        	// http 연결을 모두 https 로 리다이렉트 시킴.
        	/*if("http".equals(request.getScheme())) {
        		if("www.lamazon.co.kr".equals(request.getServerName())) {
        			String url = "http://lamazon.co.kr"+currentURL;
            		//response.sendRedirect(url);
            		return "redirect:"+url;
        		}
        	}*/

        	if(currentURL.startsWith("/rest")) { //  rest가 비정상적인 접근일 경우 리턴 없음.

        		if(request.getHeader("referer")!=null) {
        			String referer = request.getHeader("referer");

        			/*
        			if(referer.startsWith(request.getScheme()+"://"+request.getServerName())) {

        			} else {
        				System.out.println("비인가 접속이 감지됨 "+request.getRemoteAddr());
        				response.setContentType("application/json");
            		    response.setCharacterEncoding("UTF-8");
            		    response.getWriter().write(Properties.JSON_MESSAGE_NEED_LOGIN);
            			return "/";
        			}
        			*/
        		}
        	}

        	request.setAttribute("RETURN_URL", returnURL);

        	if(currentURL.startsWith("/rest/my")) {
        		if(loginService.isLogin(request)) {

        		} else {
        			response.setContentType("application/json");
        		    response.setCharacterEncoding("UTF-8");
        		    response.getWriter().write(Properties.JSON_MESSAGE_NEED_LOGIN);
        			return "/";
        		}
        	} else if(currentURL.startsWith("/rest/manage")) {
        		if(loginService.isAdmin(request)||loginService.isUser(request)) {

        		} else {
        			response.setContentType("application/json");
        		    response.setCharacterEncoding("UTF-8");
        		    response.getWriter().write(Properties.JSON_MESSAGE_NO_PERMISSION);
        			return "/";
        		}
        	} else if( currentURL.startsWith("/manage/excel") && !currentURL.startsWith("/manage/excelUpload") ) {
    			//return "exception/no_permission";

        	} else if( currentURL.startsWith("/admin/user/list") || currentURL.startsWith("/admin/manage") ) {
        		if(loginService.isLogin(request.getSession())) {
        			if(loginService.isAdmin(request)) {

        			} else {
        				return "exception/no_permission";
        			}
        		} else {
        			return go_login(request, response, returnURL);
        		}

        	} else if( currentURL.startsWith("/admin") ) {
        		if(loginService.isLogin(request.getSession())) {
        			/*if(loginService.isAdmin(request)) {

        			} else {
        				return "exception/no_permission";
        			}*/
        		} else {
        			return go_login(request, response, returnURL);
        		}

        	} else if( currentURL.startsWith("/order") || currentURL.startsWith("/bbs") ) {
        		if(loginService.isLogin(request.getSession())) {
        			/*if(loginService.isAdmin(request)) {

        			} else {
        				return "exception/no_permission";
        			}*/
        		} else {
        			return go_login(request, response, returnURL);
        		}

/*
        	} else if( currentURL.endsWith("/auth") ) {
        		if(loginService.isLogin(request.getSession())) {
        			if( loginService.isAdmin(request) ) {

        			} else {
        				return "exception/no_permission";
        			}
        		} else {
        			return go_login(request, response, returnURL);
        		}
*/
        	}


        	request.setAttribute("CURRENT_URI", requestURI);
        	request.setAttribute("CURRENT_URL", currentURL);

        	//로그 쌓기
    		//LogManager.getInstance().doLog(request, response);

            Object result = jp.proceed();

            logger.info("메서드 정상 종료 [AuthFilter] : " + jp.getSignature());

			return result;

		} catch (Exception e) {
			logger.info("메서드 비정상 종료 [AuthFilter] : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

}
