package com.camping101.beta.web.domain.site.model.dto;

import com.camping101.beta.db.entity.site.SiteDetail;
import lombok.Getter;

@Getter
public class SiteDetailDto {

    private Integer refundableDate;

    private String policy;

    public SiteDetailDto(int refundableDate, String policy) {
        this.refundableDate = refundableDate;
        this.policy = policy;
    }

    public SiteDetail toEntity() {
        return SiteDetail.create(refundableDate, policy);
    }
}
