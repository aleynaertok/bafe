package com.bafe.dto;


import com.bafe.model.Building;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingDto extends BaseDto implements Serializable {


    private String name;
    private int floorNumber;
    private String status;
    private int years;
    private String zone;
    private String ground;
    private int buildingPoint;
    private double acceleration;
    private List<Boolean> verticalIrregularities;
    private List<Boolean> horizontalIrregularities;
    private int verticalNumber;
    private Boolean impactEffect;
    private Boolean heavyHits;
    private int horizontalNumber;
    private String buildingType;
    private Boolean reinforcedConcrete;
    private AccountDto accountDto;

}
