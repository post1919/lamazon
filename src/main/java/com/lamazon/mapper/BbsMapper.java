package com.lamazon.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BbsMapper {

	int bbs_cnt(HashMap<String, String> map);

	ArrayList<Map> bbs_list(HashMap map);

	Map bbs(Map map);

	ArrayList<Map> bbs_reply(Map map);

	int bbs_insert(HashMap map);

	int bbs_update(HashMap map);

	int bbs_reply_update(HashMap map);

	int bbs_reply_insert(HashMap map);
}
