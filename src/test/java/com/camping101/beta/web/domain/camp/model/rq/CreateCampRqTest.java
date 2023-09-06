package com.camping101.beta.web.domain.camp.model.rq;

import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.PERMISSION;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.ONLINE;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.TUESDAY;
import static com.camping101.beta.db.entity.camp.enums.Facility.SHOWER;
import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static com.camping101.beta.db.entity.camp.enums.Facility.WATERPROOF;
import static com.camping101.beta.db.entity.camp.enums.Season.AUTUMN;
import static com.camping101.beta.db.entity.camp.enums.Season.WINTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampDetail;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import com.camping101.beta.web.domain.camp.model.dto.CampDetailDto;
import com.camping101.beta.web.domain.camp.model.dto.CampFacilityDto;
import com.camping101.beta.web.domain.camp.model.dto.CampLocationDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenDayDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenSeasonDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateCampRqTest {

    @Test
    @DisplayName("rq로 Camp를 만든다.")
    void toEntityTest() {
        // given
        List<CampOpenSeasonDto> campOpenSeasonDtoList = Arrays.asList(
            new CampOpenSeasonDto("AUTUMN"),
            new CampOpenSeasonDto("WINTER")
        );
        List<CampOpenDayDto> campOpenDayDtoList = Arrays.asList(
            new CampOpenDayDto("MONDAY"),
            new CampOpenDayDto("TUESDAY")
        );
        List<CampFacilityDto> campFacilityDtoList = Arrays.asList(
            new CampFacilityDto("TOILET", 3),
            new CampFacilityDto("SHOWER", 2),
            new CampFacilityDto("WATERPROOF", 1)
        );

        CreateCampRq createCampRq = new CreateCampRq(
            1L, "수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION",
            new AttachFileDto("fileUid", "fileName", "filePath", 10),
            new CampLocationDto("강", "수원시", "영통구", "1234", "5678"),
            new CampDetailDto("망치, 가위", "www.camping101.com"),
            campOpenSeasonDtoList, campOpenDayDtoList, campFacilityDtoList
        );

        // when
        Camp camp = createCampRq.toEntity();

        // then
        assertThat(camp.getName()).isEqualTo("수원캠핑장");
        assertThat(camp.getIntro()).isEqualTo("수원캠핑장입니다!");
        assertThat(camp.getPhoneNumber()).isEqualTo("010-1234-5678");
        assertThat(camp.getBusinessNo()).isEqualTo("사업자번호");
        assertThat(camp.getCampReservationType()).isEqualTo(ONLINE);
        assertThat(camp.getAnimalCapabilityType()).isEqualTo(PERMISSION);
        assertThat(camp.getFirstImage()).isEqualTo(
            new AttachFile("fileUid", "fileName", "filePath", 10)
        );
        assertThat(camp.getCampLocation()).isEqualTo(
            new CampLocation("강", "수원시", "영통구", "1234", "5678")
        );
        assertThat(camp.getCampDetail()).isEqualTo(
            new CampDetail("망치, 가위", "www.camping101.com")
        );
        assertThat(camp.getCampOpenSeasons())
            .extracting("season")
            .containsExactlyInAnyOrder(AUTUMN, WINTER);

        assertThat(camp.getCampOpenDays())
            .extracting("dayOfTheWeek")
            .containsExactlyInAnyOrder(MONDAY, TUESDAY);

        assertThat(camp.getCampFacilities())
            .extracting("facility", "facilityNum")
            .containsExactlyInAnyOrder(tuple(TOILET, 3), tuple(SHOWER, 2), tuple(WATERPROOF, 1));
    }
}