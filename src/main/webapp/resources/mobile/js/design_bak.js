
/* tab */
function tabView(name,id){
	$("."+name+"_s").removeClass("on");
	$(".slnb0"+id).addClass("on");
	$("#"+name+id).addClass("on");
	$("."+name+"_a").hide();
	$("#"+name+"_a"+id).show();
}


/* 레이어 팝 */
function ViewlayerPop(pop_no){
	var WinHeight = $(window).height();
	
	var PopWidth = $("#layerPop"+pop_no).width();
	var pop_ = eval("document.getElementById('layerPop"+pop_no+"')");
	//$(".foot_a").after( '<div id="black"></div>');
	//$(".layerpop_a").fadeOut(200);
	$(".layerPop").hide();
	$("#black").fadeIn(200);
	$(pop_).css({marginLeft:-PopWidth/2});
	$(pop_).children(".layerPop_in").css({maxHeight:WinHeight - 60});


	var PopHeight = $("#layerPop"+pop_no).height();
	//console.debug(PopHeight);
	if (WinHeight >= PopHeight) {
		$(pop_).css({marginTop:-PopHeight/2});
	}else {
		$(pop_).css({marginTop:-WinHeight/2});
	}
	$(pop_).fadeIn(200);
	//$("body").addClass("fixe");
}
function counselPop(){
	var WinHeight = $(window).height();
	var PopWidth = $("#counselPop").width();

	$(".layerPop").hide();
	$("#black").fadeIn(200);
	$("#counselPop").css({marginLeft:-PopWidth/2});
	$("#counselPop").children(".layerPop_in").css({maxHeight:WinHeight - 0});


	var PopHeight = $("#counselPop").height();
	//console.debug(PopHeight);
	if (WinHeight >= PopHeight) {
		$("#counselPop").css({marginTop:-PopHeight/2});
	}else {
		$("#counselPop").css({marginTop:-WinHeight/2});
	}
	$("#counselPop").fadeIn(200);
}



function CloselayerPop(){
	//var pop_ = eval("document.getElementById('layerPop"+pop_no+"').style");
	//pop_.display = 'none';	
	$("#black, .layerPop").fadeOut(200);
	$("body").removeClass("fixe");
}


function popPrint(){
	$("#black").addClass("printW");
	$(".layerPop").addClass("printW");
	window.print();
	setTimeout ( function (){
		$("#black").removeClass("printW");
		$(".layerPop").removeClass("printW");
	},100);
}






var is_Resized;
var is_Resized_cnt = 0;

/* 팝업 auto_resiaing */
function win_resize(){
        $(document).attr("overflow-x","hidden");
        $(document).attr("overflow-y","auto");

        var wrapWidth = $("#Ne_Popw").outerWidth();
        var wrapHeight = $("#Ne_Popw").outerHeight();
        
        var w1 = $(window).width();
        var h1 = $(window).height();

		try {
			// 크롬의 문제로 W, H 값을 따로 설정
			// window.resizeBy(wrapWidth - w1, wrapHeight-h1);
			window.resizeBy(wrapWidth- w1, 0);
			window.resizeBy(0, wrapHeight - h1);
			//창 크기 자동 조절 E

			if ($(window).height() != $("#Ne_Popw").outerHeight()) {
				is_Resized = setTimeout(function(){win_resize()}, 200);
									is_Resized_cnt++;
									if (is_Resized_cnt >= 3) {
											  clearTimeout(is_Resized);
									}
			}

		}
		catch (e) {
		}
}

function Target(id) {
	 $(".dtab > li").removeClass("on");
	 $(".target_"+id).parent().addClass("on"); 
}


/* 이미지 전환 */
function faqlist(q_open) {
	if($(q_open).css('display')!="none"){
		$(q_open).hide();
	}else{
		$('tbody>tr.bg2').hide();
	}
}






var dt= new Date();
var check_month = dt.getMonth() + 1;
var check_year = dt.getFullYear();

var i = 0;
/* gnb_sub */
	$(document).ready(function(){
		
		
		/* 페이지 체크 */
		$(".gnb"+oneDepth).addClass("on");
		$(".on .lnb"+twoDepth).addClass("on");
		
		
		var pageName1 = $(".gnb ul > li.on > a").html();
		var pageName2 = $(".sub_menu > li.on > a").html();
		
		
		$("#lnb_tit, #location_1").html(pageName1);
		//$("#one").html(pageName1);
		$("#title").html(pageName2);
		$("#location_2").html(pageName2);
		$("#wrap").addClass("sub_wrap"+oneDepth);
		
		
		
		var onGnb = $(".gnb ul > li.on > .sub_menu").html();
		var onGnb2 = $(".gnb ul > li.on > .sub_menu > li.on").html();
		$("#lnb_menu").html(onGnb);
		
		
		/* 검색 */
		$("#open_search").click(function() {
			$(".search_areaW").slideToggle(300);
		});
		$("#close_search").click(function() {
			$(".search_areaW").slideUp(300);
		});
		
		

	
	
		//ie9이하 placeholder 적용
		$('input, textarea').placeholder();
		/* 페밀리 사이트 */
		$(".languages > p").click(function() {
            $("#footerW").toggleClass("z_over");
			$(".languages > ul").toggleClass("show");
        });
		$(".languages").mouseleave(function () {
			$("#footerW").removeClass("z_over");
			$(".languages > ul").removeClass("show");
		});
		
		
		$('#review_table td.bt').click(function(){
			
			var item = $(this).parent("tr");
			
			if(item.next().css('display')!="none"){
				
				item.next().hide();
				$(this).removeClass("open");
			
			}else{
				$('#review_table tr.bg2').hide();
				$('#review_table tr.bg .bt').removeClass("open");
				item.next().show();
				$(this).addClass("open");
	
			}
			
		});
		
		/* 자주 묻는 질문 아코디언 */
		$('tbody>tr.bg').click(function(){
			var item = $(this);
			
			if(item.next().css('display')!="none"){
				$('tbody>tr.bg2').hide();
				$(".open").removeClass("open");
			}else{
				$('tbody>tr.bg2').hide();
				$(".open").removeClass("open");
				item.next().show();
				$(this).addClass("open");
			}
			
		});
		
		/* 전체메뉴 */
		$("#all_open").click(function(){
			$(".sitemap_wrap").show()
			$("#black").fadeIn(200);
			$("body").addClass("fixe");	
		});
		
			
	
		/* gnb */
		$(".gnb5").mouseenter(function(){
			
			$("#gnb_bar").stop().slideDown(200);
			
		});
		
		$(".gnb > ul > li").mouseenter(function(){
			var item = $(this);
			//$("#gnb_bar").stop().slideDown(200);
			//fadeTo( ①duration, ②opacity, [ ③easing ], [ ④callback ] );
			item.children(".sub_menu").stop().slideDown(200);
		});
		$(".gnb > ul > li").mouseleave(function(){
			var item = $(this);
			$("#gnb_bar").slideUp(200);
			//fadeTo( ①duration, ②opacity, [ ③easing ], [ ④callback ] );
			$(".sub_menu").slideUp(200);
		});
		
		
		/* lnb */
		$(".right_menu > li").click(function(){
			var item = $(this)
			if ($(this).children(".sub_m").css('display')!="none"){
				 $(this).children(".sub_m").slideUp(300);
				 $(this).removeClass("on");
			}else{
				$(".sub_m").slideUp(300);
				$(".right_menu > li").removeClass("on");
				$(this).children(".sub_m").slideDown(300);
				$(this).addClass("on");
			}
			
		});

	
		/* layer pop */
		$("#black, .close_bt, .close_bt2").click(function() {
			$("#black, .layerPop, .sitemap_wrap").fadeOut(200);
			$("body").removeClass("fixe");
		});
		
		var ddd="Y";
		
		$(".layerPop").click( function(){
			if (ddd=="Y") {
				 $("#black, .layerPop").fadeOut(200);
				 $("body").removeClass("fixe");
			} else {
				ddd="Y";
			}
		});
		$(".layerPop_in").click( function(){
			ddd="N";
		});
		
		
		/* 선택영역 프린트 */
		$(".bt_print, .print_bt").click(function(){
			$("#Ne_Popw").addClass("printW");
			window.print();
			setTimeout ( function (){
				$("#Ne_Popw").removeClass("printW");
			},100);
		});	
		
		
		$(".onlyNumber").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
		});
		
		$(".onlyAlphabet").keyup(function(event){
			if (!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^a-z]/gi,''));    
			}
		});
		
		$(".notHangul").keyup(function(event){
			if (!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
			}
		});
		
		$(".onlyHangul").keyup(function(event){
			if (!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[a-z0-9]/gi,''));
			}
		});
	

		
	});
	
	/* 셀렉 디쟈인 */
		function Select(id) {
			
			if($(".Select-"+id).css('display')=="none"){
				$(".Select-"+id).show(); 
			} else {
				$(".Select-"+id).hide(); 
			}
			
			$(".Select-"+id).find("li").on("click",function(){
				if($(this).text()=="직접입력"){
					$("#Select-"+id).prop("readonly",false).focus().val("");        
					$(".Select-"+id).hide();
				}
				else{
				var $val=$(this).text();
				$("#Select-"+id).val($val);
				$(".Select-"+id).hide();
				}
			})
			
			$(".st_select").mouseleave(function () {
			$(".Select").hide();
			});

		}





/* 이벤트 롤링 */
jQuery.fn.imageScroller = function(params){
	var p = params || {
		next:"buttonNext",
		prev:"buttonPrev",
		frame:"scrollerFrame",
		width:130,
		child:"a",
		auto:true
	}; 
	var _btnNext = $("#"+ p.next);
	var _btnPrev = $("#"+ p.prev);
	var _imgFrame = $("#"+ p.frame);
	var _width = p.width;
	var _child = p.child;
	var _auto = p.auto;
	var _itv;
	
	var turnLeft = function(){
		_btnPrev.unbind("click",turnLeft);
		if(_auto) autoStop();
		_imgFrame.animate( {marginLeft:-_width}, '300', '', function(){
			_imgFrame.find(_child+":first").appendTo( _imgFrame );
			_imgFrame.css("marginLeft",0);
			_btnPrev.bind("click",turnLeft);
			if(_auto) autoPlay();
		});
	};
	
	var turnRight = function(){
		_btnNext.unbind("click",turnRight);
		if(_auto) autoStop();
		_imgFrame.find(_child+":last").clone().show().prependTo( _imgFrame );
		_imgFrame.css("marginLeft",-_width);
		_imgFrame.animate( {marginLeft:0}, '300' ,'', function(){
			_imgFrame.find(_child+":last").remove();
			_btnNext.bind("click",turnRight);
			if(_auto) autoPlay(); 
		});
	};
	
	_btnNext.css("cursor","hand").click( turnRight );
	_btnPrev.css("cursor","hand").click( turnLeft );
	
	var autoPlay = function(){
	  _itv = window.setInterval(turnLeft, 3000);  
	};
	var autoStop = function(){
		window.clearInterval(_itv);
	};
	if(_auto)	autoPlay();
};


	
		

/* 이미지 전환 */
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}







