package com.personal.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class for Plant entity.
 */
@Entity(name = "Plants")
public class Plant {

    /**
     * Id of the plant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plant", nullable = false)
    private Long id;

    /**
     * Scientific name of the plant.
     */
    @NotNull
    @NotEmpty
    @Column(name = "scientific_name", nullable = false)
    private String scientificName;

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
     * Foliage of the plant.
     */
    @NotNull
    @NotEmpty
    private String foliage;

    /**
     * Flowers of the plant.
     */
    @NotNull
    @NotEmpty
    private String flowers;

    /**
     * Size of the plant.
     */
    @NotNull
    private double size;

    /**
     * Sunlight needed to take care about the plant.
     */
    @NotNull
    @NotEmpty
    private String sunlight;

    /**
     * Watering needed to take care about the plant.
     */
    @NotNull
    @NotEmpty
    private String watering;

    /**
     * Type of Soil of the plant.
     */
    @NotNull
    @NotEmpty
    private String soil;

    /**
     * Maximum size of temperature.
     */
    private static final int MAX_SIZE_OF_TEMPERATURE = 10;

    /**
     * Temperature needed for a good environment.
     */
    @Size(max = MAX_SIZE_OF_TEMPERATURE)
    @NotNull
    @NotEmpty
    private String temperature;

    /**
     * Explain how to take care about the plant.
     */
    @NotNull
    @NotEmpty
    private String care;

    /**
     * Explain how the plant can be toxic.
     */
    @NotNull
    @NotEmpty
    private String toxicity;

    /**
     * Image of the plant.
     */
    @NotNull
    @NotEmpty
    private String image;

    /**
     * Species of the plant.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_species")
    @JsonManagedReference
    private Specie specie;

    /**
     * Description of Environment.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_environment")
    @JsonManagedReference
    private Environment environment;

    /**
     * Regions where the plant can be found.
     */
    @ManyToMany(mappedBy = "plants")
    @JsonManagedReference
    private Set<Region> regions;

    /**
     * Relation for the plant which are favorite for certain users.
     */
    @OneToMany(mappedBy = "plant")
    private Set<Favorite> favorites = new LinkedHashSet<>();

    /**
     * Constructor without parameters.
     */
    protected Plant() {
        // Default constructor.
    }

    /**
     * Build a plant with the given parameters.
     * @param builder Builder that contains the parameters of the plant.
     */
    private Plant(final Builder builder) {
        this.id = builder.id;
        this.scientificName = builder.scientificName;
        this.name = builder.name;
        this.description = builder.description;
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
        this.favorites = builder.favorites;
    }

    /**
     * Builder class for Plant.
     */
    public static class Builder {
        /**
         * Id of the plant.
         */
        private Long id;
        /**
         * Scientific name of the plant.
         */
        private String scientificName;
        /**
         * Name of the plant.
         */
        private String name;
        /**
         * Description of the plant.
         */
        private String description;
        /**
         * Foliage of the plant.
         */
        private String foliage;
        /**
         * Flowers of the plant.
         */
        private String flowers;
        /**
         * Size of the plant.
         */
        private double size;
        /**
         * Sunlight needed to take care about the plant.
         */
        private String sunlight;
        /**
         * Watering needed to take care about the plant.
         */
        private String watering;
        /**
         * Type of Soil of the plant.
         */
        private String soil;
        /**
         * Temperature needed for a good environment.
         */
        private String temperature;
        /**
         * Explain how to take care about the plant.
         */
        private String care;
        /**
         * Explain how the plant can be toxic.
         */
        private String toxicity;
        /**
         * Image of the plant.
         */
        private String image;
        /**
         * Species of the plant.
         */
        private Specie specie;
        /**
         * Description of Environment.
         */
        private Environment environment;
        /**
         * Regions where the plant can be found.
         */
        private Set<Region> regions;
        /**
         * Relation for the plant which are favorite for certain users.
         */
        private Set<Favorite> favorites;

        /**
         * Constructor without parameters.
         */
        public Builder() {
            // Default constructor.
        }

        /**
         * Setters for the Plant.
         * @param newId Id of the plant.
         * @return Builder.
         */
        public Builder setId(final Long newId) {
            this.id = newId;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newScientificName Scientific name of the plant.
         * @return Builder.
         */
        public Builder setScientificName(final String newScientificName) {
            this.scientificName = newScientificName;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newName Name of the plant.
         * @return Builder.
         */
        public Builder setName(final String newName) {
            this.name = newName;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newDescription Description of the plant.
         * @return Builder.
         */
        public Builder setDescription(final String newDescription) {
            this.description = newDescription;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newFoliage Foliage of the plant.
         * @return Builder.
         */
        public Builder setFoliage(final String newFoliage) {
            this.foliage = newFoliage;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newFlowers Flowers of the plant.
         * @return Builder.
         */
        public Builder setFlowers(final String newFlowers) {
            this.flowers = newFlowers;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newSize Size of the plant.
         * @return Builder.
         */
        public Builder setSize(final double newSize) {
            this.size = newSize;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newSunlight Sunlight needed to take care about the plant.
         * @return Builder.
         */
        public Builder setSunlight(final String newSunlight) {
            this.sunlight = newSunlight;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newWatering Watering needed to take care about the plant.
         * @return Builder.
         */
        public Builder setWatering(final String newWatering) {
            this.watering = newWatering;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newSoil Type of Soil of the plant.
         * @return Builder.
         */
        public Builder setSoil(final String newSoil) {
            this.soil = newSoil;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newTemperature Temperature needed for a good environment.
         * @return Builder.
         */
        public Builder setTemperature(final String newTemperature) {
            this.temperature = newTemperature;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newCare Explain how to take care about the plant.
         * @return Builder.
         */
        public Builder setCare(final String newCare) {
            this.care = newCare;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newToxicity Explain how the plant can be toxic.
         * @return Builder.
         */
        public Builder setToxicity(final String newToxicity) {
            this.toxicity = newToxicity;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newImage Image of the plant.
         * @return Builder.
         */
        public Builder setImage(final String newImage) {
            this.image = newImage;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newSpecie Species of the plant.
         * @return Builder.
         */
        public Builder setSpecie(final Specie newSpecie) {
            this.specie = newSpecie;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newEnvironment Description of Environment.
         * @return Builder.
         */
        public Builder setEnvironment(final Environment newEnvironment) {
            this.environment = newEnvironment;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newRegions Regions where the plant can be found.
         * @return Builder.
         */
        public Builder setRegions(final Set<Region> newRegions) {
            this.regions = newRegions;
            return this;
        }

        /**
         * Setters for the Plant.
         * @param newFavorites Relation for the plant
         *                     which are favorite for certain users.
         * @return Builder.
         */
        public Builder setFavorites(final Set<Favorite> newFavorites) {
            this.favorites = newFavorites;
            return this;
        }

        /**
         * Build a plant with the given parameters.
         * @return Plant.
         */
        public Plant build() {
            return new Plant(this);
        }
    }

    /**
     * Getters for id.
     * @return Id of the plant.
     */
    public long getId() {
        return id;
    }

    /**
     * Getters for name.
     * @return Name of the plant.
     */
    public String getName() {
        return name;
    }

    /**
     * Getters for scientificName.
     * @return Scientific name of the plant.
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Getters for description.
     * @return Description of the plant.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getters for foliage.
     * @return Foliage of the plant.
     */
    public String getFoliage() {
        return foliage;
    }

    /**
     * Getters for flowers.
     * @return Flowers of the plant.
     */
    public String getFlowers() {
        return flowers;
    }

    /**
     * Getters for size.
     * @return Size of the plant.
     */
    public double getSize() {
        return size;
    }

    /**
     * Getters for sunlight.
     * @return Sunlight needed to take care about the plant.
     */
    public String getSunlight() {
        return sunlight;
    }

    /**
     * Getters for watering.
     * @return Watering needed to take care about the plant.
     */
    public String getWatering() {
        return watering;
    }

    /**
     * Getters for soil.
     * @return Type of Soil of the plant.
     */
    public String getSoil() {
        return soil;
    }

    /**
     * Getters for temperature.
     * @return Temperature needed for a good environment.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Getters for care.
     * @return Explain how to take care about the plant.
     */
    public String getCare() {
        return care;
    }

    /**
     * Getters for toxicity.
     * @return Explain how the plant can be toxic.
     */
    public String getToxicity() {
        return toxicity;
    }

    /**
     * Getters for image.
     * @return Image of the plant.
     */
    public String getImage() {
        return image;
    }

    /**
     * Getters for specie.
     * @return Species of the plant.
     */
    public Specie getSpecie() {
        return this.specie;
    }

    /**
     * Getters for environment.
     * @return Description of Environment.
     */
    public Environment getEnvironment() {
        return this.environment;
    }

    /**
     * Getters for regions.
     * @return Regions where the plant can be found.
     */
    public Set<Region> getRegions() {
        return this.regions;
    }

    /**
     * Getters for favorites.
     * @return Relation for the plant which are favorite for certain users.
     */
    public Set<Favorite> getFavorites() {
        return this.favorites;
    }

}
