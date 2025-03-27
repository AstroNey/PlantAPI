package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Specie class.
 * Represents a specie of plant.
 */
@Entity
@Table(name = "species")
public class Specie {

    /**
     * Specie id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_species", nullable = false)
    private Long id;

    /**
     * Specie name.
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Protected constructor.
     */
    protected Specie() { }

    /**
     * Constructor.
     * @param newId Specie id.
     * @param newName Specie name.
     */
    public Specie(final Long newId, final String newName) {
        this.id = newId;
        this.name = newName;
    }

    /**
     * Get specie id.
     * @return Specie id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get specie name.
     * @return Specie name.
     */
    public String getName() {
        return name;
    }
}
