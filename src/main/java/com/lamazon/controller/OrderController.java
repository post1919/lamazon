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
import com.lamazon.service.CommonService;
import com.lamazon.service.LoginService;
import com.lamazon.service.OrderService;
import com.lamazon.util.ContextUtil;

@Controller
@RequestMapping("order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdminService adminService;

	@Autowired
	OrderService orderService;

	@GetMapping(path="list")
	public String order_list(Model model) {

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

		return "order/order_list";
	}

	@GetMapping(path="jungsan_list")
	public String jungsan_list(Model model) {

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

		return "order/jungsan_list";
	}

	@GetMapping(path="detail")
	public String order_detail(
			  @RequestParam(value="flag", required=false) String flag
			, @RequestParam(value="o_pk", required=false) String o_pk
			, @RequestParam(value="actionType", required=false) String actionType
			, Model model
	) throws IOException {
		try {
			String uPk = String.valueOf(loginService.getUPK(ContextUtil.getRequest()));

			Map map = new HashMap();
			//마켓구분
			map.put("code", "O_MARKET_GUBUN");
			model.addAttribute("O_MARKET_GUBUN",  commonService.commonCodeList(map));

			model.addAttribute("flag", flag);

			//상세
			if( !"regist".equals(flag) && o_pk != null ) {
				map.put("O_PK", o_pk);
				Map orderMaster = orderService.getOrderMaster(map);

				if( orderMaster == null ) {
					return "exception/error";

				} else if( !uPk.equals(String.valueOf(orderMaster.get("R_U_PK"))) && !loginService.isAdmin(ContextUtil.getRequest()) ) {
					return "exception/no_permission";
				}

				model.addAttribute("ORDER_MASTER", orderMaster);

				ArrayList orderDetail = orderService.getOrderDetail(map);

				model.addAttribute("ORDER_DETAIL", orderDetail);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return "template/order/detail";
	}
	
	@GetMapping(path="upload")
	public String upload(Model model) {

		return "popup/order/order_upload";
	}
}
