package com.camping101.beta.web.domain.camp.model.rs;

import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampLocation;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateCampRs {

    private Long campId;
    private String campName;
    private String intro;
    private String manageStatus;
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


    public static CreateCampRs createCampRs(Camp camp) {

        return CreateCampRs.builder()
            .campId(camp.getCampId())
            .campName(camp.getName())
            .intro(camp.getIntro())
            .manageStatus(
                String.valueOf(camp.getCampManageStatus())) // => 캠핑장 생성이 완료되었습니다. 관리자가 요청을 확인합니다.
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
