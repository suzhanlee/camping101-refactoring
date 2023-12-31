package com.camping101.beta.db.entity.site;

import static javax.persistence.EnumType.STRING;

import com.camping101.beta.db.entity.site.enums.SiteCapacityType;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(STRING)
    @Column(nullable = false)
    private SiteCapacityType siteCapacityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

    public SiteCapacity(SiteCapacityType siteCapacityType) {
        this.siteCapacityType = siteCapacityType;
    }

    public static SiteCapacity create(String siteCapacityType) {
        SiteCapacity siteCapacity = new SiteCapacity();
        siteCapacity.siteCapacityType = SiteCapacityType.valueOf(siteCapacityType);
        return siteCapacity;
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
        SiteCapacity that = (SiteCapacity) o;
        return getSiteCapacityType() == that.getSiteCapacityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSiteCapacityType());
    }
}
