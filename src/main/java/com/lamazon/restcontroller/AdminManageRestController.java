package com.lamazon.restcontroller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamazon.service.AdminManageRestService;
import com.lamazon.service.AdminService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;

@RestController
@RequestMapping("rest/adminManage")
public class AdminManageRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminManageRestController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	CommonService commonService;

	@Autowired
	AdminManageRestService adminManageRestService;

	//목표설정 년도별 데이터 불러오기
	@GetMapping(path="manageGoal/{year}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String manageGoalData(@PathVariable("year") String year) {
		String res = "";
		Pattern datePattern = Pattern.compile("\\d{4}");
		Matcher yearMatcher = datePattern.matcher(year);
		if(yearMatcher.find()) {
			HashMap<String, String> map = new HashMap<>();
			//String[] arr = new String[] {};
			map.put("YEAR", year + "");
			JSONObject obj = new JSONObject();
			obj.put("data", adminService.manage_goal_year(map));

			res = obj.toString();
		}

		return res;
	}

	@GetMapping(path="manageGoal/change", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String changeGoalData(
			HttpServletRequest request,
			@RequestParam("goalFlag") String goalFlag, @RequestParam("goal") String goal, @RequestParam("yyyymm") String yyyymm
			) {
		String res = "";

		String login_id = request.getParameter("user_id") == null ? "" : request.getParameter("user_id");
		if(loginService.isLogin(request)) {
			login_id = loginService.getUNAME(request);
		}

		HashMap<String, String> map = new HashMap<>();

		if(goal == null || goal.equals("")) {
			goal = "";
		}
		map.put("YEAR", yyyymm);
		map.put("GOAL_FLAG", goalFlag);
		map.put("INCODE", login_id);
		map.put("GOAL", goal);
		int cnt = 0;
		int result = 0;

		cnt = adminService.manage_goal_exist(map);
		if(cnt > 0) {
			result = adminService.manage_goal_update(map);
		} else {
			result = adminService.manage_goal_insert(map);
		}

		JSONObject obj = new JSONObject();
		if(result < 1) {
			obj.append("status", "error");
			res = obj.toString();
		} else {
			obj.append("status", "success");
			res = obj.toString();
		}

		return res;
	}

	@GetMapping(path="manageGoal/calcAver", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String calculateAverage(@RequestParam("yyyymm") String yyyymm) {
		String res = "";

		JSONObject obj = new JSONObject();

		HashMap<String, String> map = new HashMap<>();
		map.put("YYYYMMDD", yyyymm);

		Date tempDate = null;
		try {
			tempDate = new SimpleDateFormat("yyyyMM").parse(yyyymm);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(tempDate);
		int targetYear = cal.get(Calendar.YEAR);
		int targetMonth = cal.get(Calendar.MONTH) + 1;
		int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		DateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat noDay = new SimpleDateFormat("yyyy-MM");

		cal.set(targetYear, targetMonth - 1, firstDay);
		String firstDate = fullFormat.format(cal.getTime());
		String noDate = noDay.format(cal.getTime());
		cal.set(targetYear, targetMonth - 1, lastDay);
		String lastDate = fullFormat.format(cal.getTime());

		String sum = adminService.total_saleday_of_month(map);
		if(sum != null && !sum.equals("")) {

			map.put("SALE_DAY", sum);
			map.put("START_DATE", firstDate);
			map.put("END_DATE", lastDate);
			map.put("CONTRACT_MONTH", noDate);
			map.put("DATE_FORMAT", "%Y-%m-%d");

			String monthlyOrder = adminService.monthly_average_order(map);
			String monthlyContract = adminService.monthly_average_contract(map);

			map.put("CATEGORY", "0000"); //현재로서는 0000 밖에 없음
			map.put("YEAR", yyyymm);

			Map<String, String> jsonMap = new HashMap<>();

			// 의뢰
			if(monthlyOrder != null && !monthlyOrder.equals("")) {
				if(map.containsKey("GOAL_FLAG")) {
					map.remove("PR_CNT");
				}
				if(map.containsKey("GOAL_FLAG")) {
					map.remove("PR_CNT");
				}
				map.put("GOAL_FLAG", "1000");
				map.put("PR_CNT", monthlyOrder);

				int goalCnt = adminService.manage_goal_exist(map);
				if(goalCnt < 1) {
					jsonMap.put("order", "no_order");
				} else if(goalCnt > 1) {
					jsonMap.put("order", "too_many");
				} else {
					int resultCnt = adminService.monthly_average_insert(map);
					if(resultCnt != 1) {
						jsonMap.put("order_cnt", resultCnt+"");
						jsonMap.put("order", "error");
					} else {
						jsonMap.put("order", "success");
					}
				}
			}
			// 수주
			if(monthlyContract != null && !monthlyContract.equals("")) {
				if(map.containsKey("GOAL_FLAG")) {
					map.remove("PR_CNT");
				}
				if(map.containsKey("GOAL_FLAG")) {
					map.remove("PR_CNT");
				}
				map.put("GOAL_FLAG", "2000");
				map.put("PR_CNT", monthlyContract);

				int goalCnt = adminService.manage_goal_exist(map);
				if(goalCnt < 1) {
					jsonMap.put("contract", "no_contract");
				} else if(goalCnt > 1) {
					jsonMap.put("contract", "too_many");
				} else {
					int resultCnt = adminService.monthly_average_insert(map);
					if(resultCnt != 1) {
						jsonMap.put("contract_cnt", resultCnt+"");
						jsonMap.put("contract", "error");
					} else {
						jsonMap.put("contract", "success");
					}
				}
			}

			obj.put("work", jsonMap);
			obj.put("status", "success");
			res = obj.toString();

		} else {
			obj.put("status", "no_days");
			res = obj.toString();
		}



		return res;
	}

	// 프로젝트 리스트
	@GetMapping(path="commonCode/list/{code}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String project_list(@PathVariable("code")        String code
			, @RequestParam(value="CMM_CODE_GROUP", required=false)  String CMM_CODE_GROUP
			, @RequestParam(value="CMM_CODE_GROUP_NM", required=false)    String CMM_CODE_GROUP_NM
			, @RequestParam(value="pq_curpage", required=false) String page
			, @RequestParam(value="pq_rpp", required=false) String pp
			, @RequestParam(value="pq_sort", required=false) String pq_sort ){

		HashMap<String, String> map = new HashMap<String, String>();
		//String[] arr = {};

		if("all".equals(code)) {
			//map.put("RECRUIT", "RECRUIT");
		} else {
			return "exception/error";
		}

		if(CMM_CODE_GROUP!=null && CMM_CODE_GROUP.length()>0) {
			map.put("CMM_CODE_GROUP", CMM_CODE_GROUP);
		}

		if(CMM_CODE_GROUP_NM!=null && CMM_CODE_GROUP_NM.length()>0) {
			map.put("CMM_CODE_GROUP_NM", CMM_CODE_GROUP_NM);
		}

		int p = 1;
		if(page!=null && page.length()>0) {

			p = Integer.parseInt(page);
			int lineNum = 100;
			if(pp!=null && pp.length()>0) {
				lineNum = Integer.parseInt(pp);
			}
			map.put("PAGING", (p-1)*lineNum+","+lineNum);
		}

		commonService.setPQ_sort(pq_sort, map, "ORDER BY PR_PK desc");

		String res = "";

		int total = adminManageRestService.common_code_list_m_count(map);

		JSONObject obj = new JSONObject();
		ArrayList<Map> data = adminManageRestService.common_code_list_m(map);

		obj.put("data", data);
		obj.put("total_count", total);
		obj.put("page", p);

		res = obj.toString();
		return res;
	}

}
