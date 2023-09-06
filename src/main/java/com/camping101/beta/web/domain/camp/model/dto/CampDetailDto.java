package com.camping101.beta.web.domain.camp.model.dto;

import com.camping101.beta.db.entity.camp.CampDetail;
import lombok.Getter;

@Getter
public class CampDetailDto {

    private String equipmentTools;

    private String homepage;

    public CampDetailDto(String equipmentTools, String homepage) {
        this.equipmentTools = equipmentTools;
        this.homepage = homepage;
    }

    public CampDetail toEntity() {
        return CampDetail.create(equipmentTools, homepage);
    }
}
