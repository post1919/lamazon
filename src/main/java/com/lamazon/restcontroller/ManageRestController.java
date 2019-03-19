package com.lamazon.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamazon.properties.Properties;
import com.lamazon.service.AdminService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.service.ManageRestService;
import com.lamazon.util.CommonUtil;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@RestController
@RequestMapping("rest/manage")
public class ManageRestController {

	private static final Logger logger = LoggerFactory.getLogger(ManageRestController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	ManageRestService manageRestService;

	@Autowired
	AdminService adminService;

	@Autowired
	LoginService loginService;

	CommonUtil commonUtil = new CommonUtil();
	/*
	 * GET
	 */

	@GetMapping(path="user/list", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String form(
			  @RequestParam(value="name",         required=false) String name
			, @RequestParam(value="utype",        required=false) String utype
			, @RequestParam(value="email",        required=false) String email
			, @RequestParam(value="mobile",       required=false) String mobile
			, @RequestParam(value="userid",       required=false) String userid
			, @RequestParam(value="sFromdate",    required=false) String sFromdate
			, @RequestParam(value="sTodate",      required=false) String sTodate
			, @RequestParam(value="U_COMPANY",    required=false) String U_COMPANY
			, @RequestParam(value="pq_curpage",   required=false) String page
			, @RequestParam(value="pq_rpp",       required=false) String pp
			, @RequestParam(value="pq_sort",      required=false) String pq_sort
			){
		String res = "";

		try {
			HashMap<String, String> map = new HashMap<String, String>();

			if(name!=null && name.length()>0) {
				map.put("NAME", name);
			}

			if(email!=null && email.length()>0) {
				map.put("EMAIL", email);
			}

			if(mobile!=null && mobile.length()>0) {
				map.put("MOBILE", mobile);
			}

			if(userid!=null && userid.length()>0) {
				map.put("USERID", userid);
			}

			if(sFromdate!=null && sFromdate.length()>0) {
				map.put("FROMDATE", sFromdate);
			}

			if(sTodate!=null && sTodate.length()>0) {
				map.put("TODATE", sTodate);
			}

			if(userid!=null && userid.length()>0) {
				map.put("USERID", userid);
			}

			if(U_COMPANY!=null && U_COMPANY.length()>0) {
				map.put("U_COMPANY", U_COMPANY);
			}

			if(utype==null || utype.length()==0 && "0".equals(utype)) {

			} else {
				map.put("USER_TYPE", utype);
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

			commonService.setPQ_sort(pq_sort, map, "ORDER BY U_PK DESC");

			try {
				int total = adminService.manage_user_count(map);
				JSONObject obj = new JSONObject();
				obj.put("data", adminService.manage_user_list(map));

				obj.put("total_count", total);
				obj.put("page", p);

				res = obj.toString();

			} catch (IOException e) {
				e.printStackTrace();
				res = Properties.JSON_MESSAGE_HAS_ERROR;
			}

		} catch(Exception ex) {
			ex.printStackTrace();
			res = Properties.JSON_MESSAGE_FAIL;
		}

		return res;
	}

	@GetMapping(path="file/{type}/{code}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String form( @PathVariable String type, @PathVariable String code ){
		String res = "";
		JSONObject o = new JSONObject();

		try {
			File[] list = null;
			if("user".equals(type)) {
				//list = Utils.fileList(Properties.PATH_CERTI_FILE+code);
			} else if("project".equals(type)) {
				//list = Utils.fileList(Properties.PATH_PROJECT_FILE+code);
			} else {
				return Properties.JSON_MESSAGE_HAS_ERROR;
			}

			o.put("result", "success");
			if(list!=null && list.length>0) {
				JSONArray jsonlist = new JSONArray();

				for(int i=0;i<list.length;i++) {
					File f = list[i];
					JSONObject o2 = new JSONObject();
					o2.put("name", f.getName());
					jsonlist.put(o2);
				}

				o.put("data", jsonlist);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}
		return o.toString();
	}

	@PostMapping(path="user/certi", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String user_certi( @RequestParam(value="act") String act, @RequestParam(value="id") String uPk ){
		HashMap<String, String> map = new HashMap<String, String>();
		//String[] arr = {uPk};
		map.put("uPk", uPk);

		String res = "";
		if("accept".equals(act)) {
			int cnt1 = adminService.manage_user_status_change_accept(map);

			//notiManager.noti_certi_ok("",uPk); // 회사 인증 알람

			res = Properties.JSON_MESSAGE_SUCCESS;
		} else if("reject".equals(act)) {
			//DBManager.getInstance().insert_table("manage_user_status_change_reject", map, arr);
			int resReject = adminService.manage_user_status_change_reject(map);

			res = Properties.JSON_MESSAGE_SUCCESS;
		} else if("review".equals(act)) {
			//DBManager.getInstance().insert_table("manage_user_status_change_review", map, arr);
			int resReview = adminService.manage_user_status_change_review(map);
			res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	// 유저 비밀번호 변경
	@PostMapping(path="user/change_password", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String approve_user(@RequestParam(value="id") String uPk, @RequestParam(value="password") String password){
		password = Utils.makePassword(password);

		HashMap<String, String> map = new HashMap<String, String>();
//		String[] arr = {password, uPk};
		map.put("password", password);
		map.put("uPk",      uPk);

		String res = "";
		try {
			//DBManager.getInstance().insert_table("manage_user_change_password", map, arr);
			int resChange = adminService.manage_user_change_password(map);


			res = Properties.JSON_MESSAGE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			res = Properties.JSON_MESSAGE_HAS_ERROR;
		}
		return res;
	}

	//회원 삭제
	@PostMapping(path="user/delete", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String user_delete(@RequestParam(value="U_PK") String U_PK){

		//String mocode = LoginManager.getInstance().getUPK(request);
		int mocode = loginService.getUPK(ContextUtil.getRequest());

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "mocode",   mocode );
		map.put( "U_STATUS", "0"    );
		map.put( "U_PK",     U_PK   );

		//String[] arr = {"0", mocode, U_PK};

		String res = "";
		try {
			//DBManager.getInstance().insert_table("manage_user_delete", map, arr);
			int resDelete = adminService.manage_user_delete(map);

			res = Properties.JSON_MESSAGE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			res = Properties.JSON_MESSAGE_HAS_ERROR;
		}
		return res;
	}

	//회원 포인트
	@PostMapping(path="user/point_update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String point_update(
		  @RequestParam(name="U_PK",    required=true) String U_PK
		, @RequestParam(name="U_POINT", required=true) String U_POINT
	){
		HashMap<String, String> map = new HashMap<String, String>();

		int uPk = loginService.getUPK(ContextUtil.getRequest());
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		System.out.println("U_PK => " + U_PK);
		System.out.println("U_POINT => " + U_POINT);
		System.out.println("U_MOCODE => " + uPk);

		if( uPk != -1 ) {
			map.put("U_PK",     U_PK);
			map.put("U_POINT",  U_POINT);
			map.put("U_MOCODE", uPk+"");

			int rescnt = adminService.point_update(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

}
