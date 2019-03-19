package com.lamazon.restcontroller;

import java.io.File;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lamazon.properties.Properties;
import com.lamazon.service.BbsService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.CommonUtil;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@RestController
@RequestMapping("rest/bbs")
public class BbsRestController {

	private static final Logger logger = LoggerFactory.getLogger(BbsRestController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	CommonService commonService;

	@Autowired
	BbsService bbsService;

	@GetMapping(path="list", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String list(
			  @RequestParam(value="name",         required=false) String name
			, @RequestParam(value="b_title",        required=false) String b_title
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
			HashMap map = new HashMap();

			if( !loginService.isAdmin(ContextUtil.getRequest()) ) {
				map.put("U_PK", loginService.getUPK(ContextUtil.getRequest())+"");
			}

			if(name!=null && name.length()>0) {
				map.put("NAME", name);
			}

			if(b_title!=null && b_title.length()>0) {
				map.put("B_TITLE", b_title);
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

			commonService.setPQ_sort(pq_sort, map, "ORDER BY B_PK DESC");

			int total = bbsService.bbs_cnt(map);

			JSONObject obj = new JSONObject();
			obj.put("data", bbsService.bbs_list(map));

			obj.put("total_count", total);
			obj.put("page", p);

			res = obj.toString();

		} catch(Exception ex) {
			ex.printStackTrace();
			res = Properties.JSON_MESSAGE_FAIL;
		}

		return res;
	}

	//게시물 등록/수정
	@PostMapping(path="regist/auth", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String regist_master(
			  @RequestParam(value="b_pk", required=false) String b_pk
			, @RequestParam(value="r_u_pk", required=false) String r_u_pk
			, @RequestParam(value="b_title") String b_title
			, @RequestParam(value="b_content") String b_content
			, @RequestParam(value="flag", required=false, defaultValue="") String flag
			, @RequestParam(value="b_file",        required=false, defaultValue="") String b_file
			, @RequestParam(value="b_file_rename", required=false, defaultValue="") String b_file_rename
			, @RequestParam(value="files", required=false) MultipartFile files
			, Model model) {

		JSONObject obj = new JSONObject();

		try {
			HashMap map = new HashMap();
			int B_PK = -1;

			int login_u_pk = loginService.getUPK(ContextUtil.getRequest());

			map.put("B_TITLE", b_title);
			map.put("B_CONTENT", b_content);
			map.put("R_U_PK",   login_u_pk+"");

			String targetPath = Properties.PATH_B_FILE;

			if(files != null && !files.isEmpty()) {
				b_file = files.getOriginalFilename();
				b_file_rename = CommonUtil.makeRename(files);

				File file = new File(targetPath, b_file_rename);
				files.transferTo(file);

				map.put("B_FILE", b_file);
				map.put("B_FILE_RENAME", b_file_rename);
			}

			//등록
			if( "regist".equals(flag) && Utils.isEmpty(b_pk) ) {
				map.put("B_INCODE", login_u_pk+"");
				int bbs_insert_cnt = bbsService.bbs_insert(map);

				if( map.get("B_PK") != null ) {
					B_PK = (int)(long)map.get("B_PK");
				}

			//수정
			} else {
				if( b_pk == null ) {
					return Properties.JSON_MESSAGE_HAS_ERROR;
				}

				B_PK = Integer.valueOf(b_pk);

				//권한 체크
				if( loginService.isAdmin(ContextUtil.getRequest()) || r_u_pk.equals(login_u_pk+"") ) {
					map.put("B_PK", b_pk);
					map.put("B_MOCODE", login_u_pk+"");
					int bbs_update_cnt = bbsService.bbs_update(map);
				} else {
					return Properties.JSON_MESSAGE_NO_PERMISSION;
				}
			}

			obj.put("B_PK", B_PK);
			obj.put("result", Properties.JSON_SUCCESS);
			return obj.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}
	}

	//댓글 등록/수정
	@PostMapping(path="regist_detail/auth", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String regist_detail(
			  @RequestParam(value="u_pk") String u_pk
			, @RequestParam(value="b_pk") String b_pk
			, @RequestParam(value="br_pk") String br_pk
			, @RequestParam(value="br_content") String br_content
			, @RequestParam(value="flag",           required=false, defaultValue="regist") String flag
			, @RequestParam(value="br_file",        required=false, defaultValue="") String br_file
			, @RequestParam(value="br_file_rename", required=false, defaultValue="") String br_file_rename
			, @RequestParam(value="files",          required=false) MultipartFile files
			, Model model) {

		try {

			HashMap<String, String> map = new HashMap();
			int login_u_pk = loginService.getUPK(ContextUtil.getRequest());

			int br_update_cnt = 0;
			int br_insert_cnt = 0;

			map.put("B_PK",       b_pk);
			map.put("BR_PK",      br_pk);
			map.put("U_PK",       u_pk);
			map.put("BR_CONTENT", br_content);

			String targetPath = Properties.PATH_B_FILE;

			if(files != null && !files.isEmpty()) {
				br_file = files.getOriginalFilename();
				br_file_rename = CommonUtil.makeRename(files);

				File file = new File(targetPath, br_file_rename);
				files.transferTo(file);
			}
			map.put("BR_FILE", br_file);
			map.put("BR_FILE_RENAME", br_file_rename);

			//수정
			if( "detail".equals(flag) ) {
				//권한 체크
				if( loginService.isAdmin(ContextUtil.getRequest()) || u_pk.equals(login_u_pk+"") ) {
					map.put("BR_PK", br_pk);
					map.put("BR_MOCODE", login_u_pk+"");
					br_update_cnt = bbsService.bbs_reply_update(map);
				} else {
					return Properties.JSON_MESSAGE_NO_PERMISSION;
				}
			//등록
			} else {
				map.put("BR_INCODE", login_u_pk+"");
				br_insert_cnt = bbsService.bbs_reply_insert(map);
			}

			if( br_insert_cnt == 0 && br_update_cnt == 0 ) {
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
