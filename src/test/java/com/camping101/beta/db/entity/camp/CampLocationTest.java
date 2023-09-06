package com.camping101.beta.db.entity.camp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampLocationTest {

    @Test
    @DisplayName("campLocation을 만든다.")
    void createTest(){
        // given // when
        CampLocation campLocation =
            CampLocation.create(
                "강", "수원시", "영통구", "1234", "5678"
            );

        // then
        assertThat(campLocation).isEqualTo(new CampLocation("강", "수원시", "영통구", "1234", "5678"));

    }

}