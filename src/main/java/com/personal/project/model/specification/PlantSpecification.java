package com.personal.project.model.specification;

import com.personal.project.model.Plant;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PlantSpecification {

    public static Specification<Plant> nameStartWith(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(root.get("name"), name + "%");
    }

    public static Specification<Plant> hasRegion(Long regionId) {
        return (root, query, cb) -> {
            if (regionId == null || regionId == 0) {
                return null; // aucun filtre appliqué
            }

            // On fait un JOIN sur la relation ManyToMany
            Join<Object, Object> regionJoin = root.join("regions", JoinType.INNER);

            // On compare l'ID de la région jointe
            return cb.equal(regionJoin.get("id"), regionId);
        };
    }
}
