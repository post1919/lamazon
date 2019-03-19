package com.lamazon.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lamazon.service.LoginService;

@Controller
@RequestMapping("logout.cast")
public class LogoutController {

	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@Autowired
	LoginService loginService;

	@GetMapping
	public String logout(HttpServletRequest request, HttpServletResponse response){

		//로그아웃
		loginService.logout(request);

		String uri = request.getRequestURI();
		try {
			uri = URLDecoder.decode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String[] acts = uri.split("/");
		String act = "";

		if( acts != null && acts.length > 2 ){
			act = acts[2];
		}

		String url = "/";

		return "redirect:"+url;
	}
}
