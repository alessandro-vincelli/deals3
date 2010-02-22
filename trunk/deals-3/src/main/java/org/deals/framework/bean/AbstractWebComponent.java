package org.deals.framework.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractWebComponent generated by MyEclipse - Hibernate Tools
 */

public abstract class AbstractWebComponent implements PersistentObject, java.io.Serializable {


    // Fields    

     private Integer wcId;
     private ComponentType componentType;
     private String rlTitle;
     private String rlContent;
     private String rlTarget;
     private String rlHref;
     private String wrName;
     private String wrDescription;
     private String wrMimetype;
     private String wrAlt;
     private Date wcInsertDate;
     private Set APageComponentses = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractWebComponent() {
    }

	/** minimal constructor */
    public AbstractWebComponent(ComponentType componentType) {
        this.componentType = componentType;
    }
    
    /** full constructor */
    public AbstractWebComponent(ComponentType componentType, String rlTitle, String rlContent, String rlTarget, String rlHref, String wrName, String wrDescription, String wrMimetype, String wrAlt, Date wcInsertDate, Set APageComponentses) {
        this.componentType = componentType;
        this.rlTitle = rlTitle;
        this.rlContent = rlContent;
        this.rlTarget = rlTarget;
        this.rlHref = rlHref;
        this.wrName = wrName;
        this.wrDescription = wrDescription;
        this.wrMimetype = wrMimetype;
        this.wrAlt = wrAlt;
        this.wcInsertDate = wcInsertDate;
        this.APageComponentses = APageComponentses;
    }

   
    // Property accessors

    public Integer getWcId() {
        return this.wcId;
    }
    
    public void setWcId(Integer wcId) {
        this.wcId = wcId;
    }

    public ComponentType getComponentType() {
        return this.componentType;
    }
    
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getRlTitle() {
        return this.rlTitle;
    }
    
    public void setRlTitle(String rlTitle) {
        this.rlTitle = rlTitle;
    }

    public String getRlContent() {
        return this.rlContent;
    }
    
    public void setRlContent(String rlContent) {
        this.rlContent = rlContent;
    }

    public String getRlTarget() {
        return this.rlTarget;
    }
    
    public void setRlTarget(String rlTarget) {
        this.rlTarget = rlTarget;
    }

    public String getRlHref() {
        return this.rlHref;
    }
    
    public void setRlHref(String rlHref) {
        this.rlHref = rlHref;
    }

    public String getWrName() {
        return this.wrName;
    }
    
    public void setWrName(String wrName) {
        this.wrName = wrName;
    }

    public String getWrDescription() {
        return this.wrDescription;
    }
    
    public void setWrDescription(String wrDescription) {
        this.wrDescription = wrDescription;
    }

    public String getWrMimetype() {
        return this.wrMimetype;
    }
    
    public void setWrMimetype(String wrMimetype) {
        this.wrMimetype = wrMimetype;
    }

    public String getWrAlt() {
        return this.wrAlt;
    }
    
    public void setWrAlt(String wrAlt) {
        this.wrAlt = wrAlt;
    }

    public Set getAPageComponentses() {
        return this.APageComponentses;
    }
    
    public void setAPageComponentses(Set APageComponentses) {
        this.APageComponentses = APageComponentses;
    }

	public Date getWcInsertDate() {
		return wcInsertDate;
	}

	public void setWcInsertDate(Date wcInsertDate) {
		this.wcInsertDate = wcInsertDate;
	}
   
}