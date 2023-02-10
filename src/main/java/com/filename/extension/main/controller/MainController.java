package com.filename.extension.main.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
	private static String deleteType; 
	
	@Autowired
	MainService mainService;//Service Bean
	
	@GetMapping
	public String mainPage(Model model) {
		List<MainDomain> defaultExtension = mainService.getExtensionByDefaultCheck(true); //고정확장자
		model.addAttribute("getExtensionByDefaultCheck", defaultExtension);
		return "index";
	}
	
	@RequestMapping("DefaultVal")
	@ResponseBody // HTTP(Hyper Text Transfer Protocol)통신을 이용해 비동기 통신을 할때에 body공간에 데이터를 담는다.
	public void DefaultVal(@RequestBody Map<String, Object> allData) {
		String defaultVal = (String)allData.get("defaultVal"); //고정 확장자의 값
		boolean check = (boolean)allData.get("checked"); //체크박스 체크 유무
		mainService.updateCheckBoxByextension(check, defaultVal);
	}
	
	@GetMapping("addType")
	public String ajax(Model model, HttpServletRequest request) {
		String extension = request.getParameter("type"); //입력값 추가 확장자
		int count = mainService.getCountBydefaultCheckFalse();//커스텀확장자 개수
		boolean DupCheck = mainService.extensionDuplicatedCheck(extension);//동일 확장자 체크
		boolean StringCheck = Pattern.matches("^[a-zA-Z]{3,20}$", extension); //문자열 유효성검사
		boolean ready = extension == "" ? false : true; 
		
		if(!DupCheck && ready && count < 200 && StringCheck) {
			mainService.addExtension(extension);
			model.addAttribute("success","'"+extension+"'가 등록 되었습니다.");
		}
		
		count = mainService.getCountBydefaultCheckFalse();
		
		if(!StringCheck && ready) model.addAttribute("stringErr", "3~20 자의 영문 대 소문자를 사용하세요.");
		else if(count >= 200) model.addAttribute("addErr", "더이상 추가할 수 없습니다.");
		else if(DupCheck) model.addAttribute("duplicateErr", "해당 확장자는 이미 등록된 확장자 입니다.");
		
		model.addAttribute("deleteType",deleteType); //삭제된 데이터
		deleteType = ""; 
		
		List<MainDomain> addExtension = mainService.getExtensionByDefaultCheck(false); //커스텀확장자
		model.addAttribute("getExtensionByDefaultCheck", addExtension);
		model.addAttribute("getCountBydefaultCheckFalse", count);
		
		return "main/mainAjax";
	}
	
	@RequestMapping("DeleteType")
	@ResponseBody
	public void addVal(@RequestBody String extension,Model model) {
		mainService.deleteExtension(extension); //해당 커스텀 확장자 삭제
		deleteType = "'"+extension+"'가 삭제되었습니다.";
	}

	
	@PostMapping
	public String main(MainDomain mainDomain, Model model) {
		mainService.deleteAllCustomExtension();
		return "redirect:/";
	}
	
}