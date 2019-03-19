package com.lamazon.service.impl;

/*
 * DBManager.java
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lamazon.mapper.CommonMapper;
import com.lamazon.model.AdminMenu;
import com.lamazon.service.CommonService;

@Transactional
@Service
//@Scope("application")
public class CommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	CommonMapper commonMapper;

	private Map<String, Map> categoryMap = null;
	private Map jongcodeMap = null;
	private Map blog_categoryMap = null;
	private ArrayList categoryFilterListTree = null; // father에 따른 카테고리 필터 리스트
	//private List<Map> officeCategoryList = null;

	private List<Map> categoryListTree  = null; // father에 따른 카테고리 리스트
	private List<Map> jongcodeListTree = null;
	private List<AdminMenu> adminMenuListTree = null; // father에 따른 어드민 메뉴
	private static HashMap<String, Object> agents = null;

	private HashMap<String, HashMap<String, Object>> lock_ids = new HashMap<String, HashMap<String, Object>>(); // 아이디 중복 체크시 ID를 락 걸어둔다.

	private static final String categoryCodebaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Override
	public String getCategoryCode(int depth, int length) {
		if(depth==0) return null;

		if(length==1) depth += 9;

		int base = categoryCodebaseDigits.length();

		String tempVal = "";
        int mod = 0;
        while (depth != 0) {
            mod = depth % base;
            tempVal = categoryCodebaseDigits.substring(mod, mod+1) + tempVal;
            depth = depth / base;
        }
        if(length==2) {
        	if(tempVal.length()==1) tempVal = "0"+tempVal;
        }
        return tempVal;
	}

	/**
	 * 어드민
	 * 메뉴 가져오기
	 */
	@Override
	public List<AdminMenu> adminMenuListTree(int parent, String status) throws IOException {

		if( adminMenuListTree == null || adminMenuListTree.size() == 0 ){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("AM_PK", parent);

			if( "1".equals(status) ) {
				map.put("AM_STATUS", status);
			}

			List<AdminMenu> adminMenuListTree = commonMapper.adminMenuListByParent(map); //1차

			for( AdminMenu c : adminMenuListTree ) {
				map.put("AM_PK", c.getAM_PK());
				List<AdminMenu> list2 = commonMapper.adminMenuListByParent(map); //2차

				//for( AdminMenu c2 : list2 ) {
				//	List<AdminMenu> list3 = commonMapper.categoryListByFather(c2.getAM_PK()); //3차
				//	c.setCHILD2(list3);
				//}

				c.setCHILD1(list2);
			}

			this.adminMenuListTree = adminMenuListTree;
		}

		return this.adminMenuListTree;
	}

	/**
	 * 어드민
	 * 메뉴 가져오기
	 * 관리자 메뉴 전체
	 */
	@Override
	public List<AdminMenu> adminMenuListTreeAll() throws IOException {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("AM_PK", 0);

			List<AdminMenu> adminMenuListTree = commonMapper.adminMenuListByParent(map); //1차
			List<AdminMenu> newList1 = new ArrayList<AdminMenu>();

			for( AdminMenu c : adminMenuListTree ) {
				map.put("AM_PK", c.getAM_PK());
				List<AdminMenu> list2 = commonMapper.adminMenuListByParent(map); //2차
				List<AdminMenu> newList2 = new ArrayList<AdminMenu>();

				for( AdminMenu c2 : list2 ) {
					map.put("AM_PK", c2.getAM_PK());
					List<AdminMenu> list3 = commonMapper.adminMenuListByParent(map); //3차
					c2.setCHILD2(list3);
					newList2.add(c2);
				}

				c.setCHILD1(newList2);
				newList1.add(c);
			}

			this.adminMenuListTree = newList1;

		return this.adminMenuListTree;
	}

	@Override
	public List<Map> categoryListByFather(Map map) throws IOException {
//		return DBManager.getInstance().makeMapArray("category_list_by_father", new HashMap<String, String>(), new String[] {father});

		return commonMapper.category_list_by_father(map);
	}

	/**
	 * 어드민
	 * 현재화면에 해당하는 URI의 FULLNAME 가져오기
	 */
	@Override
	public AdminMenu getAdminMenuFullname(String requestURI) {
		AdminMenu adminMenu = commonMapper.getAdminMenuFullname(requestURI);
		return adminMenu;
	}

	/**
	 * 메뉴 가져오기
	 */
	@Override
	public List<Map> categoryListTree() throws IOException {

		if(categoryListTree==null || categoryListTree.size()==0)
			try {
				this.initCategoryInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return this.categoryListTree;
	}

	@Override
	public void initCategoryInfo() throws IOException {
		Map categoryAllMap = new HashMap();
		//categoryAllMap.put("CODE", "A-01");
		//categoryAllMap.put("PK", 10);
		List<Map> category_all = commonMapper.category_all(categoryAllMap);

		//목록데이터를 가져와 CODE값을 키, 값을 ROW로 가지는 새로운 맵생성
		categoryMap = makeMapInMap(category_all, "CODE");

		Map cateMap = new HashMap();
		cateMap.put("father", "0");
		//cateMap.put("ORDER_OPTION", "random");

		List<Map> list = categoryListByFather(cateMap); // 1차

		for( Map map : list ) {
			cateMap.put("father", (int)map.get("PK"));
			List<Map> list2 = this.categoryListByFather(cateMap); //2차

			for( Map map2 : list2 ) {
				cateMap.put("father", (int)map2.get("PK"));
				List<Map> list3 = this.categoryListByFather(cateMap); //3차
				map2.put("CHILD", list3);
			}

			map.put("CHILD", list2);
		}
		categoryListTree = list;

		//해당 데이터(this.category_for_office)ROW가 없어서 사용안하는거같음
		//this.officeCategoryList = this.category_for_office();
	}

	//List안의 Map을 새로운 Map넣기
	@Override
	public Map makeMapInMap(List<? extends Map> list, String keyName) {

		Map newMap = new HashMap();
		for( Map map : list ) {
			newMap.put(map.get(keyName), map);
		}

		return newMap;
	}

	@Override
	public List<Map> jongcodeListTree() {

		if(jongcodeListTree==null || jongcodeListTree.size()==0)
			try {
				this.initJongCodeInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return this.jongcodeListTree;
	}

	@Override
	public List<Map> categoryListTreeWithoutAll() {

		if(categoryListTree==null || categoryListTree.size()==0)
			try {
				this.initCategoryInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return this.categoryListTree;
	}

	@Override
	public void initJongCodeInfo() throws IOException {
		jongcodeListTree = jongcodeListByFather(); // 1차
	}

	@Override
	public List<Map> jongcodeListByFather() throws IOException {
		return commonMapper.common_code_all();
	}

	//public List<Map> category_for_office() throws IOException {
	//	return commonMapper.category_for_office();
	//}

	@Override
	public void lockId_insert(String id, HashMap<String, Object> info) {
		lock_ids.put(id, info);
	}

	// 가입시 확인
	@Override
	public boolean lockIdIsMine(String id, HashMap<String, Object> info) {
		String ip = (String)info.get("IP");
		String session_id = (String)info.get("SESSION_ID");

		Map lockInfo = null;
		//lockInfo = this.makeMap("user_id_lock_select", new HashMap(), new String[]{id});
		lockInfo = commonMapper.user_id_lock_select(id);

		if(lockInfo==null) {
			//this.insert_table("user_id_lock_insert", new HashMap(), new String[]{id, ip, session_id});
			Map map = new HashMap();
			map.put("id", id);
			map.put("ip", ip);
			map.put("session_id", session_id);
			commonMapper.user_id_lock_insert(map);
			return true;
		} else {
			if(ip.equals(lockInfo.get("IP"))) { // && session_id.equals((String)lockInfo.get("SESSION_ID")) ) {
				return true;
			} else {
				return false;
			}
		}
	}

	// 가입시 UID 중복확인
	@Override
	public boolean dupCheckU_ID(String id) {
		boolean flag = false;
		int cnt = commonMapper.user_id_dup_select(id);

		if(cnt == 0) {
			flag = true;
		} else {
			flag =  false;
		}
		return flag;
	}

	// ID 가 lock 되었는 지 확인 후 그렇지 않으면 lock 함.
	@Override
	public boolean lockIdisValid(String id, HashMap<String, Object> info) {
		return lockIdIsMine(id, info);
	}

	// 30분 이상 경과된 locked id remove
	@Override
	public void lockIdClean() {
		try {
			Iterator<String> keys = lock_ids.keySet().iterator();

			long currentTime = System.currentTimeMillis();

			long offset = 30*60*1000; // 등록된 후 30분 이상 경과후 삭제됨

			ArrayList<String> list = new ArrayList<String>();

			while( keys.hasNext() ){
	            String key = keys.next();
	            HashMap<String, Object> info = lock_ids.get(key);

	            long time = (long)info.get("TIME");

	            if((currentTime-time)>offset) {
	            	list.add(key);
	            }
	        }

			for(int i=0;i<list.size();i++) {
				String key = list.get(i);
				lock_ids.remove(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가입완료 후 rock 삭제
	// 관리자에서 파트너 id 변경 후 사용
	@Override
	public void lockIdRemove(String ip, String sessionId) {
		try {
			Iterator<String> keys = lock_ids.keySet().iterator();

			ArrayList<String> list = new ArrayList<String>();

			while( keys.hasNext() ){
	            String key = keys.next();
	            HashMap<String, Object> info = lock_ids.get(key);

	            String info_ip = (String)info.get("IP");
	            String info_session_id = (String)info.get("SESSION_ID");

	            if(ip.equals(info_ip) && sessionId.equals(info_session_id)) {
	            	list.add(key);
	            }
	        }

			for(int i=0;i<list.size();i++) {
				String key = list.get(i);
				lock_ids.remove(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	public ArrayList categoryListForOffice() {
		if(this.officeCategoryList==null) {
			try {
				this.initCategoryInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.officeCategoryList;
	}
	*/


	/**
	 * 카테고리 필터 리스트
	 * @return
	 */
	@Override
	public ArrayList categoryFilterListTree( String catecode ) {
		ArrayList list = null;
			try {
				list = categoryFilterInfo( catecode );
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;
	}

	/**
	 * 카테고리 필터 가져오기
	 * @throws IOException
	 */
	@Override
	public ArrayList categoryFilterInfo(String catecode) throws IOException {

		ArrayList list = categoryFilterListByCateCode(catecode);//카테고리 필터 제목

		for(int i=0;i<list.size();i++) {
			Map c = (HashMap)list.get(i);

			ArrayList list2 = categoryFilterContentByRtPk((String)c.get("RT_PK")); //2차

			for(int j=0;j<list2.size();j++) {
				Map c2 = (HashMap)list2.get(j);
				ArrayList list3 = categoryListByFather((String)c2.get("PK")); //2차
				c2.put("CHILD", list3);

			}
			c.put("CHILD", list2);
		}
		return list;
	}

	@Override
	public void initBlogCategoryInfo() throws IOException {
		Map _bMap = this.makeMapInMap(commonMapper.common_code_all(), "CM_CODE");
		blog_categoryMap = _bMap;
	}

	@Override
	public void initCategoryInfoWithoutAll() throws IOException {
		Map _cMap = this.makeMapInMap(commonMapper.category_without_all(), "CODE");
		categoryMap = _cMap;

		ArrayList list = categoryListByFather("0"); // 1차

		for(int i=0;i<list.size();i++) {
			Map c = (HashMap)list.get(i);

			ArrayList list2 = categoryListByFather((String)c.get("PK")); //2차

			for(int j=0;j<list2.size();j++) {
				Map c2 = (HashMap)list2.get(j);
				ArrayList list3 = categoryListByFather((String)c2.get("PK")); //2차
				c2.put("CHILD", list3);
			}
			c.put("CHILD", list2);
		}
		categoryListTree = list;

		//this.officeCategoryList = this.makeMapArray("category_for_office", map, arr);
	}

	@Override
	public Map category(String code) throws IOException {
		if(categoryMap==null) initCategoryInfo();
		Map map = null;
		if(categoryMap.get(code)!=null) map = categoryMap.get(code);
		return map;
	}

	@Override
	public Map jongcode(String code) throws IOException {
		if(jongcodeMap==null) initJongCodeInfo();
		Map map = null;
		if(jongcodeMap.get(code)!=null) map = (HashMap)jongcodeMap.get(code);
		return map;
	}

	@Override
	public Map blog_category(String code) throws IOException {
		if(blog_categoryMap==null) initBlogCategoryInfo();
		Map map = null;
		if(blog_categoryMap.get(code)!=null) map = (HashMap)blog_categoryMap.get(code);
		return map;
	}

	@Override
	public Map categoryByPk(String pk) throws IOException {
		//return DBManager.getInstance().makeMap("category_by_pk", new HashMap<String, String>(), new String[] {pk});
		return commonMapper.category_by_pk(Integer.parseInt(pk));
	}

	@Override
	public Map upjongByPk(String pk) throws IOException {
		//return DBManager.getInstance().makeMap("upjong_by_pk", new HashMap<String, String>(), new String[] {pk});
		return commonMapper.upjong_by_pk(Integer.parseInt(pk));
	}

	@Override
	public Map rfpByPk(String catecode, String pk) throws IOException {
		//return DBManager.getInstance().makeMap("rfp_by_pk", new HashMap<String, String>(), new String[] {catecode, pk});
		Map map = new HashMap();
		map.put("catecode", catecode);
		map.put("pk",       Integer.parseInt(pk));
		return commonMapper.rfp_by_pk(map);
	}

	@Override
	public Map rfpTitleByCategory(String catecode) throws IOException {
		//return DBManager.getInstance().makeMap("rfp_title_by_catecode", new HashMap<String, String>(), new String[] {catecode});
		return commonMapper.rfp_title_by_catecode(catecode);
	}

	@Override
	public Map rfpContentByPk(String pk) throws IOException {
		//return DBManager.getInstance().makeMap("rfp_content_by_pk", new HashMap<String, String>(), new String[] {pk});
		return commonMapper.rfp_content_by_pk(Integer.parseInt(pk));
	}

	@Override
	public ArrayList categoryListByFather(String father) throws IOException {
		//return DBManager.getInstance().makeMapArray("category_list_by_father", new HashMap<String, String>(), new String[] {father});
		return commonMapper.category_list_by_father(Integer.parseInt(father));
	}

	@Override
	public ArrayList upjongListByFather(String father) throws IOException {
		//return DBManager.getInstance().makeMapArray("upjong_list_by_father", new HashMap<String, String>(), new String[] {father});

		return commonMapper.upjong_list_by_father(Integer.parseInt(father));
	}

	@Override
	public ArrayList upjongListBy3th(String father) throws IOException {
		//return DBManager.getInstance().makeMapArray("upjong_list_by_3th", new HashMap<String, String>(), new String[] {father});
		return commonMapper.upjong_list_by_3th(Integer.parseInt(father));
	}

	@Override
	public ArrayList blogCategoryListByFather(String father) throws IOException {
		//return DBManager.getInstance().makeMapArray("common_code_list", new HashMap<String, String>(), new String[] {father});

		Map map = new HashMap();
		map.put("code", father);

		return commonMapper.common_code_list(map);
	}

	/**
	 * 카테고리 필터 타이틀
	 * @param father
	 * @return
	 * @throws IOException
	 */
	@Override
	public ArrayList categoryFilterListByCateCode(String catecode) throws IOException {
		//return DBManager.getInstance().makeMapArray("category_filter_list_by_catecode", new HashMap<String, String>(), new String[] {catecode});
		return commonMapper.category_filter_list_by_catecode(catecode);
	}

	/**
	 * 카테고리 필터 상세
	 * @param father
	 * @return
	 * @throws IOException
	 */
	@Override
	public ArrayList categoryFilterContentByRtPk(String rt_pk) throws IOException {
		//return DBManager.getInstance().makeMapArray("category_filter_list_by_rtpk", new HashMap<String, String>(), new String[] {rt_pk});
		return commonMapper.category_filter_list_by_rtpk(Integer.parseInt(rt_pk));
	}

	/**
	 * 해당카테고리
	 * @param father
	 * @param up_code
	 * @return
	 * @throws IOException
	 */
	@Override
	public ArrayList blogCategoryListByFather(String father, String up_code) throws IOException {
		//return DBManager.getInstance().makeMapArray("common_code_list_group", new HashMap<String, String>(), new String[] {father,up_code});
		Map map = new HashMap();
		map.put("father", father);
		map.put("up_code", up_code);
		return commonMapper.common_code_list_group(map);
	}

	@Override
	public ArrayList categoryTree(String code) throws IOException {
		ArrayList list = new ArrayList();

		if(code==null || code.length()==0) return list;

		StringTokenizer st = new StringTokenizer(code,"-");

		String _code = "";
		while(st.hasMoreTokens()){

			if(_code.length()>0) {
				_code += "-";
			} else {

			}
			_code += st.nextToken();

			Object o = category(_code);

			if(o!=null) {
				list.add(o);
			}
		}
		return list;
	}

	@Override
	public void syncUserProjectInfo(String uPk) {

		try {
			int num          = commonMapper.project_count_by_upk(Integer.parseInt(uPk));
			int num_contract = commonMapper.project_count_contract_num_by_upk(Integer.parseInt(uPk));
			int num_ing      = commonMapper.project_count_ing_num_by_upk(Integer.parseInt(uPk));
			int num_done     = commonMapper.project_count_done_num_by_upk(Integer.parseInt(uPk));
			int price        = commonMapper.project_sum_price_by_upk(Integer.parseInt(uPk));

			Map map = new HashMap();
			map.put("num",          num);
			map.put("num_contract", num_contract);
			map.put("num_ing",      num_ing);
			map.put("num_done",     num_done);
			map.put("price",        price);
			map.put("uPk",          uPk);

			commonMapper.user_info_update_project_num(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void syncPartnerProjectInfo(String cPk) {
		try {
			int num          = commonMapper.project_apply_count_by_cpk(Integer.parseInt(cPk));
			int num_contract = commonMapper.project_contract_by_cpk(Integer.parseInt(cPk));
			int price        = commonMapper.project_sum_price_by_cpk(Integer.parseInt(cPk));

			Map map = new HashMap();
			map.put("num",          num);
			map.put("num_contract", num_contract);
			map.put("price",        price);
			map.put("cPk",          cPk);

			commonMapper.project_contract_update_partner(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void syncProjectNum(String categoryCode) {
		String[] arr = categoryCode.split("-");

		String out = "";
		for(String code : arr) {
			out += code;
			_syncProjectNum(out);
			out += "-";
		}
	}

	private void _syncProjectNum(String categoryCode) {
		try {
			int count = commonMapper.project_count_by_category(categoryCode);

			Map map = new HashMap();
			map.put("count",        count);
			map.put("categoryCode", categoryCode);

			commonMapper.project_update_category_num(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getPartnerPk(String cid) {
		Map map = new HashMap();
		map.put("cid", cid);

		int pk = commonMapper.partner_pk_by_cid(map);

		return pk;
	}

	// TODO 트리거로 교체
	@Override
	public void updateCategoryPartnerNumUpdate(String codes) {
		if(codes.startsWith("|")) {
			codes = codes.substring(1);
		}

		StringTokenizer st = new StringTokenizer(codes,"|");

		while(st.hasMoreTokens()) {
			String code = st.nextToken();

			String code1 = code.substring(0,4);

			_updateCategoryPartnerNumUpdate(code);
			_updateCategoryPartnerNumUpdate(code1);
		}
	}

	private void _updateCategoryPartnerNumUpdate(String code) {
		try {
			int count = commonMapper.category_count_partner_num_by_code(code);

			Map map = new HashMap();
			map.put("count", count);
			map.put("code",  code);

			commonMapper.category_count_partner_num_update(map);
		} catch (Exception  e) {
			e.printStackTrace();
		}

	}

	//새로 쿼리 짜야함 20180928
	@Override
	public int getNewPk(String tableName, String[] map) {
		int pk = 0 ;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(tableName);
		sb.append(" ( ");

		for(int i=0;i<map.length;i++) {
			sb.append(map[i]);
			if(i!=(map.length-1)) sb.append(",");
		}
		sb.append(") values (");

		for(int i=0;i<map.length;i++) {
			sb.append("0");
			if(i!=(map.length-1)) sb.append(",");
		}
		sb.append(")");

		Map paramMap = new HashMap();
		paramMap.put("GENERATE_INSERT", sb.toString());

		commonMapper.getNewPk(paramMap);

		return (int)(long)paramMap.get("PK");
	}

	@Override
	public int getNewPk(String tableName, Map<String, String> map) {
		int pk = 0 ;

		String values = "";
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" ( ");

		int i=0;
		for(Map.Entry entry : map.entrySet()){
			sb.append(entry.getKey());
			values += "'"+entry.getValue()+"'";
			if( i < map.size()-1 ){
				sb.append(",");
				values += ",";
			}
			i++;
    	}

		sb.append(") VALUES (");
		sb.append(values);
		sb.append(")");

		Map paramMap = new HashMap();
		paramMap.put("GENERATE_INSERT", sb.toString());

		commonMapper.getNewPk(paramMap);

		return (int)(long)paramMap.get("PK");
	}

	//타입까지 지정해줌
	@Override
	public int getNewPk(String tableName, Map<String, String[]> map, String defaul) {
		int pk = 0 ;

		String values = "";
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" ( ");

		int i=0;
		String[] arrValue = new String[] {};
		for(Map.Entry entry : map.entrySet()){
			sb.append(entry.getKey());

			arrValue = (String[])entry.getValue();
			if( "int".equals(arrValue[1]) ) {
				values += arrValue[0];
			} else {
				values += "'"+arrValue[0]+"'";
			}

			if( i < map.size()-1 ){
				sb.append(",");
				values += ",";
			}
			i++;
    	}

		sb.append(") VALUES (");
		sb.append(values);
		sb.append(")");

		Map paramMap = new HashMap();
		paramMap.put("GENERATE_INSERT", sb.toString());

		commonMapper.getNewPk(paramMap);

		return (int)(long)paramMap.get("PK");
	}

	public int getNewPk(String tableName, String name, String value) {
		int pk = 0 ;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into "+tableName+" ("+name+") values ('"+value+"')");

		Map paramMap = new HashMap();
		paramMap.put("GENERATE_INSERT", sb.toString());

		commonMapper.getNewPk(paramMap);

		return (int)(long)paramMap.get("PK");
	}

	//데이터 중복체크 => 의뢰등록한 고객의 현재시간-30초이내에 등록된게 있는지 여부
	@Override
	public int getProjectDuplChk(String uPk) {
		//String sql = "SELECT COUNT(*) AS CNT FROM PROJECT WHERE TIMESTAMPDIFF(SECOND, PR_REGISTER_DATE, NOW()) < 30 AND R_U_PK=" + uPk;
		return commonMapper.getProjectDuplChk(Integer.parseInt(uPk));
	}

	@Override
	public Map getUserByPK(String pk) {
		return commonMapper.user_info_all(Integer.parseInt(pk));
	}

	@Override
	public Map getPartnerByPK(String pk) {
		return commonMapper.partner_by_pk(Integer.parseInt(pk));
	}

	@Override
	public Map getPartnerByPK4Manager(String pk) {
		return commonMapper.partner_by_pk_for_manager(Integer.parseInt(pk));
	}

	@Override
	public Map getPartnerByID(String id) {
		return commonMapper.partner_by_cid(id);
	}

	@Override
	public Map getPartnerByUPK(String id) {
		return commonMapper.partner_basicinfo_by_upk(Integer.parseInt(id));
	}

	@Override
	public Map getPartnerByCPK(String id) {
		return commonMapper.partner_basicinfo_by_cpk(Integer.parseInt(id));
	}

	@Override
	public Map getProjectByID(String id) {
		return commonMapper.project_by_pid(id);
	}

	@Override
	public Map getProjectByPk(String pk) {
		return commonMapper.project_by_pk(Integer.parseInt(pk));
	}

	@Override
	public Map getProjectApplyByPK(String pk) {
		return commonMapper.project_apply_by_pk(Integer.parseInt(pk));
	}

	@Override
	public Map getProjectApplyByID(String id) {
		return commonMapper.project_apply_by_id(id);
	}

	@Override
	public Map getProjectApplyByIDWithCPk(String id, String cpk) {
		Map map = new HashMap();
		map.put("id",  id);
		map.put("cpk", cpk);

		return commonMapper.project_apply_by_id_with_cpk(map);
	}

	@Override
	public Map getReviewByIDForManager(String id) {
		return commonMapper.manage_review_by_pk(Integer.parseInt(id));
	}

	@Override
	public Map getPromotionById(String id) {
		return commonMapper.promotion(id);
	}

	@Override
	public Map getPromotionByPk(String pk) {
		return commonMapper.promotion_by_pk(Integer.parseInt(pk));
	}

	/**
	 * pa_id로 파트너 담당자 정보 찾기
	 * @param pa_id
	 * @return
	 */
	@Override
	public Map getCompanyPersonByPAID(String pa_id) {
		return commonMapper.company_person_by_paid(pa_id);
	}

	@Override
	public int getTotalNumOfProject() {
		Map map = new HashMap();
		map.put("FOR_CASTINGN", "and pr_from=''");
		map.put("FOR_MK",       "and pr_from='mk.co.kr'");
		map.put("FOR_OFFICE",   "and pr_from='officeplus.com'");

		return commonMapper.project_total_number_for_main(map);
	}

	@Override
	public void syncAllProject() throws IOException {

		ArrayList list = commonMapper.sync_list_all_project_u_pk();

		for(int i=0;i<list.size();i++) {
			Map m = (HashMap)list.get(i);

			String uPk = (String)m.get("R_U_PK");

			this.syncUserProjectInfo(uPk);
		}

		ArrayList list2 = commonMapper.sync_list_all_project_apply_c_pk();

		for(int i=0;i<list2.size();i++) {
			Map m = (HashMap)list2.get(i);

			String cPk = (String)m.get("R_C_PK");

			this.syncPartnerProjectInfo(cPk);
		}
	}

	@Override
	public Map getPartnerStatementByCPK(String C_PK) {
		return commonMapper.partner_statement_by_cpk(Integer.parseInt(C_PK));
	}

	@Override
	public int getTotalNumOfSales() {
		Map map = new HashMap();
		map.put("FOR_CASTINGN", "and pr_from=''");
		map.put("FOR_MK", "and pr_from='mk.co.kr'");
		map.put("FOR_OFFICE", "and pr_from='officeplus.com'");

		return commonMapper.sales_total_number_for_main(map);
	}

	//가망 파트너 현황 - 상세
	@Override
	public Map getPartnerOutsourcingByCPK(String id) {
		return commonMapper.manage_partner_outsourcing_detail(Integer.parseInt(id));
	}

	//가망 파트너 현황 - 파일
	@Override
	public Map getPartnerOutsourcingFile(String CO_PK) {
		return commonMapper.manage_partner_outsourcing_file_info(Integer.parseInt(CO_PK));
	}

	@Override
	public void setPQ_sort(String pq_sort, HashMap<String, String>map, String defaultOrdering) {
		if(pq_sort!=null) {
			pq_sort = pq_sort.substring(1,pq_sort.length()-1);
			JSONObject order = new JSONObject(pq_sort);

			String colName = order.getString("dataIndx");
			String dir	= order.getString("dir");

			if("up".equals(dir)) dir = "ASC";
			else dir = "DESC";

			map.put("ORDERING", "ORDER BY "+colName+" "+dir);
		} else {
			map.put("ORDERING", defaultOrdering);
		}
	}

	@Override
	public int sejong_lms_insert(Map map) {
		return commonMapper.sejong_lms_insert(map);
	}

	@Override
	public List<Map> commonCodeList(Map map) throws IOException {
		return commonMapper.common_code_list(map);
	}

	@Override
	public List<Map> common_code_list(Map map) throws IOException {
		return commonMapper.common_code_list(map);
	}

	@Override
	public int user_notification_insert(HashMap<String, String> map) {
		return commonMapper.user_notification_insert(map);
	}

	@Override
	public Map<String, String> mail_info_detail(Map<String, String> map) {
		return commonMapper.mail_info_detail(map);
	}

	@Override
	public Map<String, String> mail_info_template_detail(Map<String, String> map) {
		return commonMapper.mail_info_template_detail(map);
	}

	@Override
	public int xmail_save_info(Map<String, String> map) {
		return commonMapper.xmail_save_info(map);
	}

	@Override
	public ArrayList<Map<String, String>> user_notification_message_texts(HashMap<String, String> map) {
		return commonMapper.user_notification_message_texts(map);
	}

	@Override
	public String company_check_registration_number(String number) {
		return commonMapper.company_check_registration_number(number);
	}

	@Override
	public ArrayList<Map> category_list_by_father(Map map) {
		return commonMapper.category_list_by_father(map);
	}

	@Override
	public ArrayList<Map> category_list_by_father_new(Map map) {
		return commonMapper.category_list_by_father_new(map);
	}

	@Override
	public ArrayList<Map> category_list_depth2_by_father(Map map) {
		return commonMapper.category_list_depth2_by_father(map);
	}

	@Override
	public ArrayList<Map> category_list_depth3_by_father(Map map) {
		return commonMapper.category_list_depth3_by_father(map);
	}

	@Override
	public ArrayList<Map> category_list_depth1(Map map) {
		return commonMapper.category_list_depth1(map);
	}

	@Override
	public ArrayList<Map> category_list_depth3(Map map) {
		return commonMapper.category_list_depth3(map);
	}

	@Override
	public ArrayList<Map> category_list_depth2(Map map) {
		return commonMapper.category_list_depth2(map);
	}

	@Override
	public Map user_info_project_by_pk(int code) {
		return commonMapper.user_info_project_by_pk(code);
	}

	@Override
	public Map kakao_msg_mst_info(String titleKey) {
		return commonMapper.kakao_msg_mst_info(titleKey);
	}

	@Override
	public void kakao_insert(HashMap<String, String> map) {
		commonMapper.kakao_insert(map);
	}

	@Override
	public Map get_project_allinfo(String prPk) {
		return commonMapper.get_project_allinfo(prPk);
	}

	@Override
	public String category_max_depth(String father) {
		return commonMapper.category_max_depth(father);
	}

	@Override
	public String category_max_pk() {
		return commonMapper.category_max_pk();
	}

	@Override
	public void manage_category_update_fullname_all1() {
		commonMapper.manage_category_update_fullname_all1();
	}

	@Override
	public void manage_category_update_fullname_all2() {
		commonMapper.manage_category_update_fullname_all2();
	}

	@Override
	public void manage_category_update_fullname_all3() {
		commonMapper.manage_category_update_fullname_all3();
	}

	@Override
	public int update_project_pr_status(HashMap<String, Object> map) {
		return commonMapper.update_project_pr_status(map);
	}

	@Override
	public Map get_project_allinfo_by_prpk(String prPk) {
		return commonMapper.get_project_allinfo_by_prpk(prPk);
	}

	@Override
	public Map user_info_all(int parseInt) {
		return commonMapper.user_info_all(parseInt);
	}

	@Override
	public Map get_user_info_by_u_pk(Map map) {
		return commonMapper.get_user_info_by_u_pk(map);
	}

}
