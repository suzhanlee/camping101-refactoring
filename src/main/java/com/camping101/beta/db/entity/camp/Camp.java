package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.ManageStatus.UNAUTHORIZED;
import static javax.persistence.EnumType.STRING;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.enums.AnimalCapabilityType;
import com.camping101.beta.db.entity.camp.enums.CampReservationType;
import com.camping101.beta.db.entity.campauth.CampAuth;
import com.camping101.beta.db.entity.member.Member;
import com.camping101.beta.db.entity.site.Site;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Camp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camp_id")
    private Long campId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String businessNo;

    @Column(nullable = false)
    private CampReservationType campReservationType;

    @Enumerated(STRING)
    @Column(nullable = false)
    private AnimalCapabilityType animalCapabilityType;

    @Enumerated(STRING)
    @Column(nullable = false)
    private ManageStatus manageStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attchFile_id")
    private AttachFile firstImage;

    @OneToOne(mappedBy = "camp")
    private CampLocation campLocation;

    @OneToOne(mappedBy = "camp")
    private CampDetail campDetail;

    @OneToMany(mappedBy = "camp")
    private List<CampOpenSeason> campOpenSeasons = new ArrayList<>();

    @OneToMany(mappedBy = "camp")
    private List<CampOpenDay> campOpenDays = new ArrayList<>();

    @OneToMany(mappedBy = "camp")
    private List<CampFacility> campFacilities = new ArrayList<>();

    @OneToMany(mappedBy = "camp", cascade = CascadeType.REMOVE)
    private List<Site> sites = new ArrayList<>();

    @OneToMany(mappedBy = "camp", cascade = CascadeType.REMOVE)
    private List<CampAuth> campAuthList = new ArrayList<>();

    public static Camp createUnAuthorizedCamp(String name, String intro, String phoneNumber, String businessNo,
        String campReservationType, String animalCapabilityType) {
        Camp camp = new Camp();
        camp.name = name;
        camp.intro = intro;
        camp.phoneNumber = phoneNumber;
        camp.businessNo = businessNo;
        camp.manageStatus = UNAUTHORIZED;
        camp.campReservationType = CampReservationType.valueOf(campReservationType);
        camp.animalCapabilityType = AnimalCapabilityType.valueOf(animalCapabilityType);
        return camp;
    }

    public static Camp createMockCamp(String name, String intro, String phoneNumber, String businessNo,
        String campReservationType, String animalCapabilityType) {
        Camp camp = new Camp();
        camp.campId = 1L;
        camp.name = name;
        camp.intro = intro;
        camp.phoneNumber = phoneNumber;
        camp.businessNo = businessNo;
        camp.manageStatus = UNAUTHORIZED;
        camp.campReservationType = CampReservationType.valueOf(campReservationType);
        camp.animalCapabilityType = AnimalCapabilityType.valueOf(animalCapabilityType);
        return camp;
    }

    public void modifyCamp(String name, String intro, String phoneNumber, String businessNo,
        String campReservationType, String animalCapabilityType) {
        this.name = name;
        this.intro = intro;
        this.phoneNumber = phoneNumber;
        this.businessNo = businessNo;
        this.manageStatus = UNAUTHORIZED;
        this.campReservationType = CampReservationType.valueOf(campReservationType);
        this.animalCapabilityType = AnimalCapabilityType.valueOf(animalCapabilityType);
    }

    public void addFirstImage(AttachFile firstImage) {
        this.firstImage = firstImage;
    }

    public void addMember(Member member) {
        this.member = member;
    }

    public void addCampLocation(CampLocation campLocation) {
        if (campLocation.getCamp() == null) {
            campLocation.addCamp(this);
        }
        this.campLocation = campLocation;
    }

    public void addCampDetail(CampDetail campDetail) {
        if (campDetail.getCamp() == null) {
            campDetail.addCamp(this);
        }
        this.campDetail = campDetail;
    }

    public void addCampOpenSeasonList(List<CampOpenSeason> campOpenSeasonList) {
        campOpenSeasonList.stream().filter(campOpenSeason -> campOpenSeason.getCamp() == null)
            .forEach(campOpenSeason -> campOpenSeason.addCamp(this));
        this.campOpenSeasons = campOpenSeasonList;
    }

    public void addCampOpenDayList(List<CampOpenDay> campOpenDayList) {
        campOpenDayList.stream().filter(campOpenSeason -> campOpenSeason.getCamp() == null)
            .forEach(campOpenSeason -> campOpenSeason.addCamp(this));
        this.campOpenDays = campOpenDayList;
    }

    public void addCampFacilityList(List<CampFacility> campFacilityList) {
        campFacilityList.stream().filter(campOpenSeason -> campOpenSeason.getCamp() == null)
            .forEach(campOpenSeason -> campOpenSeason.addCamp(this));
        this.campFacilities = campFacilityList;
    }

    public CampAuth requestAuthorization() {
        return CampAuth.createCampAuth(this);
    }

    //============== NOT YET REFACTORING ===================

    public void editManageStatus() {
        this.manageStatus = ManageStatus.AUTHORIZED;

    }

    public void addSite(Site site) {
        sites.add(site);
    }

}
