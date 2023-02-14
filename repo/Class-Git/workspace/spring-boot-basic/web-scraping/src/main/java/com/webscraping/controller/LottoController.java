package com.webscraping.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = { "/lotto" })
public class LottoController {
	
	private HashMap<String, Object> parseWinningNumbers(Document doc) {
		
		HashMap<String, Object> parsingResult = new HashMap<>();

		// Element option = doc.selectFirst("#dwrNoList option:selected");
		String sRound = doc.selectFirst(".win_result h4 strong").text();
		int round = Integer.parseInt(sRound.substring(0, sRound.length() - 1));
		parsingResult.put("round", round);
		
		ArrayList<Integer> numbers = new ArrayList<>();
		Elements spans = doc.select(".num.win p span");
		for (Element span : spans) {
			numbers.add(Integer.parseInt(span.text()));
		}
		
		Element span = doc.select(".num.bonus p span").first();
		numbers.add(Integer.parseInt(span.text()));
		
		parsingResult.put("numbers", numbers);
		
		return parsingResult;
	}
	
	@GetMapping(path = { "/winning-numbers-by-round" })
	public String showWinningNumbersForm(Model model) {
		
		try {
			Document doc = 
					Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=byWin").get();
			HashMap<String, Object> parsedData = parseWinningNumbers(doc);
			model.addAttribute("parsedData", parsedData);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "lotto/winning-numbers-by-round";
	}
	
	@PostMapping(path = { "/winning-numbers-by-round" })
	@ResponseBody
	public HashMap<String, Object> searchWinningNumbersByRound(
			@RequestParam(defaultValue = "0") int round, Model model) {
	
		HashMap<String, Object> responseData;
		try {
			Document doc = 
					Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=byWin")
						 .data("drwNo", String.valueOf(round))
						 .post();
			responseData = parseWinningNumbers(doc);
			responseData.put("result", "success");
		} catch (Exception ex) {
			responseData = new HashMap<>();
			responseData.put("result", "fail");
		}
		
		return responseData;
	}

}




