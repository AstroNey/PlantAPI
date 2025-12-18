package com.personal.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

/**
 * Favorite class.
 * Represents a favorite plant.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @MapsId("idPlant")
    @JoinColumn(name = "id_plant")
    private Plant plant;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Column(name = "added_on")
    private LocalDate  addedOn = LocalDate.now();
}
