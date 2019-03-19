<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import = "com.lamazon.properties.Properties" %>

<script>
	$(document).ready(function(){
		var $menu = $(".sidebar-menu").find("li[class='active']");
		
		//페이지별 메뉴명 셋팅[admin_layout.jsp에 있음]
		if( "${CURRENT_URI}" == "/admin/admin_main" ){
			$("#page_title").html("<small>Dashboard</small>");
			
		} else {
			$("#page_title").html($menu.text() + "<small>LAMAZON</small>");	
		}
		
		//현재 URI에 해당하는 메뉴 활성화
		$menu.parents(".treeview").addClass("active");
	});
</script>

<!-- Left -->
<!-- Left side column. contains the logo and sidebar -->
	  <aside class="main-sidebar">
	    <!-- sidebar: style can be found in sidebar.less -->
	    <section class="sidebar">
	      <!-- Sidebar user panel -->
	      <div class="user-panel">
	        <div class="pull-left image">
	          <c:if test="${ empty USER['U_PICTURE_RENAME'] }">
                  <img src="/images/common/noimage02.png" class="img-circle" style="max-width:100px;">
              </c:if>
              <c:if test="${ not empty USER['U_PICTURE_RENAME'] }">
                  <img src="<%=Properties.URL_U_PICTURE %>/<c:out value="${USER['U_PICTURE_RENAME'] }"/>" class="img-circle" style="max-width:100px;">
               </c:if>
	        </div>
	        <div class="pull-left info" style="left:110px;">
	          <p><c:out value="${USER['U_NAME']}"/></p>
	          <a href="javascript:alert('준비중');"><i class="fa fa-circle text-success"></i> Online</a>
	        </div>
	      </div>
	      
	      <%--
	      <!--검색--> 
	      <!-- search form -->
	      <form action="#" method="get" class="sidebar-form">
	        <div class="input-group">
	          <input type="text" name="q" class="form-control" placeholder="Search...">
	          <span class="input-group-btn">
	            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
	            </button>
	          </span>
	        </div>
	      </form>
	      <!-- /.search form -->
	    --%>
	    
	    <!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">MAIN NAVIGATION</li>

          	<c:forEach var="item" items="${ADMIN_MENU}" varStatus="status_1">
          		<!-- 1차메뉴 노출권한 -->
				<c:if test="${ IS_ADMIN or USER['U_TYPE'] eq item['AM_AUTH'] or USER['U_TYPE'] eq item['AM_AUTH2'] }">
		          	<!-- 1차메뉴 -->
					<li class="<c:if test="${not empty item['CHILD1']}">treeview</c:if> <c:if test='${CURRENT_URI eq item["AM_URI"]}'>active</c:if>">
			          	<a href="${item['AM_URI']}">
			            	<i class="${item['AM_ICON']}"></i> <span><c:out value="${item['AM_NAME']}"/></span>
			            	
			            	<!-- 2차메뉴 있는경우만 -->
			            	<c:if test="${not empty item['CHILD1']}">
			            	<span class="pull-right-container">
			              		<i class="fa fa-angle-left pull-right"></i>
			            	</span>
			            	</c:if>
			          	</a>
		            	
		            	<!-- 2차메뉴 있는경우만 -->
		            	<c:if test="${not empty item['CHILD1']}">
		            	<!-- 2차메뉴 -->
		               	<ul class="treeview-menu">
		               		<c:forEach var="item2" items="${item['CHILD1']}" varStatus="status2">
								<c:if test="${ IS_ADMIN or USER['U_TYPE'] eq item2['AM_AUTH'] or USER['U_TYPE'] eq item2['AM_AUTH2'] }">
			               		<!-- 2차메뉴 노출권한 -->
			               		<li <c:if test='${CURRENT_URI eq item2["AM_URI"]}'>class="active"</c:if>><a href="${item2['AM_URI']}"><i class="${item2['AM_ICON']}"></i> <c:out value="${item2['AM_NAME']}"/></a></li>
			               		</c:if>
		                   	</c:forEach>
		               	</ul>
		               	</c:if>
					</li>
				</c:if>
			</c:forEach>
			
	        <li class="header">ETC</li>
	        <li><a href="/logout.cast"><i class="fa fa-power-off text-red"></i> <span>LOGOUT</span></a></li>
	        <li><a href="/admin/admin_main"><i class="fa fa-home text-aqua"></i> <span>Home</span></a></li>
	      </ul>
	    </section>
	    <!-- /.sidebar -->
	  </aside>
<!--// Left -->