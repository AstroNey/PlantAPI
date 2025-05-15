package com.personal.project.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @MapsId("idPlant")
    @JoinColumn(name = "id_plant")
    private Plant plant;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
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
     * @param user User.
     * @param plant Plant.
     */
    public Favorite(final User user, final Plant plant) {
        this.user = user;
        this.plant = plant;
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
}
