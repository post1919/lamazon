package com.lamazon.aspect;

//@Aspect
//@Component
public class LogManager {
/*
	private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

	//@Autowired(required=false)
	//DataSource dataSource;

	@Autowired
	//@Qualifier("partnerService")
	CommonService commonService;

	@Autowired
	//@Qualifier("partnerService")
	//LogManagerService logManagerService;

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

	//테이블명 생성
	public String tableName() {
		String tableName = "";
		Calendar cal = Calendar.getInstance();

		if(cal.get(Calendar.MONTH)>8) {
			tableName = "LOGGING_" + cal.get(Calendar.YEAR) + "_" + (cal.get(Calendar.MONTH)+1);
		} else {
			tableName = "LOGGING_" + cal.get(Calendar.YEAR) + "_0" + (cal.get(Calendar.MONTH)+1);
		}

		return tableName;
	}

	@Around("execution(* *..*Controller.*(..))")
	public Object doLog(ProceedingJoinPoint jp) throws Throwable {

		logger.debug("메서드 시작 [LogManager] : " + jp.getSignature());

		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

			HttpServletRequest request   = servletRequestAttributes.getRequest();
			HttpServletResponse response = servletRequestAttributes.getResponse();

			String uri         = request.getRequestURI();
			String sessionId   = request.getSession().getId();
			String queryString = request.getQueryString();
			String referer     = request.getHeader("Referer");
			String ip          = request.getRemoteAddr();
			int    uPk         = loginService.getUPK(request);
			String txt = "";

			int a_id = logManagerService.get_agent_id(request);

			// 로그 남기지 않을 써블릿 들.
			if( uri.startsWith("/Show") || uri.startsWith("/rest") ){
				//return;
			}

			//POST요청시 파라미터값들 가져오기
			if( "post".equals(request.getMethod().toLowerCase()) ){

				Enumeration em =  request.getParameterNames();

				while(em.hasMoreElements()) {
					String _name = (String)em.nextElement();

					if((request.getParameter(_name)).getClass().isArray()) {
						String[] args = request.getParameterValues(_name);
						for(String arg:args) {
							txt += _name+" : "+arg+"\r\n";
						}
					} else {
						try {
							if(_name.indexOf("passwd")==-1 && _name.indexOf("password")==-1) {
								String _val = request.getParameter(_name);
								String[] lines = _val.split(System.getProperty("line.separator"));
								if(lines.length>1) _val = lines[0];
								if(_val.length()>100) _val = _val.substring(0,99);
								txt += _name+" : "+_val+"\r\n";
							}
						} catch (Exception e) {

						}

					}
				}
			}
			//LOG정보 쿠키 셋팅[S]

			String gubun = "NEW"; //신규방문 NEW/재방문 RE 2가지로 구분
			String cookie_name = "CASTINGN_INFO"; //쿠키명

			//로그인아이디
			String login_id = request.getParameter("user_id") == null ? "" : request.getParameter("user_id");
			if( loginService.isLogin(request) ){
				login_id = loginService.getUserId(request);
			}

			String gubun_login = ""; //로그인 여부

			//메인페이지일경우 LANDING_TYPE 쿠키값을 'main'으로 셋팅함(LandingEnroll.java에서 어떤 팝업띄워줄건지 정할떄 사용됨)
			if( uri.equals("/") ){
				Cookie cookie = new Cookie("LANDING_TYPE", "main");
				response.addCookie(cookie);
			}

			boolean first_visit     = false; //첫방문여부
			String first_visit_time = "";    //첫방문 시간 - 쿠키처음 생성시에만 기록
	  		boolean login_date      = false; //로그인 날짜
	  		boolean logout_date     = false; //로그아웃 날짜

			//URI로 로그인 판단함
			if( uri.startsWith("/rest/join/login") ){
				gubun_login = "LOGIN";
				login_date  = true; //로그인할때 시간기록
			}

			//URI로 로그아웃 판단함
			if( uri.startsWith("/logout.cast") ){
				gubun_login = "LOGOUT";
				logout_date = true; //로그아웃할때 시간기록
			}

			String category         = "";  //전체 접근한 카테고리
			String category_cur     = "";  //현재 접근한 카테고리
			String pageview_percent = "0"; //페이지를 본 영역(전체 높이에서 스크롤 위치까지로 계산)

			try {
				CookieUtil cookieUtil = new CookieUtil(request, response);

				//현재 날짜
				Calendar cal = Calendar.getInstance();
			    String dateString, timeString;
			    dateString = String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
			    timeString = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			    String yyyymmddhhmmss = dateString + " " + timeString;

			    //쿠키가 존재하지않을때 첫방문 구분 및 시간 셋팅
			    if( !cookieUtil.exists(cookie_name) ){
			    	first_visit      = true; //첫방문
				    first_visit_time = yyyymmddhhmmss;

				//첫방문 아닐경우는 첫방문날짜(first_visit_time)와 현재 날짜를 비교해서 재방문여부(gubun) 셋팅
			    } else {
			    	try {
			    		//쿠키에있는 최초방문날짜를 Calendar 타입으로 변환
				    	Calendar cal_cookie = Calendar.getInstance();

			    		String cookie_first_visit_time = cookieUtil.getValue(cookie_name, "FIRST_VISIT_TIME");

			    		if( cookie_first_visit_time == null || "".equals(cookie_first_visit_time) ){
			    			;
			    		} else {
			    			first_visit_time = cookie_first_visit_time;

					    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	cal_cookie.setTime(formatter.parse(cookie_first_visit_time));

					    	//재방문여부를 결정하기위해 최초방문날짜에서 현재방문날짜를 비교해서 재방문여부 결정
					    	long diffSec = (cal.getTimeInMillis() - cal_cookie.getTimeInMillis()) / 1000;
					    	long diffDay = diffSec / (60*60*24);

					    	//diffDay = 1;

					    	//최초방문날짜로부터 하루가 지났으면 재방문으로 셋팅
					    	if( diffDay >= 1 ){
					    		gubun = "RE";
					    	}
			    		}
			    	} catch( ParseException pe ){
			    		pe.printStackTrace();
			    	}
			    }

				//회원의 로그DB 정보 가져오기
				if( login_id != null && !"".equals(login_id) ){
					//로그테이블 명 가져옴
					String tableName = tableName();

					HashMap<String, String> map = new HashMap<String, String>();
					map.put("TABLE_NAME", tableName);

					//로그인 했을경우 - 해당 회원것만 가져옴
					if( loginService.isLogin(request) ){
						map.put("LOGIN_ID", login_id);
					}
				}

				//파라미터 카테고리
				if( !"".equals(queryString) || !"".equals(referer) ){
					Map<String, String> queryStringMap = cookieUtil.convertMap(queryString);
					Map<String, String> refererMap     = cookieUtil.convertMap(referer);

					//쿼리스트링에 카테고리 값 정보
					if( queryStringMap != null ){
						for( String key : queryStringMap.keySet() ){
							if( "c".equals(key) || "cate".equals(key) || "cat1_depth1".equals(key) || "cat1_depth2".equals(key) ){
								//위의 쿠키에서 가져온 CATEGORY값 담은 category변수에 이어서 담는다.
								category += queryStringMap.get(key).trim() + ",";
							}
						}
					}

					//referer에 카테고리 값 정보
					if( refererMap != null ){
						for( String key : refererMap.keySet() ){
							if( "c".equals(key) || "cate".equals(key) || "cat1_depth1".equals(key) || "cat1_depth2".equals(key) ){
								//위의 쿠키에서 가져온 CATEGORY값 담은 category변수에 이어서 담는다.
								category += refererMap.get(key).trim() + ",";
							}
						}
					}
				}

				//쿠키에 담겨있는 카테고리 정보 읽어오기
				Map<String, String> castingnInfoMap = cookieUtil.getValueMap(cookie_name);

				if( castingnInfoMap != null ){
					for( String key : castingnInfoMap.keySet() ){
						//값 있는경우만 가져오기
						if( "CATEGORY".equals(key) && castingnInfoMap.get(key) != null ){
							//카테고리 값 가져오기(C-03-03,C-03-02,C-03-03,A-01-35 형식으로 만듬)
							category += castingnInfoMap.get(key) + ",";
						}
					}
				}

				//C-03-03,C-03-02,C-03-03,A-01-35 형식으로 만든 CATEGORY값의 중복제거위해 ,구분으로 다시 자른다
				String[] arrCategory = category.split(",");

				//배열 arrCategory을 중복제거위해 Set에 담는다.
				Set<String> categorySet = Arrays.stream(arrCategory).collect(Collectors.toCollection(LinkedHashSet::new));

				//카테고리 정보를 새로 담기위해 초기화
				category = "";

				//마지막으로 C-03-03,C-03-02,C-03-03,A-01-35 형식으로 다시 만듬
				if( categorySet != null ){
					int i=0;
					for( String val : categorySet ){

						//최근본카테고리정보 - 첫번째 값으로 셋팅함
						if(i == 0){
							category_cur = val;
						}

						if(i++ > 0){
							category += ",";
						}
						category += val;
					}
				}

				Map<String,String> logMap = new HashMap<String,String>();
				logMap.put( "GUBUN_LOGIN"      , gubun_login                   );
				logMap.put( "CASTINGN_INFO"    , cookie_name                   );
				logMap.put( "GUBUN"            , gubun                         );
				logMap.put( "LOGIN_ID"         , login_id                      );
			  //logMap.put( "LOGIN_DATE"       , Boolean.toString(login_date)  );
			  //logMap.put( "LOGOUT_DATE"      , Boolean.toString(logout_date) );
				logMap.put( "CATEGORY"         , category                      );
				logMap.put( "CATEGORY_CUR"     , category_cur                  );
			  //logMap.put( "PAGEVIEW_PERCENT" , pageview_percent              );
			  //logMap.put( "VISIT_CNT"        , "1"                           );
				logMap.put( "FIRST_VISIT"      , Boolean.toString(first_visit) );
				logMap.put( "FIRST_VISIT_TIME" , first_visit_time              );

				String domain = "";

				Cookie cookie = cookieUtil.setCookie(cookie_name, logMap, domain, "/", 60*60*24*365);

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			//LOG정보 쿠키 셋팅[E]

			//테이블 명 가져옴
			String tableName = tableName();

			String stay_uri  = ""; //체류페이지URI
			String stay_time = ""; //체류시간

			//체류시간[s]
			//체류시간 체크할 페이지 URI를 다~적어야함~~~~~ 이거 어떻게 해야할거같다~
			if( uri.equals("/") || uri.startsWith("/login.cast") || uri.startsWith("/projectEnroll") || uri.startsWith("/projectFind") ||
				uri.startsWith("/logout.cast") || uri.startsWith("/promotion") || uri.startsWith("/partner/category/") ||
				uri.startsWith("/my/request/tender") || uri.startsWith("/item/index.html") || uri.startsWith("/join/partner/formnew") ||
				uri.startsWith("/join/user/form") || uri.startsWith("/my/info /my/club/info") || uri.startsWith("/help/notice") ||
				uri.startsWith("/help/guide/user") || uri.startsWith("/help/guide/partner") || uri.startsWith("/help/price") ||
				uri.startsWith("/project") || uri.startsWith("/cblog/main") || uri.startsWith("/partner/search") ||
				uri.startsWith("/shoppingTori") || uri.startsWith("/help/faq") || uri.startsWith("/trust/html/about.html") ||
				uri.startsWith("/trust/html/promise.html") || uri.startsWith("/my/request/view/") || uri.startsWith("/partner") ||
				uri.startsWith("/review") || uri.startsWith("/bc/BcCardLandingEnroll") || uri.startsWith("/projectEnrollRFP") ||
				uri.startsWith("/landingEnroll")
			  ){
				stay_uri = uri;

				//현재 사용자의 IP와 SESSION_ID값의 최근데이터 하나 가져오고
				//Map<String, String> logMap = DBManager.getInstance().makeMap("get_log_new", map, new String[] {ip,sessionId});
				Map<String, String> logNewParamMap = new HashMap<String, String>();
				logNewParamMap.put("TABLE_NAME", tableName);
				logNewParamMap.put("ip",         ip);
				logNewParamMap.put("sessionId",  sessionId);

				Map logMap = logManagerService.get_log_new(logNewParamMap);

				//데이터가 있으면
				if( logMap != null ) {
					if( !logMap.isEmpty() ) {
						String get_pid         = logMap.get("PID").toString();
						String get_session_id  = logMap.get("SESSION_ID").toString();
						String get_ip          = logMap.get("IP").toString();
						String get_stay_uri    = logMap.get("STAY_URI").toString();
						String get_access_time = logMap.get("ACCESS_TIME").toString();

						//페이지가 변경되었다면[!stay_uri.equals(get_stay_uri)] 체류시간 찍는다.
						if( sessionId.equals(get_session_id) && ip.equals(get_ip) && !stay_uri.equals(get_stay_uri) ){
							Map<String, String> updateLogMap = new HashMap<String, String>();
							updateLogMap.put("TABLE_NAME",      tableName);
							updateLogMap.put("GET_ACCESS_TIME", get_access_time);
							updateLogMap.put("GET_PID",         get_pid);

							int result = logManagerService.update_log_new_by_pid(updateLogMap);
						}
					}
				}
			}
			//체류시간[e]

			//검색어 로그[s]
			//쿠키값 가져오기
			String slPk      = "";
			String sl_stay   = "N";
		    Cookie[] cookies = request.getCookies();
		    if(cookies != null){
		        for(int i=0; i < cookies.length; i++){
		            Cookie c = cookies[i];
		            String cName = c.getName();
		            if( "SEARCH_LOG_PK".equals(cName) ) {
		            	slPk = c.getValue();
		            	break;
		            }
		        }
		    }

			//검색어 있는경우만 로그 쌓는다.
			if( !"".equals(slPk) ){
				//아래 조건의 URL로 갈경우는 그페이지 계속 머무는걸로
				if( uri.startsWith("/partner") || uri.startsWith("/product/main") || uri.startsWith("/projectEnrollRFP")) {
					sl_stay = "Y";
				}

				try {
					//arr = new String[] {uri, sl_stay, slPk};
					//insert_table("update_search_log", map, arr);

					Map<String, Object> searchLogMap = new HashMap<String, Object>();
					searchLogMap.put("uri",     uri);
					searchLogMap.put("sl_stay", sl_stay);
					searchLogMap.put("slPk",    slPk);
					int res = logManagerService.update_search_log(searchLogMap);

					Cookie kc = new Cookie("SEARCH_LOG_PK", null);
					kc.setMaxAge(0); // 유효시간을 0으로 설정
					kc.setPath("/");
					response.addCookie(kc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//검색어 로그[e]

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("TABLE_NAME", tableName);

			//로그인할때면
			if( login_date ){
				map.put("LOGIN_DATE",      "LOGIN_DATE");
			}

			//로그아웃할때면
			if( logout_date ){
				map.put("LOGOUT_DATE",      "LOGOUT_DATE");
			}

			//첫방문이면(로그인전/로그인할때) 날짜 넣음
			if( first_visit ){
				map.put("FIRST_VISIT",      "FIRST_VISIT");
				map.put("FIRST_VISIT_TIME", first_visit_time);
			}

			Map<String, Object> logNewMap = new HashMap<String, Object>();
			logNewMap.put("TABLE_NAME",       tableName                                    );
			logNewMap.put("SESSIONID",        sessionId                                    );
			logNewMap.put("IP",               ip                                           );
			logNewMap.put("URI",              uri                                          );
			logNewMap.put("QUERYSTRING",      queryString                                  );
			logNewMap.put("REFERER",          referer                                      );
			logNewMap.put("UPK",              uPk                                          );
			logNewMap.put("TXT",              txt                                          );
			logNewMap.put("A_ID",             a_id                                         );
			logNewMap.put("GUBUN_LOGIN",      gubun_login                                  );
			logNewMap.put("COOKIE_NAME",      cookie_name                                  );
			logNewMap.put("GUBUN",            gubun                                        );
			logNewMap.put("LOGIN_ID",         login_id                                     );
			logNewMap.put("CATEGORY",         category                                     );
			logNewMap.put("PAGEVIEW_PERCENT", pageview_percent                             );
			logNewMap.put("ISMOBILE",         String.valueOf(Utils.isMobile(request))      );
			logNewMap.put("STAY_URI",         stay_uri                                     );
			logNewMap.put("STAY_TIME",        stay_time                                    );

			try {
				//로그쌓기
				int result = logManagerService.log_new(logNewMap);

			} catch (Exception e) {
				e.printStackTrace();

				try {
					StringBuffer sb = new StringBuffer();
					sb.append("  CREATE TABLE castingn_log."+tableName+" ( ");
					sb.append(" 		PID 	            int PRIMARY KEY NOT NULL AUTO_INCREMENT,	               ");
					sb.append(" 		SESSION_ID	        varchar(255),                                              ");
					sb.append(" 		IP	                varchar(15),                                               ");
					sb.append(" 		URI	                varchar(255),                                              ");
					sb.append(" 		QUERY_STRING        text,                                                      ");
					sb.append(" 		REFERER             text,                                                      ");
					sb.append(" 		U_PK	            int,                                                       ");
					sb.append(" 		DATA	            text,                                                      ");
					sb.append(" 		ACCESS_TIME	        datetime,                                                  ");
					sb.append(" 		USER_AGENT	        int,                                                       ");
					sb.append(" 		GUBUN_LOGIN         varchar(10)  DEFAULT NULL COMMENT '로그인 구분',           ");
					sb.append(" 	    COOKIE_NAME         varchar(255) DEFAULT NULL COMMENT '쿠키명',                ");
					sb.append(" 	    GUBUN               varchar(10)  DEFAULT NULL COMMENT '구분',                  ");
					sb.append(" 	    LOGIN_ID            varchar(255) DEFAULT NULL COMMENT '로그인아이디',          ");
					sb.append(" 	    LOGIN_DATE          datetime     DEFAULT NULL,                                 ");
					sb.append(" 	    LOGOUT_DATE         datetime     DEFAULT NULL,                                 ");
					sb.append(" 	    CATEGORY            text,                                                      ");
					sb.append(" 	    PAGEVIEW_PERCENT    int(11)      DEFAULT NULL,                                 ");
					sb.append(" 	    FIRST_VISIT         datetime     DEFAULT NULL COMMENT '최초방문일',            ");
					sb.append(" 	    FIRST_VISIT_TIME    datetime     DEFAULT NULL COMMENT '최초방문일(쿠키시간)',  ");
					sb.append(" 	    IS_MOBILE           VARCHAR(10)  COMMENT '디바이스구분[MOBILE|WEB]',           ");
					sb.append(" 	    STAY_URI            VARCHAR(255) COMMENT '체류할페이지URI',                    ");
					sb.append(" 	    STAY_TIME           VARCHAR(20)  COMMENT '체류시간[초단위]'                    ");
					sb.append(" ) ");

					logNewMap.put("CREATE_TABLE_SQL", sb.toString());

					//테이블 생성
					logManagerService.log_make_table_new(logNewMap);

					//로그쌓기
					int result = logManagerService.log_new(logNewMap);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			String result = (String)jp.proceed();

			logger.info("메서드 정상 종료 [LogManager] : " + jp.getSignature());

			return result;

		} catch (Exception e) {
			logger.info("메서드 비정상 종료 [LogManager] : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
	*/
}
