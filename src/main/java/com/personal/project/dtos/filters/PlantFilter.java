package com.personal.project.dtos.filters;

import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlantFilter {
    String name;
    LightLevel hasSunlight;
    Watering hasWatering;
    Boolean hasFlower;
}
