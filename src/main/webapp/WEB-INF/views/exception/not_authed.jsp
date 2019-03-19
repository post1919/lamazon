<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>

<link rel="stylesheet" type="text/css" href="/css/general.css"/>

<style>
.member_success {
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

<div class="sub_container">
    <div class="sub_content">
        <div class="member_indi">
            <h4 class="member_title_t">관리자 승인이 필요합니다.</h4>
        </div>
        <div class="member_success">
            <div class="success_img"></div>
            <p><span class="castingn">요청하신 페이지는 관리자 승인이 필요한 페이지입니다.</p>
            <div class="success_txt">
                기타 문의사항이 있으실 경우 고객센터로 연락 부탁 드립니다.
            </div>
	        <div class="member_success_1" style="text-align:center">
	                <a href="/" class="btn_main">메인</a>
	        </div>
        </div>
    </div>
</div>