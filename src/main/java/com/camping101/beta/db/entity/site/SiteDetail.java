package com.camping101.beta.db.entity.site;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Integer refundableDate;

    private String policy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

    public SiteDetail(Integer refundableDate, String policy) {
        this.refundableDate = refundableDate;
        this.policy = policy;
    }

    public static SiteDetail create(int refundableDate, String policy) {
        SiteDetail siteDetail = new SiteDetail();
        siteDetail.refundableDate = refundableDate;
        siteDetail.policy = policy;
        return siteDetail;
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
        SiteDetail that = (SiteDetail) o;
        return Objects.equals(getRefundableDate(), that.getRefundableDate())
            && Objects.equals(getPolicy(), that.getPolicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRefundableDate(), getPolicy());
    }
}
