package com.camping101.beta.db.entity.site;

import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.ANIMAL;
import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.STAY_OVER;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.BASIC;
import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.PARKING;
import static com.camping101.beta.db.entity.site.enums.SiteOpenType.CLOSED;
import static com.camping101.beta.db.entity.site.enums.SiteType.GLAMP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.reservation.Reservation;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiteTest {

    @Test
    @DisplayName("사이트를 만들 수 있습니다.")
    void createTest() {
        // given // when
        Site site = Site.create("글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED",
            15000, LocalTime.of(9, 0),
            LocalTime.of(12, 0), 1);

        // then
        assertThat(site.getName()).isEqualTo("글램핑장");
        assertThat(site.getIntroduction()).isEqualTo("수원캠핑장의 글램핑장입니다!");
        assertThat(site.getPrice()).isEqualTo(15000);
        assertThat(site.getSiteType()).isEqualTo(GLAMP);
        assertThat(site.getSiteOpenType()).isEqualTo(CLOSED);
        assertThat(site.getCheckIn()).isEqualTo(LocalTime.of(9, 0));
        assertThat(site.getCheckOut()).isEqualTo(LocalTime.of(12, 0));
        assertThat(site.getLeastScheduling()).isEqualTo(1);
    }

    @Test
    @DisplayName("사이트에 siteDetail을 siteDetail에 사이트를 추가할 수 있습니다.")
    void addSiteDetailTest() {
        // given
        Site site = Site.create("글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED",
            15000, LocalTime.of(9, 0),
            LocalTime.of(12, 0), 1);

        SiteDetail siteDetail = SiteDetail.create(3, "사이트 정책");

        // when
        site.addSiteDetail(siteDetail);

        // then
        assertThat(site.getSiteDetail()).isEqualTo(siteDetail);
        assertThat(siteDetail.getSite()).isEqualTo(site);
    }

    @Test
    @DisplayName("사이트에 siteCapacity를 siteCapacity에 사이트를 추가할 수 있습니다.")
    void addSiteCapacitiesTest() {
        // given
        Site site = Site.create("글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED",
            15000, LocalTime.of(9, 0),
            LocalTime.of(12, 0), 1);

        SiteCapacity basic = SiteCapacity.create("BASIC");
        SiteCapacity parking = SiteCapacity.create("PARKING");

        // when
        List<SiteCapacity> siteCapacityList = Arrays.asList(basic, parking);
        site.addSiteCapacities(siteCapacityList);

        // then
        assertThat(site.getSiteCapacities())
            .extracting("siteCapacityType")
            .containsExactlyInAnyOrder(BASIC, PARKING);

        assertThat(basic.getSite()).isEqualTo(site);
        assertThat(parking.getSite()).isEqualTo(site);
    }

    @Test
    @DisplayName("사이트에 SiteCapabilities를 SiteCapabilities에 사이트를 추가할 수 있습니다.")
    void addSiteCapabilitiesTest() {
        // given
        Site site = Site.create("글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED",
            15000, LocalTime.of(9, 0),
            LocalTime.of(12, 0), 1);

        SiteCapability animal = SiteCapability.create("ANIMAL");
        SiteCapability stayOver = SiteCapability.create("STAY_OVER");

        // when
        List<SiteCapability> siteCapabilityList = Arrays.asList(animal, stayOver);
        site.addSiteCapabilities(siteCapabilityList);

        // then
        assertThat(site.getSiteCapabilities())
            .extracting("siteCapabilityType")
            .containsExactlyInAnyOrder(ANIMAL, STAY_OVER);

        assertThat(animal.getSite()).isEqualTo(site);
        assertThat(stayOver.getSite()).isEqualTo(site);
    }

    @Test
    @DisplayName("사이트에 SiteFiles를 SiteFiles에 사이트를 추가할 수 있습니다.")
    void addSiteFilesTest() {
        // given
        Site site = Site.create("글램핑장", "수원캠핑장의 글램핑장입니다!", "GLAMP", "CLOSED",
            15000, LocalTime.of(9, 0),
            LocalTime.of(12, 0), 1);

        AttachFile mainAttachFile = AttachFile.create("fileUid1", "main", "filePath1", 10);
        AttachFile subAttachFile = AttachFile.create("fileUid2", "sub", "filePath2", 20);

        SiteFile mainImage = SiteFile.create(mainAttachFile, "mainImage");
        SiteFile subImage = SiteFile.create(subAttachFile, "subImage");

        // when
        List<SiteFile> siteFileList = Arrays.asList(mainImage, subImage);
        site.addSiteFiles(siteFileList);

        // then
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

        assertThat(mainImage.getSite()).isEqualTo(site);
        assertThat(subImage.getSite()).isEqualTo(site);
    }

    @Test
    @DisplayName("사이트 엔티티에 예약 엔티티를 저장할 수 있습니다.")
    void addReservationTest() {
        // given
        Site site = new Site();
        Reservation reservation = new Reservation();
        // when
        site.addReservation(reservation);
        // then
        assertThat(site.getReservationList().contains(reservation)).isTrue();
    }

//    @Test
//    @DisplayName("사이트의 개장 여부를 변경할 수 있습니다.")
//    void changeOpenYnTest() {
//        // given
//        Site closedSite = new Site();
//        Site openedSite = openedSite();
//        // when
//        closedSite.changeOpenYn();
//        openedSite.changeOpenYn();
//        // then
//        assertThat(closedSite.isOpenYn()).isTrue();
//        assertThat(openedSite.isOpenYn()).isFalse();
//    }

//    private static Site openedSite() {
//        Site openSite = new Site();
//        openSite.changeOpenYn();
//        return openSite;
//    }
}