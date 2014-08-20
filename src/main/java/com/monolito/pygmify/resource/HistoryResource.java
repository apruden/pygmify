package com.monolito.pygmify.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monolito.pygmify.model.HistoryEntry;
import com.monolito.pygmify.service.HistoryService;

/**
 * 
 * @author alex
 *
 */
@Component
@Path("/history")
public class HistoryResource {

	@Autowired
	HistoryService historyService;
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	@GET
	public List<HistoryEntry> getList(String query) {
		return new ArrayList<>();
	}
	
	/**
	 * 
	 * @param entry
	 */
	@DELETE
	public void deleteEntry(HistoryEntry entry) {
		historyService.deleteEntry(entry);
	}
}
