package com.camping101.beta.web.domain.camp.model.rs;

import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.enums.CampManageStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindCampDetailsAdminRs {

    private Long campId;
    private String campName;
    private String intro;
    private CampManageStatus campManageStatus;
    private CampLocation campLocation;

    private String tel;
    private String oneLineReserveYn;

    private String openSeason;
    private LocalDate openDateOfWeek;
//    private FacilityCnt facilityCnt;

    private String facility;
    private String leisure;
    private String animalCapable;
    private String equipmentTools;
    private String firstImage;
    private String homepage;
    private String businessNo; // 사업자 번호


    public static FindCampDetailsAdminRs createCampDetailsAdminRs(Camp camp) {

        return FindCampDetailsAdminRs.builder()
            .campId(camp.getCampId())
            .campName(camp.getName())
            .intro(camp.getIntro())
            .campManageStatus(camp.getCampManageStatus())
            .campLocation(camp.getCampLocation())
//            .tel(camp.getTel())
//            .oneLineReserveYn(camp.getOneLineReserveYn())
//            .openSeason(camp.getOpenSeason())
//            .openDateOfWeek(camp.getOpenDateOfWeek())
//            .facilityCnt(camp.getFacilityCnt())
//            .facility(camp.getFacility())
//            .leisure(camp.getLeisure())
//            .animalCapable(camp.getAnimalCapable())
//            .equipmentTools(camp.getEquipmentTools())
//            .firstImage(camp.getFirstImage())
//            .homepage(camp.getHomepage())
            .businessNo(camp.getBusinessNo())
            .build();

    }

}
