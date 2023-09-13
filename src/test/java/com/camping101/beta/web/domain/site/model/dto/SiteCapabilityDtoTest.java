package com.camping101.beta.web.domain.site.model.dto;

import static com.camping101.beta.db.entity.site.enums.SiteCapabilityType.ANIMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.camping101.beta.db.entity.site.QSiteCapability;
import com.camping101.beta.db.entity.site.SiteCapability;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiteCapabilityDtoTest {

    @Test
    @DisplayName("dto로 SiteCapability를 만들 수 있습니다.")
    void toEntityTest(){
        // given
        SiteCapabilityDto siteCapabilityDto = new SiteCapabilityDto("ANIMAL");

        // when
        SiteCapability siteCapability = siteCapabilityDto.toEntity();

        // then
        assertThat(siteCapability).isEqualTo(new SiteCapability(ANIMAL));
    }
}