package com.bafe.service;

import com.bafe.dto.BuildingDto;
import com.bafe.dto.BuildingInformationDto;
import com.bafe.exception.IsEmptyException;
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

    public BuildingDto irregularities(BuildingDto buildingDto) {


        buildingDto.setVerticalNumber((int) buildingDto.getVerticalIrregularities().stream().filter(verticalIrregularit -> verticalIrregularit.equals(true)).count());

        buildingDto.setHorizontalNumber((int) buildingDto.getHorizontalIrregularities().stream().filter(horizontalIrregularit -> horizontalIrregularit.equals(true)).count());


        return buildingDto;
    }

    public BuildingDto calculateZone(BuildingDto buildingDto) {

        if (buildingDto.getAcceleration() < 0.250) {
            buildingDto.setZone("1");

        } else if (0.250 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 0.500){

                        buildingDto.setZone("2");

        }

        else if(0.500 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 1000){
            buildingDto.setZone("3");
        }

        else if(1100 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 1500){
            buildingDto.setZone("4");
        }
        else{
            buildingDto.setZone("5");
        }

        return buildingDto;

    }


    public BuildingDto saveBuild(Long id, BuildingDto buildingDto) {
        Building building = new Building();

        Account account = accountService.getAccountById(id);

        calculateZone(buildingDto);

        irregularities(buildingDto);


        reInforcedConcrete(buildingDto);


        building.setFloorNumber(buildingDto.getFloorNumber());
        building.setName(buildingDto.getName());
        building.setStatus(buildingDto.getStatus());
        building.setAccount(account);

        buildingRepository.save(building);

        return buildingDto;


    }


    public List<BuildingInformationDto> getPersonalBuildings(Long id) {


        Account account = accountService.getAccountById(id);
        List<Building> buildingsByAccount = buildingRepository.findBuildingsByAccount(account);

        if (buildingsByAccount.isEmpty()) {
            throw new IsEmptyException("Bu kullanıcıya ait bina bulunamadı");
        }
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


                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }

            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 5);
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

                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }
            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 2);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 10);
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

                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }
            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 28);
            }
            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 10);
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
                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }
            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 28);
            }
            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 15);
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
                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }
            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 28);
            }
            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 15);
            }
        }

        if (buildingDto.getGround().equals("ZA") || buildingDto.getGround().equals("ZB")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 20);
        }

        buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + (buildingDto.getVerticalNumber() * -44) + (buildingDto.getHorizontalNumber() * -32));

        if (buildingDto.getBuildingPoint() < 12) {
            buildingDto.setStatus("Risk durumu yüksek!");
        } else if (buildingDto.getBuildingPoint() > 12 && buildingDto.getBuildingPoint() < 28) {
            buildingDto.setStatus("Risk durumu orta düzeyde");
        } else if (buildingDto.getBuildingPoint() > 28 && buildingDto.getBuildingPoint() < 40) {
            buildingDto.setStatus("Risk durumu düşük");
        } else {
            buildingDto.setStatus("Risk durumu bulunmamakta bina güvenli.");
        }

        return buildingDto;

    }


}
