package com.sammy.entity.business;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Tour_Package")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourPackage {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
