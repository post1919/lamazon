<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import = "com.lamazon.properties.Properties" %>

<link rel="stylesheet" type="text/css" href="/css/join/partner_cate_popup2.css">

<%-- /css/join/partner_cate_popup2.css 파일내용이다. 어드민서버에 파일을 반영해도 인식이 안되어서 내용 그냥 갖다붙임 --%>
<style>
	/* default definition */
	@import url(https://fonts.googleapis.com/earlyaccess/nanumgothic.css);
	*{margin:0; padding:0; border:0; outline:0; text-decoration:none;}
	html, body,label {
	font-family: 'Nanum Gothic','나눔고딕', '맑은고딕', sans-serif;
	margin:0; padding:0; overflow-x:hidden; color:#2b2d36;
	}
	ul,ol,li{list-style:none;}
	a{text-decoration:none; color:#2b2d36;}
	
	.popup_wrap{width:1000px; height:859px; background:#f4f4f4;margin:0; padding:0; overflow-y:auto;}
	.poptop{ box-sizing:border-box; width:100%; height:50px; color:#fff; font-size:22px; font-weight:700; background:url(/img/partner_cate_popup/ico001.gif) no-repeat 20px center #2b2d36; line-height:50px; padding-left:55px;}
	
	.pop_inner{/* padding:30px; */ text-align:center;}
	.tit_bluedot{background:url(/img/partner_cate_popup/dot_blue.gif) no-repeat 5px; color:#2b2d36; font-size:18px; font-weight:700; padding-left:20px; text-align:left;}
	.tit_reddot{background:url(/img/partner_cate_popup/dot_red.gif) no-repeat 5px; color:#2b2d36; font-size:18px; font-weight:700; padding-left:20px; text-align:left;}
	.tit_small{/* background:url(/img/partner_cate_popup/dot_blue.gif) no-repeat 20px; *//* font-weight:700; */font-size: 14px;padding-left: 20px;padding-top:10px;color:gray;line-height:13px;}
	.tit_small2{font-size:14px;padding-right:20px;padding-top:10px;color:#7b7b7b;line-height:13px;font-weight:500;}
	
	.pop_comment{color:#2b2d36;font-size:18px;font-weight:700;text-align:left;padding-bottom:10px;}
	.pop_comment_txt{color:#27c0d9; font-size:14px; font-weight:500; line-height:140%; letter-spacing:-1px;}
	
	.category01 {margin-top:20px; margin-bottom:40px; display:table; text-align:left;}
	.category01 li{float:left; width:25%;}
	.category01 li a{display:block; background:#fff; line-height:20px; max-height:20px; padding:9px 20px 9px 20px; margin:-1px 0 0 -1px; font-size:14px; border:1px solid #ddd;}
	.category01 li a .ico{display:inline-block; width:18px; height:17px; overflow:hidden; vertical-align:middle; padding-right:20px;}
	.category01 .on a{background:#56bdd7; color:#fff; font-weight:700;} 
	.category01 .on a{color:#fff; font-weight:700;} 
	.category01 .on a .ico img{margin-top:-42px;} 
	.category01 .on a label{color:#fff;} 
	
	.category02 {margin-top:20px; margin-bottom:40px; display:table; text-align:left; width: 940px;}
	.category02 li{float:left; width:25%;}
	.category02 li a{display:block; background:url(/img/partner_cate_popup/arrow.png) no-repeat 92% #fff; line-height:20px; max-height:20px; padding:9px 20px 9px 20px; margin:-1px 0 0 -1px; font-size:14px; border:1px solid #ddd;}
	.category02 .on a{background:url(/img/partner_cate_popup/arrow_on.png) no-repeat 92% #e63b42; color:#fff; font-weight:700; }
	.category02 .on a label{color:#fff;}
	
	.category03 {margin-top:20px; margin-bottom:40px; display:table; text-align:left; width: 940px;}
	.category03 li{float:left; width:25%;}
	.category03 li a{display:block; background:url(/img/partner_cate_popup/arrow.png) no-repeat 92% #fff; line-height:20px; max-height:20px; padding:9px 20px 9px 20px; margin:-1px 0 0 -1px; font-size:14px; border:1px solid #ddd;}
	.category03 .on a{background:url(/img/partner_cate_popup/arrow_on.png) no-repeat 92% #e63b42; color:#fff; font-weight:700; }
	.category03 .on a label{color:#fff;}
	
	.select_area{ width:100%; border-radius:15px; display:table; margin-bottom:40px;}
	.select_area li{float:left;}
	.select_area .title {width:10%; height:120px; box-sizing:border-box; display:inline-block; vertical-align:middle; padding:35px 0 0 0; text-align:center; background:#e63b42; color:#fff; font-size:18px; font-weight:700; line-height:120%;  border-radius:15px 0 0 15px;}
	.comp_category {display:inline-block; border-radius:5px; background:#ddd; padding:10px 10px 10px 15px;; border:1px solid #ccc; margin:010px 10px 0; box-sizing:border-box; text-align:left;}
	.comp_category img {margin-left:10px;}
	.select_area .area{width:89%; height:120px; box-sizing:border-box; height:120px; padding:5px 10px 0 0; box-sizing:border-box;   background:#fff; border-radius: 0  15px 15px 0;  border:1px solid #ddd;}
	
	.select_area .area div{overflow-y:auto; height:110px;}
	
	.btn_blue{border-radius:5px; border:solid 1px #0b9fc4; background: #56bdd7; text-align:center; color:#fff !important;  padding:15px 25px; font-size:18px; font-weight:700; letter-spacing:-1px; margin-left:20px;}
	
	.btn_grey{border-radius:5px; border:solid 1px #777777; background: #959595; text-align:center; color:#fff !important;  padding:10px 18px; font-size:18px; font-weight:700; letter-spacing:-1px; margin-left:20px;}
	
	/*20171110*/
	.popup_wrap001{width:427px; height:600px; background:#f4f4f4;margin:0; padding:0;}
	.image-editor{width:100%; text-align:center;}
	.cropit-image-preview_renewal {background-color: #f8f8f8; border: 1px solid #ddd; border-radius: 15px; margin:0 auto;width: 700px;    height: 525px; cursor: move; margin-top:20px;}
	.cropit-image-preview {background-color: #f8f8f8; border: 1px solid #ddd; border-radius: 15px; margin:0 auto;width: 200px;    height: 200px; cursor: move; margin-top:20px;}
	.cropit-image-background {opacity: .1; cursor: auto;}
	.par_img_in {height: 47px; width:100%; font-size: 14px; background: #fff !important; border: 1px solid #ddd; color:#999; display:inline-block; background:#fff; } 
	.par_img_in input {background:none; height: 47px; line-height:normal; line-height:47px; border:none;text-align:center;width:100%;}
	.par_img_up {position: relative; width:300px; margin-top:20px; margin-left:20px; overflow: hidden; padding:0px 100px 0 0;} 
	.par_img_up .btn_file_up {position:absolute; top: 0px; right: 50px;} 
	.par_img_up .fileUpload .btnUpload {position: absolute; top: 0; right: 50px; margin:0; padding:0; height:50px; font-size: 20px; cursor: pointer; opacity: 0; filter: alpha(opacity=0);}
	.btn_file_up {display:inline-block; font-size:15px; min-width:76px; height:47px; border:solid 1px #0b9fc4; background: #56bdd7; text-align:center; color:#fff !important;font-weight:700; line-height:47px; letter-spacing:-1px;} 
	
	.btn_file_up2 {display:inline-block; font-size:15px; min-width:76px; height:47px; border:solid 1px #ca282f; background: #e63b42; text-align:center; color:#fff !important;font-weight:700; line-height:47px; letter-spacing:-1px;} 
	.btn_file_up3 {display:inline-block; font-size:15px; min-width:76px; height:47px; border:solid 1px #0b9fc4; background: #56bdd7; text-align:center; color:#fff !important;font-weight:700; line-height:47px; letter-spacing:-1px;}
	.par_fo002{font-weight:300; color:#2b2d36; font-size:15px; letter-spacing:-1px; line-height:150%; margin-top:10px;}
	
	.btn_view{ /* position:absolute; */ top: 0px;  left: 324px; display:inline-block; width:76px; font-size:15px; min-width:76px; border:solid 1px #ca282f; background: #e63b42; text-align:center; color:#fff !important; font-weight:700; line-height:47px; letter-spacing:-1px;}
	
	
	.tl{text-align:left;}
	.tc{text-align:center;}
	.image-size-label{font-weight:300; color:#2b2d36; font-size:16px; letter-spacing:-1px; line-height:120%; margin-top:10px;}
	
	.span_blue {color: #484cdc;font-family:Dotum, gulim;font-size: 14px;font-weight:  700;}
	.rfp_content {padding:0px 10px 0px 53px;margin-top:0px;}
	.btn_red{border-radius:5px; border:solid 1px #ca282f; background: #e63b42; text-align:center; color:#fff !important;  padding:15px 25px; font-size:18px; font-weight:700; letter-spacing:-1px; margin-left:20px;}
	
	.btn_red_001{display:inline-block;height:25px;padding:0px 18px;border:solid 1px #ca282f;background: #e63b42;text-align:center;color:#fff !important;font-size:14px;font-weight:700;letter-spacing:-1px;line-height:25px;border-radius:5px;z-index:999;margin-right:5px;}
	
	
	
	.popup_wrap002{width:688px; height:981px; background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap003{width:688px; height:656px; background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap004{width:1000px; height:650px; background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap005{width:688px; height:941px; background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap006{width:688px; height:630px; background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap007{width:860px; /* height:2030px; */ background:#f4f4f4;margin:0; padding:0;}
	.popup_wrap008{width:555px; height:825px; background:#f4f4f4;margin:0; padding:0;}
	.pop_table001 {width:100%;}
	.pop_table001 td{padding:40px 20px 0 20px;}
	.pop_table001 .nonpd01{padding-bottom:0px;}
	.pop_table001 .nonpd02{padding-top:0px;}
	.pop_table003 {width:100%;}
	.pop_table003 td{padding:30px;}
	.pop_table003 .nonpd01{padding-bottom:0px;}
	.pop_table003 .nonpd02{padding-top:0px;}
	.input0007{width:100%; box-sizing:border-box; height: 47px; padding: 0 15px 2px; font-size: 14px; background: #fff !important; border: 1px solid #ddd; color:#999; margin-top:10px; }
	.input0007:focus {border-color: #56bdd7;outline: none;}
	.input0008{width:100%; height:100px; margin-top:10px; box-sizing:border-box; padding: 10px; font-size: 14px; background: #fff; border: 1px solid #ddd; color:#333;}
	.input0008:focus {border-color: #56bdd7; background: #fff; outline: none;}
	.input00010{width:85%; box-sizing:border-box; height: 35px; padding: 0 15px 2px; font-size: 14px; background: #fff !important; border: 1px solid #ddd; color:#999; margin-top:10px; }
	.input00010:focus {border-color: #56bdd7;outline: none;}
	.tit_bluedot span{color:#999; font-size:14px; font-weight:500; line-height:140%; letter-spacing:-1px;}
	.tit_reddot span{color:#999; font-size:14px; font-weight:500; line-height:140%; letter-spacing:-1px;}
	
	.btn_grey_s{height:45px; display:inline-block; padding:0 18px; border:solid 1px #7d7d7d; background: #959595; text-align:center; color:#fff !important;   font-size:14px; font-weight:700; letter-spacing:-1px; line-height:45px;border-radius:5px;}
	.tit_reddot{background:url(/img/partner/partner_cate_popup/dot_red.gif) no-repeat 5px; color:#2b2d36; font-size:18px; font-weight:700; padding-left:20px; text-align:left;}
	.tit_reddot span{color:#999; font-size:14px; font-weight:500; line-height:140%; letter-spacing:-1px;}
	.par_img_up {position: relative; width:92%; margin-top:10px; overflow: hidden; padding:0px;} 
	.par_img_up .btn_file_up {position:absolute; top: 0px;right: 0;border-radius:5px;} 
	.par_img_up .fileUpload .btnUpload {position: absolute; top: 0;  margin:0; padding:0; height:50px; font-size: 20px; cursor: pointer; opacity: 0; filter: alpha(opacity=0);}
	.par_com_in {height: 47px; width:100%; font-size: 14px; background: #fff !important; border: 1px solid #ddd; color:#999; display:inline-block; background:#fff; }
	.par_com_in input {background:none; height: 47px; line-height:normal; line-height:47px; border:none; }
	.par_com_up {position: relative; width:100%; margin-top:10px; overflow: hidden; padding:0px;} 
	.par_com_up .btn_file_up2 {position:absolute; top: 0px; right: 0;} 
	.par_com_up .btn_file_up3 {position:absolute; top: 0px; right: 0;} 
	.par_com_up .fileUpload .btnUpload {position: absolute; top: 0; margin:0;  width:100%; padding:0; height:50px; font-size: 20px; cursor: pointer; opacity: 0; filter: alpha(opacity=0);}
	
	
	ul.data_tabs {margin-top: -36px; float: left; height: 20px;width: 100%;}
	.par_table04 td{/* height:30px; */background:#f4f4f4;color:#2b2d36;font-weight:500;font-size:14px;padding:7px;border:1px solid #ddd;letter-spacing:-1px;}
	ul.data_tabs li a {color: #999; display: block; font-size: 14px; padding: 0 ; }
	ul.data_tabs li a:hover{color:#fff !important; font-weight:700;}
	ul.data_tabs li:hover {background: #56bdd7; border: 1px solid #0b9fc4; }	
	ul.data_tabs li.active{background: #56bdd7; border: 1px solid #0b9fc4;}
	ul.data_tabs li.active a{color:#fff !important; font-weight:700;}
	.data_tab_cont{background:#fff; margin-top:40px; border:2px solid #56bdd7; padding:10px;}
	.data_tab_cont .select01 {text-align:left;}
	.data_tab_cont .select01 li{width:17%; height:20px; text-align:left; display:inline-block; padding:10px;}
	.data_tab_cont .select01 li:hover{background:#ccf5ff; font-weight:700; color:#1d8099; }
	.data_tab_cont .select01 .on{background:#ccf5ff; font-weight:700; color:#1d8099;}
	.data_tab_cont .select02 {text-align:left; background:#f4f4f4;}
	.data_tab_cont .select02  label{padding-left:20px;}
	.data_tab_cont .select02 li{width:17%; height:20px; text-align:left; display:inline-block; padding:10px;}
	.text001{ padding-left:20px; font-size:16px; line-height:120%; color:#999; width:100%;}
	.blue_border_box{border:2px solid #56bdd7; background:#f4f4f4; padding:10px; margin-top:10px;}
	.blue_border_box li{width:12%; height:20px; text-align:left; display:inline-block; padding:5px;}
	.blue_border_box label{padding-left:10px;}
	
	.pop_table002{width:100%; border:1px solid #ddd; background:#fff; margin-top:20px; }
	.pop_table002 th{background:#f4f4f4; border-right:1px solid #ddd;}
	.pop_table002 td{padding:10px; vertical-align:middle;}
	.pop_table002 ul li{display:inline-block; width:31%; text-align:left;}
	.pop_table002 ul li label{padding-left:10px; font-size:14px; font-weight:700;}
	
	.input0009{width:100%; box-sizing:border-box; height: 30px; padding: 0 15px 2px; font-size: 14px; background: #fff !important; border: 1px solid #ddd; color:#999;}
	.input0009:focus {border-color: #56bdd7;outline: none;}
	
	
	.rfp_checkbox { margin:10px 5px 5px 0px;cursor:pointer; }
	
	/* 나이스디앤비 팝업[s] */
	.Saupnotable{margin-top:20px; width:92%; margin-left:20px;}
	.Saupnotable th{background:#848484; padding:10px; color:#fff; font-weight:500; border-right:#ddd 1px solid;}
	.Saupnotable td{background:#fff; padding:10px; color:#263626; font-weight:500; border-right:#ddd 1px solid; border-bottom:#ddd 1px solid;}
	.Saupnotable th:first-child,.Saupnotable td:first-child{border-left:#ddd 1px solid;}
	/* 페이징 */
	.br_paging {border-top:3px solid #1e1e1e; margin-top:20px;}photo_listW
	.br_paging .paging  {margin-top:0px; background:none;}
	.paging_box {position:relative; /*border-top:2px solid #505050;*/}
	.write_btn {position:absolute; right:0; top:32px; z-index:9;}
	.paging {overflow:hidden; text-align:center; font-size:0px; margin:30px 0px; width:100%;} 
	.paging img {vertical-align:top;}
	.paging a {color:#737070; vertical-align:middle; font-size:13px; display:inline-block; zoom:1; line-height:28px; *display:inline; width:28px; height:28px; border:1px solid #e2e2e2; margin-left:2px;}
	.paging a.prev {padding:0px 10px 0px 0px; border:none; height:30px;}
	.paging a.first, .paging a.prev, .paging a.next, .paging a.last {border:none; width:30px; height:30px;}
	.paging a.next {padding:0px 0px 0px 10px; border:none; height:30px;}
	.paging a.on {color:#fff; background:#53535d; border:1px solid #53535d;}
	/* 나이스디앤비 팝업[e] */
	
	.tit_bluedot { font-size:14px; }
	.par_table04 th { font-size:12px; }
	.btn_grey_s{height: 33px;display:inline-block;padding:0 18px;border:solid 1px #7d7d7d;background: #959595;text-align:center;color:#fff !important;font-size:14px;font-weight:700;letter-spacing:-1px;line-height: 33px;border-radius:5px;}
	.par_img_in {height: 30px;width:100%;font-size: 14px;background: #fff !important;border: 1px solid #ddd;color:#999;display:inline-block;background:#fff;}
	.par_img_in input {background:none;height: 30px;line-height:normal;line-height: 30px;border:none;text-align:center;width:100%;}
	.btn_file_up {display:inline-block;font-size: 13px;min-width: 72px;height: 30px;border:solid 1px #0b9fc4;background: #56bdd7;text-align:center;color:#fff !important;font-weight:700;line-height: 30px;letter-spacing:-1px;}
	.input0008{width:100%;height:100px;margin-top: 5px;box-sizing:border-box;padding: 5px;font-size: 14px;background: #fff;border: 1px solid #ddd;color:#333;}
	.input00010{width:85%;box-sizing:border-box;height: 30px;padding: 0 5px 0px;font-size: 13px;background: #fff !important;border: 1px solid #ddd;color:#999;margin-top: 5px;}
	.pop_table003 .nonpd01{padding-bottom:0px;font-size: 13px;}
	.pop_table003 td{padding: 15px 10px 0 35px;font-size: 13px;}
	.par_table04 td{padding: 0px;height: 0px;background:#f4f4f4;color:#2b2d36;font-weight:500;font-size: 13px;padding: 5px;border:1px solid #ddd;letter-spacing:-1px;}
	.par_img_up {position: relative;width:92%;margin-top: 5px;overflow: hidden;padding:0px;}
	.point_text { margin-left: 10px } 
	.par_table04 td{height:0px;background:#f4f4f4;color:#2b2d36;font-weight:500;font-size: 13px;padding:7px;border:1px solid #ddd;letter-spacing:-1px;}
</style>

<link rel="stylesheet" type="text/css" href="/static/css/partner_new.css">

<form id="frm" name="frm" action="/manage/project/apply_form_new" method="post" enctype="multipart/form-data">
	<input type="hidden" id="flag" name="flag" value="${flag}" /> <!-- 등록-regist, 수정-detail -->
	<input type="hidden" id="u_pk" name="u_pk" value="${u_pk}" />

	<!--팝업 크기 : 688px*830px-->
	<div class="popup_wrap008">
		<div class="poptop" style="font-size:15px;">회원</div>
		
		<table class="pop_table003">
			<colgroup>
				<col width="50%" />
				<col width="50%" />
			</colgorup>
			<tbody>
				<tr>
					<td class="nonpd01" style="padding-top:30px;">
						<p class="tit_bluedot">아이디</p>
						<div>
							<!-- 상세/수정 -->
							<input type="text" id="u_id" name="u_id" class="input00010" title="아이디" value="${USER_INFO['U_ID']}" maxlength="15" 
							<c:if test="${flag eq 'detail'}">readonly disabled style="background:#f1f1f1 !important;"</c:if>
							/>
						</div>
					</td>
					
					<c:if test="${USER['U_TYPE'] eq 1000}">
					<td class="nonpd01" style="padding-top:30px;">
						<p class="tit_bluedot">회원구분</p>
						<select id="u_type" name="u_type" title="회원구분" class="input00010">
							<option value="">선택</option>
							<c:forEach var="item" items="${U_TYPE}" varStatus="status">
							<option value="${item.CM_CODE}" <c:if test="${item.CM_CODE eq USER_INFO['U_TYPE'] }">selected</c:if> ><c:out value="${item.CM_NAME}" /></option>
							</c:forEach>
						</select>
					</td>
					</c:if>
				</tr>
				
				<tr>
					<td class="nonpd01" style="padding-top:30px;">
						<p class="tit_bluedot">이름</p>
						<input type="text" id="u_name" name="u_name" class="input00010" title="이름" value="${USER_INFO['U_NAME']}" maxlength="50" />
					</td>
					
					<td class="nonpd01" style="padding-top:30px;">
						<p class="tit_bluedot">회사명</p>
						<input type="text" id="u_company" name="u_company" class="input00010" title="회사명" value="${USER_INFO['U_COMPANY']}" maxlength="50" />
					</td>
				</tr>
				
				<tr>
					<td class="nonpd01">
						<p class="tit_bluedot">비밀번호</p>
						<input type="password" id="u_passwd" name="u_passwd" class="input00010" title="비밀번호" maxlength="15" />
					</td>
					<td>
						<p class="tit_bluedot">비밀번호 확인</p>
						<input type="password" id="u_passwd_confirm" name="u_passwd_confirm" class="input00010" title="비밀번호 확인" maxlength="15" />
					</td>
				</tr>
				
				<tr>
					<td class="nonpd01">
						<p class="tit_bluedot">사업자 등록번호</p>
						<input type="text" id="u_registration_number" name="u_registration_number" class="input00010" title="사업자 등록번호" value="${USER_INFO['U_REGISTRATION_NUMBER']}"  maxlength="10" onkeypress='onlyNumber()' />
					</td>
					<td>
						<p class="tit_bluedot">대표자 성명</p>
						<input type="text" id="u_founder" name="u_founder" class="input00010" title="대표자 성명" value="${USER_INFO['U_FOUNDER']}" maxlength="20" />
					</td>
				</tr>
				
				<tr>
					<td class="nonpd01">
						<p class="tit_bluedot">업태</p>
						<input type="text" id="u_uptae" name="u_uptae" class="input00010" title="업태" value="${USER_INFO['U_UPTAE']}" maxlength="50" />
					</td>
					<td>
						<p class="tit_bluedot">종목</p>
						<input type="text" id="u_upjong" name="u_upjong" class="input00010" title="종목" value="${USER_INFO['U_UPJONG']}" maxlength="50" />
					</td>
				</tr>
				
				<tr>
					<td class="nonpd01">
						<p class="tit_bluedot">전화번호</p>
						<input type="text" id="u_phone" name="u_phone" class="input00010" title="전화번호" value="${USER_INFO['U_PHONE']}" maxlength="20" onkeypress='onlyNumber()' />
					</td>
					<td>
						<p class="tit_bluedot">핸드폰번호</p>
						<input type="text" id="u_mobile" name="u_mobile" class="input00010" title="핸드폰번호" value="${USER_INFO['U_MOBILE']}" maxlength="20" onkeypress='onlyNumber()' />
					</td>
				</tr>	
				
				<tr>
					<td colspan="2">
						<p class="tit_bluedot">이메일주소</p>
						<input type="text" name="u_email" id="u_email" size="300" class="input00010" style="width:93%" value="${USER_INFO['U_EMAIL']}" maxlength="50" />
					</td>
				</tr>
				
				<tr>
					<td class="nonpd01" colspan="3">
						<p class="tit_bluedot">주소</p>
						
						<input type="text" id="user_zipcode" name="user_zipcode" title="우편번호" class="input00010" style="width:30%;" value="<c:out value="${USER_INFO['U_ZIPCODE'] }"/>" readonly />
						<a href="javascript:void(0);" id="btn_addr" class="btn_grey_s">검색</a>
						
	            		<input type="text"  id="user_address" name="user_address" value="<c:out value="${USER_INFO['U_ADDRESS'] }"/>" size="300" class="input00010" style="width:93%" maxlength="100" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<p class="tit_bluedot">사진
							<c:if test="${not empty USER_INFO['U_PICTURE_RENAME']}">
							<span>
								(<a href="<%=Properties.URL_U_PICTURE %>/${USER_INFO['U_PICTURE_RENAME']}" style="color:red;" target="_blank">
									${USER_INFO['U_PICTURE']}
								</a>)
							</span>
							</c:if>
						</p>
									
						<div class="pop_inner">
							<div class="image-editor">
								<div class="par_img_up" style="margin-left:0px;"> 
									<div class="par_img_in">
										<input type="text"   id="u_picture"        name="u_picture"        title="사진" value="${USER_INFO['U_PICTURE']}" />
										<input type="hidden" id="u_picture_rename" name="u_picture_rename" title="사진" value="${USER_INFO['U_PICTURE_RENAME']}" />
									</div> 
									<div class="fileUpload"> 
										<a href="javascript:void(0);" onclick="selectFile(''); return false;" class="btn_file_up">첨부하기</a> 
										<input type="file" id="files" name="files" title="파일찾기" class="cropit-image-input btnUpload" accept=".jpg,.jpeg,.png,.gif">
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<p class="tc" style="margin:35px 0 10px 0;">
			<c:choose>
				<c:when test="${flag eq 'detail'}">
					<a href='javascript:void(0);' id="btn_update" name="btn_update" class="btn_blue" style="font-size:15px;">저장하기</a>
				</c:when>
				<c:otherwise>
					<a href='javascript:void(0);' id="btn_save" name="btn_save" class="btn_blue" style="font-size:15px;">회원가입</a>
				</c:otherwise>
			</c:choose>
		</p>
	</div>
</form>

<!-- 주소검색 -->
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>

<!-- IE에서 PROMISE 사용위해 -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script> 

<script>
	var paramPopupname   = null;
	var paramWidth       = null;
	var paramHeight      = null;
	var paramLeft        = null;
	var paramTop         = null;
	var paramToolbar     = null;
	var paramMenubar     = null;
	var paramLocation    = null;
	var paramScrollbars  = null;
	var paramStatus      = null;
	var paramRealzable   = null;
	var paramFullscreen  = null;
	var paramChannelmode = null;
	var paramUrl         = null;

	//공통 팝업
	function fn_openPopup(){
		
		//window.open 속성들
		var popupname   = paramPopupname   == undefined ? ""    : paramPopupname;
		var width       = paramWidth       == undefined ? "300" : paramWidth; 
		var height      = paramHeight      == undefined ? "300" : paramHeight; 
		var left        = paramLeft        == undefined ? "100" : paramLeft; 
		var top         = paramTop         == undefined ? "100" : paramTop;
		var toolbar     = paramToolbar     == undefined ? "no"  : paramToolbar; 
		var menubar     = paramMenubar     == undefined ? "no"  : paramMenubar; 
		var location    = paramLocation    == undefined ? "no"  : paramLocation; 
		var scrollbars  = paramScrollbars  == undefined ? "no"  : paramScrollbars; 
		var status      = paramStatus      == undefined ? "no"  : paramStatus; 
		var realzable   = paramRealzable   == undefined ? "no"  : paramRealzable; 
		var fullscreen  = paramFullscreen  == undefined ? "no"  : paramFullscreen; 
		var channelmode = paramChannelmode == undefined ? "no"  : paramChannelmode;
		var url         = paramUrl         == undefined ? ""    : paramUrl;
		
		//var name        = encodeURI(encodeURIComponent(landingParam.name));
		
		window.open( url
				   , popupname
				   , "width="+width+"px, height="+height+"px, left="+left+"px, top="+top+"px, toolbar="+toolbar+", menubar="+menubar+", location="+location
				     + ", scrollbars="+scrollbars+", status="+status+", realzable="+realzable+", fullscreen="+fullscreen+", channelmode="+channelmode
				   );
		
		//파라미터 변수 초기화
		fn_paramvalue_clear();
		
	} //openPopup()
	
	//파라미터 변수 초기화
	function fn_paramvalue_clear(){
		paramPopupname   = null;   
		paramWidth       = null;   
		paramHeight      = null;   
		paramLeft        = null;   
		paramTop         = null;   
		paramToolbar     = null;   
		paramMenubar     = null;   
		paramLocation    = null;   
		paramScrollbars  = null;   
		paramStatus      = null;   
		paramRealzable   = null;   
		paramFullscreen  = null;   
		paramChannelmode = null;
		paramUrl         = null;
	} //fn_paramvalue_clear()
	
	function open_rfp(upk, cpk, cid, catecode, cr_pk){
		//var url = "/manage/partner/mdeval?uPk="+upk+"&cPk="+cpk+"&cId="+cid+"&tab=rfp&cate="+catecode+"&frompage=partner";
		//var url = "/partner/popup/partnerRFPPopup/"+cid+"?uPk="+upk+"&cPk="+cpk+"&cId="+cid+"&tab=rfp&cate="+catecode;
		//openPopup(url,'rfp_popup1',1050,800,"yes");
		
		<c:choose>
			<c:when test="${empty PARTNER_REFERENCE}">
				alert("상세 업무 정보는 포트폴리오 저장 후에 등록 가능합니다.");
				return false;
			</c:when>
			<c:otherwise>
				catecode = $("#cat1_depth2").val();
				
				if( catecode == "" ){
					alert("카테고리를 선택해주세요.");
					$("#cat1_depth2").focus();
					return false;
				}
				
				paramUrl       = "/partner/popup/partnerRFPPopup/"+cid+"?uPk="+upk+"&cPk="+cpk+"&cId="+cid+"&tab=rfp&cate="+catecode+"&cr_pk="+cr_pk+"&procFlag=rfp_reference";
				paramPopupname = "rfp_popup";
				paramWidth     = "875";
				paramHeight    = "720";
				paramScrollbars= "yes";
				//alertParam();
				fn_openPopup();
			</c:otherwise>
		</c:choose>
	}

	$(document).ready(function() {
		
		//주소검색
		setButtonZipcode('#user_zipcode', 'user_zipcode', 'user_address');
		
		$("#btn_addr").on('click', function(){
			$("#user_zipcode").trigger('click');
		});
		
		//숫자만 입력 셋팅
		$("input:text[numberOnly]").number(false, '');
		
		//회원가입 / 수정 버튼
		$("#btn_save, #btn_update").on('click', function(){
			<c:if test="${flag ne 'detail'}">
			if($('#u_id').val()=='') {
				alert('아이디를 입력해주세요.');
				$('#u_id').focus();
				return;
			}
			</c:if>
			
			<c:if test="${USER['U_TYPE'] eq 1000}">
			//관리자
			if( $("#u_type").val() == "" ){
				alert("회원구분을 선택해주세요.");
				$("#u_type").focus();
				return;
			}
			</c:if>
			
			if($('#u_name').val()=='') {
				alert('이름을 입력해주세요.');
				$('#u_name').focus();
				return;
			}
			
			if($('#u_company').val()=='') {
				alert('회사명을 입력해주세요.');
				$('#u_company').focus();
				return;
			}
			
			<c:if test="${empty IS_ADMIN}">
			if($('#u_passwd').val()=='') {
				alert('비밀번호를 입력해주세요.');
				$('#u_passwd').focus();
				return;
			}
			
			if($('#u_passwd_confirm').val()=='') {
				alert('비밀번호 확인을 입력해주세요.');
				$('#u_passwd_confirm').focus();
				return;
			}
			</c:if>
			
			if( $('#u_passwd').val() != $('#u_passwd_confirm').val() ) {
				alert('비밀번호와 비밀번호 확인의 값이 다릅니다.');
				$('#u_passwd').focus();
				return;
			}
			
			if($('#u_registration_number').val()=='') {
				alert('사업자번호를 입력해주세요.');
				$('#u_registration_number').focus();
				return;
			}
			
			if($('#u_founder').val()=='') {
				alert('대표자 성명을 입력해주세요.');
				$('#u_founder').focus();
				return;
			}
			
			if($('#u_uptae').val()=='') {
				alert('업태를 입력해주세요.');
				$('#u_uptae').focus();
				return;
			}
			
			if($('#u_upjong').val()=='') {
				alert('종목(업종)을 입력해주세요.');
				$('#u_upjong').focus();
				return;
			}
			
			if($('#u_phone').val()=='') {
				alert('전화번호를 입력해주세요.');
				$('#u_phone').focus();
				return;
			}
			
			if(!isNumber($('#u_phone').val())) {
				alert('전화번호는 숫자만 입력해주세요.');
				$('#u_phone').focus();
				return;
			}
			
			if($('#u_mobile').val()=='') {
				alert('핸드폰번호를 입력해주세요.');
				$('#u_mobile').focus();
				return;
			}
			
			if(!isNumber($('#u_mobile').val())) {
				alert('핸드폰번호는 숫자만 입력해주세요.');
				$('#u_mobile').focus();
				return;
			}
			
			if(!validation_user_mobile($('#u_mobile').val())) {
				alert('유효한 핸드폰번호를 입력해주세요.');
				$('#u_mobile').focus();
				return;
			}
			
			if($('#u_email').val()=='') {
				alert('이메일주소를 입력해주세요.');
				$('#u_email').focus();
				return;
			}
			
			if(!validation_user_email($('#u_email').val())) {
				alert('유효한 이메일주소를 입력해주세요.');
				$('#u_email').focus();
				return;
			}
			
			if($('#user_zipcode').val()=='' || $("#user_address").val()=='') {
				alert('주소를 입력해주세요.');
				$('#user_zipcode').focus();
				return;
			}
			
			//파일첨부여부 체크
			if( !uploadFile() ){
				return;
			}
			
			if( confirm('등록 하시겠습니까?') ){
				/*
				var param = {
	               url : "/rest/join/user_regist",
	               data : {
	                 u_id                  : $("#u_id").val(),
	                 u_type                : $("#u_type").val(),
	                 u_name                : $("#u_name").val(),
	                 u_company             : $("#u_company").val(),
	                 u_passwd              : $("#u_passwd").val(),
	                 u_registration_number : $("#u_registration_number").val(),
	                 u_founder             : $("#u_founder").val(),
	                 u_uptae               : $("#u_uptae").val(),
	                 u_upjong              : $("#u_upjong").val(),
	                 u_phone               : $("#u_phone").val(),
	                 u_mobile              : $("#u_mobile").val(),
	                 u_email               : $("#u_email").val(),
	                 user_zipcode          : $("#user_zipcode").val(),
	                 user_address          : $("#user_address").val(),
	                 flag                  : "${flag}"
	               },
	             }
				*/
	
                var url = "/rest/join/user_regist";
				
                //var form = $("#frm")[0];
                //var data = new FormData(form);
                
				var data = new FormData();
				data.append("u_id", $("#u_id").val()                  );
				data.append("u_pk", $("#u_pk").val()                  );
				data.append("u_type", $("#u_type").val()              );
				data.append("u_name", $("#u_name").val()              );
				data.append("u_company", $("#u_company").val()        );
				data.append("u_passwd", $("#u_passwd").val()          );
				data.append("u_registration_number", $("#u_registration_number").val());
				data.append("u_founder", $("#u_founder").val()        );
				data.append("u_uptae", $("#u_uptae").val()            );
				data.append("u_upjong", $("#u_upjong").val()          );
				data.append("u_phone", $("#u_phone").val()            );
				data.append("u_mobile", $("#u_mobile").val()          );
				data.append("u_email", $("#u_email").val()            );
				data.append("user_zipcode", $("#user_zipcode").val()  );
				data.append("user_address", $("#user_address").val()  );
				data.append("u_picture", $("#u_picture").val()                );
				data.append("u_picture_rename", $("#u_picture_rename").val()  );
				data.append("flag", $("#flag").val()                  );
				data.append("files", $("#files")[0].files[0]          );
				
				$.ajax({
				  url: url,
				  data: data,
				  async : true,
				  method: "POST",
				  dataType: "JSON",
				  processData : false,
				  contentType : false,
				  success: function(data, status, xhr) {
					console.log()
					if( data.result === 'success' ){
					  	item = data;
					  	
					  	if( $("#flag").val() == 'detail' ){
			   				alert('저장되었습니다.');
					  		opener.location.reload();
					  		
					  	} else {
			   				alert('가입되었습니다.\n\n로그인해주세요.');					  		
					  	}
					  	
					  	/* 
					  	<c:choose>
			   				<c:when test="${flag eq 'detail'}>">
			   				</c:when>
			   				<c:otherwise>
			   				</c:otherwise>
			   			</c:choose>
			   			*/
			   			
						window.close();
          	   	
					} else if( data.result === 'exist' ){
					  	alert('존재하는 데이터가 있습니다.');
					} else if( data.result === 'no_permission' ){
						alert('권한이 없습니다.');
					} else {
					  	alert("처리중에 문제가 있었습니다.\n\n잠시후 다시 시도해주세요.");
					}
				  },
				  error: function(xhr) {
					console.log(xhr);
				  }
				});
				
				/* common_ajax(param, function fn_success(result){
	               if( result.result == 'success' ){
	            	   <c:choose>
			   				<c:when test="${flag eq 'detail'}>">
			   				alert('저장되었습니다.');
			   				</c:when>
			   				<c:otherwise>
			   				alert('가입되었습니다.\n\n로그인해주세요.');
			   				</c:otherwise>
			   			</c:choose>
			   			
	            	   	window.close();
	               }
	             }); */
			}
		});
		
		//첨부파일 핸들링
		$("#files").change(function(e) {
			FileSelectHandler(e, "u_picture");
		});
		
	});
	
	function selectFile(idx) {
		$("#files"+idx).click();
	}

	function FileSelectHandler(e, id) {
		var files = e.target.files || e.dataTransfer.files;
		
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f, id);
		}
	}
	//업로드할 파일 리스트 구성
	function ParseFile(file, id) {
		//if (file.type.indexOf("image") == 0) {
			$("#"+id).val(file.name);
			//$("#cropit-image-preview").css("background-image",<mono:img_url_partner>file.name</mono:img_url_partner>);
		//} else {
//			alert("업로드할 수 없습니다.")
		//}
	}

	function uploadFile() {
		var files = document.frm.files.files;
		
		if(files.length) {
			//if(files.length==0) {
				//alert("파일을 선택해주세요.");
				//return false;
			//}
			
			for(var i=0;i<files.length;i++) {
				var file = files[i];
				var name = file.name;
				if((/\.(jpg|jpeg|png|gif)$/i).test(name)) {
					
				} else {
					alert(name+'은 올릴수 있는 파일이 아닙니다.');		
					return false;
				}
			}
		//} else {
			//alert("파일을 선택해주세요.");
			//return false;
		}
		
		return true;
	}
</script>