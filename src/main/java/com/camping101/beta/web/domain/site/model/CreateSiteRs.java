package com.camping101.beta.web.domain.site.model;

import com.camping101.beta.db.entity.site.Site;
import com.camping101.beta.db.entity.site.SiteCapacity;
import com.camping101.beta.db.entity.site.enums.SiteType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateSiteRs {

    private Long siteId;
    private Long campId;

    private String name;
    private String rpImage; //대표 이미지
    private String introduction;
    private SiteType type;
    private boolean openYn;
//    private SiteYn siteYn;


    private LocalDate checkIn; // 체크 인 시간
    private LocalDate checkOut;// 체크 아웃 시간
    private int leastScheduling; // 최소 일정


    private SiteCapacity siteCapacity;
    private String mapImage;
    private String policy;
    private int price;
    private LocalDate refundableDate;

    public static CreateSiteRs createSiteRs(Site site) {

        return CreateSiteRs.builder()
            .siteId(site.getSiteId())
            .campId(site.getCamp().getCampId())
            .name(site.getName())
//            .rpImage(site.getRpImage())
//            .introduction(site.getIntroduction())
//            .type(site.getSiteType())
//            .openYn(site.isOpenYn())
//            .siteYn(site.getSiteYn())
//            .checkIn(site.getCheckIn())
//            .checkOut(site.getCheckOut())
//            .leastScheduling(site.getLeastScheduling())
//            .siteCapacity(site.getSiteCapacity())
//            .mapImage(site.getMapImage())
//            .policy(site.getPolicy())
//            .price(site.getPrice())
//            .refundableDate(site.getRefundableDate())
            .build();

    }


}