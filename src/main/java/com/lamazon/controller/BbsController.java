package com.lamazon.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lamazon.service.AdminService;
import com.lamazon.service.BbsService;
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.util.ContextUtil;

@Controller
@RequestMapping("bbs")
public class BbsController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	BbsService bbsService;

	@GetMapping(path="list")
	public String bbs_list(Model model) {

		//오늘 날짜 YYYY-MM-DD
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("toDate", sf.format(date));

		//1주일전 날짜 YYYY-MM-DD
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, -2);
		date = cal.getTime();
		sf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("fromDate",   sf.format(date));

		return "bbs/bbs_list";
	}

	@GetMapping(path="detail")
	public String bbs_detail(
			  @RequestParam(value="flag", required=false) String flag
			, @RequestParam(value="b_pk", required=false) String b_pk
			, @RequestParam(value="actionType", required=false) String actionType
			, Model model
	) throws IOException {
		try {
			String uPk = String.valueOf(loginService.getUPK(ContextUtil.getRequest()));

			Map map = new HashMap();
			//map.put("code", "O_MARKET_GUBUN");
			//model.addAttribute("O_MARKET_GUBUN",  commonService.commonCodeList(map));

			model.addAttribute("flag", flag);

			//상세
			if( !"regist".equals(flag) && b_pk != null ) {
				map.put("B_PK", b_pk);
				Map bbs_list = bbsService.bbs(map);

				if( bbs_list == null ) {
					return "exception/error";

				} else if( !uPk.equals(String.valueOf(bbs_list.get("R_U_PK"))) && !loginService.isAdmin(ContextUtil.getRequest()) ) {
					return "exception/no_permission";
				}
				
				//자기글인지 여부
				if( uPk.equals(String.valueOf(bbs_list.get("R_U_PK"))) ){
					bbs_list.put("MINE", "MINE");
				}
				
				model.addAttribute("BBS", bbs_list);

				ArrayList<Map> bbs_reply = bbsService.bbs_reply(map);
				
				if( bbs_reply != null ) {
					for(int i=0; i<bbs_reply.size(); i++) {
						//자기글인지 여부
						if( uPk.equals(String.valueOf(bbs_reply.get(i).get("BR_INCODE"))) ) {
							bbs_reply.get(i).put("MINE", "MINE");
						}
					}
				}
				
				model.addAttribute("BBS_REPLY", bbs_reply);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return "bbs/bbs_detail";
	}
}
