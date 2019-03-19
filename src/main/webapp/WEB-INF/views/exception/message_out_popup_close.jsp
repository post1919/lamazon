<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>
<script>

<c:if test="${ not empty ACEL_TRACKING_12_NEXT_URL }">
document.location.href = "<c:out value="${ACEL_TRACKING_12_NEXT_URL}"/>";
</c:if>

//오프너의 그리드만 리로드
<c:if test="${ not empty OPENER_SEARCH }">
	opener.fn_list_reload();
</c:if>

<c:if test="${ not empty OPENER_OPENER_SEARCH }">
	opener.opener.fn_list_reload();
</c:if>

<c:if test="${ not empty OPENER_RELOAD }">
opener.location.reload(true);
</c:if>
<c:if test="${ not empty COUNSEL_RELOAD}">
opener.counselReload();
</c:if>

<c:if test="${ not empty MESSAGE }">
	alert('<c:out value="${MESSAGE}"/>');
</c:if>

<c:if test="${ not empty CALLBACK }">
	<c:if test="${ not empty CALLBACK_ID }">
		opener.<c:out value="${CALLBACK}"/>('<c:out value="${CALLBACK_ID}"/>');
	</c:if>
	
	<c:if test="${ empty CALLBACK_ID }">
		opener.<c:out value="${CALLBACK}"/>;
	</c:if>
	
	self.close();
</c:if>

<c:if test="${ empty CALLBACK }">
	<c:if test="${ empty NEXT_URL }">
		//opener.location.reload(true);
		self.close();
	</c:if>
	
	<c:if test="${ not empty NEXT_URL }">
		document.location.href = "<c:out value="${NEXT_URL}" escapeXml='false' />";
	</c:if>
	
	<c:if test="${ not empty OPRNER_NEXT_URL }">
		opener.location.href = "<c:out value="${OPRNER_NEXT_URL}" escapeXml='false' />";
	</c:if>
</c:if>
</script>