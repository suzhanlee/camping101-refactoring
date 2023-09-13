package com.camping101.beta.web.domain.site.model;

import com.camping101.beta.db.entity.site.Site;
import com.camping101.beta.db.entity.site.SiteCapability;
import com.camping101.beta.db.entity.site.SiteCapacity;
import com.camping101.beta.db.entity.site.SiteDetail;
import com.camping101.beta.db.entity.site.SiteFile;
import com.camping101.beta.db.entity.site.SiteRelation;
import com.camping101.beta.web.domain.site.model.dto.SiteCapabilityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteCapacityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteDetailDto;
import com.camping101.beta.web.domain.site.model.dto.SiteFileDto;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSiteRq {

    private Long campId;

    private String name;

    private String introduction;

    private String siteType;

    private String siteOpenType;

    private Integer price;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private Integer leastScheduling;

    private SiteDetailDto siteDetailDto;

    private List<SiteCapacityDto> siteCapacityDtoList;

    private List<SiteCapabilityDto> siteCapabilityDtoList;

    private List<SiteFileDto> siteFileDtoList;

    public Site toEntity() {
        Site site = Site.create(name, introduction, "GLAMP", "CLOSED", price, checkIn, checkOut,
            leastScheduling);

        SiteDetail siteDetail = siteDetailDto.toEntity();

        List<SiteCapacity> siteCapacityList = siteCapacityDtoList.stream()
            .map(SiteCapacityDto::toEntity).collect(Collectors.toList());
        List<SiteCapability> siteCapabilityList = siteCapabilityDtoList.stream()
            .map(SiteCapabilityDto::toEntity).collect(Collectors.toList());
        List<SiteFile> siteFileList = siteFileDtoList.stream()
            .map(SiteFileDto::toEntity).collect(Collectors.toList());

        return SiteRelation.setRelation(site, siteDetail,
            siteCapacityList, siteCapabilityList, siteFileList);
    }
}
