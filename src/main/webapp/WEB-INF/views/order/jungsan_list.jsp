<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<link rel="stylesheet" type="text/css" href="/css/new/base.css" />
<link rel="stylesheet" type="text/css" href="/css/new/import.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />

<!--PQ Grid files-->
<link rel="stylesheet" href="/grid-2.2.0/pqgrid.min.css" />
<script src="/grid-2.2.0/pqgrid.min.js"></script>
<!--PQ Grid Office theme-->
<link rel="stylesheet" href="/grid-2.2.0/themes/Office/pqgrid.css" />

<!-- content -->
<div id="admin_inner_wrap">
	<h3 class="s_tit">정산관리</h3>
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
					<th scope="row">등록일자</th>
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
					<td><input type="text" id="user_id" name="user_id" placeholder="사용자 아이디" class="input" onkeypress="if(event.keyCode == 13) fn_list();" /></td>

					<th scope="row">이름</th>
					<td><input type="text" id="user_name" name="user_name" placeholder="사용자 이름" class="input" onkeypress="if(event.keyCode == 13) fn_list();" /></td>
					
					<th scope="row"></th>
					<td>
						
					</td>
				</tr>
				<tr>
					<th scope="row">주문번호</th>
					<td><input type="text" id="o_number" name="o_number" placeholder="주문번호" class="input" onkeypress="if(event.keyCode == 13) fn_list();" /></td>
					
					<th scope="row">휴대폰</th>
					<td><input type="text" id="user_mobile" name="user_mobile" placeholder="사용자 휴대폰" class="input" onkeypress="if(event.keyCode == 13) fn_list();" /></td>

					<th scope="row">회사명</th>
					<td>
						<input type="text" id="U_COMPANY" name="U_COMPANY" value="${U_COMPANY}" class="input" placeholder="회사명" onkeypress="if(event.keyCode == 13) fn_list();" />
					</td>
					<th scope="row"></th>
					<td>
						<a href="javascript:fn_list();" class="btnNormal blues"><span>검색</span></a>
						
						<!-- <a href="javascript:excel_user();" class="btnNormal green"><span>엑셀다운로드</span></a> -->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id= "grid_wrap" >
		<div class= "helper">
			<!-- 목록 부분 - jqGrid -->
      		<div id="json_grid" style="margin:0 auto; margin-top: 15px;"></div>
		</div>     
	</div>
	
	<!-- 레이어팝업 -->
	<div id= "popup-dialog-crud" style ="display :none;"></div>
</div>

<!-- 우클릭 메뉴레이어 팝업 -->
<c:if test="${ USER['U_TYPE'] eq '1000' }">
	<ul id="grid_context_menu" class="contextMenu" style ="display :none;">
		<li data-menu="cmenu_o_status_2000" class="cm_user cm_user_delete" target="_blank"><i class="fa"></i>입고처리</li>
		<li data-menu="cmenu_o_status_3000" class="cm_user cm_user_delete" target="_blank"><i class="fa"></i>출고처리</li>	
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
		fn_list();
		
		//마우스 우클릭
		//setUserRightClick();
		
		setDatePicker('#fromdate');
		setDatePicker('#todate');
		
		//텍스트 박스 더블클릭하면 초기화
		$(":text").dblclick(function(){
			$(this).val("");
		});
	});

	var $grid = $("#json_grid");
	
	//목록
	function fn_list() {
		
		try { $("div#json_grid").pqGrid( "destroy" ); } catch(err){}
		
	   	var grid_type    = "user";
	   	var user_name    = $("#user_name").val().trim();
	   	var o_number     = $("#o_number").val().trim();
	   	var user_mobile  = $("#user_mobile").val().trim();
	   	var user_id      = $("#user_id").val().trim();
	   	var sFromdate    = $("#sFromdate").val().trim(); //기간FROM
		var sTodate      = $("#sTodate").val().trim();   //기간TO
		var U_COMPANY    = $("#U_COMPANY").val().trim();   //회사명
	   	
		var grid_main_title = "정산관리";
		
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
						        , url      : "/rest/order/list"
					   	                     + "?name="+user_name
					   	                     + "&o_number="+o_number
					   	                     + "&mobile="+user_mobile
					   	                     + "&userid="+user_id
					   	                     + "&sFromdate="+sFromdate
					   	                     + "&sTodate="+sTodate
					   	                     + "&U_COMPANY="+U_COMPANY
								, sorting  : "remote"
								, sortIndx : "O_PK"
								, sortDir  : "DESC"
						        , getData  : function (dataJSON) {
						        	return { curPage: dataJSON.page, totalRecords: dataJSON.total_count, data: dataJSON.data };
						        }
			                  }
		    , pageModel : {type: 'remote', rPP: 2, rPPOptions: [50,100,150,200]}
		    
		    , colModel  : [
		    		{title:"순번",     dataType: "string" , width: "50",  dataIndx: "O_PK",              align:"center"},
		    		<c:if test="${IS_ADMIN}">
		    		{title:"아이디",   dataType: "string" , width: "80",  dataIndx: "U_ID",              align:"center"},
		    		{title:"이름",     dataType: "string" , width: "80",  dataIndx: "U_NAME",            align:"center"},
		    		{title:"회사명",   dataType: "string" , width: "120", dataIndx: "U_COMPANY",         align:"center"},
		    		</c:if>
		    	    {title:"주문번호", dataType: "string" , width: "120", dataIndx: "O_NUMBER",          align:"center"},
	                {title:"제목",     dataType: "string" , width: "200", dataIndx: "O_TITLE",           align:"left"  },
	                {title:"마켓구분", dataType: "string" , width: "80",  dataIndx: "O_MARKET_GUBUN_NM", align:"center"},
					{title:"진행상태", dataType: "string" , width: "80",  dataIndx: "O_STATUS_NM",       align:"center"},
					{title:"차감전 포인트", dataType: "integer" , width: "110", dataIndx: "O_POINT_BEFORE", align:"right",
						render: function (ui) {                         
	                        var rowData = typeof ui.rowData["O_POINT_BEFORE"] === 'undefined' ? 0 : ui.rowData["O_POINT_BEFORE"];
	                       	rowData = comma(rowData) + "원";
	                       	
	                      	//셀에 배경색 입힘
	    		   			ui.rowData.pq_cellcls = ui.rowData.pq_cellcls || {};
	    		   			ui.rowData.pq_cellcls[ui.dataIndx] = 'background_yellow';
	    		   			
	    		       		viewHtml = "<span>"+ ui.cellData +"</span>";
	                       	
	                        return rowData;
						}},
					{title:"차감 포인트", dataType: "integer" , width: "110", dataIndx: "O_POINT", align:"right",
						render: function (ui) {                         
							var rowData = typeof ui.rowData["O_POINT"] === 'undefined' ? 0 : ui.rowData["O_POINT"];
	                       	rowData = comma(rowData) + "원";
	                       	
	                      	//셀에 배경색 입힘
	    		   			ui.rowData.pq_cellcls = ui.rowData.pq_cellcls || {};
	    		   			ui.rowData.pq_cellcls[ui.dataIndx] = 'background_yellow';
	    		   			
	    		       		viewHtml = "<span>"+ ui.cellData +"</span>";
	                       	
	                        return rowData;
						}},
					{title:"차감후 포인트", dataType: "integer" , width: "110", dataIndx: "O_POINT_AFTER", align:"right",
						render: function (ui) {                         
							var rowData = typeof ui.rowData["O_POINT_AFTER"] === 'undefined' ? 0 : ui.rowData["O_POINT_AFTER"];
	                       	rowData = comma(rowData) + "원";
	                       	
	                      	//셀에 배경색 입힘
	    		   			ui.rowData.pq_cellcls = ui.rowData.pq_cellcls || {};
	    		   			ui.rowData.pq_cellcls[ui.dataIndx] = 'background_yellow';
	    		   			
	    		       		viewHtml = "<span>"+ ui.cellData +"</span>";
	                       	
	                        return rowData;
						}},
					{title:"등록일",   dataType: "string" , width: "90",  dataIndx: "O_INDATE",          align:"center",
						render: function (ui) {                         
	                        var rowData = ui.rowData["O_INDATE"];
	                       	rowData = rowData.substring(0,10);
	                        return rowData;
					
						}},
					{title:"R_U_PK",         dataType: "string", dataIndx: "R_U_PK",         hidden:true },
					{title:"O_MARKET_GUBUN", dataType: "string", dataIndx: "O_MARKET_GUBUN", hidden:true },
					{title:"O_STATUS",       dataType: "string", dataIndx: "O_STATUS",       hidden:true },
					{title:"O_INCODE",       dataType: "string", dataIndx: "O_INCODE",       hidden:true }
			]
		    
		    <c:if test="${IS_ADMIN}">
		    , rowRightClick : function( event, ui ) {
		    	/*
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
		    	*/
			}
		    </c:if>
		    
		    , cellClick : function( event, ui ) {
		    	var rowIndx = ui.rowIndxPage;
				cRowIndx = rowIndx;
				var dataIndx = ui.dataIndx;
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var cell = $grid.pqGrid( "getCell", { rowIndx: ui.rowIndx, colIndx: ui.colIndx } );
				var datamenu = $(".cm_partner").data("menu");
				var row = data[cRowIndx];
				
				var url = "/order/detail?o_pk="+row.O_PK;
				//openPopup(url,'apply_list',555,825,true);
				
				var openNewWindow = window.open("about:blank");
				openNewWindow.location.href = url;
				
				
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
			function (){},
			function (){
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
			
			//입고처리
			if( datamenu == "cmenu_o_status_2000" || datamenu == "cmenu_o_status_3000" ) {
				
				var DM = $grid.pqGrid("option", "dataModel");
				var data = DM.data;
				var row = data[cRowIndx];
				var o_pk = row['O_PK'];
				var u_company = row['U_COMPANY'] + " / " + row['O_NUMBER'];
				
				var proc_text = "입고";
				var o_status = 2000;
				if( datamenu == "cmenu_o_status_3000" ){
					proc_text = "출고";
					o_status = 3000;
				}
				
				$( "#popup-dialog-crud").html("<br/><span style='font-size:14px;font-weight:600;height:80px;'>"+proc_text+"처리 하시겠습니까?"+"</span>");
				$( "#popup-dialog-crud").dialog(
						{ title: u_company+"건", 
						  width:"250px",
						  resizable: false,
						  modal: true,
						  buttons: {
					  		취소: function () {
								$(this).dialog("close");},
							확인: function () {
								
								var param = {
					               url : "/rest/order/update_status",
					               data : { "o_status":o_status, "o_pk":o_pk }
								}
					
								common_ajax(param, function fn_success(result){
					               if( result.result == 'success' ){
					            	   	$grid.pqGrid("refreshDataAndView" );
						   				alert('처리되었습니다.');
					               }
					             });
								
								$(this).dialog("close");
								}
							}
						}).dialog( "open");
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
</script>