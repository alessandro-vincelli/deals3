package org.deals.framework.search;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.deals.framework.util.DateUtils;
import org.deals.framework.util.SafeUtil;
import org.deals.lucene.highlight.Highlighter;
import org.deals.lucene.highlight.QueryScorer;


public class Searcher {
	
	
	public static final String TAG_COMUNICATI_STAMPA = "Comunicati stampa";
	

	// ricerca semplice searchCriteria che va su SEARCH_CAN
	// ESCLUDERE I COMUNICATI STAMPA....
	public Vector<SearchResult> simpleSearch(String searchCriteria) throws Exception {
		if (SafeUtil.isNullOrEmpty(searchCriteria)) searchCriteria = "a";
		String crit = "-" + WebPageIndexer.CATEGORIES + ": " + TAG_COMUNICATI_STAMPA + "\n";
		crit += "+" + WebPageIndexer.SEARCH_CAN + ": " + searchCriteria + "\n";
		return searchHighlight(searchCriteria);		
	}
		
	// ricerca avanzata, il searchCriteria va su SEARCH_CAN ma poi tra i risultati filtra 
	// solo quelli della sezione richiesta, se è stata richiesta
	// ESCLUDERE I COMUNICATI STAMPA....
	public Vector<SearchResult> advancedSearch(String section, String searchCriteria, String tag) throws Exception {
		String crit = "";
		if (!SafeUtil.isNullOrEmpty(section)) crit += "+" + WebPageIndexer.SECTIONS + ": " + section + "\n";
		if (!SafeUtil.isNullOrEmpty(searchCriteria)) crit += "+" + WebPageIndexer.SEARCH_CAN + ": " + searchCriteria + "\n";
		if (!SafeUtil.isNullOrEmpty(tag)) crit += "+" + WebPageIndexer.TAGS + ": " + tag + "\n";
		crit += "-" + WebPageIndexer.CATEGORIES + ": " + TAG_COMUNICATI_STAMPA + "\n";
		return searchHighlight(crit);
	}	

	// SOLO COMUNICATI STAMPA
	public Vector<SearchResult> comunicatiSearch(Integer mese, Integer anno, String societa, String servizio, String searchCriteria, String tag) throws Exception {
		String crit = "";		
		crit += "+" + WebPageIndexer.CATEGORIES + ": " + TAG_COMUNICATI_STAMPA + "\n";
		if (!SafeUtil.isNullOrEmpty(societa)) crit += "+" + WebPageIndexer.CATEGORIES + ": " + societa;
		if (!SafeUtil.isNullOrEmpty(servizio)) crit += "+" + WebPageIndexer.CATEGORIES + ": " + servizio;
		if (!SafeUtil.isNullOrEmpty(searchCriteria)) crit += "+" + WebPageIndexer.SEARCH_CAN + ": " + searchCriteria;
		if (!SafeUtil.isNullOrEmpty(tag)) crit += "+" + WebPageIndexer.TAGS + ": " + tag + "\n";
		
		// il filtro sul periodo me lo devo fare a mano, per ora....
		Vector<SearchResult> sr = searchHighlight(crit); 
		Vector<SearchResult> realResult = new Vector<SearchResult>();
		for (Iterator iter = sr.iterator(); iter.hasNext();) {
			SearchResult element = (SearchResult) iter.next();
			String date = element.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.customDatePattern);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			int month = cal.get(Calendar.MONTH)+1;
			int year = cal.get(Calendar.YEAR);
			if (mese!=null) if (month!=mese) continue;
			if (anno!=null) if (year!=anno) continue;
			realResult.add(element);
		}
		return realResult;
	}
	
	// ricerca nei comunicati.... searchCriteria che va su SEARCH_CAN
	//							  periodo che filtra sulla published [se è specificato un periodo]
	//							  società  [se specificato un valore va tra le categorie della scheda alla ricerca della società richiesta]
	// 							  servizio [se specificato un valore va tra le categorie della scheda alla ricerca della società richiesta]
	// ....................................................................................................................................
	
	private Vector<SearchResult> searchHighlight(String searchCriteria) throws Exception {
		IndexSearcher searcher = new IndexSearcher(WebPageIndexer.luceneDir);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser(WebPageIndexer.CONTENT, analyzer);
		Query query = parser.parse(searchCriteria);
		//query = query.rewrite(reader); //required to expand search terms
		Hits hits = searcher.search(query);
		Highlighter highlighter = new Highlighter(new QueryScorer(query));
		
		Vector<SearchResult> searchResult = new Vector<SearchResult>();
		for (int i = 0; i < hits.length(); i++)
		{
			String text = hits.doc(i).get(WebPageIndexer.CONTENT);
			TokenStream tokenStream = analyzer.tokenStream(WebPageIndexer.CONTENT, new StringReader(text));
			// Get 3 best fragments and seperate with a "..."
			String result = highlighter.getBestFragments(tokenStream, text, 3 , "...");			
			
			SearchResult sr = new SearchResult();
			sr.setDate(hits.doc(i).get(WebPageIndexer.PUBLISHED));
			sr.setHref(hits.doc(i).get(WebPageIndexer.URL));
			sr.setResult(result);
			sr.setScore(hits.score(i)*100);			
			sr.setTitle(hits.doc(i).get(WebPageIndexer.CONTENT_TITLE));
			
			sr.setSections(tokenizeSections(hits.doc(i).get(WebPageIndexer.SECTIONS)));
			sr.setCategories(tokenizeCategories(hits.doc(i).get(WebPageIndexer.CATEGORIES)));
			
			searchResult.add(sr);
		}						
		return searchResult;
	}


	private String[] tokenizeSections(String sectionsString) {
		if (SafeUtil.isNullOrEmpty(sectionsString)) return new String[0];
		List<String> secs = Arrays.asList(sectionsString.split(WebPageIndexer.SECTIONS_SEPARATOR));
		for (int i = 0; i < secs.size(); i++) {
			String el = secs.get(i);
			if (SafeUtil.isNullOrEmpty(el)) {
				secs.remove(i);
				i--;
			}
		}
		String[] result = new String[secs.size()];
		return secs.toArray(result);
	}
	
	private String[] tokenizeCategories(String categoriesString) {
		if (SafeUtil.isNullOrEmpty(categoriesString)) return new String[0];
		List<String> cats = Arrays.asList(categoriesString.split(WebPageIndexer.CATEGORIES_SEPARATOR));
		for (int i = 0; i < cats.size(); i++) {
			String el = cats.get(i);
			if (SafeUtil.isNullOrEmpty(el)) {
				cats.remove(i);
				i--;
			}
		}
		String[] result = new String[cats.size()];
		return cats.toArray(result);
	}
	
}
