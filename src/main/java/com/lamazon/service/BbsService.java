package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BbsService {
	public int bbs_cnt(HashMap map);

	public ArrayList<Map> bbs_list(HashMap map);

	public Map bbs(Map map);

	public ArrayList<Map> bbs_reply(Map map);

	public int bbs_insert(HashMap map);

	public int bbs_update(HashMap map);

	public int bbs_reply_update(HashMap map);

	public int bbs_reply_insert(HashMap map);
}
