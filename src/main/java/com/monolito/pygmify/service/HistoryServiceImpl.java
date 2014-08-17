package com.monolito.pygmify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monolito.pygmify.model.HistoryEntry;

/**
 * 
 * @author alex
 * 
 */
@Service
public class HistoryServiceImpl implements HistoryService {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void deleteEntry(HistoryEntry entry) {
	}

	@Override
	public List<HistoryEntry> listEntries() {
		CriteriaQuery<HistoryEntry> c = em.getCriteriaBuilder().createQuery(
				HistoryEntry.class);
		c.from(HistoryEntry.class);

		return em.createQuery(c).getResultList();
	}
}