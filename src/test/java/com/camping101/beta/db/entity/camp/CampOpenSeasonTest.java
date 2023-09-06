package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.enums.Season.SUMMER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampOpenSeasonTest {

    @Test
    @DisplayName("CampOpenSeason을 만든다.")
    void createTest(){
        // given // when
        CampOpenSeason campOpenSeason = CampOpenSeason.create("SUMMER");

        // then
        assertThat(campOpenSeason).isEqualTo(new CampOpenSeason(SUMMER));
    }

}