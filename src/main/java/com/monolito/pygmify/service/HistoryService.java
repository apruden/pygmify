package com.monolito.pygmify.service;

import java.util.List;

import com.monolito.pygmify.model.HistoryEntry;

public interface HistoryService {

	void deleteEntry(HistoryEntry entry);
	
	List<HistoryEntry> listEntries();
}
