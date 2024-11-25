package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Class for Plant entity.
 */
@Entity
public class Plant {

    /**
     * Id of the plant.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name of the plant.
     */
    @NotNull
    @NotEmpty
    private String name;

    /**
     * Description of the plant.
     */
    @NotNull
    @NotEmpty
    private String description;

    /**
     * Image of the plant.
     */
    private String image;

    /**
     * Constructor for Plant.
     * @param newId the id
     * @param newName the name
     * @param newDescription the description
     * @param newImage the image
     */
    public Plant(
        final Long newId,
        final String newName,
        final String newDescription,
        final String newImage
    ) {
        this.id = newId;
        this.name = newName;
        this.description = newDescription;
        this.image = newImage;
    }

    /**
     * Protected constructor for Plant.
     */
    protected Plant() {
        // Protected constructor
    }

    /**
     * Getter for the id.
     * @return the id of the plant
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for the name.
     * @return the name of the plant
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name.
     * @param newName the name of the plant
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Getter for the description.
     * @return the description of the plant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description.
     * @param newDescription the description of the plant
     */
    public void setDescription(final String newDescription) {
        this.description = newDescription;
    }

    /**
     * Getter for the image.
     * @return the image of the plant
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for the image.
     * @param newImage the image of the plant
     */
    public void setImage(final String newImage) {
        this.image = newImage;
    }
}
