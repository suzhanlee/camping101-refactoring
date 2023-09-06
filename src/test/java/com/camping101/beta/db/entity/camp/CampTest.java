package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.Camp.createUnAuthorizedCamp;
import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.BAN;
import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.PERMISSION;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.ONLINE;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.PHONE;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.SUNDAY;
import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static com.camping101.beta.db.entity.camp.enums.Facility.WATERPROOF;
import static com.camping101.beta.db.entity.camp.enums.Season.SUMMER;
import static com.camping101.beta.db.entity.camp.enums.Season.WINTER;
import static com.camping101.beta.db.entity.campauth.CampAuthStatus.UNAUTHORIZED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.campauth.CampAuth;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampTest {

    @Test
    @DisplayName("관리자에게 허가받지 않은 캠핑장을 만든다.")
    void createUnAuthorizedCampTest() {
        // given // when
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        // then
        assertThat(camp.getName()).isEqualTo("수원캠핑장");
        assertThat(camp.getIntro()).isEqualTo("수원캠핑장입니다!");
        assertThat(camp.getPhoneNumber()).isEqualTo("010-1234-5678");
        assertThat(camp.getBusinessNo()).isEqualTo("사업자번호");
        assertThat(camp.getCampReservationType()).isEqualTo(ONLINE);
        assertThat(camp.getAnimalCapabilityType()).isEqualTo(PERMISSION);
    }

    @Test
    @DisplayName("캠핑장의 세부정보들을 수정할 수 있습니다.")
    void modifyCampTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        // when
        camp.modifyCamp("서울캠핑장", "서울캠핑장입니다!", "010-5678-1234",
            "사업자번호2", "PHONE", "BAN");

        // then
        assertThat(camp.getName()).isEqualTo("서울캠핑장");
        assertThat(camp.getIntro()).isEqualTo("서울캠핑장입니다!");
        assertThat(camp.getPhoneNumber()).isEqualTo("010-5678-1234");
        assertThat(camp.getBusinessNo()).isEqualTo("사업자번호2");
        assertThat(camp.getCampReservationType()).isEqualTo(PHONE);
        assertThat(camp.getAnimalCapabilityType()).isEqualTo(BAN);
    }

    @Test
    @DisplayName("캠핑장에 메인 사진을 추가할 수 있습니다.")
    void addFirstImageTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");
        AttachFile firstImage = AttachFile.create("fileUid", "fileName", "filePath", 10);

        // when
        camp.addFirstImage(firstImage);

        // then
        assertThat(camp.getFirstImage()).isEqualTo(firstImage);
    }

    @Test
    @DisplayName("캠핑장에 campLocation을 추가할 수 있습니다.")
    void addCampLocationTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");
        CampLocation campLocation = CampLocation.create("강", "수원시", "영통구", "1234", "5678");

        // when
        camp.addCampLocation(campLocation);

        // then
        assertThat(camp.getCampLocation()).isEqualTo(campLocation);
        assertThat(campLocation.getCamp()).isEqualTo(camp);
    }

    @Test
    @DisplayName("캠핑장에 campDetail을 추가할 수 있습니다.")
    void addCampDetailTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");
        CampDetail campDetail = CampDetail.create("망치, 가위", "www.camping101.com");

        // when
        camp.addCampDetail(campDetail);

        // then
        assertThat(camp.getCampDetail()).isEqualTo(campDetail);
        assertThat(campDetail.getCamp()).isEqualTo(camp);
    }

    @Test
    @DisplayName("캠핑장을 여는 계절을 선택할 수 있습니다.")
    void addCampOpenSeasonListTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");
        CampOpenSeason summer = CampOpenSeason.create("SUMMER");
        CampOpenSeason winter = CampOpenSeason.create("WINTER");

        // when
        camp.addCampOpenSeasonList(List.of(summer, winter));

        // then
        assertThat(camp.getCampOpenSeasons())
            .extracting("season")
            .containsExactlyInAnyOrder(
                SUMMER, WINTER
            );
        assertThat(summer.getCamp()).isEqualTo(camp);
        assertThat(winter.getCamp()).isEqualTo(camp);
    }

    @Test
    @DisplayName("캠핑장을 여는 요일을 정할 수 있습니다.")
    void addCampOpenDayListTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");
        CampOpenDay monday = CampOpenDay.create("MONDAY");
        CampOpenDay sunday = CampOpenDay.create("SUNDAY");

        // when
        camp.addCampOpenDayList(List.of(monday, sunday));

        // then
        assertThat(camp.getCampOpenDays())
            .extracting("dayOfTheWeek")
            .containsExactlyInAnyOrder(
                MONDAY, SUNDAY
            );
        assertThat(monday.getCamp()).isEqualTo(camp);
        assertThat(sunday.getCamp()).isEqualTo(camp);
    }

    @Test
    @DisplayName("캠핑장에서 제공하는 편의시설들을 추가할 수 있습니다.")
    void addCampFacilityListTest() {
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        CampFacility toilet = CampFacility.create("TOILET", 3);
        CampFacility waterproof = CampFacility.create("WATERPROOF", 2);

        // when
        camp.addCampFacilityList(List.of(toilet, waterproof));

        // then
        assertThat(camp.getCampFacilities())
            .extracting("facility", "facilityNum")
            .containsExactlyInAnyOrder(
                tuple(TOILET, 3), tuple(WATERPROOF, 2)
            );
        assertThat(toilet.getCamp()).isEqualTo(camp);
        assertThat(waterproof.getCamp()).isEqualTo(camp);
    }

    @Test
    @DisplayName("관리자에게 캠핑장 허가 요청을 보낼 수 있다.")
    void requestAuthorizationTest(){
        // given
        Camp camp = createUnAuthorizedCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        // when
        CampAuth campAuth = camp.requestAuthorization();

        // then
        assertThat(campAuth.getCamp()).isEqualTo(camp);
        assertThat(campAuth.getCampAuthStatus()).isEqualTo(UNAUTHORIZED);
    }
}