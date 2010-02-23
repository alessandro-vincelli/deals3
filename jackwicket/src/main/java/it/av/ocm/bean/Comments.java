package it.av.ocm.bean;

import java.util.Date;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.apache.wicket.IClusterable;

@Node(jcrType = "nt:unstructured", jcrMixinTypes = "mix:versionable")
public class Comments implements BasicNode, IClusterable {
    private static final long serialVersionUID = 1L;
    @Field(path = true)
    private String path;
    @Field(uuid = true)
    private String uuid;
    @Field
    private String text;
    @Field
    private String title;
    @Field
    private Date date;
    @Field
    private String email;
    @Field
    private String visitorName;
    private String version;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public final String getUuid() {
        return uuid;
    }

    public final void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public final String getText() {
        return text;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        this.date = date;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final String getVisitorName() {
        return visitorName;
    }

    public final void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

}
