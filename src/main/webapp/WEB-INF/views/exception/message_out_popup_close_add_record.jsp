<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>

<script>

<c:if test="${ not empty OPENER_SEARCH }">
	opener.fn_list_reload();
</c:if>

<c:if test="${ not empty OPENER_RELOAD }">
opener.location.reload(true);
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
	document.location.href = "<c:out value="${NEXT_URL}"/>";
</c:if>
</c:if>
</script>