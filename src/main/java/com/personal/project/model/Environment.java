package com.personal.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Environment class.
 * Represents an environment where plants are located.
 */
@Entity
@Table(name = "environments")
public class Environment {

    /**
     * Environment attributes.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_environment", nullable = false)
    private Long id;

    /**
     * Environment name.
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Plants in the environment.
     */
    @OneToMany(mappedBy = "environment")
    private Set<Plant> plants = new LinkedHashSet<>();

    /**
     * Protected constructor.
     */
    protected Environment() { }

    /**
     * Constructor.
     * @param newId Environment id.
     * @param newName Environment name.
     */
    public Environment(final Long newId, final String newName) {
        this.id = newId;
        this.name = newName;
    }

    /**
     * Get the environment id.
     * @return Environment id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the environment name.
     * @return Environment name.
     */
    public String getName() {
        return name;
    }
}
