package com.lamazon.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * HTTP COOKIE 공통 UTIL
 * 2017-09-15 신동아
 * 쿠키 저장방식 2가지
 * 1. String으로 VALUE값 저장 - setCookie("COOKIE_NAME", "COOKIE_STRING", "DOMAIN_NAME", "PATH_NAME", MAX_PERIOD);
 * 2. Map으로    VALUE값 저장 - setCookie("COOKIE_NAME", COOKIE_MAP,      "DOMAIN_NAME", "PATH_NAME", MAX_PERIOD);
 */
public class CookieUtil {

	//private static CookieUtil cookieUtil        = null;
	private HttpServletRequest request   = null;
	private HttpServletResponse response = null;

	//public CookieUtil(){}

	public CookieUtil(HttpServletRequest request, HttpServletResponse response){
		this.request  = request;
		this.response = response;
	}

	/*
	public CookieUtil( HttpServletRequest request ){
        Cookie[] cookies = request.getCookies();
        if( cookies != null ){
            for( int i=0; i<cookies.length; i++ ){
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
    }
	*/
	/*
	public static synchronized CookieUtil getInstance(HttpServletRequest request, HttpServletResponse response) {
		if(cookieUtil == null) {
			cookieUtil = new CookieUtil(request, response);
		}

		return cookieUtil;
	}
	*/

    public Cookie setCookie(String name, String value) throws IOException {
        return setCookieData(name, value, null, null, 0);
    }

    public Cookie setCookie(String name, Map<String,String> value) throws IOException {
    	return setCookieData(name, convertQueryString(value), null, null, 0);
    }

    public Cookie setCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
    	return setCookieData(name, value, domain, path, maxAge);
    }

    public Cookie setCookie(String name, Map<String,String> value, String domain, String path, int maxAge) throws IOException {
        return setCookieData(name, convertQueryString(value), domain, path, maxAge);
    }

    private Cookie setCookieData(String name, String value, String domain, String path, int maxAge) throws IOException {
    	Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));

    	if( domain != null && domain != "" ){ cookie.setDomain(domain); }
    	if( path   != null && path   != "" ){ cookie.setPath(path);     }
    	if( maxAge > 0 ){ cookie.setMaxAge(maxAge); }

    	response.addCookie(cookie);
    	response.setContentType("text/plain;charset=UTF-8");

        return cookie;
    }

    /*
     * 전체 쿠키목록 가져오기
     * return Map
     */
    public Map<String,String> getCookies(){
    	Cookie[] cookies = request.getCookies();
    	Map<String,String> map = null;
        if( cookies != null ){
        	map = new HashMap<String,String>();
            for( int i=0; i<cookies.length; i++ ){
            	map.put(cookies[i].getName(), cookies[i].getValue());
            }
        }

        return map;
    }

    /*
     * cookieName에 해당하는 정보 가져오기
     * return String
     */
    public String getValue(String cookieName) throws IOException {
    	if( cookieName == null ){
    		return null;

        } else {
        	Map<String, String> cookiesMap = getCookies();

        	if( cookiesMap == null ){
        		return null;
        	} else {
        		if( cookiesMap.containsKey(cookieName) ){
        			return URLDecoder.decode(cookiesMap.get(cookieName), "UTF-8");
        		} else {
        			return null;
        		}
        	}
        }
    }

    /*
     * cookieName에 해당하는 정보 가져오기
     * return Map
     */
    public Map<String, String> getValueMap(String cookieName) throws IOException {
    	if( cookieName == null ){
    		return null;

        } else {
        	Map<String, String> cookiesMap = getCookies();

        	if( cookiesMap == null ){
        		return null;
        	} else {
        		if( cookiesMap.containsKey(cookieName) ){
        			return convertMap(URLDecoder.decode(cookiesMap.get(cookieName), "UTF-8"));
        		} else {
        			return null;
        		}
        	}
        }
    }

    /**
   	 * String cookieName에 해당하는 쿠키의 속성값들중 propertyKey에 해당하는 속성값 가져오기
   	 *
   	 * @param cookieName
   	 * @param propertyKey
   	 * @return String
   	 */
    public String getValue(String cookieName, String propertyKey) throws IOException {

    	Map<String, String> map = getValueMap(cookieName);
    	if( map == null ){
    		return null;

        } else {
        	//해당쿠키(cookieName)의 속성값들중 해당키(propertyKey)가 있는지
        	if( map.containsKey(propertyKey) ){
        		//있다면 해당키의 value값 리턴
        		return map.get(propertyKey);
        	}
        }

		return null;
    }

    /*
     * 쿠키목록중 name의 쿠키정보 존재여부
     * return boolean
     */
    public boolean exists(String name) {
    	Map<String, String> cookiesMap = getCookies();
    	boolean rtn = false;
    	if( cookiesMap != null ){
	    	if( getCookies().get(name) != null ){
	    		return true;
	    	}
    	}

    	return rtn;
    }

	/*
	 * QueryString => Map으로 변환
	 * return Map
	 */
	public Map<String, String> convertMap(String queryString) {
	    if( queryString == null ) return null;

	    Map<String,String> map = new HashMap<String,String>();
	    String[] params = queryString.split("[&]");
	    String[] param = null;
	    for( int i = params.length-1; i >=0; i-- ) {
	        param = params[i].split("[=]");
	        if( param.length == 2 )
	            map.put( param[0], param[1] );
	    }

	    return map;
	}

	/*
	 * Map => QueryString으로 변환
	 * return String
	 */
	public String convertQueryString(Map<String,String> data) {
	    if( data == null ) return null;

	    String[] keys = data.keySet().toArray(new String[0]);
	    int keyCount = keys.length;

	    StringBuffer queryString = new StringBuffer();
	    for( int i = 0; i < keyCount; i++ ) {
	        queryString.append( keys[i] ).append( "=" ).append( data.get(keys[i]) );
	        if( i != keyCount-1 ) queryString.append("&");
	    }

	    return queryString.toString();
	}
}
