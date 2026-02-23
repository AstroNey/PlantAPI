package com.personal.project.repository;

import com.personal.project.entities.Favorite;
import com.personal.project.entities.FavoriteId;
import com.personal.project.entities.User;
import com.personal.project.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Favorite repository.
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findByUser(User user);
    List<Favorite> findByPlant(Plant plant);
    Optional<Favorite> findById(FavoriteId id);
    boolean existsById(FavoriteId id);
    void deleteById(FavoriteId id);
}
