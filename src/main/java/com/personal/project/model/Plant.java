package com.personal.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.project.model.builders.PlantBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Plants")
@NoArgsConstructor
@Getter
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plant", nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "scientific_name", nullable = false)
    private String scientificName;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String foliage;

    @NotNull
    @NotEmpty
    private String flowers;

    @NotNull
    private double size;

    @NotNull
    @NotEmpty
    private String sunlight;

    @NotNull
    @NotEmpty
    private String watering;

    @NotNull
    @NotEmpty
    private String soil;

    private static final int MAX_SIZE_OF_TEMPERATURE = 10;

    @Size(max = MAX_SIZE_OF_TEMPERATURE)
    @NotNull
    @NotEmpty
    private String temperature;

    @NotNull
    @NotEmpty
    private String care;

    @NotNull
    @NotEmpty
    private String toxicity;

    @NotNull
    @NotEmpty
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_species")
    private Specie specie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_environment")
    private Environment environment;

    @ManyToMany
    @JoinTable(
            name = "plant_region",
            joinColumns = @JoinColumn(name = "id_plant"),
            inverseJoinColumns = @JoinColumn(name = "id_region")
    )
    private Set<Region> regions;

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Favorite> favorites = new LinkedHashSet<>();

    public Plant(final PlantBuilder builder) {
        this.id = builder.id;
        this.scientificName = builder.scientificName;
        this.name = builder.name;
        this.foliage = builder.foliage;
        this.flowers = builder.flowers;
        this.size = builder.size;
        this.sunlight = builder.sunlight;
        this.watering = builder.watering;
        this.soil = builder.soil;
        this.temperature = builder.temperature;
        this.care = builder.care;
        this.toxicity = builder.toxicity;
        this.image = builder.image;
        this.specie = builder.specie;
        this.environment = builder.environment;
        this.regions = builder.regions;
        this.favorites = builder.favorites != null
                ? builder.favorites : new LinkedHashSet<>();
    }

    /**
     * Add a favorite to the plant.
     * @param favorite Favorite to add.
     */
    public void addFavorite(final Favorite favorite) {
        this.getFavorites().add(favorite);
    }
}
