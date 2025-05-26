package com.personal.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

/**
 * Favorite class.
 * Represents a favorite plant.
 */
@Entity
@Table(name = "favorites")
public class Favorite {

    // TODO adapt some tests

    /**
     * Id favorite comes from composed key.
     */
    @EmbeddedId
    private FavoriteId id;

    /**
     * Plant that is marked as favorite.
     * This field is not serialized to JSON to avoid circular references.
     */
    @ManyToOne
    @MapsId("idPlant")
    @JoinColumn(name = "id_plant")
    private Plant plant;

    /**
     * User who added the favorite.
     * This field is not serialized to JSON to avoid circular references.
     */
    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    /**
     * Date when the favorite was added.
     */
    @CreationTimestamp
    @Column(name = "added_on")
    private LocalDate  addedOn = LocalDate.now();

    /**
     * Protected constructor.
     */
    protected Favorite() { }

    /**
     * Constructor.
     * @param newUser User.
     * @param newPlant Plant.
     */
    public Favorite(final User newUser, final Plant newPlant) {
        this.user = newUser;
        this.plant = newPlant;
        this.id = new FavoriteId(user.getId(), plant.getId());
        this.addedOn = LocalDate.now();
    }

    /**
     * Get id.
     * @return id.
     */
    public FavoriteId getId() {
        return id;
    }

    /**
     * Get the associated Plant.
     * @return Plant.
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Get the date the favorite was added.
     * @return LocalDate.
     */
    public LocalDate getAddedOn() {
        return addedOn;
    }
}
