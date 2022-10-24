package com.bafe.service;

import com.bafe.dto.BuildingDto;
import com.bafe.dto.BuildingInformationDto;

import java.util.List;

public interface IBuildingService {

    void deleteBuild(Long id);
    void saveBuild(Long id, BuildingDto buildingDto);
    void irregularities(BuildingDto buildingDto);
    void calculateZone(BuildingDto buildingDto);
    List<BuildingInformationDto> getPersonalBuildings(Long id);
    void reInforcedConcrete(BuildingDto buildingDto);
    void timberWork(BuildingDto buildingDto);
    void steelStructure(BuildingDto buildingDto);
    void masonry(BuildingDto buildingDto);

}
