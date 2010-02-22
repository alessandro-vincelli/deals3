package org.deals.framework.core;

import org.apache.commons.lang.StringUtils;

public enum MimeType {
	BMP("image/bmp", ".bmp","image"),
	GIF("image/gif", ".gif","image"),
	ICO("image/x-icon", ".ico","image"),
	JPG("image/jpg",".jpg","image"),
	JPEG("image/jpeg",".jpeg","image"),
	PNG("image/png",".png","image"),
	PDF("application/pdf",".pdf","document"),
	DOC("application/msword",".doc","document"),
	RTF("application/rtf",".rtf","document"),
	SWF("application/x-shockwave-flash",".swf","document"),
	// aggiungere i tipi di file conosciuti...
	UNKNOWN("",".*","unknown");			
	
	private String mimeType;
	private String extension;
	private String type;
	
	MimeType(String mimeType, String extension, String type) {
		this.mimeType = mimeType;
		this.extension = extension;
		this.type = type;
	}
	
	public String mimeType() {
		return mimeType;
	}
	
	public String extension() {
		return extension;
	}
	
	public String type() {
		return type;
	}
	
	public static MimeType getMimeType(String mimeType) {
		if (mimeType==null) return UNKNOWN;
		if (mimeType.equals("image/bmp")) return BMP;
		if (mimeType.equals("image/gif")) return GIF;
		if (mimeType.equals("image/x-icon")) return ICO;
		if (mimeType.equals("image/jpeg")) return JPEG;
		if (mimeType.equals("image/jpg")) return JPG;		
		if (mimeType.equals("image/png")) return PNG;
		if (mimeType.equals("application/pdf")) return PDF;
		if (mimeType.equals("application/msword")) return DOC;
		if (mimeType.equals("application/rtf")) return RTF;		
		if (mimeType.equals("application/x-shockwave-flash")) return SWF;
		
		// TODO in realtà dovrebbe lanciare eccezione, MimeTypeNotFound o simile...
		return UNKNOWN;
	}
	
	////////////////////////////////////////////////////////////
	
	public boolean isImage() {
		return type().equals("image");
	}

	public boolean isDocument() {
		return type().equals("document");
	}
	
	
	public static MimeType byFileName(String fileName) {
		int index = StringUtils.lastIndexOf(fileName, '.');
		if (index>-1) {
			String ext = fileName.substring(index+1);
			if (ext.toLowerCase().equals("bmp")) return BMP;
			if (ext.toLowerCase().equals("gif")) return GIF;
			if (ext.toLowerCase().equals("ico")) return ICO;
			if (ext.toLowerCase().equals("jpeg")) return JPEG;
			if (ext.toLowerCase().equals("jpg")) return JPG;
			if (ext.toLowerCase().equals("png")) return PNG;
			if (ext.toLowerCase().equals("pdf")) return PDF;
			if (ext.toLowerCase().equals("doc")) return DOC;
			if (ext.toLowerCase().equals("rtf")) return RTF;
			if (ext.toLowerCase().equals("swf")) return SWF;
		}
		return UNKNOWN;
	}
	
}
