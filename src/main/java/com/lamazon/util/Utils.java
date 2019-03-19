package com.lamazon.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.NotFoundException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.comparator.NameFileComparator;
import org.json.JSONObject;

import com.lamazon.properties.Properties;

public class Utils {

//	Category cat = DisneyLogger.getInstance(Utils.class.getName());

	private static final int BUFFER_SIZE = 4096;

	public static void main(String[] args) {

		String path = "/var/www/lamazon/images/upload/";

		File directory = new File(path);

		File[] files = directory.listFiles();

		//Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);

		//Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

		Arrays.sort(files, NameFileComparator.NAME_REVERSE);


/*		List<File> list = new ArrayList<File>();


		for(File folder : files) {
			System.out.println(folder.getAbsolutePath());
			list.addAll(Arrays.asList(folder.listFiles()));
		}

		File[] images = Arrays.copyOf(list.toArray(), list.toArray().length, File[].class);*/

		for(File image : files) {
			//System.out.println(image.getAbsolutePath());
			String p = image.getPath();

			String backslash= System.getProperty("file.separator") ;
			//System.out.println("backslash  "+backslash);
			p = p.replace(backslash, "/");
			//System.out.println(p);
		}
/*
		Arrays.sort(files, NameFileComparator.NAME_REVERSE);

		System.out.println("=================================================================");
		for(File image : files) {
			System.out.println(image.getAbsolutePath());
		}


		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

		System.out.println("=================================================================");
		for(File image : files) {
			System.out.println(image.getAbsolutePath());
		}*/

	}


	public static String stringToHex(String s) {
	    String result = "";

	    for (int i = 0; i < s.length(); i++) {
	      result += String.format("%02X ", (int) s.charAt(i));
	    }

	    return result;
	  }


	  // 헥사 접두사 "0x" 붙이는 버전
	  public static String stringToHex0x(String s) {
	    String result = "";

	    for (int i = 0; i < s.length(); i++) {
	      result += String.format("0x%02X ", (int) s.charAt(i));
	    }

	    return result;
	  }

	public static String getStreamFromUrl(String url) {
		return getStreamFromUrl(url,"UTF-8");
	}


	public static String getStreamFromUrl(String url, String encoding) {
		String str = "";

		URL oracle;
		try {
			oracle = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream(), encoding));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null) str += inputLine;
	        in.close();

		} catch (IOException e) {
			e.printStackTrace();
			str = null;
		}
		return str;
	}

	public static String getServerName(Object obj) {
		return Properties.SERVER_NAME_LAMAZON;
	}

	public static String getFrom(HttpServletRequest request) {
		return "";
	}

	public static String makePromotionId(int pk) {
		return "PM-"+toBase62Long(pk)+""+ fromCurrnet(System.currentTimeMillis());
	}

	public static HashMap<String, Object> makeLockIdInfo(HttpServletRequest request) {
		HashMap<String, Object> info = new HashMap<String, Object>();

		info.put("IP", request.getRemoteAddr());
		info.put("SESSION_ID", request.getSession().getId());
		info.put("TIME", System.currentTimeMillis());

		return info;
	}

	public static void fileDown(HttpServletResponse response, String path, String fileName) throws IOException, FileNotFoundException {
		String _fileName = URLEncoder.encode(fileName, "UTF-8");
		 _fileName = URLDecoder.decode(_fileName, "ISO8859_1");
		 response.setHeader("Content-disposition", "attachment; filename="+ _fileName);
		 response.setHeader("Content-Length", new File(path).length()+"");
		 response.setHeader("Content-Transfer-Encoding", "binary;");
		 response.setHeader("Pragma", "no-cache;");
		 response.setHeader("Expires", "0;");

		ServletOutputStream output  =null;
		FileInputStream flinp =null;
		BufferedInputStream buffinp =null;
		BufferedOutputStream buffoup =null;

		File f = new File(path);
		if(!f.exists()) {
			throw new FileNotFoundException();
		}

		try {
			output = response.getOutputStream();
			flinp = new FileInputStream(path);
			buffinp = new BufferedInputStream(flinp);
			buffoup = new BufferedOutputStream(output);
			int ch=0;
			while ((ch=buffinp.read()) != -1) {
				buffoup.write(ch);
			}
		} finally {
			buffoup.close();
			buffinp.close();
			flinp.close();
	        output.close();
		}
	}

	//익스플로러 파일다운시에 깨짐현상 수정위해 fileDown 함수 하나더 추가 20170710 신동아
	public static void fileDown(HttpServletRequest request, HttpServletResponse response, String path, String fileName) throws IOException, FileNotFoundException {

		String header = request.getHeader("User-Agent");

		//익스플로러
		if( header.contains("MSIE") || header.contains("Trident") ){
			String _fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

			// 요놈이 문제였다. 제대로 디코딩이 안됐음 그냥 주석처리
			//_fileName = URLDecoder.decode(_fileName, "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename="+ _fileName + ";");

		//나머지 찌그레기
		} else {
			String _fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			_fileName = URLDecoder.decode(_fileName, "ISO8859_1");

			response.setHeader("Content-Disposition", "attachment; filename=\""+ _fileName + "\"");
		}

		 response.setHeader("Content-Length", new File(path).length()+"");
		 response.setHeader("Content-Type", "application/octet-stream");
		 response.setHeader("Content-Transfer-Encoding", "binary;");
		 response.setHeader("Pragma", "no-cache;");
		 response.setHeader("Expires", "0;");

		ServletOutputStream output  =null;
		FileInputStream flinp =null;
		BufferedInputStream buffinp =null;
		BufferedOutputStream buffoup =null;

		File f = new File(path);
		if(!f.exists()) {
			throw new FileNotFoundException();
		}

		try {
			output = response.getOutputStream();
			flinp = new FileInputStream(path);
			buffinp = new BufferedInputStream(flinp);
			buffoup = new BufferedOutputStream(output);
			int ch=0;
			while ((ch=buffinp.read()) != -1) {
				buffoup.write(ch);
			}
		} finally {
			buffoup.close();
			buffinp.close();
			flinp.close();
	        output.close();
		}
	}


	public static File[] fileList(String path) {

		File f = new File(path);

		FileFilter directoryFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile();
			}
		};
		return f.listFiles(directoryFilter);
	}

	public static void setFiles(HttpServletRequest request, String name, String path) {

		File[] list = Utils.fileList(path);
		request.setAttribute(name, list);
	}

	public static void getDirectoryInfo(String path){
		File dir = new File(path);
		if( !dir.exists() ){
			dir.mkdir();
		}
	}

	//디렉토리(안의 파일까지) 삭제
	public static boolean deleteDirectoryWithFile(File path) {
		if (!path.exists()) {
			return false;
		}
		File[] files = path.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteDirectoryWithFile(file);
			} else {
				file.delete();
			}
		}

		return path.delete();
	}

	public static void deleteFile(String path, String fileName) {

		if(path.endsWith("/")) path = path.substring(0, path.length()-1);

		if( fileName != null ) {
			File f = new File(path,fileName);
			if(f.exists()) f.delete();
		}
	}

	public static String makeCID(String name) { // 파트너의 고유 id
		String str = "";

		//if(name.length()>10) name = name.substring(0,10);

        int l = 0;
        char[] charArray = name.toCharArray();
        for(int i=0;i<charArray.length;i++) {
        	int indx = charArray[i];
        	l += indx;
        }
        str = toBase62Long(l)+""+ fromCurrnet(System.currentTimeMillis());
		return str;
	}

	public static boolean file_move(String source, String target) {
		boolean res = file_copy(source,target);
		if(res==true) {
			File f = new File(source);
			f.delete();
		}
		return res;
	}

	public static boolean file_copy(String source, String target) {

		Path _source = Paths.get(source);
		Path _destination = Paths.get(target);
		Path p = _destination.getParent();

		File path = p.toFile();
		if(!path.exists()) path.mkdirs();

		try {
			File f = new File(target);
			if(f.isFile() && f.exists()) f.delete();

			Files.copy(_source, _destination);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public static String image_download(String fileURL, String path, String fileName) {

		URL url;
		HttpURLConnection httpConn = null;
		InputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			url = new URL(fileURL);
			httpConn = (HttpURLConnection) url.openConnection();

			int responseCode = httpConn.getResponseCode();

	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            inputStream = httpConn.getInputStream();

	            if(!new File(path).exists()) {
	            	new File(path).mkdirs();
	            }

	            String saveFilePath = path + File.separator + fileName;

	            outputStream = new FileOutputStream(saveFilePath);

	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	        }

		} catch (Exception e) {
			e.printStackTrace();
			fileName = "";
		} finally {
			if(outputStream!=null)
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(inputStream!=null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(httpConn!=null) httpConn.disconnect();
		}
		return fileName;
	}

	public void copyProfilePicture(String source, String target) {

	}


	// 비밀번호용
	public static String makePassword(String planText) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(planText.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


	private static String key = "JEVop6zfZq5WfYaP";
	private static String key_officeplus = "akfssgnsgjlshlgs";
	//291e7e90639c3fa53fd04abf1f07dd837424d361a2a4e1c21537496c0df252a8

	// 계좌번호 암호화용
	public static String encrypt(String message) throws Exception{
	    return encrypt(message, key);
	}

	public static String decrypt(String message) throws Exception{
	    return decrypt(message, key);
	}


	public static String encrypt_officeplus(String message) throws Exception{
	    return encrypt(message, key_officeplus);
	}

	public static String decrypt_officeplus(String message) throws Exception{
	    return decrypt(message, key_officeplus);
	}


	public static JSONObject decrypt_json_officeplus(String message) throws Exception{
		JSONObject r = new JSONObject(decrypt(decrypt(message, key_officeplus),key_officeplus,"EUC-KR"));

		String r_id = "";
		String r_date = "";
		String r_now = "";

		Iterator it = r.keys();

		JSONObject r2 = new JSONObject();

		while(it.hasNext()) {
			String k = (String)it.next();
			String k2 = k.substring(k.indexOf("-")+1);
			r2.put(k2, r.getString(k));
		}

	    return r2;
	}

	private static String encrypt(String message, String key) throws Exception{

	    if(message == null){
	        return null;
	    }else{
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(message.getBytes());

	        return byteArrayToHex(encrypted);
	    }
	}


	private static String byteArrayToHex(byte[] encrypted) {

	    if(encrypted == null || encrypted.length ==0){
	        return null;
	    }

	    StringBuffer sb = new StringBuffer(encrypted.length * 2);
	    String hexNumber;

	    for(int x=0; x<encrypted.length; x++){
	        hexNumber = "0" + Integer.toHexString(0xff & encrypted[x]);
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }

	    return sb.toString();
	}

	// 계좌번호 복호화용
	public static String decrypt(String encrypted, String key) throws Exception{

	    if(encrypted == null){
	        return null;
	    }else{
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

	        byte[] original = cipher.doFinal(hexToByteArray(encrypted));

	        String originalStr = new String(original);

	        return originalStr;
	    }
	}


	public static String decrypt(String encrypted, String key, String charset) throws Exception{

	    if(encrypted == null){
	        return null;
	    }else{
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

	        byte[] original = cipher.doFinal(hexToByteArray(encrypted));

	        String originalStr = new String(original,charset);

	        return originalStr;
	    }
	}


	private static byte[] hexToByteArray(String hex) {

	    if(hex == null || hex.length() == 0){
	        return null;
	    }

	  //16진수 문자열을 byte로 변환
	    byte[] byteArray = new byte[hex.length() /2 ];

	    for(int i=0; i<byteArray.length; i++){
	        byteArray[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2*i+2), 16);
	    }
	    return byteArray;
	}

	public static void keyGen() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);

		Key key = generator.generateKey();


		byte[] keyBytes = key.getEncoded();
		String base64EncodedKey = Base64.encodeBase64URLSafeString(keyBytes);
	}


	// 임시 비밀번호 발급용
	private static final String keyTxt = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#";

	public static String tmpPasswd(int size) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			int r = random.nextInt(keyTxt.length());
			buffer.append(keyTxt.subSequence(r, r+1));
		}
		return buffer.toString();
	}



	private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String fromCurrnet(long base62Number) {
        return toBase62Long(base62Number);
    }

    public static String toBase62(int decimalNumber) {
        return fromDecimalToOtherBase(62, decimalNumber);
    }
    public static String toBase62Long(long decimalNumber) {
        return fromDecimalToOtherBaseLong(62, decimalNumber);
    }


    private static String fromDecimalToOtherBase(int base, int decimalNumber) {
        String tempVal = decimalNumber == 0 ? "0" : "";
        int mod = 0;
        while (decimalNumber != 0) {
            mod = decimalNumber % base;
            tempVal = baseDigits.substring(mod, mod + 1) + tempVal;
            decimalNumber = decimalNumber / base;
        }

        return tempVal;
    }
    private static String fromDecimalToOtherBaseLong(int base, long decimalNumber) {
        String tempVal = decimalNumber == 0 ? "0" : "";
        long mod = 0;
        while (decimalNumber != 0) {
            mod = decimalNumber % base;
            tempVal = baseDigits.substring((int)mod, (int)mod + 1) + tempVal;
            decimalNumber = decimalNumber / base;
        }

        return tempVal;
    }




	public static String getProjectEmail(Map project, Map user) {
		String wemail = "";

		if(project.get("PR_EMAIL")!=null) {
			wemail = (String)project.get("PR_EMAIL");
		}

		if(wemail==null || wemail.length()==0) {
			wemail = (String)user.get("U_EMAIL");
		}

		return wemail;
	}


	public static String getProjectEmail(Map project) {
		String wemail = "";

		if(project.get("PR_EMAIL")!=null) {
			wemail = (String)project.get("PR_EMAIL");
		}

		if(wemail==null || wemail.length()==0) {
			wemail = (String)project.get("U_EMAIL");
		}

		return wemail;
	}

	/**
	    * 변경하고자 하는 기간만큼 이동한 후 원하는 날짜를 가져온다.<br>
	    *
	    * @param    String cDate    입력일<br>
	    * @param    String ymd      변경 구분 , yyyy(년),  mm(월), dd(일)<br>
	    * @param    int    term     변경하고자 하는 기간<br>
	    * @return   String rtnVal   변경된 일자<br>
		*/
		public static String calDate(String cDate, String ymd, int term, String gubun){

	        int yyyyN, mmN, ddN;        //변경된 일자
	        int yyyyO, mmO, ddO;        //입력한 일자

	        cDate = cDate.replaceAll("-", "");

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

	        rtnVal = yyyyN+gubun+mmS+gubun+ddS;

	        return rtnVal;

	    }//end method()

		public static String getYyyymmdd() {
			SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd");
			String yyyymmdd = sdfFile.format(new java.util.Date());
			return yyyymmdd;
		}

		public static String getYyyymmdd(String divStr) {
			SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd");
			String yyyymmdd = sdfFile.format(new java.util.Date());
			yyyymmdd = yyyymmdd.substring(0,4)+divStr+yyyymmdd.substring(4,6)+divStr+yyyymmdd.substring(6,8);
			return yyyymmdd;
		}

		public static String getPRPrice( String price )	    {
			if (price.equalsIgnoreCase("11"))
	            return "100만원 미만" ;
			else if (price.equalsIgnoreCase("12"))
	            return "200만원 미만" ;
			else if (price.equalsIgnoreCase("13"))
	            return "300만원 미만" ;
			else if(price.equalsIgnoreCase("21"))
	            return "500만원 미만" ;
			else if(price.equalsIgnoreCase("28"))
	            return "800만원 미만" ;
	        else if(price.equalsIgnoreCase("31"))
	            return "1,000만원 미만" ;
	        else if(price.equalsIgnoreCase("41"))
	            return "3,000만원 미만" ;
	        else if(price.equalsIgnoreCase("51"))
	            return "5,000만원 미만" ;
	        else if(price.equalsIgnoreCase("61"))
	            return "5,000만원 이상" ;
	        else if(price.equalsIgnoreCase("67"))
	            return "7,000만원 미만" ;
	        else if(price.equalsIgnoreCase("68"))
	            return "7,000만원 이상" ;
	        else if(price.equalsIgnoreCase("71"))
	            return "1억원 이상" ;
	        else if(price.equalsIgnoreCase("81"))
	            return "별도 협의" ;
	        else
	            return price ;
	    }

		public static String getPRPeriod( String period )	    {
	        if (period.equalsIgnoreCase("1"))
	            return "1개월 이하" ;
	        else if(period.equalsIgnoreCase("3"))
	            return "1~3개월" ;
	        else if(period.equalsIgnoreCase("6"))
	            return "3~6개월" ;
	        else if(period.equalsIgnoreCase("12"))
	            return "6개월~1년" ;
	        else if(period.equalsIgnoreCase("13"))
	            return "1년이상" ;
	        else
	            return period ;
	    }

	//실제 서버에 파일이 존재하는지 여부
	public static boolean isFileExist(String pathFile){
		File file;
		boolean isExist = false;

		//파일정보가 없는경우
		if( "".equals(pathFile) ){
			isExist = false;
		} else {
			file = new File(pathFile);
			isExist = file.exists();
		}

		return isExist;
	}

	//이메일유효성 체크
	public static boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",email.trim());
        return b;
    }

	//모바일여부 체크
	public static boolean isMobile( HttpServletRequest request ) {
		boolean returnFlag = false;

		if( request.getHeader("User-Agent") != null ){
			String userAgent = request.getHeader("User-Agent").toLowerCase().replaceAll(" ", "");
			boolean mobile1 = userAgent.matches("\".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*\"");
			boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
			if( mobile1 || mobile2 || userAgent.indexOf("mobile") != -1){
				returnFlag = true;
			}
		}

		return returnFlag;
	}

	//NULL OR 빈값 체크
	public static boolean isEmpty(Object s) {
		if (s == null) {
			return true;
		}
		if ((s instanceof String) && (((String) s).trim().length() == 0)) {
			return true;
		}
		if (s instanceof Map) {
			return ((Map<?, ?>) s).isEmpty();
		}
		if (s instanceof List) {
			return ((List<?>) s).isEmpty();
		}
		if (s instanceof Object[]) {
			return (((Object[]) s).length == 0);
		}
		return false;
	}

}
