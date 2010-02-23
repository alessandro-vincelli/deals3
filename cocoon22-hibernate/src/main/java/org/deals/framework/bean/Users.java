package org.deals.framework.bean;

/**
 * 
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 14, 2008
 */
public class Users extends AbstractUsers implements java.io.Serializable {

	private static final long serialVersionUID = -2956165511534938028L;

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String usUsername, String usPassword) {
		super(usUsername, usPassword);
	}

	/** full constructor */
	public Users(String usUsername, String usPassword, String usFirstname, String usSurname, String usEmail) {
		super(usUsername, usPassword, usFirstname, usSurname, usEmail);
	}

	private int rowId;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

}
