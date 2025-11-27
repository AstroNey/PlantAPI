package com.personal.project.model.specification;

import com.personal.project.model.Plant;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;

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
     * Specification to filter plants associated with a specific region.
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

    /**
     * Specification to filter plants associated with a specific environment.
     * @param environmentId the ID of the environment
     * @return a Specification for filtering plants by environment ID
     */
    public static Specification<Plant> hasEnvironment(final Long environmentId) {
        return (root, query, cb) -> {
            if (environmentId == null || environmentId == 0) {
                return null; // aucun filtre appliqué
            }

            Join<Object, Object> environmentJoin
                    = root.join("environment", JoinType.INNER);

            return cb.equal(environmentJoin.get("id"), environmentId);
        };
    }

    /**
     * Specification to filter plants associated with a specific specie.
     * @param specieId the ID of the specie
     * @return a Specification for filtering plants by specie ID
     */
    public static Specification<Plant> hasSpecie(final Long specieId) {
        return (root, query, cb) -> {
            if (specieId == null || specieId == 0) {
                return null; // aucun filtre appliqué
            }

            Join<Object, Object> specieJoin
                    = root.join("specie", JoinType.INNER);

            return cb.equal(specieJoin.get("id"), specieId);
        };
    }

    /**
     * Specification to filter plants by toxicity.
     * @param toxicity the toxicity level
     * @return a Specification for filtering plants by toxicity
     */
    public static Specification<Plant> hasToxicity(final String toxicity) {
        return (root, query, cb) ->
                toxicity == null ? null : cb.equal(root.get("toxicity"), toxicity);
    }

    // TODO sunlight, watering, soil, temperature
    // TODO favorites plus tard
}
