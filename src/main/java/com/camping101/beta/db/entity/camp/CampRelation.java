package com.camping101.beta.db.entity.camp;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import java.util.List;

public class CampRelation {

    public static Camp setRelation(Camp camp, AttachFile attachFile, CampLocation campLocation,
        CampDetail campDetail, List<CampOpenSeason> campOpenSeasonList,
        List<CampOpenDay> campOpenDayList, List<CampFacility> campFacilityList) {

        camp.addFirstImage(attachFile);
        camp.addCampLocation(campLocation);
        camp.addCampDetail(campDetail);

        camp.addCampOpenSeasonList(campOpenSeasonList);
        camp.addCampOpenDayList(campOpenDayList);
        camp.addCampFacilityList(campFacilityList);

        return camp;
    }
}
