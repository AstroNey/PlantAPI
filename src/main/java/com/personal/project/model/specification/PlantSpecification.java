package com.personal.project.model.specification;

import com.personal.project.model.Plant;
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
     *
     * @param name the prefix of the plant name
     * @return a Specification for filtering plants by name prefix
     */
    public static Specification<Plant> nameStartWith(final String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(root.get("name"), name + "%");
    }

    /**
     * Specification to filter plants associated with a specific region.
     *
     * @param regionId the ID of the region
     * @return a Specification for filtering plants by region ID
     */
    public static Specification<Plant> hasRegion(final Long regionId) {
        return (root, query, cb) -> {
            if (regionId == null || regionId == 0) {
                return null; // aucun filtre appliqué
            }

            // On fait un JOIN sur la relation ManyToMany
            Join<Object, Object> regionJoin
                    = root.join("regions", JoinType.INNER);

            // On compare l'ID de la région jointe
            return cb.equal(regionJoin.get("id"), regionId);
        };
    }
}
