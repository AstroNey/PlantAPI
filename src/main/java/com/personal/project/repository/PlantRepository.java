package com.personal.project.repository;

import com.personal.project.model.Plant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>,
                                        JpaSpecificationExecutor<Plant> {

    List<Plant> findAll(Specification<Plant>  specification);
}
