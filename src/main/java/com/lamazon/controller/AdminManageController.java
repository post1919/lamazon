package com.lamazon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lamazon.service.AdminManageService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.CommonUtil;
import com.lamazon.util.ContextUtil;

@Controller
@RequestMapping("/admin/manage")
public class AdminManageController {

	private static final Logger logger = LoggerFactory.getLogger(AdminManageController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	AdminManageService adminManageService;

	@Autowired
	CommonService commonService;

	@Autowired
	CommonUtil util;

	@GetMapping(value="manageGoal")
	public String manageGoal(Model model) {
		int year = Year.now().getValue();

		model.addAttribute("THIS_YEAR", year);

		return "admin/manage/manage_goal";
	}

	@GetMapping(value="businessDate")
	public String businessDate(Model model) {


		return "admin/manage/business_date";
	}

	@GetMapping(value="searchCollection")
	public String searchCollection(HttpServletRequest request) {

		//오늘 날짜 YYYY-MM-DD
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("toDate", sf.format(date));

		//3달전 날짜 YYYY-MM-DD
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();
		sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("fromDate", sf.format(date));

		//현재 년도 YEAR
		sf = new SimpleDateFormat("yyyy");

		List<String> yearList = new ArrayList<String>();
		yearList.add(Integer.parseInt(sf.format(date))-1+""); //이전해
		yearList.add(sf.format(date)); //현재
		yearList.add(Integer.parseInt(sf.format(date))+1+""); //다음해
		request.setAttribute("YEAR_LIST", yearList);

		return "admin/sales/search_collection_list";
	}

	@GetMapping(value="partnerReqLog")
	public String partnerReqLog(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<>();
		map.put("code", "PRL_MGUBUN");

		try {
			request.setAttribute("PRL_MGUBUN", commonService.common_code_list(map));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//오늘 날짜 YYYY-MM-DD
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("toDate", sf.format(date));

		//3달전 날짜 YYYY-MM-DD
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, -1);
		date = cal.getTime();
		sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("fromDate", sf.format(date));

		//현재 년도 YEAR
		sf = new SimpleDateFormat("yyyy");

		List<String> yearList = new ArrayList<String>();
		yearList.add(Integer.parseInt(sf.format(date))-1+""); //이전해
		yearList.add(sf.format(date)); //현재
		yearList.add(Integer.parseInt(sf.format(date))+1+""); //다음해
		request.setAttribute("YEAR_LIST", yearList);

		return "admin/sales/partnerreq_log_list";
	}

	@PostMapping(value="businessDateUpdate")
	public String businessDateUpdate(HttpServletRequest request, @RequestParam(name="files") MultipartFile orgFile) {
		String fileName = "";
		if(orgFile != null && !orgFile.isEmpty()) {
			fileName = orgFile.getOriginalFilename();
		}

		FileInputStream fis = null;
		File file = null;
		int workCount = 0;
		int dataCount = 0;

		File tempFile = new File("d:\\dev\\var\\www\\lamazon\\upload" + fileName); // 로컬 경로
//		File tempFile = new File(Properties.PATH_UPLOAD + fileName); // 실제 경로
		try {
			orgFile.transferTo(tempFile);
			String filePath = tempFile.getAbsolutePath();

			fis = new FileInputStream(filePath);
			file = new File(filePath);
			Workbook wb = null;
			if(filePath.toUpperCase().endsWith(".XLS")) {
				wb = new HSSFWorkbook(fis);
			} else if(filePath.toUpperCase().endsWith(".XLSX")) {
				wb = new XSSFWorkbook(fis);
			}

			Sheet sheet = wb.getSheetAt(0);

			int numOfRows = sheet.getPhysicalNumberOfRows();
			int numOfCells = 0;
			Row row = null;
			Cell cell = null;
			List<Map<String, String>> rowList = new ArrayList<>();
			Pattern datePattern = Pattern.compile("\\d{8}");

			for(int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
				row = sheet.getRow(rowIndex);
				if(row != null) {
					numOfCells = row.getPhysicalNumberOfCells();
					String cellDatas[] = new String[2];
					for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
						cell = row.getCell(cellIndex);
						String value = "";

						if(cell == null) {
							value = "";
						} else {
							if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
								value = cell.getCellFormula();
							} else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								value = ((int) cell.getNumericCellValue()) + "";
							} else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
								value = cell.getStringCellValue();
							} else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue() + "";
							} else if(cell.getCellType() == Cell.CELL_TYPE_ERROR) {
								value = cell.getErrorCellValue() + "";
							} else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
								value = "";
							} else {
								value = cell.getStringCellValue();
							}
						}
						cellDatas[cellIndex] = value;
					}
					Matcher dateMatcher = datePattern.matcher(cellDatas[0]);
					if(dateMatcher.find() && (cellDatas[1].equals("1") || cellDatas[1].equals("0"))) {
						Map<String, String> dataMap = new HashMap<>();
						dataMap.put("DATE", cellDatas[0]);
						dataMap.put("STATE", cellDatas[1]);
						rowList.add(dataMap);
					}
				}
			}

			dataCount = rowList.size();

			for(int listIndex = 0; listIndex < rowList.size(); listIndex++) {
				HashMap<String, String> map = (HashMap<String, String>)rowList.get(listIndex);
				String[] arr = new String[] {};
				int dateCount = 0;
/*
				for(String key: map.keySet()) {
					System.out.println(key + ": [ " + map.get(key) + " ]");
				}
*/
				dateCount = adminManageService.business_date_find(map);
				if(dateCount == 1) {
					workCount += adminManageService.business_date_update(map);
				} else if(dateCount == 0) {
					workCount += adminManageService.business_date_insert(map);
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(file.exists()) {
				file.delete();
			}
		}

		request.setAttribute("WORK_COUNT", workCount);
		request.setAttribute("DATA_COUNT", dataCount);
		request.setAttribute("GAP_COUNT", Math.abs(dataCount - workCount));

		return "admin/manage/business_date_upload";
	}

	@GetMapping(value="commonCodeList")
	public String commonCodeList(Model model) {
		//form.mapping(".admin_manage_commoncode_list");
		return "admin/manage/admin_manage_commoncode_list";
	}

	@GetMapping(value="commonCodePopup")
	public String commonCodePopup(
			Model model
			, @RequestParam(value="cmmCodeGroup", required=false, defaultValue="") String cmmCodeGroup
			) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("CMM_CODE_GROUP", cmmCodeGroup);
		//String arr[] = new String[] {cmmCodeGroup};

		//코드마스터 정보
		model.addAttribute("CODE_MST_M", adminManageService.common_code_master_detail(map));

		//코드상세 정보
		model.addAttribute("CODE_MST", adminManageService.common_code_detail_list(map));

		model.addAttribute("cmmCodeGroup", cmmCodeGroup);

		//form.mapping(".admin_manage_commoncode_list_pop");
		return "popup/admin/manage/admin_manage_commoncode_pop";
	}

	@GetMapping(value="kakaoList")
	public String kakaoList(Model model) {
		//form.mapping(".admin_manage_commoncode_list");
		return "admin/manage/kakao_list";
	}

	@GetMapping(value="kakaoDetailPopup")
	public String kakaoDetailPopup(Model model
			, @RequestParam(value="pk", required=false, defaultValue="") String pk) {

		Map map = new HashMap();
		map.put("KM_PK", pk);

		//상세
		if( pk != null ) {
			//주요업종 공통코드 목록
			model.addAttribute("KAKAO_DETAIL", adminManageService.kakao_detail(map));
		}

		model.addAttribute("pk", pk);

		//form.mapping(".admin_kakao_detail");
		//return;
		return "popup/admin/manage/kakao_detail";
	}

	@GetMapping(value="mailList")
	public String mailList(Model model) {
		//form.mapping(".admin_mail_list");
		return "admin/manage/mail_list";
	}

	@GetMapping(value="mailDetail")
	public String mailDetail(
			Model model
			, @RequestParam(value="mi_pk", required=false) String mi_pk
			, @RequestParam(value="table_name", required=false) String table_name
			) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("MI_PK", mi_pk);

		//메일 기본정보
		model.addAttribute("MAIL_DETAIL",  adminManageService.mail_info_detail(map));

		//템플릿 정보
		String[] param = new String[] {};
		ArrayList templateList = adminManageService.mail_info_template_list(map);
		model.addAttribute("TEMPLATE_LIST", templateList);

		model.addAttribute("mi_pk", mi_pk);

		//form.mapping(".admin_mail_detail");
		//return;
		return "admin/manage/mail_detail";
	}

	@GetMapping(value="mailTemplateList")
	public String mailTemplateList(Model model) {
		//form.mapping(".admin_mail_list");
		return "admin/manage/mail_template_list";
	}

	//메일템플릿관리 상세
	@GetMapping(value="mailTemplateDetail")
	public String mailTemplateDetail(Model model
			, @RequestParam(value="mit_pk", required=false) String mit_pk
			) {
		//템플릿 정보
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("MIT_PK", mit_pk);
		//String[] param = new String[] {mit_pk};

		model.addAttribute("MAIL_TEMPLATE", adminManageService.mail_info_template_detail(map));

		model.addAttribute("mit_pk", mit_pk);

		//form.mapping(".admin_mail_template_detail");
		//return;

		return "admin/manage/mail_template_detail";
	}

	//메일기본관리 신규등록
	@GetMapping(value="mailRegist")
	public String mailRegist_get(Model model) {
		//템플릿 정보
		//ArrayList templateList = DBManager.getInstance().makeMapArray("mail_info_template_list", map, param);
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList templateList = adminManageService.mail_info_template_list(map);
		model.addAttribute("TEMPLATE_LIST", templateList);

		//form.mapping(".admin_mail_detail");
		//return;

		return "admin/manage/mail_detail";
	}

	//메일관리 - 등록/수정
	@PostMapping(value="mailRegist")
	public String mailRegist_post(Model model
			, @RequestParam(value="mi_pk", required=false, defaultValue="") String  mi_pk
			, @RequestParam(value="mi_memo", required=false, defaultValue="") String  mi_memo
			, @RequestParam(value="mi_gubun", required=false, defaultValue="") String  mi_gubun
			, @RequestParam(value="mi_title", required=false, defaultValue="") String  mi_title
			, @RequestParam(value="content", required=false, defaultValue="") String  mi_content
			, @RequestParam(value="mi_status", required=false, defaultValue="") String  mi_status
			, @RequestParam(value="mit_pk", required=false, defaultValue="0") String  mit_pk
			) {
		int uPk = loginService.getUPK(ContextUtil.getRequest());

		HashMap<String, String> map = new HashMap<String, String>();

		//수정일 경우만 사용
		if( !"".equals(mi_pk) ) {
			map.put("MOCODE", "MOCODE");
			map.put("MODATE", "MODATE");
		}

		String MI_PK = "";
		//메일기본정보 신규등록
		if( "".equals(mi_pk) ) {
			HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
	    	paramMap.put("MI_INCODE",  new String[] {uPk+"",  "int"});
	    	paramMap.put("MI_INDATE",  new String[] {"NOW()", "int"});

			int _pk = commonService.getNewPk("MAIL_INFO", paramMap, "");
			MI_PK = Integer.toString(_pk);

		//수정
		} else {
			MI_PK = mi_pk;
		}

		//메일 기본정보 UPDATE
		map = new HashMap<String, String>();
		map.put("MIT_PK", mit_pk);
		//String[] arr = new String[]{ mi_gubun, mi_memo, mi_title, mi_content, mi_status, MI_PK };
		//this.insert_table("mail_info_update", map, arr);
		map.put("MI_GUBUN",   mi_gubun);
		map.put("MI_MEMO",    mi_memo);
		map.put("MI_TITLE",   mi_title);
		map.put("MI_CONTENT", mi_content);
		map.put("MI_STATUS",  mi_status);
		map.put("MI_PK",      MI_PK);

		int rescnt = adminManageService.mail_info_update(map);

		//response.sendRedirect("/admin/manage/mailDetail?mi_pk="+MI_PK);
		//return;
		return "redirect:/admin/manage/mailDetail?mi_pk="+MI_PK;
	}

	//메일관리 - 등록/수정
	@GetMapping(value="mailTemplateRegist")
	public String mailTemplateRegist_get(Model model) {
		//form.mapping(".admin_mail_template_detail");
		//return;
		return "admin/manage/mail_template_detail";
	}

	//메일관리 - 등록/수정
	@PostMapping(value="mailTemplateRegist")
	public String mailTemplateRegist_post(Model model
			, @RequestParam(value="mit_pk", required=false, defaultValue="") String  mit_pk
			, @RequestParam(value="mit_memo", required=false, defaultValue="") String  mit_memo
			, @RequestParam(value="content", required=false, defaultValue="") String  mit_content
			, @RequestParam(value="mit_status", required=false, defaultValue="") String  mit_status
			) {
		int uPk = loginService.getUPK(ContextUtil.getRequest());
		HashMap<String, String> map = new HashMap<String, String>();

		//수정일 경우만 사용
		if( !"".equals(mit_pk) ) {
			map.put("MOCODE", "MOCODE");
			map.put("MODATE", "MODATE");
		}

		String MIT_PK = "";
		//메일기본정보 신규등록
		if( "".equals(mit_pk) ) {
			HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
			paramMap.put("MIT_INCODE",  new String[] {uPk+"",     "int"});
			paramMap.put("MIT_INDATE",  new String[] {"NOW()", "int"});

			int _pk = commonService.getNewPk("MAIL_INFO_TEMPLATE", paramMap, "");
			MIT_PK = Integer.toString(_pk);

			//수정
		} else {
			MIT_PK = mit_pk;
		}

		//메일템플릿 UPDATE
		map = new HashMap<String, String>();
		//String[] arr = new String[]{ mit_memo, mit_content, mit_status, MIT_PK };
		//this.insert_table("mail_info_template_update", map, arr);
		map.put("MIT_MEMO", mit_memo);
		map.put("MIT_CONTENT", mit_content);
		map.put("MIT_STATUS", mit_status);
		map.put("MIT_PK", MIT_PK);
		int rescnt = adminManageService.mail_info_template_update(map);

		//response.sendRedirect("/admin/manage/mailTemplateDetail?mit_pk="+MIT_PK);
		//return;
		return "redirect:/admin/manage/mailTemplateDetail?mit_pk="+MIT_PK;
	}

	//메뉴관리 목록
	@GetMapping(value="adminMenu")
	public String adminMenu(Model model) {
		//메뉴 목록
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("code", "U_TYPE");
			model.addAttribute("U_TYPE",  commonService.commonCodeList(map));

			model.addAttribute("ADMIN_MENU_LIST", commonService.adminMenuListTreeAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "admin/manage/menu_list";
	}

	//공통코드/마스터 등록/수정 팝업
	@PostMapping(value="commonCodeUpdate")
	public String commonCodeUpdate(
			  @RequestParam(value="procFlag", required=false, defaultValue="") String procFlag
			, @RequestParam(value="paramCCM_CODE_GROUP", required=false, defaultValue="0") String paramCCM_CODE_GROUP
			, @RequestParam(value="paramCM_CODE", required=false, defaultValue="0") String paramCM_CODE
			, @RequestParam(value="paramCM_NAME", required=false, defaultValue="0") String paramCM_NAME
			, @RequestParam(value="paramCM_MEMO", required=false, defaultValue="0") String paramCM_MEMO
			, @RequestParam(value="paramCM_SORT", required=false, defaultValue="0") String paramCM_SORT
			, @RequestParam(value="paramCM_USEYN", required=false, defaultValue="0") String paramCM_USEYN

			, @RequestParam(value="CM_CODE_SIZE", required=false, defaultValue="0") int CM_CODE_SIZE
			, Model model
			, HttpServletRequest request
			) {

		int uPk = loginService.getUPK(request);
		int cPk = loginService.getCPK(request);

		HashMap map = new HashMap();
		//String[] arr;

		int res = 0;
		if( !"".equals(procFlag) ){

			//공통코드 일괄저장
		    if( "b".equals(procFlag) ){
		    	if( CM_CODE_SIZE > 0 ){

		    		String[] arrROWFLAG    = new String[CM_CODE_SIZE];
    				String[] arrCM_CODE    = new String[CM_CODE_SIZE];
    				String[] arrCM_NAME	   = new String[CM_CODE_SIZE];
    				String[] arrCM_MEMO	   = new String[CM_CODE_SIZE];
    				//String[] arrCM_SORT	   = new String[CM_CODE_SIZE];
    				String[] arrCM_USEYN   = new String[CM_CODE_SIZE];

			        Enumeration e = request.getParameterNames();

			        String name = "";
			        String value[] = null;

			        while(e.hasMoreElements()) {
			            name = (String)e.nextElement();

			            value = request.getParameterValues(name);

			            for( int i=0; i<value.length; i++ ){
			                // value[i] = new String(value[i].getBytes("ISO-8859-1"), "UTF-8"); //한글변환

			            	if( "ROWFLAG" .equals(name) ) arrROWFLAG [i] = value[i];
			            	if( "CM_CODE" .equals(name) ) arrCM_CODE [i] = value[i];
			            	if( "CM_NAME" .equals(name) ) arrCM_NAME [i] = value[i];
			            	if( "CM_MEMO" .equals(name) ) arrCM_MEMO [i] = value[i];
			            	//if( "CM_SORT" .equals(name) ) arrCM_SORT [i] = value[i];
			            	if( "CM_USEYN".equals(name) ) arrCM_USEYN[i] = value[i];
			            }
			        }

			        int sortNum = 1;
			        for(int i=0; i<arrROWFLAG.length; i++){

			        	//화면에 숨겨져있는 추가영역 및 코드입력 안된 애들 제외시킴(CM_CODE값없는결로 체크함)
			        	if( !"".equals(arrCM_CODE[i]) ){
				        	//신규
							if( "INSERT".equals(arrROWFLAG[i]) ){
								//arr = new String[]{ paramCCM_CODE_GROUP, arrCM_CODE[i], arrCM_NAME[i], arrCM_MEMO[i], Integer.toString(sortNum++), arrCM_USEYN[i], uPk };
								//res = DBManager.getInstance().insert_table("common_code_detail_insert", map, arr);
								map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
								map.put("CM_CODE", arrCM_CODE[i]);
								map.put("CM_NAME", arrCM_NAME[i]);
								map.put("CM_MEMO", arrCM_MEMO[i]);
								map.put("sortNum", Integer.toString(sortNum++));
								map.put("CM_USEYN", arrCM_USEYN[i]);
								map.put("U_PK", uPk);

								int rescnt = adminManageService.common_code_detail_insert(map);

							//수정
							} else if( "UPDATE".equals(arrROWFLAG[i]) ){
								//arr = new String[]{ arrCM_NAME[i], arrCM_MEMO[i], Integer.toString(sortNum++), arrCM_USEYN[i], uPk, paramCCM_CODE_GROUP, arrCM_CODE[i] };
								//res = DBManager.getInstance().insert_table("common_code_detail_update", map, arr);

								map.put("CM_NAME", arrCM_NAME[i]);
								map.put("CM_MEMO", arrCM_MEMO[i]);
								map.put("sortNum", Integer.toString(sortNum++));
								map.put("CM_USEYN", arrCM_USEYN[i]);
								map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
								map.put("CM_CODE", arrCM_CODE[i]);
								map.put("U_PK", uPk);
								int rescnt = adminManageService.common_code_detail_update(map);
							}
			        	}
			        }
		    	}

			//공통코드 마스터 등록
		    } else if( "im".equals(procFlag) ){
				//arr = new String[]{ paramCCM_CODE_GROUP, paramCM_NAME, paramCM_USEYN, "admin" };
				//res = DBManager.getInstance().insert_table("common_code_master_insert", map, arr);
		    	map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
		    	map.put("CM_NAME", paramCM_NAME);
		    	map.put("CM_USEYN", paramCM_USEYN);
				map.put("U_PK", "admin");
				int rescnt = adminManageService.common_code_master_insert(map);

			//공통코드 마스터 수정
			} else if( "um".equals(procFlag) ){
				//arr = new String[]{ paramCM_NAME, paramCM_USEYN, "admin", paramCCM_CODE_GROUP };
				//res = DBManager.getInstance().insert_table("common_code_master_update", map, arr);
				map.put("CM_NAME", paramCM_NAME);
				map.put("CM_USEYN", paramCM_USEYN);
				map.put("U_PK", "admin");
				map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
				int rescnt = adminManageService.common_code_master_update(map);

			//공통코드 상세 등록
			} else if( "i".equals(procFlag) ){
				//arr = new String[]{ paramCCM_CODE_GROUP, paramCM_CODE, paramCM_NAME, paramCM_MEMO, paramCM_SORT, paramCM_USEYN, "admin" };
				//res = DBManager.getInstance().insert_table("common_code_detail_insert", map, arr);
				map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
				map.put("CM_CODE", paramCM_CODE);
				map.put("CM_NAME", paramCM_NAME);
				map.put("CM_MEMO", paramCM_MEMO);
				map.put("CM_SORT", paramCM_SORT);
				map.put("CM_USEYN", paramCM_USEYN);
				map.put("U_PK", "admin");
				int rescnt = adminManageService.common_code_detail_insert(map);

			//공통코드 상세 수정
			} else {
				//arr = new String[]{ paramCM_NAME, paramCM_MEMO, paramCM_SORT, paramCM_USEYN, "admin", paramCCM_CODE_GROUP, paramCM_CODE };
				//res = DBManager.getInstance().insert_table("common_code_detail_update", map, arr);
				map.put("CM_NAME", paramCM_NAME);
				map.put("CM_MEMO", paramCM_MEMO);
				map.put("CM_SORT", paramCM_SORT);
				map.put("CM_USEYN", paramCM_USEYN);
				map.put("U_PK", "admin");
				map.put("CCM_CODE_GROUP", paramCCM_CODE_GROUP);
				map.put("CM_CODE", paramCM_CODE);
				int rescnt = adminManageService.common_code_detail_update(map);
			}

		// NEW OPENER 페이지 RELOAD하고 현재페이지로 다시 이동
		request.setAttribute("OPENER_RELOAD", "OPENER_RELOAD");
		request.setAttribute("NEXT_URL", "/admin/manage/commonCodePopup?cmmCodeGroup="+paramCCM_CODE_GROUP);

		//form.mapping(".message_out_popup_close");
		return "exception/message_out_popup_close";

		//response.sendRedirect("/manage/user/infoeval?pk="+pk);
		//return;

		} else {
			//form.mapping(".error");
			return "exception/error";
		}
	}

	//카카오정보 - 등록/수정
	@PostMapping(value="kakao_update")
	public String kakao_update(Model model
			, @RequestParam(value="km_pk", required=false, defaultValue="") String  km_pk
			, @RequestParam(value="km_flag", required=false, defaultValue="") String  km_flag
			, @RequestParam(value="km_status", required=false, defaultValue="") String  km_status
			, @RequestParam(value="tmpl_code", required=false, defaultValue="") String  tmpl_code
			, @RequestParam(value="km_situation", required=false, defaultValue="") String  km_situation
			, @RequestParam(value="tmpl_name", required=false, defaultValue="") String  tmpl_name
			, @RequestParam(value="tmpl_msg", required=false, defaultValue="") String  tmpl_msg
			) {
		String uPk = String.valueOf(loginService.getUPK(ContextUtil.getRequest()));

		HashMap<String, String> map = new HashMap<String, String>();

		//수정일 경우만 사용
		if( !"".equals(km_pk) ) {
			map.put("MOCODE", "MOCODE");
			map.put("MODATE", "MODATE");
		}

		String KM_PK = "";
		//메일기본정보 신규등록
		if( "".equals(km_pk) ) {
			HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
			paramMap.put("INCODE",    new String[] {uPk,       "int"});
			paramMap.put("INDATE",    new String[] {"NOW()",   "int"});
			paramMap.put("TMPL_CODE", new String[] {tmpl_code, "String"});

			int _pk = commonService.getNewPk("KAKAO_MSG_MST", paramMap, "");
			KM_PK = Integer.toString(_pk);

			//수정
		} else {
			KM_PK = km_pk;
		}

		//String[] arr = new String[]{ km_situation, tmpl_name, tmpl_msg, km_status, km_flag, uPk, KM_PK };
		//this.insert_table("update_kakao_msg_mst", map, arr);

		//메일템플릿 UPDATE
		map = new HashMap<String, String>();
		map.put("KM_SITUATION", km_situation);
		map.put("TMPL_NAME", tmpl_name);
		map.put("TMPL_MSG", tmpl_msg);
		map.put("KM_STATUS", km_status);
		map.put("KM_FLAG", km_flag);
		map.put("U_PK", uPk);
		map.put("KM_PK", KM_PK);

		int rescnt = adminManageService.update_kakao_msg_mst(map);

		//response.sendRedirect("/admin/manage/kakaoDetailPopup?pk="+	KM_PK);
		//return;
		return "redirect:/admin/manage/kakaoDetailPopup?pk="+KM_PK;
	}
}
