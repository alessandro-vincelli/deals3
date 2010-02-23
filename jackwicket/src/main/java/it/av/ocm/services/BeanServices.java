package it.av.ocm.services;

import it.av.ocm.JackWicketException;

import java.util.Collection;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.version.VersionException;

/**
 * Base operations on beans
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 *
 * @param <T>
 */
public interface BeanServices<T> {

    T save(T user) throws RepositoryException;

    Collection<T> getAll();

    Collection<T> find(String pattern);

    void remove(T bean) throws JackWicketException;

    List<T> getAllRevisions(String path) throws JackWicketException;

    T getByPath(String path) throws VersionException;

}