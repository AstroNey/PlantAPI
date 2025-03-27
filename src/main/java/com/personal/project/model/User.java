package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User class.
 * Represents a user.
 */
@Entity(name = "users")
public class User {

    /**
     * User id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long id;

    /**
     * User name.
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * User last name.
     */
    @NotNull
    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * User email.
     */
    @NotNull
    @NotEmpty
    private String email;

    /**
     * User password.
     */
    @NotNull
    @NotEmpty
    private String password;

    /**
     *
     */
    @OneToMany
    private Set<Favorite> favorites = new LinkedHashSet<>(); //TODO

    /**
     * Protected constructor.
     */
    protected User() { }

    /**
     * Constructor.
     * @param newId Long
     * @param newName String
     * @param newLastName String
     * @param newEmail String
     * @param newPassword String
     */
    public User(
            final Long newId, final String newName,
            final String newLastName, final String newEmail,
            final String newPassword
    ) {
        this.id = newId;
        this.name = newName;
        this.lastName = newLastName;
        this.email = newEmail;
        this.password = newPassword;
    }

    /**
     * Get user id.
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get user name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get user last name.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get user email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get user password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
