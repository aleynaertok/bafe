package com.bafe.repository;




import com.bafe.model.Account;
import com.bafe.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface BuildingRepository extends JpaRepository<Building, Long> {


    List<Building> findBuildingsByAccount(Account account);

    @Query("select b.status  from Building b " +
            "where b.account.id = :id and b.floorNumber = :floorNumber")
    List<Building> findBuildingsByIdAndFloorNumber(@Param("id") Long id,@Param("floorNumber") int floorNumber);
    //JPA QUERY Language


}
