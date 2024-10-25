package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * User that define user account on site.
 */
@Entity
public class User {

    /**
     * Unique id, generated BD sde.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * FirstName of user.
     */
    private String firstName;

    /**
     * LastName of user.
     */
    private String lastName;

    /**
     * Email of user.
     */
    private String email;

    /**
     * Phone number of user.
     */
    private String phoneNumber;
}
