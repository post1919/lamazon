package com.lamazon.properties;

public class Properties {

	public static boolean isMySQL = true;

	public static String SERVER_NAME = "http://lamazon.co.kr";
	public static String SERVER_NAME_LAMAZON = "http://lamazon.co.kr";

	public static String reCAPTCHA_SITE_KEY = "6LdWChkTAAAAADeqCrD3PaTANBaH4coMnu2I2fX7";		// 아이디/비밀번호 찾기용 reCAPTCHA
	public static String reCAPTCHA_SECRET_KEY = "6LdWChkTAAAAAC5jWaRGNtXtA7CR0x5SM2boggxE";

	public static String DATA_SOURCE = "lamazon.datasource";		// DB 접속
	public static String QUERY_FILENAME = "lamazon.xml";			// 쿼리 파일명

	public static int TEMP_PASSWORD_SIZE = 8;						// 임시 비밀번호 발급 길이

	public static int POINT = 2000; //포인트

	// 리턴 메세지 plain text
	public static String OUTPUT_OK = "OK";
	public static String OUTPUT_FAIL = "FAIL";
	public static String OUTPUT_EXIST = "EXIST";
	public static String OUTPUT_ERROR = "ERROR";
	public static String OUTPUT_NEED_LOGIN = "NEED_LOGIN";


	public static String[] MAIL_RECEIVER = new String[] {"post1919@naver.com"};

	public static String TOMCAT_ROOT        = "/lamazon/tomcat/webapps"; // 테스트 서버
	public static String PATH_IMAGE 		= TOMCAT_ROOT + "/image";
	public static String PATH_FILE   		= TOMCAT_ROOT + "/file";
	public static String PATH_UPLOAD		= "/lamazon/upload";
	public static String URL_FILE   		= "/file";

	public static String URL_U_PICTURE	    = "/file/u_picture";
	public static String PATH_U_PICTURE	    = TOMCAT_ROOT + URL_U_PICTURE;
	public static String URL_OD_FILE	    = "/file/od_file";
	public static String PATH_OD_FILE	    = TOMCAT_ROOT + URL_OD_FILE;
	public static String URL_B_FILE	        = "/file/b_file";
	public static String PATH_B_FILE	    = TOMCAT_ROOT + URL_B_FILE;
	public static String URL_BR_FILE	    = "/file/br_file";
	public static String PATH_BR_FILE	    = TOMCAT_ROOT + URL_BR_FILE;
	
	public static String IS_ADMIN = "IS_ADMIN";
	public static String IS_USER = "IS_USER";

	public static String JSON_SUCCESS       = "success";
	public static String JSON_NO_PERMISSION = "no_permission";
	public static String JSON_NEED_LOGIN    = "need_login";
	public static String JSON_HAS_ERROR     = "has_error";
	public static String JSON_FAIL		    = "fail";
	public static String JSON_EXIST		    = "exist";

	public static String JSON_MESSAGE_SUCCESS = "{\"result\":\"success\"}";
	public static String JSON_MESSAGE_NO_PERMISSION = "{\"result\":\"no_permission\"}";
	public static String JSON_MESSAGE_NEED_LOGIN = "{\"result\":\"need_login\"}";
	public static String JSON_MESSAGE_POINT      = "{\"result\":\"point\"}";
	public static String JSON_MESSAGE_HAS_ERROR = "{\"result\":\"has_error\"}";
	public static String JSON_MESSAGE_FAIL		= "{\"result\":\"fail\"}";
	public static String JSON_MESSAGE_EXIST		= "{\"result\":\"exist\"}";

	//SMS
	public static String SMS_AUTH_NUM = "16442653";

	//메세지 관련
	public static String JOIN_COMPLETE   = "회원가입 되었습니다.";
}
