package com.personal.project.services;

import com.personal.project.entities.Favorite;
import com.personal.project.entities.FavoriteId;
import com.personal.project.entities.Plant;
import com.personal.project.entities.User;
import com.personal.project.repository.FavoriteRepository;
import com.personal.project.repository.PlantRepository;
import com.personal.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Transactional
    public Favorite addFavorite(Long userId, Long plantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new IllegalArgumentException("Plant not found"));
        FavoriteId id = new FavoriteId(userId, plantId);
        if (favoriteRepository.existsById(id)) {
            throw new IllegalStateException("Favorite already exists");
        }
        Favorite favorite = new Favorite(id, plant, user, null);
        return favoriteRepository.save(favorite);
    }

    @Transactional
    public void removeFavorite(Long userId, Long plantId) {
        FavoriteId id = new FavoriteId(userId, plantId);
        if (!favoriteRepository.existsById(id)) {
            throw new IllegalArgumentException("Favorite does not exist");
        }
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return favoriteRepository.findByUser(user);
    }

    public boolean isFavorite(Long userId, Long plantId) {
        FavoriteId id = new FavoriteId(userId, plantId);
        return favoriteRepository.existsById(id);
    }
}
