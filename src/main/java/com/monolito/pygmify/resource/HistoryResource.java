package com.monolito.pygmify.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monolito.pygmify.model.HistoryEntry;
import com.monolito.pygmify.service.HistoryService;

@Component
@Path("/history")
public class HistoryResource {

	@Autowired
	HistoryService historyService;
	
	@GET
	public List<HistoryEntry> getList(String query) {
		return null;
	}
	
	@DELETE
	public void deleteEntry(HistoryEntry entry) {
		historyService.deleteEntry(entry);
	}
}
