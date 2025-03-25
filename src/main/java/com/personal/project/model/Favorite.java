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
     */
    public Favorite(
            final FavoriteId newId
    ) {
        this.id = newId;
    }

    /**
     * Get id.
     * @return id.
     */
    public FavoriteId getId() {
        return id;
    }
}
