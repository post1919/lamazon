//<![CDATA[

function setCookie(name, value, expiredays) //쿠키 저장함수
{
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays);
    document.cookie = name + "=" + escape(value) + "; path=/; expires="
            + todayDate.toGMTString() + ";"
}



function getCookie(Name) { // 쿠키 불러오는 함수
    var search = Name + "=";
    if (document.cookie.length > 0) { // if there are any cookies
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // if cookie exists
            offset += search.length; // set index of beginning of value
            end = document.cookie.indexOf(";", offset); // set index of end of cookie value
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
}

// Date field 값을 쿠키로 저장 
function setDate( id, cookieName )
{
	 setCookie( "test", $(("#" + id)).val(), 0);
	 
}


// 쿠키에 값이 있으면 Date field 에 값 설정  
function checkDate( id, cookieName )
{
	 if (getCookie(cookieName)) {
		 $(("#" + id)).val(getCookie(cookieName));
	 } 
		 
}

// 팝업창 열기
function popupWindow(url,title,left,top,width,height)
{
	var option = "left="+left+",top="+top+",width="+width+",height="+height+",toolbar=no,left=0,top=0,location=no,directories=no,scrollbars=yes,status=no,menubar=no,resizable=no";
	var newWin = window.open(url, title, option);
	if (!newWin)
		alert("팝업창을 허락해 주세요!");

	return false;
}


$(document).ready(function() {
	$(".datepicker").datepicker($.datepicker.regional.kr);    
	
	
});

// localstorage set
function setLocalStorage(item, value) {
	var path = unescape(document.location);
    key = path + "_" + item;
	localStorage.setItem(key, value);
}

// localstorage get
function getLocalStorage(item) {
	var path = unescape(document.location);
    key = path + "_" + item;

	return localStorage.getItem(key);
}

var GRID_PAGESIZE = 100;					/* 그리드 공통으로 사용하는 페이지 사이즈 */



function getPageSize()
{
	var size = GRID_PAGESIZE;
	
	if ( getLocalStorage("pagecount") != null )
	{
		if ( $.isNumeric(getLocalStorage("pagecount"))   )  {
			size = getLocalStorage("pagecount") ;
		}
	}
	
	return size;
}


function getPageSizeByInput()
{
	var size = GRID_PAGESIZE;
	
	if ( $.isNumeric($("#pagecount").val())   )  {
		size = $("#pagecount").val();
	}
	
	return size;
}


// 원단위 절삭 또는 반올림 ( 4원이하 절삭, 5원이상 반올림 )
function round( value )
{
	var num = 0;
	var mod = value % 10;
	
	if ( mod < 5 ) 	{
		num = Math.floor( value / 10 ) * 10 ;
	} else {
		num = Math.floor( value / 10 ) * 10 + 10;
	}

	
	return num;
}

function fnMove(obj, position){
    var offset = $(obj).offset();
    $('html, body').animate({scrollTop : offset.top+position}, 400);
}

//문자열에서 HTML태그 내용 지워줌
function removeHTML(text){
	text = text.replace(/<br\/>/ig, "\n"); 
	text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
	return text;
}

//]]>