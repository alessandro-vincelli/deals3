package it.av.ocm.util;

import java.io.IOException;
import java.io.InputStream;

import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeTypeManager;

import org.apache.jackrabbit.core.nodetype.InvalidNodeTypeDefException;
import org.apache.jackrabbit.core.nodetype.NodeTypeDef;
import org.apache.jackrabbit.core.nodetype.NodeTypeManagerImpl;
import org.apache.jackrabbit.core.nodetype.NodeTypeRegistry;
import org.apache.jackrabbit.core.nodetype.xml.NodeTypeReader;

/**
 * Utility class for managing OCM repositories.
 * 
 */
public final class RepositoryInitTool {

    public void registerNodeTypes(Session session) throws InvalidNodeTypeDefException, javax.jcr.RepositoryException, IOException {

        InputStream xml = getClass().getClassLoader().getResourceAsStream("custom_nodetypes.xml");
        // HINT: throws InvalidNodeTypeDefException, IOException
        NodeTypeDef[] types = NodeTypeReader.read(xml);

        Workspace workspace = session.getWorkspace();
        NodeTypeManager ntMgr = workspace.getNodeTypeManager();
        NodeTypeRegistry ntReg = ((NodeTypeManagerImpl) ntMgr).getNodeTypeRegistry();

        for (int j = 0; j < types.length; j++) {
            NodeTypeDef def = types[j];

            try {
                ntReg.getNodeTypeDef(def.getName());
            } catch (NoSuchNodeTypeException nsne) {
                // HINT: if not already registered than register custom node
                // type
                ntReg.registerNodeType(def);
            }
        }
    }
}
