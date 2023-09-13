package com.camping101.beta.web.domain.site.model.dto;

import static com.camping101.beta.db.entity.site.enums.SiteCapacityType.BASIC;
import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.site.SiteCapacity;
import javax.persistence.Basic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiteCapacityDtoTest {

    @Test
    @DisplayName("dto로 SiteCapacity를 만들 수 있습니다.")
    void toEntityTest(){
        // given
        SiteCapacityDto siteCapacityDto = new SiteCapacityDto("BASIC");

        // when
        SiteCapacity siteCapacity = siteCapacityDto.toEntity();

        // then
        assertThat(siteCapacity).isEqualTo(new SiteCapacity(BASIC));
    }
}