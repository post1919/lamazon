/* gnb animation */
$(function() {
    $(".menu_btn").on("click", function() {
        var that = $(this);
        if (that.hasClass("is-open")) {
            that.removeClass("is-open").addClass("is-closed");
        } else {
            that.removeClass("is-closed").addClass("is-open");
        }
    });
});




/* 레이어 팝 */
function ViewlayerPop(pop_no){
			var WinHeight = $(window).height();
			
			var pop_ = eval("document.getElementById('layerPop"+pop_no+"')");
			$(".layerPop").hide();
			$("#black").fadeIn(200);
			$(pop_).children(".layerPop_in").css({maxHeight:WinHeight - 120});
			
			var PopHeight = $("#layerPop"+pop_no).height() ;
			if (WinHeight >= PopHeight) {
				$(pop_).css({marginTop:-PopHeight/2});
			}else {
				$(pop_).css({marginTop:-WinHeight/2});
			}
			$(pop_).fadeIn(200);
			$("body").addClass("fixe");
}

function CloselayerPop(){
	$(".layerPop").hide();
	$("#black, .layerPop").fadeOut(200);
	$("body").removeClass("fixe");
}
function CloselayerPop(pop_no){
	var pop_ = eval("document.getElementById('layerPop"+pop_no+"').style");
	pop_.display = 'none';	
	$("#black, .layerPop").fadeOut(200);
	$("body").removeClass("fixe");
}




/* 폰트 사이즈 */
function FontDown(){
	var objFont = (parseInt($(".reding_txt .txt").css("fontSize"))-1) + "px";
	$(".reding_txt .txt").css("fontSize",objFont);
}
function FontUp(){
	var objFont = (parseInt($(".reding_txt .txt").css("fontSize"))+1) + "px";
	$(".reding_txt .txt").css("fontSize",objFont);
}
function FontBack(){
	$(".reding_txt .txt").css("fontSize","16px");
}
function KID_FontBack() {
	$(".reding_txt .txt").css("fontSize","18px");
}



var dt= new Date();
var check_month = dt.getMonth() + 1;
var check_year = dt.getFullYear();

var i = 0;
/* gnb_sub */
	$(document).ready(function(){
		
		
		/* 페이지 체크
		$(".gnb0"+oneDepth).addClass("on");
		$(".on .lnb0"+twoDepth).addClass("on");
		
		var pageName1 = $(".gnb ul > li.on > a").html();
		var pageName2 = $(".sub_menu > li.on > a").html();
		
		
		$("#lnb_tit, #location_1").html(pageName1);
		$("#title").html(pageName2);
		$("#location_2").html(pageName2);
		$("#wrap").addClass("sub_wrap_"+oneDepth);
		
		var onGnb = $(".gnb ul > li.on > .sub_menu").html();
		var gnb = $("#gnbW").html();
		$("#lnb_menu").html(onGnb);
		$("#onedepth").html(gnb);
		 */
		
		
	
	
		
		
		/* 전체메뉴 */
		var allmenu = 0
		$('.menu_btn').click(function(){
			if (allmenu == 0){
				$('#blackW').fadeIn(200);
				$("body").css("overflow", "hidden");
				$('#all_gnbW').animate({left:0},300);
				allmenu = 1;
			}else {
				$('#blackW').fadeOut(200);
				$("body").css("overflow", "");
				$('#all_gnbW').animate({"left":"-81%"},300);
				allmenu = 0;
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
		
		
	
		
	

		
	});
	
	

		

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







