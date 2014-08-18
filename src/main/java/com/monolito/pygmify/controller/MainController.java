package com.monolito.pygmify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/get", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getPygmified(String url) {
		return this.scrapperService.getContent(url);
	}

	@RequestMapping(value = "/history")
	public String listEntries(Map<String, Object> viewModel,
			@RequestParam(value = "query", required = false) String query) {
		viewModel.put("entries", historyService.listEntries());

		return "history";
	}
}