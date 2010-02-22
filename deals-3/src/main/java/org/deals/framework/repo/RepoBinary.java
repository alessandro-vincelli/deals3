package org.deals.framework.repo;


import java.io.InputStream;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import org.deals.framework.core.Config;
import org.springmodules.jcr.support.JcrDaoSupport;

public class RepoBinary extends JcrDaoSupport {

	Session session;
	private static final String BASE_REPO_BINARY_NODE = Config.getProperty("Config.BASE_REPO_BINARY_NODE");

	public InputStream saveBinary(String id, InputStream file, String mimeType) {

		session = getSession();

		try {
			Node root = session.getRootNode();
			checkRootNode();
			
			Node binaryFiles = root.getNode(BASE_REPO_BINARY_NODE);

			Node fileNode = binaryFiles.addNode(id, "nt:resource");
			
			fileNode.setProperty("jcr:data", file);
			fileNode.setProperty("jcr:mimeType", mimeType);
			fileNode.setProperty("jcr:lastModified", System.currentTimeMillis());
		
			session.save();
			
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidItemStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchNodeTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;

	}


	public InputStream getBinary(String src) {

		session = getSession();

		try {
			Node root = session.getRootNode();
			Node node = root.getNode("binaryFiles/" + src);

			return node.getProperty("jcr:data").getStream();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidItemStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchNodeTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;

	}


	private void checkRootNode() throws RepositoryException{
		Node root = session.getRootNode();
		if(!root.hasNode("binaryFiles")){
			root.addNode("binaryFiles");
			session.save();
		}
	}

}
