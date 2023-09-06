package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.Camp.createUnAuthorizedCamp;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.SUNDAY;
import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static com.camping101.beta.db.entity.camp.enums.Facility.WATERPROOF;
import static com.camping101.beta.db.entity.camp.enums.Season.SUMMER;
import static com.camping101.beta.db.entity.camp.enums.Season.WINTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampRelationTest {

    @Test
    @DisplayName("캠핑장과 관계있는 연관관계들을 추가할 수 있다.")
    void setRelationTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        AttachFile attachFile = AttachFile.create("fileUid", "fileName", "filePath", 10);
        CampLocation campLocation = CampLocation.create("강", "수원시", "영통구", "1234", "5678");
        CampDetail campDetail = CampDetail.create("망치, 가위", "www.camping101.com");

        CampOpenSeason summer = CampOpenSeason.create("SUMMER");
        CampOpenSeason winter = CampOpenSeason.create("WINTER");
        List<CampOpenSeason> campOpenSeasonList = Arrays.asList(summer, winter);

        CampOpenDay monday = CampOpenDay.create("MONDAY");
        CampOpenDay sunday = CampOpenDay.create("SUNDAY");
        List<CampOpenDay> campOpenDayList = Arrays.asList(monday, sunday);

        CampFacility toilet = CampFacility.create("TOILET", 3);
        CampFacility waterproof = CampFacility.create("WATERPROOF", 2);
        List<CampFacility> campFacilityList = Arrays.asList(toilet, waterproof);

        // when
        CampRelation.setRelation(camp, attachFile, campLocation, campDetail,
            campOpenSeasonList, campOpenDayList, campFacilityList);

        // then
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
            .containsExactlyInAnyOrder(SUMMER, WINTER);

        assertThat(camp.getCampOpenDays())
            .extracting("dayOfTheWeek")
            .containsExactlyInAnyOrder(MONDAY, SUNDAY);

        assertThat(camp.getCampFacilities())
            .extracting("facility", "facilityNum")
            .containsExactlyInAnyOrder(tuple(TOILET, 3), tuple(WATERPROOF, 2));

        assertThat(campLocation.getCamp()).isEqualTo(camp);
        assertThat(campDetail.getCamp()).isEqualTo(camp);

        assertThat(summer.getCamp()).isEqualTo(camp);
        assertThat(winter.getCamp()).isEqualTo(camp);

        assertThat(monday.getCamp()).isEqualTo(camp);
        assertThat(sunday.getCamp()).isEqualTo(camp);

        assertThat(toilet.getCamp()).isEqualTo(camp);
        assertThat(waterproof.getCamp()).isEqualTo(camp);
    }
}