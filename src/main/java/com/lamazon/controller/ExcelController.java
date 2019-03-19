package com.lamazon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lamazon.service.AdminService;
import com.lamazon.service.BlogRestService;
import com.lamazon.service.LoginService;
import com.lamazon.service.ManageRestService;

@Controller
@RequestMapping("manage/excel")
public class ExcelController {

	private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@Autowired(required=false)
	AdminService adminService;

	@Autowired(required=false)
	LoginService loginService;

	@Autowired(required=false)
	ManageRestService manageRestService;

	@Autowired(required=false)
	BlogRestService blogRestService;

	@GetMapping(value="user_list")
	public void user_list(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="type",         required=false) String type
			, @RequestParam(value="opt_user",     required=false) String code
			, @RequestParam(value="user_name",    required=false) String user_name
			, @RequestParam(value="user_email",   required=false) String user_email
			, @RequestParam(value="user_mobile",  required=false) String user_mobile
			, @RequestParam(value="user_id",      required=false) String user_id
			, @RequestParam(value="sFromdate",    required=false) String sFromdate
			, @RequestParam(value="sTodate",      required=false) String sTodate
			, @RequestParam(value="U_COMPANY",    required=false) String U_COMPANY
			, @RequestParam(value="U_NEWSLETTER", required=false) String U_NEWSLETTER
			, @RequestParam(value="pq_curpage",   required=false) String page
			, @RequestParam(value="pq_rpp",       required=false) String pp
			, @RequestParam(value="pq_sort",      required=false) String pq_sort
			) throws ServletException, IOException {
		logger.debug(" >>>>>>>>>> ExcelController.user_list()");

		HashMap<String, String> _map = new HashMap<String, String>();

		if("standby".equals(code)) {
			_map.put("REVIEW", "REVIEW");
		} else if("reject".equals(code)) {
			_map.put("REJECT", "REJECT");
		} else if("all".equals(code)) {
			_map.put("ALL", "ALL");
		} else if("allN".equals(code)) {
			_map.put("ALL", "ALL");
		} else if("withdraw".equals(code)) {
			_map.put("WITHDRAW", "WITHDRAW");
		} else if("accept".equals(code)) {
			_map.put("ACCEPT", "ACCEPT");
		} else {
			//return "";
		}

		if(type!=null && type.length()>0) 	_map.put("ALL", type);
		if(user_name!=null && user_name.length()>0) 	_map.put("NAME", user_name);
		if(user_email!=null && user_email.length()>0) 		_map.put("EMAIL", user_email);
		if(user_mobile!=null && user_mobile.length()>0) 		_map.put("MOBILE", user_mobile);
		if(user_id!=null && user_id.length()>0) 		_map.put("USERID", user_id);
		if(sFromdate!=null && sFromdate.length()>0) 		_map.put("FROMDATE", sFromdate);
		if(sTodate!=null && sTodate.length()>0) 		_map.put("TODATE", sTodate);
		if(U_COMPANY!=null && U_COMPANY.length()>0) 		_map.put("U_COMPANY", U_COMPANY);
		if(U_NEWSLETTER!=null && U_NEWSLETTER.length()>0) 		_map.put("U_NEWSLETTER", U_NEWSLETTER);

		_map.put("PAGING", "1000000");
		_map.put("ORDERING", "order by u.U_PK asc");

		int uPk = loginService.getUPK(request);
		String arr[] = new String[] {};

		ArrayList list = adminService.manage_user_list(_map);

		request.setAttribute("type",         type         );
		request.setAttribute("code",         code         );
		request.setAttribute("user_name",    user_name    );
		request.setAttribute("user_email",   user_email   );
		request.setAttribute("user_mobile",  user_mobile  );
		request.setAttribute("user_id",      user_id      );
		request.setAttribute("sFromdate",    sFromdate    );
		request.setAttribute("sTodate",      sTodate      );
		request.setAttribute("U_COMPANY",    U_COMPANY    );
		request.setAttribute("U_NEWSLETTER", U_NEWSLETTER );
		request.setAttribute("page",         page         );
		request.setAttribute("pp",           pp           );
		request.setAttribute("pq_sort",      pq_sort      );
		request.setAttribute("list",         list         );

		request.getRequestDispatcher("/WEB-INF/views/manage/excel/excel_user.jsp").forward(request, response);
	}

}
