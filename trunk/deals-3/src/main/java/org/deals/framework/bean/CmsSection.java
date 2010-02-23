package org.deals.framework.bean;

// Generated by MyEclipse Persistence Tools

import java.util.Set;

/**
 * CmsSection generated by MyEclipse Persistence Tools
 */
public class CmsSection extends AbstractCmsSection implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CmsSection() {
	}

	/** minimal constructor */
	public CmsSection(Integer cmssId, String cmssName) {
		super(cmssId, cmssName);
	}

	/** full constructor */
	public CmsSection(Integer cmssId, String cmssName, String cmssUri,
			String cmssDescription, String cmssShortdesc, Set AProfileSectionses) {
		super(cmssId, cmssName, cmssUri, cmssDescription, cmssShortdesc,
				AProfileSectionses);
	}

	
	private boolean cmssSelected;


	public boolean isCmssSelected() {
		return cmssSelected;
	}

	public void setCmssSelected(boolean cmssSelected) {
		this.cmssSelected = cmssSelected;
	}
	
}