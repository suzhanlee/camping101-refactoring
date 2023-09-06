package com.camping101.beta.web.domain.camp.model.dto;

import static com.camping101.beta.db.entity.camp.enums.Season.SUMMER;
import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.camp.CampOpenSeason;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampOpenSeasonDtoTest {

    @Test
    @DisplayName("dto로 CampOpenSeason을 만든다.")
    void toEntityTest(){
        // given
        CampOpenSeasonDto campOpenSeasonDto = new CampOpenSeasonDto("SUMMER");

        // when
        CampOpenSeason campOpenSeason = campOpenSeasonDto.toEntity();

        // then
        assertThat(campOpenSeason).isEqualTo(new CampOpenSeason(SUMMER));
    }

}