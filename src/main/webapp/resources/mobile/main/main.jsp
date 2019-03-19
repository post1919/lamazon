<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../include/top.jsp" %>

<div id="wrap" class="login_wrap"> 
	<!-- header -->
	<div id="header">
		<%@ include file="../include/header.jsp" %>
	</div>
	<!-- //header --> 
	
	<!-- container -->
	<div class="login_page">
		<h2>캐스팅엔 로그인</h2>
		<div class="login_area ">
			<p>
				<input type="text" id="uid" class="input" placeholder="아이디" name="uid" maxlength="20">
			</p>
			<p class="text01">
				<input type="password" class="input" id="upw" placeholder="비밀번호" name="upw" maxlength="20">
			</p>
			<p><label><input name="" type="checkbox" value=""> 로그인 상태 유지</label></p>
			<p class="btn">
				<button type="submit">로그인 </button>
			</p>
		</div>
	</div>
	<!-- //container --> 
	
	<!-- footer-->
	<%@ include file="../include/footer.jsp" %>
	<!-- //footer--> 
</div>

<!--//wrap -->

</body>
</html>
