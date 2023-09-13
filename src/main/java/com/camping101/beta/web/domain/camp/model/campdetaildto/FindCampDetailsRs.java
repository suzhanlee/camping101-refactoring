package com.camping101.beta.web.domain.camp.model.campdetaildto;

import com.camping101.beta.db.entity.camp.CampLocation;
import com.camping101.beta.db.entity.camp.enums.CampManageStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class FindCampDetailsRs {

    private Long campId;
    private String name;
    private String intro;
    private CampManageStatus campManageStatus;
    private CampLocation campLocation;
    private String tel;
    private String oneLineReserveYn;
    private String openSeason;
    private LocalDate openDateOfWeek;
//    private FacilityCnt facilityCnt;
    private String facility;
    private String leisure;
    private String animalCapable;
    private String equipmentTools;
    private String firstImage;
    private String homepage;
    private String businessNo;
    private List<SiteInCamp> siteInCampList = new ArrayList<>();
    private List<CampLogInCamp> campLogInCampList = new ArrayList<>();


    public FindCampDetailsRs(Long campId, String name, String intro, CampManageStatus campManageStatus,
        CampLocation campLocation, String tel, String oneLineReserveYn, String openSeason,
        LocalDate openDateOfWeek, String facility, String leisure,
        String animalCapable, String equipmentTools, String firstImage, String homepage,
        String businessNo) {
        this.campId = campId;
        this.name = name;
        this.intro = intro;
        this.campManageStatus = campManageStatus;
        this.campLocation = campLocation;
        this.tel = tel;
        this.oneLineReserveYn = oneLineReserveYn;
        this.openSeason = openSeason;
        this.openDateOfWeek = openDateOfWeek;
//        this.facilityCnt = facilityCnt;
        this.facility = facility;
        this.leisure = leisure;
        this.animalCapable = animalCapable;
        this.equipmentTools = equipmentTools;
        this.firstImage = firstImage;
        this.homepage = homepage;
        this.businessNo = businessNo;
    }

    public void addSiteInCamp(List<SiteInCamp> sites) {
        this.siteInCampList = sites;
    }

    public void addCampLogInCamp(List<CampLogInCamp> campLogs) {
        this.campLogInCampList = campLogs;
    }
}
