package com.personal.project.services;

import com.personal.project.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

/**
 * Favorite service.
 */
@Service
public class FavoriteService {
    /**
     * Favorite repository.
     */
    private final FavoriteRepository favoriteRepository;

    /**
     * Constructor.
     * @param favoriteRepository Favorite repository.
     */
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

}
