package com.bafe.dto;


import com.bafe.model.Building;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingDto extends BaseDto implements Serializable {

    private String name;
    private int floorNumber;
    private int years;
    private String zone;
    private String ground;
    private int buildingPoint;
    private Boolean reinforcedConcrete;
    private AccountDto accountDto;


}
