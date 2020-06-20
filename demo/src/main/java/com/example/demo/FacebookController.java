package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacebookController {

	@GetMapping("/facebook/link")
	public String link(FacebookVO vo, Model model) {
//		oga1(vo);
		System.out.println("url: " + vo.getUrl());
		model.addAttribute("og", vo);
		return "facebook/link";
	}

	@GetMapping("/facebook/og")
	public String og(FacebookVO vo, Model model) {
//		oga1(vo);
		model.addAttribute("og", vo);
		return "facebook/og";
	}

	private void oga1(FacebookVO vo) {
		String today = getToday();
		if (StringUtils.isEmpty(vo.getUrl())) {
//			vo.setUrl("http://godsoft.iptime.org:8080/facebook/og");
			vo.setUrl("http://godsoft.iptime.org:8080/1.html");
		}
		if (StringUtils.isEmpty(vo.getTitle())) {
			vo.setTitle("제목 " + today);
		}
		if (StringUtils.isEmpty(vo.getDescription())) {
			vo.setDescription("설명 " + today);
		}
		if (StringUtils.isEmpty(vo.getImage())) {
			vo.setImage("http://godsoft.iptime.org:8080/10956906_396737803821010_168799778_n.png");
		}
	}

	private String getToday() {
		Date date = new Date();
		String strDateFormat = "yyyy-mm-dd hh:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		return dateFormat.format(date);

	}

}
