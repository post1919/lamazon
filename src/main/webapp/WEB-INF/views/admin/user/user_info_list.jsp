<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<link rel="stylesheet" type="text/css" href="/css/new/base.css" />
<link rel="stylesheet" type="text/css" href="/css/new/import.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />

<!--jQuery dependencies-->
<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>    
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->

<!--PQ Grid files-->
<link rel="stylesheet" href="/grid-2.2.0/pqgrid.min.css" />
<script src="/grid-2.2.0/pqgrid.min.js"></script>
<!--PQ Grid Office theme-->
<link rel="stylesheet" href="/grid-2.2.0/themes/Office/pqgrid.css" />

<style>
	.layer_input_text { width:100%;padding:5px;font-size:15px !important;padding-right:25px; } 
	
	.layer_input_price { poistion:relative; }
	.layer_input_price .layer_input_innertext{position:absolute;top: 13px;right: 19px;font-size:15px;}
</style>

<!-- content -->
<div id="admin_inner_wrap">
	<h3 class="s_tit">회원 현황</h3>
	<div class="line_box">
		<table cellspacing="0" cellpadding="0" class="clean_table search_form" summary="기본정보 입력양식입니다.">
			<caption>기본정보입력</caption>
			<colgroup>
				<col width="5%">
				<col width="18%">
				<col width="5%">
				<col width="13%">
				<col width="5%">
				<col width="13%">
				<col width="5%">
				<col width="10%">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">가입일자</th>
					<td>
						<div class="datepicker_wrap">
							<span class="datepicker_area">
								<input type="text" id="sFromdate" name="sFromdate" value="${fromDate}" class="date_picker input">
							</span>
							<span class="unit">~</span>	
							<span class="datepicker_area">
								<input type="text" id="sTodate"   name="sTodate"   value="${toDate}" class="date_picker input">
							</span>
						</div>
					</td>
					
					<th scope="row">아이디</th>
					<td><input type="text" id="user_id" name="user_id" placeholder="사용자 아이디" class="input" onkeypress="if(event.keyCode == 13) list_user();" /></td>

					<th scope="row">이름</th>
					<td><input type="text" id="user_name" name="user_name" placeholder="사용자 이름" class="input" onkeypress="if(event.keyCode == 13) list_user();" /></td>
					
					<th scope="row"></th>
					<td>
						
					</td>
				</tr>
				<tr>
					<th scope="row">이메일</th>
					<td><input type="text" id="user_email" name="user_email" placeholder="사용자 이메일" class="input" onkeypress="if(event.keyCode == 13) list_user();" /></td>
					
					<th scope="row">휴대폰</th>
					<td><input type="text" id="user_mobile" name="user_mobile" placeholder="사용자 휴대폰" class="input" onkeypress="if(event.keyCode == 13) list_user();" /></td>

					<th scope="row">회사명</th>
					<td>
						<input type="text" id="U_COMPANY" name="U_COMPANY" value="${U_COMPANY}" class="input" placeholder="회사명" onkeypress="if(event.keyCode == 13) list_user();" />
					</td>

					<th scope="row"></th>
					<td>
						<a href="javascript:list_user();" class="btnNormal blues"><span>검색</span></a>
						
						<!-- <a href="javascript:excel_user();" class="btnNormal green"><span>엑셀다운로드</span></a> -->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id= "grid_wrap" >
		<div class= "helper">
			<!-- <div class="content"> -->
				<!-- 목록 부분 - jqGrid -->
	      		<div id="json_grid" style="margin:0 auto; margin-top: 15px;"></div>
	      	<!-- </div> -->
		</div>     
	</div>
	
	<!-- 메세지창 부분 -->
	<div id= "popup-dialog-crud" style ="display :none;"></div>
	<div id= "popup-dialog_project_reject" style ="display :none;">
		메모 : <input type="text" class="memo" style="width:290px"/>
	</div>
</div>

<!-- 우클릭 메뉴레이어 팝업 -->
<c:if test="${ USER['U_TYPE'] eq '1000' }">
	<ul id="grid_context_menu" class="contextMenu" style ="display :none;">
		<li data-menu="cmenu_change_point" class="cm_user cm_partner cm_change_passwd"><i class="fa"></i>포인트설정</li>
		<li data-menu="cmenu_change_passwd" class="cm_user cm_partner cm_change_passwd"><i class="fa"></i>비밀번호변경</li>
		<!-- <li data-menu="cmenu_user_login" class="cm_user cm_user_login"><i class="fa"></i>고객 로그인</li> -->
		<li data-menu="cmenu_user_delete" class="cm_user cm_user_delete" target="_blank"><i class="fa"></i>회원삭제</li>	
	</ul>
</c:if>

<script>

	var cRowIndx = 0;

	$(window).load(function(){
		//숫자만 입력 셋팅
		$("input:text[onlyNumber]").number(true);
	});
	
	$(function(){
		$('.date_picker').datepicker({
		 	closeText          : '닫기',
		 	currentText        : '오늘',
		    dateFormat         : 'yy-mm-dd',
		    prevText           : '이전 달',
		    nextText           : '다음 달',
		    monthNames         : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort    : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames           : ['일','월','화','수','목','금','토'],
		    dayNamesShort      : ['일','월','화','수','목','금','토'],
		    dayNamesMin        : ['일','월','화','수','목','금','토'],
		    showMonthAfterYear : true,
		    showOn             : 'button',
		    buttonImage        : '/mobile/img/common/calendar.gif',
		    buttonImageOnly    : true,
		    //yearSuffix         : '년',
		    changeMonth        : true,
	       changeYear         : true,
	       showButtonPanel    : true,
		});
	
	   	$('#sFromdate').datepicker("option", "maxDate", $("#sTodate").val());
	   	$('#sFromdate').datepicker("option", "onClose", function ( selectedDate ) {
			$("#sTodate").datepicker( "option", "minDate", selectedDate );
	   	});
	   
	   	$('#sTodate').datepicker("option", "minDate", $("#sFromdate").val());
	   	$('#sTodate').datepicker("option", "onClose", function ( selectedDate ) {
			$("#sFromdate").datepicker( "option", "maxDate", selectedDate );
	   	});
	   
	   	//가입일자 텍스트박스 클릭시 달력 노출
	   	$("#sFromdate, #sTodate").click(function(){
			$(this).next(".ui-datepicker-trigger").click();
	   	});
	
		//목록 데이터 호출
		list_user();
		
		setUserRightClick();
		
		setDatePicker('#fromdate');
		setDatePicker('#todate');
		
		//텍스트 박스 더블클릭하면 초기화
		$(":text").dblclick(function(){
			$(this).val("");
		});
		
	});

	function setTopMenu(obj) {
		var id = $(obj).attr("id");
		
		$('.manager_top_menu span').removeClass('sel');
		$(obj).addClass('sel');
		
		var rowid = "row_"+id.replace('manager_top_menu_btn_','');
		
		$('.manager_menu .row').hide();
		$('.manager_menu .'+rowid).show();
	}
	
	var $grid = $("#json_grid");
	
	//목록
	function list_user() {
		
		try { $("div#json_grid").pqGrid( "destroy" ); } catch(err){}
		
	   	var grid_type    = "user";
	   	var user_name    = $("#user_name").val().trim();
	   	var user_email   = $("#user_email").val().trim();
	   	var user_mobile  = $("#user_mobile").val().trim();
	   	var user_id      = $("#user_id").val().trim();
	   	var sFromdate    = $("#sFromdate").val().trim(); //기간FROM
		var sTodate      = $("#sTodate").val().trim();   //기간TO
		var U_COMPANY    = $("#U_COMPANY").val().trim();   //회사명
	   	
		var grid_main_title = "회원현황";
		
	   	$grid.pqGrid({
			//  width         : "100%"
		    //, height        : 400
		      title         : grid_main_title
		    , flexHeight    : true
		    , editable      : false
		    , resizable     : true
		    , scrollModel   : {autoFit: true}
		    , collapsible   : false
		    , dataModel     : {
				          		  location : "remote"   
						        , dataType : "JSON"
						        , url      : "/rest/manage/user/list"
					   	                     + "?name="+user_name
					   	                     + "&email="+user_email
					   	                     + "&mobile="+user_mobile
					   	                     + "&userid="+user_id
					   	                     + "&sFromdate="+sFromdate
					   	                     + "&sTodate="+sTodate
					   	                     + "&U_COMPANY="+U_COMPANY
								, sorting  : "remote"
								, sortIndx : "U_PK"
								, sortDir  : "DESC"
						        , getData  : function (dataJSON) {
						        	return { curPage: dataJSON.page, totalRecords: dataJSON.total_count, data: dataJSON.data };
						        }
			                  }
		    , pageModel : {type: 'remote', rPP: 50, rPPOptions: [50,100,150,200]}
		    , colModel  : [
		    	    {title:"구분",   dataType: "string" , width: "70",  dataIndx: "U_TYPE_NM", align:"center"},
	                {title:"이름",   dataType: "string" , width: "80",  dataIndx: "U_NAME", align:"center"},
	                {title:"아이디", dataType: "string" , width: "100", dataIndx: "U_ID", align:"center"},
	                {title:"이메일", dataType: "string" , width: "140", dataIndx: "U_EMAIL", align:"center"},
	                {title:"연락처", dataType: "string" , width: "90", dataIndx: "U_PHONE", align:"center"},
	                {title:"휴대폰", dataType: "string" , width: "90",  dataIndx: "U_MOBILE", align:"center"},
					{title:"회사명", dataType: "string" , width: "150", dataIndx: "U_COMPANY", align:"center"},
					{title:"포인트", dataType: "integer" , width: "110", dataIndx: "U_POINT", align:"right",
						render: function (ui) {                         
	                        var rowData = ui.rowData["U_POINT"];
	                       	rowData = comma(rowData) + "원";
	                       	
	                      	//셀에 배경색 입힘
	    		   			ui.rowData.pq_cellcls = ui.rowData.pq_cellcls || {};
	    		   			ui.rowData.pq_cellcls[ui.dataIndx] = 'background_yellow';
	    		   			
	    		       		viewHtml = "<span>"+ ui.cellData +"</span>";
	                       	
	                        return rowData;
						}},
					{title:"가입일", dataType: "string" , width: "80",  dataIndx: "U_REGISTER_DATE", align:"center",
						render: function (ui) {                         
	                        var rowData = ui.rowData["REGISTER_DATE"];
	                       	rowData = rowData.substring(0,10);
	                        return rowData;
					
						}},
					{title:"U_PK",    dataType: "string" , dataIndx: "U_PK",    hidden:true },
					{title:"U_POINT", dataType: "string" , dataIndx: "U_POINT", hidden:true }
			]
		    , rowRightClick : function( event, ui ) {
				var rowIndx = ui.rowIndxPage;
		    	cRowIndx = rowIndx;
		    	
		    	var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var row = data[cRowIndx];
				
				$('.contextMenu li').hide();
				$('.contextMenu li.cm_all').show();
				$('.contextMenu li.cm_user').show();
				$("#grid_context_menu").show();
		    	
		    	var offset = $( document ).height() - event.pageY - $("ul.contextMenu").height()-20;
				if(offset>0) offset = 0;
		    	
		    	$("ul.contextMenu").show().css({ top: event.pageY + offset, left: event.pageX + 10 });
		    	return false;
			}
		    , cellClick : function( event, ui ) {
				var rowIndx = ui.rowIndxPage;
				cRowIndx = rowIndx;
				var dataIndx = ui.dataIndx;
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var cell = $grid.pqGrid( "getCell", { rowIndx: ui.rowIndx, colIndx: ui.colIndx } );
				var datamenu = $(".cm_partner").data("menu");
				var row = data[cRowIndx];
				
				var url = "/login/adminLogin/user_regist?u_pk="+row.U_PK+"&flag=detail";
				openPopup(url,'apply_list',555,825,true);
				
				//가망파트너 현황 더블클릭 허용
				if(datamenu == "cmenu_partner_outsourcing"){
				 $("#grid_context_menu li").click();
				}
		    	
		    }
		});
   	}

	function setUserRightClick() {
		//우클릭시
		$('ul.contextMenu').hover(
			function () {
			},  
						
			function () {
				isHovered = $("ul.contextMenu").is(":hover");
					if (isHovered == false) {
						$("ul.contextMenu").fadeOut("fast");
					}
			}
		);

		//리스트 우클릭해서 나오는 메뉴누를시
		$("#grid_context_menu li").click(function() {
			var datamenu = $(this).data("menu");
			$("ul.contextMenu").hide();
			
			//파일 검색
			if(datamenu == "cmenu_file"){
				list_file_popup();
			
			//고객 평가 등록
			} else if( datamenu == "cmenu_user" ){
				open_user_infoeval();
			
			//고객 등록 반려
			} else if(datamenu == "cmenu_reject"){
				$( "#popup-dialog-crud").html("<br><br><div style='text-align:center;'><b>반려하시겠습니까?</b></div>");
				$( "#popup-dialog-crud").dialog(
						{ title: "확인 메세지" , 
						  width:"400px",
						  resizable: false,
						  modal: true,
						  buttons: {
					  		취소: function () {
								$(this).dialog("close");},
							확인: function () {
								
								var DM = $grid.pqGrid("option", "dataModel");
								var data = DM.data;
								var row = data[cRowIndx];
								var id = row['PK'];
								
								var data = {"act":"reject","id":id};
								var url = "/rest/manage/user/certi";
								
								if( url != "" ) {
									do_post(url, data);
								}
								
								$(this).dialog("close");}
						}
						}).dialog( "open");				
				
			//포인트
			} else if( datamenu == "cmenu_change_point") {
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var row = data[cRowIndx];
				var U_POINT = row['U_POINT'];
				
				$( "#popup-dialog-crud").html(
						"<div class='layer_input_price'><input type='text' id='u_point' name='u_point' value='"+U_POINT+"' class='layer_input_text' style='text-align:right;' maxlength='15' onlyNumber /> <span class='layer_input_innertext'>원</span>"
				);
				
				//숫자만 입력 셋팅
				$("#u_point").number(true);
				
				$( "#popup-dialog-crud").dialog(
						{ title: "포인트 설정" , 
						  width:"250px",
						  resizable: false,
						  modal: true,
						  buttons: {
					  		취소: function () {
								$(this).dialog("close");},
							확인: function () {
								fn_point();
								$(this).dialog("close");
								fn_grid_reload();
								}}
						}).dialog( "open");
				
			//비밀번호변경
			} else if( datamenu == "cmenu_change_passwd") {
				$( "#popup-dialog-crud").html("<input type='password' id='change_passwd' class='layer_input_text' /> ");
				$( "#popup-dialog-crud").dialog(
						{ title: "변경할 비밀번호" , 
						  width:"400px",
						  resizable: false,
						  modal: true,
						  buttons: {
					  		취소: function () {
								$(this).dialog("close");},
							확인: function () {
								change_passwd();
								$(this).dialog("close");}}
						}).dialog( "open");
				
			//삭제
			} else if( datamenu == "cmenu_user_delete") {
				
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var row = data[cRowIndx];
				var U_PK = row['U_PK'];
				var U_COMPANY = row['U_NAME'] + " / " + row['U_COMPANY'];
				
				$( "#popup-dialog-crud").html(U_COMPANY + " 회원을 삭제하시겠습니까?");
				$( "#popup-dialog-crud").dialog(
						{ title: "확인 메세지" , 
						  width:"400px",
						  resizable: false,
						  modal: true,
						  buttons: {
					  		취소: function () {
								$(this).dialog("close");},
							확인: function () {
								//fn_user_delete();
								
								var data = { "act":"user_delete", "U_PK":U_PK };
								do_post("/rest/manage/user/delete", data);
								
								$(this).dialog("close");}}
						}).dialog( "open");
			
			//고객 로그인
			} else if ( datamenu == "cmenu_user_login") {
				open_user_login();
				
			//가망 파트너 현황
			}else if( datamenu == "cmenu_partner_outsourcing" ){
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var row = data[cRowIndx];
				var coPk = row['CO_PK'];
				var url = "/manage/partner/outsourcing-detail?coPk="+coPk+"";
				 
				window.location.href = url;
			} 
			
			var fn = window[datamenu];
			if (typeof fn === "function") fn();
		});
	}
	
	//엑셀다운로드
	function excel_user() {
		var grid_type    = "user";
	   	var user_name    = $("#user_name").val().trim();
	   	var user_email   = $("#user_email").val().trim();
	   	var user_mobile  = $("#user_mobile").val().trim();
	   	var user_id      = $("#user_id").val().trim();
	   	var sFromdate    = $("#sFromdate").val().trim(); //기간FROM
		var sTodate      = $("#sTodate").val().trim();   //기간TO
		var U_COMPANY    = $("#U_COMPANY").val().trim();   //회사명
	    	
	   	var code = $('#opt_user').val();
		var type = $('#opt_user_type').val();
	
		var url = "/manage/excel/user_list?utype="+type+"&name="+user_name+"&email="+user_email+"&mobile="+user_mobile+"&userid="+user_id+"&sFromdate="+sFromdate+"&sTodate="+sTodate+"&U_COMPANY="+U_COMPANY+"&opt_user="+opt_user;
		
		document.location.href = encodeURI(url);
	}
	
	//포인트 셋팅
	function fn_point(){
		var DM   = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row  = data[cRowIndx];
		var U_PK = row['U_PK'];
		var U_POINT = $("#u_point").val();
		
		if( U_POINT == '' ){
			alert('설정할 포인트를 입력해주세요.');
		} else {
			U_POINT = uncomma(U_POINT);
			var url = "/rest/manage/user/point_update";
			var data = {"U_PK":U_PK,"U_POINT":$("#u_point").val()};
			do_post(url, data);
		}
	}
	
	function change_passwd() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row['U_PK'];
		
		var passwd = $('#change_passwd').val();
		
		passwd = passwd.trim();
		
		if(passwd.length<5) {
			alert("비밀번호는 다섯글자 이상 입력해주세요.");
			return;
		}
		
		url = "/rest/manage/user/change_password";
		row['PK'];
		
		if(url!="") {
			var data = {"password":passwd,"id":id};
			do_post(url, data);
		}
	}
	
	function list_file_popup_user() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row["U_PK"];
		call(data,{"type":"GET",dataType:"json",url:"/rest/manage/file/user/"+id,call_back:function(json) {
			if(json['result']=='success') {
				if(json['data']) {
					var out = json['data'];
					
					var html = "";
					
					for(var i=0;i<out.length;i++) {
						var url = '/manage/file/user?id='+id+'&name='+out[i]["name"];
						html += "<a class='file' href='"+url+"'>"+out[i]["name"]+"</a><br>";
					}
					
					$( "#popup-dialog-crud").html(html);
					$( "#popup-dialog-crud").dialog( { 
						title: "인증 파일" , 
						width:"400px",
						resizable: false,
						modal: true,
						buttons: {
							확인: function () {	$(this).dialog("close"); }
						}
					}).dialog( "open");
				} else {
					alert('등록된 파일이 없습니다.');
				}
			} else {
				console.log("has error");
			}
		}});
	}
	
	function open_user_info() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row["PK"];
		var url = "/manage/user/info?pk="+id;
		openPopup(url,'user_info',900,600,true);
	}
	
	//고객 평가 등록
	function open_user_infoeval() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row["PK"];
		var url = "/manage/user/infoeval?pk="+id;
		
		window.location.href = url;
		
		//openPopup(url,'user_infoeval',1000,1000,true);
	}
	
	//고객 로그인
	function open_user_login() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row["PK"];
		login_user(id);
	}
	
	function login_user(p_id) {
		
// 		alert(p_id);
		
		window.open('about:blank','popwin');
		
		var form = document.createElement('form');

		var objs;

		objs = document.createElement('input');
		objs.setAttribute('type', 'hidden');
		objs.setAttribute('name', 'user_pk');
		objs.setAttribute('value', p_id);
		form.appendChild(objs);


		form.setAttribute('method', 'get');
		form.setAttribute('target', 'popwin');

		form.setAttribute('action', "https://www.castingn.com/inner_login/userLogin");

		document.body.appendChild(form);

		form.submit();
	}
	
	function login(p_id) {
		var user_id = p_id;
		var user_passwd = "!QAZXSW@";
		var id_mem = "N";
		
		var data = {"user_id":user_id,"user_passwd":user_passwd,"id_mem":id_mem};
		
		call(data,{dataType:'json',url:"/rest/join/login_admin",call_back:function(out) {
			
			if(out['result']=="success") {
				document.location.href = "/";
				
				//window.open("/", "new"); 
			} else if(out['result']=="fail") {
				error("아이디, 비밀번호를 확인해주세요.");		
				
			} else {
				error("오류가 발생하였습니다.");	
				
			}
		}})
	}
	
	//파일 검색
	function list_file_popup() {
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		
		var data = {};
		var DM = $grid.pqGrid("option", "dataModel");
		var data = DM.data;
		var row = data[cRowIndx];
		var id = row["PK"];
		call(data,{"type":"GET",dataType:"json",url:"/rest/manage/file/user/"+id,call_back:function(json) {
			if(json['result']=='success') {
				if(json['data']) {
					var out = json['data'];
					
					var html = "";
					
					for(var i=0;i<out.length;i++) {
						var url = '/manage/file/user?id='+id+'&name='+out[i]["name"];
						html += "<a class='file' href='"+url+"'>"+out[i]["name"]+"</a><br>";
					}
					
					$( "#popup-dialog-crud").html(html);
					$( "#popup-dialog-crud").dialog( { 
						title: "인증 파일" , 
						width:"400px",
						resizable: false,
						modal: true,
						buttons: {
							확인: function () {	$(this).dialog("close"); }
						}
					}).dialog( "open");
				} else {
					alert('등록된 파일이 없습니다.');
				}
			} else {
				console.log("has error");
			}
		}});	
	}
	
	//Ajax
	function do_post(url, data) {
		if(url!="") {
			call(data,{"type":"POST",dataType:"json",url:url,call_back:function(out) {
	    		if(out['result'] == "success") {
	    			$grid.pqGrid("refreshDataAndView" );
	    			
	    		//로그인상태가 아님
	    		} else if( out['result'] == "need_login") {
	    			error("로그인상태가 아닙니다. 로그인해주세요.");
	    			
	    		//관리자 로그인상태가 아님
	    		} else if( out['result'] == "no_permission") {
	    			error("관리자 로그인상태가 아닙니다. 관리자로 다시 로그인해주세요.");
	    			
	    		} else {
	    			error("처리가 되지않았습니다. 다시시도해주세요");			
	    		}
	    	}});
		}
	}
</script>

<%-- 
<%@ include file="/WEB-INF/views/manage/common_js.jsp" %>
<%@ include file="/WEB-INF/views/manage/model_js.jsp" %>
<%@ include file="/WEB-INF/views/manage/user_js.jsp" %> 
--%>