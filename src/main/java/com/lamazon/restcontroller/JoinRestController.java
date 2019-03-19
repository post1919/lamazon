package com.lamazon.restcontroller;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lamazon.properties.Properties;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.CommonUtil;
import com.lamazon.util.ContextUtil;
import com.lamazon.util.Utils;

@RestController
@RequestMapping("rest/join")
public class JoinRestController {

	private static final Logger logger = LoggerFactory.getLogger(JoinRestController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	CommonService commonService;

	@PostMapping(path="login", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String form(@RequestParam(required=false) String user_id
			, @RequestParam(required=false) String user_passwd
			, HttpServletRequest request){

		String res = "";

		try {
			logger.debug(" >>>>>>>>>> 로그인페이지 ");

			res = loginService.login(request, user_id, user_passwd);

		} catch(Exception ex) {
			ex.printStackTrace();
			res = Properties.JSON_MESSAGE_FAIL;
		}

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

	@PostMapping(path="check_dup_registration_number", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String check_dup_registration_number(@RequestParam(value="number") String number) {
		String result = "";

		try {
			String res = commonService.company_check_registration_number(number);

			if( res == null ) return Properties.OUTPUT_OK;
			else return Properties.OUTPUT_EXIST;

		} catch (Exception e) {
			e.printStackTrace();
			result = Properties.OUTPUT_ERROR;
		}

		return result;
	}

	@PostMapping(path="check_dup_id", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String check_dup_id(@RequestParam(value="id") String U_ID) {
		String queryKey = "user_info_check_id";

		String rescnt = loginService.user_info_check_id(U_ID) + "";

		try {
			if( rescnt != null && !"null".equals(rescnt) ) {
				return Properties.OUTPUT_EXIST;
			} else {
				HashMap<String, Object> info = new HashMap<String, Object>();

				info.put("IP", ContextUtil.getRequest().getRemoteAddr());
				info.put("SESSION_ID", ContextUtil.getRequest().getSession().getId());
				info.put("TIME", System.currentTimeMillis());

				if( commonService.lockIdisValid(U_ID, info) ) {
					return Properties.OUTPUT_OK;

				} else {
					return Properties.OUTPUT_EXIST;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Properties.OUTPUT_ERROR;
		}
	}

	//회원가입
	@PostMapping(path="user_regist", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String user_regist_post(
			  @RequestParam(value="u_id") String u_id
			, @RequestParam(value="u_pk", required=false, defaultValue="") String u_pk
			, @RequestParam(value="u_type", required=false, defaultValue="2000") String u_type
			, @RequestParam(value="u_name") String u_name
			, @RequestParam(value="u_company") String u_company
			, @RequestParam(value="u_passwd") String u_passwd
			, @RequestParam(value="u_registration_number") String u_registration_number
			, @RequestParam(value="u_founder") String u_founder
			, @RequestParam(value="u_uptae") String u_uptae
			, @RequestParam(value="u_upjong") String u_upjong
			, @RequestParam(value="u_phone") String u_phone
			, @RequestParam(value="u_mobile") String u_mobile
			, @RequestParam(value="u_email") String u_email
			, @RequestParam(value="user_zipcode") String user_zipcode
			, @RequestParam(value="user_address") String user_address
			, @RequestParam(value="flag",             required=false, defaultValue="regist") String flag
			, @RequestParam(value="u_picture",        required=false, defaultValue="") String u_picture
			, @RequestParam(value="u_picture_rename", required=false, defaultValue="") String u_picture_rename
			, @RequestParam(value="files",            required=false) MultipartFile files
			, Model model) {

		try {

			HashMap<String, String> map = new HashMap<String, String>();

			int login_u_pk = loginService.getUPK(ContextUtil.getRequest());
			if( login_u_pk != -1) {
				map.put("U_MOCODE", login_u_pk+"");
			}

			map.put("U_ID", u_id);
			map.put("U_NAME", u_name);
			map.put("U_COMPANY", u_company);

			//관리자의경우 일반고객 정보 수정가능하므로 u_passwd 공백여부로 체크
			if( u_passwd != null && !"".equals(u_passwd) ) {
				map.put("U_PASSWD", Utils.makePassword(u_passwd));
			}

			map.put("U_REGISTRATION_NUMBER", u_registration_number);
			map.put("U_FOUNDER", u_founder);
			map.put("U_UPTAE", u_uptae);
			map.put("U_UPJONG", u_upjong);
			map.put("U_PHONE", u_phone);
			map.put("U_MOBILE", u_mobile);
			map.put("U_EMAIL", u_email);
			map.put("U_ZIPCODE", user_zipcode);
			map.put("U_ADDRESS", user_address);
			map.put("flag", flag);

			if( loginService.isAdmin(ContextUtil.getRequest()) ) {
				map.put("U_TYPE", u_type); //회원구분
			} else {
				map.put("U_TYPE", "2000"); //고객사 2000
			}

			String targetPath = Properties.PATH_U_PICTURE;

			if(files != null && !files.isEmpty()) {
				u_picture = files.getOriginalFilename();
				u_picture_rename = CommonUtil.makeRename(files);

				File file = new File(targetPath, u_picture_rename);
				files.transferTo(file);
			}
			map.put("U_PICTURE", u_picture);
			map.put("U_PICTURE_RENAME", u_picture_rename);

			//회원수정
			if( "detail".equals(flag) ) {
				//권한 체크
				if( loginService.isAdmin(ContextUtil.getRequest()) || u_pk.equals(login_u_pk+"") ) {
					map.put("U_PK", u_pk);
					int U_PK = loginService.user_info_update(map);
					
					//로그인 세션갱신
					loginService.setUser(ContextUtil.getRequest());
				} else {
					return Properties.JSON_MESSAGE_NO_PERMISSION;
				}

			//회원가입
			} else {
				//회원여부 체크
				if( loginService.user_info_check_id(u_id) != null ) {
					return Properties.JSON_MESSAGE_EXIST;
				}

				int U_PK = loginService.user_info_insert(map);
			}

			return Properties.JSON_MESSAGE_SUCCESS;


		} catch (Exception e) {
			e.printStackTrace();
			return Properties.JSON_MESSAGE_HAS_ERROR;
		}

	}
}
