<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="/WEB-INF/views/exception/error.jsp"%>

<jsp:useBean id="now" class="java.util.Date" /> 

<%
String landingPopupUrl   = "";
String landingPopupParam = "{}"; //팝업띄울때 쓰일 파라미터들(JSON타입)
if( request.getAttribute("landingPopupUrl") != null ){
	landingPopupUrl   = request.getAttribute("landingPopupUrl").toString();
	landingPopupParam = request.getAttribute("landingPopupParam").toString();
}
%>
<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>캐스팅엔 | 최초의 기업형 나라장터</title>		
		<meta http-equiv="Cache-Control" content="no-cache"> 
		<meta http-equiv="Expires" content="0"/>
		<meta http-equiv="Pragma" content="no-cache">
		
		<meta name="description" content="대표 업무마켓 캐스팅엔에서 전문업체 굿초이스!"/>
		<meta name="keywords" content="대표 업무마켓, 국내 최초 외주 플랫폼, 외주입찰플랫폼, 3개업체 비교견적, 기업 외주 전문 플랫폼"/>

		<!-- <meta id="meta_og_thumbnail" name="thumbnail" content="https://www.castingn.com/images/logo_1024.png"/>
		<meta name="og:image" content="https://www.castingn.com/images/logo_1024.png"/> -->		
		<meta name="naver-site-verification" content="6193910c4691e721412c2e9b364ba1aec34efdf1"/>
				 
		<meta property="fb:app_id"        content="102602823629845" />
		<meta id="meta_og_type" property="og:type"          content="website" />
		<meta id="meta_og_image" property="og:image"     content="/event2/img/E-04/bg_banner_mobile.jpg" />
		<meta id="meta_og_site_name" property="og:site_name"   content="캐스팅엔 [castingn.com]"/>
		<meta id="meta_og_url" property="og:url"         content="https://www.castingn.com/">
		<meta id="meta_og_title" property="og:title"       content="캐스팅엔 | Casting N">
		<meta id="meta_og_description" property="og:description" content="국내 최초 나라장터형 외주입찰플랫폼 캐스팅엔, 3개업체 비교견적 제공"/>
		
		<%
		String patchGubun = (String) request.getAttribute("javax.servlet.forward.request_uri");
		String replacPatch = patchGubun.replaceAll("/", "");
		
		boolean viewportType = false; 
		if( replacPatch.indexOf("tender")      > -1 || replacPatch.indexOf("category")  > -1 || patchGubun.indexOf("rating") > -1 ||
			replacPatch.indexOf("projectFind") > -1 || replacPatch.indexOf("promotion") > -1 || patchGubun.indexOf("/my/request") > -1 ||
			
			
			patchGubun.indexOf("/join/user/foddrm") > -1 || patchGubun.indexOf("/join/user/form") > -1 || patchGubun.indexOf("/join/partner/formnew") > -1 ||
			
			patchGubun.indexOf("project") > -1 || patchGubun.indexOf("shoppingTori") > -1 || patchGubun.indexOf("/login.cast") > -1 || patchGubun.indexOf("event") > -1 ){
	
			viewportType = true;
		}
		
		if(viewportType){
		%>	
		<meta name="viewport" content="user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,width=device-width">
		<link rel="stylesheet" href="/css/new/btn.css?ver=1">
		<link rel="stylesheet" href="/css/new/base.css?ver=1">		
		<%	
		} else {
		%>
			<!-- <meta name="viewport" content="width=1020, maximum-scale=1.0"> -->
			<meta name="viewport" content="user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,width=device-width">
		<%	
		}
		%>
		<!-- 20180425 기존 old css 추가 -->
		<link rel="stylesheet" href="/css/new/layout.css?ver=0.2" type="text/css">
		<link rel="stylesheet" href="/css/new/bbs.css" type="text/css">		
		<link rel="stylesheet" href="/static/css/font.css" type="text/css">
		<link rel="stylesheet" href="/static/xeicon.min.css" type="text/css">
		<link rel="stylesheet" href="/static/css/style.css?ver=0.5" type="text/css">
		<link rel="stylesheet" href="/static/css/layout.css?ver=0.5" type="text/css">
		<link rel="stylesheet" href="/static/css/contents.css?ver=0.5" type="text/css">
		<link rel="stylesheet" href="/static/css/animate.css?ver=0.1" type="text/css">		
		<link rel="stylesheet" href="/static/xeicon.min.css" type="text/css">
		<link rel="stylesheet" type="text/css" href="/css/new/new_gnb.css">
		<link rel="stylesheet" type="text/css" href="/css/new/shoppingtory.css">
		<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">	
		<link rel="stylesheet" type="text/css" href="/css/custom.css">			
		
		<%
		//신규 입찰 프로세스변경 작업후 관련된 URL만 추가하기 20180123 신동아
		//꼭 base.css아래있어야함!!!!
		if( viewportType ){
		%>
			<link rel="stylesheet" type="text/css" href="/css/new/tender_layout.css?ver=0.1">
		<%
		}
		%>
		<script src="/static/js/lib/jquery-1.11.3.min.js"></script>
		<script src="/static/js/lib/jquery.popupoverlay.js"></script>
		<script src="/static/js/lib/jquery.popupoverlay.js"></script>
		<script type="text/javascript" src="/js/new/base.js"></script>
		<script type="text/javascript" src="/js/new/design.js"></script>
		<script type="text/javascript" src="/js/new/placeholders.min.js"></script>
		<script type="text/javascript" src="/js/jquery.cookie.js"></script>
		
		<link rel="stylesheet" href="/css/new/jquery.bxslider.css" type="text/css">
		<script type="text/javascript" src="/js/new/jquery.bxslider.min.js"></script>

		
		<script src="/js/jquery.number.min.js"></script>
		<script src="/js/common.js"></script>
		<script src="/js/common_validation.js"></script>
		<script src="/js/common_js.jsp"></script><!-- SNS -->
		<script src="/js/jquery-ui.js"></script><!-- SNS -->
		<!--[if lt IE 9]>
		<script src="/static/js/lib/html5shiv.min.js"></script>
		<script src="/static/js/lib/respond.js"></script>
		<![endif]-->
		<c:if test="${ FROM_SITE eq 'mk.co.kr' }"><link rel="stylesheet" type="text/css" href="/css/mk.co.kr.css"/></c:if>
		<c:if test="${ FROM_SITE eq 'officeplus.com' }"><link rel="stylesheet" type="text/css" href="/css/officeplus.com.css"/></c:if>
		<!--//기존 -->
		
		<%-- 채팅 Channel.io[s] --%>
		<!-- Channel Plugin Scripts -->
		<script>
		  ;window.channelPluginSettings = {
		    "pluginKey": "fbd17607-0716-48a7-b476-f5945a99e40e", //please fill with your plugin key
		    "userId": "<c:out value="${ USER['U_ID'] }"/>", //fill with user id
		    "profile": {
		      "name": "<c:out value="${ USER['U_NAME'] }"/>", //fill with user name
		      "mobileNumber": "<c:out value="${ USER['U_PHONE'] }"/>", //fill with user phone number
		      "CUSTOM_VALUE_1": "<c:out value="${ USER['U_TYPE'] }"/>", //any other custom meta data
		      "CUSTOM_VALUE_2": "00"
		    }
		  };
		  (function() {
		    var w = window;
		      if (w.ChannelIO) {
		      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
		    }
		    var d = window.document;
		    var ch = function() {
		      ch.c(arguments);
		    };
		    ch.q = [];
		    ch.c = function(args) {
		      ch.q.push(args);
		    };
		    w.ChannelIO = ch;
		    function l() {
		      if (w.ChannelIOInitialized) {
		        return;
		      }
		      w.ChannelIOInitialized = true;
		      var s = document.createElement('script');
		      s.type = 'text/javascript';
		      s.async = true;
		      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
		      s.charset = 'UTF-8';
		      var x = document.getElementsByTagName('script')[0];
		      x.parentNode.insertBefore(s, x);
		    }
		    if (document.readyState === 'complete') {
		      l();
		    } else if (window.attachEvent) {
		      window.attachEvent('onload', l);
		    } else {
		      window.addEventListener('DOMContentLoaded', l, false);
		      window.addEventListener('load', l, false);
		    }
		  })();
		</script>
		<!-- End Channel Plugin -->
		<%-- 채팅 Channel.io[s] --%>
		
		<!-- Facebook Pixel Code -->
		<script>
		
		var oneDepth = 0;
		var twoDepth = 0;
		
		!function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?
		n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;
		n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;
		t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,
		document,'script','https://connect.facebook.net/en_US/fbevents.js');
		fbq('init', '296849497354223', {
		em: 'snyong@castingn.com'
		});
		fbq('track', 'PageView');
		</script>
		<noscript><img height="1" width="1" style="display:none"
		src="https://www.facebook.com/tr?id=296849497354223&ev=PageView&noscript=1"
		/></noscript>
		<!-- DO NOT MODIFY -->
		<!-- End Facebook Pixel Code -->
		
		<!-- This script is for AceCounter START -->
		<script language='javascript'>
		var _ag   = 0 ;         // 로그인사용자 나이
		var _id   = '<c:out value="${ USER['U_ID'] }"/>';    		// 로그인사용자 아이디
		var _mr   = '';       	// 로그인사용자 결혼여부 ('single' , 'married' )
		var _gd   = '';        // 로그인사용자 성별 ('man' , 'woman')
		var _skey = '' ;      // 내부검색어
		
		//var _jn = '' ;          //  가입탈퇴 ( 'join','withdraw' ) 
		//var _jid = '' ;			// 가입시입력한 ID
		
		//고객,파트너 구분
		var mem_flag = '';
		<c:choose>
			<c:when test="${ not empty IS_ADMIN  }">
			mem_flag = '1000';
			</c:when>
			<c:when test="${ not empty USER }">
			mem_flag = '2000';
			_mr = 'single';
			</c:when>
		    <c:otherwise>
			mem_flag = '';
			</c:otherwise>
		</c:choose>
		
		var _ud1 = mem_flag ;			// 사용자 정의변수 1 ( 1 ~ 10 정수값)
		var _ud2 = '' ;			// 사용자 정의변수 2 ( 1 ~ 10 정수값)
		var _ud3 = '' ;			// 사용자 정의변수 3 ( 1 ~ 10 정수값)
		</script>
		<!-- AceCounter END -->
		
		<!--  groobee 삭제, 2018.05.12 -->
		
		
		<style>.async-hide { opacity: 0 !important} </style>
		<script>(function(a,s,y,n,c,h,i,d,e){s.className+=' '+y;h.start=1*new Date;
		h.end=i=function(){s.className=s.className.replace(RegExp(' ?'+y),'')};
		(a[n]=a[n]||[]).hide=h;setTimeout(function(){i();h.end=null},c);h.timeout=c;
		})(window,document.documentElement,'async-hide','dataLayer',4000,
		{'GTM-WMGGSP5':true});</script>
		
		
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-74944943-1', 'auto');
		  ga('require', 'GTM-WMGGSP5');//swjang
		  ga('send', 'pageview');
		
		</script>
		
		<!-- 타불라 2018.07.09 START, 강일평 요청 -->
		<script type='text/javascript'>
		  window._tfa = window._tfa || [];
		  window._tfa.push({notify: 'event', name: 'page_view'});
		  !function (t, f, a, x) {
		         if (!document.getElementById(x)) {
		            t.async = 1;t.src = a;t.id=x;f.parentNode.insertBefore(t, f);
		         }
		  }(document.createElement('script'),
		  document.getElementsByTagName('script')[0],'//cdn.taboola.com/libtrc/unip/1151665/tfa.js','tb_tfa_script');
		</script>
		<noscript>
		  <img src='//trc.taboola.com/1151665/log/3/unip?en=page_view' width='0' height='0' style='display:none'/>
		</noscript>
		<!-- 타불라 2018.07.09 END -->
				
		<%-- 20180809 강일평 http://castingn.hanbiro.net/ngw/app/#/board/view/h0_35/0_574 --%>
		<!-- ADN Tracker[공통] start -->
		<script type="text/javascript">
		var adn_param = adn_param || [];
		adn_param.push([{ 	
		 ui:'100452',
		 ut:'Home'
		}]);
		</script>
		<script type="text/javascript" async src="//fin.rainbownine.net/js/adn_tags_1.0.0.js"></script>
		<!-- ADN Tracker[공통] end -->
				
		<!-- Dable 스크립트 시작 / 문의 support@dable.io -->
		<script>
		(function(d,a,b,l,e,_) {
		d[b]=d[b]||function(){(d[b].q=d[b].q||[]).push(arguments)};e=a.createElement(l);
		e.async=1;e.charset='utf-8';e.src='//static.dable.io/dist/dablena.min.js';
		_=a.getElementsByTagName(l)[0];_.parentNode.insertBefore(e,_);
		})(window,document,'dablena','script');
		dablena('init', 'castingn');
		dablena('track', 'PageView');
		</script>
		<!-- Dable 스크립트 종료 / 문의 support@dable.io -->
				
	</head>
	<body>
		<div id="pageIndex">
		    <a href="#contents" class="shortcut_g">본문내용보기</a>
		    <a href="#pageGnb" class="shortcut_g">메뉴바로가기</a>
		</div>
		<div id="wrap">
		    <tiles:insertAttribute name="header" />
			<!-- 180425 추가 -->
			<div id="wrap" class="body__global reaction sub_wrap2">
				<tiles:insertAttribute name="body" />
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
		<!--// wrap -->
		
		<%-- 기존.. 사용안할것같음 --%>
		<%--
		<script type="text/javascript">
			$(document).ready(function() {
				$(".bx-pager-link1").html('입찰등록');
				$(".bx-pager-link2").html('협력사 비교/선택');
				$(".bx-pager-link3").html('상담 및 계약진행');
				$(".bx-pager-link4").html('업무 진행');
				$(".bx-pager-link5").html('평가 및 완료');
			});
			
			var slider = $('.bxslider').bxSlider({
			  auto: false,
			  pause: 5000,
			  onSlideAfter: function(){
				slider.startAuto();
			  }
			});
		</script>
		--%>
		<%-- //기존.. 사용안할것같음 --%>
		
		<!-- AceCounter Log Gathering Script V.7.5.AMZ2017020801 -->
		<script language='javascript'>
			var _AceGID=(function(){var Inf=['gtb4.acecounter.com','8080','AH3A41024068523','AW','1','NaPm,Ncisy','ALL','0']; var _CI=(!_AceGID)?[]:_AceGID.val;var _N=0;var _T=new Image(0,0);if(_CI.join('.').indexOf(Inf[3])<0){ _T.src =( location.protocol=="https:"?"https://"+Inf[0]:"http://"+Inf[0]+":"+Inf[1]) +'/?cookie'; _CI.push(Inf);  _N=_CI.length; } return {o: _N,val:_CI}; })();
			var _AceCounter=(function(){var G=_AceGID;var _sc=document.createElement('script');var _sm=document.getElementsByTagName('script')[0];if(G.o!=0){var _A=G.val[G.o-1];var _G=(_A[0]).substr(0,_A[0].indexOf('.'));var _C=(_A[7]!='0')?(_A[2]):_A[3];var _U=(_A[5]).replace(/\,/g,'_');_sc.src=(location.protocol.indexOf('http')==0?location.protocol:'http:')+'//cr.acecounter.com/Web/AceCounter_'+_C+'.js?gc='+_A[2]+'&py='+_A[4]+'&gd='+_G+'&gp='+_A[1]+'&up='+_U+'&rd='+(new Date().getTime());_sm.parentNode.insertBefore(_sc,_sm);return _sc.src;}})();
		</script>
		<noscript><img src='http://gtb4.acecounter.com:8080/?uid=AH3A41024068523&je=n&' border='0' width='0' height='0' alt=''></noscript>	
		<!-- AceCounter Log Gathering Script End -->
		
		<!-- NAVER PREMINUM 공통 적용 스크립트 , 모든 페이지에 노출되도록 설치. 단 전환페이지 설정값보다 항상 하단에 위치해야함 --> 
		<script type="text/javascript" src="//wcs.naver.net/wcslog.js"> </script> 
		<script type="text/javascript"> 
		if (!wcs_add) var wcs_add={};
		wcs_add["wa"] = "s_93ab7ded2ad";
		if (!_nasa) var _nasa={};
		wcs.inflow();
		wcs_do(_nasa);
		</script>
		
		<!-- WIDERPLANET  SCRIPT START 2016.11.17 -->
		<div id="wp_tg_cts" style="display:none;"></div>
		<script type="text/javascript">
		var wptg_tagscript_vars = wptg_tagscript_vars || [];
		wptg_tagscript_vars.push(
		(function() {
			return {
				wp_hcuid:"<c:out value="${ USER['U_PK'] }"/>",   /*Cross device targeting을 원하는 광고주는 로그인한 사용자의 Unique ID (ex. 로그인 ID, 고객넘버 등)를 암호화하여 대입.
						*주의: 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
				ti:"32127",	/*광고주 코드*/
				ty:"Home",	/*트래킹태그 타입*/
				device:"web"	/*디바이스 종류 (web 또는 mobile)*/
				
			};
		}));
		</script>
		<script type="text/javascript" async src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>
		<!-- // WIDERPLANET  SCRIPT END 2016.11.17 -->
		
		<script type="text/javascript">
		    var roosevelt_params = {
		        retargeting_id:'e4ePbqYAZNjZ6U_6cTTAlw00',
		        tag_label:'xGdfd2S5RGWC0REykZssrA'
		    };
		</script>
		<script type="text/javascript" src="//adimg.daumcdn.net/rt/roosevelt.js" async></script>
		
		<script>
		$(document).ready(function(){
			//랜딩페이지에서 띄울 팝업 공통
			var landingParam;
			if( "<%=landingPopupUrl%>" != "" ){
				
				landingParam = $.parseJSON('<%=landingPopupParam%>');
				
				//console.log("landingPopupUrl : <%=landingPopupUrl%>");
				//console.log("landingParam.name : " + landingParam.name);
				//console.log("$.cookie : " + $.cookie('landingPopup_online'));
				
				//window.open 속성들
				var popupname   = landingParam.popupname   == undefined ? "landingPopup" : landingParam.popupname;
				var width       = landingParam.width       == undefined ? "300"          : landingParam.width; 
				var height      = landingParam.height      == undefined ? "300"          : landingParam.height; 
				var left        = landingParam.left        == undefined ? "100"          : landingParam.left; 
				var top         = landingParam.top         == undefined ? "100"          : landingParam.top;
				var toolbar     = landingParam.toolbar     == undefined ? "no"           : landingParam.toolbar; 
				var menubar     = landingParam.menubar     == undefined ? "no"           : landingParam.menubar; 
				var location    = landingParam.location    == undefined ? "no"           : landingParam.location; 
				var scrollbars  = landingParam.scrollbars  == undefined ? "no"           : landingParam.scrollbars; 
				var status      = landingParam.status      == undefined ? "no"           : landingParam.status; 
				var realzable   = landingParam.realzable   == undefined ? "no"           : landingParam.realzable; 
				var fullscreen  = landingParam.fullscreen  == undefined ? "no"           : landingParam.fullscreen; 
				var channelmode = landingParam.channelmode == undefined ? "no"           : landingParam.channelmode;
				
				var name        = encodeURI(encodeURIComponent(landingParam.name));
				<%--
				if( $.cookie('landingPopup') == undefined ){
					window.open( "<%=landingPopupUrl%>?name="+name
							   , popupname
							   , "width="+width+"px, height="+height+"px, left="+left+"px, top="+top+"px, toolbar="+toolbar+", menubar="+menubar+", location="+location
							     + ", scrollbars="+scrollbars+", status="+status+", realzable="+realzable+", fullscreen="+fullscreen+", channelmode="+channelmode
							   );
				}
				--%>
			}
		});
		
		//window.open( "/popup/system.html", "system_popup", "width=580px, height=600px, left=200px, top=200px");
		</script>
		<%--
		//카카오이벤트 레이어팝업 막음 20180408 신동아
		<c:if test="${landing_event_popup ne '' && landing_event_popup ne null}">
		<%@ include file="/WEB-INF/jsp/tiles/event_popup.jsp" %>
		</c:if>
		--%>
		
		<!-- //설문조사 레이어팝업 20180706 박태석 -->
		<%-- <c:if test="${empty USER}">
		<%@ include file="/WEB-INF/jsp/event/include/E-07/E-07_popup.jsp" %>
		</c:if> --%>
		
		<%-- 20180503 장성원 - 뷰저블 사이트분석 스크립트 --%>
		<script type="text/javascript">
		(function(w, d, a){
            w.__beusablerumclient__ = {
				load: function(src){
		             var b = d.createElement("script");
		
		             b.src = src; b.async=true; b.type = "text/javascript";
		
		             d.getElementsByTagName("head")[0].appendChild(b);
				}
            };w.__beusablerumclient__.load(a);
		})(window, document, '//rum.beusable.net/script/b180503e074529u105/46026db526');
		</script>
		
		<!-- TIXEL corp Web Analitics & Live Chat START 2018.09.05 -->
		<script type="text/javascript">
		/* Custom Parameters */
		var _HCmz={
			PC: "",  //[상품상세 페이지에서 입력] 상품명
			PT: "",  //[상품상세 페이지에서 입력] 카테고리명(카테고리가 여러단계일 경우 ';'으로 구분 [예] 홈;가방) 
			PS: "",  //[상품상세 페이지에서 입력] 상품가격(예:29000)
			PA: "",  //[장바구니 페이지에서 입력] 장바구니상품 (상품명_수량) 여러개 경우 ';'로 구분 (상품명1_수량;상품명2_수량)
			MP: "",  //[주문서작성 페이지에서 입력] 구매전환상품 (상품명_가격[단가]_수량) 여러개 경우 ';'로 구분
			OD: "",  //[주문완료 페이지에서 입력] 주문번호
			SO: "",  //[장바구니, 주문서작성, 주문완료 페이지에서 입력] 전환단계 (장바구니:cart, 주문서작성:cartend, 주문완료:payend)
			MA: "",  //[수집가능한 페이지에서 입력] 회원연령(예: 30)
			MS: "",  //[수집가능한 페이지에서 입력] 회원성별(M 또는 W) 
			MR: "",  //[수집가능한 페이지에서 입력] 회원지역(예: 서울)
			IS: "",  //[홈페이지내 검색결과페이지에서 입력] 검색어
			IC: "",  //[홈페이지내 검색결과페이지에서 입력] 검색성공여부(검색결과가 있을경우: Y, 없을경우: N)
		
			FPC: "",  //[상품상세 페이지에서 입력] 상품코드
			FPU: "",  //[상품상세 페이지에서 입력] 상품URL (http://를 포함한 전체URL -> 불필요한 GET 데이터는 제거 권장)
			FIU: "",  //[상품상세 페이지에서 입력] 이미지URL (http://를 포함한 전체URL)
			FCP: "",  //[장바구니 페이지에서 입력] 장바구니에 담겨진 상품코드 (여러개일경우 ';'으로 구분)
			FPS: "",  //[장바구니 페이지에서 입력] 장바구니 상품 총금액
			FMP: "",  //[주문서작성 또는 구매완료페이지에서 단한번만 입력] 상품코드_가격[단가]_수량 (여러개일경우 ';'으로 구분)
			FBP: "",  //[게시판 글쓰기완료 또는 신청완료 페이지 에서 입력] 글쓰기완료시 Y
			FIS: ""   //[홈페이지내 검색결과페이지에서 입력] 검색어
		};
		/* Custom Parameters */
		
		function logCorpAScript_full(){
			HTTP_MSN_MEMBER_NAME="";/*member name*/
			HTTP_MSN_FBAID_KEY="960954614048337";
			var prtc=(document.location.protocol=="https:")?"https://":"http://";
			var hst=prtc+"webfb.http.or.kr";
			var rnd="r"+(new Date().getTime()*Math.random()*9);
			this.ch=function(){
				if(document.getElementsByTagName("head")[0]){logCorpAnalysis_full.dls();}else{window.setTimeout(logCorpAnalysis_full.ch,30)}
			}
			this.dls=function(){
				var h=document.getElementsByTagName("head")[0];
				var s=document.createElement("script");s.type="text/jav"+"ascript";try{s.defer=true;}catch(e){};try{s.async=true;}catch(e){};
				if(h){s.src=hst+"/HTTP_MSN/UsrConfig/castingn1/js/ASP_Conf.js?s="+rnd;h.appendChild(s);}
			}
			this.init= function(){
				document.write('<img src="'+hst+'/sr.gif?d='+rnd+'" style="width:1px;height:1px;position:absolute;display:none" onload="logCorpAnalysis_full.ch()" alt="" />');
			}
		}
		if(typeof logCorpAnalysis_full=="undefined"){var logCorpAnalysis_full=new logCorpAScript_full();logCorpAnalysis_full.init();}
		</script>
		<noscript><img src="//webfb.http.or.kr/HTTP_MSN/Messenger/Noscript.php?key=castingn1" style="display:none;width:0;height:0;" alt="" /></noscript>
		<!-- TIXEL corp Web Analitics & Live Chat END -->
	
	<%-- <!-- 우측 하단 레이어 -->
	<script>
        $(window).load(function () {
        	
        	var filter = "win16|win32|win64|mac|macintel"; 
    		var isMobile = false;
    		if ( navigator.platform ) { 
    			if ( filter.indexOf( navigator.platform.toLowerCase() ) < 0 ) {
    				//모바일
    				isMobile = true;
    			}
    		}
        	
        	var setTime = 5000;
        	
          	//로그인 1초, 그냥은 5초
          	<c:choose>
				<c:when test="${ not empty USER }">
				setTime = '1000';
				</c:when>
			    <c:otherwise>
			    setTime = '10000';
				</c:otherwise>
			</c:choose>
        	
            $('#layer_ad').css({
                'position':'fixed',
                'bottom' :'2px',
                'right':'20px',
                'padding':'10px',
                'font-size':'12px',
                <c:choose>
					<c:when test="${ not empty USER }">
					'width':'534px',
	                'height':'519px',
					</c:when>
				    <c:otherwise>
				    'width':'350px',
	                'height':'460px',
					</c:otherwise>
				</c:choose>
                'border-width':'1px 1px 1px 1px',
                'border-color':'#aaaaaa',
                'border-style':'solid',
                'z-index':9999999999
            });
		    
            
            if( $.cookie('referral') == "referral" || isMobile ){
            	$("#layer_ad").css('display','none');
            } else {
                setTimeout(function() {
	                if ($('#layer_ad').css('bottom') != '20px') {
	                    $('#layer_ad').animate({'bottom':'2px'}, 'slow');
	                } else {
	                    $('#layer_ad').animate({'bottom':'0px'}, 'slow');
	                }
	            }, setTime);
            }
            
            $("#layer_ad").click(function() {
            	var date = new Date();
            	date.setTime(date.getTime() + 60*60*1000); // 60분
            	$.cookie( 'referral', 'referral',{ expires: date, path: '/' } );
        	});
        	
        	$("#layer_ad2").click(function(){
    			closeAdNotToday();
    		});
    		
        });
        
      	//레이어팝업 숨김
    	function closeAdPopup(){
    		$("#layer_ad").hide('fade');
    	}
      	
    	function closeAdNotToday(){
    		setCookie("referral",'referral', 1);
    		$("#layer_ad").hide('fade');
    	}
    	
    	function setCookie(name, value, expiredays) {
    		var today = new Date();
    		today.setDate(today.getDate() + expiredays);
    	
    		document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
    	}
    	
    	function getCookie(name){ 
    	    var cName = name + "="; 
    	    var x = 0; 
    	    while ( x <= document.cookie.length ) 
    	    { 
    	        var y = (x+cName.length); 
    	        if ( document.cookie.substring( x, y ) == cName ) 
    	        { 
    	            if ( (endOfCookie=document.cookie.indexOf( ";", y )) != -1 ){ 
    	                //endOfCookie = document.cookie.length;
    	            	return unescape( document.cookie.substring( y, endOfCookie ) ); 
    	            }
    	        } 
    	        x = document.cookie.indexOf( " ", x ) + 1; 
    	        if ( x == 0 ) 
    	            break; 
    	    } 
    	    return ""; 
    	}
    	
    </script>
    
    <%--
    //임시 주석 처리 다시사용예정 20180719 최은별 
    <!-- 로그인 ID만 있으면 동료 소개하는 만큼 백화점 상품권 - 레이어팝업 -->
	<c:choose>
		<c:when test="${ not empty USER }">
		<div id="layer_ad" class="layer_closeh" style="background: #ffffff;cursor:pointer;">
			<a href="/event/view?code=E-06"><img src="/event2/img/E-06/180625_login.png"></a>
			<input type="checkbox" id="layer_ad2" style="margin-right:10px;"><label for="layer_ad2" style="cursor:pointer;">오늘하루 보지않기 </label>
	      	<button type="button" class="btn_filter_close" id="intro_btn_filter_close" title="닫기" onClick="javascript:closeAdPopup();" style="padding-left: 300px;"><span>닫기</span></button>
		</div>
		</c:when>
	    <c:otherwise>
	    <div id="layer_ad" class="layer_closeh" style="background: #ffffff;cursor:pointer;">
	    	<a href="/event/view?code=E-06"><img src="/event2/img/E-06/180629_waitjoin.png"></a>
	   	 	<input type="checkbox" id="layer_ad2" style="margin-right:10px;"><label for="layer_ad2" style="cursor:pointer;">오늘하루 보지않기 </label>
	      	<button type="button" class="btn_filter_close" id="intro_btn_filter_close" title="닫기" onClick="javascript:closeAdPopup();" style="padding-left: 150px;"><span>닫기</span></button>
	    </div>  	
		</c:otherwise>
	</c:choose> --%>
	
	</body>
</html>
<% session.removeAttribute("MESSAGE_ALERT");%>