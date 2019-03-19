 <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ include file="../include/top.jsp" %>
<script>
function Toggle(num) {
		if($("#toggle_"+num).css('display')!="none"){
			$("#toggle_bt_"+num).html('열기 &nbsp;&nbsp;▼');
		}else {
			$("#toggle_bt_"+num).html('닫기 &nbsp;&nbsp;▲');
		}
		
		$("#toggle_"+num).slideToggle(200);
	}

</script>

<div id="wrap" class="sub_wrap"> 
	<!-- header -->
	<div id="header">
		<%@ include file="../include/header.jsp" %>
	</div>
	<!-- //header --> 
	<div class="title_wrap">
		<h2>발주요청 확인</h2>
		<p class="t_btn"><a href="javascript:history.back(-1)"><img src="../img/common/btn_right.png" alt="" style="width:21px;"></a></p>
	</div>
	<!-- container -->
	<div id="container" class="sub_con"> 
	
	
	
		<!-- 내용 -->
		
		<h3 class="ss_tit">[ 입찰건 제목이 노출 됩니다. ]</h3>
		<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
				</colgroup>
				<tbody>
				<tr>
						<th scope="row">희망예산</th>
						<td>500만원 이하</td>
						<th scope="row">마감일</th>
						<td>마감일</td>
					</tr>
					<tr>
						<th scope="row">제안금액</th>
						<td>4,200,000원</td>
						<th scope="row">제안일</th>
						<td>2016.11.31</td>
					</tr>
					
					<tr>
						<th scope="row">진행상태</th>
						<td>모집중</td>
						<th scope="row">요청상태</th>
						<td><a href="#" class="btnSmall"><span>발주확인</span></a></td>
					</tr>
				</tbody>
			</table>
		
		<h3 class="s_tit">발주 업체 기본 정보</h3>
		<p class="toggle_btn"><a href="#url" onclick="Toggle(1); return false;"><span id="toggle_bt_1">닫기 &nbsp;&nbsp;▲</span></a></p>
		<div id="toggle_1">
			<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="25%">
				<col width="%">
				
				</colgroup>
				<tbody>
				<tr>
						<th scope="row">발주업체명</th>
						<td>꿈꾸는다락방</td>
						
					</tr>
					<tr>
						<th scope="row">사업자번호</th>
						<td>111-11-11111</td>
						
					</tr>
					
					<tr>
						<th scope="row">대표전화</th>
						<td>02-2222-2222</td>
					</tr>
					<tr>
						<th scope="row">주소</th>
						<td>주소</td>
					</tr>
					<tr>
						<th scope="row">담당자명</th>
						<td>담당자명</td>
					</tr>
					<tr>
						<th scope="row">연락처</th>
						<td>연락처</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>이메일</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<h3 class="s_tit">수주 업체 기본 정보</h3>
		<p class="toggle_btn"><a href="#url" onclick="Toggle(2); return false;"><span id="toggle_bt_2">닫기 &nbsp;&nbsp;▲</span></a></p>
		<div id="toggle_2">
			<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="25%">
				<col width="%">
				
				</colgroup>
				<tbody>
				<tr>
						<th scope="row">수주업체명</th>
						<td>꿈꾸는다락방</td>
						
					</tr>
					<tr>
						<th scope="row">사업자번호</th>
						<td>111-11-11111</td>
						
					</tr>
					
					<tr>
						<th scope="row">대표전화</th>
						<td>02-2222-2222</td>
					</tr>
					<tr>
						<th scope="row">주소</th>
						<td>주소</td>
					</tr>
					<tr>
						<th scope="row">담당자명</th>
						<td>담당자명</td>
					</tr>
					<tr>
						<th scope="row">연락처</th>
						<td>연락처</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>이메일</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<h3 class="s_tit">발주내역</h3>
		<div>
			<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="33%">
				<col width="%">
				
				</colgroup>
				<tbody>
				<tr>
						<th scope="row">입찰건 정보
</th>
						<td>꿈꾸는다락방 <a href="#" class="btnSmall ml5"><span>상세보기</span></a></td>
						
					</tr>
					<tr>
						<th scope="row">계약(발주)금액
</th>
						<td>1</td>
						
					</tr>
					
					<tr>
						<th scope="row">발주요청일</th>
						<td>발주요청일</td>
					</tr>
					<tr>
						<th scope="row">종료(납품) 예정일</th>
						<td>종료(납품) 예정일</td>
					</tr>
					<tr>
						<th scope="row">대금지불 방식</th>
						<td>대금지불 방식</td>
					</tr>
					<tr>
						<th scope="row">대금지불 조건</th>
						<td>대금지불 조건</td>
					</tr>
					
				</tbody>
			</table>
		</div>

	
		<h3 class="s_tit">수수료율</h3>
		
		<p class="f20"><span class="br_box db tc" style="padding:10px">계약금액의 <strong>10%</strong></span></p>
		<p class="pt10"><label><input name="" type="checkbox" value=""> 수수료 조정 요청</label></p>
		<table cellspacing="0" cellpadding="0" class="board-write mt10" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="33%">
				<col width="%">
				
				</colgroup>
				<tbody>
				<tr>
						<th scope="row">변경%</th>
						<td><input name="" style="width:80%;" type="text">%</td>
						
					</tr>
					<tr>
						<th scope="row">변경요청사유</th>
						<td><textarea name="" cols="" rows="4" class="textarea"></textarea></td>
						
					</tr>
				
				</tbody>
			</table>
		
		<div class="btn_group"> <a href="#" class="btnNormal black db"><span>발주확인</span></a></div>
	
		<!-- //내용 --> 
	</div>
	<!-- //container --> 
	
	<!-- footer-->
	<%@ include file="../include/footer.jsp" %>
	
	<!-- //footer--> 
</div>
<!--//wrap -->

</body>
</html>