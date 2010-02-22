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

import org.springmodules.jcr.support.JcrDaoSupport;

public class RepoImage extends JcrDaoSupport {

	Session session;

	public InputStream getImage(String src) {

		session = getSession();
		
		try {
			Node root = session.getRootNode();

            // Store content
/*
			FileInputStream file = new FileInputStream("/Users/alessandro/Desktop/alessandro.jpg");
            Node hello = root.addNode("hello" );
            Node world = hello.addNode("world", "nt:resource");
            //world.setProperty("message", "Hello, World!");
            world.setProperty("jcr:data", file);
            world.setProperty("jcr:mimeType", "image/jpg");
            world.setProperty("jcr:lastModified", System.currentTimeMillis());
            System.out.println(world.getProperty("jcr:mimeType").getString());
            System.out.println("worldPath" + world.getPath());
            session.save();
*/
            // Retrieve content
            Node node = root.getNode("binaryFiles/" + src);
            
            //System.out.println(node.getPath());
            //System.out.println(node.getProperty("jcr:mimeType").getString());
            //System.out.println(node.getProperty("jcr:data").getString());
            return node.getProperty("jcr:data").getStream();
            

			/*
			FileInputStream file = new FileInputStream("/Users/alessandro/Desktop/alessandro.jpg");
			Node images = root.addNode("images");
			Node image = images.addNode("image");
			image.setProperty("message", "ssss");
			session.save();
			Node node = root.getNode("images/image");
			String pa = node.getPath();
			boolean boolas = node.hasProperties();
			Iterator it = node.getProperties();
			for (Iterator iterator = it; iterator.hasNext();) {
				Object type = (Object) iterator.next();
				type.toString();
				
			}
			
			Property p = node.getProperty("message");
			return node.getProperty("message").getStream();
			*/
			

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

}
