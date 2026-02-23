package com.personal.project.entities.specification;

import com.personal.project.entities.Plant;
import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlantSpecificationTest {

    @Mock
    private Root<Plant> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private Path<String> stringPath;

    @Mock
    private Path<Object> objectPath;

    @Mock
    private Predicate predicate;

    @Test
    void constructor_throwsIllegalState() throws Exception {
        Constructor<PlantSpecification> constructor =
                PlantSpecification.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(IllegalStateException.class, exception.getCause());
    }

    // --- nameStartWith ---

    @Test
    @SuppressWarnings("unchecked")
    void nameStartWith_withValue_returnsPredicate() {
        when(root.<String>get("name")).thenReturn(stringPath);
        when(cb.like(eq(stringPath), anyString())).thenReturn(predicate);

        Specification<Plant> spec = PlantSpecification.nameStartWith("Rose");
        Predicate result = spec.toPredicate(root, query, cb);

        assertNotNull(result);
    }

    @Test
    void nameStartWith_withNull_returnsNull() {
        Specification<Plant> spec = PlantSpecification.nameStartWith(null);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNull(result);
    }

    // --- hasLightLevel ---

    @Test
    void hasLightLevel_withValue_returnsPredicate() {
        when(root.get("sunlight")).thenReturn(objectPath);
        when(cb.equal(objectPath, LightLevel.HIGH)).thenReturn(predicate);

        Specification<Plant> spec = PlantSpecification.hasLightLevel(LightLevel.HIGH);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNotNull(result);
    }

    @Test
    void hasLightLevel_withNull_returnsNull() {
        Specification<Plant> spec = PlantSpecification.hasLightLevel(null);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNull(result);
    }

    // --- hasWatering ---

    @Test
    void hasWatering_withValue_returnsPredicate() {
        when(root.get("watering")).thenReturn(objectPath);
        when(cb.equal(objectPath, Watering.MEDIUM)).thenReturn(predicate);

        Specification<Plant> spec = PlantSpecification.hasWatering(Watering.MEDIUM);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNotNull(result);
    }

    @Test
    void hasWatering_withNull_returnsNull() {
        Specification<Plant> spec = PlantSpecification.hasWatering(null);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNull(result);
    }

    // --- hasFlowers ---

    @Test
    void hasFlowers_withValue_returnsPredicate() {
        when(root.get("hasFlowers")).thenReturn(objectPath);
        when(cb.equal(objectPath, true)).thenReturn(predicate);

        Specification<Plant> spec = PlantSpecification.hasFlowers(true);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNotNull(result);
    }

    @Test
    void hasFlowers_withNull_returnsNull() {
        Specification<Plant> spec = PlantSpecification.hasFlowers(null);
        Predicate result = spec.toPredicate(root, query, cb);

        assertNull(result);
    }
}

