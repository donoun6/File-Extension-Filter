package com.filename.extension.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.filename.extension.main.domain.MainDomain;
import com.filename.extension.main.service.MainService;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	MainService mainService;//Service Bean
	
	@GetMapping
	public String mainPage(Model model) {
		model.addAttribute("main",new MainDomain());
		model.addAttribute("getExtensionByDefaultCheck",mainService.getExtensionByDefaultCheck(true));
		return "index";
	}
	
	@RequestMapping("DefaultVal")
	@ResponseBody // HTTP(Hyper Text Transfer Protocol)통신을 이용해 비동기 통신을 할때에 body공간에 데이터를 담는다.
	public void DefaultVal(@RequestBody Map<String, Object> allData) {
		String defaultVal = (String)allData.get("defaultVal");
		boolean check = (boolean)allData.get("checked");
		mainService.updateCheckBoxByextension(check, defaultVal);
	}
	
	@GetMapping("addType")
	public String ajax(Model model, HttpServletRequest request, HttpServletResponse response) {
		String extension = request.getParameter("type");
		if(extension!="") mainService.addExtension(extension);
		model.addAttribute("getExtensionByDefaultCheck",mainService.getExtensionByDefaultCheck(false));
		model.addAttribute("getCountBydefaultCheckFalse",mainService.getCountBydefaultCheckFalse());
		return "main/mainAjax";
	}
	
	@RequestMapping("DeleteType")
	@ResponseBody
	public void addVal(@RequestBody String extension) {
		mainService.deleteExtension(extension);
	}

	
	@PostMapping
	public String main(@ModelAttribute("main") MainDomain mainDomain, Model model) {
		model.addAttribute("main",mainDomain);
		return "redirect:/";
	}
	
}