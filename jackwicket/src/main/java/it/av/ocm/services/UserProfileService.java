package it.av.ocm.services;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.UserProfile;

import java.util.Collection;
import java.util.List;

public interface UserProfileService {

    UserProfile save(UserProfile profile) throws JackWicketException;

    Collection<UserProfile> getAll() throws JackWicketException;

    Collection<UserProfile> find(String pattern) throws JackWicketException;

    void remove(UserProfile user) throws JackWicketException;

    List<UserProfile> getAllRevisions(String path) throws JackWicketException;

    UserProfile getByPath(String path) throws JackWicketException;
}