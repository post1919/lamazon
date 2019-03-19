package com.lamazon.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ExcelMapper {

	ArrayList manage_partner_updatelist(HashMap<String, String> map);

	int insert_lms_reservation(Map map);

}
