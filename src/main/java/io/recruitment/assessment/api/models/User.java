package io.recruitment.assessment.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Basic user entity
 */
@Entity(name = "users")
public class User extends BaseModel {

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts;

    /**
     * Gets user username
     * @return {String}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * @param username this is the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user password
     * @return {String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of a user
     * @param password this is the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user
     *
     * @return {Role}
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set the role of the user
     *
     * @param role role you want the user to take
     */
    public void setRole(Role role) {
        this.role = role;
    }

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', password='%s, role='%s']",
                getId(), username, password, role.name());
    }
}
