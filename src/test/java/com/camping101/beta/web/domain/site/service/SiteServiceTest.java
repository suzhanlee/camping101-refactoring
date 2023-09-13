package com.camping101.beta.web.domain.site.service;

import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.ANIMAL;
import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.STAY_OVER;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.BASIC;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.PARKING;
import static com.camping101.beta.db.entity.site.enums.SiteOpenType.CLOSED;
import static com.camping101.beta.db.entity.site.enums.SiteType.GLAMP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
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
import com.camping101.beta.db.entity.site.Site;
import com.camping101.beta.db.entity.site.SiteDetail;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import com.camping101.beta.web.domain.camp.service.FindCampService;
import com.camping101.beta.web.domain.site.model.CreateSiteRq;
import com.camping101.beta.web.domain.site.model.dto.SiteCapabilityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteCapacityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteDetailDto;
import com.camping101.beta.web.domain.site.model.dto.SiteFileDto;
import com.camping101.beta.web.domain.site.repository.SiteRepository;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SiteServiceTest {

    @InjectMocks
    private SiteService siteService;

    @Mock
    private SiteRepository siteRepository;

    @Mock
    private FindSiteService findSiteService;

    @Mock
    private FindCampService findCampService;

    @Test
    @DisplayName("캠핑장에 사이트를 생성할 수 있습니다.")
    void saveSiteTest() {
        // given
        Camp mockCamp = createMockCampAndRelation();

        SiteDetailDto siteDetailDto = new SiteDetailDto(3, "사이트 정책");

        List<SiteCapabilityDto> siteCapabilityDtoList = Arrays.asList(
            new SiteCapabilityDto("ANIMAL"),
            new SiteCapabilityDto("STAY_OVER")
        );

        List<SiteCapacityDto> siteCapacityDtoList = Arrays.asList(
            new SiteCapacityDto("BASIC"),
            new SiteCapacityDto("PARKING")
        );

        List<SiteFileDto> siteFileList = Arrays.asList(
            new SiteFileDto(
                new AttachFileDto("fileUid1", "main", "filePath1", 10),
                "mainImage"),
            new SiteFileDto(
                new AttachFileDto("fileUid2", "sub", "filePath2", 20),
                "subImage")
        );

        CreateSiteRq createSiteRq = new CreateSiteRq(
            mockCamp.getCampId(), "글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED", 15000,
            LocalTime.of(9, 0), LocalTime.of(12, 0), 1,
            siteDetailDto, siteCapacityDtoList, siteCapabilityDtoList, siteFileList
        );

        // when
//        when(siteRepository.save(any())).thenReturn(site);
        when(findCampService.findCampOrElseThrow(any())).thenReturn(mockCamp);

        Long siteId = siteService.saveSite(createSiteRq);

        //stub
//        when(findSiteService.findSiteOrElseThrow(siteId)).thenReturn(site);
        Site site = findSiteService.findSiteOrElseThrow(siteId);

        // then
        assertThat(site.getCamp().getCampId()).isEqualTo(mockCamp.getCampId());

        assertThat(site.getName()).isEqualTo("글램핑장");
        assertThat(site.getIntroduction()).isEqualTo("수원캠핑장의 글램핑장입니다!");
        assertThat(site.getPrice()).isEqualTo(15000);
        assertThat(site.getSiteType()).isEqualTo(GLAMP);
        assertThat(site.getSiteOpenType()).isEqualTo(CLOSED);
        assertThat(site.getCheckIn()).isEqualTo(LocalTime.of(9, 0));
        assertThat(site.getCheckOut()).isEqualTo(LocalTime.of(12, 0));
        assertThat(site.getLeastScheduling()).isEqualTo(1);

        assertThat(site.getSiteDetail()).isEqualTo(new SiteDetail(3, "사이트 정책"));

        assertThat(site.getSiteCapacities())
            .extracting("siteCapacityType")
            .containsExactlyInAnyOrder(BASIC, PARKING);

        assertThat(site.getSiteCapabilities())
            .extracting("siteCapabilityType")
            .containsExactlyInAnyOrder(ANIMAL, STAY_OVER);

        assertThat(site.getSiteFiles())
            .extracting("attachFile")
            .extracting("fileUid", "fileName", "filePath", "fileSize")
            .containsExactlyInAnyOrder(
                tuple("fileUid1", "main", "filePath1", 10),
                tuple("fileUid2", "sub", "filePath2", 20)
            );

        assertThat(site.getSiteFiles())
            .extracting("name")
            .containsExactlyInAnyOrder("mainImage", "subImage");
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