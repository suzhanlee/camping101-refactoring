package com.camping101.beta.db.entity.site;

import java.util.List;

public class SiteRelation {

    public static Site setRelation(Site site, SiteDetail siteDetail,
        List<SiteCapacity> siteCapacityList, List<SiteCapability> siteCapabilityList,
        List<SiteFile> siteFileList) {

        site.addSiteDetail(siteDetail);
        site.addSiteCapacities(siteCapacityList);
        site.addSiteCapabilities(siteCapabilityList);
        site.addSiteFiles(siteFileList);

        return site;
    }
}
