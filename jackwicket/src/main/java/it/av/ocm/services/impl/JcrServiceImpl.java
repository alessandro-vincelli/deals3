package it.av.ocm.services.impl;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.BasicNode;
import it.av.ocm.services.JcrService;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.ocm.query.Filter;
import org.apache.jackrabbit.ocm.query.Query;
import org.apache.jackrabbit.ocm.query.QueryManager;
import org.apache.jackrabbit.ocm.version.Version;
import org.apache.jackrabbit.ocm.version.VersionIterator;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.extensions.jcr.jackrabbit.ocm.JcrMappingTemplateGeneric;

public class JcrServiceImpl<T extends BasicNode> implements JcrService<T>{

    @SpringBean
    private JcrMappingTemplateGeneric<T> jcrMappingtemplate;
    /**
     * Base Path, doesn't contains final slash 
     */
    private String basePath;

    public T update(T object) throws JackWicketException {
        if(object == null || object.getPath() == null){
            throw new JackWicketException("Object or/and object's path is null");
        }
        jcrMappingtemplate.checkout(object.getPath());
        jcrMappingtemplate.update(object);
        jcrMappingtemplate.save();
        jcrMappingtemplate.checkin(object.getPath());
        //verify if necessary the save
        jcrMappingtemplate.save();
        return jcrMappingtemplate.getObject(object.getPath());
    }
    
    public T insert(T object) throws JackWicketException {  
        if(!(StringUtils.startsWith(object.getPath(), basePath))){
            object.setPath(basePath + object.getPath());    
        }
        jcrMappingtemplate.insert(object);
        jcrMappingtemplate.save();
        jcrMappingtemplate.checkout(object.getPath());
        jcrMappingtemplate.checkin(object.getPath());
        //verify if necessary the save
        jcrMappingtemplate.save();
        return jcrMappingtemplate.getObject(object.getPath());
    }
    
    public List<T> getAll() throws JackWicketException{
        QueryManager queryManager = jcrMappingtemplate.createQueryManager();
        Filter filter = queryManager.createFilter(getPersistentClass());
        // scope ends with double slash // to search in all sub nodes and fields 
        filter.setScope(basePath + "//");
        Query query = queryManager.createQuery(filter);
        return new ArrayList<T>(jcrMappingtemplate.getObjects(query));
    }

    
    public List<T> find(String pattern) throws JackWicketException {
        QueryManager queryManager = this.jcrMappingtemplate.createQueryManager();
        Filter filter = queryManager.createFilter(getPersistentClass());
        // scope ends with double slash // to search in all sub nodes and fields 
        filter.setScope(basePath + "//");
        filter.addContains(".", pattern);
        Query query = queryManager.createQuery(filter);
        return new ArrayList<T>(jcrMappingtemplate.getObjects(query));
    }

    public void remove(T object) throws JackWicketException {
        jcrMappingtemplate.remove(object.getPath());
        jcrMappingtemplate.save();
    }

    public List<T> getAllRevisions(String path) throws JackWicketException {
        ArrayList<T> revisions = new ArrayList<T>();
        if (StringUtils.isNotEmpty(path)) {
            VersionIterator versionIterator = jcrMappingtemplate.getAllRevisions(path);
            while (versionIterator.hasNext()) {
                Version version = (Version) versionIterator.next();
                if (version.getName().equals("jcr:rootVersion")) {
                    continue;
                }
                T versionObject = jcrMappingtemplate.getObjectByVersion(path, version.getName());
                versionObject.setVersion(version.getName());
                revisions.add(versionObject);
            }
        }
        //Sort to have latest release in first position
        Collections.reverse(revisions);
        return revisions;
    }

    public T getByPath(String path) throws JackWicketException {
        T object = jcrMappingtemplate.getObject(path);
        object.setVersion(jcrMappingtemplate.getBaseVersion(path));
        return object;
    }
    
    @Override
    public T getByUuid(String uuid) throws JackWicketException {
        Node node = jcrMappingtemplate.getNodeByUUID(uuid);
        try {
            return jcrMappingtemplate.getObject(node.getPath());
        } catch (RepositoryException e) {
           throw new JackWicketException(e);
        }
    }

    
    public void setJcrMappingtemplate(JcrMappingTemplateGeneric<T> jcrMappingtemplate) {
        this.jcrMappingtemplate = jcrMappingtemplate;
    }
    
    public final JcrMappingTemplateGeneric<T> getJcrMappingtemplate() {
        return jcrMappingtemplate;
    }


    public String getBasePath() {
        if(basePath == null){
            return "";
        }
        return basePath;
    }

    public void setBasePath(String basePath) {
        if(StringUtils.isBlank(basePath)){
            this.basePath = "";
        }
        else{
            this.basePath = StringUtils.trimToEmpty(basePath);
            if ((StringUtils.endsWith(this.basePath, "/"))){
                this.basePath = StringUtils.removeEnd(this.basePath, "/");
            }
        }
    }


    protected Class<T> getPersistentClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
