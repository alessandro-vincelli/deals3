package org.deals.framework.search;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.deals.framework.bean.Item;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.Config;
import org.deals.framework.core.UrlCreator;
import org.deals.framework.dao.ItemDAO;
import org.deals.framework.util.DateUtils;
import org.deals.framework.util.SafeUtil;
import org.deals.framework.util.TextUtils;

public class WebPageIndexer {
	
	
	public final static String CONTENT_TITLE = "contentTitle"; 
	public final static String HEADER = "header"; 
	public final static String FOOTER = "footer"; 
	public final static String CONTENT = "content"; 
/////////////////////////////////////////////////////////////////////////////////////////////////	
	public final static String URL = "url"; 
/////////////////////////////////////////////////////////////////////////////////////////////////	
	public final static String PUBLISHED = "published"; 
/////////////////////////////////////////////////////////////////////////////////////////////////	
	public final static String SECTIONS = "sections"; 
	public final static String CATEGORIES = "categories";
/////////////////////////////////////////////////////////////////////////////////////////////////	
	public final static String TAGS = "tags";
	
	
	public final static String SEARCH_CAN = "search_can"; 
	
	
	public final static String SECTIONS_SEPARATOR = "##";
	public final static String CATEGORIES_SEPARATOR = "##";
	
	public final static String SECTION = "section"; 
	public final static String COMUNICATO = "comunicato";
	public final static String PAGESTATE = "pageState"; 
	public final static String AUTHOR = "author"; 
	public final static String TITLE = "contentTitle";
	//private WebPageDAO dao; 
	private ItemDAO itemDao; 
	
	
	public static String luceneDir = Config.getProperty("Config.LUCENE_DIR");	
	
	public void indicizeWebPages() throws Exception {
		
		// deve essere getAllItems, ....
		List<WebPage> pages =  itemDao.findAll();
		initializeIndex();
		for (Iterator<WebPage> iter = pages.iterator(); iter.hasNext();) {
			Item page = (Item)iter.next();
			indexWebPage(page);
		}
	}

	private void initializeIndex() throws IOException {
		Analyzer analyzer = new StandardAnalyzer();		
		IndexWriter writer = new IndexWriter(luceneDir, analyzer, true);
		writer.close();
	}
	
	
	private void indexWebPage(Item page) throws Exception {
		Document document = createDocument(page);		
		indexDocument(document);		
	}
	
	
	private Document createDocument(Item page) {						
		Document doc = new Document();

		if (page.getWpContentTitle() != null) 
			doc.add(new Field(CONTENT_TITLE, TextUtils.stripHTMLTags(page.getWpContentTitle()), Store.YES, Index.TOKENIZED));
		if (page.getWpHeader() != null) 
			doc.add(new Field(HEADER, TextUtils.stripHTMLTags(page.getWpHeader()), Store.YES, Index.TOKENIZED));
		if (page.getWpFooter() != null) 
			doc.add(new Field(FOOTER, TextUtils.stripHTMLTags(page.getWpFooter()), Store.YES, Index.TOKENIZED));
		if (page.getWpContent() != null) 
			doc.add(new Field(CONTENT, TextUtils.stripHTMLTags(page.getWpContent()), Store.YES, Index.TOKENIZED));
		
		doc.add(new Field(URL, UrlCreator.createUrl(page.getWpId(), page.getPageType()),Store.YES, Index.NO));
		
		if (page.getWpPublished() != null) 
			doc.add(new Field(PUBLISHED, DateUtils.toDateStringCustom(page.getWpPublished()), Store.YES, Index.NO));
		
		doc.add(new Field(SEARCH_CAN, TextUtils.stripHTMLTags(TextUtils.stripHTMLTags(SafeUtil.safeToString(page.getWpContentTitle())) + " \n " + 
				TextUtils.stripHTMLTags(SafeUtil.safeToString(page.getWpHeader())) + " \n " +
						TextUtils.stripHTMLTags(SafeUtil.safeToString(page.getWpContent())) + " \n " + 
								TextUtils.stripHTMLTags(SafeUtil.safeToString(page.getWpFooter()))), Store.YES, Index.TOKENIZED));
		
		
		
		// costruzione del tag delle sezioni della scheda
		StringBuffer sectionsField = new StringBuffer();
		List<WebPage> sections = page.getSections();
		for (Iterator iter = sections.iterator(); iter.hasNext();) {
			WebPage element = (WebPage) iter.next();
			sectionsField.append(element.getWpName());			
			sectionsField.append(SECTIONS_SEPARATOR);
		}
		doc.add(new Field(SECTIONS, sectionsField.toString(), Store.YES, Index.TOKENIZED));
		
		
		// costruzione del tag delle categorie della scheda		
		StringBuffer categoriesField = new StringBuffer();
		List<WebPage> categories = page.getCategories();
		for (Iterator iter = categories.iterator(); iter.hasNext();) {
			WebPage element = (WebPage) iter.next();
			categoriesField.append(element.getWpName());			
			categoriesField.append(CATEGORIES_SEPARATOR);
		}
		doc.add(new Field(CATEGORIES, categoriesField.toString(), Store.YES, Index.TOKENIZED));
		
		
		// aggiungere anche il campo dei tag, se e' diverso da blank o da null
		if (page.getWpTags()!=null && SafeUtil.isNullOrEmpty(page.getWpTags())) {
			doc.add(new Field(TAGS, page.getWpTags(), Store.YES, Index.TOKENIZED));
		}
		
		return doc;
	}
	
	
	private void indexDocument(Document document) throws Exception {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriter writer = new IndexWriter(luceneDir, analyzer, false);
		writer.addDocument(document);
		writer.optimize();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		WebPageIndexer wpi = new WebPageIndexer();
		wpi.indicizeWebPages();
		
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	

	
}
