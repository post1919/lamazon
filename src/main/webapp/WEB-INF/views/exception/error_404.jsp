<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>페이지를 찾을 수 없습니다</title>
<link rel="stylesheet" type="text/css" href="/css/tamd.css"/>
<style>
* { margin: 0; padding: 0; }
html, body { 
    height: 100%;
    text-align: center;font-family: noto; }
.error { 
    text-align: center;
    width: 700px;
    height: 300px;
    margin-top:100px;
    vertical-align: middle;
    display: inline-block; }
    
.success_txt {display: inline-block;margin-top:20px}

a {width:200px;line-height:40px;display:block;background-color:#01bbd6;color:white;decoration:none;margin:20px auto}
</style>
</head>
<body>
<div class="error">
<img src="/images/400err_ver.png" width=400/>
<span class="success_txt">
	기타 문의사항이 있으실 경우 고객센터 로 연락 부탁 드립니다.
</span>
<a href="/" class="btn_main">메인</a>
</div>
</body>
</html>