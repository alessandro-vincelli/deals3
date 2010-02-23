package it.av.ocm.bean;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.apache.wicket.IClusterable;

@Node(jcrType = "nt:unstructured", jcrMixinTypes = "mix:versionable")
public class UserProfile implements BasicNode, IClusterable {
    private static final long serialVersionUID = 1L;
    @Field(path = true)
    private String path;
    @Field(uuid = true)
    private String uuid;
    @Field
    private String name;
    @Field
    private String description;
    private String version;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
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

}
