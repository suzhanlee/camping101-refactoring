package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.enums.DayOfTheWeek.MONDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.camping101.beta.db.entity.camp.enums.DayOfTheWeek;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampOpenDayTest {

    @Test
    @DisplayName("CampOpenDay를 만든다.")
    void createTest(){
        // given // when
        CampOpenDay campOpenDay = CampOpenDay.create("MONDAY");

        // then
        assertThat(campOpenDay).isEqualTo(new CampOpenDay(MONDAY));
    }

}