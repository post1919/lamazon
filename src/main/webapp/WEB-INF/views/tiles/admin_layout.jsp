<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/WEB-INF/views/exception/error.jsp"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>LAMAZON</title>
		<meta http-equiv="Cache-Control" content="no-cache"> 
		<meta http-equiv="Pragma" content="no-cache">
		
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	
		 <script src="/admin/components/jquery/dist/jquery.min.js"></script>
		 <!-- jquery 3버젼이하 호환성을 위해 추가 -->
		 <script src="/admin/components/jquery/dist/jquery-migrate-3.0.1.min.js"></script>
		 
		 <!-- Bootstrap 3.3.7 -->
		 <script src="/admin/components/bootstrap/dist/js/bootstrap.min.js"></script>
		 <!-- ChartJS -->
		 <script src="/admin/components/chart.js/Chart.min.js"></script>
		 <!-- FastClick -->
		 <script src="/admin/components/fastclick/lib/fastclick.js"></script>
		 <!-- AdminLTE App -->
		 <script src="/admin/dist/js/adminlte.min.js"></script>
		 <!-- AdminLTE for demo purposes -->
		 <script src="/admin/dist/js/demo.js"></script>
		 
		 <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		 <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!--// ADMIN LTE -->
	
		<!-- 기존 -->
		<!-- <script src="//code.jquery.com/jquery-1.10.2.js"></script> -->
		<!-- <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
		<script src="/js/jquery-ui.js"></script>
		
		<script src="/js/common.js"></script>
		<script src="/js/common_validation.js"></script>
		
		<script src="/js/jquery.number.min.js"></script>
		<script src="/js/jquery.maskedinput.min.js"></script>
		
		<link rel="shortcut icon" href="/static/images/favicon.ico">
		
		<!-- Reset CSS File -->
		<link rel="stylesheet" type="text/css" href="/css/reset.css" media="screen" />
		<!-- Main CSS File -->
		<link rel="stylesheet" type="text/css" href="/css/style.css" media="screen" />
		
		<link rel="stylesheet" type="text/css" href="/css/castingn.css"/>
		<link rel="stylesheet" type="text/css" href="/css/manage.css"/>
		<link rel="stylesheet" type="text/css" href="/css/popup.css">
		<!-- <link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
		<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
		
		<link rel="stylesheet" href="/css/grid.css" />
		<link rel="stylesheet" href="/css/pqgrid.min.css" />
		<!-- <link rel="stylesheet" href="/css/manage.css" /> -->
		
		<!-- ADMIN LTE -->
		<link rel="stylesheet" href="/admin/components/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="/admin/components/font-awesome/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="/admin/components/Ionicons/css/ionicons.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="/admin/dist/css/AdminLTE.min.css">
		<!-- AdminLTE Skins. Choose a skin from the css/skins
		folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="/admin/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="/admin/dist/css/custom.css">
		
		<style type="text/css">
			#container {
			width:960px;
			margin-left: auto;
			margin-right: auto;
			padding: 0;
			}
			
			.clear {
			clear: both;
			margin: 0;
			padding: 0;
			}
			
			p {
			margin: 1.6em 0;
			line-height: 1.6em;
			}
			
			h2 {
			font-size: 20px;
			line-height: 20px;
			margin: 22px 0 18px 0;
			}
		</style>
		<!-- 기존 -->
		
	</head>
	<body class="skin-blue-light sidebar-mini">
		<div style="height:100%; min-height: 100%;">
		  <div class="wrapper">
	
			<!-- Header -->
			<tiles:insertAttribute name="admin_header" />	
		    
		    <!-- Left -->
		    <tiles:insertAttribute name="admin_left" />
		
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
			
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1 id="page_title"><!-- 실적현황<small>Dashboard</small> --></h1>
					
					<ol class="breadcrumb">
						<%-- 
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li><a href="#">Charts</a></li>
						--%>
						
						<li class="active"><c:out value="${MENU_FULL_NAME.AM_FULL_NAME}"/></li>
					</ol>
				</section>
						
				<!-- Body -->
				<tiles:insertAttribute name="body" />  
			</div>
		
			<!-- Footer -->
		  	<tiles:insertAttribute name="admin_footer" />
		  
		  	<!-- Right -->
		  	<tiles:insertAttribute name="admin_right" />
		  
		   </div>
		 </div>
	</body>
</html>

<% session.removeAttribute("MESSAGE_ALERT");%>