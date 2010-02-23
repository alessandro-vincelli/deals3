package it.av.ocm.services;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.BasicNode;

import java.util.List;

public interface JcrService<T extends BasicNode> {

    T update(T item) throws JackWicketException;
    
    T insert(T item) throws JackWicketException;

    List<T> getAll() throws JackWicketException;

    List<T> find(String pattern) throws JackWicketException;

    void remove(T item) throws JackWicketException;

    List<T> getAllRevisions(String path) throws JackWicketException;

    T getByPath(String path) throws JackWicketException;
    
    T getByUuid(String uuid) throws JackWicketException;

}
