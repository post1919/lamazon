<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/css/new/base.css"   />
<link rel="stylesheet" type="text/css" href="/css/new/import.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"  />

<!--jQuery dependencies-->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<!--PQ Grid files-->
<link rel="stylesheet" href="/grid-2.2.0/pqgrid.min.css" />
<script src="/grid-2.2.0/pqgrid.min.js"></script>

<!--PQ Grid Office theme-->
<link rel="stylesheet" href="/grid-2.2.0/themes/Office/pqgrid.css" />

<script type="text/javascript">
	
	var cRowIndx = 0;
	
    $(function(){
    	
    	//목록 jqGrid 호출
		list_sales();
        
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
   		    yearSuffix         : '년',
   		    changeMonth        : true,
   	        changeYear         : true,
   	        showButtonPanel    : true,
   		});
   	 
   	 	//$('#start_date').datepicker();
   	    $('#fromdate').datepicker("option", "maxDate", $("#todate").val());
   	    $('#fromdate').datepicker("option", "onClose", function ( selectedDate ) {
   	        $("#todate").datepicker( "option", "minDate", selectedDate );
   	    });
   	    
   	    //$('#apply_end_date').datepicker();
   	    $('#todate').datepicker("option", "minDate", $("#fromdate").val());
   	    $('#todate').datepicker("option", "onClose", function ( selectedDate ) {
   	        $("#fromdate").datepicker( "option", "maxDate", selectedDate );
   	    });
   	    
   	    $("#excelDown").click(function(){
   	    	var url = "/manage/excel/salesListExcelDown";
   	    	
   	    	document.location.href = url;
   	    });
   	    
   	    $("#btnRegist").click(function(){
   	    	
   			var url = '/admin/manage/commonCodePopup?cmmCodeGroup=';
   			
   			var specs = "left=10,top=0,width=900px,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=no";
   			window.open(url, name, specs);
   	    });
   	    
    }); //$(function(){})	
    
    function list_sales(){
    	
    	try {
    		$("div#grid_paging").pqGrid( "destroy" );
    	} catch(err){
    	    
    	}
    	
    	var code              = "all"; //진행상태
    	var CMM_CODE_GROUP    = $("#CMM_CODE_GROUP").val().trim();
    	var CMM_CODE_GROUP_NM = $("#CMM_CODE_GROUP_NM").val().trim();
    	
    	var colM2 = [        	
        	{ title:"코드그룹",    dataType:"string", width:"40%",  dataIndx:"CMM_CODE_GROUP"     },        	
        	{ title:"코드그룹명",  dataType:"string", width:"40%",  dataIndx:"CMM_CODE_GROUP_NM"  },      	
        	{ title:"사용여부",    dataType:"string", width:"20%",  dataIndx:"CMM_USEYN_NM"       }     	
        ];
    	
    	var dataModel2 = {
             location : "remote",
             dataType : "JSON",
             method   : "GET",
             url      : "/rest/adminManage/commonCode/list/"+code+"?CMM_CODE_GROUP="+CMM_CODE_GROUP+"&CMM_CODE_GROUP_NM="+encodeURIComponent(CMM_CODE_GROUP_NM),
             getData  : function (dataJSON) {
                        	var data = dataJSON.data;
                 			//return { curPage: dataJSON.curPage, totalRecords: dataJSON.totalRecords, data: data };
                 			return { curPage: dataJSON.page, totalRecords: dataJSON.total_count, data: data };
             			}
         };

         var grid1 = $("div#grid_paging").pqGrid({ width: '100%', height: 600,
             dataModel      : dataModel2,
             colModel       : colM2,
             //freezeCols     : 9,
             pageModel      : { type: "remote", rPP: 50, strRpp: "{0}" },
             sortable       : false,
             selectionModel : { swipe: false },
             wrap           : false, hwrap: false,
             //virtualX:false, 
             numberCell     : { resizable: true, width: 30, title: "#" },
             title          : "공통코드 현황",
             resizable      : true,
             rowClick       : function(event, ui){
				             	var rowIndx = ui.rowIndxPage
				 		    	cRowIndx = rowIndx;
				             	info_sales();
             				  }
         });
         
    } //END function list_sales()
    
    function info_sales(){
    	var DM = $("div#grid_paging").pqGrid("option", "dataModel");
    	var data = DM.data;
    	var row = data[cRowIndx];
    	var cmmCodeGroup = row['CMM_CODE_GROUP'];
    	
		var url = '/admin/manage/commonCodePopup?cmmCodeGroup='+cmmCodeGroup;
		
		var specs = "left=200px,top=200px,width=900px,height=800px;toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=no";
		window.open(url, name, specs);
    }
    
</script>    

<!-- content -->
<div id="admin_inner_wrap">
	<h3 class="s_tit">공통코드 현황</h3>
	<div class="line_box">
		<table cellspacing="0" cellpadding="0" class="clean_table " summary="기본정보 입력양식입니다.">
			<caption>기본정보입력</caption>
			<colgroup>
				<col width="5%">
				<col width="15%">
				<col width="5%">
				<col width="15%">
				<col width="5%">
				<col width="15%">
				<col width="5%">
				<col width="15%">
				<col width="5%">
				<col width="15%">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">코드그룹</th>
						<td><input type="text" id="CMM_CODE_GROUP" name="CMM_CODE_GROUP" placeholder="코드그룹" class="input" style="width:200px" onkeypress="if(event.keyCode == 13) list_sales();"/>
					</td>
				
					<th scope="row">코드그룹명</th>
					<td><input type="text" id="CMM_CODE_GROUP_NM" name="CMM_CODE_GROUP_NM" placeholder="코드그룹명"  class="input" style="width:100px" onkeypress="if(event.keyCode == 13) list_sales();"/></td>
				
					<th scope="row">&nbsp;</th>
					<td>&nbsp;
						<!-- <input type="text" id="fromdate" name="fromdate" value="" class="date_picker input" style="width:100px"/>~
						<input type="text" id="todate" name="todate" value="" class="date_picker input" style="width:100px"/> -->
					</td>
				
					<th scope="row"></th>
					<td>
						<a href="javascript:list_sales();" class="btnNormal blues" ><span>검색</span></a>
					   	<a id="btnRegist" name="btnRegist" href="javascript:void(0);" class="btnNormal green"><span>등록</span></a>
					</td>
				</tr>
				<tr>
					<th scope="row"></th>
					<td></td>
					
					<th scope="row"></th>
					<td></td>
				
					<th scope="row"></th>
					<td></td>
				
					<th scope="row"></th>
					<td></td>
				
					<th scope="row"></th>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 그리그 목록 -->
	<div id="grid_paging" style="margin:0 auto; margin-top: 15px; z-index:0;"></div>
</div>