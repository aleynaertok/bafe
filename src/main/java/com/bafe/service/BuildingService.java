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


    public BuildingDto saveBuild(Long id, BuildingDto buildingDto) {
        Building building = new Building();

        Account account = accountService.getAccountById(id);

        calculateZone(buildingDto);

        irregularities(buildingDto);


        switch (buildingDto.getBuildingType()){
            case "reinforced":

                reInforcedConcrete(buildingDto);
                break;

            case "timberwork":

                    timberWork(buildingDto);
                break;
            case "steelstructure":

                steelStructure(buildingDto);
            break;

            case "masonry":

                masonry(buildingDto);
            break;

            default:
                throw new IsEmptyException("Unexpected Value" + buildingDto.getBuildingType());



        }


        reInforcedConcrete(buildingDto);


        building.setFloorNumber(buildingDto.getFloorNumber());
        building.setName(buildingDto.getName());
        building.setStatus(buildingDto.getStatus());
        building.setAccount(account);

        buildingRepository.save(building);

        return buildingDto;


    }

    public BuildingDto irregularities(BuildingDto buildingDto) {


        buildingDto.setVerticalNumber((int) buildingDto.getVerticalIrregularities().stream().filter(verticalIrregularit -> verticalIrregularit.equals(true)).count());

        buildingDto.setHorizontalNumber((int) buildingDto.getHorizontalIrregularities().stream().filter(horizontalIrregularit -> horizontalIrregularit.equals(true)).count());


        return buildingDto;
    }

    public BuildingDto calculateZone(BuildingDto buildingDto) {

        if (buildingDto.getAcceleration() < 0.250) {
            buildingDto.setZone("1");

        } else if (0.250 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 0.500) {

            buildingDto.setZone("2");

        } else if (0.500 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 1000) {
            buildingDto.setZone("3");
        } else if (1100 <= buildingDto.getAcceleration() && buildingDto.getAcceleration() < 1500) {
            buildingDto.setZone("4");
        } else {
            buildingDto.setZone("5");
        }

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

        if(buildingDto.getYears() < 2007){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 28);
        }
        else if(buildingDto.getYears() > 2018){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 84);
        }

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


    public BuildingDto timberWork(BuildingDto buildingDto) { //Ahşap

        if (buildingDto.getFloorNumber() == 1 || buildingDto.getFloorNumber() == 2) {

            switch (buildingDto.getZone()) {
                case "1":

                    buildingDto.setBuildingPoint(84);
                    break;
                case "2":

                    buildingDto.setBuildingPoint(159);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(181);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(225);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(274);
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
                    buildingDto.setBuildingPoint(75);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(148);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(164);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(204);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(255);
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
        }

        if (buildingDto.getGround().equals("ZA") || buildingDto.getGround().equals("ZB")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 4);
        } else if (buildingDto.getGround().equals("ZE")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 8);
        }

        buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + (buildingDto.getVerticalNumber() * -48) + (buildingDto.getHorizontalNumber() * -44));

        if(buildingDto.getYears() < 2007){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 44);
        }
        else if(buildingDto.getYears() > 2018){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 64);
        }

        if (buildingDto.getBuildingPoint() < 44) {
            buildingDto.setStatus("Risk durumu yüksek!");
        } else if (buildingDto.getBuildingPoint() > 44 && buildingDto.getBuildingPoint() < 103) {
            buildingDto.setStatus("Risk durumu orta düzeyde");
        } else if (buildingDto.getBuildingPoint() > 103 && buildingDto.getBuildingPoint() < 147) {
            buildingDto.setStatus("Risk durumu düşük");
        } else {
            buildingDto.setStatus("Risk durumu bulunmamakta bina güvenli.");
        }

        return buildingDto;


    }


    public BuildingDto steelStructure(BuildingDto buildingDto) { // çelik
        if (buildingDto.getFloorNumber() == 1 || buildingDto.getFloorNumber() == 2) {

            switch (buildingDto.getZone()) {
                case "1":

                    buildingDto.setBuildingPoint(66);
                    break;
                case "2":

                    buildingDto.setBuildingPoint(93);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(102);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(119);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(168);
                    break;


                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }

            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 5);
            }

            if (buildingDto.getZone().equals("ZE")) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 8);
            }


        } else if (buildingDto.getFloorNumber() == 3) {
            switch (buildingDto.getZone()) {

                case "1":
                    buildingDto.setBuildingPoint(60);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(87);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(93);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(108);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(156);
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

            if (buildingDto.getZone().equals("ZE")) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 8);
            }


        } else if (buildingDto.getFloorNumber() == 4) {
            switch (buildingDto.getZone()) {


                case "1":
                    buildingDto.setBuildingPoint(50);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(72);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(79);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(95);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(134);
                    break;

                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }

            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 10);
            }

            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 24);
            }
        } else if (buildingDto.getFloorNumber() == 5) {
            switch (buildingDto.getZone()) {
                case "1":
                    buildingDto.setBuildingPoint(43);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(60);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(66);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(80);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(113);
                    break;
                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }

            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 15);
            }

            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - -24);
            }

        } else if (buildingDto.getFloorNumber() == 6 || buildingDto.getFloorNumber() == 7) {
            switch (buildingDto.getZone()) {
                case "1":
                    buildingDto.setBuildingPoint(40);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(57);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(63);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(71);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(101);
                    break;
                default:
                    throw new IsEmptyException("Unexpected value: " + buildingDto.getZone());
            }

            if (buildingDto.getImpactEffect()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 3);
            }
            if (buildingDto.getHeavyHits()) {
                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 15);
            }

            if (buildingDto.getGround().equals("ZE")) {

                buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 24);
            }

        }

        if (buildingDto.getGround().equals("ZA") || buildingDto.getGround().equals("ZB")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 16);
        }

        buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + (buildingDto.getVerticalNumber() * -40) + (buildingDto.getHorizontalNumber() * -32));

        if(buildingDto.getYears() < 2007){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() - 24);
        }
        else if(buildingDto.getYears() > 2018){
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 56);
        }

        if (buildingDto.getBuildingPoint() < 20) {
            buildingDto.setStatus("Risk durumu yüksek!");
        } else if (buildingDto.getBuildingPoint() > 20 && buildingDto.getBuildingPoint() < 47) {
            buildingDto.setStatus("Risk durumu orta düzeyde");
        } else if (buildingDto.getBuildingPoint() > 47 && buildingDto.getBuildingPoint() < 67) {
            buildingDto.setStatus("Risk durumu düşük");
        } else {
            buildingDto.setStatus("Risk durumu bulunmamakta bina güvenli.");
        }





        return buildingDto;

    }

    public BuildingDto masonry(BuildingDto buildingDto) { //Yığma
        if (buildingDto.getFloorNumber() == 1 || buildingDto.getFloorNumber() == 2) {

            switch (buildingDto.getZone()) {
                case "1":

                    buildingDto.setBuildingPoint(40);
                    break;
                case "2":

                    buildingDto.setBuildingPoint(44);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(53);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(75);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(141);
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
                    buildingDto.setBuildingPoint(36);
                    break;
                case "2":
                    buildingDto.setBuildingPoint(41);
                    break;
                case "3":
                    buildingDto.setBuildingPoint(48);
                    break;
                case "4":
                    buildingDto.setBuildingPoint(68);
                    break;
                case "5":
                    buildingDto.setBuildingPoint(131);
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
        }

        if (buildingDto.getGround().equals("ZA") || buildingDto.getGround().equals("ZB")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + 12);
        } else if (buildingDto.getGround().equals("ZE")) {
            buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() -8);
        }

        buildingDto.setBuildingPoint(buildingDto.getBuildingPoint() + (buildingDto.getVerticalNumber() * -28) + (buildingDto.getHorizontalNumber() * -16));

        if (buildingDto.getBuildingPoint() < 8) {
            buildingDto.setStatus("Risk durumu yüksek!");
        } else if (buildingDto.getBuildingPoint() > 8 && buildingDto.getBuildingPoint() < 19) {
            buildingDto.setStatus("Risk durumu orta düzeyde");
        } else if (buildingDto.getBuildingPoint() > 19 && buildingDto.getBuildingPoint() < 27) {
            buildingDto.setStatus("Risk durumu düşük");
        } else {
            buildingDto.setStatus("Risk durumu bulunmamakta bina güvenli.");
        }

        return buildingDto;
    }


}


