package com.bafe.service;

import com.bafe.dto.BuildingDto;
import com.bafe.dto.BuildingInformationDto;
import com.bafe.model.Account;
import com.bafe.model.Building;
import com.bafe.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final AccountService accountService;

    public BuildingService(BuildingRepository buildingRepository, AccountService accountService) {
        this.buildingRepository = buildingRepository;
        this.accountService = accountService;

    }


    public BuildingDto saveBuild(Long id, BuildingDto buildingDto) {
        Building building = new Building();

        Account account = accountService.getAccountById(id);

        if (buildingDto.getReinforcedConcrete()) {
            reInforcedConcrete(buildingDto);
        }


        building.setFloorNumber(buildingDto.getFloorNumber());
        building.setName(buildingDto.getName());
        building.setAccount(account);
        return buildingDto;


    }


    public List<BuildingInformationDto> getPersonalBuildings(Long id) {


        Account account = accountService.getAccountById(id);
        List<Building> buildingsByAccount = buildingRepository.findBuildingsByAccount(account);
        return buildingsByAccount.stream().map((BuildingInformationDto::new)).collect(Collectors.toList());


    }


    public BuildingDto reInforcedConcrete(BuildingDto buildingDto) {

        if (buildingDto.getFloorNumber() == 1 || buildingDto.getFloorNumber() == 2) {

            switch (buildingDto.getZone()) {
                case "1":

                    buildingDto.setBuildingPoint(53);

                    break;
                case "2":

                    buildingDto.setBuildingPoint(88);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(93);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(110);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(185);
                    break;
            }

        } else if (buildingDto.getFloorNumber() == 3) {
            switch (buildingDto.getZone()) {

                case "1":
                    buildingDto.setBuildingPoint(48);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(82);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(85);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(100);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(172);
                    break;

            }

        } else if (buildingDto.getFloorNumber() == 4) {
            switch (buildingDto.getZone()) {
                case "1":
                    buildingDto.setBuildingPoint(40);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(68);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(72);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(88);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(148);
                    break;
            }
        } else if (buildingDto.getFloorNumber() == 5) {
            switch (buildingDto.getZone()) {
                case "1":
                    buildingDto.setBuildingPoint(35);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(57);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(60);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(74);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(124);
                    break;
            }
        } else if (buildingDto.getFloorNumber() == 6 || buildingDto.getFloorNumber() == 7) {
            switch (buildingDto.getZone()) {
                case "1":
                    buildingDto.setBuildingPoint(32);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(54);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(57);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(66);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(111);
                    break;
            }
        }

        return buildingDto;

    }


}
