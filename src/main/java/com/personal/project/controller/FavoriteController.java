package com.personal.project.controller;


import com.personal.project.services.FavoriteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Specie controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteController {

    /**
     * Favorite service.
     */
    private final FavoriteService favoriteService;

    /**
     * Constructor for FavoriteController.
     * @param refFavoriteService the favorite service
     */
    public FavoriteController(final FavoriteService refFavoriteService) {
        this.favoriteService = refFavoriteService;
    }
}
