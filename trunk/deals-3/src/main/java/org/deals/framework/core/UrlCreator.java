package org.deals.framework.core;

import org.apache.commons.lang.StringUtils;
import org.deals.framework.bean.PageType;

public class UrlCreator {
	private static final String CONTEXT_PATH_SITE = Config.getProperty("Config.CONTEXT_PATH_SITE");	
	private static final String BASE_FOLDER_SITE = Config.getProperty("Config.BASE_FOLDER_SITE");
	private static final String BASE_REPO_BINARY_NODE = Config.getProperty("Config.BASE_REPO_BINARY_NODE");
	

	private static final String IMAGE_FOLDER = Config.getProperty("Config.IMAGE_FOLDER");
	private static final String PDF_FOLDER = Config.getProperty("Config.PDF_FOLDER");
	private static final String DOC_FOLDER = Config.getProperty("Config.DOC_FOLDER");
	
	private static final String IMAGE_TEMP_FOLDER = Config.getProperty("Config.IMAGE_TEMP_FOLDER");
	private static final String PDF_TEMP_FOLDER = Config.getProperty("Config.PDF_TEMP_FOLDER");
	private static final String DOC_TEMP_FOLDER = Config.getProperty("Config.DOC_TEMP_FOLDER");	
	
	


	
	// TODO urgente refactoring per ottenere da risorse esterne i 
	// vari path necessari alla costruzione delle URL
	public static String createUrl(int pageId, PageType pageType) {
		return StringUtils.trim(CONTEXT_PATH_SITE + "/" + pageType.getPtName() + "/" + pageId + ".html");
	}
	
	
	public static String createTempFilePathOnDisk(String fileName, MimeType mimeType) {
		String url = ""; //$NON-NLS-1$
		if (mimeType.isImage()) {			
		  url = BASE_FOLDER_SITE + IMAGE_TEMP_FOLDER + "/" + fileName;
		}
		if (mimeType.equals(MimeType.PDF)) {
			url = BASE_FOLDER_SITE + PDF_TEMP_FOLDER + "/" + fileName;
		}

		if (mimeType.equals(MimeType.DOC)||mimeType.equals(MimeType.RTF)) {
			url = BASE_FOLDER_SITE + DOC_TEMP_FOLDER + "/" + fileName;
		}
  	    //  TODO ... qui vanno aggiunti gli if per gli altri tipi di file che si vogliono gestire...
		//		     swf
		//           ...	
		
		// TODO così FA SCHIFO, USARE IL POLIFORFISMO CHE CHIAMA LE CORRETTE CREATETEMP
		// NEL PUNTO IN CUI VIENE CHIAMATA SI DEMANDA LA SCELTA AL TIPO DI FILE CHE SI 
		// VUOLE TRASFERIRE... PENSARCI UN PO'
		
		return url;
	}
	

	public static String createFilePathOnDisk(int resourceId, String fileName, MimeType mimeType) {
		// applica metodo di renaming univoco del fileName
		String uniqueFileName = createUniqueFileName(resourceId, fileName);
		String url = ""; //$NON-NLS-1$
		if (mimeType.isImage()) {			
		  url = BASE_FOLDER_SITE + IMAGE_FOLDER + "/" + uniqueFileName;
		}
		
		if (mimeType.equals(MimeType.PDF)) {
			url = BASE_FOLDER_SITE + PDF_FOLDER + "/" + uniqueFileName;
		}

		if (mimeType.equals(MimeType.DOC)||mimeType.equals(MimeType.RTF)) {
			url = BASE_FOLDER_SITE + DOC_FOLDER + "/" + uniqueFileName;
		}
		
		//  TODO ... qui vanno aggiunti gli if per gli altri tipi di file che si vogliono gestire...
		//		     swf
		//           ...		

		// TODO così FA SCHIFO, USARE IL POLIFORFISMO CHE CHIAMA LE CORRETTE CREATETEMP
		// NEL PUNTO IN CUI VIENE CHIAMATA SI DEMANDA LA SCELTA AL TIPO DI FILE CHE SI 
		// VUOLE TRASFERIRE... PENSARCI UN PO'
		
		return url;
	}
	

	public static String createTempFilePathOnSite(String fileName, MimeType mimeType) {
		String url = ""; //$NON-NLS-1$
		if (mimeType.isImage()) {			
		  url = CONTEXT_PATH_SITE + IMAGE_TEMP_FOLDER + "/" + fileName;
		}
		if (mimeType.equals(MimeType.PDF)) {
			url = CONTEXT_PATH_SITE + PDF_FOLDER + "/" + fileName;
		}

		if (mimeType.equals(MimeType.DOC)||mimeType.equals(MimeType.RTF)) {
			url = CONTEXT_PATH_SITE + DOC_FOLDER + "/" + fileName;
		}
  	    //  TODO ... qui vanno aggiunti gli if per gli altri tipi di file che si vogliono gestire...
		//		     swf
		//           ...		

		// TODO così FA SCHIFO, USARE IL POLIFORFISMO CHE CHIAMA LE CORRETTE CREATETEMP
		// NEL PUNTO IN CUI VIENE CHIAMATA SI DEMANDA LA SCELTA AL TIPO DI FILE CHE SI 
		// VUOLE TRASFERIRE... PENSARCI UN PO'
		
		return url;
	}
	
	@Deprecated
	public static String createFilePathOnSite(int resourceId, String fileName, MimeType mimeType) {
		// applica metodo di renaming univoco del fileName
		String uniqueFileName = createUniqueFileName(resourceId, fileName);
		String url = ""; //$NON-NLS-1$
		if (mimeType.isImage()) {			
		  url = CONTEXT_PATH_SITE + IMAGE_FOLDER + "/" + uniqueFileName;
		}
		if (mimeType.equals(MimeType.PDF)) {
			url = CONTEXT_PATH_SITE + PDF_FOLDER + "/" + uniqueFileName;
		}

		if (mimeType.equals(MimeType.DOC)||mimeType.equals(MimeType.RTF)) {
			url = CONTEXT_PATH_SITE + DOC_FOLDER + "/" + uniqueFileName;
		}
		
  	    //  TODO ... qui vanno aggiunti gli if per gli altri tipi di file che si vogliono gestire...
		//		     swf
		//           ...		

		// TODO così FA SCHIFO, USARE IL POLIFORFISMO CHE CHIAMA LE CORRETTE CREATETEMP
		// NEL PUNTO IN CUI VIENE CHIAMATA SI DEMANDA LA SCELTA AL TIPO DI FILE CHE SI 
		// VUOLE TRASFERIRE... PENSARCI UN PO'
		
		return url;
	}
	
	public static String createFilePathOnRepo(int resourceId, String fileName, MimeType mimeType) {
		// applica metodo di renaming univoco del fileName
		String uniqueFileName = createUniqueFileName(resourceId, fileName);
		String url = ""; //$NON-NLS-1$
		if (mimeType.isImage()) {			
		  url = CONTEXT_PATH_SITE  + BASE_REPO_BINARY_NODE + "/" + uniqueFileName;
		}
		if (mimeType.equals(MimeType.PDF)) {
			url = CONTEXT_PATH_SITE + BASE_REPO_BINARY_NODE + "/" + uniqueFileName;
		}

		if (mimeType.equals(MimeType.DOC)||mimeType.equals(MimeType.RTF)) {
			url = CONTEXT_PATH_SITE + BASE_REPO_BINARY_NODE + "/" + uniqueFileName;
		}
		
  	    //  TODO ... qui vanno aggiunti gli if per gli altri tipi di file che si vogliono gestire...
		//		     swf
		//           ...		

		// TODO così FA SCHIFO, USARE IL POLIFORFISMO CHE CHIAMA LE CORRETTE CREATETEMP
		// NEL PUNTO IN CUI VIENE CHIAMATA SI DEMANDA LA SCELTA AL TIPO DI FILE CHE SI 
		// VUOLE TRASFERIRE... PENSARCI UN PO'
		
		return url;
	}


	
	
	
	/**
	 * In questo punto si decide il metodo di renaming del file name
	 * per ottenere un nome file univoco
	 */
	public static String createUniqueFileName(int resourceId, String fileName) {
		int FILENAME_ID_PAD = 8;
		return StringUtils.leftPad(resourceId+"", FILENAME_ID_PAD,'0') + "_" + fileName; //$NON-NLS-1$ //$NON-NLS-2$
	}



	
}
