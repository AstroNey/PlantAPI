package com.personal.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoriteIdTest {

    private FavoriteId favoriteId;

    @BeforeEach
    void setUp() {
        favoriteId = new FavoriteId(1L, 1L);
    }

    @Test
    void testValidFavoriteId() {
        assertEquals(1L, favoriteId.getIdUser());
        assertEquals(1L, favoriteId.getIdPlant());
    }

    @Test
    void testInvalidFavoriteId() {
        // Attempt to create FavoriteId with null values
        FavoriteId favoriteInvalidId = new FavoriteId(null, null);

        // Check that the idUser  and idPlant are null
        assertNull(favoriteInvalidId.getIdUser (), "User  ID should be null");
        assertNull(favoriteInvalidId.getIdPlant(), "Plant ID should be null");

        // You can also check if the equals method behaves correctly with null values
        FavoriteId anotherFavoriteId = new FavoriteId(null, null);
        assertEquals(favoriteInvalidId, anotherFavoriteId, "FavoriteId objects with null IDs should be equal");
    }

    @Test
    void testEquals() {
        FavoriteId favoriteId1 = new FavoriteId(1L, 2L);
        FavoriteId favoriteId2 = new FavoriteId(1L, 2L);
        FavoriteId favoriteId3 = new FavoriteId(1L, 3L);
        FavoriteId favoriteId4 = new FavoriteId(2L, 1L);
        String notAFavoriteId = "Not a FavoriteId";

        // Test: Same object should be equal to itself
        assertEquals(favoriteId1, favoriteId1, "Should be equal to itself");

        // Test: Two FavoriteId objects with the same values should be equal
        assertEquals(favoriteId1, favoriteId2, "Should be equal to another FavoriteId with same values");

        // Test: Two FavoriteId objects with different idPlant should not be equal
        assertNotEquals(favoriteId1, favoriteId3, "Should not be equal to another FavoriteId with different values");

        // Test: An object should not be equal to null
        assertNotEquals(null, favoriteId1, "Should not be equal to null");

        // Test: An object should not be equal to an object of a different class
        assertNotEquals(favoriteId1, notAFavoriteId, "Should not be equal to an object of a different class");

        // Test: Two FavoriteId objects with different idUser  and idPlant should not be equal
        assertNotEquals(favoriteId1, favoriteId4, "Should not be equal to another FavoriteId with different idUser  and idPlant");
    }

    @Test
    void testHashCode() {
        FavoriteId favoriteId1 = new FavoriteId(1L, 2L);
        FavoriteId favoriteId2 = new FavoriteId(1L, 2L);
        FavoriteId favoriteId3 = new FavoriteId(2L, 3L);

        assertEquals(favoriteId1.hashCode(), favoriteId2.hashCode(), "Hash codes should match for equal FavoriteId objects");
        assertNotEquals(favoriteId1.hashCode(), favoriteId3.hashCode(), "Hash codes should not match for different FavoriteId objects");
    }
}
