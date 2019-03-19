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
	.popup_wrap008{width:555px; height:215px; background:#f4f4f4;margin:0; padding:0;}
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

<form id="frm" name="frm" action="/manage/excelUpload/upload" method="post" enctype="multipart/form-data">

	<!--팝업 크기 : 688px*830px-->
	<div class="popup_wrap008">
		<div class="poptop" style="font-size:15px;">업로드</div>
		
		<table class="pop_table003">
			<colgroup>
				<col width="50%" />
				<col width="50%" />
			</colgorup>
			<tbody>
				<tr>
					<td colspan="2">
						<p class="tit_bluedot">사진
							<span>
								(샘플파일다운 : <a href="http://lamazon.co.kr/file/sample/excel_upload_sample.xlsx">excel_upload_sample.xlsx</a>)
							</span>
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
										<input type="file" id="files" name="files" title="파일찾기" class="cropit-image-input btnUpload" accept=".xlsx,.xls">
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<p class="tc" style="margin:35px 0 10px 0;">
			<a href='javascript:void(0);' id="btn_save" name="btn_save" class="btn_blue" style="font-size:15px;">일괄 등록하기</a>
		</p>
	</div>
</form>

<script>
	$(document).ready(function() {
		
		//회원가입 / 수정 버튼
		$("#btn_save").on('click', function(){
			
			//파일첨부여부 체크
			if( !uploadFile() ){
				return;
			}
			
			if( confirm('등록 하시겠습니까?') ){
				$("#frm").submit();
				
				/*
                var url = "/rest/join/user_regist";
				
				var data = new FormData();
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
					  		
					  	} else {
			   				alert('가입되었습니다.\n\n로그인해주세요.');					  		
					  	}
					  	
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
				*/
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
				if((/\.(xlsx|xls)$/i).test(name)) {
					
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