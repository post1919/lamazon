<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/WEB-INF/jsp/exception/error.jsp"%>
<%@ taglib prefix="tiles"	uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>LAMAZON</title>
<link rel="stylesheet" href="/css/general.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/js/jquery.number.min.js"></script>
<script src="/js/jquery.maskedinput.min.js"></script>
<script src="/js/common.js"></script>

<script src="/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">

<link rel="shortcut icon" href="/static/images/favicon.ico">

</head>
<body>
<tiles:insertAttribute name="body" />
</body>
</html>