package com.monolito.pygmify.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monolito.pygmify.service.HistoryService;
import com.monolito.pygmify.service.ScrapperService;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 
 * @author alex
 *
 */
@Controller
public class MainController {

	static Logger log = Logger.getLogger(MainController.class.getName());

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ScrapperService scrapperService;

	/**
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/get", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getPygmified(String url) {
		return this.scrapperService.getContent(url);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("/getasync")
	public @ResponseBody
	Callable<String> getPygmifiedAsync(final String url) {
		log.debug("init callable");

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				log.debug("called callable");
				Future<String> f = scrapperService.getContentAsync(url);
				String res = f.get();

				return res;
			}
		};

		return callable;
		// return new WebAsyncTask<String>(30000, callable);
	}

	/**
	 * 
	 * @param viewModel
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/history")
	public String listEntries(Map<String, Object> viewModel,
			@RequestParam(value = "query", required = false) String query) {
		viewModel.put("entries", historyService.listEntries());

		return "history";
	}
}