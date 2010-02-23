package org.deals.framework.bean;

/**
 * 
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 14, 2008
 */
public abstract class AbstractUsers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596479974890961596L;
	// Fields

	private Integer usId;
	private String usUsername;
	private String usPassword;
	private String usFirstname;
	private String usSurname;
	private String usEmail;

	// Constructors

	/** default constructor */
	public AbstractUsers() {
	}

	/** minimal constructor */
	public AbstractUsers(String usUsername, String usPassword) {
		this.usUsername = usUsername;
		this.usPassword = usPassword;
	}

	/** full constructor */
	public AbstractUsers(String usUsername, String usPassword, String usFirstname, String usSurname, String usEmail) {
		this.usUsername = usUsername;
		this.usPassword = usPassword;
		this.usFirstname = usFirstname;
		this.usSurname = usSurname;
		this.usEmail = usEmail;
	}

	// Property accessors

	public Integer getUsId() {
		return this.usId;
	}

	public void setUsId(Integer usId) {
		this.usId = usId;
	}

	public String getUsUsername() {
		return this.usUsername;
	}

	public void setUsUsername(String usUsername) {
		this.usUsername = usUsername;
	}

	public String getUsPassword() {
		return this.usPassword;
	}

	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	public String getUsFirstname() {
		return this.usFirstname;
	}

	public void setUsFirstname(String usFirstname) {
		this.usFirstname = usFirstname;
	}

	public String getUsSurname() {
		return this.usSurname;
	}

	public void setUsSurname(String usSurname) {
		this.usSurname = usSurname;
	}

	public String getUsEmail() {
		return this.usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

}