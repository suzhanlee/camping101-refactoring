package com.camping101.beta.web.domain.site.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.site.SiteDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiteDetailDtoTest {

    @Test
    @DisplayName("dto로 SiteDetail을 만들 수 있습니다.")
    void toEntityTest() {
        // given
        SiteDetailDto siteDetailDto = new SiteDetailDto(3, "사이트 정책");

        // when
        SiteDetail siteDetail = siteDetailDto.toEntity();

        // then
        assertThat(siteDetail).isEqualTo(new SiteDetail(3, "사이트 정책"));
    }
}