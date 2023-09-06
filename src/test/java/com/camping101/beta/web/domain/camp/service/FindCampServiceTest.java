package com.camping101.beta.web.domain.camp.service;

import static com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType.PERMISSION;
import static com.camping101.beta.db.entity.camp.enums.CampReservationType.ONLINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camp.CampDetail;
import com.camping101.beta.db.entity.camp.CampFacility;
import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.CampOpenDay;
import com.camping101.beta.db.entity.camp.CampOpenSeason;
import com.camping101.beta.db.entity.camp.CampRelation;
import com.camping101.beta.web.domain.camp.repository.CampRepository;
import com.camping101.beta.web.domain.member.service.FindMemberService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindCampServiceTest {

    @InjectMocks
    private FindCampService findCampService;

    @Mock
    private CampRepository campRepository;

    @Mock
    private FindMemberService findMemberService;

    @Test
    @DisplayName("id로 캠핑장을 찾을 수 있습니다.")
    void findCampOrElseThrowTest() {
        // given
        Camp camp = createMockCampAndRelation();

        // when
        when(campRepository.findById(camp.getCampId())).thenReturn(Optional.of(camp));

        Camp findCamp = findCampService.findCampOrElseThrow(camp.getCampId());

        // then
        assertThat(camp.getName()).isEqualTo("수원캠핑장");
        assertThat(findCamp.getIntro()).isEqualTo("수원캠핑장입니다!");
        assertThat(findCamp.getPhoneNumber()).isEqualTo("010-1234-5678");
        assertThat(findCamp.getBusinessNo()).isEqualTo("사업자번호");
        assertThat(findCamp.getCampReservationType()).isEqualTo(ONLINE);
        assertThat(findCamp.getAnimalCapabilityType()).isEqualTo(PERMISSION);
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
}