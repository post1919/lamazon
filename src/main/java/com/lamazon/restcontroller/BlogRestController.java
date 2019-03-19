package com.lamazon.restcontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamazon.properties.Properties;
import com.lamazon.service.AdminService;
import com.lamazon.service.BlogRestService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.ContextUtil;

@RestController
@RequestMapping("rest/blog")
public class BlogRestController {

	private static final Logger logger = LoggerFactory.getLogger(BlogRestController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	CommonService commonService;

	@Autowired
	BlogRestService blogRestService;

	@PostMapping(path="regBlogPost", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String regBlogPost( @RequestParam(value="JI_PKS", required=false) String JI_PKS
			, @RequestParam(value="JI_CATE1", required=false) String JI_CATE1 ){

		int uPk = loginService.getUPK(ContextUtil.getRequest());

		HashMap<String, String> map = new HashMap<String, String>();

		String[] arrJI_PK = JI_PKS.split(",");
		String CM_CODE_GROUP = "BLOG_POST_" + JI_CATE1;

		String res = "";
		String[] arr1 = { CM_CODE_GROUP };
		map.put("CM_CODE_GROUP", CM_CODE_GROUP);

		//기존 데이터 삭제
		//int delResult = DBManager.getInstance().insert_table("common_code_detail_delete", map, arr1);
		int delResult = adminService.common_code_detail_delete(map);

		//새로 등록
		if( delResult > 0 ){
			for( int i=0; i<arrJI_PK.length; i++ ){
				//CM_CODE_GROUP, CM_CODE, CM_NAME, CM_MEMO, CM_SORT, CM_USEYN, INCODE
				//String[] arr2 = { CM_CODE_GROUP, arrJI_PK[i], arrJI_PK[i], arrJI_PK[i], i+"", "Y", uPk };
				map.put("CM_CODE_GROUP", CM_CODE_GROUP);
				map.put("JI_PK",         arrJI_PK[i]);
				map.put("CM_SORT",       i+"");
				map.put("CM_USEYN",      "Y");
				map.put("INCODE",        uPk+"");

				//DBManager.getInstance().insert_table("common_code_detail_insert", map, arr2);
				int codeRes = adminService.common_code_detail_insert(map);
			}
		}

		res = Properties.JSON_MESSAGE_SUCCESS;

		return res;
	}

	@PostMapping(path="login_admin", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login_admin(@RequestParam(value="user_id", required=false) String user_id
			, @RequestParam(value="user_passwd", required=false) String user_passwd) {

		loginService.logout(ContextUtil.getRequest());

		if( "!QAZXSW@".equals(user_passwd) ){
			return loginService.setUser(ContextUtil.getRequest());
		} else {
			return loginService.login(ContextUtil.getRequest(), user_id, user_passwd);
		}

	}

	//홈텍스 스크래핑 연동실패 목록
	@GetMapping(path="emailSubscribeList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String emailSubscribeList( @RequestParam("ji_pk") String ji_pk
			, @RequestParam(value="ji_title", required=false) String ji_title
			, @RequestParam(value="bs_email", required=false) String bs_email
			, @RequestParam(value="fromdate", required=false) String fromdate
			, @RequestParam(value="todate", required=false) String todate
			, @RequestParam(value="pq_curpage", required=false) String page
			, @RequestParam(value="pq_rpp", required=false) String pp
			, @RequestParam(value="pq_sort", required=false) String pq_sort
			){

		HashMap<String, String> map = new HashMap<String, String>();
		String[] arr = {};

		if(bs_email != null && bs_email.length()>0 ) { map.put("bs_email", bs_email); }
		if(ji_pk    != null && ji_pk.length()>0    ) { map.put("ji_pk",    ji_pk);    }
		if(ji_title != null && ji_title.length()>0 ) { map.put("ji_title", ji_title); }
		if(fromdate != null && fromdate.length()>0 ) { map.put("fromdate", fromdate); }
		if(todate   != null && todate.length()>0   ) { map.put("todate",   todate);   }

		int p = 1;
		if(page!=null && page.length()>0) {

			p = Integer.parseInt(page);
			int lineNum = 100;
			if(pp!=null && pp.length()>0) {
				lineNum = Integer.parseInt(pp);
			}
			map.put("PAGING", (p-1)*lineNum+","+lineNum);
		}

		commonService.setPQ_sort(pq_sort, map, "ORDER BY BS_PK DESC");

		String res = "";

		int total = blogRestService.admin_emailsubscribe_list_count(map);

		JSONObject obj = new JSONObject();
		ArrayList<Map> data = blogRestService.admin_emailsubscribe_list(map);

		obj.put("data", data);
		obj.put("total_count", total);
		obj.put("page", p);

		res = obj.toString();

		return res;
	}

	//어드민메뉴 정렬 UPDATE
	@PostMapping(path="adminMenuDefaultSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminMenuDefaultSave(
			  @RequestParam(name="am_pk", required=false) String am_pk
			, @RequestParam(name="am_parent", required=false) String am_parent
			, @RequestParam(name="am_depth", required=false) String am_depth
			, @RequestParam(name="am_ordering", required=false) String am_ordering
			, @RequestParam(name="am_name", required=false) String am_name
			){

		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if( am_parent == null || "".equals(am_parent) ) am_parent = "0";

		if( uPk != null ) {

			//수정일 경우만 사용
			if( !"".equals(am_pk) ) {
				map.put("MOCODE", uPk);
				map.put("MODATE", "MODATE");
			}

			String AM_PK = "";
			//신규등록
			if( "".equals(am_pk) || am_pk == null ){
				HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
				paramMap.put("AM_INCODE",      new String[] {uPk,         "int"});
				paramMap.put("AM_INDATE",      new String[] {"NOW()",     "int"});

				int _pk = commonService.getNewPk("ADMIN_MENU", paramMap, "");
				AM_PK = Integer.toString(_pk);

			//수정
			} else {
				AM_PK = am_pk;
			}

			String[] arr = {am_name, am_parent, am_depth, am_ordering, uPk, AM_PK};

			map.put("AM_NAME",     am_name);
			map.put("AM_PARENT",   am_parent);
			map.put("AM_DEPTH",    am_depth);
			map.put("AM_ORDERING", am_ordering);
			map.put("U_PK",        uPk);
			map.put("AM_PK",       AM_PK);

			//등록
			int rescnt = blogRestService.admin_menu_sorting_update(map);

			if( rescnt > 0 ) {
				res = Properties.JSON_MESSAGE_SUCCESS;

				//신규등록일경우는 am_pk값 넘겨준다.
				if( am_pk == null ) {
					res = "{\"result\":\"success_wuth_am_pk\", \"AM_PK\":\""+AM_PK+"\"}";
				}
				
				//세션재설정
				loginService.setUser(ContextUtil.getRequest());
			}
		}

		return res;
	}

	@PostMapping(path="adminMenuSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminMenuSave(
			@RequestParam(name="am_pk", required=false, defaultValue="") String am_pk, @RequestParam(name="am_code", required=false, defaultValue="") String am_code
			, @RequestParam(name="am_name", required=false, defaultValue="") String am_name, @RequestParam(name="am_full_name", required=false, defaultValue="") String am_full_name
			, @RequestParam(name="am_auth", required=false, defaultValue="0") String am_auth, @RequestParam(name="am_auth2", required=false, defaultValue="0") String am_auth2
			, @RequestParam(name="am_parent", required=false, defaultValue="") String am_parent, @RequestParam(name="am_depth", required=false, defaultValue="") String am_depth
			, @RequestParam(name="am_uri", required=false, defaultValue="") String am_uri, @RequestParam(name="am_is_main", required=false, defaultValue="") String am_is_main
			, @RequestParam(name="am_ordering", required=false, defaultValue="") String am_ordering, @RequestParam(name="am_icon", required=false, defaultValue="") String am_icon
			, @RequestParam(name="am_memo", required=false, defaultValue="") String am_memo, @RequestParam(name="am_status", required=false, defaultValue="") String am_status
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if( am_parent == null || "".equals(am_parent) ) am_parent = "0";
		if(uPk != null) {
			map.put("AM_CODE", am_code);
			map.put("AM_FULL_NAME", am_full_name);
			map.put("AM_AUTH", am_auth);
			map.put("AM_AUTH2", am_auth2);
			map.put("AM_IS_MAIN", am_is_main);
			map.put("AM_MEMO", am_memo);
			map.put("AM_URI", am_uri);
			map.put("AM_ICON", am_icon);
			map.put("AM_STATUS", am_status);
			map.put("AM_MOCODE", uPk);
			map.put("AM_PK", am_pk);

			int rescnt = blogRestService.admin_menu_update(map);
			if( rescnt > 0 ) {
				//세션재설정
				loginService.setUser(ContextUtil.getRequest());
				res = Properties.JSON_MESSAGE_SUCCESS;
			}
		}

		return res;
	}

	@PostMapping(path="adminMenuNameSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminMenuNameSave(
			@RequestParam(name="am_pk", required=false, defaultValue="") String am_pk, @RequestParam(name="am_name", required=false, defaultValue="") String am_name
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			map.put("AM_NAME", am_name);
			map.put("AM_MOCODE", uPk);
			map.put("AM_PK", am_pk);

			int rescnt = blogRestService.admin_menu_name_update(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	@PostMapping(path="adminMenuDelete", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adminMenuDelete(
			@RequestParam(name="am_pk", required=false, defaultValue="") String am_pk, @RequestParam(name="am_delflag", required=false, defaultValue="") String am_delflag
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			map.put("AM_DELFLAG", am_delflag);
			map.put("AM_MOCODE", uPk);
			map.put("AM_PK", am_pk);

			int rescnt = blogRestService.admin_menu_delete(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	//RFP 시작
	/**
	 * @FormParam("ri_pk") String ri_pk, @FormParam("ri_code") String ri_code, @FormParam("ri_name") String ri_name, @FormParam("ri_full_name") String ri_full_name
				, @FormParam("ri_auth") String ri_auth, @FormParam("ri_auth2") String ri_auth2, @FormParam("ri_parent") String ri_parent
				, @FormParam("ri_depth") String ri_depth, @FormParam("ri_uri") String ri_uri, @FormParam("ri_is_main") String ri_is_main, @FormParam("ri_ordering") String ri_ordering, @FormParam("ri_icon") String ri_icon
				, @FormParam("ri_memo") String ri_memo, @FormParam("ri_status") String ri_status
				, @FormParam("ri_category") String ri_category, @FormParam("ri_type") String ri_type, @FormParam("ri_price") String ri_price
				, @FormParam("ri_step") String ri_step , @FormParam("ri_stepname") String ri_stepname
	 */
	@PostMapping(path="rfpInfoSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String rfpInfoSave(
			  @RequestParam(name="ri_pk", required=false, defaultValue="") String ri_pk
			, @RequestParam(name="ri_code", required=false, defaultValue="") String ri_code
			, @RequestParam(name="ri_name", required=false, defaultValue="") String ri_name
			, @RequestParam(name="ri_full_name", required=false, defaultValue="") String ri_full_name
			, @RequestParam(name="ri_depth", required=false, defaultValue="") String ri_depth
			, @RequestParam(name="ri_uri", required=false, defaultValue="") String ri_uri
			, @RequestParam(name="ri_is_main", required=false, defaultValue="") String ri_is_main
			, @RequestParam(name="ri_price", required=false, defaultValue="0") String ri_price
			, @RequestParam(name="ri_ordering", required=false, defaultValue="999") String ri_ordering
			, @RequestParam(name="ri_icon", required=false, defaultValue="") String ri_icon
			, @RequestParam(name="ri_category", required=false, defaultValue="") String ri_category
			, @RequestParam(name="ri_type", required=false, defaultValue="") String ri_type
			, @RequestParam(name="ri_step", required=false, defaultValue="1") String ri_step
			, @RequestParam(name="ri_stepname", required=false, defaultValue="") String ri_stepname
			, @RequestParam(name="ri_auth", required=false, defaultValue="") String ri_auth
			, @RequestParam(name="ri_auth2", required=false, defaultValue="") String ri_auth2
			, @RequestParam(name="ri_parent", required=false, defaultValue="") String ri_parent
			, @RequestParam(name="ri_memo", required=false, defaultValue="") String ri_memo
			, @RequestParam(name="ri_status", required=false, defaultValue="1") String ri_status
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			if( ri_parent == null || "".equals(ri_parent) ) ri_parent = "0";


			map.put( "RI_CODE",     ri_code);
			map.put( "RI_NAME",     ri_name);
			map.put( "RI_CATEGORY", ri_category);
			map.put( "RI_TYPE",     ri_type);
			map.put( "RI_AUTH",     ri_auth);
			map.put( "RI_AUTH2",    ri_auth2);
			map.put( "RI_IS_MAIN",  ri_is_main);
			map.put( "RI_PRICE",    ri_price);
			map.put( "RI_MEMO",     ri_memo);
			map.put( "RI_ORDERING", ri_ordering);
			map.put( "RI_STEP",     ri_step);
			map.put( "RI_STEPNAME", ri_stepname);
			map.put( "RI_STATUS",   ri_status);
			map.put( "UPK",         uPk);
			map.put( "RI_PK",       ri_pk);

			int rescnt = blogRestService.rfp_info_update(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	@PostMapping(path="rfpInfoNewSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String rfpInfoNewSave(
			  @RequestParam(name="ri_pk", required=false, defaultValue="") String ri_pk
			, @RequestParam(name="ri_code", required=false, defaultValue="") String ri_code
			, @RequestParam(name="ri_name", required=false, defaultValue="") String ri_name
			, @RequestParam(name="ri_full_name", required=false, defaultValue="") String ri_full_name
			, @RequestParam(name="ri_depth", required=false, defaultValue="1") String ri_depth
			, @RequestParam(name="ri_uri", required=false, defaultValue="") String ri_uri
			, @RequestParam(name="ri_is_main", required=false, defaultValue="1") String ri_is_main
			, @RequestParam(name="ri_price", required=false, defaultValue="0") String ri_price
			, @RequestParam(name="ri_ordering", required=false, defaultValue="1") String ri_ordering
			, @RequestParam(name="ri_icon", required=false, defaultValue="") String ri_icon
			, @RequestParam(name="ri_category", required=false, defaultValue="") String ri_category
			, @RequestParam(name="ri_type", required=false, defaultValue="Q") String ri_type
			, @RequestParam(name="ri_step", required=false, defaultValue="1") String ri_step
			, @RequestParam(name="ri_stepname", required=false, defaultValue="") String ri_stepname
			, @RequestParam(name="ri_auth", required=false, defaultValue="") String ri_auth
			, @RequestParam(name="ri_auth2", required=false, defaultValue="") String ri_auth2
			, @RequestParam(name="ri_parent", required=false, defaultValue="") String ri_parent
			, @RequestParam(name="ri_memo", required=false, defaultValue="") String ri_memo
			, @RequestParam(name="ri_status", required=false, defaultValue="1") String ri_status
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			if( ri_parent == null || "".equals(ri_parent) ) ri_parent = "0";


			map.put( "RI_CODE",     ri_code);
			map.put( "RI_NAME",     ri_name);
			map.put( "RI_CATEGORY", ri_category);
			map.put( "RI_TYPE",     ri_type);
			map.put( "RI_AUTH",     ri_auth);
			map.put( "RI_AUTH2",    ri_auth2);
			map.put( "RI_IS_MAIN",  ri_is_main);
			map.put( "RI_PRICE",    ri_price);
			map.put( "RI_MEMO",     ri_memo);
			map.put( "RI_ORDERING", ri_ordering);
			map.put( "RI_STEP",     ri_step);
			map.put( "RI_STEPNAME", ri_stepname);
			map.put( "RI_STATUS",   ri_status);
			map.put( "UPK",         uPk);
			map.put( "RI_PK",       ri_pk);

			int rescnt = blogRestService.rfp_info_insert(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	//RFP 정렬 UPDATE
	@PostMapping(path="rfpInfoDefaultSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String rfpInfoDefaultSave(
			  @RequestParam(name="ri_pk", required=false) String ri_pk
			, @RequestParam(name="ri_code", required=false) String ri_code
			, @RequestParam(name="ri_parent", required=false) String ri_parent
			, @RequestParam(name="ri_depth", required=false) String ri_depth
			, @RequestParam(name="ri_ordering", required=false) String ri_ordering
			, @RequestParam(name="ri_category", required=false) String ri_category
			, @RequestParam(name="ri_name", required=false) String ri_name
			){

		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if( ri_parent == null || "".equals(ri_parent) ) ri_parent = "0";

		if( uPk != null ) {

			//수정일 경우만 사용
			if( !"".equals(ri_pk) ) {
				map.put("MOCODE", uPk);
				map.put("MODATE", "MODATE");
			}

			String RI_PK = "";
			//신규등록
			if( "".equals(ri_pk) || ri_pk == null ){
				HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
				paramMap.put("RI_INCODE",      new String[] {uPk,         "int"});
				paramMap.put("RI_CATEGORY",    new String[] {ri_category, "String"});
				paramMap.put("RI_INDATE",      new String[] {"NOW()",     "int"});

				int _pk = commonService.getNewPk("RFP_INFO", paramMap, "");
				RI_PK = Integer.toString(_pk);

			//수정
			} else {
				RI_PK = ri_pk;
			}

			map.put("RI_CODE",     ri_code);
			map.put("RI_NAME",     ri_name);
			map.put("RI_PARENT",   ri_parent);
			map.put("RI_DEPTH",    ri_depth);
			map.put("RI_ORDERING", ri_ordering);
			map.put("RI_CATEGORY", ri_category);
			map.put("U_PK",        uPk);
			map.put("RI_PK",       RI_PK);

			//등록
			int rescnt = blogRestService.rfp_info_sorting_update(map);

			if( rescnt > 0 ) {
				res = Properties.JSON_MESSAGE_SUCCESS;

				//신규등록일경우는 am_pk값 넘겨준다.
				if( ri_pk == null ) {
					res = "{\"result\":\"success_wuth_ri_pk\", \"RI_PK\":\""+RI_PK+"\"}";
				}
			}
		}

		return res;
	}


	@PostMapping(path="rfpInfoNameSave", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String rfpInfoNameSave(
			@RequestParam(name="ri_pk", required=false, defaultValue="") String ri_pk
			, @RequestParam(name="ri_name", required=false, defaultValue="") String ri_name
			, @RequestParam(name="ri_category", required=false, defaultValue="") String ri_category
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			map.put("RI_NAME", ri_name);
			map.put("RI_MOCODE", uPk);
			map.put("RI_PK", ri_pk);
			map.put("RI_CATEGORY", ri_category);

			int rescnt = blogRestService.rfp_info_name_update(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	@PostMapping(path="rfpInfoDelete", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String rfpInfoDelete(
			@RequestParam(name="ri_pk", required=false, defaultValue="") String ri_pk, @RequestParam(name="ri_delflag", required=false, defaultValue="") String ri_delflag
			) {
		HashMap<String, String> map = new HashMap<String, String>();

		String uPk = Integer.toString(loginService.getUPK(ContextUtil.getRequest()));
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		if(uPk != null) {
			map.put("RI_DELFLAG", ri_delflag);
			map.put("RI_MOCODE", uPk);
			map.put("RI_PK", ri_pk);

			int rescnt = blogRestService.rfp_info_delete(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}

	//RFP END



	@PostMapping(path="imageUploadBase64", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String imageUploadBase64(
			@RequestParam(name="imageBase64", required=false) String imageBase64
			, @RequestParam(name="imageName", required=false) String imageName
			, @RequestParam(name="imageFilename", required=false) String imageFilename
			) {
		//리턴값 JSON형식으로 만듬
		String result = "{\"result\":\""+Properties.OUTPUT_OK+"\"}";

		//String imageBase64   = (String)request.getAttribute("imageBase64");
		//String imageName = (String)request.getAttribute("imageName");
		//String imageFilename = (String)request.getAttribute("imageFilename");

		Date today = new Date();
		SimpleDateFormat yyyyMMddHHmmss=new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			HashMap<String, String> map = new HashMap<String, String>();
			if( imageBase64 != null ){
				String extensionName = imageFilename.split("\\.")[1];

				String saveFilename = imageName+"_"+yyyyMMddHHmmss.format(today)+"."+extensionName;

//				String savePath  = Properties.PATH_PRODUCT_CONTENT+saveFilename;

	//			ImageUtils.imageDecoderWithoutImageType(imageBase64, savePath, extensionName);

				result = "{\"result\":\""+Properties.OUTPUT_OK+"\", \"filename\":\""+saveFilename+"\"}";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = Properties.OUTPUT_ERROR;
		}

		return result;
	}
}
