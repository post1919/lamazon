<%-- <%@ page contentType="text/html" pageEncoding="MS949" isErrorPage="true" session="false"%> --%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true" session="false"%>

<% 	String message = null;
	String next_url = null;
	if(request.getAttribute("MESSAGE_ALERT")!=null) message = (String)request.getAttribute("MESSAGE_ALERT");
	else if(request.getAttribute("MESSAGE")!=null) message = (String)request.getAttribute("MESSAGE");
	
	if(request.getAttribute("NEXT_URL")!=null) next_url = (String)request.getAttribute("NEXT_URL");
	else next_url = "index";
	%>
<script>
<%if(message!=null) {%>
	alert('<%=message%>');
<%}%>
	document.location.href = "<%=next_url%>";
</script>