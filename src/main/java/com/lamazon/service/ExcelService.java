package com.lamazon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ExcelService {

	ArrayList manage_partner_updatelist(HashMap<String, String> _map);

	int insert_lms_reservation(Map map);

}
