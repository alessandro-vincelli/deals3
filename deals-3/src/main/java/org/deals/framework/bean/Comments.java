package org.deals.framework.bean;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.deals.framework.exception.SiteException;

@XmlRootElement
public class Comments extends AbstractComments implements java.io.Serializable {

	
	// Used on the  CFORM Repeater Binding
	private int rowId;

   	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}	
	
	public Comments() {
	}

	public Comments(WebPage webPage, String commentAuthor, String commentAuthorEmail, int commentApproved) {
		super(webPage, commentAuthor, commentAuthorEmail, commentApproved);
	}

	public Comments(WebPage webPage, String commentAuthor, String commentAuthorEmail, String commentAuthorUrl, String commentAuthorIp, Date commentDate, Date commentDateGmt,
			String commentContent, int commentApproved, String commentAgent, String commentType, Integer usId) {
		super(webPage, commentAuthor, commentAuthorEmail, commentAuthorUrl, commentAuthorIp, commentDate, commentDateGmt, commentContent, commentApproved, commentAgent,
				commentType, usId);
	}

	/**
	 * Return the XML version of the comment
	 * 
	 * @return XML representation of the Comments beans
	 */
	public String toXML(String rootElement) throws SiteException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Comments.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			// prevents the <xml ...> declaration at the begging of the fragment
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(this, bos);
		} catch (JAXBException e) {
			throw new SiteException("Error during the XML serialization of a comment", e);
		}
		return bos.toString();
	}
}
