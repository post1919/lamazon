<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="org.apache.poi.hssf.usermodel.*
               , java.util.*
               , com.lamazon.properties.Properties
               , com.lamazon.util.*
               , java.net.*
               , java.io.*
               , org.apache.poi.ss.usermodel.CellStyle
               , org.apache.poi.ss.usermodel.Cell
               , org.apache.poi.ss.usermodel.Row
               , org.apache.poi.ss.usermodel.Sheet
               , org.apache.poi.hssf.util.HSSFColor
               , org.apache.poi.hssf.usermodel.HSSFWorkbook
               , org.apache.poi.xssf.usermodel.XSSFCellStyle
               , org.apache.poi.ss.usermodel.Font
               " 
%>
               <%-- org.jepetto.xls.XcelWriter --%>

<%
	CommonUtil commonUtil = new CommonUtil();

	String name = "회원리스트";
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	String type        = request.getParameter("type");
	String code   = request.getParameter("opt_user");
	String user_name   = request.getParameter("user_name");
	String user_email  = request.getParameter("user_email");
	String user_mobile = request.getParameter("user_mobile");
	String user_id     = request.getParameter("user_id");
	String sFromdate   = request.getParameter("sFromdate");
	String sTodate     = request.getParameter("sTodate");
	String U_COMPANY     = request.getParameter("U_COMPANY");
	String U_NEWSLETTER     = request.getParameter("U_NEWSLETTER");
	
	HashMap<String, String> _map = new HashMap<String, String>();
	/* 
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
	
	String uPk = LoginManager.getInstance().getUPK(request);
	String arr[] = new String[] {};
	
	ArrayList list = DBManager.getInstance().makeMapArray("manage_user_list", _map, arr);
	 */
	
	ArrayList list = (ArrayList)request.getAttribute("list");
	 
	System.out.println("list >>>>>>>>>>>>>>>>>>> " + list);
	
	ArrayList<String> list_label = new ArrayList<String>();
	ArrayList<String> list_tab = new ArrayList<String>();
	ArrayList<Short> list_align = new ArrayList<Short>();
	
	list_label.add("PK");				list_tab.add("PK");								list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("이름");				list_tab.add("NAME");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("아이디");			list_tab.add("ID");								list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("이메일");			list_tab.add("EMAIL");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("연락처");			list_tab.add("PHONE");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("휴대폰");			list_tab.add("MOBILE");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("형태");				list_tab.add("ID_TYPE");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("회사명");			list_tab.add("COMPANY");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("대표자명");			list_tab.add("FOUNDER");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("직무");				list_tab.add("DUTY");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("가입일");			list_tab.add("REGISTER_DATE");					list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("승인일");			list_tab.add("CERTIFY_DATE");					list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("회사인증상태");		list_tab.add("U_COMPANY_CERITIFY_TXT");			list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("의뢰");				list_tab.add("PR_CNT");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("계약");				list_tab.add("PROJ_CONTRACT_NUM");				list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("계약금액");			list_tab.add("PROJ_TOTAL_PRICE");				list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("추천인아이디");		list_tab.add("AGENT_ID");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("추천인명");		    list_tab.add("AGENT_NAME");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("고객직무");		    list_tab.add("U_DUTY");							list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("고객_프로젝트_카테고리"); list_tab.add("U_PROJECT_CATE");			list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("사업자번호");        list_tab.add("REGISTRATION_NUMBER");			list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("상담요청횟수");      list_tab.add("QNA_CNT");						list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("전화문의횟수");      list_tab.add("PHONE_POP_CNT");					list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("전화클릭수");        list_tab.add("PHONE_CALL_CNT");				list_align.add(CellStyle.ALIGN_CENTER);
	//list_label.add("최대등록카테고리");      list_tab.add("PR_CATEGORY_MAX");
	//list_label.add("최대등록카테고리명");      list_tab.add("PR_CATEGORY_MAX_NM");
	//list_label.add("최대등록카테고리 수");      list_tab.add("PR_CATEGORY_MAX_CNT");
	list_label.add("의뢰등록유무");      list_tab.add("PROJECT_REG_FLAG");				list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("이메일용 직무 구분");   list_tab.add("EMAIL_GUBUN");				list_align.add(CellStyle.ALIGN_CENTER);
	//list_label.add("이메일용 목적 구분");   list_tab.add("PURPOSE_GUBUN");			list_align.add(CellStyle.ALIGN_CENTER);
	list_label.add("뉴스레터수신여부");   list_tab.add("U_NEWSLETTER_NM");				list_align.add(CellStyle.ALIGN_CENTER);

FileOutputStream fileOut = null;
	
//	XcelWriter excel = new XcelWriter();
	HSSFWorkbook excel = new HSSFWorkbook();
	HSSFSheet sheet = excel.createSheet(name);
	
	Font headerFont = excel.createFont();
	headerFont.setColor(HSSFColor.BLACK.index);
	headerFont.setBold(true);
	
	//style
	sheet.setZoom(15,20);//75% scale (분자/분모)
	
	sheet.setDefaultRowHeight((short) (960/2));
	sheet.setHorizontallyCenter(true);
    sheet.setVerticallyCenter(true);
    
    HSSFCellStyle headerStyle        = excel.createCellStyle();
    HSSFCellStyle contentStyleLeft   = excel.createCellStyle();
    HSSFCellStyle contentStyleCenter = excel.createCellStyle();
    HSSFCellStyle contentStyleRight  = excel.createCellStyle();
    
    short stdStyle =   XSSFCellStyle.BORDER_THIN;  // 일반라인
    
 	// 선처리(일반선)
    headerStyle.setBorderLeft(stdStyle);
    headerStyle.setBorderRight(stdStyle);
    headerStyle.setBorderTop(stdStyle);
    headerStyle.setBorderBottom(stdStyle);
    headerStyle.setAlignment(CellStyle.ALIGN_CENTER);            //가운데정렬
    headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //가운데정렬
    headerStyle.setFont(headerFont);                             //폰트
    
    contentStyleLeft.setBorderLeft(stdStyle);
    contentStyleLeft.setBorderRight(stdStyle);
    contentStyleLeft.setBorderTop(stdStyle);
    contentStyleLeft.setBorderBottom(stdStyle);
    contentStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    contentStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);           
    
    contentStyleCenter.setBorderLeft(stdStyle);
    contentStyleCenter.setBorderRight(stdStyle);
    contentStyleCenter.setBorderTop(stdStyle);
    contentStyleCenter.setBorderBottom(stdStyle);
    contentStyleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    contentStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);           
    
    contentStyleRight.setBorderLeft(stdStyle);
    contentStyleRight.setBorderRight(stdStyle);
    contentStyleRight.setBorderTop(stdStyle);
    contentStyleRight.setBorderBottom(stdStyle);
    contentStyleRight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    contentStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);         
    
 	// Cell 색깔, 무늬 채우기
	headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
 	//HSSFPalette palette = excel.getCustomPalette();
	//palette.setColorAtIndex(HSSFColor.SKY_BLUE.index, (byte) 228, (byte) 236, (byte) 247); 
	
    headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    
	int rownum = 0;
	
	HSSFRow row = sheet.createRow(rownum);
	
	//헤더
	for( int i=0; i<list_label.size(); i++ ){
		HSSFCell cell = row.createCell(i);
		cell.setCellValue((String)list_label.get(i));
		cell.setCellStyle(headerStyle); // 셀 스타일 적용
		sheet.setColumnWidth(i, 256*20);
	}
	
	//본문
	for( int i=0; i<list.size(); i++ ){
		HashMap info = (HashMap)list.get(i);
		
		HSSFRow _row = sheet.createRow(i+1);
		
		for( int j=0; j<list_tab.size(); j++){
			String m = (String)list_tab.get(j);
			
			HSSFCell cell = _row.createCell(j);

			String getValue = "";
			if( info.get(m) == null ){
				getValue = "";
			} else {
				getValue = info.get(m).toString();
			}
			
			Short align = (Short)list_align.get(j);
			
			//이메일용 직무 구분
			if( "EMAIL_GUBUN".equals(m) ){
				//의뢰건등록이 있는경우는 CATEGORY_GUBUN테이블의 EMAIL_GUBUN값 보여줌
				if( "Y".equals((String)info.get("PROJECT_REG_FLAG")) ){
					cell.setCellValue(getValue);
					
				//의뢰건등록이 없는경우는 직무값에 따른 인총/마케팅 보여줌
				} else {
					String duty = (String)info.get("DUTY");
					
					if( "경영/전략/기획".equals(duty) || "마케팅/광고/홍보".equals(duty) || "영업/CS".equals(duty) || "IT/디자인".equals(duty) || "생산/제조".equals(duty) || "서비스/고객지원".equals(duty) ){
						cell.setCellValue("마케팅");
					} else if( "전문직".equals(duty) || "총무/구매".equals(duty) || "유통/물류/무역".equals(duty) || "인사/노무/교육".equals(duty) || "연구개발".equals(duty) || "법률/법무".equals(duty) || "세무/재무/회계".equals(duty) || "공무원".equals(duty) ){
						cell.setCellValue("인총");
					} else {
						cell.setCellValue("");
					}
				}
			
			//이메일용 목적 구분
			} else if( "PURPOSE_GUBUN".equals(m) ){
				//의뢰건등록이 있는경우는 CATEGORY_GUBUN테이블의 EMAIL_GUBUN값 보여줌
				if( "Y".equals((String)info.get("PROJECT_REG_FLAG")) ){
					cell.setCellValue(getValue);
					
				//의뢰건등록이 없는경우는 목적구분값 없음
				} else {
					cell.setCellValue("");
				}
				
			} else {
				cell.setCellValue(getValue);
			}
			
			// 셀 스타일 적용
			if( align == CellStyle.ALIGN_LEFT ){
				cell.setCellStyle(contentStyleLeft); 
			} else if( align == CellStyle.ALIGN_CENTER ){
				cell.setCellStyle(contentStyleCenter); 
			} else if( align == CellStyle.ALIGN_RIGHT ){
				cell.setCellStyle(contentStyleRight); 
			}
		}
	}
	
	String fileName = name;
	
	fileOut = new FileOutputStream(Properties.PATH_UPLOAD+fileName);
	excel.write(fileOut);
	
	out.clear();
	out = pageContext.pushBody();
	
	Utils.fileDown(request, response, Properties.PATH_UPLOAD+fileName, name+"_"+commonUtil.getCurrentDateMiliTime("yyyyMMddHHmmss")+".xls");

	Utils.deleteFile(Properties.PATH_UPLOAD, fileName);
%>