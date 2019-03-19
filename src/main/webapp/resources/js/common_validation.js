/**
 * 
 */

var variousChecked = {
    /* 한글 입력체크 */
    NanChecked:function (str) {
        var strCode;
        for (i = 0; i < str.length; i++) {
            strCode = str.charCodeAt(i);
            if (strCode < 12593 || strCode > 12643 && strCode < 44032 || strCode > 55203) {
                alert("한글만 입력이 가능합니다.");
                return false;
            } else {
                return true;
            }
        }
    }
    /* 숫자입력체크 숫자면 true */
    ,NumberChecked:function (num, id, name) {
        var numCode;
        for (i = 0; i < num.length; i++) {
            numCode = num.charCodeAt(i);
            
            //numCode == 45 => - 
            if (numCode <= 57 && numCode >= 48 || numCode == 45) {
                //return true;
            } else {
                alert(name + "숫자만 입력이 가능합니다.");
                
                if( id != '' ){
                	$("#"+id).focus();
                }
                
                return false;
            }
        }
        return true;
    }
    /* 영문입력체크 영문이면 true */
    ,EglishChecked:function (str) {
        var strCode;
        for (i = 0; i < str.length; i++) {
            strCode = str.charCodeAt(i);
            if (strCode <= 90 && strCode >= 65 || strCode <= 122 && strCode >= 97) {
                return true;
            } else {
                alert("영문만 입력이 가능합니다.");
                return false;
            }
        }
    }
    /* 영문입력체크 대문자면 1 / 소문자명 2 / 영문이 아니면 false */
    ,CaseSensitive:function (str) {
        var strCode;
        for (i = 0; i < str.length; i++) {
            strCode = str.charCodeAt(i);
            if (strCode <= 90 && strCode >= 65) {
                return "1";
            } else if (strCode <= 122 && strCode >= 97) {
                return "2";
            } else {
                alert("영문이 아니거나 값이 없습니다.");
                return false;
            }
        }
    }
    /* 아이디 체크 첫글자 영문(소)이고 영문(소),숫자이여야 하고 8~16자 */
    ,IdChecked:function (str) {
        var regexp = /^[a-z][a-z0-9]{8,16}$/; 
        if (str.match(regexp) == str) {
            return true;
        } else {
            alert("아이디는 영문(소) 또는 숫자이여야 하고 8자이상 16자이하 이여야 하고 첫글자는 영문자로 시작하여야 합니다.");
            return false;
        }
    }
    /* 패스워드 체크 8~16자이여야하고 아이디가 포함되어서는 안되고 같은 글자가 4자이상이면 false */
    ,PasswordChecked:function (passStr1, passStr2, idStr) {
        var regexp1 = /(\w)\1\1\1/;
        if (passStr1 != passStr2) {
            alert("비밀번호 비밀번호 확인이 다릅니다."); return false;
        } else if (passStr1.length < 8 || passStr1.length > 16) {
            alert("8자 이상 16자 이하 이여야 합니다."); return false;
        } else if (passStr1.indexOf(idStr) > -1) {
            alert("비밀번호에 아이디가 포함되어 있어서는 안됩니다."); return false;
        } else if (passStr1.match(regexp1) != null) {
            alert("비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다."); return false;
        } else {
            return true;
        }
    }
    /* 빈 문자 체크 */
    ,IsEmpty:function (str) {
        var strCode;
        for (i = 0; i < str.length; i++) {
            strCode = str.charCodeAt(i);
            if (strCode == 32) {
                return true;
            } else {
                return false;
            }
        }
    }
    /* 주민번호 체크 */
    ,JuminNumber:function (number1, number2) {
        // 주민번호 검증공식 마지막 자리를 제외한 앞자리수를 규칙에 맞게 곱한다.
        // 123456-1234567
        // ****** ******
        // 234567 892345
        // 곱한 각각에 자리수를 더한다.
        // 더한수를 11 로 나눈 후 나눈 나머지 수를 구한다.
        // 나눈 나머지 수를 11에 뺀다.
        // 11 - (각각더한수%11)
        // 주민번호 마지막 번호와 일치하면 검증일치
		var totalNumber = 0;
        var num = 2;
		var juminNum = number1 + "" + number2;
        for (i = 0; i < juminNum.length-1; i++) {
			if(num > 9) {
				num = 2;
			}
            totalNumber = totalNumber + (parseInt(juminNum.charAt(i)) * num);
			num++;
        }
		totalNumber = 11 - (totalNumber%11);
		
        var mm      = number1.substring(2,4);
        var dd      = number1.substring(4,6);
        var gender  = number2.substring(0,1);
        if (number1 == "" || number2 == "") {
            alert("주민등록번호를 입력하세요.");
            return false;
        } else if (!this.NumberChecked(number1,'','') || !this.NumberChecked(number2,'','')) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else if (number1.length != 6 || number2.length != 7) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else if (parseInt(mm) < 1 || parseInt(mm) > 12) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else if (parseInt(dd) < 1 || parseInt(dd) > 31) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else if (parseInt(gender) > 4 || parseInt(gender) < 1) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else if (totalNumber != juminNum.charAt(juminNum.length-1)) {
            alert("유효하지 않은 주민등록번호 입니다.");
            return false;
        } else {
            return true;
        }
    }
    /* 사업자번호 체크 */
    ,BusinessNumber:function (str, id, name) {
		// 사업자번호 오류검증
		// 아래 공식으로 계산후 10의 배수가 나오면 검증일치
		var num = new Array();
			num[0] = 1;
			num[1] = 3;
			num[2] = 7;
			num[3] = 1;
			num[4] = 3;
			num[5] = 7;
			num[6] = 1;
			num[7] = 3;
			num[8] = 5;
		var totalNumber = 0;
		var _num		= 0;
		for (i = 0; i < str.length-1; i++) {
			_num = parseInt(str.charAt(i)) * num[i];
			_num = "" + _num;
			if (i < 8) {
				totalNumber = totalNumber + parseInt(_num.charAt(_num.length-1));
			} else {
				totalNumber = (_num.charAt(_num.length-2) == "") ? totalNumber + 0 : totalNumber + parseInt(_num.charAt(_num.length-2));
				totalNumber = totalNumber + parseInt(_num.charAt(_num.length-1));
			}
        }
		totalNumber = totalNumber + parseInt(str.charAt(str.length-1));
        var num1    = str.substring(0,3);
        var num2    = str.substring(3,5);
        var num3    = str.substring(5,10);
        if (str == "") {
            alert("사업자번호를 입력하세요.");
            $("#"+id).focus();
            return false;
        } else if (num1.length != 3 || num2.length != 2 || num3.length != 5) {
            alert("유효하지 않은 사업자 번호입니다.");
            $("#"+id).focus();
            return false;
        } else if (!this.NumberChecked(str,'',name)) {//숫자만
            //alert("유효하지 않은 사업자 번호입니다.");
            $("#"+id).focus();
            return false;
        } else if (totalNumber%10 != 0) {
            alert("유효하지 않은 사업자 번호입니다.");
            $("#"+id).focus();
            return false;
        } else {
            return true;
        }
    }
    /* 법인번호 체크 */
    ,CorporationNumber:function (str, id, name) {
        // 법인번호 오류검증 공식
        // 법인번호에서 마지막 자리를 제외한
        // 앞에 모든 자리수를 1과 2를 순차적으로 곱한다.
        // 예) 1234567890123
        //     ************
        //     121212121212
        //     각각 곱한 수를 모든 더하고 10으로 나눈 나머지 수를 구한다.
        //     (각각더한수 % 10)
        //     나눈 나머지 수와 법인번호 마지막 번호가 일치하면 검증일치  
        /*var totalNumber = 0;
        var num = 0;
        for (i = 0; i < str.length-1; i++) {
            if (((i + 1) % 2) == 0) {
                num = parseInt(str.charAt(i)) * 2;
            } else {
                num = parseInt(str.charAt(i)) * 1;
            }
            if (num > 0) {
                totalNumber = totalNumber + num;
            }
        }
		totalNumber = (totalNumber%10 < 10) ? totalNumber%10 : 0;*/
    	//유효성 로직 아래 내용으로 변경 , 1101112454041 정상번호인데 처리가 안됨
    	
    	
    	var as_Biz_no = String(str);
        var I_TEMP_SUM = 0;
        var I_CHK_DIGIT = 0;

        for(var index01 = 1; index01 < 13; index01++) {
            var i = index01 % 2;
            var j = 0;

            if (i == 1) {
                j = 1; 
                 
            } else if (i == 0) {
                j = 2;
                 
            }

            I_TEMP_SUM = I_TEMP_SUM + parseInt(as_Biz_no.substring(index01 - 1, index01), 10) * j;
        }

        I_CHK_DIGIT = I_TEMP_SUM % 10;
         
        if (I_CHK_DIGIT != 0) {
            I_CHK_DIGIT = 10 - I_CHK_DIGIT;
        }

        if (str == "") {
            alert("법인번호를 입력하세요.");
            $("#"+id).focus();
            return false;
        } else if (str.length != 13) {
            alert("유효하지 않은 법인 번호입니다.");
            $("#"+id).focus();
            return false;
        } else if (!this.NumberChecked(str,'',name)) {//숫자만
            //alert("유효하지 않은 법인 번호입니다.");
            $("#"+id).focus();
            return false;
        //} else if (totalNumber != str.charAt(str.length-1)) {
          //법인번호 유효성 변경, 2017.10.19    
        } else if (as_Biz_no.substring(12, 13) != String(I_CHK_DIGIT)) {
            alert("유효하지 않은 법인 번호입니다.");
            $("#"+id).focus();
            return false;
        } else {
            return true;
        }
    }
    /* 이메일 체크 */
    , EmailCheck : function (str, id, name) {
        // 이메일 체크
    	var reg_email = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if( !reg_email.test(str) ){
        	alert(name + "유효하지 않은 이메일 입니다.");
            $("#"+id).focus();
        	return false;
        }
        return true;
    }
    //특수문자 체크
    , SpecialLetterCheck : function(strobj){
		var re = /[~!@\#$%^&*\()\=+_'.`\-|,?<>:;\/\"\[\]\{\}\\]/gi;
		if( re.test(strobj) ){
			// 특수문자는 입력하실수 없습니다.
			alert("특수문자는 입력하실수 없습니다.");
			return false;
		}
		return true;
	}
    
}

