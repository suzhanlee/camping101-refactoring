package com.camping101.beta.web.domain.camp.model.dto;

import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.camp.CampOpenDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampOpenDayDtoTest {

    @Test
    @DisplayName("dto로 CampOpenDay를 만든다.")
    void toEntityTest(){
        // given
        CampOpenDayDto campOpenDayDto = new CampOpenDayDto("MONDAY");

        // when
        CampOpenDay campOpenDay = campOpenDayDto.toEntity();

        // then
        assertThat(campOpenDay).isEqualTo(new CampOpenDay(MONDAY));
    }

}