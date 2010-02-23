package it.av.ocm.services;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.User;
import it.av.ocm.bean.UserProfile;

import java.util.List;

public interface UserService {

    User save(User user) throws JackWicketException;

    List<User> getAll() throws JackWicketException;

    List<User> find(String pattern) throws JackWicketException;

    void remove(User user) throws JackWicketException;

    List<User> getAllRevisions(String path) throws JackWicketException;

    User getByPath(String path) throws JackWicketException;
    
    List<User> getByProfile(UserProfile pattern);
}