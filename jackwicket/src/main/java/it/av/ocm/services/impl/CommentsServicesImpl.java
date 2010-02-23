package it.av.ocm.services.impl;

import it.av.ocm.JackWicketException;
import it.av.ocm.bean.Comments;
import it.av.ocm.services.CommentsServices;

import org.apache.commons.lang.StringUtils;

/**
 * Comments services class. Use this class to manage the {@link Comments}
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public class CommentsServicesImpl extends JcrServiceImpl<Comments> implements  CommentsServices {

    /* (non-Javadoc)
     * @see it.av.ocm.services.CommentsServices#save(it.av.ocm.bean.Comments)
     */
    @Override
    //#FIXME change the generation of the path
    public Comments save(Comments object) throws JackWicketException {
        if (StringUtils.isEmpty(object.getPath())) {
            object.setPath("/" + StringUtils.deleteWhitespace(object.getEmail()));
        }
        if(getJcrMappingtemplate().itemExists(object.getPath())){
            update(object);
        }
        else{
            insert(object);
        }
        getJcrMappingtemplate().save();
        return object;
    }
}
