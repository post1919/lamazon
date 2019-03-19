<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true" session="false"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>CastingN</title>
<link rel="stylesheet" href="/css/general.css">
<style>
.member_success {
	width:100% !important;
		-webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;	
}
.btn_main {
    font-size: 16px;
    display: inline-block;
    width: 175px;
    height: 50px;
    text-align: center;
    line-height: 50px;
    background-color: #01bbd6;
    color: #fff;
    margin:auto;
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/js/jquery.number.min.js"></script>
<script src="/js/jquery.maskedinput.min.js"></script>
<script src="/js/common.js"></script>
</head>
<body>
<div class="mypage_popup" style="width:100%">
    <div class="popup_header_s">
        파일 업로드 에러
    </div>
	<div class="popup_body">
		
		<div class="member_success">
            <div class="success_img"></div>
            <p><span class="castingn">
            	<c:if test="${ not empty FILE_NAME  }">
            	<c:out value="${FILE_NAME}"/> <span style="color:grey">은 <br>업로드할 수 없는 파일입니다.</span>
            	</c:if>
            	<c:if test="${ empty FILE_NAME  }">
            	파일용량은 10MB 이하만 가능합니다. 파일 업로드에 오류가 있습니다. 다시 시도해보세요.
            	
            	</c:if>
            </span></p>
            <div class="success_txt">
                기타 문의사항이 있으실 경우 고객센터로 연락 부탁 드립니다.
            </div>
        </div>
        <div class="member_success_1" style="text-align:center">
                <a href="javascript:history.back();" class="btn_main">이전페이지</a>
        </div>	
	</div>
</div> 
</body>
</html>