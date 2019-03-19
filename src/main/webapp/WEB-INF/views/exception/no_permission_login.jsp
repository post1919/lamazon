<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>

<link rel="stylesheet" type="text/css" href="/css/general.css"/>

<style type="text/css">
ul, ol, li { list-style:none;}
#login_alert { width:498px; height:418px;text-align:center;
				   border:1px solid #d7d7d7;}
#login_alert .al_popup { width:498px; height:418px;
								 text-align:center;
								 }
/*contents*/								
.al_popup .al_icon { text-align:center; margin:31px 0 25px;}
.al_popup h2 { font-size:23pt; color:#333;
					line-height:80%; padding-bottom:20px;}
.al_popup .al_subtxt { font-size:11.5pt; padding-bottom:40px;}
.al_popup .al_btn { margin-left:37px;}
.al_popup .al_btn > li { width:168px; float:left;
							   padding-left:30px;
							   }
</style>


<div class="sub_container">
    <div class="sub_content">
        <div class="sub_inner">
			
            <div class="find_success">
                <img src="/images/find/login_alert_icon.png" title="">
                <br />
                <br />
                <p class="mt60">로그인이 필요한 페이지 입니다.</p>
                <div class="find_success_txt mt20">
                    <p>
                        이미 캐스팅엔 회원이실 경우 로그인 부탁 드립니다.<br/>아직 캐스팅엔 회원이 아니시라면 회원가입 후 이용 부탁 드립니다.
                    </p>
                </div>
            </div>
            
            <div class="find_btn_wrap mt20" style="text-align:center; margin-bottom: 30px;margin-top:50px;">
                    <a href="/login.cast?returnURL=<c:out value="${RETURN_URL}"/>"><img src="/images/find/login_alert_btn01.png" alt="로그인하기" /></a>
                    <a href="/join/user/form"><img src="/images/find/login_alert_btn02.png" alt="회원가입하기" /></a>
            </div>
           
        </div><!--// sub_inner -->
    </div>
</div>

  