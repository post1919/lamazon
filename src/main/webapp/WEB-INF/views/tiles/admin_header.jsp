<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import = "com.lamazon.properties.Properties" %>

<!-- Header -->
<header class="main-header">
	<!-- Logo -->
	<a href="/admin/admin_main" class="logo">
	  	<!-- mini logo for sidebar mini 50x50 pixels -->
	  	<span class="logo-mini"><b>LMZ</b></span>
	  	<!-- logo for regular state and mobile devices -->
	  	<span class="logo-lg"><b>LAMAZON</b></span>
	</a>
	
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
	  	<!-- Sidebar toggle button-->
	  	<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
	    	<span class="sr-only">Toggle navigation</span>
	    	<span class="icon-bar"></span>
	    	<span class="icon-bar"></span>
	    	<span class="icon-bar"></span>
	  	</a>
	  	<ul class="quick_menu">
	        <li><a href="/logout.cast"><i class="fa fa-power-off v1"></i> <span>LOGOUT</span></a></li>
	        <li><a href="/admin/admin_main"><i class="fa fa-home v3"></i> <span>Home</span></a></li>
	    </ul>
	  	<div class="navbar-custom-menu">
		    <ul class="nav navbar-nav">
		  		<%-- 
				<!-- 메일 -->
				<!-- Messages: style can be found in dropdown.less-->
			    <li class="dropdown messages-menu">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			          <i class="fa fa-envelope-o"></i>
			          <span class="label label-success">4</span>
			        </a>
			        <ul class="dropdown-menu">
			          <li class="header">You have 4 messages</li>
			          <li>
			            <!-- inner menu: contains the actual data -->
			            <ul class="menu">
			              <li><!-- start message -->
			                <a href="#">
			                  <div class="pull-left">
			                    <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
			                  </div>
			                  <h4>
			                    Support Team
			                    <small><i class="fa fa-clock-o"></i> 5 mins</small>
			                  </h4>
			                  <p>Why not buy a new awesome theme?</p>
			                </a>
			              </li>
			              <!-- end message -->
			            </ul>
			          </li>
			          <li class="footer"><a href="#">See All Messages</a></li>
			        </ul>
			      </li>
			      --%>
			      
			      <%-- 
			  		<!-- 알람 -->
			      <!-- Notifications: style can be found in dropdown.less -->
			      <li class="dropdown notifications-menu">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			          <i class="fa fa-bell-o"></i>
			          <span class="label label-warning">10</span>
			        </a>
			        <ul class="dropdown-menu">
			          <li class="header">You have 10 notifications</li>
			          <li>
			            <!-- inner menu: contains the actual data -->
			            <ul class="menu">
			              <li>
			                <a href="#">
			                  <i class="fa fa-users text-aqua"></i> 5 new members joined today
			                </a>
			              </li>
			            </ul>
			          </li>
			          <li class="footer"><a href="#">View all</a></li>
			        </ul>
			      </li>
			      <!-- Tasks: style can be found in dropdown.less -->
			      <li class="dropdown tasks-menu">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			          <i class="fa fa-flag-o"></i>
			          <span class="label label-danger">9</span>
			        </a>
			        <ul class="dropdown-menu">
			          <li class="header">You have 9 tasks</li>
			          <li>
			            <!-- inner menu: contains the actual data -->
			            <ul class="menu">
			              <li><!-- Task item -->
			                <a href="#">
			                  <h3>
			                    Design some buttons
			                    <small class="pull-right">20%</small>
			                  </h3>
			                  <div class="progress xs">
			                    <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
			                    aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
			                    <span class="sr-only">20% Complete</span>
			                  </div>
			                </div>
			              </a>
			            </li>
			            <!-- end task item -->
			          </ul>
			        </li>
			        <li class="footer">
			          <a href="#">View all tasks</a>
			        </li>
			      </ul>
			    </li>
			    --%>
		    
			  	<!-- 회원정보 -->
			    <!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
			      	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			        	<c:if test="${ empty USER['U_PICTURE'] }">
		                    <img src="/images/common/noimage02.png" width="158" height="158" class="user-image">
		                </c:if>
		                <c:if test="${ not empty USER['U_PICTURE'] }">
		                    <img src="<%=Properties.URL_U_PICTURE %>/<c:out value="${USER['U_PICTURE_RENAME'] }"/>" class="user-image">
		                 </c:if>
			        	<span class="hidden-xs"><c:out value="${USER['U_NAME']}"/> / <fmt:formatNumber value="${USER['U_POINT']}" pattern="#,###" />원</span>
			      	</a>
			      	<ul class="dropdown-menu" style="width:350px;">
			        	<!-- User image -->
			        	<li class="user-header" style="height:365px;padding:20px;">
			          		<c:if test="${ empty USER['U_PICTURE'] }">
			                    <img src="/images/common/noimage02.png" class="img-circle" style="height:270px;width:270px;">
			                </c:if>
			                <c:if test="${ not empty USER['U_PICTURE'] }">
			                    <img src="<%=Properties.URL_U_PICTURE %>/<c:out value="${USER['U_PICTURE_RENAME'] }"/>" class="img-circle" style="height:270px;width:270px;">
			                 </c:if>
			          		<p>
			            		<c:out value="${USER['U_NAME']}"/> - <c:out value="${USER['U_UPJONG']}"/>
			            		
			            		<fmt:parseDate value="${USER['U_REGISTER_DATE']}" var="U_REGISTER_DATE" pattern="yyyy-MM-dd"/>
			            		<small>Member since <fmt:formatDate value="${U_REGISTER_DATE}" pattern="yyyy-MM-dd"/></small>
			          		</p>
			        	</li>
			        	
			        	<%-- 
			        	<!-- Menu Body -->
			        	<li class="user-body">
			          		<div class="row">
			            		<div class="col-xs-4 text-center">
			              			<a href="#">Followers</a>
		            			</div>
			            		
			            		<div class="col-xs-4 text-center">
			              			<a href="#">Sales</a>
			            		</div>
			            			
		            			<div class="col-xs-4 text-center">
			              			<a href="#">Friends</a>
			            		</div>
			          		</div>
			          	<!-- /.row -->
			        	</li>
			        	--%>
			        	
			        	<!-- Menu Footer-->
			        	<li class="user-footer">
			          		<div class="pull-left">
			            		<a href="javascript:void(0);" id="btn_join" name="btn_join" class="btn btn-default btn-flat">회원정보</a>
			          		</div>
			          		<div class="pull-right">
			            		<a href="/logout.cast" class="btn btn-default btn-flat">로그아웃</a>
			          		</div>
		        		</li>
			      	</ul>
			    </li>
			    <!-- Control Sidebar Toggle Button -->
			    
			    <!-- 오른쪽 영역 설정버튼 -->
				<li>
					<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
		</div>
	</nav>
</header>
<!--// Header -->

<script>
$(function(){
	//회원정보 팝업 버튼
	$("#btn_join").on('click', function(){
		var url = "/login/adminLogin/user_regist?u_pk=${USER['U_PK']}&flag=detail";
		openPopup(url,'apply_list',555,825,true);
	});
});
</script>