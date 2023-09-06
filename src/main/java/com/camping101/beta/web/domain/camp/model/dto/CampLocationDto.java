package com.camping101.beta.web.domain.camp.model.dto;

import com.camping101.beta.db.entity.camp.CampLocation;
import lombok.Getter;

@Getter
public class CampLocationDto {

    private String environment;

    private String addr1;

    private String addr2;

    private String latitude;

    private String longitude;

    public CampLocationDto(String environment, String addr1, String addr2, String latitude,
        String longitude) {
        this.environment = environment;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CampLocation toEntity() {
        return CampLocation.create(environment, addr1, addr2, latitude, longitude);
    }
}
