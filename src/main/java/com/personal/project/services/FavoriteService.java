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
     * @param refFavoriteRepository Favorite repository.
     */
    public FavoriteService(final FavoriteRepository refFavoriteRepository) {
        this.favoriteRepository = refFavoriteRepository;
    }

}
