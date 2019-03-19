var zone1_arr = ["전국","서울","경기","인천","충남","충북","강원","전남","전북","경남","경북","부산","대구","대전","광주","울산","제주"];
var zone2_json = {"전국":["전국"],
		"서울":["전지역","광진구","동대문구","중랑구","용산구","성동구","강북구","도봉구","노원구","은평구","서대문구","마포구","양천구","강서구","구로구","금천구","영등포구","동작구","관악구","서초구","강남구","송파구","강동구","종로구","중구","성북구","서대문구","마포구","양천구","강서구","구로구","금천구","영등포구","동작구","관악구","서초구","강남구","송파구","강동구","종로구","중구","성북구"],
		"경기":["전지역","파주시","수원시","성남시","화성시","안양시","부천시","광명시","평택시","이천시","동두천시","안산시","안성시","고양시","과천시","구리시","남양주시","오산시","시흥시","군포시","의왕시","하남시","김포시","용인시","연천군","가평군","양평군","광주시","포천시","양주시","의정부시","여주시"],
		"인천":["전지역","동구","남구","연수구","남동구","계양구","서구","강화군","동진군","중구","부평구"],
		"충남":["전지역","공주시","천안시","천안시","서북구","청양군","홍성군","논산시","예산군","계룡시","태안군","금산군","부여군","보령시","서천군","아산시","천안시","동남구","서산시","당진시"],
		"충북":["전지역","괴산군","제천시","음성군","단양군","옥천군","영동군","청주시","청주시","상당구","청주시","흥덕구","충주시","증평군","진천군","보은군","청주시","청원구","청주시","서원구"],
		"강원":["전지역","횡성군","영월군","평창군","정선군","철원군","화천군","양구군","인제군","원주시","고성군","강릉시","태백시","속초시","삼척시","홍천군","춘천시","양양군","동해시"],
		"전남":["전지역","목포시","여수시","장성군","장흥군","완도군","광양시","강진군","담양군","진도군","곡성군","해남군","신안군","영암군","구례군","고흥군","무안군","순천시","보성군","함평군","화순군","영광군","나주시"],
		"전북":["전지역","정읍시","순창군","남원시","고창군","김제시","완주군","부안군","완산구","전주시","덕진구","진안군","익산시","장수군","임실군","군산시","무주군"],
		"경남":["전지역","함안군","진주시","창녕군","통영시","사천시","고성군","김해시","산청군","밀양시","남해군","하동군","거제시","창원시","양산시","의령군","합천군","창원시","진해구","창원시","마산합포구","창원시","마산회원구","창원시","성산구","창원시","의창구","함양군","거창군"],
		"경북":["전지역","안동시","구미시","포항시","포항시","남구","영천시","포항시","북구","영주시","경주시","김천시","청도군","고령군","성주군","경산시","칠곡군","군위군","예천군","의성군","봉화군","울진군","상주시","울릉군","영덕군","문경시","청송군","영양군"],
		"부산":["전지역","동구","영도구","부산진구","동래구","서구","남구","북구","해운대구","금정구","강서구","연제구","수영구","사상구","기장군","중구","사하구"],
		"대구":["전지역","중구","동구","서구","남구","달서구","달성군","북구","수성구"],
		"대전":["전지역","동구","중구","서구","유성구","대덕구"],
		"광주":["전지역","동구","서구","남구","북구","광산구"],
		"울산":["전지역","중구","동구","울주군","남구","북구"],
		"제주":["전지역","제주시","서귀포시"]}

/*
var cate1_arr = ["마케팅","인사/노무","총무/법무"]
var cate2_json = {"마케팅":["고객관리","광고홍보","디자인","브랜드가이드","사업계획서","온라인광고","웹사이트 구축","판촉물","행사기획"],
		"인사/노무":["교육","복리후생","인력구인"],
		"총무/법무":[]
}

var cate3_json = {"마케팅":["전국"],
		"인사/노무":[],
		"총무/법무":[]
}*/

function go_login() {
	var url = '';
	if(window.location.href) {
		url = window.location.href;
	} else if(document.URL) {
		url = document.URL;
	}
	if(url!='') {
		url = escape(url);
		document.location.href = "/login.cast?returnURL="+url;
	} else {
		url = escape(url);
		document.location.href = "/login.cast";
	}
}



function go_login_officeplus() {
	var url = '';
	if(window.location.href) {
		url = window.location.href;
	} else if(document.URL) {
		url = document.URL;
	}
	if(url!='') {
		url = escape(url);
		document.location.href = "http://www.officeplus.com/member/login/login.jsp?goUrl="+url;
	} else {
		url = escape(url);
		document.location.href = "http://www.officeplus.com/member/login/login.jsp?goUrl=http://qubridge.castingn.com";
	}
}


function go_login_mk() {
	var url = '';
	if(window.location.href) {
		url = window.location.href;
	} else if(document.URL) {
		url = document.URL;
	}
	if(url!='') {
		url = escape(url);
		document.location.href = "https://member.mk.co.kr/mem/v1/login.php?successUrl="+url;
	} else {
		url = escape(url);
		document.location.href = "https://member.mk.co.kr/mem/v1/login.php?successUrl=http://bizplus.mk.co.kr/";
	}
}



function go_join() {
	document.location.href = "/join/select";
}

function go_join_user(){
	document.location.href = "/join/user/form";
}

function setZone(id_prefix) {
	for(var i=0;i<zone1_arr.length;i++) {
		$(id_prefix+"_zone1").append("<option>"+zone1_arr[i]+"</option>");
	}
	
	var _zone1 = "전국";
	if($(id_prefix+"_zone1").attr("data-value")!="") {
		_zone1 = $(id_prefix+"_zone1").attr("data-value");
	}
	
	$(id_prefix+"_zone1").val(_zone1);

	_setZone2(_zone1,id_prefix);
	
	if($(id_prefix+"_zone2").attr("data-value")!="") {
		$(id_prefix+"_zone2").val($(id_prefix+"_zone2").attr("data-value"));
	}
	
	
	$(id_prefix+"_zone1").change(function() {
		$(id_prefix+"_zone2").find("option").remove();
		
		var zone1 = $(id_prefix+"_zone1 option:selected").text();
		console.log('zone 1 '+zone1)
		
		_setZone2(zone1,id_prefix);
	})
}

function setButtonZipcode(bttn, zipcodeName, addressName, addressDetailName) {
	$(bttn).click(function() {
		new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('input[name='+zipcodeName+']').val(data.zonecode)
                $('input[name='+addressName+']').val(fullAddr)
                //document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                //document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById('sample6_address2').focus();
    
                //상세주소에 포커스 줄경우
                if( typeof $('input[name='+addressDetailName+']').val() == 'undefined' ){
                	$('input[name='+addressName+']').focus();
                	
            	//기본주소에 포커스 줄경우
                } else {
                	$('input[name='+addressDetailName+']').focus();
                }
            }
        }).open();
	});
}

function _setZone2(zone1,id_prefix) {
	if(zone1=="시/도") {
		$(id_prefix+"_zone2").append("<option>구/군</option>");
		return;
	}
	
	var arr = zone2_json[zone1];
	
	for(var i=0;i<arr.length;i++) {
		$(id_prefix+"_zone2").append("<option>"+arr[i]+"</option>");
	}
}

function validation_user_id(user_id) {
	var reg_id = /^[a-z0-9]{3,19}$/; //첫번째는 영어소문자인 영어나 숫자나 영문숫자 혼합 4~ 20자 [a-z0-9A-z_-]는 \w로 표현가능.
	if(!reg_id.test(user_id)){
    	return false;
    }
	return true;
}
function validation_user_passwd(user_pw){
	var reg_pw = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	if(!reg_pw.test(user_pw)){
    	return false;
    }
	return true;
}

function validation_user_email(user_email){
	var reg_email = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if(!reg_email.test(user_email)){
    	return false;
    }
    return true;
}

function validation_user_mobile(user_mobile){
	var reg_mobile =  /^01([0|1|6|7|8|9]?)?-?([0-9]{3,4})?-?([0-9]{4})$/;
    if(!reg_mobile.test(user_mobile)){
    	return false;
    }
    return true;
}

function isNumber(str) {
    var oneDecimal = false;
    var oneChar = 0;
    str = str.toString();
    for (var i = 0; i < str.length; i++) {
        oneChar = str.charAt(i).charCodeAt(0);
        
        if (oneChar == 45) {
            if (i == 0) {
                continue;
            } else {
                return false;
            }
        }
        if (oneChar < 48 || oneChar > 57) {
            return false;
        }
    }
    return true;
}

function onlyNumber() {
	if(event.keyCode<48 || event.keyCode>57) event.returnValue = false;
}

function onlyNumberDash() {
	if(event.keyCode == 45) { // - 는 사용 가능
		
	} else if(event.keyCode<48 || event.keyCode>57) {
		event.returnValue = false;
	}
}

function call(data,opt) {
	var _async = true;
	var _dataType = 'text';
	var _type = "POST";
	
	if(opt['async']!=null) {
		_async = opt['async'];
	}
	
	if(opt['dataType']!=null) {
		_dataType = opt['dataType'];
	}
	
	if(opt['type']!=null) {
		_type = opt['type'];
	}
	
	
	$.ajax({
		type: _type,
		url: opt['url'],
		data:data,
		dataType:_dataType,
		async: true,
		success:function( json ) {
			if(typeof opt['call_back'] =="function") {
				opt['call_back'](json);
			}
		},
		fail:function() {
			console.log('error')
			if(typeof opt['call_back_fail'] =="function") {
				opt['call_back_fail'](json);
			}
		}
	});
}


function error(message) {
	alert(message);
}


function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

function find_id() {
	var url = "/join/find/id";
	var name = 'find_id';
	var width = 440;
	var height = 310;
	var scroll = false;
	openPopup(url,name,width,height,scroll);
}

function find_passwd() {
	var url = "/join/find/password";
	var name = 'find_passwd';
	var width = 440;
	var height = 352;
	var scroll = false;
	openPopup(url,name,width,height,scroll);
}

function openPopup(url,name,width,height,scroll) {
	var left = ($(window).width() - width) /2 
	var top = ($(window).height() - height) /2 
	
	if(top<0) top = 0;
	
	var specs = "left="+left+",top="+top+",width="+width+",height="+height;
	specs += ",toolbar=no,menubar=no,status=no,resizable=no,";
	
	if(scroll) specs += "scrollbars=yes";
	else specs += "scrollbars=no";
	
	window.open(url, name, specs);
}

function openNewWindow(url, name) {
	  var specs = "left=10,top=10,width=900,height=550";
	  specs += ",toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no";
	  window.open(url, name, specs);
}


$(function(){
	$("#btn_user_menu").click(function() {
		if($(this).attr("data-status")=="hide") {
			console.log("aaa")
			$("#user_menu").show();
			$(this).attr("data-status","show");
		} else {
			$("#user_menu").hide();
			$(this).attr("data-status","hide");
		}
	});
	
	$('#btn_user_menu').mouseenter(function(e) {
		$("#user_menu").show();
    });
	
	$('#user_menu').mouseleave(function(e) {
		$("#user_menu").hide();
    });
	
	
});




Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var month = ["January","Febuary","March","April","May","June","July","August","September","October","November","December"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|Month|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "Month": return month[d.getMonth()];;
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};


 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


function nowdate() {
	return new Date().format("yyyyMMddHHmmss");
}


// format 2016-01-25 15:00:00
function getDate(_d) {
	var ye = _d.substring(0,4);
	var mo = _d.substring(5,7);
	var da = _d.substring(8,10);
	var hh = _d.substring(11,13);
	var mi = _d.substring(14,16);
	var ss = _d.substring(17,19);
	return new Date(ye, mo-1, da, hh, mi, ss);
}

function ddate(now, wdate) {
	var _d = getDate(wdate);
	
	var d = now.getTime()-_d.getTime();
	
	d = Math.ceil(d / 1000); // 초
	if(d<60) return d+"초전";
	
	d = Math.ceil(d / 60); // 분
	if(d<60) return d+"분전";
	
	d = Math.ceil(d / 60); // 시간
	if(d<24) return d+"시간전";
	
	return _d.format("MM월 dd일");
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/gi, "");
}

// 사업자 등록번호 유효성 체크
function validation_registration_number(bizID) {
	
	// bizID는 숫자만 10자리로 해서 문자열로 넘긴다. 
    var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
    var tmpBizID, i, chkSum=0, c2, remander; 
    bizID = bizID.replace(/-/gi,''); 

    for (i=0; i<=7; i++) chkSum += checkID[i] * bizID.charAt(i); 
    c2 = "0" + (checkID[8] * bizID.charAt(8)); 
    c2 = c2.substring(c2.length - 2, c2.length); 
    chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
    remander = (10 - (chkSum % 10)) % 10 ; 

    if (Math.floor(bizID.charAt(9)) == remander) return true ; // OK! 
    return false; 
}


function check_register_number() {
	var number = $("input[name=comp_registration_number]").val();
	
	if(number.length==10) {
		var _number = number.substring(0,3)+"-"+number.substring(3,5)+"-"+number.substring(5);
		$("input[name=comp_registration_number]").val(_number);
		
		number = _number;
	}
	
	if(!validation_registration_number(number)) {
		alert("유효한 사업자 번호가 아닙니다.");
		return;
	}
	var data = {"number":number};
	
	call(data,{url:"/rest/join/check_dup_registration_number",call_back:function(out) {
		if(out=="OK") {
			$("#btn_check_registration_number").html("확인됨");
			_check_user_registration_number = true;
		} else {
			error("중복된 사업자번호입니다. 고객센터로 별도 문의부탁드립니다.");			
		}
	}})
}

function commify(n) {
	var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
	n += '';                          // 숫자를 문자열로 변환

	while (reg.test(n))
		n = n.replace(reg, '$1' + ',' + '$2');

	return n;
}

function setDatePicker(a) {
	$(a).datepicker({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년'
});
}

function openReference(partnerId, id) {
	var url = "/reference/"+partnerId+"/"+id;
	var name = 'reference';
	var width = 445;
	var height = 600;
	var scroll = true;
	openPopup(url,name,width,height,scroll);
}

function setFiles(applyId) {
	
	alert(applyId);
	
	var data = {};
	call(data,{"type":"GET",dataType:"json",url:"/rest/project/file/apply/"+applyId,call_back:function(json) {
		if(json['result']=='success') {
			if(json['data']) {
				var out = json['data'];
				
				if(out.length>0) {
					$('#files_'+applyId).show();
					var html = "";
					
					//alert(1);
					
					for(var i=0;i<out.length;i++) {
						//var url = '/files/apply/'+applyId+'/'+out[i]["name"];
						var url = '/files/project/'+applyId+'/'+out[i]["name"];
						//alert(url);
						html += "<a class='file' href='"+url+"'>"+out[i]["name"]+"</a><br>";
					}
					$('#files_'+applyId).find('span').html(html);
				}
			} else {
				//alert('등록된 파일이 없습니다.');
				console.log("no files");
			}
		} else {
			console.log("has error");
		}
	}});
}

function setFiles(pid, applyId) {
	var data = {};
	call(data,{"type":"GET",dataType:"json",url:"/rest/project/file/apply/"+applyId,call_back:function(json) {
		if(json['result']=='success') {
			if(json['data']) {
				var out = json['data'];
				
				if(out.length>0) {
					$('#files_'+applyId).show();
					var html = "";
					
					//alert(1);
					
					for(var i=0;i<out.length;i++) {
						//var url = '/files/apply/'+applyId+'/'+out[i]["name"];
						var url = '/files/project/'+pid+'/'+applyId+'/'+out[i]["name"];
						//alert(url);
						html += "<a class='file' href='"+url+"'>"+out[i]["name"]+"</a><br>";
					}
					$('#files_'+applyId).find('span').html(html);
				}
			} else {
				//alert('등록된 파일이 없습니다.');
				console.log("no files");
			}
		} else {
			console.log("has error");
		}
	}});
}


function setReference(partnerId, applyId) {
	var data = {};
	call(data,{"type":"GET",dataType:"json",url:"/rest/project/reference/apply/"+applyId,call_back:function(json) {
		console.log(json);
		
		if(json['result']=='success') {
			if(json['data']) {
				var out = json['data'];
				
				if(out.length>0) {
					$('#reference_'+applyId).show();
					
					var html = "";
					
					for(var i=0;i<out.length;i++) {
						html += "<li style=\"background-image:url('/image/partner/"+out[i]['CR_PICTURE']+"')\" data-value="+out[i]['CR_ID']+"></li>";
						//alert(html);
					}
					
					$('#reference_'+applyId).find('ul').html(html);
					
					$('#reference_'+applyId+' li').click(function() {
						openReference(partnerId, $(this).attr("data-value"));
					});
				}
			} else {
				//alert('등록된 파일이 없습니다.');
				console.log("no files");
			}
		} else {
			console.log("has error");
		}
	}});
}


function ajaxindicatorstart(text)
{
	if(jQuery('body').find('#resultLoading').attr('id') != 'resultLoading'){
	jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="/images/ajax-loader.gif"><div>'+text+'</div></div><div class="bg"></div></div>');
	}

	jQuery('#resultLoading').css({
		'width':'100%',
		'height':'100%',
		'position':'fixed',
		'z-index':'10000000',
		'top':'0',
		'left':'0',
		'right':'0',
		'bottom':'0',
		'margin':'auto'
	});

	jQuery('#resultLoading .bg').css({
		'background':'#000000',
		'opacity':'0.7',
		'width':'100%',
		'height':'100%',
		'position':'absolute',
		'top':'0'
	});

	jQuery('#resultLoading>div:first').css({
		'width': '250px',
		'height':'75px',
		'text-align': 'center',
		'position': 'fixed',
		'top':'0',
		'left':'0',
		'right':'0',
		'bottom':'0',
		'margin':'auto',
		'font-size':'16px',
		'z-index':'10',
		'color':'#ffffff'

	});

    jQuery('#resultLoading .bg').height('100%');
       jQuery('#resultLoading').fadeIn(300);
    jQuery('body').css('cursor', 'wait');
}


function ajaxindicatorstop()
{
    jQuery('#resultLoading .bg').height('100%');
       jQuery('#resultLoading').fadeOut(300);
    jQuery('body').css('cursor', 'default');
}

/* 팝업 관련 */
	function openWin( winName ) {  
	   var blnCookie    = getCookie( winName );  
	   var obj = eval( "window." + winName );  
	   if( !blnCookie ) {  
	       obj.style.display = "block";  
	   }  
	   //if($(".visual_a").length) $(".visual_a").cycle('pause');
	}  
	  
	// 창닫기  
	function closeWin(winName, expiredays) {   
		if(expiredays>0) setCookie( winName, "done" , expiredays);   
	   var obj = eval( "window." + winName );  
	   obj.style.display = "none";  
	}  
	function closeWinAt00(winName, expiredays) {   
	   setCookieAt00( winName, "done" , expiredays);   
	   var obj = eval( "window." + winName );  
	   obj.style.display = "none";  
	}  
	  
	// 쿠키 가져오기  
	function getCookie( name ) {  
	   var nameOfCookie = name + "=";  
	   var x = 0;  
	   while ( x <= document.cookie.length )  
	   {  
	       var y = (x+nameOfCookie.length);  
	       if ( document.cookie.substring( x, y ) == nameOfCookie ) {  
	           if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )  
	               endOfCookie = document.cookie.length;  
	           return unescape( document.cookie.substring( y, endOfCookie ) );  
	       }  
	       x = document.cookie.indexOf( " ", x ) + 1;  
	       if ( x == 0 )  
	           break;  
	   }  
	   return "";  
	}  
	  
	  
	// 24시간 기준 쿠키 설정하기  
	// expiredays 후의 클릭한 시간까지 쿠키 설정  
	function setCookie( name, value, expiredays ) {   
	   var todayDate = new Date();   
	   todayDate.setDate( todayDate.getDate() + expiredays );   
	   document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
	}  
	  
	// 00:00 시 기준 쿠키 설정하기  
	// expiredays 의 새벽  00:00:00 까지 쿠키 설정  
	function setCookieAt00( name, value, expiredays ) {   
	    var todayDate = new Date();   
	    todayDate = new Date(parseInt(todayDate.getTime() / 86400000) * 86400000 + 54000000);  
	    if ( todayDate > new Date() )  
	    {  
	    expiredays = expiredays - 1;  
	    }  
	    todayDate.setDate( todayDate.getDate() + expiredays );   
	     document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
	} 
	
function chkWorkNumb(strNumb)
{
    strNumb = Replace(strNumb,"-");
    if (strNumb.length != 10) {
        alert("사업자등록번호가 잘못되었습니다.");
        return false;
    }
    
    sumMod = 0;
    sumMod += parseInt(strNumb.substring(0,1));
    sumMod += parseInt(strNumb.substring(1,2)) * 3 % 10;
    sumMod += parseInt(strNumb.substring(2,3)) * 7 % 10;
    sumMod += parseInt(strNumb.substring(3,4)) * 1 % 10;
    sumMod += parseInt(strNumb.substring(4,5)) * 3 % 10;
    sumMod += parseInt(strNumb.substring(5,6)) * 7 % 10;
    sumMod += parseInt(strNumb.substring(6,7)) * 1 % 10;
    sumMod += parseInt(strNumb.substring(7,8)) * 3 % 10;
    sumMod += Math.floor(parseInt(strNumb.substring(8,9)) * 5 / 10);
    sumMod += parseInt(strNumb.substring(8,9)) * 5 % 10;
    sumMod += parseInt(strNumb.substring(9,10));
    
    if (sumMod % 10 != 0) {
        alert("사업자등록번호가 잘못되었습니다.");
        return false;
    }
    return true;
}

function Replace(strString, strChar) {
    var strTmp = "";
    for (i = 0; i< strString.length; i++) {
        if (strString.charAt(i) != strChar) {
            strTmp = strTmp + strString.charAt(i);
        }
    }
    return strTmp;
} 

//공통 SNS API
function sendSns(sns, url, txt){
    var o;
    var _url = encodeURIComponent(url);
    var _txt = encodeURIComponent(txt);
    var _br  = encodeURIComponent('\r\n');
 
    switch(sns){
    	//페이스북
        case 'facebook':
            o = {
                method:'popup',
                url:'http://www.facebook.com/sharer/sharer.php?u=' + _url
            };
            break;
            
        //트위터
        case 'twitter':
            o = {
                method:'popup',
                url:'http://twitter.com/intent/tweet?text=' + _txt + '&url=' + _url
            };
            break;
            
        //미투데이
        case 'me2day':
            o = {
                method:'popup',
                url:'http://me2day.net/posts/new?new_post[body]=' + _txt + _br + _url + '&new_post[tags]=epiloum'
            };
            break;
 
        //카카오톡
        case 'kakaotalk':
            o = {
                method:'web2app',
                param:'sendurl?msg=' + _txt + '&url=' + _url + '&type=link&apiver=2.0.1&appver=2.0&appid=dev.epiloum.net&appname=' + encodeURIComponent('Epiloum 개발노트'),
                a_store:'itms-apps://itunes.apple.com/app/id362057947?mt=8',
                g_store:'market://details?id=com.kakao.talk',
                a_proto:'kakaolink://',
                g_proto:'scheme=kakaolink;package=com.kakao.talk'
            };
            break;
            
        //카카오스토리
        case 'kakaostory':
            o = {
                method:'web2app',
                param:'posting?post=' + _txt + _br + _url + '&apiver=1.0&appver=2.0&appid=dev.epiloum.net&appname=' + encodeURIComponent('Epiloum 개발노트'),
                a_store:'itms-apps://itunes.apple.com/app/id486244601?mt=8',
                g_store:'market://details?id=com.kakao.story',
                a_proto:'storylink://',
                g_proto:'scheme=kakaolink;package=com.kakao.story'
            };
            break;
            
        //밴드
        case 'band':
            o = {
                method:'web2app',
                param:'create/post?text=' + _txt + _br + _url,
                a_store:'itms-apps://itunes.apple.com/app/id542613198?mt=8',
                g_store:'market://details?id=com.nhn.android.band',
                a_proto:'bandapp://',
                g_proto:'scheme=bandapp;package=com.nhn.android.band'
            };
            break;
            
        //구글 플러스
        case 'googleplus':
            o = {
                method:'web2app',
                param:'create/post?text=' + _txt + _br + _url,
                a_store:'itms-apps://itunes.apple.com/app/id542613198?mt=8',
                g_store:'market://details?id=com.nhn.android.band',
                a_proto:'bandapp://',
                g_proto:'scheme=bandapp;package=com.nhn.android.band'
            };
            break; 
        
        //네이버
        case 'naver':
            o = {
        		method : 'popup',
	            url    : "http://share.naver.com/web/shareView.nhn?url=" + encodeURI(_url) + "&title=" + encodeURI(_txt)
            };
            break; 
            
        default:
            alert('지원하지 않는 SNS입니다.');
            return false;
    }
 
    switch(o.method){
    	case 'popup':
            window.open(o.url);
            break;
 
        case 'web2app':
        if( navigator.userAgent.match(/android/i) ){
            // Android
            setTimeout(function(){ location.href = 'intent://' + o.param + '#Intent;' + o.g_proto + ';end'}, 100);
            
        } else if(navigator.userAgent.match(/(iphone)|(ipod)|(ipad)/i)) {
            // Apple
            setTimeout(function(){ location.href = o.a_store; }, 200);          
            setTimeout(function(){ location.href = o.a_proto + o.param }, 100);
            
        } else {
            alert('이 기능은 모바일에서만 사용할 수 있습니다.');
        }
        
        break;
    }
}

// 카카오톡 공유하기
function sendKakaoTalk(pId, pUrl, pTitle, pUrlImage, pTitleImage){
//<![CDATA[
	
/*
	// 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('702309762b3bda814e40322080ebb314');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#'+pId,
      success: function(authObj) {
        // 로그인 성공시, API를 호출합니다.
        Kakao.API.request({
          url: '/v1/user/me',
          success: function(res) {
            alert(JSON.stringify(res));
          },
          fail: function(error) {
            alert(JSON.stringify(error));
          }
        });
      },
      fail: function(err) {
        alert(JSON.stringify(err));
      }
    });
*/
	
	  
    
  
	/*
	Kakao.Link.sendTalkLink({
		label: pTitle,
		image: {
			src    : pUrl,
			width  : '300',
			height : '200'
		},
		webButton: {
			text : pTitleImage,
			url  : pUrlImage // 앱 설정의 웹 플랫폼에 등록한 도메인의 URL이어야 합니다.
		}
	});
	*/
//]]>	
}

// 카카오스토리 공유하기
 function shareStory( pUrl, pTitle ){
	 Kakao.Story.share({
		 url  : pUrl, text : pTitle
	 });
}

// send to SNS
function fn_common_sns( sns, strTitle, strURL, imagePath ){
	var snsArray = new Array();
    var strMsg   = strTitle + " " + strURL;
    var image    = imagePath;

    //트위터
    snsArray['twitter'] = "http://twitter.com/home?status=" + encodeURIComponent(strTitle) + ' ' + encodeURIComponent(strURL);
    
    //페이스북
    snsArray['facebook'] = "http://www.facebook.com/share.php?u=" + encodeURIComponent(strURL);
    
    //프린터리스트
    snsArray['pinterest'] = "http://www.pinterest.com/pin/create/button/?url=" + encodeURIComponent(strURL) + "&media=" + image + "&description=" + encodeURIComponent(strTitle);

    //네이버 밴드
    snsArray['band'] = "http://band.us/plugin/share?body=" + encodeURIComponent(strTitle) + "  " + encodeURIComponent(strURL) + "&route=" + encodeURIComponent(strURL);

    //네이버 블로그
    snsArray['blog'] = "http://blog.naver.com/openapi/share?url=" + encodeURIComponent(strURL) + "&title=" + encodeURIComponent(strTitle);

    //라인
    snsArray['line'] = "http://line.me/R/msg/text/?" + encodeURIComponent(strTitle) + " " + encodeURIComponent(strURL);

    //폴라
    snsArray['pholar'] = "http://www.pholar.co/spi/rephol?url=" + encodeURIComponent(strURL) + "&title=" + encodeURIComponent(strTitle);

    //구글플러스
    snsArray['google'] = "https://plus.google.com/share?url=" + encodeURIComponent(strURL) + "&t=" + encodeURIComponent(strTitle);

    //네이버
    snsArray['naver'] = "http://share.naver.com/web/shareView.nhn?url=" + encodeURI(strURL) + "&title=" + encodeURI(strTitle);
    
    window.open(snsArray[sns],'sns',"width=800,height=550,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=no");
}

//URL 문자열 복사
function copy_clip(url){
    var IE = (document.all) ? true : false;

    if(IE){
    	window.clipboardData.setData("Text", url);
        alert("이 글의 단축url이 클립보드에 복사되었습니다.");

    } else {
        temp = prompt("이 글의 단축url입니다. Ctrl+C를 눌러 클립보드로 복사하세요", url);
    }
}

//사용 기기 체크 PC, 스마트폰, 태블릿
function fn_user_device_type(){
	
	var deviceName = "other";
	
	//스마트폰일 때 실행 될 스크립트
	if(navigator.userAgent.match(/Mobile|iP(hone|od)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)){
		deviceName = "phone";
		
	//모바일(스마트폰+태블릿)일 때 실행 될 스크립트
	} else if(navigator.userAgent.match(/Android|Mobile|iP(hone|od|ad)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)){
		deviceName = "tablet";
		
	//PC에서만 실행 될 스크립트
	} else if(!navigator.userAgent.match(/Android|Mobile|iP(hone|od|ad)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)){
		deviceName = "desktop";
		
	//태블릿,PC에서만 실행 될 스크립트
	} else if(!navigator.userAgent.match(/Mobile|iP(hone|od)|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/)){
		deviceName = "tablet";
	}
	
	return deviceName;
}


function f_price_name( price ){
	
		if ( price == "11")
            return "100만원 미만" ;
		else if (price == "12")
            return "200만원 미만" ;
		else if (price == "13")
            return "300만원 미만" ;
		else if(price == "21")
            return "500만원 미만" ;
		else if(price == "28")
            return "800만원 미만" ;
        else if(price == "31")
            return "1,000만원 미만" ;
        else if(price == "41")
            return "3,000만원 미만" ;
        else if(price == "51")
            return "5,000만원 미만" ;
        else if(price == "61")
            return "5,000만원 이상" ;
        else if(price == "67")
            return "7,000만원 미만" ;
        else if(price == "68")
            return "7,000만원 이상" ;
        else if(price == "71")
            return "1억원 이상" ;
        else if(price == "81")
            return "별도 협의" ;
        else
            return price ;
    
}

function fnMove(obj, position){
    var offset = $(obj).offset();
    $('html, body').animate({scrollTop : offset.top+position}, 400);
}

//특정 요소위치로 스크롤 자동이동하기
function fn_move($element, addRange, scrollSpeed){
	var offsetstr = $element.offset();
	$("html, body").animate({scrollTop:offsetstr.top + addRange}, scrollSpeed);
}


/**
 * 중복서브밋 방지
 * 
 * @returns {Boolean}
 */
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

//천단위 콤마찍기
function fn_moneycomma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

//천단위 콤마찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

//콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

//<input type="text" onkeyup="inputMoneyFormat(this)" />
//키 입력시마다 체크해서 천단위 콤마찍기
function inputMoneyFormat(obj) {
    obj.value = fn_moneycomma(uncomma(obj.value));
}

//Ajax 처리 공통
function common_ajax(param, fn_success){
	var item;
	
	$.ajax({
	  url: param.url,
	  data: param.data,
	  async : typeof param.async === 'undefined' ? true : param.async,
	  method: typeof param.method === 'undefined' ? "POST" : param.method,
	  dataType: typeof param.dataType === 'undefined' ? "JSON" : param.dataType,
	  success: function(data, status, xhr) {
		
		if( data.result === 'success' ){
		  item = data;
		  fn_success(item);
		} else if( data.result === 'exist' ){
		  alert('존재하는 데이터가 있습니다.');
		} else if( data.result == 'point' ){
       		alert("포인트가 부족하여 등록할수 없습니다.\n관리자에게 문의해주세요.");
		} else {
		  alert("처리중에 문제가 있었습니다.\n\n잠시후 다시 시도해주세요.");
		}
	  },
	  error: function(xhr) {
		console.log(xhr);
	  }
	});
	
	return item;
}

//그리드 리로드
function fn_grid_reload(){
	var grid = $("div#grid_paging").pqGrid('getInstance').grid;
	
	grid.refreshDataAndView();
}