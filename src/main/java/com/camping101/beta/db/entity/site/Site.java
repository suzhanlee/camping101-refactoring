package com.camping101.beta.db.entity.site;

import static com.camping101.beta.db.entity.site.enums.SiteType.GLAMP;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.camplog.CampLog;
import com.camping101.beta.db.entity.reservation.Reservation;
import com.camping101.beta.db.entity.site.enums.SiteOpenType;
import com.camping101.beta.db.entity.site.enums.SiteType;
import com.camping101.beta.web.domain.site.model.ModifySiteRq;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Site extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Long siteId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SiteType siteType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SiteOpenType siteOpenType;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalTime checkIn;

    @Column(nullable = false)
    private LocalTime checkOut;

    @Column(nullable = false)
    private Integer leastScheduling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    @OneToOne(mappedBy = "site")
    private SiteDetail siteDetail;

    @OneToMany(mappedBy = "site")
    private List<SiteCapacity> siteCapacities = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<SiteCapability> siteCapabilities = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<SiteFile> siteFiles = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<CampLog> campLogList = new ArrayList<>();

    public static Site create(String name, String introduction, String siteType,
        String siteOpenType, int price, LocalTime checkIn,
        LocalTime checkOut, int leastScheduling) {
        Site site = new Site();
        site.name = name;
        site.introduction = introduction;
        site.siteType = SiteType.valueOf(siteType);
        site.siteOpenType = SiteOpenType.valueOf(siteOpenType);
        site.price = price;
        site.checkIn = checkIn;
        site.checkOut = checkOut;
        site.leastScheduling = leastScheduling;
        return site;
    }

    public void addCamp(Camp camp) {
        this.camp = camp;
    }

    public void hasReservation() {
        for (Reservation reservation : reservationList) {
            reservation.isGreaterThanNow();
        }
    }

    public void addSiteDetail(SiteDetail siteDetail) {
        if (siteDetail.getSite() == null) {
            siteDetail.addSite(this);
        }
        this.siteDetail = siteDetail;
    }

    public void addSiteCapabilities(List<SiteCapability> siteCapabilityList) {
        siteCapabilityList.stream().filter(siteCapability -> siteCapability.getSite() == null)
            .forEach(siteCapability -> siteCapability.addSite(this));
        this.siteCapabilities = siteCapabilityList;
    }

    public void addSiteCapacities(List<SiteCapacity> siteCapacityList) {
        siteCapacityList.stream().filter(SiteCapacity -> SiteCapacity.getSite() == null)
            .forEach(SiteCapacity -> SiteCapacity.addSite(this));
        this.siteCapacities = siteCapacityList;
    }

    public void addSiteFiles(List<SiteFile> siteFileList) {
        siteFileList.stream().filter(SiteFile -> SiteFile.getSite() == null)
            .forEach(SiteFile -> SiteFile.addSite(this));
        this.siteFiles = siteFileList;
    }

    //=========== NOT YET =============

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }

    public Site updateSite(ModifySiteRq modifySiteRq) {

        this.name = modifySiteRq.getName();
//        this.rpImage = modifySiteRq.getRpImage(); //대표 이미지
//        this.introduction = modifySiteRq.getIntroduction();
//        this.siteType = modifySiteRq.getType();
//        this.openYn = modifySiteRq.isOpenYn();
//        this.siteYn = modifySiteRq.getSiteYn();
//        this.checkIn = modifySiteRq.getCheckIn();
//        this.checkOut = modifySiteRq.getCheckOut();
//        this.leastScheduling = modifySiteRq.getLeastScheduling();
//        this.siteCapacity = modifySiteRq.getSiteCapacity();
//        this.mapImage = modifySiteRq.getMapImage();
//        this.policy = modifySiteRq.getPolicy();
//        this.price = modifySiteRq.getPrice();
//        this.refundableDate = modifySiteRq.getRefundableDate();

        return this;
    }

//    public void changeOpenYn() {
//        this.openYn = !this.openYn;
//    }
}
