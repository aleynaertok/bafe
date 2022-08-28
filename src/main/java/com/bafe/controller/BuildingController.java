package com.bafe.controller;


import com.bafe.dto.BuildingDto;
import com.bafe.dto.BuildingInformationDto;
import com.bafe.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> saveBuild(@PathVariable Long id, @RequestBody BuildingDto buildingDto) {

        buildingService.saveBuild(id,buildingDto);
        return ResponseEntity.ok().build();



    }


    @GetMapping("/{id}")
    public ResponseEntity<List<BuildingInformationDto>> getPersonalBuildings(@PathVariable Long id) {

        return ResponseEntity.ok(buildingService.getPersonalBuildings(id));

    }


}
