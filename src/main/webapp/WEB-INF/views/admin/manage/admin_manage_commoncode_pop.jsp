<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--jQuery dependencies-->
<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" /> -->
<link rel="stylesheet" href="/css/admin/code_mst.css" />

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->
<script src="/js/common_validation.js"></script> <!-- 공통 유효성체크 -->
<script src="/js/jquery.number.min.js"></script> <!-- 금액 콤마찍기 -->

<script type="text/javascript">
    $(function(){
    	
 	 	//코드마스터 등록
		$("#btnInsertM").click(function(){
			
			if( $("#CMM_CODE_GROUP").val() == "" ){
				alert("코드그룹을 입력해주세요.");
				return;
			}
			
			if( $("#CMM_CODE_GROUP_NM").val() == "" ){
				alert("코드그룹명을 입력해주세요.");
				validationCheck = false;
			}
			
			if( confirm("등록 하시겠습니까?") ){
				$("#rowForm").find("#procFlag").val("im");
				
				fn_setParam($("#CMM_CODE_GROUP").val(), '', $("#CMM_CODE_GROUP_NM").val(), '', '', $("#CMM_USEYN").val());
				
				$("#rowForm").submit();
			}
		});
   	 	
		//코드마스터 수정
		$("#btnUpdateM").click(function(){
			if( $("#CMM_CODE_GROUP_NM").val() == "" ){
				alert("코드그룹명을 입력해주세요.");
				return;
			}
			
			if( confirm("수정 하시겠습니까?") ){
				$("#rowForm").find("#procFlag").val("um");
				
				fn_setParam($("#CMM_CODE_GROUP").val(), '', $("#CMM_CODE_GROUP_NM").val(), '', '', $("#CMM_USEYN").val());
				
				$("#rowForm").submit();
			}
		});
    }); //$(function(){})	

    $(document).ready(function(){
		// 등록 ROW 추가
		$("#btnAddRow").click(function(){
			//추가영역 ROW를 복사
			var $newRowObj = $("#newRow").clone();
			
			//복사한 ROW를 목록 마지막에 추가
			$("#sortable").append($newRowObj.show());
		});
	});
	
	// 등록 ROW 삭제 - 추후 생성될 요소들을 미리 이벤트 리스너에 등록
	$(document).on('click', '[name=btnDelRow]', function(){
		$(this).parents("li[name=newRow]").remove();
	});
    
	//신규 ROW - 저장 버튼 클릭
	$(document).on('click', '[name=btnInsert]', function(){
		var $tb = $(this).parents("#tb");
		
		fn_update( '${cmmCodeGroup}', $tb.find("#iCM_CODE").val(), $tb.find("#iCM_NAME").val(), $tb.find("#iCM_MEMO").val(), $tb.find("#iCM_SORT").val(), $tb.find("#iCM_USEYN").val(), "i" );
	});
	
    //일괄저장
    function fn_batchSave(){
    	if( confirm("일괄저장 하시겠습니까?") ){
	    	$("#infoForm").find("#paramCCM_CODE_GROUP").val($("#CMM_CODE_GROUP").val());
	    	$("#infoForm").find("#procFlag").val("b"); //일괄저장 - b
	    	$("#infoForm").find("#CM_CODE_SIZE").val($("#infoForm").find("[name=CM_CODE]").size()); //일괄저장 - b
	    	
	    	$("#infoForm").submit();
    	}
    }                                                                                                                                                                                                                                                                                                                  
    
    //개별 저장/수정 버튼 클릭
    //flag - 'i' 등록, 'u' 수정(개별)
	function fn_update( CCM_CODE_GROUP, CM_CODE, CM_NAME, CM_MEMO, CM_SORT, CM_USEYN, flag, index ){
		
		var validationCheck = true;
		
		//수정 일경우만
		if( flag == "u" ){
			CM_NAME  = $("#CM_NAME" + index).val();
			CM_MEMO  = $("#CM_MEMO" + index).val();
			CM_SORT  = $("#CM_SORT" + index).val();
			CM_USEYN = $("#CM_USEYN" + index).val();
		}
		
		//추가 일경우만 체크
		if( flag == "i" ){
			//중복 코드 체크
			$("[name=hdCM_CODE]").each(function(){
				if( $(this).val().toUpperCase() == CM_CODE.toUpperCase() && validationCheck ){
					alert("기존 코드와 중복됩니다.\n\n새로운 코드를 입력해주세요.");
					validationCheck = false;
				}
			});
		
			if( CM_CODE == "" && validationCheck ){
				alert("코드를 입력해주세요.");
				validationCheck = false;
			}
		}
		
		if( CM_NAME == "" && validationCheck ){
			alert("코드명을 입력해주세요.");
			validationCheck = false;
		}
		
		if( CM_MEMO == "" && validationCheck ){
			alert("코드메모를 입력해주세요.");
			validationCheck = false;
		}
		
		if( CM_SORT == "" && validationCheck ){
			alert("정렬순서를 입력해주세요.");
			validationCheck = false;
		}
		
    	var procText = "수정";
    	if( flag == 'i' ){
    		procText = "추가";
    		$("#rowForm").find("#procFlag").val("i");
    	}
    	
    	if( validationCheck ){
			if( confirm(procText + " 하시겠습니까?") ){
				fn_setParam(CCM_CODE_GROUP, CM_CODE, CM_NAME, CM_MEMO, CM_SORT, CM_USEYN);
				
				$("#rowForm").submit();
			}
    	}
	}
    
    //개별 저장할때 사용될 파라미터 변수값들 설정
    function fn_setParam(CCM_CODE_GROUP, CM_CODE, CM_NAME, CM_MEMO, CM_SORT, CM_USEYN){
    	$("#rowForm").find("#paramCCM_CODE_GROUP").val(CCM_CODE_GROUP);
		$("#rowForm").find("#paramCM_CODE").val(CM_CODE);
		$("#rowForm").find("#paramCM_NAME").val(CM_NAME);
		$("#rowForm").find("#paramCM_MEMO").val(CM_MEMO);
		$("#rowForm").find("#paramCM_SORT").val(CM_SORT);
		$("#rowForm").find("#paramCM_NAME").val(CM_NAME);
		$("#rowForm").find("#paramCM_USEYN").val(CM_USEYN);
    }
</script>

<!-- jQuery Drag&Drop -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<style>
	#sortable { list-style-type: none; margin: 0; padding: 0; }
	#sortable li { font-size: 0.79em; }
	#sortable li span { position: absolute; }
</style>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#sortable").sortable();
		$("#sortable").disableSelection();
	});
</script>
<!--// jQuery Drag&Drop -->

<div class="mypage_popup" style="width:100%">
    <div class="popup_header_s" style="font-weight:bold;">
        공통코드 상세 정보
        <a href="javascript:self.close()">닫기</a>
    </div>
    
    <!-- 코드마스터 등록 -->
    <form id="masterForm" name="masterForm" action="/admin/manage/commonCodeMasterInsert" method="post">
    	<div class="popup_body">
    	
    		<div class="box_top" style="height:20px;"><span class="type_new mastertitle">코드마스터</span></div>
    	
			<input type="hidden" id="cmmPk"    name="cmmPk"    value="<c:out value="${cmmCodeGroup}" />" />
			<input type="hidden"               name="act"      value="cmmInsert" />
			
			<table style="width:100%">
				<colgroup>
					<col width="30%">
					<col width="30%">
					<col width="30%">
					<col width="*">
				</colgroup>
				
				<tr>
					<th>코드그룹</th>
					<th>코드그룹명</th>
					<th>코드그룹 사용여부</th>
					<th>&nbsp;</th>
				</tr>
				
				<tr>
					<!-- 코드그룹 -->
					<td style="font-size:0.79em;">
						<c:if test="${empty CODE_MST_M}">
						<input type="text" id="CMM_CODE_GROUP" name="CMM_CODE_GROUP" value="<c:out value="${CODE_MST_M['CMM_CODE_GROUP']}"/>" />
						</c:if>
						
						<c:if test="${!empty CODE_MST_M}">
						<c:out value="${CODE_MST_M['CMM_CODE_GROUP']}"/>
						<input type="hidden" id="CMM_CODE_GROUP" name="CMM_CODE_GROUP" value="<c:out value="${CODE_MST_M['CMM_CODE_GROUP']}"/>" />
						</c:if>
					</td>	
					
					<!-- 코드그룹명 -->
					<td><input type="text" id="CMM_CODE_GROUP_NM" name="CMM_CODE_GROUP_NM" value="<c:out value="${CODE_MST_M['CMM_CODE_GROUP_NM']}"/>" style="font-size:0.69em;" /></td>
					
					<!-- 사용여부 -->
					<td>
						<select id="CMM_USEYN" name="CMM_USEYN" style="font-size:0.69em;">
							<option value="Y" <c:if test="${CODE_MST_M['CMM_USEYN'] eq 'Y' }">selected</c:if>>사용</option>
							<option value="N" <c:if test="${CODE_MST_M['CMM_USEYN'] eq 'N' }">selected</c:if>>미사용</option>
						</select>
					</td>
					
					<!-- 공통코드 마스터 등록/수정 버튼 -->
					<td>
						<!-- 등록 버튼 -->
						<c:if test="${empty CODE_MST_M}">
							<a id="btnInsertM" name="btnInsertM" name="btnUpdateM" class="btnNormal blues" style="float:none;line-height:1.9;" onclick="fn_updatem('<c:out value="${cmmCodeGroup}"/>', '<c:out value="${item['CM_CODE']}"/>', '', '', '', '', 'u', '${status.index}');"><span style="position:relative;">등록</span></a>
						</c:if>
						
						<!-- 수정 버튼 -->
						<c:if test="${!empty CODE_MST_M}">
							<a id="btnUpdateM" name="btnUpdateM" class="btnNormal blues" style="float:none;"><span style="position:relative;line-height:1.9;">수정</span></a>
						</c:if>
					</td>
				</tr>
			</table>
			
		</div>
    </form>
    
    <!-- 처음 마스터정보 등록할 땐 상세관련 내용은 안보이게 하기위해 -->
    <c:if test="${cmmCodeGroup ne null}">
	<form id="infoForm" name="infoForm" action="/admin/manage/commonCodeUpdate" method="post">
    
    	<input type="hidden" id="paramCCM_CODE_GROUP"  name="paramCCM_CODE_GROUP" value="" /><!-- 코드마스터 키값 -->
    	<input type="hidden" id="procFlag" name="procFlag" value="b" /> <!-- 처리구분값 -->
    	<input type="hidden" id="CM_CODE_SIZE" name="CM_CODE_SIZE" value="0" /> <!-- 로우개수체크용 -->
    	
		<div class="popup_body">
		
			<input type="hidden" name="act"                 value="mdInfoSave" />
			
			<div class="box_top" style="height:20px;"><span class="type_new subtitle">코드상세</span></div>
			
			<div class="box_top" style="height:20px;"><span class="type_new noticeTitle">※ 항목들을 위아래로 마우스 드래그하여 정렬순서 설정</span></div>
			
			<a href="javascript:fn_batchSave();" class="btnNormal" ><span>일괄저장</span></a>
			<a id="btnAddRow" name="btnAddRow" href="javascript:void(0);" class="btnNormal orange"><span>추가</span></a>
			
			<table style="width:100%">
				<colgroup>
					<col width="24%">
					<col width="27%">
					<col width="27%">
					<col width="12%">
					<col width="*">
				</colgroup>
				
				<tr>
					<th>코드</th>
					<th>코드명</th>
					<th>코드메모</th>
					<th>사용여부</th>
					<th>&nbsp;</th>
				</tr>
			</table>
			
			<ul id="sortable">
				<c:forEach var="item" items="${CODE_MST}" varStatus="status">
				<li>
					<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
					
					<table id="tb" style="width:100%">
						<colgroup>
							<col width="24%">
							<col width="27%">
							<col width="27%">
							<col width="12%">
							<col width="*">
						</colgroup>
						
						<tr>
							<!-- 코드 -->
							<td>
								<input type="hidden" id="ROWFLAG" name="ROWFLAG" value="UPDATE" />
								<input type="hidden" id="CM_CODE${status.index}" name="CM_CODE" value="<c:out value="${item['CM_CODE']}"/>" />
								<c:out value="${item['CM_CODE']}"/>
							</td>
							
							<!-- 코드명 -->
							<td><input type="text" id="CM_NAME${status.index}" name="CM_NAME" value="<c:out value="${item['CM_NAME']}"/>" /></td>
							
							<!-- 코드메모 -->
							<td><input type="text" id="CM_MEMO${status.index}" name="CM_MEMO" value="<c:out value="${item['CM_MEMO']}"/>" /></td>
							
							<!-- 사용여부 -->
							<td>
								<!-- 정렬순서(임시사용) -->
								<input type="hidden" id="CM_SORT${status.index}" name="CM_SORT" value="<c:out value="${item['CM_SORT']}"/>" style="width:40px;"/>
								
								<select id="CM_USEYN${status.index}" name="CM_USEYN">
									<option value="Y" <c:if test="${item['CM_USEYN'] eq 'Y' }">selected</c:if>>사용</option>
									<option value="N" <c:if test="${item['CM_USEYN'] eq 'N' }">selected</c:if>>미사용</option>
								</select>
							</td>
					
							<!-- 버튼 -->
							<td>
								<input type="hidden" id="hdCM_CODE${status.index}" name="hdCM_CODE" value="<c:out value="${item['CM_CODE']}"/>" />
								
								<a id="btnUpdate" name="btnUpdate" class="btnNormal blues" style="float:none;" onclick="fn_update('<c:out value="${cmmCodeGroup}"/>', '<c:out value="${item['CM_CODE']}"/>', '', '', '', '', 'u', ${status.index});"><span style="position:relative;">수정</span></a>
							</td>
						</tr>
						
					</table>
				</li>
				</c:forEach>
				
				<%-- 추가 영역 --%>
				<li id="newRow" name="newRow" style="display:none;">
					<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
					
					<table id="tb" style="width:100%">
						<colgroup>
							<col width="24%">
							<col width="27%">
							<col width="27%">
							<col width="12%">
							<col width="*">
						</colgroup>
						
						<tr class="trAdd">
							<!-- 코드 -->
							<td>
								<input type="hidden" id="ROWFLAG" name="ROWFLAG" value="INSERT" />
								<input type="text" id="iCM_CODE" name="CM_CODE" style="width:140px;" />
							</td>
							
							<!-- 코드명 -->
							<td><input type="text" id="iCM_NAME" name="CM_NAME" />
							</td>
							
							<!-- 코드메모 -->
							<td><input type="text" id="iCM_MEMO" name="CM_MEMO" />
							</td>
							  
							<!-- 사용여부 -->
							<td>
								<!-- 정렬(임시) -->
								<input type="hidden" id="iCM_SORT" name="CM_SORT" value="999" />
								
								<select id="iCM_USEYN" name="CM_USEYN">
									<option value="Y">사용</option>
									<option value="N">사용안함</option>
								</select>
							</td>
							
							<!-- 버튼 -->
							<td>
								<a id="btnInsert" name="btnInsert" class="btnNormal blues" style="float:none;"><span style="position:relative;">저장</span></a>
								<a id="btnDelRow" name="btnDelRow" class="btnNormal blues" style="float:none;"><span style="position:relative;">삭제</span></a>
							</td>
							
						</tr>
					</table>
				</li>
			</ul>
			
		</div>
	</form>
	</c:if>
</div>

<form id="rowForm" name="rowForm" action="/admin/manage/commonCodeUpdate" method="post">
	<input type="hidden" id="procFlag" name="procFlag" value="u" />

	<input type="hidden" id="paramCCM_CODE_GROUP"  name="paramCCM_CODE_GROUP" value="" />
	<input type="hidden" id="paramCM_CODE"         name="paramCM_CODE"        value="" />
	<input type="hidden" id="paramCM_NAME"         name="paramCM_NAME"        value="" />
	<input type="hidden" id="paramCM_MEMO"         name="paramCM_MEMO"        value="" />
	<input type="hidden" id="paramCM_SORT"         name="paramCM_SORT"        value="" />
	<input type="hidden" id="paramCM_USEYN"        name="paramCM_USEYN"       value="" />
</form>