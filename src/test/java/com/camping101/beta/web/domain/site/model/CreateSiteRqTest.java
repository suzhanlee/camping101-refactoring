package com.camping101.beta.web.domain.site.model;

import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.ANIMAL;
import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.STAY_OVER;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.BASIC;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.PARKING;
import static com.camping101.beta.db.entity.site.enums.SiteOpenType.CLOSED;
import static com.camping101.beta.db.entity.site.enums.SiteType.GLAMP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.camping101.beta.db.entity.site.Site;
import com.camping101.beta.db.entity.site.SiteDetail;
import com.camping101.beta.db.entity.site.SiteFile;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import com.camping101.beta.web.domain.site.model.dto.SiteCapabilityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteCapacityDto;
import com.camping101.beta.web.domain.site.model.dto.SiteDetailDto;
import com.camping101.beta.web.domain.site.model.dto.SiteFileDto;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateSiteRqTest {

    @Test
    @DisplayName("rq로 Site를 만들 수 있다.")
    void toEntityTest() {
        // given
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
            1L, "글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED", 15000,
            LocalTime.of(9, 0), LocalTime.of(12, 0), 1,
            siteDetailDto, siteCapacityDtoList, siteCapabilityDtoList, siteFileList
            );

        // when
        Site site = createSiteRq.toEntity();

        // then
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
}