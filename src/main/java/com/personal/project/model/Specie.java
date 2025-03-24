package com.personal.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

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
     * Plants of the specie.
     */
    @OneToMany(mappedBy = "specie", fetch = FetchType.LAZY)
    private Set<Plant> plants = new LinkedHashSet<>();

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
