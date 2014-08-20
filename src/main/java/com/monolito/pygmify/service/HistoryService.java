package com.monolito.pygmify.service;

import java.util.List;

import com.monolito.pygmify.model.HistoryEntry;

/**
 * 
 * @author alex
 *
 */
public interface HistoryService {

	/**
	 * 
	 * @param entry
	 */
	void deleteEntry(HistoryEntry entry);
	
	/**
	 * 
	 * @return
	 */
	List<HistoryEntry> listEntries();
}
