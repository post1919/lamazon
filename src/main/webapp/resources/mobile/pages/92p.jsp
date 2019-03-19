 <%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ include file="../include/top.jsp" %>
<div id="wrap" class="sub_wrap"> 
	<!-- header -->
	<div id="header">
		<%@ include file="../include/header.jsp" %>
	</div>
	<!-- //header -->
	<div class="title_wrap">
		<h2>입찰리스트 상세</h2>
		<p class="t_btn"><a href="javascript:history.back(-1)"><img src="../img/common/btn_right.png" alt="" style="width:21px;"></a></p>
	</div>
	<!-- container -->
	<div id="container" class="sub_con"> 
		<!-- 내용 -->
		<div class="list_box box_view mt20">
				<div class="box_top"> <span class="type" style="background:#02a5af">일반</span><a href="#" class="tit">지하철, 버스광고 및 제휴마케팅 견적 문의</a> </div>
				<ul class="list_ico">
					<li class="ico01"><strong>희망예산</strong><br>
						<span>5,000,000</span><br>
						원 이하</li>
					<li class="ico02"><strong>모집마감일</strong><br>
						<span>D-7</span><br>
						(2016.11.15)</li>
					
					<li class="ico04"><strong>진행상태</strong><br>
						<span>모집중</span></li>
					
				</ul>
			</div>
			
			<ul class="atab ">
				<li class=""><a href="#"><span>상세내용</span></a></li>
				<li class="on"><a href="#"><span>참여하기</span></a></li>
				<li class=""><a href="#"><span>관심리스트추가</span></a></li>
			</ul>
			<h3 class="s_tit">참여 업체 기본 정보</h3>
			<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="100px">
				<col width="%">
				
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">회사개요</th>
						<td>회사개요</td>
					</tr>
					<tr>
						<th scope="row">위치</th>
						<td><select name="" class="select " style="min-width:140px;">
								<option value="">시/도 선택</option>
							</select>
							<select name="" class="select " style="min-width:140px;">
								<option value="">구/군 선택</option>
							</select></td>
						
					</tr>
					<tr>
						
						<th scope="row">최근매출액</th>
						<td>최근매출액</td>
					</tr>
					<tr>
						<th scope="row">설립일</th>
						<td><select name="" class="select " style="min-width:140px;">
								<option value="">설립년도 선택</option>
							</select>
							<select name="" class="select " style="min-width:140px;">
								<option value="">월 선택</option>
							</select></td>
						
					</tr>
					<tr>
						
						<th scope="row">직원수</th>
						<td>직원수</td>
					</tr>
					<tr>
						<th scope="row">주요사업내용</th>
						<td>주요사업내용</td>
					</tr>
					<tr>
						<th scope="row">회사 특장점</th>
						<td>회사 특장점</td>
					</tr>
				</tbody>
			</table>
			<p class="tr pt10">
				<label>
					<input name="" type="checkbox" value="">
					회사 기본 정보를 입력한 내용으로 업데이트 합니다.</label>
			</p>
			
			<h3 class="s_tit">제안 상세 내용</h3>
			
			
			<table cellspacing="0" cellpadding="0" class="board-write tl" summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="100px">
				
				<col width="%">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">업체명</th>
						<td>파트너사명</td>
						
					</tr>
					<tr>
						
						<th scope="row">담당자</th>
						<td><select name="" class="select " style="min-width:90%;">
								<option value="">담당자 선택</option>
							</select></td>
					</tr>
					<tr>
						<th scope="row">제안금액</th>
						<td><input type="text" value="" name="" style="width:80%;" class="input">
							원</td>
						
					</tr>
					<tr>
						
						<th scope="row">예상소요기간</th>
						<td><input type="text" value="" name="" style="width:50%;" class="input">
							<select name="" class="select " style="width:40%;">
								<option value="">선택</option>
							</select></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td>제목</td>
					</tr>
					<tr>
						<th scope="row">제안상세내용</th>
						<td>제안 상세 내용이 노출 됩니다.</td>
					</tr>
					<tr>
						<th scope="row">파일첨부</th>
						<td><ul class="radio_group">
								<li><strong class="pr5">제안서</strong> <a href="#url" onclick="ViewlayerPop(1); return false;" class="bt_down"><span>다운로드</span></a></li>
								<li class="mt5"><strong class="pr5">견적서</strong> <a href="#" onclick="ViewlayerPop(1); return false;" class="bt_down"><span>다운로드</span></a></li>
								<li class="mt5"><strong class="pr5">기타자료</strong> <a href="#" onclick="ViewlayerPop(1); return false;" class="bt_down "><span>다운로드</span></a></li>
							</ul></td>
					</tr>
				</tbody>
			</table>
			<h3 class="s_tit">관련 레퍼런스 선택</h3>
			<div class="round_box">
				<div class="photo_listW">
					<ul class="reference">
						<li><div><span class="photo" style="background:url(../img/common/bt_photo.gif) no-repeat center center;"><a href="#url" onclick="ViewlayerPop(3); return false;"><img src="../img/common/photo.png"></a></span></div></li>
						
					</ul>
				</div>
			</div>
			
			<h3 class="s_tit">수수료율</h3>
			<p class="f20"><span class="br_box db tc" style="padding:10px">계약금액의 <strong>10%</strong></span></p>
			<ul class="bul02 mt20">
				<li>입찰건 수주 및 계약 진행 완료 시 캐스팅엔에 제공해 주실 수수료율 입니다. (예시) 100만원 계약시 10% 수수료 = 10만원</li>
<li>수수료율은 카테고리별 기본 수수료율로 우선 책정 되며, 계약 및 발주 시점에 협의를 통해 조정가능 합니다.</li>
			</ul>
			<div class="btn_group"> <a href="#" class="btnNormal black"><span>입찰 참여</span></a>  <a href="#" class="btnNormal cancel"><span>취소</span></a></div>
		<!-- //내용 --> 
	</div>
	<!-- //container --> 
	
	<!-- footer-->
	<%@ include file="../include/footer.jsp" %>
	
	<!-- //footer--> 
</div>
<!--//wrap -->
<!-- 파일선택 팝 -->
<div id="layerPop1" class="layerPop">
	<div class="layerPop_in">
		<div class="Pop_in file_pop"> 
			<!-- 내용 -->
			<div class="panel-body">
				<dl class="file_pop_list ico01">
					<dt>등록된 문서</dt>
					<dd class="txt">문서관리 메뉴에서 미리 등록해 놓은 
						문서파일을 선택하실 수 있습니다.</dd>
					<dd class="bt"><a href="#url" onclick="ViewlayerPop(2); return false;" class="btnNormal black"><span>문서검색</span></a></dd>
				</dl>
				<dl class="file_pop_list">
					<dt>파일 찾기</dt>
					<dd class="txt">PC에 저장된 파일을 선택하여 
						업로드 하실 수 있습니다.</dd>
					<dd class="bt">
						<div class="file_input">
							<input type="text" id="imgfile_route_1" readonly="">
							<label>파일선택
								<input type="file" name="filen[]" onchange="javascript:document.getElementById('imgfile_route_1').value=this.value">
							</label>
						</div>
					</dd>
				</dl>
			</div>
		</div>
		
		<!-- //내용 --> 
	</div>
	<a href="#url" class="close_bt"><img src="../img/common/close_bt.png" alt="닫기"></a> </div>
<!-- //파일선택 팝 -->

<!-- 문서검색 팝 -->
<div id="layerPop2" class="layerPop">
	<div class="layerPop_in">
	<p class="pop_title f16">등록된 문서 선택</p>
		<div class="Pop_in "> 
			<!-- 내용 -->
			<div class="panel-body">
				
				<table cellspacing="0" cellpadding="0" class="board-list " summary="기본정보 입력양식입니다.">
				<caption>
				기본정보입력
				</caption>
				<colgroup>
				<col width="10%">
				<col width="16%">
				<col width="15%">
				<col width="%">
				
				<col width="20%">
				<col width="13%">

				
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>등록일</th>
						<th>등록자</th>
						<th>파일제목</th>
						<th>파일명</th>
						<th>선택하기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>3</td>
						<td>2016.11.12</td>
						<td>홍길동</td>
						
						<td>회사 표준 소개서</td>
						<td>aaaaaa.pdf</td>
						<td><input name="" type="checkbox" value="" /></td>
					</tr>
					<tr>
						<td>3</td>
						<td>2016.11.12</td>
						<td>홍길동</td>
						
						<td>회사 표준 소개서</td>
						<td>aaaaaa.pdf</td>
						<td><input name="" type="checkbox" value="" /></td>
					</tr>
					<tr>
						<td>3</td>
						<td>2016.11.12</td>
						<td>홍길동</td>
						
						<td>회사 표준 소개서</td>
						<td>aaaaaa.pdf</td>
						<td><input name="" type="checkbox" value="" /></td>
					</tr>
				
				</tbody>
			</table>
			<div class="btn_group"> <a href="#" class="btnNormal black"><span>선택완료</span></a>  <a href="#" class="btnNormal cancel"><span>취소</span></a></div>
			
			</div>
		</div>
		
		<!-- //내용 --> 
	</div>
	<a href="#url" class="close_bt"><img src="../img/common/close_bt.png" alt="닫기"></a> </div>
<!-- //문서검색 팝 -->


<!-- 레퍼런스 선택 팝 -->
<div id="layerPop3" class="layerPop">
	<div class="layerPop_in">
	<p class="pop_title f16">등록된 문서 선택</p>
		<div class="Pop_in "> 
			<!-- 내용 -->
			<div class="panel-body">
				<div class="photo_listW">
					<ul class="reference">
						<li><div><span class="check"><input name="" type="checkbox" value="" /></span><span class="photo" style="background:url(../img/common/photo.gif) no-repeat center center"><img src="../img/common/photo.png"></span><strong class="txt">레퍼런스 제목</strong>
							<p class="txt_line"><strong class="name">고객사</strong>ooo주식회사</p>
							<p class="txt_line"><strong class="name">금액대</strong>500만원 이하</p>
						</div></li>
						<li><div><span class="check"><input name="" type="checkbox" value="" /></span><span class="photo" style="background:url(../img/common/photo.gif) no-repeat center center"><img src="../img/common/photo.png"></span><strong class="txt">레퍼런스 제목</strong>
							<p class="txt_line"><strong class="name">고객사</strong>ooo주식회사</p>
							<p class="txt_line"><strong class="name">금액대</strong>500만원 이하</p>
						</div></li>
						<li><div><span class="check"><input name="" type="checkbox" value="" /></span><span class="photo" style="background:url(../img/common/photo.gif) no-repeat center center"><img src="../img/common/photo.png"></span><strong class="txt">레퍼런스 제목</strong>
							<p class="txt_line"><strong class="name">고객사</strong>ooo주식회사</p>
							<p class="txt_line"><strong class="name">금액대</strong>500만원 이하</p>
						</div></li>
						<li><div><span class="check"><input name="" type="checkbox" value="" /></span><span class="photo" style="background:url(../img/common/photo.gif) no-repeat center center"><img src="../img/common/photo.png"></span><strong class="txt">레퍼런스 제목</strong>
							<p class="txt_line"><strong class="name">고객사</strong>ooo주식회사</p>
							<p class="txt_line"><strong class="name">금액대</strong>500만원 이하</p>
						</div></li>
					</ul>
				</div>
				
			<div class="btn_group"> <a href="#" class="btnNormal black"><span>등록하기</span></a>  <a href="#" class="btnNormal cancel"><span>취소</span></a></div>
			
			</div>
		</div>
		
		<!-- //내용 --> 
	</div>
	<a href="#url" class="close_bt"><img src="../img/common/close_bt.png" alt="닫기"></a> </div>
<!-- //레퍼런스 선택 팝 -->

</body>
</html>