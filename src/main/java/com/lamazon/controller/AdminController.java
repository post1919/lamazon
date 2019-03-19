package com.lamazon.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lamazon.service.AdminService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@Controller
@RequestMapping("admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@GetMapping(value="admin_main")
	public String admin_main(Model model) {
		logger.debug(" >>>>>>>>>> ADMIN 메인");
		HashMap<String, String> map = new HashMap<String, String>();
		//당월 목표 달성 현황
		//model.addAttribute("MM_GOAL_REPORT", adminService.mm_goal_report(map));//DBManager.getInstance().makeMapArray("mm_goal_report", map, arr));

		//당월 일별 실적 현황
		//model.addAttribute("DD_GOAL_REPORT",adminService.dd_goal_report(map));// DBManager.getInstance().makeMapArray("dd_goal_report", map, arr));


		return "admin/admin_index";
	}

	/*
	 * 어드민 > 회원관리 > LMS발송
	 */
	@GetMapping(value="user/mms")
	public String user_mms(Model model) {

		//오늘 날짜 YYYY-MM-DD
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		model.addAttribute("toDate", sf.format(date));
		model.addAttribute("TOTAL_CNT", ContextUtil.getRequest().getSession().getAttribute("TOTAL_CNT"));
		ContextUtil.getRequest().getSession().removeAttribute("TOTAL_CNT");

		return "admin/user/mms";
	}

	/*
	 * 어드민 > 회원관리 > 회원현황 > 목록
	 */
	@GetMapping(path="user/list/{code}")
	public String user_list_get(@PathVariable String code, Model model) {

		//오늘 날짜 YYYY-MM-DD
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("toDate", sf.format(date));

		//1주일전 날짜 YYYY-MM-DD
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, -2);
		date = cal.getTime();
		sf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("fromDate",   sf.format(date));

		return "admin/user/user_info_list";
	}

	//탈퇴 팝업
	@GetMapping(path="withdraw/popup")
	public String withdraw_popup_get(
			@RequestParam(value="uPk") String uPk
			, Model model) {
		model.addAttribute("uPk", uPk);
		return "popup/my/withdrawPop";
	}

	//탈퇴
	@PostMapping(path="withdraw")
	public String withdraw_post(
			  @RequestParam(value="password") String password
			, @RequestParam(value="uPk") String uPk
			, Model model) {

		model.addAttribute("uPk", uPk);

		//String uPk = String.valueOf(loginService.getUPK(ContextUtil.getRequest()));
		String uId = String.valueOf(loginService.getUserId(ContextUtil.getRequest()));

		HashMap<String, String> map = new HashMap<String, String>();
		//String arr[] = new String[] {uId, Utils.makePassword(password)};
		map.put("U_PK", uPk);
		map.put("U_PASSWD", Utils.makePassword(password));

		//Map user = DBManager.getInstance().makeMap("user_info_login", map, arr);
		Map user = adminService.user_info_login(map);

		if(user!=null) {
			boolean isUser = loginService.isUser(ContextUtil.getRequest());

			// TODO 실제 탈퇴 코드 삽입
			//DBManager.getInstance().insert_table("user_info_withdraw", map, new String[] {(String)user.get("U_PK")});
			map.put("U_PK", String.valueOf(user.get("U_PK")));
			int rescnt = adminService.user_info_withdraw(map);

			//관리자 아니면 세션끊음
			if( loginService.isAdmin(ContextUtil.getRequest()) ) {
				ContextUtil.getSession(false).invalidate();
			}

			//고객일경우(탈퇴전)
			if( isUser ){
				//오프너 RELOAD
				model.addAttribute("OPENER_RELOAD", "OPENER_RELOAD");
				//form.mapping(".message_out_popup_close");
				return "exception/message_out_popup_close";
			} else {
				//form.mapping(".my_info_withdraw_done");
				return "popup/my/withdraw_done";
			}

		} else {
			//파트너일경우
			//if( loginService.isUser(ContextUtil.getRequest()) ){
				//form.mapping_message_out("비밀번호가 일치하지 않습니다.", "/my/info/withdraw/popup");
				//if(message != null) req.setAttribute("MESSAGE", message);
				//if(url != null) req.setAttribute("NEXT_URL", url);
				//mapping("/WEB-INF/jsp/exception/message_out_exception.jsp");

				model.addAttribute("MESSAGE", "비밀번호가 일치하지 않습니다.");
				model.addAttribute("NEXT_URL", "/admin/withdraw/popup?uPk="+uPk);
				return "exception/message_out_exception";
			//} else {
				//form.mapping_message_out("비밀번호가 일치하지 않습니다.", "/my/info/changepassword");
				//if(message != null) req.setAttribute("MESSAGE", message);
				//if(url != null) req.setAttribute("NEXT_URL", url);
				//mapping("/WEB-INF/jsp/exception/message_out_exception.jsp");
				//model.addAttribute("MESSAGE", "비밀번호가 일치하지 않습니다.");
				//model.addAttribute("NEXT_URL", "/my/info/changepassword");
				//return "exception/message_out_exception";
			//}
		}
	}
}
