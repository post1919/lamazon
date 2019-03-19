package com.lamazon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.ContextUtil;

@Controller
@RequestMapping("login/adminLogin")
public class AdminLoginFormController {

	@Autowired
	LoginService loginService;

	@Autowired
	CommonService commonService;

	private static final Logger logger = LoggerFactory.getLogger(AdminLoginFormController.class);

	@GetMapping
	public String admin_login(HttpServletRequest request, Model model) throws IOException {

		if(loginService.isLogin(request)) {
			return "admin/admin_index";

		//로그인페이지
		} else {
			return "login/admin_login";
		}
	}

	@GetMapping(path="user_regist")
	public String user_regist(
			  @RequestParam(value="flag", required=false, defaultValue="regist") String flag
			, @RequestParam(value="u_pk", required=false, defaultValue="") String u_pk
			, Model model
			) throws IOException {
		String login_u_pk = loginService.getUPK(ContextUtil.getRequest())+"";

		//회원수정
		if( "detail".equals(flag) ) {

			//로그인 X
			if( !loginService.isLogin(ContextUtil.getRequest()) ) {
				return "exception/no_permission_login";

			//권한 X
			} else if( !loginService.isAdmin(ContextUtil.getRequest()) && !u_pk.equals(login_u_pk) ) {
				return "exception/no_permission";
			} else {
				Map map = new HashMap();
				//회원구분(고객사/관리자)
				map.put("code", "U_TYPE");
				model.addAttribute("U_TYPE",  commonService.commonCodeList(map));

				map = new HashMap();
				map.put("U_PK", u_pk);

				Map user = commonService.get_user_info_by_u_pk(map);
				model.addAttribute("USER_INFO", user);
			}
		}

		model.addAttribute("u_pk", u_pk);
		model.addAttribute("flag", flag);

		return "login/user_regist";
	}

}
