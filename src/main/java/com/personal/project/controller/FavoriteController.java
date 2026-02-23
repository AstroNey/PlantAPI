package com.personal.project.controller;

import com.personal.project.entities.Favorite;
import com.personal.project.services.FavoriteService;
import com.personal.project.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserRepository userRepository;

    public FavoriteController(final FavoriteService refFavoriteService, UserRepository userRepository) {
        this.favoriteService = refFavoriteService;
        this.userRepository = userRepository;
    }

    // Ajouter un favori
    @PostMapping("/{plantId}")
    public Favorite addFavorite(@PathVariable Long plantId, Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        return favoriteService.addFavorite(userId, plantId);
    }

    // Retirer un favori
    @DeleteMapping("/{plantId}")
    public void removeFavorite(@PathVariable Long plantId, Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        favoriteService.removeFavorite(userId, plantId);
    }

    // Lister mes favoris
    @GetMapping
    public List<Favorite> getFavorites(Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        return favoriteService.getFavoritesByUser(userId);
    }

    // Vérifier si une plante est en favori
    @GetMapping("/{plantId}")
    public boolean isFavorite(@PathVariable Long plantId, Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        return favoriteService.isFavorite(userId, plantId);
    }

    // Méthode utilitaire pour extraire l'id utilisateur
    private Long getUserIdFromAuth(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found for username: " + username))
                .getId();
    }
}
