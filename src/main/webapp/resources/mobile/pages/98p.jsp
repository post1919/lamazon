<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ include file="../include/top.jsp" %>
<div id="wrap" class="sub_wrap"> 
	<!-- header -->
	<div id="header">
		<%@ include file="../include/header.jsp" %>
	</div>
	<!-- //header -->
	<div class="title_wrap">
		<h2>검수요청 진행</h2>
		<p class="t_btn"><a href="javascript:history.back(-1)"><img src="../img/common/btn_right.png" alt="" style="width:21px;"></a></p>
	</div>
	<!-- container -->
	<div id="container" class="sub_con">
		<p class="f19 tc pt30"><strong class="red">[입찰건명]</strong> 에 대한 검수를 요청 합니다.</p>
		<p class="tc f14 pt30 " style=" line-height:160%;">최종 업무 완료 또는 납품이 완료 되었을 경우에만 검수 요청을 진행해 주세요.<br>
			검수 요청 후 입찰 등록자의 최종 확인이 완료되면 진행완료로 최종 변경 됩니다.</p>
		<!-- 내용 -->
		
		<div class="btn_group"> <a href="#" class="btnNormal black "><span>검수요청진행</span></a> <a href="#" class="btnNormal cancel "><span>취소</span></a></div>
		
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