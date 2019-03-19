<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="mono"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AdminLTE 2 | Log in</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="/admin/components/bootstrap/dist/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/admin/components/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="/admin/components/Ionicons/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="/admin/dist/css/AdminLTE.min.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="/admin/plugins/iCheck/square/blue.css">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	  <!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="/admin/components/bootstrap/dist/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/admin/components/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="/admin/components/Ionicons/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="/admin/dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="/admin/dist/css/custom.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="/admin/plugins/iCheck/square/blue.css">
</head>
<body>

<script>
var user_id;
var user_passwd;
var id_mem;
function login() {
	
	user_id = $("input[name=user_id]").val();
	user_passwd = $("input[name=user_passwd]").val();
	var id_mem = $("checkbox[name=id_mem]").val();
	
	id_mem = $("input[name='id_mem']:checkbox:checked").val();
	
	var data = {"user_id":user_id,"user_passwd":user_passwd,"id_mem":id_mem};
	
	$('#btn_login').html('확인중...');
	$('#btn_login').attr("disabled",true);
	
	call(data,{dataType:'json',url:"/rest/join/login",call_back:function(out) {
		//로그인 성공 후 처리
		if(out['result']=="success") {
			loginSuccessAfter(out['result'], out['type'], out['c_id']);
		
		} else if(out['result']=="fail") {
			error("아이디, 비밀번호를 확인해주세요.");		
			$('#btn_login').html('LOGIN');
			$('#btn_login').attr("disabled",false);
			
		} else {
			error("오류가 발생하였습니다.");	
			$('#btn_login').html('LOGIN');
			$('#btn_login').attr("disabled",false);
		}
	}});
}

//로그인 성공 후 처리
function loginSuccessAfter(result, type, c_id){
	if( 'Y' == id_mem ){
		setCookie("id_mem", user_id, 365);
	} else {
		setCookie("id_mem", "", 365);
	}
	
	document.location.href = "/admin/admin_main";
}

$(document).ready(function() {
	$("#btn_login").click(function() {
		login();
	})
});
</script>

	<div class="hold-transition login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href=""><b>LAMAZON.CO.KR</b></a>
			</div>
			<!-- /.login-logo -->
			<div class="login-box-body">
				<p class="login-box-msg">LAMAZON 사이트에 오신걸 환영합니다.</p>

				
					<div class="form-group has-feedback">
						<input type="text" name="user_id" class="form-control" placeholder="ID" onkeypress="if(event.keyCode == 13) $('input[name=user_passwd]').focus()">
						<span class="glyphicon glyphicon-user form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" name="user_passwd" class="form-control" placeholder="Password"  onkeypress="if(event.keyCode == 13) login();">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-xs-5">
							<div class="checkbox icheck">
								<label>
									<input type="checkbox" name="id_mem"  value="Y"> Remember Me
								</label>
							</div>
						</div>
						
						<div class="col-xs-4" style="padding-right:0px;">
							<button class="btn btn-primary btn-block btn-flat" id="btn_join" name="btn_join">회원가입</button>
						</div>
						
						<div class="col-xs-3" style="padding-left:5px;">
							<button type="submit" class="btn btn-primary btn-block btn-flat" id="btn_login">LOGIN</button>
						</div>
					</div>
				

				<!-- <div class="social-auth-links text-center">
					<p>- OR -</p>
					<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
					Facebook</a>
					<a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
					Google+</a>
				</div> -->
				<!-- /.social-auth-links -->

				<!-- <a href="#">I forgot my password</a><br>
				<a href="register.html" class="text-center">Register a new membership</a>
 -->
			</div>
			<!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
	</div>
	<!-- jQuery 3 -->
	<script src="/admin/components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="/admin/components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="/admin/plugins/iCheck/icheck.min.js"></script>
	
	<script>
		$(function () {
			$('input').iCheck({
				checkboxClass: 'icheckbox_square-blue',
				radioClass: 'iradio_square-blue',
		  		increaseArea: '20%' // optional
			});
		});
		
		//회원가입 버튼
		$("#btn_join").on('click', function(){
			var url = "/login/adminLogin/user_regist";
			openPopup(url,'apply_list',555,825,true);
		});
	</script>
</body>
</html>
