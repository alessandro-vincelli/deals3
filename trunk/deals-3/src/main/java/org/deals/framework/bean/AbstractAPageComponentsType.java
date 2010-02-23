package org.deals.framework.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * APageComponentsType generated by hbm2java
 */

public abstract class AbstractAPageComponentsType implements PersistentObject, java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8731134735771225667L;

	private Integer apctId;

	private String apctName;

	private String apctDescri;

	private Set<APageComponents> APageComponentses = new HashSet<APageComponents>(0);

	// Constructors

	/** default constructor */
	public AbstractAPageComponentsType() {
	}

	/** minimal constructor */
	public AbstractAPageComponentsType(Integer apctId, String apctName) {
		this.apctId = apctId;
		this.apctName = apctName;
	}

	/** full constructor */
	public AbstractAPageComponentsType(Integer apctId, String apctName, String apctDescri, Set<APageComponents> APageComponentses) {
		this.apctId = apctId;
		this.apctName = apctName;
		this.apctDescri = apctDescri;
		this.APageComponentses = APageComponentses;
	}

	// Property accessors
	public Integer getApctId() {
		return this.apctId;
	}

	public void setApctId(Integer apctId) {
		this.apctId = apctId;
	}

	public String getApctName() {
		return this.apctName;
	}

	public void setApctName(String apctName) {
		this.apctName = apctName;
	}

	public String getApctDescri() {
		return this.apctDescri;
	}

	public void setApctDescri(String apctDescri) {
		this.apctDescri = apctDescri;
	}

	public Set<APageComponents> getAPageComponentses() {
		return this.APageComponentses;
	}

	public void setAPageComponentses(Set<APageComponents> APageComponentses) {
		this.APageComponentses = APageComponentses;
	}

}