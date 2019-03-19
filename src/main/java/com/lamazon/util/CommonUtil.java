package com.lamazon.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Component
public class CommonUtil {

	/**
	 * 각 사이트 정보를 개발 편이상 로컬 및 테스트 서버, 라이브 서버로 변수 구분하여
	 * qubridge.properties 추가 하여 따로 관리 하도록 변경
	 * 2011-04-08 khj
	 */


	public static String OPURL = "http://www.officeplus.com";//CommonConfig.getOPURL();
	public static String SCURL = "http://www.schoolplus.co.kr";//CommonConfig.getSCURL();
	public static String SECURE = "http://secure.officeplus.com";//CommonConfig.getSECURE();

    public static String B2BURL = "http://tos.officeplus.com";//CommonConfig.getB2BURL();
	public static String PATHBASE = "/home/ybkim/docroot/images";//CommonConfig.getPATHBASE();// back, front을 모두 포함하는 path base

	public static String FILEURL = "http://www.officeplus.com";//CommonConfig.getFILEURL();
	public static String HITTINGURL ="www.hitting.co.kr";//CommonConfig.getHITTINGURL();

	// 이지페이 관련 상수
	public String tid60 = "05109389";//CommonConfig.getTid60();
	public String pggw = "gw.easypay.co.kr";
	public String pgjs = "pg.easypay.co.kr";


	/*
	public static String OPURL = ConfigReader.getProperties("OPURL");//CommonConfig.getOPURL();
	public static String SCURL = ConfigReader.getProperties("SCURL");//CommonConfig.getSCURL();
	public static String SECURE = ConfigReader.getProperties("SECURE");//CommonConfig.getSECURE();

    public static String B2BURL = ConfigReader.getProperties("B2BURL");//CommonConfig.getB2BURL();
	public static String PATHBASE = ConfigReader.getProperties("PATHBASE");//CommonConfig.getPATHBASE();// back, front을 모두 포함하는 path base

	public static String FILEURL = ConfigReader.getProperties("FILEURL");//CommonConfig.getFILEURL();
	public static String HITTINGURL = ConfigReader.getProperties("HITTINGURL");//CommonConfig.getHITTINGURL();

	// 이지페이 관련 상수
	public String tid60 = ConfigReader.getProperties("tid60");//CommonConfig.getTid60();
	public String pggw = ConfigReader.getProperties("pggw");
	public String pgjs = ConfigReader.getProperties("pgjs");
	*/

	Calendar calIns = Calendar.getInstance();

   /***************************************************************
   	KSC5601 -> 8859_1 , 8859_1 ->euc-kr <br>
   	@param String convert 할 변수 입니다
   ****************************************************************/
   public static String toEng (String ko)  {

		if (ko == null) {
			return null;
		}

		try {
			return new String(ko.getBytes("KSC5601"),"8859_1");
		} catch(Exception e) {
			e.printStackTrace();
			return ko;
		}
	}

	/***************************************************************
   	8859_1 ->euc-kr <br>
   	@param String convert 할 변수

	****************************************************************/

	public static String toKor (String en) {
		if (en == null) {
			return null;
		}
		try {
			return new String (en.getBytes("8859_1"), "euc-kr");
		} catch(Exception e) {
			e.printStackTrace();
			return en;

		}
	}

	/************************************************
	 해당 문자가 Null 인지 아닌지를 판단해준다. <br>
	 워낙 많이 쓰는 구문이라서 따로 함수로 정의놓고 쓰면 편하다. <br><br>

	 ex) <br>
	 if(request.getParameter("a") == null || request.getParameter("a").equals("") ) <br>

	 -> if(util.isCheckNull(request.getParameter("a"));<br>

	 @param String Sring
	 @return Null 인경우 true, 아니면 false
	*************************************************/
	public boolean isCheckNull(String str) {
		if(str == null || str.equals("") || str.equals("null")) {
			return true;
		} else {
			return false;
		}
	}

    /***********************************************************************
    * 문자를 null, "null", " " 일경우 check하여 ""으로 리턴하는 메쏘드
    * @param String str - null이 의심되는 String
    * @return null객체,null문자,Empty String은 Empty String으로 리턴 <br>
    * @return 정상적인 String은 trim()해서 리턴
    * <p><B>기타:</B>
    * <ul>
    * <li> 작성자 : 나
    * </ul>
    ************************************************************************/
    public String fixNull(String str) {
        if( null == str) {
            return "";
        } else {
            if("null".equals(str)) {
            return "";
            } else {
                return str.trim();
            }
        }
    }

    public String fixNull2(String str) {
        if( null == str) {
            return "&nbsp;";
        } else {
            if("null".equals(str)) {
            return "&nbsp;";
            } else {
                return str.trim();
            }
        }
    }

    public String fixNull3(String str) {
        if( null == str || "".equals(str) ) {
            return " ";
        } else {
            if("null".equals(str)) {
            return " ";
            } else {
                return str.trim();
            }
        }
    }

	/************************************************
	천단위별로 , 를 찍어준다. <br>
	1888 -> 1,888 <br>

	@param int istr 입력받은 int
	@return 숫자포맷으로 변경

	************************************************/
	public String makeIntFormat(int istr) {
		NumberFormat nf = NumberFormat.getInstance();
		return nf.format( istr);
	}

	public String makeIntFormat(long istr) {
		NumberFormat nf = NumberFormat.getInstance();
		return nf.format( istr);
	}

	/************************************************
	천단위별로 , 를 찍어준다. <br>
	1888 -> 1,888 <br>

	@param String  istr 입력받은 int
	@return 숫자포맷으로 변경
	************************************************/
	public String makeIntFormat(String istr) {
                int istri = Integer.parseInt(istr);
                NumberFormat nf = NumberFormat.getInstance();
                return nf.format( istri);
   }

	/***************************************************************

	String 을  받아와서 원하는 길이의 String 으로 변환하는 함수 <br>
	ex) addString("dir", 10, "Q", "f") -> dirfffffff <br>

	@param  String  원본 String
	@param  String  최종길이
	@param  String  붙일 String
	@param  String  앞뒤 (앞이면 f, 뒤면 b)
	@return 결과값
	***************************************************************/
	public String addString (String sourceStr, int chkLen, String stringAddName, String position){

			int len = sourceStr.length();

			if (len < chkLen){

				for(int i=1; i<=(chkLen-len); i++) {
					if(position.equals("f"))
						sourceStr = stringAddName + sourceStr;
					else if(position.equals("b"))
						sourceStr = sourceStr + stringAddName;
				}
			}
			return sourceStr;
	}


	 public String getShortString( String orig, int length){

		if(orig!=null && !orig.equals("")){
		    byte[] byteString = orig.getBytes();
		    if (byteString.length <= length){
		      return orig;
		    }
		    else{
		      int minusByteCount = 0;
		      for (int i = 0; i < length; i++){
		        minusByteCount += (byteString[i] < 0) ? 1 : 0;
		      }

		      if (minusByteCount % 2 != 0){
		        length--;
		      }

		      return new String(byteString, 0, length) + "...";
		    }
		}else{
			return "&nbsp;";
		}

	 }

	 public String getShortString( String orig, int length, String endString){

		if(orig!=null && !orig.equals("")){
		    byte[] byteString = orig.getBytes();
		    if (byteString.length <= length){
		      return orig;
		    }
		    else{
		      int minusByteCount = 0;
		      for (int i = 0; i < length; i++){
		        minusByteCount += (byteString[i] < 0) ? 1 : 0;
		      }

		      if (minusByteCount % 2 != 0){
		        length--;
		      }

		      return new String(byteString, 0, length) + endString;
		    }
		}else{
			return "&nbsp;";
		}

	 }


	/*************************************************************
		현재 년도 가져오기
	 	@return yyyy
	*************************************************************/
	public int getDate(){
		Calendar now = Calendar.getInstance()	;
		int date = now.get(Calendar.DATE)		;
		return date								;
	}

	public String get_Date(){
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		int dd = cal.get(Calendar.DATE);
		int yy = cal.get(Calendar.YEAR);
		String yy_s = String.valueOf(yy);
		int mm = cal.get(Calendar.MONTH) + 1;

		String mm_s = "", dd_s = "";
		mm_s = String.valueOf(mm);
		dd_s = String.valueOf(dd);

		String date_value = mm_s + "월 " +  dd_s + "일";

		return date_value;
	}
	/*************************************************************
		현재 월 가져오기
	 	@return mm
	*************************************************************/
	public int getMonth(){
		Calendar now = Calendar.getInstance()	;
		int month = now.get(Calendar.MONTH)+1		;
		return month;
	}

	//january , february , march , april , may , june , july , august , september , october , november , december
	public String getMonth(int month){
        String[] arrMonth = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec","und"};
        return arrMonth[month];
	}
	/*************************************************************
		현재 일 가져오기
	 	@return dd
	*************************************************************/
	public int getYear(){
		Calendar now = Calendar.getInstance()	;
		int year = now.get(Calendar.YEAR)		;
		return year ;
	}

    /*************************************************************
		현재 시간 가져오기
	 	@return hh
	*************************************************************/
	public int getHour(){
		Calendar now = Calendar.getInstance()	;
		int hour = now.get(Calendar.HOUR_OF_DAY)		;
		return hour ;
	}

	/*************************************************************
		현재 분 가져오기
	 	@return mi
	*************************************************************/
	public int getMi(){
		Calendar now = Calendar.getInstance()	;
		int mi = now.get(Calendar.MINUTE)		;
		return mi ;
	}


	/***************************************************************
	문자열에서 특정한 문자를 찾아서 원하는 문자로 치환한다. <br>
	사용법 : replace(내용,'바꿀 문자','바뀔 문자'); <br>
			  	String src = "DO D HAVE DOG?"; <br>
				String result = replace(src,"O","X2"); <br>
				out.println(result); <br><br>

				결과 : DX2 D HAVE DX2G? <br>


	@param String 치환될 문자를 포함하고 있는 문자열
	@param String 치환될 문자
	@param String 치환할 문자
	@return 치환된 문자열
	***************************************************************/

	public static String strReplace(String src, String oldstr, String newstr){
        if (src == null)
            return null;

        StringBuffer dest = new StringBuffer("");
        int  len = oldstr.length();
        int  srclen = src.length();
        int  pos = 0;
        int  oldpos = 0;

        while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
            dest.append(src.substring(oldpos, pos));
            dest.append(newstr);
            oldpos = pos + len;
        }

        if (oldpos < srclen)
            dest.append(src.substring(oldpos, srclen));

        return dest.toString();
    }

	/****************************************************************
	문자를 숫자로 바꾸는 함수 <br>

	@param int 문자로 변환하고자 하는 int
	@return 변환된 String
	***************************************************************/

	public String cStr(int val) {
	  Integer VAL = new Integer(val);
	  return VAL.toString();
	}

	/****************************************************************
	숫자를 문자열로 바꾸는 함수 <br>

	@param String String 으로 변환하고자 하는 int
	@return 변환된 int
	***************************************************************/
    public int cInt(String val) {
      Integer VAL = new Integer(val);
	  return VAL.intValue();
	}

	/****************************************************************
	요일을 리턴한다.

	@param String String 으로 변환하고자 하는 int
	@return 변환된 int(1 일 , 2 월, 3 화...)
	***************************************************************/
	public int weekString(int yyyy, int mm, int dd) {
		calIns.set( yyyy, mm-1, dd );

		return calIns.get( Calendar.DAY_OF_WEEK);

	}

	/****************************************************************
	주를  리턴한다.
	@param String String 으로 변환하고자 하는 int
	@return 변환된 int(1 일 , 2 월, 3 화...)
	***************************************************************/
	public int weekString2(int yyyy, int mm, int dd) {
		calIns.set( yyyy, mm-1, dd );

		return calIns.get( Calendar.WEEK_OF_MONTH);

	}
	/*************************************************************
	디렉토리리스트를 Vecotr 로 넘긴다.<br>

	@param String  시작하는 디렉토리
	@return 디렉토리를 가지고 있는 Vector
	*************************************************************/
	public Vector getDirectoryList(String startDirect) {
		Vector vListDirectory = new Vector();

		if(startDirect.lastIndexOf("/") != 1 && startDirect.length() !=1) {
			startDirect = startDirect + "/";
		}

		String[] fList = {""};                  		// file&directory 를 저장
		File f= new File(startDirect);
		fList = f.list();

		for(int i=0; i<fList.length; i++) {
			 // 실제 디렉토리인지 검사해서 디렉토리만 Vector 에 넣는다.
			 File fd = new File(startDirect + fList[i]);
			 if(fd.isDirectory()) {
				if(!startDirect.equals("/")) {
			 		vListDirectory.addElement(startDirect.substring(0,startDirect.length()-1)+fList[i]);
				} else {
			 		vListDirectory.addElement(startDirect+fList[i]);
				}
			 }
		}
		return vListDirectory;
	}

	 /**************************************************************
	 FILE READ <br>

	 @param String 	찾기 시작할 path
	 @param String  	파일 네임(path 포함)
	 @return 결과String (에러시 "" 리턴)
	 **************************************************************/

	public String fileRead(String htmlRootPath, String fname) {
	try {
		String retString="";
		fname = htmlRootPath + fname;
 		BufferedReader in = new BufferedReader(new FileReader(fname));
	 	String temp = null;

			 while((temp = in.readLine()) !=null){
					 retString += temp +"\n";
			 }
		return retString;
	} catch(IOException e){
		e.printStackTrace();
		return "";

	}
	}


	/*************************************************************
	파일삭제 <br>

	@param 삭제될 디렉토리
	@return 삭제성공시 true, 실패시 false
	*************************************************************/
	public boolean deleteFile(String delfilename) {

		try {
			File f= new File(delfilename);
			f.delete();
			return true;
		} catch(Exception e) {

			e.printStackTrace();
			return false;

		}
	}

	/*************************************************************
	파일이름바꾸기 , 이동 <br>

	@param  String 원본파일경로
	@param  String 원하는 파일경로
	@return 성공시 true, 실패 false

	*************************************************************/
	public boolean moveFile(String srcfilename, String targetfilename) {

		try {
			File fs= new File(srcfilename);
			File ft = new File(targetfilename);

			fs.renameTo(ft);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


    /************************************************
	  파일의 읽어 String 을 돌려주는 Method
	  @param	String 파일의 경로
	  @return 파일의 내용
	 *************************************************/

    public String tmplFile (String Path) {

        String tmplString  = "" , line = "";

        try {
          File tmpl  = new File(Path);
    	    BufferedReader in  = new BufferedReader(new InputStreamReader(new FileInputStream(tmpl), "KSC5601"));

    	    while ((line = in.readLine()) != null) {
    	        tmplString += line;
    	    }
    	}
    	catch (IOException e) {
    	    e.printStackTrace();
    	}
    	return tmplString;
    }

    /************************************************
	 특정 경로에 존재하는 FileList를 가져옴
	  @param	String Path 경로
	  @return 디렉토리를 String 배열로 return
	 ************************************************/
    public String[] tmplFileList(String Path) {
        String [] tmpList = null;
        try {
            File dir = new File(Path);
            if(dir.isDirectory())
    	        tmpList = dir.list();
    	}
    	catch (Exception e) {
			e.printStackTrace();
    	}
	    return tmpList;
	}

	/************************************************
	앞뒤에 붙어있는 String 를 제거해주는 함수 <br>

	ex) removeSlash("/ABCDE/FGHIJKL/DDD/", "/") <br>
		 -> ABCDE/FGHIJKL/DDD


	@param String 제거할 문자를 가지고 있는 문자열
	@param String 제거될 문자열
	@return 결과 문자열
	************************************************/
	public String removeSlash(String srcString, String reString) {
		if(srcString.trim().startsWith(reString)){
			srcString = srcString.substring(1);
		}

		if(srcString.trim().endsWith("reString")){
			srcString = srcString.substring(0, srcString.length()-1);
		}

		return srcString;
	}


	/************************************************
	 String 을 byte 로 계산해서 정확히 length 를 계산해준다.  <br>
	 @param String 해당 string
	 @param String 원하는 byte 수
	 @param String 만약 짤랐을 경우 뒤에 붙여줄 스트링
	 @return 결과 문자열
	************************************************/

	public String cutString(String str, int blength, String laststr) {
		try {

			if(str.getBytes().length <= blength){
					return str;

	    	} else {
	    		String restr = new String(str.getBytes("KSC5601"), 0, blength);

	    		if(restr.length() != 0){
	    			restr = restr + laststr;
	    			return restr;
	    		} else {
	    			restr = new String(str.getBytes("KSC5601"), 0, blength+1);
	    			restr = restr + laststr;
	    			return restr;
	    		}
	  		}
	  	} catch(UnsupportedEncodingException e) {
	  		e.printStackTrace();
	  		return str;
	  	}
	}

	/************************************************
	 String 을 length 로 계산 <br>
	 @param String 해당 string
	 @param String 원하는 byte 수
	 @param String 만약 짤랐을 경우 뒤에 붙여줄 스트링
	 @return 결과 문자열
	************************************************/
	public String cutStringLength(String str, int blength, String laststr) {
		try {

			if(str.length() <= blength){
					return str;

	    	} else {
	    		String restr = str.substring(0, blength);
	    		return restr + laststr;
	  		}
	  	//} catch(UnsupportedEncodingException e) {
		} catch(Exception e) {
	  		e.printStackTrace();
	  		return str;
	  	}
	}

	/************************************************
	 list box 에서 원래 파라미터 값 유지   <br>
	 	 @return 결과 문자열
	************************************************/

	public String setListD ( String src , String tar ) {
		String returnv = "";
		if ( isCheckNull ( src ) && isCheckNull( tar ) ) {
			returnv = " selected ";
		} else if ( !isCheckNull ( src ) && !isCheckNull( tar ) ) {
			if ( src.equals( tar ) )
				returnv = " selected ";
		}

		return returnv;
	}

	public String setCheckedD ( String src , String tar ) {
		String returnv = "";
		if ( isCheckNull ( src ) && isCheckNull( tar ) ) {
			returnv = " checked ";
		} else if ( !isCheckNull ( src ) && !isCheckNull( tar ) ) {
			if ( src.equals( tar ) )
				returnv = " checked ";
		}
		return returnv;
	}

	public boolean writeFile ( String fName, String content ) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fName));
			if (content != null) {
				out.write(content, 0, content.length());
				out.close();
			}
			return true;
		} catch(IOException e) {
			System.out.println ( e );
			return false;
		}
	}

	/****
	YYYYMMDD 형식을 받아서 원하는 형식으로 리턴
	**/
	public String cvtDate ( String YYYYMMDD, String frm ) {

		try {
			String r = ""; // return string
			if ( frm.equals("YY-MM-DD") ) {
				r= YYYYMMDD.substring(2,4)+"-"+YYYYMMDD.substring(4,6)+"-"+YYYYMMDD.substring(6) ;
			}else if ( frm.equals("YYYY-MM-DD") ) {
				r= YYYYMMDD.substring(0,4)+"-"+YYYYMMDD.substring(4,6)+"-"+YYYYMMDD.substring(6) ;
			}else if ( frm.equals("YYYY/MM/DD") ) {
				r= YYYYMMDD.substring(0,4)+"/"+YYYYMMDD.substring(4,6)+"/"+YYYYMMDD.substring(6) ;
			}else if ( frm.equals("YYYY년MM월DD일") ) {
				r= YYYYMMDD.substring(0,4)+"년"+YYYYMMDD.substring(4,6)+"월"+YYYYMMDD.substring(6)+ "일" ;
			}

			return r;
		} catch(Exception e) {
			System.out.println ( e );
			return YYYYMMDD;
		}
	}

	// 변수가 넘어왔을때 | 를 분리해서 Vector 로 넘긴다.
     public Vector splitList( String v , String split) {
     	Vector ve = new Vector();

     	if ( v == null || v.equals("") ) {
    	} else {
         	StringTokenizer st = new StringTokenizer( v , split );
         	while (st.hasMoreTokens()) {
      		   ve.add( st.nextToken() );
     		}
     	}
     	return ve ;
     }

	/************************************************
	 sku 를 받아서 유아, op 경로까지 return<br>
	 	 @return 결과 문자열
	************************************************/

	public String getImgPath ( String sku , String as ) {
		String img = "";
		String path = "";
    	if ( sku == null ) {
    		return "";
    	} else {
    		//String fol = sku.toUpperCase().trim().substring(0,1);
    		String fol = sku.toUpperCase().trim().substring(0,1)+"/"+sku.toUpperCase().trim().substring(0,2)+"/"+sku.toUpperCase().trim().substring(0,3);
			//path = "http://www.officeplus.com/images/prdimg/"+fol+"/";
			path = "/images/prdimg/"+fol+"/";
    		img = path+sku+"_"+as+".gif";

    		// gif (기존 이미지 파일 형식) 먼저 확인
    		String chPath = "/images/prdimg/"+fol+"/"+sku+"_"+as+".gif";
    		if ( isFileExit(chPath) ) {
    			return img;
    		}
    		// gif 없을경우 jpg 확인
    		else{
        		chPath = "/images/prdimg/"+fol+"/"+sku+"_"+as+".jpg";
        		img = path+sku+"_"+as+".jpg";
        		if ( isFileExit(chPath) ) {
        			return img;
        		}
        		// gif, jpg 없는 경우 noimage.gif 리턴
        		else{
        			return "http://www.officeplus.com/images/UAcommon/noimage_"+as+".gif" ;
        		}
    		}
    	}
	}

    public boolean isFileExit( String fileName ) {
        File f = new File( "/www"+fileName );
        return f.exists() ;
    }

	/*
	* 현재 시점의 마지막 일을 구한다.
	*/
	public String getLastDay() {
		GregorianCalendar cld = new GregorianCalendar();
		return Integer.toString(cld.getActualMaximum ( cld.DAY_OF_MONTH ));
	}

	public String getYyyymmdd() {
		SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd");
		String yyyymmdd = sdfFile.format(new java.util.Date());
		return yyyymmdd;
	}

	public String getYyyymmdd(String divStr) {
		SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd");
		String yyyymmdd = sdfFile.format(new java.util.Date());
		yyyymmdd = yyyymmdd.substring(0,4)+divStr+yyyymmdd.substring(4,6)+divStr+yyyymmdd.substring(6,8);
		return yyyymmdd;
	}

	// 오늘을 기준으로 앞뒤 날을 구함
	public String getYyyymmdd(int expireDate) {
		Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, expireDate);

        String year = addString(String.valueOf(calendar.get(Calendar.YEAR)),4, "0", "f");
        String month = addString(String.valueOf(calendar.get(Calendar.MONTH)+1),2, "0", "f");
        String day = addString(String.valueOf(calendar.get(Calendar.DATE)),2, "0", "f");
		String yyyymmdd = year+month+day;
        return yyyymmdd;
    }

	// 유아 인경우 이름에 (유아) 가 들어간다.
	// 일반적으로 이름을 보여줄때는 (유아)를 빼야 하므로 빼서 보내는함수
	public String cvtUAName( String name ) {
		if ( name.length() <= 4) {
			return "";
		} else if ( name.indexOf("(유아)") == 0 ) {
			name = name.trim();
			return name.substring(4);
		} else {
			return name;
		}
	}

public boolean biggerDay ( String yyyymmdd , int valueAddDate ) {
   Calendar now = Calendar.getInstance();
   now.add( Calendar.DATE , valueAddDate );
   int year = now.get(Calendar.YEAR);
   int month = now.get(Calendar.MONTH) + 1 ;
   int day = now.get(Calendar.DATE);

   String y2 =  addString( Integer.toString(year), 2, "0", "f") +
                addString( Integer.toString(month), 2, "0", "f")  +
                addString( Integer.toString(day), 2, "0", "f") ;
   if ( Integer.parseInt( yyyymmdd )  >= Integer.parseInt( y2 ) ) {
        return false;
   } else {
        return true;
   }
}


String month1 = "";
String month2 = "";


private void setMonth(){

	String today_s = "";
	String next_month_s = "";
	Date now = new Date();
	Date next = new Date();

	Calendar today = Calendar.getInstance();
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	now = today.getTime();

	today_s = df.format( now );

	month1 = today_s;

	today.add(Calendar.MONTH,1);
	next = today.getTime();
	next_month_s = df.format( next );
	month2 = next_month_s;
}

public String getThisMonth(){
	setMonth();
	return 	month1;
}

public String getNextMonth(){
	return 	month2;
}

/************************************************
	 code 를 받아서 상품코드인지를 확인
	 @return 결과 문자열
	************************************************/

    public boolean isItemCode(String code) {
        StringBuffer buffer = new StringBuffer(code);

        // if third char is '-', remove it
        try {
            if (buffer.charAt(2) == '-')
                buffer.deleteCharAt(2);
        } catch (IndexOutOfBoundsException ie) {
            return false;
        }

        if (buffer.length() != 6)
            return false;
        // if first char is not between 'A' ~ 'Z', invalid..
        char first = Character.toUpperCase(buffer.charAt(0));
        char second = Character.toUpperCase(buffer.charAt(1));
        if ( ( first < 'A' || first > 'Z' )  && !Character.isDigit(first) )
            return false;

        if ( ! ((second >= 'A' && second <= 'Z') || Character.isDigit(second)))
            return false;

        char digits[] = new char[4];

        buffer.getChars(2, 6, digits, 0);
        try {
            Integer.parseInt(new String(digits));
        } catch (NumberFormatException nf) {
            return false;
        }

        return true;
    }

    /************************************************
	 t_item 의 muMonth 를 받아서 한글로 전환해서 넘김
	 @return 결과 문자열
	************************************************/
	 public String getMuMonthHan( String muMonth ) {
	      if ( isCheckNull( muMonth ) ) {
	        return "-";
	      } else {
	          muMonth = muMonth.trim();

    	      if ( muMonth.indexOf(":") == 0 )
    	        muMonth = muMonth.substring(1);

    	      if ( muMonth.lastIndexOf(":") == muMonth.length()-1 ) {
    	        muMonth = muMonth.substring( 0 , muMonth.length()-1 );
    	      }

              return strReplace( muMonth , ":", ", " );
          }
     }


	/************************************************
	 codeList 에서 code, qty 를 HashTable( itemCode, qty ) 로 리턴해준다.
	 @return 결과 문자열
	************************************************/
	 public Hashtable splitItemCode( String codeList ) {
	      Vector vc = splitList ( codeList, "/");
	      Hashtable ht = new Hashtable();
	      String temp = "";
	      for ( int i = 0 ; i <= vc.size(); i++ ) {
	        temp = vc.elementAt(i).toString();
            ht.put ( temp.substring(0, temp.indexOf(":") ) ,
                     temp.substring( temp.indexOf(":") ) );
	      }

	      return ht;
     }

    public String getMuMonth( String muList )
        throws IndexOutOfBoundsException , Exception
        {

        String sourceStr = "";
        String targetStr = "";
        String removeStr = "";
        String okStr = "";
        String c = "";

	    if ( muList.lastIndexOf("/") == muList.length()-1 )
            muList = muList.substring(0, muList.length()-1 );

        Vector vc = splitList(  strReplace(muList , "/", " / " ) , "/" );

        if ( vc.size() > 1 ) {
            // 첫번째는 fVc(first Vector) vector 로 따로 저장.
            Vector fVc = new Vector();
            StringTokenizer st = new StringTokenizer ( muList.substring( 0, muList.indexOf("/") )  , ":" ) ;

            while ( st.hasMoreTokens() ) {
                c = st.nextToken();
                fVc.add ( c );
            }

            for ( int i = 1 ; i< vc.size(); i++) {
                //if ( i > 0 ) {

                    for ( int i2 = 0 ; i2 < fVc.size(); i2++ ) {
                        targetStr = vc.elementAt(i).toString().trim();
                        sourceStr = fVc.elementAt(i2).toString().trim();
                        if ( targetStr.indexOf( ":"+ sourceStr + ":" ) < 0 && removeStr.indexOf( ":"+ sourceStr + ":" ) < 0  ) {
                            removeStr += ":"+ sourceStr +":";
                        }
                    }
                //}
            }

            for ( int i3 = 0 ; i3 < fVc.size(); i3++) {
                if ( removeStr.indexOf( ":"+ fVc.elementAt(i3).toString().trim() + ":" ) < 0 ) {
                    okStr += ":"+ fVc.elementAt(i3).toString().trim() + ":";
                }
            }
        // 넘어온게 하나면
        } else {
            okStr = muList;
        }
        return strReplace(okStr , "::", ":") ;
   }

    /*----------
        상품권번호를 생성한다.
        @date 2004.08.06
        @author 용범이
    ------------*/
    public String makeWebGiftNum()
        throws IndexOutOfBoundsException , Exception
        {
            long curTime =  System.currentTimeMillis() ;
            String cvtStr   = Math.random() + "|" + Math.random() + "|"  + Math.random() ;

            byte[] digest = MessageDigest.getInstance("MD5").digest( cvtStr.getBytes());
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
               s.append(Integer.toString((digest[i] & 0xf0) >> 4, 16));
               s.append(Integer.toString(digest[i] & 0x0f, 16));
            }
            return (s.toString().substring(0,16)).toUpperCase(); // 보기 좋게 대문자로 변경한다.
        }

   /*----------
        Domain 만 가져온다.
        -- 로그인시 CookieMgrNew 에서 사용
        -- 앞에 서브도메인만 빼는 역활
        @date 2005.06.10
        @author 용범이

    ------------*/
    public String getDomain( String bUrl )
    {
        return bUrl.substring( bUrl.indexOf(".")+1  ).trim() ;
    }

    /**
    * 변경하고자 하는 기간만큼 이동한 후 원하는 날짜를 가져온다.<br>
    *
    * @param    String cDate    입력일<br>
    * @param    String ymd      변경 구분 , yyyy(년),  mm(월), dd(일)<br>
    * @param    int    term     변경하고자 하는 기간<br>
    * @return   String rtnVal   변경된 일자<br>
	*/
	public String calDate(String cDate, String ymd, int term){

        int yyyyN, mmN, ddN;        //변경된 일자
        int yyyyO, mmO, ddO;        //입력한 일자

        if(cDate == null || cDate.trim().equals("")){
            cDate = getYyyymmdd();
        }

        Calendar tDate = null;
        tDate = Calendar.getInstance();

        yyyyO    = Integer.parseInt(cDate.substring(0,4));
        mmO      = Integer.parseInt(cDate.substring(4,6));
        ddO      = Integer.parseInt(cDate.substring(6,8));

        //cDate를 Calendar로 Casting
        tDate.set(Calendar.YEAR, yyyyO);
        tDate.set(Calendar.MONTH, mmO - 1);
        tDate.set(Calendar.DATE, ddO);

        String rtnVal = "";

        String mmS = "";
        String ddS = "";

        //원하는 일자로 변경
        if(ymd.equals("yyyy")){
            tDate.set(Calendar.YEAR, yyyyO + term);
        } else if (ymd.equals("mm")){
            tDate.set(Calendar.MONTH, mmO - 1  + term);
        } else if (ymd.equals("dd")){
            tDate.set(Calendar.DATE, ddO + term);
        }

        yyyyN    = tDate.get(Calendar.YEAR);
        mmN      = tDate.get(Calendar.MONTH)+1;
        ddN      = tDate.get(Calendar.DATE);

        if(mmN < 10){
            mmS = "0"+mmN;
        } else {
            mmS = mmN+"";
        }

        if(ddN < 10){
            ddS = "0"+ddN;
        } else {
            ddS = ddN+"";
        }

        rtnVal = yyyyN+""+mmS+ddS;

        return rtnVal;

    }//end method()

    /**
    * 현재 날짜와 밀리시간까지 가져온다.
    * @return 현재시간(yyyyMMddHHmmss) 스트링
    * <p><B>기타:</B>
    * <ul>
    * <li> 작성자 : 박태석
    * </ul>
    */
     public String getCurrentDateMiliTime() {

         SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         return timeFormat.format(new java.util.Date());
    }

     public String getCurrentDateMiliTime(String sFormat) {

         SimpleDateFormat timeFormat = new SimpleDateFormat(sFormat);

         return timeFormat.format(new java.util.Date());
    }

 	/**
      * 현재 날짜와 밀리시간까지 가져온다.
      * @return 현재시간(yyyyMMddHHmmss) 스트링
      * <p><B>기타:</B>
      * <ul>
      * </ul>
      */
	public String getCurrentDateMiliTime2() {

		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return timeFormat.format(new java.util.Date());
	}


     /**
    * SUN 의 BASE64Encode 를 이용한 encode.
    * @param   String Encoding 대상
    * @return  String Encoding 결과값
    * @throws  Exception
    */
    public static String encode64(String strEncode){
        String         result = null;
        if(strEncode==null || strEncode.trim().length()==0){
            return null;
        }
        try{
            BASE64Encoder b64e  = new BASE64Encoder();
            ByteArrayInputStream bais  = new ByteArrayInputStream(strEncode.getBytes());
            ByteArrayOutputStream baos  = new ByteArrayOutputStream();
            byte[]        bytes  = null;

            b64e.encodeBuffer(bais, baos);
            bytes             = baos.toByteArray();
            result            = (new String(bytes)).trim();

        }catch(Exception e){
            System.out.println("CommonUtil.edcode64. : "+e.toString());
        }
        return result;
    }

    /**
    * SUN 의 BASE64Encode 를 이용한 decode.
    * @param   encodeBytes Decoding 대상
    * @return  String Decoding 결과값
    * @throws  Exception
    */
    public static String decode64(String strDecode){

        String         result = null;

        if(strDecode==null || strDecode.trim().length()==0){
            return null;
        }

        try{
            BASE64Decoder     b64d  = new BASE64Decoder();
            ByteArrayInputStream bais  = new ByteArrayInputStream(strDecode.getBytes());
            ByteArrayOutputStream baos  = new ByteArrayOutputStream();
            byte[]        bytes  = null;

            b64d.decodeBuffer(bais, baos);
            bytes             = baos.toByteArray();
            result            = new String(bytes);
        }catch(Exception e){
            System.out.println("CommonUtil.decode64. : "+e.toString());
        }
        return result;
    }

    /*
    *   서희건설 전화번호때문에 만듬
    */
    public static int charCheck(String chk){
		String priStr = "";
		int cnt = 0;
		char ch;
		if(chk.length() > 0){
			priStr = chk.trim();
	        for (int i=0; i<priStr.length(); i++) {
	            ch = priStr.charAt(i);
	            if(  '-' == ch){
	            	cnt = cnt+1;
	            }
	        }
		}

		return cnt;
	}

    public String faxNull(String str) {
    	if( null == str) {
    		return "";
    	} else {
			if("null".equals(str)) {
				return "";
			} else {
				return str.trim();
			}
    	}
   }

    public String faxNullisZero(String str) {
    	if( null == str ) {
    		return "0";
    	} else {
			if("null".equals(str)) {
				return "0";
			} else {
				return str.trim();
			}
    	}
   }

    public String faxNullisZero2(String str) {
    	if( null == str  || str.equals("")) {
    		return "0";
    	} else {
			if("null".equals(str)) {
				return "0";
			} else {
				return str.trim();
			}
    	}
   }

    /**
     * 문자를 html 구문으로 변경
     * @param str 	 : 원데이터
     * @return 		 : " -> &quot;
     * @throws Exception
     */
    public String toHtml( String str )
    throws Exception {
    	String result = "";
    	if ( str == null ){
    		return "";
    	}
   		result = str.replaceAll("\"","&quot;");
   		result = result.replaceAll("<", "&lt;");
   		result = result.replaceAll(">", "&gt;");
   		result = result.replaceAll(" ", "&nbsp;");
   		result = result.replaceAll("\'", "&#39;");

    	return result;
    }

    /**
     * 문자를 DB 저장 형태로 변경
     * @param str 	 : 원데이터
     * @return 		 : &quot; -> "
     * @throws Exception
     */
    public String toDB( String str )
    throws Exception {
    	String result = "";
    	if ( str == null ){
    		return "";
    	}
    	result = str.replaceAll("&quot;",  "\"");
    	result = result.replaceAll("&lt;",  "<");
    	result = result.replaceAll("&gt;",  ">");
    	result = result.replaceAll("&nbsp;",  " ");
    	result = result.replaceAll("&amp;",  "&");
    	result = result.replaceAll("&#39;" ,"\'" );
    	return result;
    }

	/**
	 * argument 'orgValue'가 null이면, arguemnt 'defValue'의 값을 리턴하고,
	 * null이 아니면, 'oraValue'를 리턴한다.
	 * @param orgValue
	 * @param defValue
	 * @return
	 */
	public String onvl(String orgValue, String defValue){
		if(orgValue == null || orgValue.equals("") || orgValue.equals("null"))
			return defValue;
		else
			return orgValue;
	}

	/*************************************
	*	int cut숫자만큼 문자 잘라서 뒤에 ".."으로 나타내기
	**************************************/
	public static String getCutStr(String str, int cut) throws UnsupportedEncodingException {
		String returnValue;
		if(str==null || str.equals("")){
			str=null;
			return str;
		}
		if (str.getBytes("KSC5601").length > cut) {
		    returnValue = new String(str.getBytes("KSC5601"), 0, cut, "KSC5601");
		    if (str.indexOf(returnValue) < 0) {
			returnValue = new String(str.getBytes("KSC5601"), 0, cut - 1, "KSC5601");
		    }
		    returnValue += "..";
		} else {
		    returnValue = str;
		}

		return returnValue;
	}

	/**
	    * VAT 제외금액 계산, 절사로 계산 -
	    *  => round(금액/1.1)
	    * @param String istr 입력받은 String
	    * @return String round(금액/1.1)
	    */
	    public String getVat(String istr, boolean flag) {
	        if( flag ){
	            DecimalFormat df = new DecimalFormat("#########################.#");
	            double d = new Double( df.format( Double.parseDouble(istr)/1.1 ) ).doubleValue();
	            return Math.round(Math.floor(d))+"";
	        } else {
	            return istr;
	        }
	    }


	    public String getVat2(String istr, boolean flag, boolean roundflag) {
	        if( flag ){
	            DecimalFormat df = new DecimalFormat("#########################.##");
	            double d = new Double( df.format( Double.parseDouble(istr)*1.1 ) ).doubleValue();
	            if( roundflag ){
	                return Math.round(d)+"";//반올림
	            } else {
	                return Math.round(Math.floor(d))+"";//절사
	            }
	        } else {
	            return istr;
	        }
	    }

	/**
	 * <pre>
	 * 한글 존재 여부와 상관없이 일정한 Byte 단위로 String을 잘라주는 메소드

	 * <br>
	 *  입력된 Byte Size 보다 큰 경우에는 &quot;...&quot;을 추가 한다.
	 * </pre>
	 *
	 * @param asStr
	 *            변환할 문자열

	 * @param aiByteSize
	 *            반환받을 Byte Size
	 * @return 일정길이로 잘라진 문자열

	 */
	public String getShortStringNew(String asStr, int aiByteSize) {
		int iSize = 0;
		int iLen = 0;

		if (asStr == null)
			return "";

		if (asStr.getBytes().length > aiByteSize) {
			for (iSize = 0; iSize < asStr.length(); iSize++) {
				if (asStr.charAt(iSize) > 0x007F)
					iLen += 2;
				else
					iLen++;

				if (iLen > aiByteSize)
					break;
			}
			asStr = asStr.substring(0, iSize) + "..";
		}
		return asStr;
	}

	public String getLenShortString(String asStr, int aiByteSize) {
		int iSize = 0;
		int iLen = 0;

		if (asStr == null)
			return "";

		if (asStr.getBytes().length > aiByteSize) {
			for (iSize = 0; iSize < asStr.length(); iSize++) {
				if (asStr.charAt(iSize) > 0x007F)
					iLen += 2;
				else
					iLen++;

				if (iLen > aiByteSize)
					break;
			}
			asStr = asStr.substring(0, iSize) + "..";
		}
		return asStr;
	}

	public String getLenShortStringXX(String asStr, int aiByteSize) {
		int iSize = 0;
		int iLen = 0;

		if (asStr == null)
			return "";

		if (asStr.getBytes().length > aiByteSize) {
			for (iSize = 0; iSize < asStr.length(); iSize++) {
				if (asStr.charAt(iSize) > 0x007F)
					iLen += 2;
				else
					iLen++;

				if (iLen > aiByteSize)
					break;
			}
			asStr = asStr.substring(0, iSize) + "XXXXXX";
		}
		return asStr;
	}

	/**
	 * 변수가 넘어왔을때 | 를 분리해서 List 로 넘긴다.
	 *
	 * @param v
	 * @param split
	 * @return List
	 */
	public static List splitToList(String v, String split) {
		List ls = new ArrayList();
		if (v == null || v.equals("")) {} else {
			StringTokenizer st = new StringTokenizer(v, split);
			while (st.hasMoreTokens()) {
				ls.add(st.nextToken());
			}
		}
		return ls;
	}



	/**
	 * 커넥션을 닫는다.
	 * @param rs
	 */
	public void closeConnection(ResultSet rs){
		if(rs  != null) try { rs.close(); } catch(Exception e) {} ;
	}

	/**
	 * 커넥션을 닫는다.
	 * @param rs
	 * @param ps
	 */
	public void closeConnection(PreparedStatement ps, Connection con){
		if(ps  != null) try { ps.close();  } catch(Exception e) {} ;
		if(con != null) try { con.close(); } catch(Exception e) {} ;
	}

	/**
	 * 커넥션을 닫는다.
	 * @param rs
	 * @param ps
	 * @param con
	 */
	public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con){
		if(rs  != null) try { rs.close();  } catch(Exception e) {} ;
		if(ps  != null) try { ps.close();  } catch(Exception e) {} ;
		if(con != null) try { con.close(); } catch(Exception e) {} ;
	}

	/**
	 * 예외정보를 콘솔창에 출력
	 * @param Exception e
	 */
	public void printException(Exception e){

		System.out.println();
		System.out.println("************   EXCEPTION NAME   ************");
		System.out.println(e.getClass().getName());
		System.out.println();
		System.out.println("************   CLASS NAME       ************");
		System.out.println(Thread.currentThread().getStackTrace()[3].getClassName());
		System.out.println();
		System.out.println("************   METHOD NAME      ************");
		System.out.println(Thread.currentThread().getStackTrace()[3].getMethodName());
		System.out.println();
		System.out.println("************   ERROR MESSAGE    ************");
		System.out.println(e.getMessage());
		System.out.println();

		//SQLException
		if( e instanceof SQLException )
		{
			System.out.println("************   DB ERROR CODE    ************");
			System.out.println(((SQLException)e).getErrorCode());
			System.out.println();
			System.out.println("************      SQL STATE     ************");
			System.out.println(((SQLException)e).getSQLState());
			System.out.println();
		}
	}

	/**
	 * 예외정보를 콘솔창에 출력
	 * @param Exception e
	 * @param StringBuffer sb
	 */
	public void printException(Exception e, StringBuffer sb){

		System.out.println();
		System.out.println("************   EXCEPTION NAME   ************");
		System.out.println(e.getClass().getName());
		System.out.println();
		System.out.println("************   CLASS NAME       ************");
		System.out.println(Thread.currentThread().getStackTrace()[3].getClassName());
		System.out.println();
		System.out.println("************   METHOD NAME      ************");
		System.out.println(Thread.currentThread().getStackTrace()[3].getMethodName());
		System.out.println();
		System.out.println("************   ERROR MESSAGE    ************");
		System.out.println(e.getMessage());
		System.out.println();


		//SQLException
		if( e instanceof SQLException )
		{
			System.out.println("************   DB ERROR CODE    ************");
			System.out.println(((SQLException)e).getErrorCode());
			System.out.println();
			System.out.println("************      SQL STATE     ************");
			System.out.println(((SQLException)e).getSQLState());
			System.out.println();
			System.out.println("************        QUERY       ************");
			System.out.println(sb.toString());
			System.out.println();
		}

	}

	/**
	 * 특수문자 제거하기
	 * @param str
	 * @return
	 */
	public static String StringReplace(String str){
	    int str_length = str.length();
	    String strlistchar   = "";
	    String str_imsi   = "";
	    String[] filter_word = {"","\\.","\\?","\\/","\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/"};

	    for(int i=0;i<filter_word.length;i++){
	        str_imsi = str.replaceAll(filter_word[i],"");
	        str = str_imsi;
	    }

	    return str;

	   }

	//현재날짜시간가져오기
	public static String getTime() {
	    Calendar now = Calendar.getInstance()	;
		return String.valueOf(now.get(now.HOUR_OF_DAY)) + ":" + String.valueOf(now.get(now.MINUTE)) + ":"  + String.valueOf(now.get(now.SECOND));
	}

	//파라메터
	public String reqParam(HttpServletRequest req, String str, String sDefault) {

        String sParam = req.getParameter(str);

        if( null == sParam || "".equals(sParam)) {
            return sDefault;
        } else {
            if("null".equals(sParam)) {
                return sDefault;
            } else {
                return sParam.trim();
            }
        }
	}

	/**
	 * 현재 서버의 IP 주소를 가져옵니다.
	 *
	 * @return IP 주소
	 */
	public String getLocalServerIp(){
		try {
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
		        NetworkInterface intf = en.nextElement();
		        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
		            InetAddress inetAddress = enumIpAddr.nextElement();
		            if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()){
		            	return inetAddress.getHostAddress().toString();
		            }
		        }
		    }
		} catch (SocketException ex) {}

		return null;
	}

	//특정 자리수 랜덤숫자 생성
	//generateNumber(자리수)
	public int generateNumber(int length) {

	    String numStr = "1";
	    String plusNumStr = "1";

	    for (int i = 0; i < length; i++) {
	        numStr += "0";

	        if (i != length - 1) {
	            plusNumStr += "0";
	        }
	    }

	    Random random = new Random();
	    int result = random.nextInt(Integer.parseInt(numStr)) + Integer.parseInt(plusNumStr);

	    if (result > Integer.parseInt(numStr)) {
	        result = result - Integer.parseInt(plusNumStr);
	    }

	    return result;
	}

	public static String fileWriter(MultipartFile uploadFile, String filePath) {
		OutputStream out = null;
		String fileName = null;

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");

		try {
			byte[] bytes = uploadFile.getBytes();
			String originalFileName = uploadFile.getOriginalFilename();
			fileName = sdf.format(now) + "_" + originalFileName;
			String fileFullPath = filePath + fileName;

			File file = new File(fileFullPath);

			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			out = new FileOutputStream(file);
			out.write(bytes);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}

	public static String makeRename(MultipartFile uploadFile) {
		String fileName = null;
		try {
	
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
	
			String originalFileName = uploadFile.getOriginalFilename();
			fileName = sdf.format(now) + "_" + originalFileName;
	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return fileName;
	}
} //end class