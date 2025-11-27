package com.personal.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Region class.
 * Represents a region where plants are located.
 */
@Entity(name = "regions")
public class Region {

    /**
     * Id region.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region", nullable = false)
    private Long id;

    /**
     * Region name.
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Protected constructor.
     */
    protected Region() { }

    /**
     * Constructor.
     * @param newId Region id.
     * @param newName Region name.
     */
    public Region(final Long newId, final String newName) {
        this.id = newId;
        this.name = newName;
    }

    /**
     * Get region id.
     * @return Region id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get region name.
     * @return Region name.
     */
    public String getName() {
        return name;
    }
}
