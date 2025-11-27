package com.personal.project.repository;

import com.personal.project.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Favorite repository.
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}

