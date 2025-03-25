package com.personal.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;

/**
 * Favorite class.
 * Represents a favorite plant.
 */
@Entity
@Table(name = "favorite")
public class Favorite {

    /**
     * Id favorite comes from composed key.
     */
    @EmbeddedId
    private FavoriteId id;

    /**
     * User who has the favorite.
     */
    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @NotNull
    private User user;

    /**
     * Plant that is favorite.
     */
    @MapsId("idPlant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plant")
    @NotNull
    private Plant plant;

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
     * @param newId FavoriteId
     * @param newUser User
     * @param newPlant Plant
     */
    public Favorite(
            final FavoriteId newId,
            @Lazy final User newUser,
            @Lazy final Plant newPlant
    ) {
        this.id = newId;
        this.user = newUser;
        this.plant = newPlant;
    }

    /**
     * Get id.
     * @return id.
     */
    public FavoriteId getId() {
        return id;
    }

    /**
     * Get user.
     * @return user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Get plant.
     * @return plant.
     */
    public Plant getPlant() {
        return plant;
    }

}
