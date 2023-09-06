package com.camping101.beta.web.domain.camp.model.dto;

import com.camping101.beta.db.entity.camp.CampFacility;
import com.camping101.beta.db.entity.camp.enums.Facility;
import lombok.Getter;

@Getter
public class CampFacilityDto {

    private String facility;

    private Integer facilityNum;

    public CampFacilityDto(String facility, int facilityNum) {
        this.facility = facility;
        this.facilityNum = facilityNum;
    }

    public CampFacility toEntity() {
        return CampFacility.create(facility, facilityNum);
    }
}
