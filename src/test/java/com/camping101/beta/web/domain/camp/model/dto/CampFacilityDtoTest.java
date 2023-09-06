package com.camping101.beta.web.domain.camp.model.dto;

import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.camp.CampFacility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampFacilityDtoTest {

    @Test
    @DisplayName("dto로 CampFacility를 만든다.")
    void toEntityTest() {
        // given
        CampFacilityDto campFacilityDto = new CampFacilityDto("TOILET", 3);

        // when
        CampFacility campFacility = campFacilityDto.toEntity();

        // then
        assertThat(campFacility).isEqualTo(new CampFacility(TOILET, 3));
    }

}