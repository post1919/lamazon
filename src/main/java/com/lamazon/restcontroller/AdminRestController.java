package com.lamazon.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamazon.service.AdminService;
import com.lamazon.service.LoginService;

@RestController
@RequestMapping("rest/admin")
public class AdminRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	@Autowired
	AdminService adminService;

	@Autowired
	LoginService loginService;

	/*
	@PostMapping(path="user/point_update", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String point_update(
		  @RequestParam(name="U_PK",    required=true) String U_PK
		, @RequestParam(name="U_POINT", required=true) String U_POINT
	){
		HashMap<String, String> map = new HashMap<String, String>();

		int uPk = loginService.getUPK(ContextUtil.getRequest());
		String res = Properties.JSON_MESSAGE_HAS_ERROR;

		System.out.println("U_PK => " + U_PK);
		System.out.println("U_POINT => " + U_POINT);
		System.out.println("U_MOCODE => " + uPk);

		if( uPk != -1 ) {
			map.put("U_PK",     U_PK);
			map.put("U_POINT",  U_POINT);
			map.put("U_MOCODE", uPk+"");

			int rescnt = adminService.point_update(map);
			if( rescnt > 0 ) res = Properties.JSON_MESSAGE_SUCCESS;
		}

		return res;
	}
	 */
}
