package com.camping101.beta.db.entity.site;

import com.camping101.beta.db.entity.site.enums.SiteCapabilityType;
import java.util.Objects;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteCapability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SiteCapabilityType siteCapabilityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

    public SiteCapability(SiteCapabilityType siteCapabilityType) {
        this.siteCapabilityType = siteCapabilityType;
    }

    public static SiteCapability create(String siteCapabilityType) {
        SiteCapability siteCapability = new SiteCapability();
        siteCapability.siteCapabilityType = SiteCapabilityType.valueOf(siteCapabilityType);
        return siteCapability;
    }

    public void addSite(Site site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteCapability that = (SiteCapability) o;
        return getSiteCapabilityType() == that.getSiteCapabilityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSiteCapabilityType());
    }
}
