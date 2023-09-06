package com.camping101.beta.web.domain.camp.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.camp.CampLocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampLocationDtoTest {

    @Test
    @DisplayName("dto로 campLocation을 만든다.")
    void toEntityTest() {
        // given
        CampLocationDto campLocationDto =
            new CampLocationDto(
                "강", "수원시", "영통구", "1234", "5678"
            );

        // when
        CampLocation campLocation = campLocationDto.toEntity();

        // then
        assertThat(campLocation).isEqualTo(
            new CampLocation(
                "강", "수원시", "영통구", "1234", "5678"
            )
        );
    }

}