package org.deals.framework.bean;

import java.util.HashSet;
import java.util.Set;


/**
 * AbstractTemplate generated by MyEclipse - Hibernate Tools
 */

public abstract class AbstractTemplate implements PersistentObject, java.io.Serializable {


    // Fields    

     private Integer teId;
     private String teName;
     private Set webPages = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractTemplate() {
    }

	/** minimal constructor */
    public AbstractTemplate(String teName) {
        this.teName = teName;
    }
    
    /** full constructor */
    public AbstractTemplate(String teName, Set webPages) {
        this.teName = teName;
        this.webPages = webPages;
    }

   
    // Property accessors

    public Integer getTeId() {
        return this.teId;
    }
    
    public void setTeId(Integer teId) {
        this.teId = teId;
    }

    public String getTeName() {
        return this.teName;
    }
    
    public void setTeName(String teName) {
        this.teName = teName;
    }

    public Set getWebPages() {
        return this.webPages;
    }
    
    public void setWebPages(Set webPages) {
        this.webPages = webPages;
    }
   








}