package com.camping101.beta.web.domain.camp.service;

import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.BAN;
import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.PERMISSION;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.ONLINE;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.PHONE;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.FRIDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.THURSDAY;
import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.TUESDAY;
import static com.camping101.beta.db.entity.camp.enums.Facility.SHOWER;
import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static com.camping101.beta.db.entity.camp.enums.Facility.WATERPROOF;
import static com.camping101.beta.db.entity.camp.enums.Season.AUTUMN;
import static com.camping101.beta.db.entity.camp.enums.Season.SPRING;
import static com.camping101.beta.db.entity.camp.enums.Season.SUMMER;
import static com.camping101.beta.db.entity.camp.enums.Season.WINTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampDetail;
import com.camping101.beta.db.entity.camp.CampFacility;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.CampOpenDay;
import com.camping101.beta.db.entity.camp.CampOpenSeason;
import com.camping101.beta.db.entity.camp.CampRelation;
import com.camping101.beta.db.entity.member.Member;
import com.camping101.beta.global.exception.camp.CannotFindCampException;
import com.camping101.beta.web.domain.admin.campauth.repository.CampAuthRepository;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import com.camping101.beta.web.domain.camp.model.dto.CampDetailDto;
import com.camping101.beta.web.domain.camp.model.dto.CampFacilityDto;
import com.camping101.beta.web.domain.camp.model.dto.CampLocationDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenDayDto;
import com.camping101.beta.web.domain.camp.model.dto.CampOpenSeasonDto;
import com.camping101.beta.web.domain.camp.model.rq.CreateCampRq;
import com.camping101.beta.web.domain.camp.model.rq.ModifyCampRq;
import com.camping101.beta.web.domain.camp.repository.CampRepository;
import com.camping101.beta.web.domain.member.repository.MemberRepository;
import com.camping101.beta.web.domain.member.service.FindMemberService;
import com.camping101.beta.web.domain.site.service.FindSiteService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockCampServiceTest {

    @InjectMocks
    private CampService campService;

    @Mock
    private CampRepository campRepository;

    @Mock
    private FindMemberService findMemberService;

    @Mock
    private FindSiteService findSiteService;

    @Mock
    private FindCampService findCampService;

    @Mock
    private CampAuthRepository campAuthRepository;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("rq로 캠핑장을 만들어 저장한다.")
    void saveCampTest() {
        // given
        Member mockMember = Member.createMockMember();
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
            mockMember.getMemberId(), "수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION",
            new AttachFileDto("fileUid", "fileName", "filePath", 10),
            new CampLocationDto("강", "수원시", "영통구", "1234", "5678"),
            new CampDetailDto("망치, 가위", "www.camping101.com"),
            campOpenSeasonDtoList, campOpenDayDtoList, campFacilityDtoList
        );

        // when

        //stub1
        when(findMemberService.findMemberOrElseThrow(mockMember.getMemberId())).thenReturn(
            mockMember);

        //stub2
        Camp camp = createMockCampAndRelation();

        when(campRepository.save(any())).thenReturn(camp);
        when(findCampService.findCampOrElseThrow(camp.getCampId())).thenReturn(camp);

        Long campId = campService.saveCamp(createCampRq);
        Camp findCamp = findCampService.findCampOrElseThrow(campId);

        // then
        assertThat(findCamp.getName()).isEqualTo("수원캠핑장");
        assertThat(findCamp.getIntro()).isEqualTo("수원캠핑장입니다!");
        assertThat(findCamp.getPhoneNumber()).isEqualTo("010-1234-5678");
        assertThat(findCamp.getBusinessNo()).isEqualTo("사업자번호");
        assertThat(findCamp.getCampReservationType()).isEqualTo(ONLINE);
        assertThat(findCamp.getAnimalCapabilityType()).isEqualTo(PERMISSION);
        assertThat(findCamp.getFirstImage()).isEqualTo(
            new AttachFile("fileUid", "fileName", "filePath", 10)
        );
        assertThat(findCamp.getCampLocation()).isEqualTo(
            new CampLocation("강", "수원시", "영통구", "1234", "5678")
        );
        assertThat(findCamp.getCampDetail()).isEqualTo(
            new CampDetail("망치, 가위", "www.camping101.com")
        );
        assertThat(findCamp.getCampOpenSeasons())
            .extracting("season")
            .containsExactlyInAnyOrder(AUTUMN, WINTER);

        assertThat(findCamp.getCampOpenDays())
            .extracting("dayOfTheWeek")
            .containsExactlyInAnyOrder(MONDAY, TUESDAY);

        assertThat(findCamp.getCampFacilities())
            .extracting("facility", "facilityNum")
            .containsExactlyInAnyOrder(tuple(TOILET, 3), tuple(SHOWER, 2), tuple(WATERPROOF, 1));
    }

    private static Camp createMockCampAndRelation() {
        Camp mockCamp = Camp.createMockCamp("수원캠핑장", "수원캠핑장입니다!", "010-1234-5678",
            "사업자번호", "ONLINE", "PERMISSION");

        AttachFile mockAttachFile = AttachFile.create("fileUid", "fileName", "filePath", 10);
        CampLocation mockCampLocation = CampLocation.create("강", "수원시", "영통구", "1234", "5678");
        CampDetail mockCampDetail = CampDetail.create("망치, 가위", "www.camping101.com");

        List<CampOpenSeason> mockCampOpenSeasonList = Arrays.asList(
            CampOpenSeason.create("AUTUMN"),
            CampOpenSeason.create("WINTER")
        );
        List<CampOpenDay> mockCampOpenDayList = Arrays.asList(
            CampOpenDay.create("MONDAY"),
            CampOpenDay.create("TUESDAY")
        );
        List<CampFacility> mockCampFacilityList = Arrays.asList(
            CampFacility.create("TOILET", 3),
            CampFacility.create("SHOWER", 2),
            CampFacility.create("WATERPROOF", 1)
        );

        return CampRelation.setRelation(
            mockCamp, mockAttachFile, mockCampLocation, mockCampDetail,
            mockCampOpenSeasonList, mockCampOpenDayList, mockCampFacilityList
        );
    }

    @Test
    @DisplayName("rq로 기존의 Camp엔티티를 수정한다.")
    void modifyCampTest() {
        // given
        Camp mockCamp = createMockCampAndRelation();

        List<CampOpenSeasonDto> campOpenSeasonDtoList = Arrays.asList(
            new CampOpenSeasonDto("SPRING"),
            new CampOpenSeasonDto("SUMMER")
        );
        List<CampOpenDayDto> campOpenDayDtoList = Arrays.asList(
            new CampOpenDayDto("THURSDAY"),
            new CampOpenDayDto("FRIDAY")
        );
        List<CampFacilityDto> campFacilityDtoList = Arrays.asList(
            new CampFacilityDto("TOILET", 2),
            new CampFacilityDto("WATERPROOF", 4)
        );

        ModifyCampRq modifyCampRq = new ModifyCampRq(
            1L, 1L, "서울캠핑장", "서울캠핑장입니다!", "010-5678-1234",
            "사업자번호2", "PHONE", "BAN",
            new AttachFileDto("fileUid2", "fileName2", "filePath2", 20),
            new CampLocationDto("산", "서울시", "강남구", "1357", "5678"),
            new CampDetailDto("망치, 가위", "www.camping1012.com"),
            campOpenSeasonDtoList, campOpenDayDtoList, campFacilityDtoList
        );

        // when

        // stub 1
        when(findCampService.findCampOrElseThrow(modifyCampRq.getCampId())).thenReturn(mockCamp);

        campService.modifyCamp(modifyCampRq);

        Camp camp = findCampService.findCampOrElseThrow(modifyCampRq.getCampId());

        // then
        assertThat(camp.getName()).isEqualTo("서울캠핑장");
        assertThat(camp.getIntro()).isEqualTo("서울캠핑장입니다!");
        assertThat(camp.getPhoneNumber()).isEqualTo("010-5678-1234");
        assertThat(camp.getBusinessNo()).isEqualTo("사업자번호2");
        assertThat(camp.getCampReservationType()).isEqualTo(PHONE);
        assertThat(camp.getAnimalCapabilityType()).isEqualTo(BAN);
        assertThat(camp.getFirstImage()).isEqualTo(
            new AttachFile("fileUid2", "fileName2", "filePath2", 20)
        );
        assertThat(camp.getCampLocation()).isEqualTo(
            new CampLocation("산", "서울시", "강남구", "1357", "5678")
        );
        assertThat(camp.getCampDetail()).isEqualTo(
            new CampDetail("망치, 가위", "www.camping1012.com")
        );
        assertThat(camp.getCampOpenSeasons())
            .extracting("season")
            .containsExactlyInAnyOrder(SPRING, SUMMER);

        assertThat(camp.getCampOpenDays())
            .extracting("dayOfTheWeek")
            .containsExactlyInAnyOrder(THURSDAY, FRIDAY);

        assertThat(camp.getCampFacilities())
            .extracting("facility", "facilityNum")
            .containsExactlyInAnyOrder(tuple(TOILET, 2), tuple(WATERPROOF, 4));
    }

    /**
     * site test 를 먼저 만들어야 가능하다.
     */
//    @Test
//    @DisplayName("캠핑장의 사이트에 예약이 없으면 캠핑장을 삭제할 수 있습니다.")
//    void removeCamp() {
//        // given
//        Camp mockCamp = createMockCampAndRelation();
//        Long mockCampId = mockCamp.getCampId();
//
//        // when
//
//        //stub1
//        when(findCampService.findCampOrElseThrow(mockCampId)).thenReturn(mockCamp);
//
//        campService.removeCamp(mockCampId);
//
//        //stub2
//        when(findCampService.findCampOrElseThrow(mockCampId)).thenReturn(new CannotFindCampException());
//
//        // then
//        assertThatThrownBy(() -> findCampService.findCampOrElseThrow(mockCampId))
//            .isInstanceOf(CannotFindCampException.class);
//    }

}