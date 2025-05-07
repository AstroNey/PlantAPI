package com.personal.project.model.requestModel;

/**
 * User request to create or modify.
 */
public class UserRequest {

    /**
     * Username.
     */
    private String username;

    /**
     * Lastname.
     */
    private String lastname;

    /**
     * Password.
     */
    private String password;

    /**
     * Email.
     */
    private String email;

    /**
     * Constructor only for test.
     * @param refUsername username
     * @param refLastname lastname
     * @param refPassword password
     * @param refEmail email
     */
    public UserRequest(final String refUsername, final String refLastname,
                       final String refEmail, final String refPassword) {
        this.username = refUsername;
        this.lastname = refLastname;
        this.password = refPassword;
        this.email = refEmail;
    }

    /**
     * Get username.
     * @return username
     */
    public String getName() {
        return username;
    }

    /**
     * Get lastname.
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Get password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get email.
     * @return email
     */
    public String getEmail() {
        return email;
    }
}
