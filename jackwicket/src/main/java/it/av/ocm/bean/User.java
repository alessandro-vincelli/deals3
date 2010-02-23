package it.av.ocm.bean;

import org.apache.jackrabbit.ocm.manager.beanconverter.impl.ReferenceBeanConverterImpl;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.apache.wicket.IClusterable;

@Node(jcrType = "nt:unstructured", jcrMixinTypes = "mix:versionable")
public class User implements BasicNode, IClusterable{
    private static final long serialVersionUID = 1L;
    @Field(path = true)
    private String path;
    @Field(uuid = true)
    private String uuid;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private String firstname;
    @Field
    private String lastname;
    @Field
    private String email;
    @Bean(converter = ReferenceBeanConverterImpl.class)
    private UserProfile userProfile;
    private String version;

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(String usUsername, String usPassword) {
        this.username = usUsername;
        this.password = usPassword;
    }

    /** full constructor */
    public User(String usUsername, String usPassword, String usFirstname, String usSurname, String usEmail) {
        this.username = usUsername;
        this.password = usPassword;
        this.firstname = usFirstname;
        this.lastname = usSurname;
        this.email = usEmail;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getFirstname() {
        return firstname;
    }

    public final void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public final String getLastname() {
        return lastname;
    }

    public final void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final UserProfile getUserProfile() {
        return userProfile;
    }

    public final void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public final String getPath() {
        return this.path;
    }

    @Override
    public final void setPath(String path) {
        this.path = path;
    }

    @Override
    public final String getVersion() {
        return version;
    }

    @Override
    public final void setVersion(String version) {
        this.version = version;
    }

    public final String getUuid() {
        return uuid;
    }

    public final void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
