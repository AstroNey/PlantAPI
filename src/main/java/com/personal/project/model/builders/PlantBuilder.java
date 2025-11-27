package com.personal.project.model.builders;

import com.personal.project.model.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@NoArgsConstructor
@Setter
@Accessors(chain = true)
public class PlantBuilder {

    public Long id;
    public String scientificName;
    public String name;
    public String foliage;
    public String flowers;
    public double size;
    public String sunlight;
    public String watering;
    public String soil;
    public String temperature;
    public String care;
    public String toxicity;
    public String image;
    public Specie specie;
    public Environment environment;
    public Set<Region> regions;
    public Set<Favorite> favorites;

    /**
     * Build a plant with the given parameters.
     * @return Plant.
     */
    public Plant build() {
        return new Plant(this);
    }
}