package com.personal.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * FavoriteId class, composite key for Favorite.
 */
@Embeddable
public class FavoriteId implements java.io.Serializable {

    /**
     * Id user.
     */
    @NotNull
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    /**
     * Id plant.
     */
    @NotNull
    @Column(name = "id_plant", nullable = false)
    private Long idPlant;

    /**
     * Protected constructor.
     */
    protected FavoriteId() { }

    /**
     * Constructor.
     * @param newIdUser id user.
     * @param newIdPlant id plant.
     */
    public FavoriteId(final Long newIdUser, final Long newIdPlant) {
        this.idUser = newIdUser;
        this.idPlant = newIdPlant;
    }

    /**
     * Get id user.
     * @return id user.
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Get id plant.
     * @return id plant.
     */
    public Long getIdPlant() {
        return idPlant;
    }

    /**
     * Override Equals method.
     * @param o object to check if it is equal at the original.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FavoriteId)) {
            return false;
        }
        FavoriteId entity = (FavoriteId) o;
        return Objects.equals(this.idUser, entity.idUser)
                && Objects.equals(this.idPlant, entity.idPlant);
    }

    /**
     * Hash code.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPlant);
    }
}
