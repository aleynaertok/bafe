package com.bafe.model;



import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Building")
public class Building extends BaseEntity{

    private String name;
    @Column(name = "floor_Number")
    private int floorNumber;
    private String status;

    @Column(name = "building_point")
    private int buildingPoint;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;






}
