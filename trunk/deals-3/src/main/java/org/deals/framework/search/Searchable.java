package org.deals.framework.search;

import java.util.Map;
import java.util.Vector;

public interface Searchable {

	/**
	 * Metodo che effettua la ricerca nel componente Searchable
	 * @param visited map degli elementi Searchable visitati  
	 * @param results vettore dei SearchResult ottenuti nella ricerca
	 */
	public void search(Map<Searchable, Object> visited, Vector<SearchResult> results);
	
}
