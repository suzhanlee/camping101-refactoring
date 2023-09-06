package com.camping101.beta.web.domain.camp.model.rq;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampDetail;
import com.camping101.beta.db.entity.camp.CampFacility;
import com.camping101.beta.db.entity.camp.CampRelation;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.CampOpenDay;
import com.camping101.beta.db.entity.camp.CampOpenSeason;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import com.camping101.beta.web.domain.camp.model.dto.CampDetailDto;
import com.camping101.beta.web.domain.camp.model.dto.CampFacilityDto;
import com.camping101.beta.web.domain.camp.model.dto.CampLocationDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenDayDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenSeasonDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyCampRq {

    private Long campId;

    private Long memberId;

    private String name;

    private String intro;

    private String phoneNumber;

    private String businessNo;

    private String campReservationType;

    private String animalCapabilityType;

    private AttachFileDto attachFileDto;

    private CampLocationDto campLocationDto;

    private CampDetailDto campDetailDto;

    private List<CampOpenSeasonDto> campOpenSeasonDtoList;

    private List<CampOpenDayDto> campOpenDayDtoList;

    private List<CampFacilityDto> campFacilityDtoList;


    public void modify(Camp camp) {

        AttachFile attachFile = attachFileDto.toEntity();
        CampLocation campLocation = campLocationDto.toEntity();
        CampDetail campDetail = campDetailDto.toEntity();

        List<CampOpenSeason> campOpenSeasonList = campOpenSeasonDtoList.stream()
            .map(CampOpenSeasonDto::toEntity).collect(Collectors.toList());
        List<CampOpenDay> campOpenDayList = campOpenDayDtoList.stream()
            .map(CampOpenDayDto::toEntity)
            .collect(Collectors.toList());
        List<CampFacility> campFacilityList = campFacilityDtoList.stream()
            .map(CampFacilityDto::toEntity)
            .collect(Collectors.toList());

        camp.modifyCamp(
            name, intro, phoneNumber, businessNo,
            campReservationType, animalCapabilityType
        );

        CampRelation.setRelation(
            camp, attachFile, campLocation, campDetail,
            campOpenSeasonList, campOpenDayList, campFacilityList
        );
    }
}
