package com.camping101.beta.web.domain.camp.model.rs;

import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.enums.CampManageStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindCampListRs {

    private String campName;
    private Long campId;
    private String intro;
    private CampManageStatus campManageStatus;
    private CampLocation campLocation;
    private String openSeason;
    private String animalCapable;
    private String firstImage;
    private Long campLogCnt;

    public static FindCampListRs createCampListRs(Camp camp) {

        return FindCampListRs.builder()
            .campId(camp.getCampId())
            .campName(camp.getName())
            .intro(camp.getIntro())
            .campManageStatus(camp.getCampManageStatus())
            .campLocation(camp.getCampLocation())
//            .openSeason(camp.getOpenSeason())
//            .animalCapable(camp.getAnimalCapable())
//            .firstImage(camp.getFirstImage())
//            .campLogCnt(camp.getCampLogCnt())
            .build();
    }

    @QueryProjection
    public FindCampListRs(String campName, Long campId, String intro, CampManageStatus campManageStatus,
        CampLocation campLocation, String openSeason, String animalCapable, String firstImage,
        Long campLogCnt) {
        this.campName = campName;
        this.campId = campId;
        this.intro = intro;
        this.campManageStatus = campManageStatus;
        this.campLocation = campLocation;
        this.openSeason = openSeason;
        this.animalCapable = animalCapable;
        this.firstImage = firstImage;
        this.campLogCnt = campLogCnt;
    }
}
