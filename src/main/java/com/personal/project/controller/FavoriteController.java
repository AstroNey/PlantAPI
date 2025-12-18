package com.personal.project.controller;

import com.personal.project.services.FavoriteService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(final FavoriteService refFavoriteService) {
        this.favoriteService = refFavoriteService;
    }
}
