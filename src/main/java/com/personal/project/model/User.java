package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * User that define user account on site.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String FirstName;

    private String LastName;

    private String Email;

    private String Phone;
}
