package it.av.ocm.services;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.Comments;

import java.util.Collection;
import java.util.List;

public interface CommentsServices {

    Comments save(Comments comment) throws JackWicketException;

    Collection<Comments> getAll() throws JackWicketException;

    Collection<Comments> find(String pattern) throws JackWicketException;

    void remove(Comments comment) throws JackWicketException;

    List<Comments> getAllRevisions(String path) throws JackWicketException;

    Comments getByPath(String path) throws JackWicketException;
}