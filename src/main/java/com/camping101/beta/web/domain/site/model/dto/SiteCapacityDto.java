package com.camping101.beta.web.domain.site.model.dto;

import com.camping101.beta.db.entity.site.SiteCapacity;
import com.camping101.beta.db.entity.site.enums.SiteCapacityType;
import lombok.Getter;

@Getter
public class SiteCapacityDto {

    private String siteCapacityType;

    public SiteCapacityDto(String siteCapacityType) {
        this.siteCapacityType = siteCapacityType;
    }

    public SiteCapacity toEntity() {
        return SiteCapacity.create(siteCapacityType);
    }
}
