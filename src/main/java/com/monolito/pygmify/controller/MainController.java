package com.monolito.pygmify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monolito.pygmify.service.HistoryService;
import com.monolito.pygmify.service.ScrapperService;

import java.util.Map;

@Controller
public class MainController {

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ScrapperService scrapperService;
	
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE, value = "/get")
	@ResponseBody
	public String getPygmified(String url) {
		return this.scrapperService.getContent(url);
	}

	@RequestMapping(value="/history")
	public String listEntries(Map<String, Object> viewModel) {
		viewModel.put("entries", historyService.listEntries());

		return "history";
	}
}
