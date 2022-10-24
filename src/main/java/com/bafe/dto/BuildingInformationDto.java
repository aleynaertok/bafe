package com.bafe.dto;

import com.bafe.model.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInformationDto extends BaseDto{

    private String name;
    private String status;

    public BuildingInformationDto(Building building) {
        this.status = building.getStatus();
        this.name = building.getName();
        this.setId(building.getId());

    }


}
