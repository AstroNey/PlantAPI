package com.personal.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class FavoriteId implements java.io.Serializable {

    @NotNull
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @NotNull
    @Column(name = "id_plant", nullable = false)
    private Long idPlant;

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
