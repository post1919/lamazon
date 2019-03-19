package com.lamazon.restcontroller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lamazon.properties.Properties;
import com.lamazon.service.AdminService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.service.OrderService;
import com.lamazon.util.CommonUtil;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@RestController
@RequestMapping("rest/order")
public class OrderRestController {

	private static final Logger logger = LoggerFactory.getLogger(JoinRestController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	CommonService commonService;

	@Autowired
	OrderService orderService;

	@Autowired
	AdminService adminService;

	@GetMapping(path="list", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String list(
			  @RequestParam(value="name",         required=false) String name
			, @RequestParam(value="o_number",        required=false) String o_number
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

			if( !loginService.isAdmin(ContextUtil.getRequest()) ) {
				map.put("U_PK", loginService.getUPK(ContextUtil.getRequest())+"");
			}

			if(name!=null && name.length()>0) {
				map.put("NAME", name);
			}

			if(o_number!=null && o_number.length()>0) {
				map.put("O_NUMBER", o_number);
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

			int p = 1;
			if(page!=null && page.length()>0) {

				p = Integer.parseInt(page);
				int lineNum = 100;
				if(pp!=null && pp.length()>0) {
					lineNum = Integer.parseInt(pp);
				}
				map.put("PAGING", (p-1)*lineNum+","+lineNum);
			}

			commonService.setPQ_sort(pq_sort, map, "ORDER BY O_PK DESC");

			int total = orderService.order_master_cnt(map);

			JSONObject obj = new JSONObject();
			obj.put("data", orderService.order_master(map));

			obj.put("total_count", total);
			obj.put("page", p);

			res = obj.toString();

		} catch(Exception ex) {
			ex.printStackTrace();
			res = Properties.JSON_MESSAGE_FAIL;
		}

		return res;
	}

	//미입고/출고 처리
	@PostMapping(path="update_status", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String update_status(
			  @RequestParam(value="o_pk", required=false) String o_pk
			, @RequestParam(value="o_pks[]", required=false) List<String> o_pks
			, @RequestParam(value="o_status") String o_status
			, Model model) {
		try {
			HashMap<String, String> map = new HashMap<String, String>();

			//권한 체크
			if( loginService.isAdmin(ContextUtil.getRequest()) ) {
				int update_cnt = 0;

				int login_u_pk = loginService.getUPK(ContextUtil.getRequest());
				if( login_u_pk != -1) {
					map.put("O_MOCODE", login_u_pk+"");
				}

				map.put("O_STATUS", o_status);

				//1건
				if( o_pk != null ) {
					map.put("O_PK", o_pk);
					update_cnt = orderService.order_master_o_status_update(map);

				//여러건
				} else if( o_pks != null ) {
					String items = "(";
					for(int i=0; i<o_pks.size(); i++) {
						if(i>0) items += ",";
						items += o_pks.get(i);
					}
					items+=")";

					map.put("O_PK", items);

					update_cnt = orderService.order_master_o_status_update2(map);
				}

				if( update_cnt == 0 ) {
					return Properties.JSON_MESSAGE_HAS_ERROR;
				}

			} else {
				return Properties.JSON_MESSAGE_NO_PERMISSION;
			}

			return Properties.JSON_MESSAGE_SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}
	}

	@GetMapping(path="jungsan_list", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String jungsan_list(
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

	//신청서 등록/수정
	@Transactional
	@PostMapping(path="regist_master/auth", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String regist_master(
			  @RequestParam(value="o_pk", required=false) String o_pk
			, @RequestParam(value="r_u_pk", required=false) String r_u_pk
			, @RequestParam(value="o_market_gubun") String o_market_gubun
			, @RequestParam(value="o_title") String o_title
			, @RequestParam(value="flag", required=false, defaultValue="regist") String flag
			, Model model) {

		JSONObject obj = new JSONObject();

		try {
			HashMap map = new HashMap();
			int O_PK = -1;

			int login_u_pk = loginService.getUPK(ContextUtil.getRequest());
			map.put("O_MARKET_GUBUN", o_market_gubun);
			map.put("O_TITLE", o_title);
			map.put("R_U_PK",   login_u_pk+"");

			//등록
			if( "regist".equals(flag) && Utils.isEmpty(o_pk) ) {
				//포인트 부족
				if( loginService.getPoint(ContextUtil.getRequest()) < Properties.POINT ) {
					return Properties.JSON_MESSAGE_POINT;
				}

				map.put("O_INCODE", login_u_pk+"");
				map.put("O_NUMBER", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())); //주문번호
				map.put("O_STATUS", 1000); //미입금 1000
				map.put("O_POINT_BEFORE", loginService.getPoint(ContextUtil.getRequest())); //차감전 포인트
				map.put("O_POINT", Properties.POINT); //차감될 포인트
				map.put("O_POINT_AFTER", loginService.getPoint(ContextUtil.getRequest())-Properties.POINT); //차감전 포인트-차감될포인트
				int orderMasterInsertCnt = orderService.order_insert(map);

				if( map.get("O_PK") != null ) {
					O_PK = (int)(long)map.get("O_PK");
				}

				if( O_PK > -1 ) {
					HashMap uPointMap = new HashMap();
					uPointMap.put("U_POINT", map.get("O_POINT_AFTER"));
					uPointMap.put("U_PK", loginService.getUPK(ContextUtil.getRequest()));
					//회원정보의 포인트 수정
					int update_cnt = orderService.user_info_u_point_update(uPointMap);

					if( update_cnt > 0 ) {
						//로그인 세션갱신
						loginService.setUser(ContextUtil.getRequest());
					}
				}

			//수정
			} else {
				if( Utils.isEmpty(o_pk) ) {
					return Properties.JSON_MESSAGE_HAS_ERROR;

				//권한 체크
				} else if( loginService.isAdmin(ContextUtil.getRequest()) || r_u_pk.equals(login_u_pk+"") ) {
					map.put("O_PK", o_pk);
					map.put("O_MOCODE", login_u_pk+"");
					orderService.order_update(map);
					O_PK = Integer.valueOf(o_pk);
				} else {
					return Properties.JSON_MESSAGE_NO_PERMISSION;
				}
			}

			obj.put("O_PK", O_PK);
			obj.put("result", Properties.JSON_SUCCESS);
			return obj.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}
	}

	//신청서 상세 등록/수정
	@PostMapping(path="regist_detail/auth", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String regist_detail(
			  @RequestParam(value="u_pk") String u_pk
			, @RequestParam(value="o_pk") String o_pk
			, @RequestParam(value="od_pk") String od_pk
			, @RequestParam(value="od_name") String od_name
			, @RequestParam(value="od_count") String od_count
			, @RequestParam(value="od_color") String od_color
			, @RequestParam(value="od_size") String od_size
			, @RequestParam(value="od_brand") String od_brand
			, @RequestParam(value="od_invoice") String od_invoice
			, @RequestParam(value="od_memo") String od_memo
			, @RequestParam(value="flag",           required=false, defaultValue="regist") String flag
			, @RequestParam(value="od_file",        required=false, defaultValue="") String od_file
			, @RequestParam(value="od_file_rename", required=false, defaultValue="") String od_file_rename
			, @RequestParam(value="files",          required=false) MultipartFile files
			, Model model) {

		try {

			HashMap<String, String> map = new HashMap<String, String>();

			int od_update_cnt = 0;
			int od_insert_cnt = 0;

			int login_u_pk = loginService.getUPK(ContextUtil.getRequest());
			if( login_u_pk != -1) {
				map.put("OD_INCODE", login_u_pk+"");
				map.put("OD_MOCODE", login_u_pk+"");
			}

			map.put("OD_NAME", od_name);
			map.put("U_PK", u_pk);
			map.put("R_O_PK", o_pk);
			map.put("OD_NAME", od_name);
			map.put("OD_COUNT", od_count);
			map.put("OD_COLOR", od_color);
			map.put("OD_SIZE", od_size);
			map.put("OD_BRAND", od_brand);
			map.put("OD_INVOICE", od_invoice);
			map.put("OD_MEMO", od_memo);

			String targetPath = Properties.PATH_OD_FILE;

			if(files != null && !files.isEmpty()) {
				od_file = files.getOriginalFilename();
				od_file_rename = CommonUtil.makeRename(files);

				File file = new File(targetPath, od_file_rename);
				files.transferTo(file);

				map.put("OD_FILE", od_file);
				map.put("OD_FILE_RENAME", od_file_rename);
			}

			//수정
			if( "detail".equals(flag) ) {
				//권한 체크
				if( loginService.isAdmin(ContextUtil.getRequest()) || u_pk.equals(login_u_pk+"") ) {
					map.put("OD_PK", od_pk);
					od_update_cnt = orderService.order_detail_update(map);
				} else {
					return Properties.JSON_MESSAGE_NO_PERMISSION;
				}

			//등록
			} else {
				od_insert_cnt = orderService.order_detail_insert(map);
			}

			if( od_insert_cnt == 0 && od_update_cnt == 0 ) {
				return Properties.JSON_MESSAGE_HAS_ERROR;
			} else {
				return Properties.JSON_MESSAGE_SUCCESS;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}
	}
}
