package com.personal.project.entities.specification;

import com.personal.project.entities.Plant;
import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification for Plant entity to support dynamic queries.
 */
public final class PlantSpecification {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private PlantSpecification() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Specification to filter plants whose names start with the given prefix.
     * @param name the prefix of the plant name
     * @return a Specification for filtering plants by name prefix
     */
    public static Specification<Plant> nameStartWith(final String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(root.get("name"), name + "%");
    }

    /**
     * Specification to filter plants by specific light level.
     * @param lightLevel the light level to filter by
     * @return a Specification for filtering plants by light level
     */
    public static Specification<Plant> hasLightLevel(final LightLevel lightLevel) {
        return (root, query, cb) -> {
            if (lightLevel == null) {
                return null;
            }
            return cb.equal(root.get("sunlight"), lightLevel);
        };
    }

    /**
     * Specification to filter plants by specific watering level.
     * @param watering the watering level to filter by
     * @return a Specification for filtering plants by watering level
     */
    public static Specification<Plant> hasWatering(final Watering watering) {
        return (root, query, cb) -> {
            if (watering == null) {
                return null;
            }
            return cb.equal(root.get("watering"), watering);
        };
    }

    /**
     * Specification to filter plants that have flowers.
     * @param hasFlowers true if plant must have flowers, false if must not have flowers
     * @return a Specification for filtering plants by flowers presence
     */
    public static Specification<Plant> hasFlowers(final Boolean hasFlowers) {
        return (root, query, cb) -> {
            if (hasFlowers == null) {
                return null;
            }
            return cb.equal(root.get("hasFlowers"), hasFlowers);
        };
    }
}
