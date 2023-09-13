package com.camping101.beta.web.domain.site.model.dto;

import com.camping101.beta.db.entity.site.SiteCapability;
import lombok.Getter;

@Getter
public class SiteCapabilityDto {

    private String siteCapabilityType;

    public SiteCapabilityDto(String siteCapabilityType) {
        this.siteCapabilityType = siteCapabilityType;
    }

    public SiteCapability toEntity() {
        return SiteCapability.create(siteCapabilityType);
    }
}
