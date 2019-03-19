<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import = "com.lamazon.properties.Properties" %>

<script src="/js/jquery.number.min.js"></script> <!-- 금액 콤마찍기 -->

<style>
	.modal-title, .modal_sub_tit {text-align:left;}
	
	.datepicker_wrap{overflow:hidden;}
	.datepicker_area{position:relative;display:inline-block;width:45% !important;float:left;}
	.datepicker_area_block{position:relative;display:block;width:100%;}
	.datepicker_wrap .unit{width:10%;text-align:center;float:left;}
	.datepicker_area input{width:100% !important;padding-right:20px;}
	.datepicker_area img{position:absolute;top:8px;right:8px;}
	.datepicker_area_block img{position:absolute;top:15px;right:15px;}
	div.datepicker_area_block img{position:absolute;top:8px;right:8px;}
</style>
    
<section class="content-header">
	<!-- <h1>[신청서 등록]</h1> --> 
	
	<c:if test="${flag eq 'regist'}">
		<div class="left_btn_wrap">
		<button type="button" id="btn_save_all" name="btn_save_all" class="btn btn-danger btn-lg">일괄등록</button>
		</div>
	</c:if>
</section>


<script>
$(function(){
	//회원정보 팝업 버튼
	$("#btn_save_all").on('click', function(){
		var url = "/order/upload";
		openPopup(url,'apply_list',555,215,true);
	});
});
</script>

<section class="content">
	<form id="orderForm" name="orderForm" method="post" action="/order/regist">
		<input type="hidden" id="o_pk" name="o_pk" value="<c:out value="${ORDER_MASTER.O_PK}"/>"/>
		<input type="hidden" id="r_u_pk" name="r_u_pk" value="<c:out value="${ORDER_MASTER.R_U_PK}"/>"/>
	    <input type="hidden" id="flag" name="flag" value="<c:out value="${flag}"/>"/>
   		
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">기본 정보</h3>
					</div>
					
					<div class="box-body table-responsive">
						<table class="table c_table">
							<colgroup>
								<col class="col-md-1">
								<col class="col-md-2">
								<col class="col-md-1">
								<col class="col-md-2">
							</colgroup>
							<tbody>
								<tr>
									<th>주문번호</th>
									<td>
										<c:choose>
											<c:when test="${flag eq 'regist'}">
												<input type="text" id="o_number" name="o_number" value="자동생성" style="width:100%;" class="form-control" disabled />
											</c:when>
											<c:otherwise>
												<c:out value="${ORDER_MASTER.O_NUMBER}" />
											</c:otherwise>
										</c:choose>
									</td>
									
									<th>마켓구분</th>
									<td>
										<select id="o_market_gubun" name="o_market_gubun" class="form-control"
										<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
										>
											<option value="">선택</option>
											<c:forEach var="item" items="${O_MARKET_GUBUN}" varStatus="status">
												<option value="${item.CM_CODE}" <c:if test="${ORDER_MASTER.O_MARKET_GUBUN eq item.CM_CODE}">selected</c:if>><c:out value="${item.CM_NAME}"/></option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td colspan="3">
										<input type="text" id="o_title" name="o_title"  value="<c:out value="${ORDER_MASTER.O_TITLE}" />" style="width:100%;" class="form-control" maxlength="100" 
										<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
										/>
									</td>
								</tr>
								
								<c:if test="${flag ne 'regist'}">
								<tr>
									<th>진행상태</th>
									<td>
										<input type="text" value="<c:out value="${ORDER_MASTER.O_STATUS_NM}" />" class="form-control" disabled />
									</td>
									
									<th>등록일</th>
									<td>
										<input type="text" value="<c:out value="${ORDER_MASTER.O_INDATE}" />" class="form-control" disabled />
									</td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<div class="right_btn_wrap">
							<button type="button" id="btn_save" name="btn_save" class="btn btn-warning"
							<c:if test="${flag ne 'regist' and ORDER_MASTER.O_STATUS gt 1000}">disabled</c:if>
							>
							<c:choose>
								<c:when test="${flag eq 'regist'}">등 록</c:when>
								<c:otherwise>수 정</c:otherwise>
							</c:choose>
							</button>
						</div>
						
						<script>
							$(function(){
								$("#btn_save").on("click", function(){
									if( $("#o_market_gubun").val() == "" ){
										alert("마켓구분을 선택해주세요.");
										$("#o_market_gubun").focus();
										return false;
										
									} else if( $("#o_title").val() == "" ){
										alert("제목을 입력해주세요.");
										$("#o_title").focus();
										return false;
									}
									
									var point = fn_moneycomma('<%=Properties.POINT%>');
									
									var confirmMessage = "등록 하시겠습니까?";
									if( $("#flag").val() == "regist" ) confirmMessage = point + "원이 차감됩니다.\n" + confirmMessage;
									
									if( confirm(confirmMessage) ){
										var param = {
							               url : "/rest/order/regist_master/auth",
							               data : {
							            	   o_pk           : $("#o_pk").val(),
							            	   r_u_pk         : $("#r_u_pk").val(),
							            	   o_market_gubun : $("#o_market_gubun").val(),
							            	   o_title        : $("#o_title").val(),
							                   flag           : "${flag}"
							               },
							             }
							
										common_ajax(param, function fn_success(result){
							               	if( result.result == 'success' ){
												alert('저장되었습니다.');	
												location.href="/order/detail?o_pk="+result.O_PK;
												
							               	} else if( result.result = 'has_error' ){
							               		alert("에러발생 - o_pk 없음");
							               	}
										});
									}
									
								});
							});
						</script>
						
					</div>
				</div>
			</div>
		</div>		
	</form>
	
	<tiles:insertAttribute name="content" />
	
</section>