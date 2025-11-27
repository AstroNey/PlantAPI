package com.personal.project.services;

import com.personal.project.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(final FavoriteRepository refFavoriteRepository) {
        this.favoriteRepository = refFavoriteRepository;
    }

}
